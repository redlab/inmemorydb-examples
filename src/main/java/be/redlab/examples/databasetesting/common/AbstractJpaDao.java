/*
 * Copyright 2013 Balder Van Camp
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package be.redlab.examples.databasetesting.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SuppressWarnings("unchecked")
public abstract class AbstractJpaDao<T extends Entity, Id extends Serializable> {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractJpaDao.class);

	/**
	 * Extending class provide the applications entityManager through this method.
	 *
	 * @return the EntityManager
	 */
	protected abstract EntityManager getEntityManager();

	private Class<T> persistentClass;

	public AbstractJpaDao() {
		try {
			ParameterizedType parameterizedType = getGenericParameterizedType(getClass());
			this.persistentClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		} catch (Exception e) {
			LOG.error("Class cast exception?", e);
		}
	}

	public Class<T> getEntityClass() {
		return persistentClass;
	}

	public T findById(final Id id) {
		return getEntityManager().find(persistentClass, id);
	}

	public T update(final T entity) {
		return getEntityManager().merge(entity);
	}

	public void create(final T entity) {
		getEntityManager().persist(entity);
	}

	public void delete(final T entity) {
		getEntityManager().remove(entity);
	}

	public void delete(final Id id) {
		getEntityManager().remove(findById(id));
	}

	public List<T> findAll() {
		return findByCriteria();
	}

	public long countAll() {
		return countByCriteria();
	}

	public void flush() {
		getEntityManager().flush();
	}

	public List<T> findByEntity(final T entity) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.add(Example.create(entity).excludeZeroes());
		return crit.list();
	}

	protected List<T> findByNamedQuery(final String queryName, final Pair<String, ?>... params) {
		Query query = createNamedQuery(queryName);
		setQueryParams(query, params);
		return query.getResultList();
	}

	protected List<T> findByNamedQuery(final String queryName, final Object... params) {
		Query query = createNamedQuery(queryName, params);
		return query.getResultList();
	}

	protected T findByNamedQueryUnique(final String queryName, final Pair<String, ?>... params) {
		try {
			Query query = createNamedQuery(queryName, params);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	protected T findByNamedQueryUnique(final String queryName, final Object... params) {
		try {
			Query query = createNamedQuery(queryName, params);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	protected int executeUpdateByNamedQuery(final String queryName, final Pair<String, ?>... params) {
		Query query = createNamedQuery(queryName, params);
		return query.executeUpdate();
	}

	protected int executeUpdateByNamedQuery(final String queryName, final Object... params) {
		Query query = createNamedQuery(queryName, params);
		return query.executeUpdate();
	}

	protected List<T> findByCriteria(final Criterion... criterion) {
		return findByCriteria(-1, -1, null, criterion);
	}

	protected List<T> findByCriteria(final Order order, final Criterion... criterion) {
		return findByCriteria(-1, -1, order, criterion);
	}

	protected List<T> findByCriteria(final int firstResult, final int maxResults, final Order order, final Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (order != null) {
			crit.addOrder(order);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		return crit.list();
	}

	protected long countByCriteria(final Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		return (Long) crit.list().get(0);
	}

	protected Query createNamedQuery(final String queryName) {
		return getEntityManager().createNamedQuery(queryName);
	}

	protected Query createNamedQuery(final String queryName, final Object... params) {
		Query query = createNamedQuery(queryName);
		setQueryParams(query, params);
		return query;
	}

	protected Query createNamedQuery(final String queryName, final Pair<String, ?>[] params) {
		Query query = createNamedQuery(queryName);
		setQueryParams(query, params);
		return query;
	}

	private void setQueryParams(final Query query, final Pair<String, ?>[] params) {
		for (Pair<String, ?> param : params) {
			query.setParameter(param.getFirst(), param.getSecond());
		}
	}

	private void setQueryParams(final Query query, final Object... params) {
		int position = 1;
		for (Object param : params) {
			query.setParameter(position++, param);
		}
	}

	private ParameterizedType getGenericParameterizedType(final Type type) {
		if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
			return (ParameterizedType) type;
		}
		if (Class.class.isAssignableFrom(type.getClass())) {
			Type genericSuperType = ((Class<?>) type).getGenericSuperclass();
			return getGenericParameterizedType(genericSuperType);
		}
		return null;
	}
}