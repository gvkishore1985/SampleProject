package com.automation.config;



import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ThreadGuard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.constants.Constants;
import com.automation.exception.AutomationException;
import com.automation.utils.PropertyUtils;


/**
 * The Class DriverConfig.
 *
 */
public class DriverConfig {

	/** The driver. */
	// private static ChromeDriverService service;
	public WebDriver driver = null;
	

	/** The base project path. */
	public static String baseProjectPath = System.getProperty(Constants.USER_DIR);
	
	/** The configprops. */
	public static PropertyUtils configprops = new PropertyUtils(baseProjectPath.concat(Constants.CONFIG_PROPERTY));

	/** The handle popups. */
	public static String handlePopups = configprops.getProperty(Constants.HANDLE_POPUP);

	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(DriverConfig.class);
	
	public static File folder;
	
	/**
	 * This method returns the driver object for the given browser type with the
	 * page loaded with the given url.
	 *
	 * @param browserType
	 *   the browser type
	 * @param url
	 *   the url
	 * @throws Throwable
	 *    the throwable
	 */

	// TODO remotesysname
	public void setUp(String browserType, String url) throws Throwable {
		try {
			LOG.info("Setting up the driver::" + browserType);
			
			switch (browserType) {
			case Constants.MOZILLA_FIREFOX:
				try {
					System.setProperty(Constants.GEKO_DRIVER, baseProjectPath.concat(Constants.GEKO_DRIVER_PATH));
					driver = ThreadGuard.protect(new FirefoxDriver());
					LocalDriverManager.setWebDriver(driver);
				} catch (Exception exception) {
					exception.printStackTrace();
					LOG.error("Error while loading Firefox driver::{}", exception.getMessage());
				} catch (Throwable throwable) {
					LOG.error("Error while loading Firefox driver::{}", throwable.getMessage());
				}
				break;
			case Constants.CHROME:
				try {
					System.setProperty(Constants.CHROME_WEBDRIVER,
							baseProjectPath.concat(Constants.CHROME_DRIVER_PATH));
					DesiredCapabilities chromeCapabilities=downloadingFileSetUp();
					driver = ThreadGuard.protect(new ChromeDriver(chromeCapabilities));
					LocalDriverManager.setWebDriver(driver);
				} catch (Exception exception) {
					LOG.error("Error while loading Chrome driver::{}", exception.getMessage());
				} catch (Throwable throwable) {
					LOG.error("Error while loading Chrome driver::{}", throwable.getMessage());
				}
				break;

			case Constants.IE:
				try {
					
						System.setProperty(Constants.IE_DRIVER, baseProjectPath.concat(Constants.IE_DRIVER_PATH));
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				/*	caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
					caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
					caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
					caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);*/
					caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					/*caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
					driver = ThreadGuard.protect(new InternetExplorerDriver(caps)); 
					
					LocalDriverManager.setWebDriver(driver);
					
					LocalDriverManager.getDriver().manage().window().maximize();
				} catch (Exception exception) {
					LOG.error("Error while loading Internet Explorer driver::{}", exception.getMessage());
				} catch (Throwable throwable) {
					LOG.error("Error while loading Internet Explorer driver::{}", throwable.getMessage());
				}

				break;

			case Constants.SAFARI:
				try {
				} catch (Exception exception) {
					LOG.error("Error while loading Safari driver::{}", exception.getMessage());
				} catch (Throwable throwable) {
					LOG.error("Error while loading Safari driver::{}", throwable.getMessage());
				}
				break;

			case Constants.EDGE:
				try {
					System.setProperty(Constants.EDGE_DRIVER, Constants.EDGE_PATH);
					DesiredCapabilities capabilities = DesiredCapabilities.edge();
					driver = new EdgeDriver(capabilities);
				} catch (Exception exception) {
					LOG.error("Error while loading Internet Explorer Edge driver::{}", exception.getMessage());
				} catch (Throwable throwable) {
					LOG.error("Error while loading Internet Explorer Edge driver::{}", throwable.getMessage());
				}
				break;

						}
			Thread.sleep(1000);
			LocalDriverManager.getDriver().get(url);
			LocalDriverManager.getDriver().manage().window().maximize();
			/*if (browserType.equals(Constants.IE)) {
				LocalDriverManager.getDriver().navigate()
						.to("javascript:document.getElementById('overridelink').click()");
			}*/
			
		
//			System.out.println("flag"+LocalTestDataManager.getIsGalenScenario() + LocalTestDataManager.getTestingDevice() );
			

		} catch (TimeoutException timeOutException) {
			LOG.error("Driver Timeout Exception occured::{}", timeOutException.getMessage());
			timeOutException.printStackTrace();
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE);
		} catch (Exception exception) {
			LOG.error("Exception occured in getting the driver::{}", exception.getMessage());
			throw new AutomationException(exception);
		} catch (Throwable throwable) {
			LOG.error("Error Occured in Getting the driver object ::{}", throwable.getMessage());
			throw new AutomationException(throwable);
		}

	}
	
	public DesiredCapabilities downloadingFileSetUp(){
	
 	folder=new File(UUID.randomUUID().toString());
	folder.mkdirs();
 	ChromeOptions options=new ChromeOptions();
 	Map<String, Object> prefs=new HashMap<String, Object>();
 	prefs.put("profile.default_content_settings.popups", 0);
 	prefs.put("download.default_directory", folder.getAbsolutePath());
 	options.setExperimentalOption("prefs", prefs);
 	options.setPageLoadStrategy(PageLoadStrategy.NONE);
 	DesiredCapabilities cap=DesiredCapabilities.chrome();
 	cap.setCapability(ChromeOptions.CAPABILITY, options);
 	return cap;
 }
 
 /************ deleting the folder created****************/
/* public void folderDeletion(){
 	 	
 	for(File file:folder.listFiles() ){
 		file.delete();
 	}

 	folder.delete();
 }
 */
/* public void pageLoadStrategy(){
 	ChromeOptions options = new ChromeOptions();
 	options.setPageLoadStrategy(PageLoadStrategy.NONE);
 	// Instantiate the chrome driver
 	driver = new ChromeDriver(options); 
 }*/

}
