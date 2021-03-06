/*
 * Copyright 2014 Balder Van Camp
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
package be.redlab.examples.databasetesting.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.redlab.examples.databasetesting.common.AbstractJpaDao;
import be.redlab.examples.databasetesting.dao.DrinkDao;
import be.redlab.examples.databasetesting.entity.Drink;

/**
 * @author redlab
 *
 */
@Repository
public class DrinkDaoImpl extends AbstractJpaDao<Drink, Long> implements DrinkDao {
	
	@PersistenceContext(unitName="persistenceUnitPU")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
