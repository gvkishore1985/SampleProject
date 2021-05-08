package com.automation.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.constants.Constants;
import com.automation.exception.*;
import com.automation.utils.PropertyUtils;

/**
 * The Class SeleniumNGSuite.
 */
public class SeleniumNGSuite {

	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(SeleniumNGSuite.class);

	/** The base project path. */
	public static String baseProjectPath = System.getProperty(Constants.USER_DIR);

	/** The configprops. */
	public static PropertyUtils configprops = new PropertyUtils(baseProjectPath.concat(Constants.CONFIG_PROPERTY));

	/** The browser type. */
	public static String browserType = configprops.getProperty("browser_name");

	/** The url. */
	public static String url = configprops.getProperty(Constants.URL);

	/** The current suite. */
	public static String currentSuite = "";

	/** The config. */
	public DriverConfig config = new DriverConfig();
	
	

	/**
	 * Sets the up suite.
	 *
	 * @throws Throwable
	 *             the throwable
	 */
	public void setUpSuite() throws Throwable {
		config.setUp(browserType, url);
	}

	/**
	 * Tear down.
	 *
	 */
	public void tearDown() throws AutomationException {

		try {

			if (LocalDriverManager.getDriver() != null) {
				LocalDriverManager.getDriver().close();
			}
			if (LocalDriverManager.getDriver() != null) {
				LocalDriverManager.getDriver().quit();
			}
//			config.folderDeletion();
			LOG.info("Successfully closed the browser ");
		}catch (Exception exception) {
			LOG.error("Error in closing the browser:: {}", exception.getMessage());
			exception.printStackTrace();
			throw new AutomationException(exception);
		}

	}
	
	
}