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
package be.redlab.examples.databasetesting.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import be.redlab.examples.databasetesting.common.AbstractEntity;

/**
 * @author redlab
 * 
 */
@Entity
@Table(name = "bar", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@NamedQueries({ @NamedQuery(name = Bar.FIND_BY_NAME, query = Bar.FIND_BY_NAME) })
public class Bar extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_NAME = "from Bar b where b.name = :name";
	private String name;
	private String owner;
	private Set<Drink> drinks = new HashSet<Drink>(0);

	/**
	 * @return the name
	 */
	@Column(name = "name", length = 120, unique = true, nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the owner
	 */
	@Column(name = "owner", length = 120, unique = false, nullable = false)
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the drinks
	 */
	@OneToMany(mappedBy = "bar")
	public Set<Drink> getDrinks() {
		return drinks;
	}

	/**
	 * @param drinks the drinks to set
	 */
	public void setDrinks(Set<Drink> drinks) {
		this.drinks = drinks;
	}

}
