package com.automation.runner;

import org.junit.runner.RunWith;

import com.automation.constants.Constants;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/*
 Thecan  Class RunnerTest.

 */
@RunWith(Cucumber.class)

@CucumberOptions(
		features = Constants.END_TO_END_FEATURES_PATH, 
		glue = {Constants.STEP_DEFINITIONS},
		 	 tags="@118Batch"
		 	 
		)
public class TestRunner {

}