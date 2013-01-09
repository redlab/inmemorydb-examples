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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.redlab.examples.databasetesting.common.AbstractEntity;

/**
 * @author redlab
 *
 */
@Entity
@Table(name="drink")
public class Drink extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private Double price;
	private String name;
	private Bar bar;
	/**
	 * @return the price
	 */
	@Column(name="price", precision=2,nullable=false)
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the name
	 */
	@Column(name="name", length=100,nullable=false)
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
	 * @return the bar
	 */
	@ManyToOne(optional=false)
    @JoinColumn(name="bar_id", nullable=false, updatable=false)
	public Bar getBar() {
		return bar;
	}
	/**
	 * @param bar the bar to set
	 */
	public void setBar(Bar bar) {
		this.bar = bar;
	}
	
}
