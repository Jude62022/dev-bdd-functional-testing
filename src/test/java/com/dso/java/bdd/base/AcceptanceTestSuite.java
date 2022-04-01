package com.dso.java.bdd.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.dso.java.bdd.DevBddFunctionalTestingApplication;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features"},  
			glue = {"com.dso.java.bdd.base"},
				    plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
				    			 "junit:target/cucumber-reports/Cucumber.xml",
				    		         "html:target/cucumber-reports/Cucumber.html"},
publish=true)
@CucumberContextConfiguration
@SpringBootTest(classes = DevBddFunctionalTestingApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTestSuite {
}