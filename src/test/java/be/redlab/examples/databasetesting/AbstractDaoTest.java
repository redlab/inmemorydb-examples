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
package be.redlab.examples.databasetesting;

import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * Abstract base class for running DAO tests. All tests requiring an in-memory database should use this as superclass.<br />
 * This class defines through annotations that the JUnit tests should be run with the
 * {@link UnitilsJUnit4TestClassRunner} and a Spring {@link org.springframework.context.ApplicationContext} should be
 * loaded using the config-memorytest.xml file from the classpath.
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext({ "classpath:config-memorytest.xml" })
public abstract class AbstractDaoTest {
}
