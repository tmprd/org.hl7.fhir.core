package org.hl7.fhir.validation.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
  ValidationTestSuite.class, 
  ValidationEngineTests.class,
  JsonSchemaTests.class,
  CDAValidationTestCase.class})
public class AllTests {

}
