package autom.common;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;

import autom.common.TestActions;

@Listeners({ScreenshotTestRule.class})
public abstract class MasterTest {
    private static final int GLOBAL_WAIT_SECONDS = TestPrefs.getGlobalTimeoutSeconds();
    private static final int GLOBAL_LONGWAIT_SECONDS = TestPrefs.getGlobalLongTimeoutSeconds();
    private static final String IE_DRIVER_SERVER = TestPrefs.getIEDriver();
    private static final long CREATE_REMOTEWEBDRIVER_RETRY_DELAY_SECONDS = 5;
    protected static final String ADMIN_USERNAME = "admin";
    protected static final String ADMIN_PASSWORD= "admin";
    protected static final String ADMIN_UI_PATH = "/vs4/admin";
    protected static final String VIDSHIELD_UI_PATH = "/vs4/templates/index.jsp";
    protected static final String RISKSHIELD_UI_PATH = "/vs4/situation";
    protected final static String SERVER_URL = TestPrefs.getServerURL();
    protected static int MAX_PERF_FILE_LINES_AMOUNT = TestPrefs.getMaxPerfFileLinesAmount();
    protected static int MAX_PERF_FILE_LINES_AMOUNT_CURRENT_RELEASE =TestPrefs.getMaxPerfFileLinesAmountCurrentRelease();
    protected static String buildVersion = null;
    protected static boolean AD=TestPrefs.getIsAuthModeTest();
    // Selenium WebDriver used to drive tests
	public static WebDriver driver;

	// Selenium Action builder to construct chains of actions
	public static Actions builder;
	
	// used in waiting for alerts to come up
	public static WebDriverWait wait;
    // used in waiting for cameras to come up
    public static WebDriverWait longWait;

	public void setUp(String path) {
		MyPrint.myPrint("setUp", "Start Setup");
		ScreenshotTestRule.cleanScreenshots();
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("enableNativeEvents", true);
		caps.setCapability("requireWindowFocus", true);
		caps.setCapability(
		    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		    true);
		caps.setVersion(TestPrefs.getIEVersion());

		File file = new File(IE_DRIVER_SERVER);
		if (!file.exists()) {
			MyPrint.myPrint("Setup", "IE Driver not found " + file.getAbsolutePath());
			return;
		}
		
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		
		if (TestPrefs.getSeleniumClientType().equals("grid")) {
			// Construct the URL
			URL sgcUrl = null;
			try {
				sgcUrl = new URL(TestPrefs.getSeleniumGridClient());
			} catch (MalformedURLException mue) {
				fail("Malformed URL for SeleniumGridClient: " + TestPrefs.getSeleniumGridClient());
			}
			
			// Create the RemoteWebDriver
			try {
				driver = new RemoteWebDriver(sgcUrl, caps);
			} catch (IllegalStateException ise) {
				// The one observed instance (so far) of this exception reports "The process has not exited yet therefore no result is available ...".
				// We suspect it may have been due to a transient condition; so briefly sleep, then retry. If the failure recurs, give up.
				MyPrint.myPrint("Setup", "creation of RemoteWebDriver failed with IllegalStateException; retrying...");
				
				try {
					Thread.sleep(CREATE_REMOTEWEBDRIVER_RETRY_DELAY_SECONDS * 1000);
				} catch (InterruptedException e) {
					// ignore, continue
				}
				
				try {
					driver = new RemoteWebDriver(sgcUrl, caps);
				} catch (IllegalStateException ise2) {
					fail("Failed both initial attempt and retry to create RemoteWebDriver with IllegalStateException: " + ise2.getLocalizedMessage());
				} catch (Exception e) {
					fail("Unexpected exception on retry to create RemoteWebDriver: " + e.getLocalizedMessage());
				}
			} catch (Exception e) {
				fail("Unexpected exception on initial attempt to create RemoteWebDriver: " + e.getLocalizedMessage());
			}
		} else
			driver = new InternetExplorerDriver(caps);
	
	
		wait = new WebDriverWait(driver, GLOBAL_WAIT_SECONDS);
        longWait = new WebDriverWait(driver, GLOBAL_LONGWAIT_SECONDS);

		builder = new Actions(driver);
		MyPrint.myPrint("Start tests", "Started browser");
		driver.get(SERVER_URL + path);
		
		driver.manage().window().maximize();
	}

