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
package be.redlab.examples.databasetesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringBeanByType;

import be.redlab.examples.databasetesting.dao.impl.BarDaoImpl;
import be.redlab.examples.databasetesting.entity.Bar;

/**
 * Test with programmatic creation of data.<br>
 * This test extends from {@link AbstractDaoTest} where the Spring and Unitils configuration setup is defined. In this
 * test we create an entity in {@link #setup()} and in {@link #getByName()} we try to fetch the stored entity. To be
 * sure that we don't get that same entity for another name {@link #getUnknownByName()} tries to retrieve an unknown {@link Bar}.
 */
@Transactional(value = TransactionMode.ROLLBACK)
public class BarDaoImplProgrammaticTest extends AbstractDaoTest {

	private static final String NAME = "Softies";
	@SpringBeanByType
	private BarDaoImpl barDao;
	private Bar valid;

	/**
	 * Create the entity
	 */
	@Before
	public void setup() {
		valid = new Bar();
		valid.setName(NAME);
		valid.setOwner("Soft Ties");
		barDao.create(valid);
	}

	/**
	 * Fetch the entity and validate it is not null
	 */
	@Test
	public void getByName() {
		Assert.assertNotNull(barDao.getByName(NAME));
	}

	@Test
	public void getUnknownByName() {
		Assert.assertNull(barDao.getByName("unknown"));
	}

}
