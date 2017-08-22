package autom.common;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import autom.common.MasterTest;

public class PageLoadWait extends MasterTest{
	/**
	 * Waits for document ready state
	 */
	protected static void GenericWaitLoad(){
		try{
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException ignore){}
		try {
			TestActions.waitUntilTitleChanges(driver, "Plugin Installation");
			(new WebDriverWait(driver, 3)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver drive) {
					boolean havePopUp=false;
					JavascriptExecutor js = (JavascriptExecutor) driver;			    	
					try {
						driver.getTitle();
						havePopUp=false;
					} catch (UnhandledAlertException e) {
						havePopUp=true;
					} catch (UnreachableBrowserException ignore2){}
					if (havePopUp){
						return true;
					}
					try {
						if (js.executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete")){
							if(isElementPresent(By.id("clientButton"),driver)){
								Thread.sleep(100);	
							} else {
								if (isElementPresent((By.xpath("//div[contains(@id,'blockUI blockOverlay') or contains(@id,'applicationUnLoadingSplashScreen') or contains(@id,'restartNotify') or contains(text(),'Reconnecting...')]")),driver)){
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'blockUI blockOverlay') or contains(@id,'applicationUnLoadingSplashScreen') or contains(@id,'restartNotify') or contains(text(),'Reconnecting...')]")));
								}
								if (isElementPresent(By.xpath("//*[contains(text(),'lease wait') or contains(text(),'Loading.') or contains(text(),'loading...') or contains(text(),'Loading map')]"), driver)) {
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'lease wait') or contains(text(),'Loading.') or contains(text(),'loading...') or contains(text(),'Loading map')]")));
								} else{
									return false;
								}			    				
							}
						} else {
							return false;
						}
					} catch (NoSuchWindowException e) {
						return false;
					} catch (Exception e){
						try {
							Thread.sleep(200);
						} catch (Exception ignore) {}
					}
					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					return (Boolean) js2.executeScript("return !!window.jQuery && window.jQuery.active == 0");
				}
			});
		} catch (NoSuchWindowException e) {
			try {
				Thread.sleep(6000);
			} catch (Exception ignore) {}

		} catch (UnhandledAlertException alertE) {
			System.out.println("unhandled alert window found.");
			TestActions.getActiveWindowTitle();
			AssertJUnit.assertTrue("Unhandled Alert window found - quite testing",false);
			if(wait.until(ExpectedConditions.alertIsPresent())!=null){
				AssertJUnit.assertTrue("Unhandled Alert window found - quite testing",false);
			}
		} catch (WebDriverException ignore) {}
	}


	protected static boolean isElementPresent(By selector,WebDriver dr) {
		dr.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		//	    logger.debug("Is element present '" + selector + "'");
		boolean returnVal = true;
		try{
			dr.findElement(selector);
		} catch (NoSuchElementException e){
			returnVal = false;
		} finally {
			//	        dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		return returnVal;
	}

	protected static void GenericLongWaitLoad(){
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		try {
			if(driver.getTitle().equals("Plugin Installation")){
				TestActions.waitUntilTitleChanges(driver, "Plugin Installation");	
			}
			(new WebDriverWait(driver, 120000)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver drive) {
//					boolean sWait=false;
					boolean havePopUp=false;
//					boolean bJQueryReady;
					JavascriptExecutor js;// = (JavascriptExecutor) driver;			    	
					try {
						driver.getTitle();
						havePopUp=false;
					} catch (UnhandledAlertException e) {
						havePopUp=true;
					} catch (UnreachableBrowserException ignore2){}
					if (havePopUp){
						return true;
					}
					try {
						js = (JavascriptExecutor) driver;
						if (js.executeScript("return document.readyState")==null){
							Thread.sleep(1000);
							System.out.println("readyState==null, sleep 1 second.");
						} else if (js.executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete")){
							if(isElementPresent(By.id("clientButton"),driver)){
								Thread.sleep(100);	
							} else {
								if (isElementPresent((By.xpath("//div[contains(@id,'blockUI blockOverlay') or contains(@id,'applicationUnLoadingSplashScreen') or contains(@id,'restartNotify') or contains(text(),'Reconnecting...')]")),driver)){
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@id,'blockUI blockOverlay') or contains(@id,'applicationUnLoadingSplashScreen') or contains(@id,'restartNotify') or contains(text(),'Reconnecting...')]")));
								}
								if (isElementPresent(By.xpath("//*[contains(text(),'lease wait') or contains(text(),'Loading.') or contains(text(),'loading...') or contains(text(),'Loading map')]"), driver)) {
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'lease wait') or contains(text(),'Loading.') or contains(text(),'loading...') or contains(text(),'Loading map')]")));
								} else{
									return false;
								}
							}
						} else {
							return false;
						}
					} catch (Exception e){
						try {
//							boolean sWait = true;//// need to handle while is not enough
							Thread.sleep(10000);
						} catch (Exception ignore) {}
					}
					JavascriptExecutor js2 = (JavascriptExecutor) driver;
					return (Boolean) js2.executeScript("return !!window.jQuery && window.jQuery.active == 0");
				}
			});
		} catch (NoSuchWindowException e) {
			try {
				Thread.sleep(6000);
			} catch (Exception ignore) {}
		} catch (UnhandledAlertException alertE) {
			System.out.println("unhandled alert window found.");
			TestActions.getActiveWindowTitle();
			if(wait.until(ExpectedConditions.alertIsPresent())!=null){
				AssertJUnit.assertTrue("Unhandled Alert window found - quite testing",false);
			}
		}
	}
}