	public void setUp(String path,boolean bFullURL) {
		MyPrint.myPrint("setUp", "Start Setup");
		ScreenshotTestRule.cleanScreenshots();
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability("enableNativeEvents", true);
		caps.setCapability("requireWindowFocus", true);
		caps.setCapability(
		    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		    true);
		caps.setVersion(TestPrefs.getIEVersion());

		File file = new File(IE_DRIVER_SERVER);
		if (!file.exists()) {
			MyPrint.myPrint("Setup", "IE Driver not found " + file.getAbsolutePath());
			return;
		}
		
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		
		if (TestPrefs.getSeleniumClientType().equals("grid")) {
			// Construct the URL
			URL sgcUrl = null;
			try {
				sgcUrl = new URL(path);
			} catch (MalformedURLException mue) {
				fail("Malformed URL for SeleniumGridClient: " + TestPrefs.getSeleniumGridClient());
			}
			
			// Create the RemoteWebDriver
			try {
				driver = new RemoteWebDriver(sgcUrl, caps);
			} catch (IllegalStateException ise) {
				// The one observed instance (so far) of this exception reports "The process has not exited yet therefore no result is available ...".
				// We suspect it may have been due to a transient condition; so briefly sleep, then retry. If the failure recurs, give up.
				MyPrint.myPrint("Setup", "creation of RemoteWebDriver failed with IllegalStateException; retrying...");
				
				try {
					Thread.sleep(CREATE_REMOTEWEBDRIVER_RETRY_DELAY_SECONDS * 1000);
				} catch (InterruptedException e) {
					// ignore, continue
				}
				
				try {
					driver = new RemoteWebDriver(sgcUrl, caps);
				} catch (IllegalStateException ise2) {
					fail("Failed both initial attempt and retry to create RemoteWebDriver with IllegalStateException: " + ise2.getLocalizedMessage());
				} catch (Exception e) {
					fail("Unexpected exception on retry to create RemoteWebDriver: " + e.getLocalizedMessage());
				}
			} catch (Exception e) {
				fail("Unexpected exception on initial attempt to create RemoteWebDriver: " + e.getLocalizedMessage());
			}
		} else
			driver = new InternetExplorerDriver(caps);
	
	
		wait = new WebDriverWait(driver, GLOBAL_WAIT_SECONDS);
        longWait = new WebDriverWait(driver, GLOBAL_LONGWAIT_SECONDS);

		builder = new Actions(driver);
		MyPrint.myPrint("Start tests", "Started browser");
		driver.get(path);
		
		driver.manage().window().maximize();
	}

	
	public void CleanBrowserOpenURL(String URL){
		try {
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer32.exe");			
			Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
		try {
				Thread.sleep(2000);
			} catch (Exception ignore) {}		    
		} catch (Exception ignore) {}
		///////////////debug code.
//		URL="http://Google.com";
		
		if(URL.toLowerCase().startsWith("http")){
			setUp(URL,true);
			TestActions.robotWait(5);
		} else {
			setUp(URL);
			driver.manage().deleteAllCookies();
		}
		try {
			Thread.sleep(1000);
		} catch (Exception ignore) {}
	}	
	

	public void tearDown() {
		if (driver != null)
			driver.quit();
		
		MyPrint.myPrint("Finished tests", "Closed Browser");
		MyPrint.myPrint("", "----------------------------------------------------------------------");
	}

	// We only want to hide exceptions on tests we're failing on purpose.
    public void enterCredentials(String uname, String pw) throws Exception {
        enterCredentials(uname, pw, false);
    }
    
    public void enterCredentials(String uname, String pw, boolean hideExceptions) throws Exception {
        bypassSecurityCertificate();
        try {
        	By oLoginButton=By.xpath("//form[@id='loginForm']/table[@id='loginContainer_inputTable']/tbody/tr//td/div[@id='formLoginButton']");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("formLoginButton")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']/table/tbody/tr/td/div[contains(text(), 'Username:')]")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='j_username']")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='j_password']")));
            WebElement BuildVersion = driver.findElement(By.id("loginContainer_revision"));
            buildVersion = BuildVersion.getText();
            MyPrint.myPrint("enterCredentials", "Build# " + BuildVersion.getText());
            TestActions.robotWait(1);
            @SuppressWarnings("unused")
			WebElement passwordField = driver.findElement(By.id("userpass"));
            WebElement userNameField = driver.findElement(By.id("user"));
            userNameField.click();
            TestActions.robotWait(1);
            userNameField.clear();
            TestActions.robotWait(1);
            userNameField.sendKeys(uname);
            TestActions.robotWait(1);
            TestActions.ExcuteKeyEvent(KeyEvent.VK_TAB);
            MyPrint.myPrint("User", driver.findElement(By.id("user")).getAttribute("value"));
//            passwordField.click();
//            TestActions.robotWait(1);
//            passwordField.clear();
//            passwordField.sendKeys(pw);            
            By oPasswd=By.xpath("//input[@name='j_password']");
        	driver.findElement(oPasswd).click();
            TestActions.robotWait(1);
        	driver.findElement(oPasswd).clear();
            TestActions.robotWait(1);
        	driver.findElement(oPasswd).sendKeys(pw);
            MyPrint.myPrint("Password", driver.findElement(oPasswd).getAttribute("value"));
            TestActions.robotWait(2);
            TestActions.ExcuteKeyEvent(KeyEvent.VK_TAB);
            TestActions.robotWait(2);
//            TestActions.ExcuteKeyEvent(10);
            
            driver.findElement(oLoginButton).click();
                        
            MyPrint.myPrint("Login Button Clicked.", "");
            TestActions.robotWait(9);
            try{
                if(driver.findElement(By.id("loginContainer_error")).isDisplayed()){return;}
                if(driver.findElement(oLoginButton).isDisplayed()){return;}
            } catch (NoSuchElementException e2){}
            
            if (!hideExceptions) longWait.until(ExpectedConditions.elementToBeClickable(By.id("helpButton")));
        	} catch (Exception e) {
        		if (!hideExceptions) {
//                AssertJUnit.fail(e.toString());
        		}
        }
    }

