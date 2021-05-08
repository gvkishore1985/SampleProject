package com.automation.runner;

import org.junit.runner.RunWith;

import com.automation.constants.Constants;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		features = "src\\test\\resources\\feature", 
		glue = {Constants.STEP_DEFINITIONS},
		 			 	 tags="@CH5341"
		 	 
		)
public class ExampleRunner {

}
