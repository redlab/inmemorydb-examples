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
import org.junit.Test;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBeanByType;

import be.redlab.examples.databasetesting.dao.impl.BarDaoImpl;

/**
 * Test with programmatic creation of data.<br>
 * This test extends from {@link AbstractDaoTest} where the Spring and Unitils configuration setup is defined. We load
 * the data with the {@link DataSet} annotation.
 */
@Transactional(value = TransactionMode.ROLLBACK)
@DataSet(value = "/defaultDataset.xml")
public class BarDaoImplDataSetTest extends AbstractDaoTest {

	private static final String NAME = "Blue Mountain";
	@SpringBeanByType
	private BarDaoImpl barDao;

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

	@Test
	public void barHasAllItsDrinnks() {
		Assert.assertEquals(3, barDao.getByName(NAME).getDrinks().size());
	}
}
