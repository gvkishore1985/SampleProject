package com.automation.pageobjects;

import org.openqa.selenium.By;

import com.automation.constants.Constants;
import com.automation.utils.PropertyUtils;

public class LoginPage {
	
	public static String baseProjectPath = System.getProperty(Constants.USER_DIR); //c://users.//srujan/workspace//AutomatonTesting
	public static PropertyUtils repository = new PropertyUtils(
			baseProjectPath.concat(Constants.LOGIN_PAGE_OBJECTREPOSITORY_PROPERTY));
								
	
	public static By uname=By.id(repository.getProperty("SF_USERNAME"));
	public static By pswd=By.id(repository.getProperty("SF_PASSWORD"));
	public static By login=By.id(repository.getProperty("SF_LOGIN_BUTTON"));
	
	
	
	
	
	
}