    public boolean bypassSecurityCertificate() {
        // Accept Certificate Warning if it's there.  Note the normal "By.id" syntax
        // doesn't work for this link because IE seems to be generating this page internally, 
        // so we inject JS here.  Ignore errors if it doesn't work.
        try {
            if (driver.getTitle().contains("Certificate")) {
                driver.navigate().to("javascript:document.getElementById('overridelink').click()");
                MyPrint.myPrint("bypassSecurityCertificate", "Security warning bypassed.");
                return true;
            }
        } catch (Exception ignore) {}
        return false;
    }
	
	// Navigate to admin UI by clicking Admin Button
	public void clickAdminLink() throws InterruptedException {
		MyPrint.myPrint("clickAdminLink", "Click Admin Button");
		WebElement adminButton=driver.findElement(By.id("adminSiteButton"));
        Action hover = builder.moveToElement(adminButton).click().build();
        hover.perform();

		longWait.until(ExpectedConditions.elementToBeClickable(By.id("clientButton")));

        try {
            assertNotNull(driver.findElement(By.id("clientButton")));
		} catch (NoSuchElementException e) {
			fail("Client UI link not found...Fail to navigate to admin UI.");
		}
	}

	// Navigate to admin UI by clicking Admin Button
	public void clickClientUILink() throws InterruptedException {
		MyPrint.myPrint("clickClientUILink", "Click Admin Button");
        WebElement clientButton=driver.findElement(By.id("clientButton"));
        Action hover = builder.moveToElement(clientButton).click().build();
        hover.perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("logoutButton")));

		try {
			assertNotNull(driver.findElement(By.id("adminSiteButton")));
		} catch (NoSuchElementException e) {
			fail("Client UI link not found...Fail to navigate to client UI.");
		}
	}

	// expand the camera sidebar menus in the client UI
	public void expandMenus(String dirName) throws InterruptedException {
		MyPrint.myPrint("expandMenus", "Clicking on menu " + dirName);
		longWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(dirName)));
		try {
            WebElement clientButton=driver.findElement(By.partialLinkText(dirName));
            Action hover = builder.moveToElement(clientButton).click().build();
            hover.perform();

		} catch (NoSuchElementException e) {
			fail("Menus were not properly expanded.");
		}
	}

	public static String GetUniqueFilename() {
		String out = new SimpleDateFormat("yyyy-MM-dd'.txt'")
				.format(new java.util.Date());
		String fileName = "GetProcess_" + out;
		return fileName;
	}
	

	public ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
		  return new ExpectedCondition<WebElement>() {
		    public WebElement apply(WebDriver driver) {
		      WebElement toReturn = driver.findElement(locator);
		      if (toReturn.isDisplayed()) {
		        return toReturn;
		      }
		      return null;
		    }
		  };
		}
	
	public void showElementsFor(final WebDriver driver, String name) {
		 final List<WebElement> iframes = driver.findElements(By.tagName(name));
		    for (WebElement iframe : iframes) {
		    	MyPrint.myPrint("showElements", "element " + iframe.getTagName() + " text " + iframe.getText() + " " + iframe.getAttribute("id"));
		    }
	}
	
	/**
	 * Waits for document ready state
	 */
	protected void waitForLoad() {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    new WebDriverWait(driver, 30);
	    wait.until(pageLoadCondition);
	}

	/**
	 * Accepts JS alerts
	 */
    protected void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    protected void acceptPopupDialog() {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("nomadPrompt_ok")));

        driver.findElement(By.id("nomadPrompt_ok")).click();
    }
    
    /** 
     * Waits for blockUI loading screen to complete
     */
    protected void waitForBlockUI() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='blockUI blockOverlay']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='applicationUnLoadingSplashScreen']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='restartNotify']")));
    }
    
    public void waitForJS() {    	
    	try {
    		waitForLoad();
    		waitForBlockUI();
    		if (waitForJStoLoad(driver));
    	} catch (Exception e) {
    		AssertJUnit.fail(e.toString());
    	}
    }
    
    public boolean waitForJStoLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver driver) {
            try {
            	JavascriptExecutor js = (JavascriptExecutor) driver;
            	return ((Long)(js.executeScript("return jQuery.active")) == 0);
            }
            catch (Exception e) {
              return true;
            }
          }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver driver) {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	return js.executeScript("return document.readyState").toString().equals("complete");
          }
        };
      return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    
}
