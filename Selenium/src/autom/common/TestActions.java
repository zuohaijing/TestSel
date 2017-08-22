package autom.common;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import autom.common.MasterTest;
import autom.common.MyPrint;
import autom.common.PageLoadWait;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;


public class TestActions<T> extends MasterTest {
	protected Map<String,Integer> webElementPropertyMap;
	public static WebElement workingElement;
	protected static HashMap<String, HashMap<String, HashMap<String, String>>> DataMap;
	public static String sMainwindowHandler="";
	private T value;
	public static WebElement getElement(String webElementPropertyName,String webElementPropertyValue){
		WebElement workingElement=null;
		try{
			switch (webElementPropertyName)	{
		     case "id":
		        workingElement=driver.findElement(By.id(webElementPropertyValue));
		        break;
		     case "name":
			    workingElement=driver.findElement(By.name(webElementPropertyValue));
			    break;
		     case "xpath":
		    	workingElement=driver.findElement(By.xpath(webElementPropertyValue));
		        break;
		     case "link":
		    	workingElement=driver.findElement(By.linkText(webElementPropertyValue));
		        break;
		     case "css":
		    	workingElement=driver.findElement(By.cssSelector(webElementPropertyValue));
		        break;
		     case "tagname":
		    	workingElement=driver.findElement(By.tagName(webElementPropertyValue));
		        break;
		     case "class":
		    	 workingElement=driver.findElement(By.className(webElementPropertyValue));
		    	 break;
		     case "partiallinktext":
		    	 workingElement=driver.findElement(By.partialLinkText(webElementPropertyValue));
		    	 break;
		     case "javascript":
		    	 workingElement=((WebElement) ((JavascriptExecutor)driver).executeScript(webElementPropertyValue));
		    	 break;
		     case "jsid":
		    	 workingElement=(WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+ webElementPropertyValue + "');");
		    	 break;
		     case "jsdom":
		    	 workingElement=driver.findElement(byDom(webElementPropertyValue));
		    	 break;
		     default: System.out.println("unknown method");
			}
		} catch (NoSuchElementException|NullPointerException e){
			System.out.println("Unable to find the element with propert name '" + webElementPropertyName +"' and value '" + webElementPropertyValue + "'");
		}
		return workingElement;
	}
    protected static String Parent() {
    	String sParant;
        StackTraceElement[] trace = new Throwable().getStackTrace();
        sParant = trace[2].getClassName();
        String[] parts = sParant.split("\\.");
        int iPart=parts.length;
        String stemp;
        stemp=parts[iPart-1];
        return stemp;
   }

	public static By getElementLocator (HashMap<String, HashMap<String, HashMap<String, String>>>DictLocators,String webElementLocatorID){
		By ElementLocator=null;		
		if (DictLocators.get("Locators").containsKey(webElementLocatorID)) {
			switch (DictLocators.get("Locators").get(webElementLocatorID).get("locator").toLowerCase()){
				case "id":
					ElementLocator=By.id(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;
				case "name":
					ElementLocator=By.name(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;					
				case "xpath":
					ElementLocator=By.xpath(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;
				case "link":
					ElementLocator=By.linkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;
				case "css":
					ElementLocator=By.cssSelector(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;
				case "tagname":
					ElementLocator=By.tagName(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;
				case "class":
					ElementLocator=By.className(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;
				case "partiallinktext":
					ElementLocator=By.partialLinkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression"));
					break;
//				case "javascript":
//					ElementLocator=((WebElement) ((JavascriptExecutor)driver).executeScript(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
//					break;
//				case "jsid":
//					ElementLocator=(WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+ DictLocators.get("Locators").get(webElementLocatorID).get("expression") + "');");
//	    	 		break;
//				case "jsdom":
//					ElementLocator=driver.findElement(byDom(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
//					break;	    	 
				default: System.out.println("unknown method");
			}
		} else {
			System.out.println("Element with id '" + webElementLocatorID + "' not found in the spreadsheet");
		}
		return ElementLocator;
	}

	public static By getElementLocator (String webElementLocatorID){
		By ElementLocator=null;		
		ElementLocator=getElementLocator(DataMap,webElementLocatorID);
		return ElementLocator;
	}

	
	
	public static boolean waitForElementToBePresent(By by, int waitInMilliSeconds) throws Exception {
	    int wait = waitInMilliSeconds;
	    int iterations  = (wait/200);
	    long startmilliSec = System.currentTimeMillis();
	    for (int i = 0; i < iterations; i++) {
	        if((System.currentTimeMillis()-startmilliSec)>wait)
	            return false;
	        List<WebElement> elements = driver.findElements(by);
	        if (elements != null && elements.size() > 0)
	            return true;
	        Thread.sleep(200);
	    }
	    return false;
	}
	
	
	public static boolean waitForElementToBePresent(WebElement oTarget, int waitInMilliSeconds) throws Exception {
	    int wait = waitInMilliSeconds;
	    int iterations  = (wait/200);
	    long startmilliSec = System.currentTimeMillis();
	    for (int i = 0; i < iterations; i++) {
	        if((System.currentTimeMillis()-startmilliSec)>wait)
	            return false;
	        if (ElementExists(oTarget))
	            return true;
	        Thread.sleep(200);
	    }
	    return false;
	}
	
	
	public static WebElement getElement (HashMap<String, HashMap<String, HashMap<String, String>>>DictLocators,String webElementLocatorID){
		WebElement objWebElement=null;
		try{
			if (DictLocators.get("Locators").containsKey(webElementLocatorID)) {
				try{
					waitElementpresence(webElementLocatorID);
				} catch(TimeoutException ignore){
					System.out.println("Time out for waiting for webelement with ID: '" + webElementLocatorID + "'. Ignore and continue.");
				}
				switch (DictLocators.get("Locators").get(webElementLocatorID).get("locator").toLowerCase()){
					case "id":
						objWebElement=driver.findElement(By.id(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "xpath":
						objWebElement=driver.findElement(By.xpath(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "link":
						objWebElement=driver.findElement(By.linkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "css":
						objWebElement=driver.findElement(By.cssSelector(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "tagname":
						objWebElement=driver.findElement(By.tagName(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "name":
						objWebElement=driver.findElement(By.name(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;					
					case "class":
						objWebElement=driver.findElement(By.className(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "partiallinktext":
						objWebElement=driver.findElement(By.partialLinkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "javascript":
						objWebElement=((WebElement) ((JavascriptExecutor)driver).executeScript(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "jsid":
						objWebElement=(WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+ DictLocators.get("Locators").get(webElementLocatorID).get("expression") + "');");
		    	 		break;
					case "jsdom":
						objWebElement=driver.findElement(byDom(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;	    	 
					default:
						System.out.println("unknown method");
				}
			} else {
				System.out.println("Datasheet has no entry for id '" + webElementLocatorID + "'");
				return null;
			}
		} catch (UnhandledAlertException e) {
			System.out.println("UnhandledAlertException occured.");
			if (DictLocators.get("Locators").containsKey(webElementLocatorID)) {
				waitElementpresence(webElementLocatorID);
				switch (DictLocators.get("Locators").get(webElementLocatorID).get("locator").toLowerCase()){
					case "id":
						objWebElement=driver.findElement(By.id(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "xpath":
						objWebElement=driver.findElement(By.xpath(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "link":
						objWebElement=driver.findElement(By.linkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "css":
						objWebElement=driver.findElement(By.cssSelector(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "tagname":
						objWebElement=driver.findElement(By.tagName(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "name":
						objWebElement=driver.findElement(By.name(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;					
					case "class":
						objWebElement=driver.findElement(By.className(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "partiallinktext":
						objWebElement=driver.findElement(By.partialLinkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "javascript":
						objWebElement=((WebElement) ((JavascriptExecutor)driver).executeScript(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;
					case "jsid":
						objWebElement=(WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+ DictLocators.get("Locators").get(webElementLocatorID).get("expression") + "');");
		    	 		break;
					case "jsdom":
						objWebElement=driver.findElement(byDom(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
						break;	    	 
					default:
						System.out.println("unknown method");
				}
			} else {
				System.out.println("Datasheet has no entry for id '" + webElementLocatorID + "'");
				return null;
			}			
			
		}
		return objWebElement;
	}
	
	public static WebElement getElement(String ElementDataMapID){
		WebElement objTestElement=getElement(DataMap,ElementDataMapID);
		return objTestElement;
	}
	
	public static String getElementCssAttribute(By oLocator,String sAttributeName){
		String sValue="";
		if (ElementExists(oLocator)){
			WebElement oEle=getElement(oLocator);
			switch (sAttributeName.toLowerCase()){
			case "src":
				sValue = ((JavascriptExecutor)driver).executeScript("return arguments[0].attributes['src'].value;", oEle).toString();
				break;
			case "dddd":
				
//				case "jsdom":
//				objWebElement=driver.findElement(byDom(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
				break;	    	 
			default: Assert.fail("Unknown property name.");
			}
		} else {
			Assert.fail("Element not found.");
		}
		return sValue;
	}
	
	
	public static List<WebElement> getElements (HashMap<String, HashMap<String, HashMap<String, String>>>DictLocators,String webElementLocatorID){
		List<WebElement> objWebElementList=null;
		if (DictLocators.get("Locators").containsKey(webElementLocatorID)) {
			switch (DictLocators.get("Locators").get(webElementLocatorID).get("locator").toLowerCase()){
				case "id":
					objWebElementList=driver.findElements(By.id(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
				case "xpath":
					objWebElementList=driver.findElements(By.xpath(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
				case "link":
					objWebElementList=driver.findElements(By.linkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
				case "css":
					objWebElementList=driver.findElements(By.cssSelector(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
				case "tagname":
					objWebElementList=driver.findElements(By.tagName(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
				case "name":
					objWebElementList=driver.findElements(By.name(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
				case "class":
					objWebElementList=driver.findElements(By.className(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
				case "partiallinktext":
					objWebElementList=driver.findElements(By.partialLinkText(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
					break;
//				case "javascript":
//					objWebElement=((WebElement) ((JavascriptExecutor)driver).executeScript(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
//					break;
//				case "jsid":
//					objWebElement=(WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+ DictLocators.get("Locators").get(webElementLocatorID).get("expression") + "');");
//	    	 		break;
//				case "jsdom":
//					objWebElement=driver.findElement(byDom(DictLocators.get("Locators").get(webElementLocatorID).get("expression")));
//					break;	    	 
				default: System.out.println("unknown method");
			}
		} else {
			System.out.println("Datasheet has no entry for id '" + webElementLocatorID + "'");
			return null;
		}
		return objWebElementList;
	}	
	public static List<WebElement> getElements (String webElementLocatorID){
		List<WebElement> objWebElementList=null;		
		objWebElementList=getElements(DataMap,webElementLocatorID);
		return objWebElementList;
	}
	public static List<WebElement> getElements (By webElementLocator){
		List<WebElement> objWebElementList=null;		
		objWebElementList=getElements(webElementLocator);
		return objWebElementList;
	}	
	public static WebElement getElement(WebElement objWebElement1,By oLocator){
		WebElement objTestElement=objWebElement1.findElement(oLocator);
		return objTestElement;
	}
		
	public static WebElement getElement(By oLocator){
		workingElement=driver.findElement(oLocator);
		return workingElement;
	}
	
	public static WebElement getElement(By oLocator1,By oLocator2){
		WebElement workingElement=null;
		if(ElementExists(oLocator1,oLocator2)){
			workingElement=driver.findElement(oLocator1).findElement(oLocator2);
		}
		return workingElement;
	}
	
	public static By byDom(String domExpression) { 
		final Object obj = ((JavascriptExecutor) driver).executeScript("return " + domExpression + ";");    
	    if (obj instanceof WebElement) { 
	    	return new By() {
	    		@SuppressWarnings("serial")
				@Override
	    		public List<WebElement> findElements(SearchContext searchContext) {
	    			return new ArrayList<WebElement>() {{add((WebElement) obj);}};
	             }
	         };
	     }
	     return null;
	 }
	
	@SuppressWarnings("unchecked")
	public static void waitElementClickableReady(String webElementPropertyName,String webElementPropertyValue){
		@SuppressWarnings("rawtypes")
		Wait fwait = new FluentWait(driver)
				    .withTimeout(120, TimeUnit.SECONDS)
				    .pollingEvery(1, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);
		switch (webElementPropertyName.toLowerCase())	{
	     case "id":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.id(webElementPropertyValue)));
	        break;
	     case "xpath":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.xpath(webElementPropertyValue)));
	        break;
	     case "link":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.linkText(webElementPropertyValue)));
	        break;
	     case "css":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(webElementPropertyValue)));
	        break;
	     case "tagname":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.tagName(webElementPropertyValue)));
	        break;
	     case "name":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.name(webElementPropertyValue)));
	        break;
	     case "class":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.className(webElementPropertyValue)));
	    	 break;
	     case "partiallinktext":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(webElementPropertyValue)));
	    	 break;
	     case "javascript":
//	    	 workingElement=(WebElement) ((JavascriptExecutor)driver).executeScript(webElementPropertyValue);
	    	 System.out.println("not defined yet method");
	    	 break;	    	 
	     default: System.out.println("unknown method");
		}
	}

	@SuppressWarnings("unchecked")
	public static void waitElementClickableReady(String webElementPropertyNameID){		
		@SuppressWarnings("rawtypes")
		Wait fwait = new FluentWait(driver)
				    .withTimeout(30, TimeUnit.SECONDS)
				    .pollingEvery(1, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class,NullPointerException.class);
		fwait.until(ExpectedConditions.elementToBeClickable(getElementLocator(webElementPropertyNameID)));
	}
	
	

	@SuppressWarnings("unchecked")
	public static void waitElementClickableReady(By oLocator){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
				    .withTimeout(120, TimeUnit.SECONDS)
				    .pollingEvery(1, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.elementToBeClickable(oLocator));
	}	
	
	
	public static void clickElementAndWaitReady(String webElementPropertyName,String webElementPropertyValue){
		waitElementClickableReady(webElementPropertyName,webElementPropertyValue);
		WebElement workElement=getElement(webElementPropertyName,webElementPropertyValue);
		workElement.click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		PageLoadWait.GenericWaitLoad();
	}
	
	public static void clickElementAndWaitReady(By oLocator){
		waitElementClickableReady(oLocator);
		driver.findElement(oLocator).click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		PageLoadWait.GenericWaitLoad();
	}	
		
	public static void clickElementAndWaitReady(String sLocatorID){
		waitElementClickableReady(sLocatorID);
		driver.findElement(getElementLocator(sLocatorID)).click();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}			
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}
	
	public static void ElementClick(String sLocatorID){
		driver.findElement(getElementLocator(sLocatorID)).click();
	}
	
	public static void ElementJSClick(String sLocatorID){
		WebElement InputElement=getElement(sLocatorID);
	    JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", InputElement);
	}
	public static void ElementJSClick(WebElement InputElement){
	    JavascriptExecutor js = (JavascriptExecutor)driver;		
		js.executeScript("arguments[0].click()", InputElement);
	}
	
	public static void ElementJSClick(By oLocator){
		WebElement InputElement=driver.findElement(oLocator);
	    JavascriptExecutor js = (JavascriptExecutor)driver;		
		js.executeScript("arguments[0].click()", InputElement);
	}

	public static <T> void SetElementValue(By oLocator,T sValue){
		WebElement InputElement=getElement(oLocator);
		SetElementValue(InputElement,sValue);
	}
	public static <T> void SetElementValue(WebElement oElement,T sValue){
	    JavascriptExecutor js = (JavascriptExecutor)driver;
        try {
        	if (oElement.getAttribute("tagName") !=null){
            	if (oElement.getAttribute("tagName").equalsIgnoreCase("input")){
            		js.executeScript("arguments[0].value='" + sValue +"';", oElement);
            	}
        	} 
        }catch (Exception ignore){}		
	}
	
	public static void mouseOverElement(String webElementPropertyName,String webElementPropertyValue){
		waitElementVisibility(webElementPropertyName,webElementPropertyValue);
		Actions DGAction = new Actions(driver);
		WebElement workElement=getElement(webElementPropertyName,webElementPropertyValue);
		DGAction.moveToElement(workElement).build().perform();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public static void mouseOverElement(WebElement workElement ){
		waitElementVisibility(workElement);
		Actions DGAction = new Actions(driver);
		DGAction.moveToElement(workElement).build().perform();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	public static void mouseOverElement(By Locator){
		waitElementVisibility(Locator);
		Actions DGAction = new Actions(driver);
		WebElement workElement=getElement(Locator);
		DGAction.moveToElement(workElement).build().perform();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public static void mouseOverElement(String oLocatorID){
		mouseOverElement(getElementLocator(oLocatorID));
	}

	public static void mouseOverElementClick(String oLocatorID){
		WebElement workElement=getElement(oLocatorID);
//		Actions DGAction = new Actions(driver);
		builder.moveToElement(workElement).click().build().perform();
	}	

	public static void mouseOverElementClick(WebElement oEle){
//		Actions DGAction = new Actions(driver);
		builder.moveToElement(oEle).click().build().perform();
	}
	public static void mouseOverElementRobotClick(WebElement oEle){
//		Actions DGAction = new Actions(driver);
		builder.moveToElement(oEle).build().perform();
		Robot robot2;
		try {
			robot2 = new Robot();
			robot2.mousePress(InputEvent.BUTTON1_MASK);
			robot2.delay(50);
			robot2.mouseRelease(InputEvent.BUTTON1_MASK);
			robot2.delay(50);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void mouseOverElementRobotClick(By oLocator){
		WebElement oEle=getElement(oLocator);
//		Actions DGAction = new Actions(driver);
		builder.moveToElement(oEle).build().perform();
		Robot robot2;
		try {
			robot2 = new Robot();
			robot2.mousePress(InputEvent.BUTTON1_MASK);
			robot2.delay(50);
			robot2.mouseRelease(InputEvent.BUTTON1_MASK);
			robot2.delay(50);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mouseOverElementRobotClick(String sID){
		WebElement oEle=getElement(sID);
		mouseOverElementRobotClick(oEle);
	}
	
	public static void mouseOverElementClick(By oLocator){
		WebElement workElement=getElement(oLocator);
		waitElementVisibility(workElement);
//		Actions DGAction = new Actions(driver);
		builder.moveToElement(workElement).click().build().perform();	
		
	}	
	
	@SuppressWarnings("unchecked")
	public static void waitElementVisibility(String webElementPropertyName,String webElementPropertyValue){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
				    .withTimeout(30, TimeUnit.SECONDS)
				    .pollingEvery(1, TimeUnit.SECONDS)
				    .ignoring(NoSuchElementException.class);
		String webElementPropertyNameLCase=webElementPropertyName.toLowerCase();
		switch (webElementPropertyNameLCase)	{
	     case "id":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(webElementPropertyValue)));
	        break;
	     case "xpath":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElementPropertyValue)));
	        break;
	     case "link":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(webElementPropertyValue)));
	        break;
	     case "css":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(webElementPropertyValue)));
	        break;
	     case "name":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.name(webElementPropertyValue)));
	        break;
	     case "tagname":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(webElementPropertyValue)));
	        break;
	     case "class":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.className(webElementPropertyValue)));
	    	 break;
	     case "partiallinktext":
	    	 fwait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(webElementPropertyValue)));
	    	 break;
	     case "javascript":
//	    	 workingElement=(WebElement) ((JavascriptExecutor)driver).executeScript(webElementPropertyValue);
	    	 System.out.println("not defined yet method");
	    	 break;

//	     case "jsid":
//	    	 ((WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+ webElementPropertyValue + "');")).click();
//	    	 break;
//	     case "jsdom":
////	    	 driver.findElement(byDom("document.forms[0].elements[0]"));
//	    	 ((WebElement) ((JavascriptExecutor)driver).executeScript("return "+ webElementPropertyValue + ";")).click();
//	    	 break;
	    	 
	    	 
	     default: System.out.println("unknown method");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void waitElementVisibility(By oLocator){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
			    .withTimeout(30, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);
	
		fwait.until(ExpectedConditions.visibilityOfElementLocated(oLocator));
	}
	@SuppressWarnings("unchecked")
	public static boolean waitElementVisibility(By oLocator,int iTO){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
			    .withTimeout(iTO, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);
		try{
			fwait.until(ExpectedConditions.visibilityOfElementLocated(oLocator));
			return true;
		} catch (TimeoutException e2){
		} catch (NoSuchElementException e3){
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public static void waitElementVisibility(WebElement oEle){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
			    .withTimeout(30, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.visibilityOf(oEle));
	}
	
	@SuppressWarnings("unchecked")
	public static void waitElementVisibility(String ElementID){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
			    .withTimeout(30, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.visibilityOfElementLocated(getElementLocator(ElementID)));
	}
	
	public static void waitElementVisibility(String webElementPropertyName,String webElementPropertyValue1,String webElementPropertyValue2,String webElementPropertyValue3){
		String webElementPropertyValue=webElementPropertyValue1+webElementPropertyValue2+webElementPropertyValue3;
		waitElementVisibility(webElementPropertyName,webElementPropertyValue);
	}
	public static void waitElementVisibility(String webElementPropertyName,String webElementPropertyValue1,String webElementPropertyValue2){
		String webElementPropertyValue=webElementPropertyValue1+webElementPropertyValue2;
		waitElementVisibility(webElementPropertyName,webElementPropertyValue);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void waitElementpresence(By oLocator){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
			    .withTimeout(120, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);
	
		fwait.until(ExpectedConditions.presenceOfElementLocated(oLocator));
	}
	
	@SuppressWarnings("unchecked")
	public static void waitElementpresence(String ElementID){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
			    .withTimeout(120, TimeUnit.SECONDS)
			    .pollingEvery(1, TimeUnit.SECONDS)
			    .ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.presenceOfElementLocated(getElementLocator(ElementID)));
	}
	
	
	public static void ElementSendKeys(WebElement objWebElement,String sInputWebElementString){
		if(objWebElement!=null){
			objWebElement.click();
			objWebElement.sendKeys(sInputWebElementString);			
		}
	}
	public static void ElementSendKeys(By locatorID,String sInputWebElementString){
		waitElementClickableReady(locatorID);
		getElement(locatorID).click();
		waitElementClickableReady(locatorID);
		getElement(locatorID).sendKeys(sInputWebElementString);
	}
	public static void ElementSendKeys(String webElementPropertyName,String webElementPropertyValue,String sInputWebElementString){
		WebElement objWebElement=getElement(webElementPropertyName, webElementPropertyValue);
		if(objWebElement!=null){
			objWebElement.click();
			objWebElement.sendKeys(sInputWebElementString);
		}
	}
	public static void ElementSelect(String webElementPropertyName,String webElementPropertyValue){
		WebElement objWebElement=getElement(webElementPropertyName, webElementPropertyValue);
		if(objWebElement!=null){
			ElementSelect(objWebElement);	
		}
	}
	public static void ElementSelect(WebElement objWebElement){
		if(objWebElement!=null){
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", objWebElement);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			objWebElement.click();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	public static void ElementScrollIntoView(WebElement objWebElement){
		if(objWebElement!=null){
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", objWebElement);
		}
	}
	
	public static WebElement ElementButton(String webElementPropertyName,String webElementPropertyValue,By locator){
		List <WebElement> elements = driver.findElements(locator);
		if (elements.size()>0){
			for (WebElement elementOption:elements){
				if (elementOption.isDisplayed()) {
					if (elementOption.getAttribute(webElementPropertyName).contains(webElementPropertyValue)){
						return elementOption;
					}
				}
			}
		}
		return null;
	}

	public static void ElementButtonTxtClick(String webElementDisplyText,int iNumberTxt,int iwait){
		if (iwait>0){
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[starts-with(@class,'buttonFactory')][contains(.,'" + webElementDisplyText + "')])[" + iNumberTxt + "]")));
		}
		if (driver.findElements(By.xpath("(//div[starts-with(@class,'buttonFactory')][contains(.,'" + webElementDisplyText + "')])[" + iNumberTxt + "]")).size()!=0){
			WebElement myButton=driver.findElement(By.xpath("(//div[starts-with(@class,'buttonFactory')][contains(.,'" + webElementDisplyText + "')])[" + iNumberTxt + "]"));
			myButton.click();
		}
	}

	public static void ElementButtonTxtClick(String webElementDisplyText,int iNumberTxt){
		WebElement myButton=driver.findElement(By.xpath("(//div[starts-with(@class,'buttonFactory')][contains(.,'" + webElementDisplyText + "')])[" + iNumberTxt + "]"));
		if (driver.findElements(By.xpath("(//div[starts-with(@class,'buttonFactory')][contains(.,'" + webElementDisplyText + "')])[" + iNumberTxt + "]")).size()!=0){
			myButton.click();
		}
	}
	
	public static void ElementButtonClick(String webElementPropertyName,String webElementPropertyValue,int iwait){
		WebElement myButton=ElementButton(webElementPropertyName,webElementPropertyValue,By.xpath("//div[starts-with(@class,'buttonFactory')]"));
		if (myButton!=null){
			myButton.click();
			if (iwait>0){
				PageLoadWait.GenericWaitLoad();
			}
		}
	}
	
	public static void ElementButtonClick(String webElementPropertyName,String webElementPropertyValue){
		WebElement myButton=ElementButton(webElementPropertyName,webElementPropertyValue,By.xpath("//div[starts-with(@class,'buttonFactory')]"));
		if (myButton!=null){
			myButton.click();
		}
	}

	public static void ElementButtonClick(String LocatorID){
		waitElementClickableReady(LocatorID);
		getElement(LocatorID).click();		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void clickElementWithNoWait(String webElementPropertyName,String webElementPropertyValue){
		Wait fwait = new FluentWait(driver)
	    	.withTimeout(30, TimeUnit.SECONDS)
	    	.pollingEvery(1, TimeUnit.SECONDS)
	    	.ignoring(NoSuchElementException.class);
		String webElementPropertyNameLCace=webElementPropertyName.toLowerCase();
		
		switch (webElementPropertyNameLCace)	{
	     case "id":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.id(webElementPropertyValue)));
	    	driver.findElement(By.id(webElementPropertyValue)).click();
	        break;
	     case "xpath":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.xpath(webElementPropertyValue)));
	    	driver.findElement(By.xpath(webElementPropertyValue)).click();
	        break;
	     case "link":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.linkText(webElementPropertyValue)));
	    	 driver.findElement(By.linkText(webElementPropertyValue)).click();
	        break;
	     case "css":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(webElementPropertyValue)));
	    	 driver.findElement(By.cssSelector(webElementPropertyValue)).click();
	    	 break;
	     case "tagname":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.tagName(webElementPropertyValue)));
	    	 driver.findElement(By.tagName(webElementPropertyValue)).click();
	        break;
	     case "name":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.name(webElementPropertyValue)));
	    	 driver.findElement(By.tagName(webElementPropertyValue)).click();
	        break;
	     case "class":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.className(webElementPropertyValue)));
	    	 driver.findElement(By.className(webElementPropertyValue)).click();
	    	 break;
	     case "partiallinktext":
	    	 fwait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(webElementPropertyValue)));
	    	 driver.findElement(By.partialLinkText(webElementPropertyValue)).click();
	    	 break;
	     case "javascript":
	    	 ((WebElement) ((JavascriptExecutor)driver).executeScript(webElementPropertyValue)).click();
	    	 break;
	     case "jsid":
	    	 ((WebElement) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+ webElementPropertyValue + "');")).click();
	    	 break;
	     case "jsdom":
//	    	 driver.findElement(byDom("document.forms[0].elements[0]"));
	    	 ((WebElement) ((JavascriptExecutor)driver).executeScript("return "+ webElementPropertyValue + ";")).click();
	    	 break;
	     default: System.out.println("unknown method");
		}
	}

	public static void SwitchToFrame(By oLocator){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(driver.findElements(oLocator).size()>0){
			WebElement iFrame=driver.findElement(oLocator);
			System.out.println("Found iframe " + oLocator.toString());	
			waitUntilBooleanCondition(iFrame,"visible", true);
			driver.switchTo().frame(iFrame);
			System.out.println("Switched to iframe " + oLocator.toString());			
		} else{
			System.out.println("Switched to iframe failed. iframe " + oLocator.toString() + " not found");
		}
	}
	
	public static void SwitchToFrame(String sLocatorString){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		By olocator=getElementLocator(sLocatorString);
		if(driver.findElements(olocator).size()>0){
			WebElement iFrame=driver.findElement(olocator);
			System.out.println("Found iframe " + olocator.toString());	
			waitUntilBooleanCondition(iFrame,"visible", true);
			driver.switchTo().frame(iFrame);
			System.out.println("Switched to iframe " + olocator.toString());			
		} else{
			System.out.println("Switched to iframe failed. iframe " + olocator.toString() + " not found");
		}
	}
	
	public static void clickElementWithNoWait(By oLocator){
		waitElementClickableReady(oLocator);
		WebElement element=driver.findElement(oLocator);
		clickElementWithNoWait(element);
	}
	public static void dblclickElementWithNoWait(By oLocator){
		waitElementClickableReady(oLocator);
        Actions action = new Actions(driver);
        WebElement element=driver.findElement(oLocator);
        action.doubleClick(element).perform();
	}
	
	public static void dblclickElementWithNoWait(WebElement oEle){
		robotWait(1);
        Actions action = new Actions(driver);
        action.doubleClick(oEle).perform();
        robotWait(1);
	}
	
	public static void robotWait(Integer iSeconds){
		Robot testrobot;
		try {
			testrobot = new Robot();			
			testrobot.delay(iSeconds*1000);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void robotWaitMS(Integer iMSSeconds){
		Robot testrobot;
		try {
			testrobot = new Robot();			
			testrobot.delay(iMSSeconds);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void clickElementWithNoWait(String elementID){
		waitElementClickableReady(getElementLocator(elementID));
		WebElement element=driver.findElement(getElementLocator(elementID));
		clickElementWithNoWait(element);
	}
	public static void clickElementIfExists(By oLocator){
		if (driver.findElements(oLocator).size()!=0){
			WebElement element=driver.findElement(oLocator);
			clickElementWithNoWait(element);			
		}
	}
	public static void clickElementIfExists(String elementID){
		if (driver.findElements(getElementLocator(elementID)).size()!=0){
			WebElement element=driver.findElement(getElementLocator(elementID));
			clickElementWithNoWait(element);			
		}		
	}
	public static void clickElementIfExists(WebElement oRootElemnt,By oLocator){
		if (oRootElemnt.findElements(oLocator).size()!=0){
			WebElement element=oRootElemnt.findElement(oLocator);
			clickElementWithNoWait(element);			
		}
	}
	
	public static boolean ElementVisible(By oLocator){
		boolean bVisible=false;
		if (ElementExists(oLocator)){
			WebElement oEle=getElement(oLocator);
			bVisible=oEle.getCssValue("visibility").equalsIgnoreCase("visible");
		}
		return bVisible;
	}
	public static boolean ElementVisible(WebElement oEle){
		boolean bVisible=false;
		bVisible=oEle.getCssValue("visibility").equalsIgnoreCase("visible");
		return bVisible;
	}
	public static boolean ElementExists(WebElement objWebElement){
	    try {
	    	objWebElement.getTagName();
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    } catch (NullPointerException e) {
	        return false;
	    }
	}
	public static boolean ElementExists(By oLocator){
		boolean bFound=false;
		if (driver.findElements(oLocator).size()!=0){
			bFound=true;
		} else {
			System.out.println("WebElement with description '" + oLocator.toString() + "' is not found.");
		}
		return bFound;
	}
	
	public static boolean elementExists(String locatorID) {
		return elementExists(getElementLocator(locatorID));
	}

	public static boolean elementExists(String locatorID,int iTimeOut) {
	    WebElement foo = null;
	    try {
	        foo = getElementByLocator(getElementLocator(locatorID), iTimeOut);
	     } catch ( TimeoutException te) {
	        System.out.println("There was a timeout looking for element: " + 
	        		locatorID.toString() );
	        //Swallow exception: ExceptionUtils.getMessage(te);
	        return false;
	    } catch ( ElementNotVisibleException env) {
	        System.out.println("The element was found but is invisible: " +
	        		locatorID.toString() );
	        //Swallow exception: ExceptionUtils.getMessage(env);
	        return false;
	    }
	    if ( foo == null ) {
	        return false;
	    } else {
	        return true;
	    }
	}
	
	public static boolean elementExists(By locator) {   
	    WebElement foo = null;
	    try {
	        foo = getElementByLocator(locator, 10);
	     } catch ( TimeoutException te) {
	        System.out.println("There was a timeout looking for element: " + 
	            locator.toString() );
	        //Swallow exception: ExceptionUtils.getMessage(te);
	        return false;
	    } catch ( ElementNotVisibleException env) {
	        System.out.println("The element was found but is invisible: " +
	            locator.toString() );
	        //Swallow exception: ExceptionUtils.getMessage(env);
	        return false;
	    }
	    if ( foo == null ) {
	        return false;
	    } else {
	        return true;
	    }
	}
	
	public static WebElement getElementByLocator(By locator, int timeout) {
	    System.out.println("Calling method getElementByLocator: " + locator.toString());
	    int interval = 5;
	    if (timeout <= 20)
	    	interval = 3;
	    if (timeout <= 10)
	    	interval = 2;
	    if (timeout <= 4)
	    	interval = 1;
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	        .withTimeout(timeout, TimeUnit.SECONDS)
	        .pollingEvery(interval, TimeUnit.SECONDS)
	        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class );
	    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    WebElement we = driver.findElement(locator);
	    return we;
	}
	
	
	public static boolean ElementExists(By oLocator,boolean bExpected){
		boolean bFound=false;
		if (driver.findElements(oLocator).size()!=0){
			bFound=true;
		} else {
			if(bExpected){
				System.out.println("WebElement with description '" + oLocator.toString() + "' is not found.");				
			}
		}
		return bFound;
	}
	
	public static boolean ElementExists(String sLocatorID){
		return ElementExists(getElementLocator(sLocatorID));		
	}
	
	public static boolean ElementExists(WebElement objWebElement1,By oLocator){
		boolean bFound=false;
		if (objWebElement1.findElements(oLocator).size()!=0){
			bFound=true;
		}
		return bFound;
	}
	
	public static boolean ElementExists(By oLocator1,By oLocator2){
		boolean bFound=false;
		if(ElementExists(oLocator1)){
			WebElement oFirst=getElement(oLocator1);
			bFound=ElementExists(oFirst,oLocator2);
		}
		return bFound;
	}
	
	////still need more work on the following... not working
	public static boolean ElementExists(Select objWebElement1,By oLocator){
		boolean bFound=false;
		if (((WebElement) objWebElement1).findElements(oLocator).size()!=0){
			bFound=true;
		}
		return bFound;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void waitElementVisible(WebElement oWebElement){
		@SuppressWarnings("rawtypes")
		Wait fwait = new FluentWait(driver)
		.withTimeout(20, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS);
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boolean bContainsString;				
				bContainsString=ElementVisible(oWebElement);
				return bContainsString;
	           }
	       });
	}
	
	@SuppressWarnings("unchecked")
	public static void waitElementEnabled(WebElement oWebElement){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//		System.out.println("Start waiting for the element to be enabled at time: " + timeStamp);
		try{
		@SuppressWarnings("rawtypes")
		Wait fwait = new FluentWait(driver)
		.withTimeout(150, TimeUnit.SECONDS)
		.pollingEvery(5, TimeUnit.SECONDS);
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boolean bContainsString;				
				bContainsString=!(DisabledButton(oWebElement));
				if(bContainsString){
					@SuppressWarnings("unused")
					String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//					System.out.println("WebElement turns to be enabled - end waiting at: " + timeStamp + " --> Go on testing.");
				}
				return bContainsString;
	           }
	       });} catch (TimeoutException e) {
	    	   System.out.println("WebElement '" + oWebElement.toString() + "' is still disabled. Time out.");
	    	   timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	   System.out.println("End waiting: " + timeStamp);
	    	   throw e;
	       }
		

	}

	public static boolean waitTilElementVisible(WebElement oWebElement){
		waitElementVisible(oWebElement);
		return true;
	}
	
	public static boolean waitTilElementVisible(By oByWebElement){
		waitElementVisible(oByWebElement);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void waitElementVisible(By oWebElement){
		Wait fwait = new FluentWait(driver)
		.withTimeout(20, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS);
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boolean bContainsString;				
				bContainsString=ElementVisible(oWebElement);
				return bContainsString;
	           }
	       });
	}
	
	@SuppressWarnings("unchecked")
	public static void waitElementCssValue(WebElement oWebElement,String sCssAttribute,String ScontainValue){
		@SuppressWarnings("rawtypes")
		Wait fwait = new FluentWait(driver)
		.withTimeout(20, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS);
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boolean bContainsString;				
				bContainsString=oWebElement.getCssValue(sCssAttribute).contains(ScontainValue);
				return bContainsString;
	           }
	       });
	}
	
	public static void waitElementCssValue(WebElement oWebElement, By oLocator, String sCssAttribute,String ScontainValue){
		WebElement oEle=getElement(oWebElement,oLocator);
		waitElementCssValue(oEle,sCssAttribute,ScontainValue);
	}

	public static void waitElementCssValue(By oLocator, String sCssAttribute,String ScontainValue){
		WebElement oEle=getElement(oLocator);
		waitElementCssValue(oEle,sCssAttribute,ScontainValue);
	}
	
	
	public boolean ElementCssValueMatch(WebElement oEle, String sCssName, String sCssValue){
		boolean bReturn=false;
		if(oEle.getCssValue(sCssName).equalsIgnoreCase(sCssValue)){
			bReturn=true;
		}
		return bReturn;
	}

	
	@SuppressWarnings("unchecked")
	public static void waitElementNOTCssValue(WebElement oWebElement,String sCssAttribute,String ScontainValue){
		@SuppressWarnings("rawtypes")
		Wait fwait = new FluentWait(driver)
		.withTimeout(10, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS);
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boolean bContainsString;				
				bContainsString=!(oWebElement.getCssValue(sCssAttribute).contains(ScontainValue));
				if (!bContainsString){
					System.out.println("Expect element css value for attribute '" + sCssAttribute + "'  containing unexpected value '"+ ScontainValue + "'; wait and retry.");
				}
				return bContainsString;
	           }
	       });
	}
	
	
	public static boolean DisabledButton(WebElement oWebElement){
		boolean bDisabled=false;
		String backgroundImage="";
		if (oWebElement.getCssValue("background-image")!=null){
			if(oWebElement.getCssValue("background-image").length()>0){
				backgroundImage=oWebElement.getCssValue("background-image").toLowerCase();
				if(backgroundImage.contains("_disabled")){
					bDisabled=true;
				}
			} else {
				System.out.println("Element css value for attribute 'background-image' not containing value '_disabled'.");
			}
		}
		return bDisabled;
	}

	public static boolean DisabledButton(By oLocator){
		WebElement oWebElement=getElement(oLocator);
		return DisabledButton(oWebElement);
	}
	
	
	public static void clickElementWithNoWait(WebElement oWebElement){
		if (oWebElement.getCssValue("background-image")!=null){
			if(oWebElement.getCssValue("background-image").length()>0){
				waitElementNOTCssValue(oWebElement,"background-image","default_disabled");
			} else {
				System.out.println("True - Element css value for attribute 'background-image' not containing value 'default_disabled'.");
			}
		}
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try {
        	if (oWebElement.getAttribute("tagName") !=null){
            	switch (oWebElement.getAttribute("tagName").toLowerCase()) {
            		case "option":
            			js.executeScript("arguments[0].selected=true;", oWebElement);
            			break;
            		case "input":
            			try{
            				oWebElement.click();
            			} catch (ElementNotVisibleException e3) {
            				System.out.println("Element is not visible. Give up.");
            			} catch (TimeoutException e4){
            				System.out.println("Timed out waiting for page to load.Ignore.");
            			}
            			break;
            		case "img":
            			oWebElement.click();
            			break;
            		default:
//            			System.out.println("tagname = '" + oWebElement.getAttribute("tagName") + "', do js click anyway.");
            			js.executeScript("arguments[0].click()", oWebElement);
            			break;
            	}            	
        	} else {
    			System.out.println("tagname is null, do js click anyway.");
        		js.executeScript("arguments[0].click();", oWebElement);
        	}
        } catch (UnhandledAlertException e) {
        	e.printStackTrace();
        	System.out.println("one more try to get the text from driver= '" + driver.switchTo().alert().getText() + "'.");
        	throw e;
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public static void clickElementWithNoWait(WebElement oWebElement, By oLocator){
		WebElement objTestElement=oWebElement.findElement(oLocator);
		clickElementWithNoWait(objTestElement);
	}
	
    protected static String getActiveWindowTitle() {
        HWND fgWindow = User32.INSTANCE.GetForegroundWindow();
        int titleLength = User32.INSTANCE.GetWindowTextLength(fgWindow) + 1;
        char[] title = new char[titleLength];
        User32.INSTANCE.GetWindowText(fgWindow, title, titleLength);
        String userWinTitle="";
        userWinTitle = Native.toString(title);
        System.out.println("User32 Active window title'" + userWinTitle + "'.");
        
        if(userWinTitle.equalsIgnoreCase("Message from webpage")){
        	System.out.println("Print Popup window message from the switch driver to alert():");
        	try{
        		try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
//					 TODO Auto-generated catch block
					e.printStackTrace();
				}
        		System.out.println("= '" + driver.switchTo().alert().getText() + "'.");       	
        	} catch (NoAlertPresentException ignore){
        		System.out.println("Unabled to get Popup window message from the switch driver to alert....");
        	}
        }        
        return userWinTitle;
    }

    
    private static boolean isDialogPresent() {
        try {
            driver.getTitle();
            System.out.println("no dialog exist.");
            return false;
        } catch (UnhandledAlertException e) {
        	System.out.println("unhandled exception threwed.");
            // Modal dialog showed
            return true;
        }
    }
	
	public static boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert(); 
	        return true; 
	    }
	    catch (NoAlertPresentException Ex){ 
	        return false; 
	    }   
	}

	public static void selectOKAlert(){
		WebDriverWait waitalert = new WebDriverWait(driver, 30);
		WebDriverWait waitalert2 = new WebDriverWait(driver, 2);
		if(waitalert.until(ExpectedConditions.alertIsPresent())==null)
		    System.out.println("alert was not present");
		else
			getActiveWindowTitle();
			(waitalert.until(ExpectedConditions.alertIsPresent())).accept();
			MyPrint.myPrint("OK button clicked","");
			try {
				if(waitalert2.until(ExpectedConditions.alertIsPresent())!=null){
					getActiveWindowTitle();
					(waitalert2.until(ExpectedConditions.alertIsPresent())).accept();
					MyPrint.myPrint("OK button clicked","");
				}
			} catch (TimeoutException ignore1) {}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}

			int iterations=0;
			boolean bCont=true;
	        try {
	        	while (bCont){
	        		if(((JavascriptExecutor)driver).executeScript("return document.readyState")!=null){
	        			if(((JavascriptExecutor)driver).executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete")){
	        				break;
	        			}
	        		}        		
	        		robotWait(1);
	        		iterations++;
	        		if (iterations>40){
						try {
							if(iterations<100){
								robotWait(1);
							} else {
								if(bCont){
									break;
								}
								System.out.println("looped " + iterations);
								throw new LoadingTimeOutException("failed to load window content within the time frame given. time out.");								
							}
						} catch (LoadingTimeOutException e) {
							e.printStackTrace();
						}
	        		}
	        	}
	        } catch (Exception e) {
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
			driver.switchTo().defaultContent();
	}
	
	public static void selectCancelAlert(){
		WebDriverWait waitalert = new WebDriverWait(driver, 30);
		WebDriverWait waitalert2 = new WebDriverWait(driver, 2);
		if(waitalert.until(ExpectedConditions.alertIsPresent())==null)
		    System.out.println("alert was not present");
		else
			getActiveWindowTitle();
			(waitalert.until(ExpectedConditions.alertIsPresent())).dismiss();
			try {
				if(waitalert2.until(ExpectedConditions.alertIsPresent())!=null){
					getActiveWindowTitle();
					(waitalert2.until(ExpectedConditions.alertIsPresent())).dismiss();
				}
			} catch (TimeoutException ignore1) {}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {}

			int iterations=0;
	        try {
	        	while (!((JavascriptExecutor)driver).executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete")){
	        		Thread.sleep(500);
	        		iterations++;
	        		if (iterations>40){
						try {
							throw new LoadingTimeOutException("failed to load window content within the time given frame.time out.");
						} catch (LoadingTimeOutException e) {
							e.printStackTrace();
						}
	        		}
	        	}
	        } catch (Exception e) {
	        	try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
			driver.switchTo().defaultContent();
	}
	
	
	public static void textInput(String sFieldLabel,String sValue){
    	waitElementClickableReady(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]"));
    	driver.findElement(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]")).clear();
    	driver.findElement(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]")).sendKeys(sValue);
	}
	
    public static void setAttribute(WebElement element, String attName, String attValue) {
    	((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", 
                element, attName, attValue);
    }


	public static void labelClick(String sFieldLabel,int iIndexNumber){    	
    	if (driver.findElements(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + sFieldLabel.toLowerCase() +"')]")).size()!=0){
    		if ((driver.findElements(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + sFieldLabel.toLowerCase() +"')]"))).size()>=iIndexNumber){
    			(driver.findElements(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + sFieldLabel.toLowerCase() +"')]"))).get(iIndexNumber).click();
    		}
    	}
	}
	public static void labelClick(String sFieldLabel){
    	if (driver.findElements(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + sFieldLabel.toLowerCase() +"')]")).size()!=0){
   			(driver.findElements(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + sFieldLabel.toLowerCase() +"')]"))).get(0).click();
    	}
	}

	public static void divLabelClick(String sFieldLabel){
    	if (driver.findElements(By.xpath("//div[contains(text(),'" + sFieldLabel +"')]")).size()!=0){
    		driver.findElement(By.xpath("//div[contains(text(),'" + sFieldLabel +"')]")).click();
    	}
	}
	public static void divLabelClick(By oLabelLocator){
    	if (driver.findElements(oLabelLocator).size()!=0){
    		driver.findElement(oLabelLocator).click();
    	}
	}
	
	public static void checkBoxInput(String sFieldLabel,String sValue){
		waitElementClickableReady(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]"));
		if (sValue.contentEquals("true")){
			if (!driver.findElement(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]")).isSelected()){
				driver.findElement(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]")).click();
			}
		} else {
			if (driver.findElement(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]")).isSelected()){
				driver.findElement(By.xpath("//label[contains(.,'" + sFieldLabel + "')]//following::input[1]")).click();
			}
		}
	}
	public static void checkBoxInputBeforeText(String sFieldLabel,String sValue){
		waitElementClickableReady(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input"));
		if (sValue.contentEquals("true")){
			if (!driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).isSelected()){
				driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).click();
			}
		} else {
			if (driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).isSelected()){
				driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).click();
			}
		}
	}
	public static void checkBoxInputBeforeText(String sFieldLabel,boolean sValue){
		waitElementClickableReady(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input"));
		if (sValue){
			if (!driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).isSelected()){
				driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).click();
			}
		} else {
			if (driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).isSelected()){
				driver.findElement(By.xpath("//td[contains(.,'" + sFieldLabel + "')]/preceding-sibling::td/input")).click();
			}
		}
	}
	public static void allCheckBoxes(By sValue,boolean bSelect){
		List<WebElement> checkBoxList=driver.findElements(sValue);
		for(WebElement checkBox:checkBoxList){
			if(logicalXOR(checkBox.isSelected(),bSelect)){
				mouseOverElement(checkBox);
				try {
					waitWindowComplate(20,1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mouseOverElementRobotClick(checkBox);
			}
		}
	}
	public static void logOut(){
		By Logout=By.xpath("//div[contains(@title,'Logout') and contains(@id,'logout')]");
		By oLinkLogin=By.partialLinkText("Click here to login");
		By oUserName=By.xpath("//input[@name='j_username']");
		waitElementClickableReady(Logout);
		mouseOverElementClick(Logout);
		try {
			Thread.sleep(200);
		} catch (Exception ignore) {}		
        selectOKAlert();
        longWait.until(ExpectedConditions.presenceOfElementLocated(oLinkLogin));
        longWait.until(ExpectedConditions.visibilityOfElementLocated(oLinkLogin));
		try {
			Thread.sleep(1000);
		} catch (Exception ignore) {}
		longWait.until(ExpectedConditions.elementToBeClickable(oLinkLogin));
        driver.findElement(oLinkLogin).click();
		try {
			Thread.sleep(1000);
		} catch (Exception ignore) {}        
        waitElementClickableReady(oUserName);
        MyPrint.myPrint("Logout function", "User logged out.");
		driver.manage().deleteAllCookies();
		try {
			Thread.sleep(1000);
		} catch (Exception ignore) {}
	}

	public static void login(String sUser, String sPWD) throws Exception {		
		bypassSecurityCert();
		login(sUser,sPWD, false);
	}
	@SuppressWarnings("unchecked")
	public Class<T> getClassType () {
	  return ((Class<T>) value.getClass());
	}

	
	public static void login(String sUser, String sPWD,Stack<String> windowHandles,String... Cred) throws Exception {
		if(Cred.length==0){
			bypassSecurityCert();
			if(sUser.equalsIgnoreCase("admin")||!(AD)){
				System.out.println("Trying to login as admin user - ");
				login(sUser,sPWD, false);
				if (windowHandles!=null){
					windowHandles.removeAllElements();
				}
				String sMainWindowHandle=driver.getWindowHandle();
				if (sMainWindowHandle.length()>0){
					windowHandles.push(sMainWindowHandle);
				} else {
					Assert.fail("Failed to login by using username '" + sUser + "'");
				}
			} else {
				readWriteINI<String> iniObj1=new readWriteINI<String>(((Parent()).split("_")[1]).toUpperCase()+"AD",sUser);
				String sLocalUserTemp="";
				sLocalUserTemp=iniObj1.ini.get(iniObj1.Category,iniObj1.Key,String.class);
				if(sLocalUserTemp.length()<3){
					Assert.fail("Failed to find AD username '" + sUser + "' local user group.");
				}
				String[] myArry = sLocalUserTemp.split(",");
				if(myArry.length<1){
					Assert.fail("Failed to find AD username '" + sUser + "' local user group.");
				}
				System.out.println("Trying to login as admin user - ");
				login(ADMIN_USERNAME, ADMIN_PASSWORD, false);
				ModuleAuthMode myAuthMode = new ModuleAuthMode();
				myAuthMode.MapDomainGroup(myArry);
				logOut();
				System.out.println("Wait cache updating...and then login as mapped QALAB.com user");
//				robotWait(60);
				robotWait(40);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(10);
				login("QALAB.com\\test","test", false);
				robotWait(10);
				if (windowHandles!=null){
					windowHandles.removeAllElements();
				}
				String sMainWindowHandle=driver.getWindowHandle();
				if (sMainWindowHandle.length()>0){
					windowHandles.push(sMainWindowHandle);
				} else {
					Assert.fail("Failed to login by using username '" + sUser + "'");
				}			
			}
		} else {
			if(AD){
				System.out.println("Trying to login as admin user - ");
				login(ADMIN_USERNAME, ADMIN_PASSWORD, false);
				ModuleAuthMode myAuthMode = new ModuleAuthMode();
				myAuthMode.MapDomainGroup(Cred);
				logOut();
				System.out.println("Wait cache updating...and then login as mapped QALAB.com user");
//				robotWait(60);
				robotWait(40);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(60);
//				robotWait(10);
				login("QALAB.com\\test","test", false);
				robotWait(10);
				if (windowHandles!=null){
					windowHandles.removeAllElements();
				}
				String sMainWindowHandle=driver.getWindowHandle();
				if (sMainWindowHandle.length()>0){
					windowHandles.push(sMainWindowHandle);
				} else {
					Assert.fail("Failed to login by using username '" + sUser + "'");
				}
			} else {
				bypassSecurityCert();
				System.out.println("Try to login as user '" + sUser + "' ---");
				login(sUser,sPWD, false);
				if (windowHandles!=null){
					windowHandles.removeAllElements();
				}
				String sMainWindowHandle=driver.getWindowHandle();
				if (sMainWindowHandle.length()>0){
					windowHandles.push(sMainWindowHandle);
				} else {
					fail("Failed to login by using username '" + sUser + "'");
				}				
			}
		}
	}
	public static void popuplogin(String sUser, String sPWD) throws Exception {
		bypassSecurityCert();
		popuplogin(sUser,sPWD, false);
	}	
	
    public static boolean bypassSecurityCert() {
        try {
            if (driver.getTitle().contains("Certificate")) {
                driver.navigate().to("javascript:document.getElementById('overridelink').click()");
                MyPrint.myPrint("bypassSecurityCertificate", "Security warning bypassed.");
                return true;
            }
        } catch (Exception ignore) {}
        return false;
    }	
	
	public static void login(String sUser, String sPWD, boolean hideExceptions) throws Exception {
		By oUserName=By.xpath("//input[@name='j_username']");
		By oUserName2=By.xpath("//form[@id='loginForm']/table/tbody/tr/td/div[contains(text(), 'Username:')]");
		By oPasswd=By.xpath("//input[@name='j_password']");
		By oLoginButton=By.id("formLoginButton");
		By oBuildLabel=By.id("loginContainer_revision");
		By Logout=By.xpath("//div[contains(@style, 'logout_off.png')]");
		if(driver==null){
			fail("Driver not created, test failed.");
		}
		if(!driver.toString().contains("InternetExplorerDriver: internet explorer on WINDOWS (")){
			fail("Lost connection error, test failed.");
		}
		driver.manage().deleteAllCookies();
		Thread.sleep(1000);
        try {
        	longWait.until(ExpectedConditions.elementToBeClickable(oLoginButton));
        	longWait.until(ExpectedConditions.visibilityOfElementLocated(oUserName2));
        	longWait.until(ExpectedConditions.visibilityOfElementLocated(oUserName));
        	longWait.until(ExpectedConditions.visibilityOfElementLocated(oPasswd));
        	WebElement BuildVersion = driver.findElement(oBuildLabel);
        	buildVersion = BuildVersion.getText();
        	
            if (buildVersion.toLowerCase().contains("enterprise")){
            	buildVersion=BuildVersion.getAttribute("title");
            }
        	MyPrint.myPrint("enterCredentials", "Build# " + buildVersion);
        	
			readWriteINI<String> iniObj1=new readWriteINI<String>("BuildInfo","BuildString");
	        iniObj1.setValue(buildVersion);
	        iniObj1.WriteINI();
	        iniObj1=new readWriteINI<String>("Server","ServerURL");
	        iniObj1.setValue(SERVER_URL);
	        iniObj1.WriteINI();
        	driver.findElement(oUserName).click();
        	driver.findElement(oUserName).clear();
        	driver.findElement(oUserName).sendKeys(sUser);
        	if (driver.findElement(oUserName).getAttribute("value").length()!=sUser.length()){
        		driver.findElement(oUserName).click();
        		driver.findElement(oUserName).clear();
        		driver.findElement(oUserName).sendKeys(sUser);
        	}
        	driver.findElement(oPasswd).click();
        	driver.findElement(oPasswd).clear();
        	driver.findElement(oPasswd).sendKeys(sPWD);
        	if (driver.findElement(oPasswd).getAttribute("value").length()!=sPWD.length()){
        		driver.findElement(oPasswd).click();
        		driver.findElement(oPasswd).clear();
        		driver.findElement(oPasswd).sendKeys(sPWD);
        		robotWait(1);
        		ExcuteKeyEvent(KeyEvent.VK_TAB);
        		robotWait(1);
        	}
        	driver.findElement(oLoginButton).click();
        	PageLoadWait.GenericWaitLoad();
        	robotWait(1);
        	PageLoadWait.GenericWaitLoad();
        	robotWait(1);
        	PageLoadWait.GenericWaitLoad();
        	
        	if(driver.findElements(By.xpath("//div[@id='loginContainer']")).size()>0){
        		if(sUser.toLowerCase().contains("qalab")){
                	driver.findElement(oPasswd).click();
                	driver.findElement(oPasswd).clear();
                	driver.findElement(oPasswd).sendKeys(sPWD);
                	if (driver.findElement(oPasswd).getAttribute("value").length()!=sPWD.length()){
                		driver.findElement(oPasswd).click();
                		driver.findElement(oPasswd).clear();
                		driver.findElement(oPasswd).sendKeys(sPWD);
                		robotWait(1);
                		robotWait(1);
                		ExcuteKeyEvent(KeyEvent.VK_TAB);
                		robotWait(1);
                		robotWait(1);
                	}
                	driver.findElement(oLoginButton).click();
                	PageLoadWait.GenericWaitLoad();
                	robotWait(1);
                	PageLoadWait.GenericWaitLoad();
                	robotWait(1);
                	PageLoadWait.GenericWaitLoad();
        		} else {
            		System.out.println("login error. exit testing.");
            		return;        			
        		}
        	}
        	
        	longWait.until(ExpectedConditions.visibilityOfElementLocated(Logout));
        	if (!hideExceptions) longWait.until(ExpectedConditions.elementToBeClickable(Logout));
        } catch (Exception e) {
        	if (!hideExceptions) {        		
        	}
        }
        MyPrint.myPrint("Login function", "User '" + sUser + "' logged in.");
	}

	public static void popuplogin(String sUser, String sPWD, boolean hideExceptions) throws Exception {
		By oUserName=By.xpath("//input[@name='j_username']");
		By oUserName2=By.xpath("//form[@id='loginForm']/table/tbody/tr/td/div[contains(text(), 'Username:')]");
		By oPasswd=By.xpath("//input[@name='j_password']");
		By oLoginButton=By.id("formLoginButton");
		By oBuildLabel=By.id("loginContainer_revision");
        try {
        	wait.until(ExpectedConditions.elementToBeClickable(oLoginButton));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(oUserName2));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(oUserName));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(oPasswd));
        	WebElement BuildVersion = driver.findElement(oBuildLabel);
        	buildVersion = BuildVersion.getText();
        	MyPrint.myPrint("popup window enterCredentials", "Build# " + BuildVersion.getText());
        	driver.findElement(oUserName).click();
        	driver.findElement(oUserName).clear();
        	driver.findElement(oUserName).sendKeys(sUser);
        	if (driver.findElement(oUserName).getAttribute("value").length()!=sUser.length()){
        		driver.findElement(oUserName).click();
        		driver.findElement(oUserName).clear();
        		driver.findElement(oUserName).sendKeys(sUser);
        	}
        	driver.findElement(oPasswd).click();
        	driver.findElement(oPasswd).clear();
        	driver.findElement(oPasswd).sendKeys(sPWD);
        	if (driver.findElement(oPasswd).getAttribute("value").length()!=sPWD.length()){
        		driver.findElement(oPasswd).click();
        		driver.findElement(oPasswd).clear();
        		driver.findElement(oPasswd).sendKeys(sPWD);
        	}
        	wait.until(ExpectedConditions.visibilityOfElementLocated(oLoginButton));
        	mouseOverElementClick(oLoginButton);
        	Thread.sleep(1000);
        } catch (Exception e) {
        	if (!hideExceptions) {        		
        	}
        }
        MyPrint.myPrint("Popup Login:", "User '" + sUser + "' logged in.");
	}


	
	public static void switchToNewPopUpWindow() throws InterruptedException {
		String WindowHandle="";
		boolean bWait=true;
		int waitIT=0;
		Set <String> beforeHandles=driver.getWindowHandles();
		Thread.sleep(3000);
		Set <String> afterHandles=driver.getWindowHandles();
       while (beforeHandles.size()==afterHandles.size() && bWait){
    	   Thread.sleep(3000);
    	   afterHandles=null;
    	   afterHandles=driver.getWindowHandles();
    	   if(waitIT>1000){
    		   bWait=false;
    		   return;
    	   }
    	   waitIT++;
        }
		for (String subWindowHandler : afterHandles) {
			WindowHandle=subWindowHandler;
		}
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);		
		driver.switchTo().window(WindowHandle);
		waitWindowComplate(20,1000);
	}
	
	
	public static void waitWindowComplate(int seconds, int iSleepMillsec) throws InterruptedException {
        Boolean stillChanging = true;
        int iterations = 0;
        int maxIterations = (1000 * seconds) / iSleepMillsec;
        String previous = "";
        String current = "";
        try {
        	while (!((JavascriptExecutor)driver).executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete")){
        		Thread.sleep(500);
        		iterations++;
        		waitSituationLoad("Updating");
        		if (iterations>maxIterations){
					try {
						throw new LoadingTimeOutException("failed to load window content within the time given frame.time out.");
					} catch (LoadingTimeOutException e) {
						e.printStackTrace();
					}
        		}
        	}
        } catch (Exception e) {
        	Thread.sleep(10000);
        }
        iterations=0;
        try {
        	while (stillChanging && iterations < maxIterations){
        		if(((JavascriptExecutor)driver).executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete")){
        			current = driver.getPageSource();
        			stillChanging = (!previous.equals(current));
        			previous = current;
        		}
        		Thread.sleep(iSleepMillsec);
        		iterations++;
        	}	 
        } catch (NoSuchWindowException e) {
        	Thread.sleep(5000);
        } catch (UnhandledAlertException e1) {
        	System.out.println("unhandled exception threwed.");
			try{
				Alert alert = driver.switchTo().alert();
				System.out.println("Alert message '" + alert.getText() + "'.");
				alert.accept();
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
			} catch (NoAlertPresentException ignore) {}
        	Thread.sleep(2000);
        }
	}
  		
	
	public static void OpenPopupByClickObj(WebElement objWebElmt) throws InterruptedException{
		String WindowHandle="";
		String currentHandle="";
		boolean bWait=true;
		int waitIT=0;
		Set <String> beforeHandles=driver.getWindowHandles();
		Set <String> afterHandles=null;
		currentHandle=driver.getWindowHandle();
		try {
			clickElementWithNoWait(objWebElmt);		
			Thread.sleep(1000);
			PageLoadWait.GenericWaitLoad();
			afterHandles=driver.getWindowHandles();
			while (beforeHandles.size()==afterHandles.size() && bWait){
				Thread.sleep(1000);
				afterHandles=null;
				afterHandles=driver.getWindowHandles();
				if(waitIT>1000){
					bWait=false;
					return;
				}
				waitIT++;
			}
			Set <String> mySet = setDiff(afterHandles,beforeHandles);
			if (mySet.size()==1){
				WindowHandle=mySet.iterator().next().toString();
			} else {
				System.out.println(" error switch to pop up window....");
			}
			if (!currentHandle.equalsIgnoreCase(WindowHandle)){
				driver.switchTo().window(WindowHandle);
				waitWindowComplate(20,1000);
			}
		} catch (UnhandledAlertException oAlertException) {
			try{
				Alert alert = driver.switchTo().alert();
				System.out.println("Alert message '" + alert.getText() + "'.");
				alert.accept();
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
			} catch (NoAlertPresentException ignore) {}			
		}
	}
	
	public static void OpenPopupByClickObj(WebElement objWebElmt, Stack<String> windowHandles) throws InterruptedException{
		String WindowHandle="";
		String currentHandle="";
		int waitIT=0,waitComplete=0;
		boolean bWait=true,	bwaitComplete=true;	
		HWND wHwndBeforeOpen,wHwndAfterOpen;
		Set <String> beforeHandles=driver.getWindowHandles();
		Set <String> afterHandles=null;
		wHwndBeforeOpen=User32.INSTANCE.GetForegroundWindow();			
		currentHandle=driver.getWindowHandle();
		Thread.sleep(1500);
		clickElementWithNoWait(objWebElmt);
		Thread.sleep(3000);
		wHwndAfterOpen=User32.INSTANCE.GetForegroundWindow();
	    while (wHwndAfterOpen.equals(wHwndBeforeOpen) && bWait){
		      Thread.sleep(1000);
		      wHwndAfterOpen=User32.INSTANCE.GetForegroundWindow();
		      if(waitIT>20){
		    	  	bWait=false;
		    	  	System.out.println("driver open new window error, time out");
		   			return;
		      }
		      waitIT++;
	    }
	    Thread.sleep(2000);
		afterHandles=driver.getWindowHandles();
		Thread.sleep(1000);
		while (beforeHandles.size()==afterHandles.size() && bWait){
    	   Thread.sleep(1000);
    	   afterHandles=null;
    	   afterHandles=driver.getWindowHandles();
    	   if(waitIT>1000){
    		   bWait=false;
    		   return;
    	   }
    	   waitIT++;
        }
		Set <String> mySet = setDiff(afterHandles,beforeHandles);
		if (mySet.size()==1){
			WindowHandle=mySet.iterator().next().toString();
		} else {
			System.out.println(" error switch to pop up window....");
		}
		if (!currentHandle.equalsIgnoreCase(WindowHandle)){
			waitWindowComplate(20,500);
			String newTitle="";

			newTitle=getActiveWindowTitle();
			
		    while((newTitle.contains(".jsp")|| newTitle.length()==0) && bwaitComplete){
		    	System.out.println("Window title is '" + getActiveWindowTitle() + "'. wait to complete");
		    	if (waitComplete<30){
		    		waitComplete++;
		    	} else {
		    		bwaitComplete=false;
		    		System.out.println("Time out to waiting...Window title is " + newTitle + ". go ahead anyway.");
		    	}
		    	Thread.sleep(1000);
		    	newTitle="";
		    	newTitle=getActiveWindowTitle();
		    }
		    Thread.sleep(3000);
		    waitLoaded(driver);			
			driver.switchTo().window(WindowHandle);
			waitWindowComplate(20,1000);			
			driver.switchTo().window(WindowHandle);
			waitWindowComplate(20,1000);
			windowHandles.push(WindowHandle);
		}
	}
	
	
    protected static boolean switchToWindowUsingTitle(String title,Stack<String> windowHandles) {
            String currentWindow = driver.getWindowHandle(); 
            Set<String> availableWindows = driver.getWindowHandles(); 
            if (!availableWindows.isEmpty()) {
                    for (String windowId : availableWindows) { 
                    	driver.switchTo().window(windowId);
                            if (driver.switchTo().window(windowId).getTitle().equals(title)) { 
                                    return true; 
                            } else { 
                                    driver.switchTo().window(currentWindow); 
                                    windowHandles.push(currentWindow);
                            } 
                    } 
            } 
            return false; 
    } 
    
    protected static boolean switchToWindowPartialTitle(String title,Stack<String> windowHandles) { 
        Set<String> availableWindows = driver.getWindowHandles();
        boolean bExistsHandler=false;
        if (!availableWindows.isEmpty()) {
                for (String windowId : availableWindows) { 
                		driver.switchTo().window(windowId);
                        if (driver.getTitle().toLowerCase().contains(title.toLowerCase())) {                        	
                        	for (String s : windowHandles){
                        		   if(windowId.equals(s)){
                        			   bExistsHandler=true;
                        			   break;
                        		   };
                        	}
                        	if(!bExistsHandler){
                        		windowHandles.push(windowId);
                        	}
                            return true;
                        }
                } 
        } 
        return false; 
} 
	
    public static ExpectedCondition<Boolean> absenceOfElementLocated(
    	      final By locator) {
    	    return new ExpectedCondition<Boolean>() {
    	      @Override
    	      public Boolean apply(WebDriver driver) {
    	        try {
    	          driver.findElement(locator);
    	          return false;
    	        } catch (NoSuchElementException e) {
    	          return true;
    	        } catch (StaleElementReferenceException e) {
    	          return true;
    	        }
    	      }

    	      @Override
    	      public String toString() {
    	        return "element to not being present: " + locator;
    	      }
    	    };
    	  }
    
	public static void OpenPopupByClickObj(WebElement objWebElmt, Stack<String> windowHandles,String sTitle,int iWaitSec) throws InterruptedException{
		clickElementWithNoWait(objWebElmt);
		if(VBSUtils.WaitForWindowTitle(sTitle,iWaitSec)){
			if(switchToWindowPartialTitle(sTitle,windowHandles)){
				new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='applicationLoadingSplashMessageBox'][@style='display: none;']")));
				new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='applicationLoadingSplashScreen'][@style='display: none;']")));
			}
		} else {
			System.out.println("driver open new window error, time out");
		}
	}
	
	public static void waitLoaded(WebDriver driver)
	{
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() 
	    {
	        public Boolean apply(WebDriver driver)
	        {
	            return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete");
	        }
	    };
	    Wait<WebDriver> wait = new WebDriverWait(driver,30);
	    try
	    {
	        wait.until(expectation);
	    }
	    catch(Throwable error)
	    {
	    	AssertJUnit.assertTrue("Timeout waiting for Page Load Request to complete.",true);
	    }
	}
	
	public static void ClosePopupByClickObj(WebElement objWebElmt, String sHandler) throws InterruptedException{
		int waitIT=0;
		boolean bWait=true;
		HWND wHwndBeforeClose,wHwndAfterClose;
		wHwndBeforeClose=User32.INSTANCE.GetForegroundWindow();		
		clickElementWithNoWait(objWebElmt);
		PageLoadWait.GenericWaitLoad();
		wHwndAfterClose=User32.INSTANCE.GetForegroundWindow();
	    while (wHwndAfterClose.equals(wHwndBeforeClose) && bWait){
		      Thread.sleep(1000);
		      wHwndAfterClose=User32.INSTANCE.GetForegroundWindow();
		      if(waitIT>20){
		    	  	bWait=false;
		    	  	System.out.println("Expect the window closed but not....time out");
		    	  	driver.close();
		   			return;
		      }
		      waitIT++;
	    }
	    Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.switchTo().window(sHandler);
		waitWindowComplate(20,1000);
	}
	

	public static void ClosePopupByClickObj(WebElement objWebElmt, Stack<String> windowHandles) throws InterruptedException{
		int waitIT=0;
		boolean bWait=true;
		HWND wHwndBeforeClose,wHwndAfterClose;
		wHwndBeforeClose=User32.INSTANCE.GetForegroundWindow();
		System.out.println("before close driver window title '" + getActiveWindowTitle() + "'.");
		
		
		if (isDialogPresent()){
			String sPopupMessage;
			try{
				sPopupMessage=driver.switchTo().alert().getText();
				System.out.println("Pop up found before close driver window with message '" + sPopupMessage +"'.");
			} catch(NoAlertPresentException ignore) {
				System.out.println("Pop up found and dismissed itself.");
			}
		}		
		if (objWebElmt!=null){
			clickElementWithNoWait(objWebElmt);
		} else {
			driver.close();
		}
		Thread.sleep(1000);
		wHwndAfterClose=User32.INSTANCE.GetForegroundWindow();
	    while (wHwndAfterClose.equals(wHwndBeforeClose) && bWait){
		      Thread.sleep(1000);
		      System.out.println("After close driver window title '" + getActiveWindowTitle() + "'.");
		      wHwndAfterClose=User32.INSTANCE.GetForegroundWindow();
		      
		      if(waitIT==15){
		    	  	System.out.println("After 15 seconds, the window still remains. Do one more click.");
		    	  	clickElementWithNoWait(objWebElmt);
		    	  	Thread.sleep(1000);
		      }
		      
		      if(waitIT>20){
		    	  	bWait=false;
		    	  	fail("close driver error, time out");
		    	  	driver.close();
		   			windowHandles.pop();
		   			driver.switchTo().window(windowHandles.peek());		    	   
		   			return;
		      }
		       waitIT++;
	    }
	    System.out.println("Current window with title " + getActiveWindowTitle());
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		windowHandles.pop();
		driver.switchTo().window(windowHandles.peek());
		PageLoadWait.GenericWaitLoad();
	}
	
	
	public static void CloseDriver() throws InterruptedException{
		boolean bWait=true;
		int waitIT=0;
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		Set<String> currentHandles=driver.getWindowHandles();
		driver.close();
		Thread.sleep(3000);
	    while (symmetricDifference(currentHandles,driver.getWindowHandles()) && bWait){
	       Thread.sleep(1000);
	       if(waitIT>30){
	    	   bWait=false;
	    	   System.out.println("close driver time out");
	    	   return;
	       }
	       waitIT++;
	    }	    	    
	}
	public static void CloseDriver(Stack<String>windowHandles) throws InterruptedException{
		boolean bWait=true;
		int waitIT=0;
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		Set<String> currentHandles=driver.getWindowHandles();
		driver.close();
		Thread.sleep(7000);
	    while (symmetricDifference(currentHandles,driver.getWindowHandles()) && bWait){
	       Thread.sleep(1000);
	       if(waitIT>30){
	    	   bWait=false;
	    	   System.out.println("close driver time out");
	    	   return;
	       }
	       waitIT++;
	    }
	    windowHandles.pop();
	    Thread.sleep(3000);
	    String previousTopWindowTitle=getActiveWindowTitle();
	    String CurrentTopWindowTitle=windowHandles.peek();
	    
	    
	    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	    if (switchTopwindowWithTitle(driver, previousTopWindowTitle)){
	    	System.out.println("Switched back to window '" + previousTopWindowTitle + "' successufully.");
	    } else {
	    	System.out.println("Switched back to previous window with title '" + previousTopWindowTitle + "'.");
	    	driver.switchTo().window(CurrentTopWindowTitle);
	    }
	}

	public static void CloseTestDriver(Stack<String>windowHandles) {
		boolean bWait=true;
		int waitIT=0;
		try{
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			Set<String> currentHandles=driver.getWindowHandles();
			driver.close();
			Thread.sleep(7000);
		    while (symmetricDifference(currentHandles,driver.getWindowHandles()) && bWait){
		       Thread.sleep(1000);
		       if(waitIT>30){
		    	   bWait=false;
		    	   System.out.println("close driver time out");
		    	   return;
		       }
		       waitIT++;
		    }
		    windowHandles.pop();
		    Thread.sleep(3000);
		    String previousTopWindowTitle=getActiveWindowTitle();
		    String CurrentTopWindowTitle=windowHandles.peek();
		    
		    
		    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		    if (switchTopwindowWithTitle(driver, previousTopWindowTitle)){
		    	System.out.println("Switched back to window '" + previousTopWindowTitle + "' successufully.");
		    } else {
		    	System.out.println("Switched back to previous window with title '" + previousTopWindowTitle + "'.");
		    	driver.switchTo().window(CurrentTopWindowTitle);
		    }
		} catch (InterruptedException e){
			fail("Error occurred while closing the window.");
		}
	}

	protected static boolean switchTopwindowWithTitle(WebDriver driver, String title) { 
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
		Set<String> availableWindows = driver.getWindowHandles(); 
		if (!availableWindows.isEmpty()) { 
			for (String windowId : availableWindows) { 
				if (driver.switchTo().window(windowId).getTitle().equals(title)) { 
					return true; 
				} 
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignore) {}
			} 
		} 
		return false; 
	} 

	public int getActionCase(String actionPropertyName){
		int intActionCaseNumber=0;
		String actionName;
		actionName=actionPropertyName.toLowerCase();
		intActionCaseNumber=webElementPropertyMap.get(actionName);
		if (intActionCaseNumber>0)
			return intActionCaseNumber;	
		else
			return 0;
	}
	public static Boolean symmetricDifference(Set<String> a, Set<String> b) {
	    Set<String> result = new HashSet<String>(a);
	    for (String element : b) {
	        if (!result.add(element)) {
	            result.remove(element);
	        }
	    }
	    return result.size()==0;
	}
	public static Set<String> setDiff(Set<String> a, Set<String> b) {
	    Set<String> result = new HashSet<String>(a);
	    for (String element : b) {
	        if (!result.add(element)) {
	            result.remove(element);
	        }
	    }
	    return result;
	}

@SuppressWarnings("unchecked")
public void waitUntilTextChanges(WebDriver driver, String... titles) {
	@SuppressWarnings({ "rawtypes" })
	Wait fwait = new FluentWait(driver)
	.withTimeout(30, TimeUnit.SECONDS)
	.pollingEvery(1, TimeUnit.SECONDS)
	.ignoring(NoSuchElementException.class);
	fwait.until(new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			boolean titleMatched = false;
			// Get current window title
			String windowTitle = driver.getTitle();
			for(String title : titles){
				// Iterate through all input titles and compare with window title 
				titleMatched = windowTitle.equalsIgnoreCase(title);
				// If match found, exit 
				if(titleMatched){
					break;
				}
			}    
			return titleMatched;
           }
       });
   }
	
@SuppressWarnings("unchecked")
public static void waitUntilTitleChanges(WebDriver driver, String... titles) {
	@SuppressWarnings({ "rawtypes" })
	Wait fwait = new FluentWait(driver)
	.withTimeout(30, TimeUnit.SECONDS)
	.pollingEvery(1, TimeUnit.SECONDS)
	.ignoring(NoSuchElementException.class);
	fwait.until(new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver driver) {
			boolean NotitleMatched = false;
			// Get current window title
			String windowTitle = driver.getTitle();
			for(String title : titles){
					if(windowTitle.length()>0) {
						// If NO match found, exit
						if(!windowTitle.toLowerCase().contains(title.toLowerCase())){
							NotitleMatched=true;
							break;
						}
					}
			}    
			return NotitleMatched;
           }
       });
   }

	@SuppressWarnings("unchecked")
	public static void waitUntilBooleanCondition(String sFindwebElementMethodName,String sFindwebElementMethodValue,String sWebElementBooleanProperty,boolean sExpectedBooleanResult){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
		.withTimeout(30, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);		
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement oWebElement = getElement(sFindwebElementMethodName,sFindwebElementMethodValue);
				String sElementBooleanAttribute = oWebElement.getAttribute(sWebElementBooleanProperty);
				String compareResult;
				if (sExpectedBooleanResult)
					compareResult="true";
				else
					compareResult="false";	                      
				if (sElementBooleanAttribute!=null)
					if(sElementBooleanAttribute.equals(compareResult))
						return true;
					else
						return false;
				else
					if(sFindwebElementMethodName=="id"){
						String sElementBooleanAttribute2 = ((JavascriptExecutor)driver).executeScript("return document.getElementById('" + sFindwebElementMethodValue + "')."+ sWebElementBooleanProperty +";").toString();
						if (sElementBooleanAttribute2!=null){
							if(sElementBooleanAttribute2.equals(compareResult))
								return true;
							else
								return false;
						} else
							return null;
					} else
						return null;
			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
	public static boolean waitUntilBooleanCondition(By targetWebElementLabel,boolean sExpectedBooleanResult, int iSeconsTimeOut){
		try{
			@SuppressWarnings({ "rawtypes" })
			Wait fwait = new FluentWait(driver)
					.withTimeout(iSeconsTimeOut, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);		
					fwait.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver driver) {
							if (sExpectedBooleanResult && VisibleLabel(targetWebElementLabel))
								return true;
							else
								return false;
						}
					});
					return true;
		} catch (TimeoutException eTimeout) {
			return false;
		}
	}
	
	public static void waitSituationLoad(String sPropertyName){
		if (sPropertyName==null){}
		else {
			switch (sPropertyName) {
			case "RiskShildLoadMessage":
				waitUntilBooleanCondition(getElement(DataMap, "RiskShildLoadMessage"),"display",false);
				break;
			case "loadingErrorMsg":
				waitUntilBooleanCondition("textinvisible","loadingErrorMsgXpath",true,"Loading more situations, please wait...");
			case "PassByInstall":
				if(driver.findElements(By.xpath("//div[@id='logo-title'][contains(.,'Plugin Installation')]")).size()>0){
					waitUntilBooleanCondition("disappear","PluginInstallation",true,"");						
				}
				break;
			case "Updating":
				waitUntilBooleanCondition("disappear","UpdatingChecklist",true,"");
				break;
			case "RenderText":
				waitUntilBooleanCondition("textshow","PluginInstallation",true,"");
				break;					
			default:
				break;
			}
		}
	}

	public static void waitSituationLoad(String sPropertyName,String sDataID,String sText){
		if (sPropertyName==null){}
		else {
			switch (sPropertyName) {
			case "RiskShildLoadMessage":
				waitUntilBooleanCondition(getElement(DataMap, "RiskShildLoadMessage"),"display",false);
				break;
			case "loadingErrorMsg":
				waitUntilBooleanCondition("textinvisible","loadingErrorMsgXpath",true,"Loading more situations, please wait...");
				break;
			case "PassByInstall":
				waitUntilBooleanCondition("disappear","PluginInstallation",true,"");
				break;
			case "RenderText":
				waitUntilBooleanCondition("textshow",sDataID,true,sText);
				break;					
			default:
				break;
			}
		}
	}

	public static void LoadviewCheck(String sPropertyName,By oLocator,String sText){
		if (sPropertyName==null){}
		else {
			switch (sPropertyName) {
			case "RiskShildLoadMessage":
				waitUntilBooleanCondition(getElement(DataMap, "RiskShildLoadMessage"),"display",false);
				break;
			case "loadingErrorMsg":
				waitUntilBooleanCondition("textinvisible","loadingErrorMsgXpath",true,"Loading more situations, please wait...");
				break;
			case "PassByInstall":
				waitUntilBooleanCondition("disappear","PluginInstallation",true,"");
				break;
			case "RenderText":
				waitUntilBooleanCondition("textshow",oLocator,true,sText);
				break;
			case "Clickable":
				waitUntilBooleanCondition("clickable",oLocator,true,"");
				break;
			default:
				break;
			}
		}
	}	

	@SuppressWarnings("unchecked")
	public static void waitUntilBooleanCondition(WebElement oWebElement,String sPropertyName, boolean sExpectedBooleanResult){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
		.withTimeout(30, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);		
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boolean bSet=false;
				boolean resultValue = false;
				switch (sPropertyName.toLowerCase())	{
				case "id":
					bSet=true;
					break;
				case "display":
					bSet=true;
					resultValue=oWebElement.isDisplayed();
					break;
				case "visible":
					bSet=true;
					resultValue=oWebElement.getCssValue("visibility").equalsIgnoreCase("visible");
					break;
				case "enabled":
					bSet=true;
					resultValue=oWebElement.isEnabled();
					break;
				case "selected":
					bSet=true;
					resultValue=oWebElement.isSelected();	           	     			
					break;
				default:
					break;
				}
				return logicalEqual(logicalEqual(resultValue,sExpectedBooleanResult),bSet);
			}
		});
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void waitUntilBooleanCondition(String sPropertyName,String sDataID, boolean sExpectedBooleanResult,String sText){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
		.withTimeout(30, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);
		try{
			switch (sPropertyName.toLowerCase()){
			case "invisible":
				fwait.until(ExpectedConditions.invisibilityOfElementLocated(getElementLocator(sDataID)));
				break;
			case "textinvisible":
				fwait.until(ExpectedConditions.invisibilityOfElementWithText(getElementLocator(sDataID),sText)); 
				break;
			case "clickable":
				fwait.until(ExpectedConditions.elementToBeClickable(getElementLocator(sDataID))); 
				break;	    		
			case "textshow":
				fwait.until(ExpectedConditions.textToBePresentInElement(getElementLocator(sDataID),sText)); 
				break;
			case "presence":
				fwait.until(ExpectedConditions.presenceOfElementLocated(getElementLocator(sDataID))); 
				break;	    		
			case "display":
				break;
			case "disappear":
				fwait.until(ExpectedConditions.stalenessOf(getElement(sDataID)));
				break;
			case "dismissalert":
				selectOKAlert();
				break;
			case "title":
				fwait.until(ExpectedConditions.titleIs(sText));
				break;
			case "intitle":
				fwait.until(ExpectedConditions.titleContains(sText));
				break;
			default:
			} 
		} catch (NullPointerException e) {
			try {
				waitWindowComplate(20,500);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
		} catch (Exception e){
			try {
				waitWindowComplate(20,500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void waitUntilBooleanCondition(String sPropertyName,By oLocator, boolean sExpectedBooleanResult,String sText){
		@SuppressWarnings({ "rawtypes" })
		Wait fwait = new FluentWait(driver)
		.withTimeout(30, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);
		try{
			switch (sPropertyName.toLowerCase()){
			case "invisible":
				fwait.until(ExpectedConditions.invisibilityOfElementLocated(oLocator));
				break;
			case "clickable":
				fwait.until(ExpectedConditions.elementToBeClickable(oLocator)); 
				break;	    		    		
			case "textinvisible":
				fwait.until(ExpectedConditions.invisibilityOfElementWithText(oLocator,sText)); 
				break;
			case "textshow":
				fwait.until(ExpectedConditions.textToBePresentInElement(oLocator,sText)); 
				break;
			case "presence":
				fwait.until(ExpectedConditions.presenceOfElementLocated(oLocator)); 
				break;	    		
			case "display":
				break;
			case "disappear":
				fwait.until(ExpectedConditions.stalenessOf(getElement(oLocator)));
				break;
			case "dismissalert":
				selectOKAlert();
				break;
			case "title":
				fwait.until(ExpectedConditions.titleIs(sText));
				break;
			case "intitle":
				fwait.until(ExpectedConditions.titleContains(sText));
				break;
			default:
			} 
		} catch (NullPointerException e) {
			try {
				waitWindowComplate(20,500);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
		} catch (Exception e){
			try {
				waitWindowComplate(20,500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}


	public static void openRiskShild(String sLUSName,String sPassword,Stack<String> sWindowHandlers) throws InterruptedException,Exception {
		waitElementpresence(getElementLocator("oLaunchShild2"));
		WebElement objWebElmtRiskShild=getElement("oLaunchShild2");
		waitElementClickableReady("oLaunchShild2");
		OpenPopupByClickObj(objWebElmtRiskShild, sWindowHandlers,"Summary",120);		
		if (driver.getTitle().contains("Certificate")) {
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			MyPrint.myPrint("bypassSecurityCertificate", "Security warning bypassed.");
		}
		if ((getElements("oUserNameField")).size()!=0){
			popuplogin(sLUSName, sPassword);
		}
		waitWindowComplate(20,500);
		waitSituationLoad("RiskShildLoadMessage");
		
		if(driver.findElements(By.xpath("//div[@id='nomadPrompt'][contains(@style,'visibility: visible')]")).size()>0){
			try{
				WebElement oNoMadMessage=driver.findElements(By.xpath("//div[@id='nomadPrompt_canvas']")).get(0);
				oNoMadMessage.findElement(By.xpath(".//div[@id='nomadPrompt_ok']")).click();
			} catch (ElementNotVisibleException ee){}
		}
		
		if (!(getElement("canvas_statusBar_splitViewCheckbox")).isSelected()){
			clickElementWithNoWait(getElement("canvas_statusBar_splitViewCheckbox"));
			waitSituationLoad("loadingErrorMsg");
			waitElementVisibility(getElementLocator("canvas_listLayer_headerBody"));			
		}
		longWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("canvas_listLayer_headerHeader")));		
	}
	public static void openRiskShildList(String sLUSName,String sPassword,Stack<String> sWindowHandlers) throws InterruptedException,Exception {
		waitElementpresence(getElementLocator("oLaunchShild2"));
		WebElement objWebElmtRiskShild=getElement("oLaunchShild2");
		waitElementClickableReady("oLaunchShild2");
		OpenPopupByClickObj(objWebElmtRiskShild, sWindowHandlers,"Summary",120);
		if (driver.getTitle().contains("Certificate")) {
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			MyPrint.myPrint("bypassSecurityCertificate", "Security warning bypassed.");
		}
		if ((getElements("oUserNameField")).size()!=0){
			popuplogin(sLUSName, sPassword);
		}
		waitWindowComplate(20,500);
		waitSituationLoad("RiskShildLoadMessage");
		By ViewSituationList = By.xpath("//div[@title='View situations as a list']");
		clickElementWithNoWait(ViewSituationList);
		waitElementClickableReady(getElementLocator("SummaryTable"));
	}
	
	public static boolean logicalXOR(boolean x, boolean y) {
		return ( ( x || y ) && ! ( x && y ) );
	}

	public static boolean logicalNotXOR(boolean x, boolean y) {
		return !( ( x || y ) && ! ( x && y ) );
	}
	
	
	public static boolean logicalEqual(boolean x, boolean y) {
		return (x==y);
	}

	public static boolean logicalAND(boolean x, boolean y) {
		return ( x && y );
	}

	@SuppressWarnings("unchecked")
	public static void LoadGlblData(){
		if(DataMap==null){
			File Datafile = new File("C://Temp//Book1.xlsx");
			try{
				HashMap<String, HashMap<String, HashMap<String, String>>> DataList2;
				DataList2=LoadExcel.GetExcelLines(Datafile,"Locators");
				DataMap = new HashMap<String, HashMap<String, HashMap<String, String>>>();
				DataMap.clear();
				DataMap.putAll(DataList2);
				MyPrint.myPrint("","** Load default global data sheet successfully. **");				
			} catch (NullPointerException e) {
				MyPrint.myPrint("","** DataList2 is null **");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	 }
	
	
    public static boolean VerifyListingUserGroupErrorVisible(boolean bExpectResult,By oLocator){
		boolean bErrorVisible=true;
		boolean bCheck=false;
		String sWidth;
		if (driver.findElements(oLocator).size()==0){
			bCheck=logicalNotXOR(bExpectResult,false);
			Assert.assertEquals(true,bCheck);	
			return bCheck;
		}
		sWidth=driver.findElement(oLocator).getCssValue("width");
		if (sWidth.length()>0) {
			if (sWidth.equals("0px"))
				bErrorVisible=false;
			if (sWidth.equals("1px"))
				bErrorVisible=false;
			bCheck=logicalNotXOR(bExpectResult,bErrorVisible);
			Assert.assertEquals(true,bCheck);
			return bCheck;
		} else {
			bCheck=logicalNotXOR(bExpectResult,false);
			Assert.assertEquals(true,bCheck);
			return bCheck;
		}	
    }
    
    public static void Navigation(String sButtonID,String myTag2,String myTag3){
    	Navigation2( sButtonID, myTag2, myTag3);
    }
    
    public static void Navigation2(String sButtonID,String myTag2,String myTag3) {
    	boolean bShortNavigation=false;
    	if (myTag3.isEmpty()){
    		bShortNavigation=true;
    		MyPrint.myPrint(myTag3,"Navigate to "+ myTag2 + " screen.");    		
    	} else{
    		MyPrint.myPrint(myTag3,"Navigate to "+ myTag2 + ">" + myTag3 + " screen.");
    	}
		if (!isElementPresent(getElementLocator(sButtonID))){
			By oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
			By oAdminButton=By.xpath("//td/div[@title='Admin UI'][@id='adminSiteButton']");
			By oAdminButton2=By.xpath("//div[@id='topRightCommands']/table/tbody/tr[@id='topRightCommandsContainer']/td/div[@title='Admin UI'][@id='adminSiteButton']");
			if(isElementPresent(oClientButton)){
			} else{
				wait.until(ExpectedConditions.elementToBeClickable(oAdminButton2));
				if(isElementPresent(oAdminButton)){
					wait.until(ExpectedConditions.elementToBeClickable(oAdminButton));
					clickElementWithNoWait(oAdminButton);
					PageLoadWait.GenericWaitLoad();
					waitElementClickableReady(oClientButton);
				}
			}



//			wait.until(ExpectedConditions.elementToBeClickable(oAdminButton2));
//			if(isElementPresent(oAdminButton)){
//				wait.until(ExpectedConditions.elementToBeClickable(oAdminButton));
//				clickElementWithNoWait(oAdminButton);
//				PageLoadWait.GenericWaitLoad();
//				waitElementClickableReady(oClientButton);
//			}
		}
		if (bShortNavigation){
			mouseOverElement("xpath","//div/ul/li/a[contains(text(), '" + myTag2 + "')]");
			clickElementAndWaitReady("xpath","//div/ul/li/a[contains(text(), '" + myTag2 + "')]");
			PageLoadWait.GenericWaitLoad();
		} else {
			By oMenu1=By.xpath("//div/ul/li/a[contains(text(), '" + myTag2 + "')]");
			mouseOverElement("xpath","//div/ul/li/a[contains(text(), '" + myTag2 + "')]");
			Actions DGAction = new Actions(driver);
			WebElement workElement=getElement(oMenu1);
			DGAction.moveToElement(workElement).build().perform();
			By oTarget=By.xpath("//div/ul/li/ul/li/a[text()='"+myTag3+"']");
			WebElement oSubMenu = driver.findElement(oTarget);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
		    executor.executeScript("arguments[0].click();", oSubMenu);		
			PageLoadWait.GenericWaitLoad();
		}
		mouseOverElement("id","helpButton");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
    }
    
    
    public static Point location(WebElement ele) {
        Locatable elementLocation = (Locatable) ele;
        Point locationPoint = elementLocation.getCoordinates().inViewPort();
        System.out.println(locationPoint);
        return locationPoint;
        }
    
    public static boolean CheckMyView(String sViewFullName,boolean bExpectASearchResult,boolean bExpectControl){
    	String sTourPrefixString="Live Tour:";
    	String sModifiedViewFullName;
    	boolean bTour=false;
    	boolean bAsset=false;
    	boolean bControl=false;
    	String sCurrUser;
    	
    	PageLoadWait.GenericWaitLoad();
	    System.out.println("*** Refresh screen***");
		try {
			Robot robot2 = new Robot();			
			robot2.keyPress(KeyEvent.VK_F5);
			robot2.delay(100);
			robot2.keyRelease(KeyEvent.VK_F5);
			robot2.delay(5000);
		} catch (AWTException ignore) {}
    	
    	
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	if (ElementExists("ClientButton")){
        	MyPrint.myPrint("***","Navigate to client UI screen");
    		waitElementClickableReady("ClientButton");
    		clickElementAndWaitReady("ClientButton");
    		PageLoadWait.GenericWaitLoad();
    		PageLoadWait.GenericWaitLoad();
    		PageLoadWait.GenericWaitLoad();
    		waitElementClickableReady("QALab");
    		PageLoadWait.GenericWaitLoad();
    	}
    	sCurrUser=getElement("logout").getAttribute("title").replace("'","").replace("Logout User ","");
    	clickElementWithNoWait(getElement("controlsCanvas_tabTwoTitle"));
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	SetElementValue(getElementLocator("searchTerms"),sViewFullName);
    	clickElementWithNoWait(getElement("searchDirectoryBtn"));
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	mouseOverElement(By.id("vidshieldLogo"));
    	By oEmpty=By.xpath("//ul[@class='empty'][contains(.,'lease refine your search')]");
    	By oLocator=By.xpath("//span[@class='highlight']");  	
    	
    	if (bExpectASearchResult){
            for (int j=0; j < 10; j++) {
                if (ElementExists(oEmpty)) {
                	j++;
					try {
	        			Robot robot3 = new Robot();
	        			robot3.keyPress(KeyEvent.VK_F5);
	        			robot3.delay(100);
	        			robot3.keyRelease(KeyEvent.VK_F5);
	        			robot3.delay(20000);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					PageLoadWait.GenericWaitLoad();
              		clickElementWithNoWait(getElement("controlsCanvas_tabTwoTitle"));
              		PageLoadWait.GenericWaitLoad();
              		PageLoadWait.GenericWaitLoad();
              		SetElementValue(getElementLocator("searchTerms"),sViewFullName);
              		clickElementWithNoWait(getElement("searchDirectoryBtn"));
              		PageLoadWait.GenericWaitLoad();
              		PageLoadWait.GenericWaitLoad();
              		PageLoadWait.GenericWaitLoad();
              		PageLoadWait.GenericWaitLoad();
              		PageLoadWait.GenericWaitLoad();
              		PageLoadWait.GenericWaitLoad();
              		mouseOverElement(By.id("vidshieldLogo"));
                } else {
                	j=99;
                }
            }
    		AssertJUnit.assertTrue("Verify search " + sViewFullName + " return results.", isElementPresent(oLocator));
    		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' get result on searching viewer '" + sViewFullName + "'.");
    	} else {
    		if(!ElementExists(oLocator)){
    			AssertJUnit.assertFalse("Verify search " + sViewFullName + " return no results.", false);
        		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' get no result on searching viewer '" + sViewFullName + "' as expected.");    		
        		return true;    			
    		}else{
        		List<WebElement> allElements = driver.findElements(oLocator); 
        		for (WebElement myElement: allElements) {
          			WebElement oParent = myElement.findElement(By.xpath(".."));
          			if (oParent.getText().contentEquals(sViewFullName)){
          				AssertJUnit.assertTrue("Verify search " + sViewFullName + " return no results.", false);
          			}
          			
        		}        		
        		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' get no result on searching viewer '" + sViewFullName + "' as expected.");    		
        		return true;
    		}
    	}

    	WebElement AutoTestPTZ=getElement(oLocator);
    	boolean bRobotDragDrop=true;
    	String ElementType = AutoTestPTZ.findElement(By.xpath("../../../..")).getAttribute("elemtype").toLowerCase();

    	switch (ElementType)	{
    	case "mapview":
    		bExpectControl=false;
    		break;
    	case "tour":
    		bExpectControl=false;
    		bTour=true;
    		bRobotDragDrop=true;
    		break;
    	case "camera":
//    		bCam=true;
    		break;
    	case "asset":
    		bRobotDragDrop=true;
    		bAsset=true;
    		break;    		
    	default:
    	}
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	WebElement AddImg=getElement("ViewLayoutPosition1");    	
    	Point VImage=location(AddImg);
    	mouseOverElement(AutoTestPTZ);
    	PageLoadWait.GenericWaitLoad();
    	Dimension toPosition=AddImg.getSize();
    	if(bRobotDragDrop){
    		try {
    			Robot robot4;
    			robot4 = new Robot();
    			robot4.delay(2000);
    			robot4.mousePress(InputEvent.BUTTON1_MASK);
    			robot4.delay(2000);
    			robot4.mouseMove(VImage.x + toPosition.width/2,VImage.y + toPosition.height/2);
    			robot4.delay(5000);
    			robot4.mouseRelease(InputEvent.BUTTON1_MASK);
    			robot4.delay(4000);
    			PageLoadWait.GenericWaitLoad();
    		} catch(AWTException e){
    			
    		}
    	} else {
        	builder.dragAndDrop(AutoTestPTZ, AddImg).perform();    		
    	}
		
		try {
			Robot robot2;
			robot2 = new Robot();			
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.delay(1000);
			robot2.mousePress(InputEvent.BUTTON1_MASK);
			robot2.delay(100);
			robot2.mouseRelease(InputEvent.BUTTON1_MASK);
			robot2.delay(100);
			PageLoadWait.GenericWaitLoad();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	By oviewerTitleBarLocator;
    	if (bTour){
    		sModifiedViewFullName=sTourPrefixString;
    		oviewerTitleBarLocator=By.partialLinkText("Live Tour:");
    	} else {
    		sModifiedViewFullName=sViewFullName;
    		oviewerTitleBarLocator=By.xpath("//a[contains(@id,'viewerObject__')]");
    	}
    	
    	waitElementClickableReady(oviewerTitleBarLocator);
    	AssertJUnit.assertTrue("Verify view title '" + sModifiedViewFullName + "' exists.", ElementExists(oviewerTitleBarLocator));		
    	WebElement viewerTitleBar=getElement(oviewerTitleBarLocator);
    	AssertJUnit.assertTrue("Verify view title bar of '" + sModifiedViewFullName + "' visible", viewerTitleBar.getCssValue("visibility").equalsIgnoreCase("visible"));
    	MyPrint.myPrint("***","Verified the viewer title bar of '" + sModifiedViewFullName + "' is visible.");
    	waitElementClickableReady("RemoveViewer");
    	if(!bAsset){
        	bControl=getElement("ViewerControl").getAttribute("title").contains(sCurrUser);
    	}
    	if(bControl){
    		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' owns the control on the viewer '" + sModifiedViewFullName + "'.");
    	} else {
        	switch (ElementType){
        		case "mapview":
        			MyPrint.myPrint("***","The current login user '" + sCurrUser + "' opened MapView '" + sModifiedViewFullName + "'.");
        			break;
        		case "tour":
        			MyPrint.myPrint("***","The current login user '" + sCurrUser + "' have the privilege of view tour '" + sModifiedViewFullName + "'.");
        			break;
		        case "camera":
		        	MyPrint.myPrint("***","The current login user '" + sCurrUser + "' doesn't own the control on the viewer '" + sModifiedViewFullName + "'.");
		        	break;
		        case "asset":
		        	MyPrint.myPrint("***","Check Asset '" + sViewFullName + "' popup properties window.");
		        	waitElementVisibility("assetFrame");
		        	driver.switchTo().frame(getElement("assetFrame"));
		        	mouseOverElementClick(driver.findElement(By.id("assetPropertiesButton")));
		        	driver.switchTo().defaultContent();
		        	PageLoadWait.GenericWaitLoad();
		        	if (ElementExists(By.xpath("//input[@class='readOnlyTextBox'][@value='" + sViewFullName + "']"))){
		        		MyPrint.myPrint("Check asset '" + sViewFullName + "' popup properties is accessible","Opened/Visible -- test passed.");
		        	} else {
		        		MyPrint.myPrint("Check property of Asset '" + sViewFullName + "' is accessible.","Test failed.");
		        	}
		        	mouseOverElementClick(driver.findElement(By.linkText("close")));
		        	PageLoadWait.GenericWaitLoad();
		        	break;		        	
        		default:
        	} 
    	}
    	if(!bAsset){
    		AssertJUnit.assertTrue("Verify view title '" + sModifiedViewFullName + "' visibility as expectation:", logicalNotXOR(bControl,bExpectControl));
        	MyPrint.myPrint("Verify and passed","The viewer control check marker of viewer '" + sModifiedViewFullName + "' matches the expactation.");    		
    	}
    	MyPrint.myPrint("***","Closing viewer '" + sModifiedViewFullName + "'.");
    	clickElementWithNoWait("RemoveViewer");
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	return true;
    }
    
    
    public static boolean LoadMyView(String sViewFullName,boolean bExpectASearchResult,boolean bExpectControl,int iViewer) {
    	String sCurrUser;
    	PageLoadWait.GenericWaitLoad();
    	if(iViewer<1){
    		Assert.fail("Invalid given viewer position '" + iViewer + "'");
    	}
    	if(iViewer>16){
    		Assert.fail("Invalid given viewer position '" + iViewer + "'");
    	}    	
    	By myViewer = By.xpath("//div[contains(@id,'_viewer" + (iViewer-1) + "')]");
    	WebElement AddImg;
    	if(ElementExists(myViewer)){
        	AddImg=getElement(myViewer);
    	} else {
    		Assert.fail("Invalid given viewer choice '" + iViewer + "'");
    	}
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	
		By oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		By oTabStructure=By.xpath("//div[contains(@id,'_tabOneTitle')]");
		boolean bClientButtn=false;
		
		
		if(driver.findElements(oTabStructure).size()>0){
			//do nothing
		} else if(driver.findElements(oClientButton).size()>0){
			bClientButtn=true;
		}
		
		
    	if (bClientButtn){
        	MyPrint.myPrint("***","Navigate to client UI screen");
    		waitElementClickableReady("ClientButton");
    		clickElementAndWaitReady("ClientButton");
    		PageLoadWait.GenericWaitLoad();
    		PageLoadWait.GenericWaitLoad();
    		PageLoadWait.GenericWaitLoad();
    		waitElementClickableReady("QALab");
    		PageLoadWait.GenericWaitLoad();
    	}
    	sCurrUser=getElement("logout").getAttribute("title").replace("'","").replace("Logout User ","");
    	clickElementWithNoWait(getElement("controlsCanvas_tabTwoTitle"));
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	SetElementValue(getElementLocator("searchTerms"),sViewFullName);
    	clickElementWithNoWait(getElement("searchDirectoryBtn"));
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	mouseOverElement(By.id("vidshieldLogo"));
    	By oLocator=By.xpath("//span[@class='highlight']");
    	By oSearch=By.xpath("//div[@id='searchTree'][contains(.,'Searching...')]");
    	By oSearchNoResult=By.xpath("//div[@id='searchTree'][contains(.,'No results found')]");
    	boolean bSearching=true,bNoResult=false;
    	if (bExpectASearchResult){
    		while(bSearching){
    			if (isElementPresent(oLocator)){
    				bSearching=false;
    			}
    			else if(isElementPresent(oSearch)){
    				robotWait(1);
    			} else if(isElementPresent(oSearchNoResult)){
    				bSearching=false;
    				bNoResult=true;
    			}
    		}
    		if(bNoResult){
        		AssertJUnit.fail("Verify search " + sViewFullName + " return no results.");
        		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' get NO result on searching viewer '" + sViewFullName + "'.");
        		
    		} else {    			
        		AssertJUnit.assertTrue("Verify search " + sViewFullName + " return results.", isElementPresent(oLocator));
        		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' get result on searching viewer '" + sViewFullName + "'.");    			
    		}

    	} else {
    		if(!isElementPresent(oLocator)){
    			AssertJUnit.assertFalse("Verify search " + sViewFullName + " return no results.", false);
        		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' get no result on searching viewer '" + sViewFullName + "' as expected.");    		
        		return true;    			
    		}else{
        		List<WebElement> allElements = driver.findElements(oLocator); 
        		for (WebElement myElement: allElements) {
          			WebElement oParent = myElement.findElement(By.xpath(".."));
          			if (oParent.getText().contentEquals(sViewFullName)){
          				AssertJUnit.assertTrue("Verify search " + sViewFullName + " return no results.", false);
          			}
        		}        		
        		MyPrint.myPrint("***","The current login user '" + sCurrUser + "' get no result on searching viewer '" + sViewFullName + "' as expected.");    		
        		return true;
    		}
    	}
    	robotWait(1);
    	WebElement AutoTestPTZ=getElement(oLocator);
       	if(isElementPresent(myViewer)){
        	AddImg=getElement("xpath","//div[contains(@id,'_viewer" + (iViewer-1) + "')]");
        	Robot robot2;
			try {
				robot2 = new Robot();
				robot2.delay(700);
				mouseOverElement(AutoTestPTZ);
				robot2.delay(400);
				robot2.mousePress(InputEvent.BUTTON1_MASK);
				robot2.delay(400);
				mouseOverElement(AddImg);
				robot2.delay(700);
				robot2.mouseRelease(InputEvent.BUTTON1_MASK);
				robot2.delay(1000);
			} catch (AWTException e) {
				// 	TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	MyPrint.myPrint("***","Verified the viewer '" + iViewer + "' is loaded/visible.");
    	return true;    	
    }
	public static boolean MonitorCameraInMapViewer(By oCamIcon,int iMyVewer, int iMyVewer2){
		WebElement oElem,AddImg;		
		By myViewer = By.xpath("//div[contains(@id,'_viewer" + (iMyVewer-1) + "')]");
		By myViewer2 = By.xpath("//div[contains(@id,'_viewer" + (iMyVewer2-1) + "')]");
		List<WebElement> oTargetList=driver.findElement(myViewer).findElements(oCamIcon);
		if(oTargetList.size()>0){
			for(WebElement oTarget:oTargetList){
				if(!oTarget.findElement(By.xpath("..")).getAttribute("style").contains("-")){
					oElem=oTarget;
					oElem.click();
					
					if(ElementExists(myViewer2)){
						AddImg=getElement(myViewer2);
						Robot robot2;
						try {
							robot2 = new Robot();
							robot2.delay(700);
							mouseOverElement(oElem);
							robot2.delay(400);
							robot2.mousePress(InputEvent.BUTTON1_MASK);
							robot2.delay(400);
							mouseOverElement(AddImg);
							robot2.delay(700);
							robot2.mouseRelease(InputEvent.BUTTON1_MASK);
							robot2.delay(1000);
						} catch (AWTException e) {
							// 	TODO Auto-generated catch block
							e.printStackTrace();
						}
						return true;
					} else {
						Assert.fail("Invalid given opration.");
					}
				}
			}
		}
		return false;
	}
    
    public static boolean DetachMyView(int iViewer,Stack<String> sWindowHandlers) {
    	String sDetachTitle="Detach Viewer";
    	By Detch = By.xpath(".//div[@title='" + sDetachTitle + "']");
    	if(iViewer<1){
    		Assert.fail("Invalid given viewer position '" + iViewer + "'");
    	}
    	if(iViewer>16){
    		Assert.fail("Invalid given viewer position '" + iViewer + "'");
    	}    	
    	By myViewer = By.xpath("//div[contains(@id,'_viewer" + (iViewer-1) + "')]");
    	WebElement AddImg,oDetach;
    	if(ElementExists(myViewer)){
        	AddImg=getElement(myViewer);
        	if(ElementExists(AddImg.findElement(Detch))){
        		oDetach=AddImg.findElement(Detch);
        		try {
					OpenPopupByClickObj(oDetach,sWindowHandlers);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
    	} else {
    		Assert.fail("Invalid given viewer choice '" + iViewer + "'");
    	}
    	return true;
    }
    
 
    public static boolean LoadSalvo(String sSalvoFullName,int iViewer) {
    	if(iViewer<1||iViewer>16){
    		Assert.fail("Invalid given viewer position '" + iViewer + "'");
    	}
    	By oSalvoElementLocator = By.xpath("//div[@id='salvodata']/div[contains(@title,'" + sSalvoFullName + "')]");
    	WebElement oTargetSalvo;
    	if(!ElementExists(oSalvoElementLocator)){
    		Assert.fail("Salvo with name '" + sSalvoFullName + "' is not listing.");
    	}
    	oTargetSalvo=getElement(oSalvoElementLocator);
    	
    	
    	By myViewer = By.xpath("//div[contains(@id,'_viewer1')]");
    	WebElement AddImg;
    	if(ElementExists(myViewer)){
        	AddImg=getElement(myViewer);
    	} else {
    		Assert.fail("Invalid given opration.");
    	}
    	AddImg=getElement(myViewer);
		builder.dragAndDrop(oTargetSalvo, AddImg).perform();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		By myViewerRemover;
		WebElement oViewer;
    	for (int iLoop=1; iLoop<iViewer;iLoop++){
    		myViewerRemover = By.xpath("//div[contains(@id,'_viewer" + iLoop + "')]");
    		oViewer=driver.findElement(myViewerRemover);
    		waitElementCssValue(oViewer,"visibility","visible");
    		System.out.println("Viewer " + iLoop +" loaded/rendored normally.");
    	}
    	MyPrint.myPrint("***","Verified the viewer '" + iViewer + "' is loaded/visible.");
    	return true;
    }

    @SuppressWarnings("unchecked")
	public static String getNewSituation(String sExistSitID, int TimeoutSeconds){
		@SuppressWarnings("rawtypes")
		Wait fwait = new FluentWait(driver)
		.withTimeout(TimeoutSeconds, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);		
		fwait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				PageLoadWait.GenericWaitLoad();
				WebElement oWebElement = getElement("FirstRowSituationID");
				String sCurrentSidID = oWebElement.getText();
				boolean compareResult;
				if (sCurrentSidID.equals(sExistSitID))
					compareResult=false;
				else
					compareResult=true;
				return compareResult;
			}
		});
    	return getElement("FirstRowSituationID").getText();
    }
    
    public static void DropDownSelect(WebElement oDropDown,String sOptionText){
		Select SelectDropDown = new Select(oDropDown);
		SelectDropDown.selectByVisibleText(sOptionText);
		ExcuteKeyEvent(10);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    
    public static void DropDownSelect(By oDropDown,String sOptionText){
		Select SelectDropDown = new Select(getElement(oDropDown));
		SelectDropDown.selectByVisibleText(sOptionText);
		ExcuteKeyEvent(10);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    
    public static boolean DropDownSelected(String oDropDown,String sOptionText){
    	if(ElementExists(oDropDown)){
    		Select SelectDropDown = new Select(getElement(oDropDown));
    		if(SelectDropDown.getFirstSelectedOption().getText().toLowerCase().equals(sOptionText.toLowerCase())){
    			return true;
    		} else {
    			return false;
    		}    		
    	}else{
    		return false;
    	}
    }

    public static void DropDownSelectedOption(String oDropDown,String sOptionText){
    	if(DropDownSelected(oDropDown,sOptionText)){
    		Assert.assertTrue(true, "Drop down list selected value " + sOptionText +"." );
    	} else {
    		Assert.fail("Drop down list doesn't have the expected value " + sOptionText +".");
    	}
    }
    
    
    public static void SelectContextMenu(By oPopUp,String sOptionText){
		WebElement oContextPopUp= driver.findElement(oPopUp);
		clickElementIfExists(oContextPopUp,By.xpath(".//li[contains(text(),'" + sOptionText + "')]"));
		PageLoadWait.GenericWaitLoad();
    }
    
    public static void ElementRightClick(WebElement oElement) {
        try {
        	mouseOverElement(oElement);        	
        	PageLoadWait.GenericWaitLoad();
            Actions oAction = new Actions(driver);
            oAction.moveToElement(oElement);
            oAction.contextClick(oElement).build().perform();
            PageLoadWait.GenericWaitLoad();
            PageLoadWait.GenericWaitLoad();
        } catch (StaleElementReferenceException e) {
            System.out.println("WebElement is not attached to the page document "
                    + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("WebElement " + oElement + " was not found in DOM "
                    + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("WebElement " + oElement + " was not clickable "
                    + e.getStackTrace());
        }
    }
    
    public static void ElementRightClick(By oEleBy) {
    	WebElement oElement;
    	if(ElementExists(oEleBy)){
    		oElement=getElement(oEleBy);
    		try {
    			mouseOverElement(oElement); 	
    			PageLoadWait.GenericWaitLoad();
    			Actions oAction = new Actions(driver);
    			oAction.moveToElement(oElement);
    			oAction.contextClick(oElement).build().perform();
    			PageLoadWait.GenericWaitLoad();            
    		} catch (StaleElementReferenceException e) {
    			System.out.println("WebElement is not attached to the page document "
    					+ e.getStackTrace());
    		} catch (NoSuchElementException e) {
    			System.out.println("WebElement " + oElement + " was not found in DOM "
    					+ e.getStackTrace());
    		} catch (Exception e) {
    			System.out.println("WebElement " + oElement + " was not clickable "
    					+ e.getStackTrace());
    		}
    	}
    }
    
    public static void ElementRobotRightClick(By oEleBy) {
    	WebElement oElement;
    	if(ElementExists(oEleBy)){
    		oElement=getElement(oEleBy);
    		try {
    			Robot bot = new Robot();
    			Actions oAction = new Actions(driver);
    			oAction.moveToElement(oElement);
    			bot.delay(200);
    			oAction.contextClick(oElement).build().perform();
    			bot.delay(200);          
    		} catch (StaleElementReferenceException e) {
    			System.out.println("WebElement is not attached to the page document "
    					+ e.getStackTrace());
    		} catch (NoSuchElementException e) {
    			System.out.println("WebElement " + oElement + " was not found in DOM "
    					+ e.getStackTrace());
    		} catch (Exception e) {
    			System.out.println("WebElement " + oElement + " was not clickable "
    					+ e.getStackTrace());
    		}
    	}
    }
    
    public static void ExcuteKeyEvent(int iKeyEvent){
    	Robot robot;
		try {
			robot = new Robot();
	    	robot.keyPress(iKeyEvent);
	    	robot.keyRelease(iKeyEvent);			
		} catch (AWTException e) {
			e.printStackTrace();
		}
    }
	public static MapEditor CreateFloorPlan(LocPoint fpStart,LocPoint fpEnd,String sPlan,String sBaseView,String sLevel,String sZoom,String sOwner){
//		Class cls=null;
		String sTemp = Parent();
		if(StringUtils.isEmpty(sPlan)){
			sPlan="AutoFP_"+ sTemp;
		}
		MapEditor obj;
		obj= new MapEditor(sPlan,fpStart,fpEnd);
		obj.SetMapView(sBaseView);
		obj.SetLevel(sLevel);
		obj.SetZoom(sZoom);
		obj.SetOwner(sOwner);
		obj.CreateFloorPlan();
		return obj;
	}
    
	public static ModuleLocalUser tclocalUser(String sNewAdminUserGroupNameA,String sLocalTester){
		PageLoadWait.GenericWaitLoad();
		LinkedHashMap<String , String> myTestLocalUser = new LinkedHashMap<String, String>();
		LocalUserDataMap myTest2LocalUser = new LocalUserDataMap(sNewAdminUserGroupNameA,sLocalTester);
		if(sNewAdminUserGroupNameA!=null){
			if(sNewAdminUserGroupNameA.length()>0){
				myTest2LocalUser.SetUserWithData("Owner",sNewAdminUserGroupNameA);		
			}
		}
		myTestLocalUser=myTest2LocalUser.mTestLocalUser;	
		ModuleLocalUser MyLocalUsers = new ModuleLocalUser(myTestLocalUser,sNewAdminUserGroupNameA);
		MyLocalUsers.AddNewLocalUser(myTestLocalUser);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		return MyLocalUsers;
	}
	
	public static ModuleLocalUser tclocalUser(String sNewAdminUserGroupNameA,String sLocalTester,String sLocalGroupNameA,String sOwner){
		PageLoadWait.GenericWaitLoad();
		LinkedHashMap<String , String> myTestLocalUser = new LinkedHashMap<String, String>();
		LocalUserDataMap myTest2LocalUser = new LocalUserDataMap(sNewAdminUserGroupNameA,sLocalTester);
		myTest2LocalUser.AddUserGroup(sLocalGroupNameA);
		myTest2LocalUser.AddUserGroup(sNewAdminUserGroupNameA);
		if(sOwner!=null){
			if(sOwner.length()>0){
				myTest2LocalUser.SetUserWithData("Owner",sOwner);
			}
		}
		myTestLocalUser=myTest2LocalUser.mTestLocalUser;	
		ModuleLocalUser MyLocalUsers = new ModuleLocalUser(myTestLocalUser,sLocalGroupNameA,sNewAdminUserGroupNameA);
		MyLocalUsers.AddNewLocalUser(myTestLocalUser);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		return MyLocalUsers;
	}
	
	
	public static ModuleLocalUser tclocalUserDelete(String sNewAdminUserGroupNameA,String sLocalTester,String sLocalGroupNameA,String sOwner){
		PageLoadWait.GenericWaitLoad();
		LinkedHashMap<String , String> myTestLocalUser = new LinkedHashMap<String, String>();
		LocalUserDataMap myTest2LocalUser = new LocalUserDataMap(sNewAdminUserGroupNameA,sLocalTester);
		myTest2LocalUser.AddUserGroup(sLocalGroupNameA);
		myTest2LocalUser.AddUserGroup(sNewAdminUserGroupNameA);
		if(sOwner!=null){
			if(sOwner.length()>0){
				myTest2LocalUser.SetUserWithData("Owner",sOwner);		
			}
		}
		myTestLocalUser=myTest2LocalUser.mTestLocalUser;	
		ModuleLocalUser MyLocalUsers = new ModuleLocalUser(myTestLocalUser,sLocalGroupNameA,sNewAdminUserGroupNameA);
		MyLocalUsers.DeleteLocalUser();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		return MyLocalUsers;
	}
	
	public static boolean exists(File file) {
		try {
			return Files.exists(Paths.get(file.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS);
		}
		catch (InvalidPathException e) {
			return false;
		}
	}
	

	public static void copyFile(File source, File dest) throws IOException {
	    Files.copy(source.toPath(), dest.toPath());
	}
	
	public static boolean fExists(Path path){
		if (!Files.exists(path)){
			try {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
			} catch (IOException e) {				
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public static void exists(Path path){
		if (!Files.exists(path)){
			try {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	public static boolean isDirectory(File file) { 
		  try { 
		   return Files.isDirectory(Paths.get(file.getAbsolutePath()), LinkOption.NOFOLLOW_LINKS); 
		  } 
		  catch (InvalidPathException e) { 
		   return false; 
		  } 
	}

	public static void adminDeleteSalvo(String salvoName, String sOwner){
		ModuleCreateSalvo Salvo402 = new ModuleCreateSalvo();
		Salvo402.NavSalvoAdmin();		
		Salvo402.adminSetSalvoNameOwner(salvoName,sOwner);
		Salvo402.adminSalvoDelete();
		PageLoadWait.GenericWaitLoad();
	}
	
	public static boolean GoToAddress(String sAddress,boolean bExpectGoToMapButtonDisplay){
		boolean bReturn=false;
		By oGoToPopUp=By.id("geolocationWindow_canvas");
//		By oGoToPopUpOKButton=By.id("geolocationWindow_submit");// not stable
		By oGoToPopUpOKButton=By.xpath(".//table//table//tr/td/div[@title='Go'][@id='geolocationWindow_submit'][@class='buttonFactory']");// new try
		By oGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
		By oGoToPopUpCancelButton=By.xpath(".//table//table//tr/td/div[@id='geolocationWindow_cancel'][@class='buttonFactory']");// new try
		try{
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			if(ElementExists(oGoToButton)){
				driver.findElement(oGoToButton).click();
				/////Wait long time for loading the default map view
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				waitElementClickableReady(By.id("geolocationWindow_searchFor"));
				driver.findElement(By.id("geolocationWindow_searchFor")).click();
				ElementSendKeys(By.id("geolocationWindow_searchFor"),sAddress);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				waitElementClickableReady(By.id("geolocationWindow_searchButton"));
				clickElementWithNoWait(By.id("geolocationWindow_searchButton"));		
				try{
					waitElementEnabled(driver.findElement(oGoToPopUp).findElement(oGoToPopUpOKButton));
					List <WebElement> NoSuchResult = driver.findElements(By.xpath("//div[@id='geolocationWindow_searchResults'][text()='No results found.']"));
					if(NoSuchResult.size()>0){
						AssertJUnit.fail("No result found on searching address '" +  sAddress + "'.");	
					}
				} catch(TimeoutException eTimeout){
					fail("Failed to locate address '" + sAddress + "': Address not existing.");
				}
				if(driver.findElement(By.id("geolocationWindow_searchResults")).findElements(By.xpath(".//input")).size()>0){
					waitElementEnabled(driver.findElement(oGoToPopUpCancelButton));
					waitElementEnabled(driver.findElement(oGoToButton));
					waitElementEnabled(driver.findElement(oGoToPopUpCancelButton));
					waitElementEnabled(driver.findElement(oGoToButton));
					driver.findElement(oGoToPopUp).findElement(oGoToPopUpOKButton).click();
					bReturn=true;
					MyPrint.myPrint("Open address '" + sAddress + "'", " Address found and centered in map.");
					return true;
				} else {
					MyPrint.myPrint("Goto address '" + sAddress + "'", " Address not found");
				}
			} else {
				if(bExpectGoToMapButtonDisplay){
					fail("Failed to locate the 'GoTo Address' buton before open address '" + sAddress + "'.");
				} else {
					bReturn=true;
					MyPrint.myPrint("Goto address '" + sAddress + "'", " Centering map feature is not turned on. Will not test GoTo Address.");
				}
			}	
		} catch (TimeoutException e){
			return false;
		} catch (Exception e){
			fail("Failed in centering map to address '" + sAddress + "'.");
		}
		return bReturn;
	}
	
	public static void clickDisplyLabel(By oLocator){	
		int iMyLabelSize;
//		int iLastIndexItemWidthNotZearo=0;
		boolean bFound=false;//, bdisplay=false;
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();		
		iMyLabelSize=driver.findElements(oLocator).size();
		for (int imyloop=0;imyloop<iMyLabelSize;imyloop++){
				if(driver.findElements(oLocator).get(imyloop).getLocation().getX()>0){
					try{
						ElementJSClick(driver.findElements(oLocator).get(imyloop));	
					} catch (IndexOutOfBoundsException e){
						ElementJSClick(driver.findElements(oLocator).get(imyloop-1));
					}
					bFound=true;
//					bdisplay=true;
//					iLastIndexItemWidthNotZearo=imyloop;
					break;
			}
		}
		if(!bFound){
			fail("Action failed: Failed to find/click display element as described as '" + oLocator.toString() + "'");
		} else {
			System.out.println("Action: clicked display element as described as '" + oLocator.toString() + "'");
		}
	}
	
	public static void clickDisplyObj(By oLocator){	
		int iMyLabelSize;
//		int iLastIndexItemWidthNotZearo=0;
		boolean bFound=false;//, bdisplay=false;
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();		
		iMyLabelSize=driver.findElements(oLocator).size();
		for (int imyloop=0;imyloop<iMyLabelSize;imyloop++){
				if(driver.findElements(oLocator).get(imyloop).getLocation().getX()>0){
					try{
						ElementJSClick(driver.findElements(oLocator).get(imyloop));	
					} catch (IndexOutOfBoundsException e){
						ElementJSClick(driver.findElements(oLocator).get(imyloop-1));
					}
					bFound=true;
//					bdisplay=true;
//					iLastIndexItemWidthNotZearo=imyloop;
					break;
			}
		}
		if(!bFound){
			System.out.println("Failed to find/click display element as described as '" + oLocator.toString() + "'");
		} else {
			System.out.println("Action: clicked display element as described as '" + oLocator.toString() + "'");
		}
	}
	
	public static void MouseOverclickDisplyLabel(By oLocator){	
		int iMyLabelSize;
		boolean bFound=false;
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();		
		iMyLabelSize=driver.findElements(oLocator).size();
		for (int imyloop=0;imyloop<iMyLabelSize;imyloop++){
				if(driver.findElements(oLocator).get(imyloop).getLocation().getX()>0){
					mouseOverElement(driver.findElements(oLocator).get(imyloop));
					ElementJSClick(driver.findElements(oLocator).get(imyloop));
					bFound=true;
					break;
			}
		}
		if(!bFound){
			fail("Action failed: Failed to find/click display element as described as '" + oLocator.toString() + "'");
		} else {
			System.out.println("Action: clicked display element as described as '" + oLocator.toString() + "'");
		}
	}
	
	
	
	public static int getDisplyElmentIndex(By oLocator){
		int iMyLabelSize;		
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		iMyLabelSize=driver.findElements(oLocator).size();
		for (int imyloop=0;imyloop<iMyLabelSize;imyloop++){
				if(driver.findElements(oLocator).get(imyloop).getLocation().getX()>0){
					return imyloop;
			}
		}
		fail("Action failed: Failed to find display element index as described as '" + oLocator.toString() + "'");
		return 999;
	}
	
	public void threadWait(Integer iMS){
		try {
			Thread.sleep(iMS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openRiskShield(Stack<String> sWindowHandlers){
		try {
			OpenPopupByClickObj(getElement("oLaunchShild2"),sWindowHandlers);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		waitSituationLoad("RiskShildLoadMessage");
	}
	
	public static void openRiskShieldListView(Stack<String> sWindowHandlers){
		openRiskShield(sWindowHandlers);
		if (!(getElement("canvas_statusBar_splitViewCheckbox")).isSelected()){
			clickElementWithNoWait(getElement("canvas_statusBar_splitViewCheckbox"));
			waitSituationLoad("loadingErrorMsg");
			waitElementVisibility(getElementLocator("canvas_listLayer_headerBody"));			
		}
		longWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("canvas_listLayer_headerHeader")));
		waitElementClickableReady("canvas_tabTwoTitle");
		clickElementWithNoWait(getElement("canvas_tabTwoTitle"));
		robotWait(1);
	}
	
	
	public static void RiskShieldListView(Stack<String> sWindowHandlers){
		openRiskShield(sWindowHandlers);
		waitElementClickableReady("canvas_tabTwoTitle");
		clickElementWithNoWait(getElement("canvas_tabTwoTitle"));
		robotWait(1);
	}
	
	
	public static void openRiskShieldSplitView(Stack<String> sWindowHandlers){
		openRiskShield(sWindowHandlers);
		if (!(getElement("canvas_statusBar_splitViewCheckbox")).isSelected()){
			clickElementWithNoWait(getElement("canvas_statusBar_splitViewCheckbox"));
			robotWait(1);
			waitSituationLoad("loadingErrorMsg");
			robotWait(1);
			waitElementVisibility(getElementLocator("canvas_listLayer_headerBody"));
			robotWait(1);
		}
		longWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("canvas_listLayer_headerHeader")));
		robotWait(1);
	}
	
	public static void openDeviceInDetailWindow(String CategoryFolder, String sDeviceName, Integer iViewer){
		Integer iViewerIndex=0;
		if(iViewer>0){
			iViewerIndex=iViewer-1;
		} else if(iViewer==0){
			iViewerIndex=0;
		} else if(iViewer<0){
			fail("Action failed: Viewer number is required.");
		}
		waitElementClickableReady("id","directoryCanvas_hidden_top");
		clickElementWithNoWait("id","directoryCanvas_hidden_top");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("directoryCanvas_tabs_tab2")));
		waitElementClickableReady("id","directoryCanvas_tabs_tab2");
		clickElementWithNoWait("id","directoryCanvas_tabs_tab2");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("swapOpacity")));
		waitElementClickableReady("id","swapOpacity");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='" + CategoryFolder + "']")));
		waitElementClickableReady("xpath","//a[@title='" + CategoryFolder + "']");   	
		clickElementWithNoWait("xpath","//a[@title='" + CategoryFolder + "']"); 
		By oLocator=By.xpath("//a[@title='" + sDeviceName + "']");
		wait.until(ExpectedConditions.presenceOfElementLocated(oLocator));
		WebElement AutoTestPTZ=getElement(oLocator);
		By oLocatorDevice=By.xpath("//div[contains(@id,'_viewer" + iViewerIndex + "')]");
		WebElement AddImg=getElement(oLocatorDevice);
		Robot robot2;
		try {
			robot2 = new Robot();
			robot2.delay(1000);
			mouseOverElement(AutoTestPTZ);
			robot2.delay(200);
			robot2.mousePress(InputEvent.BUTTON1_MASK);
			robot2.delay(200);
			mouseOverElement(AddImg);
			robot2.delay(500);
			robot2.mouseRelease(InputEvent.BUTTON1_MASK);
			robot2.delay(1000);
		} catch (AWTException e) {
			// 	TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isElementPresent(By by) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);  
		try {
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e) {
			return false;
		}
		finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		}
	}

	public static void openDeviceInDetailWindow(String [] CategoryFolder, Integer iViewer, boolean bExpect){
		Integer iViewerIndex=0;
		if(iViewer>0){
			iViewerIndex=iViewer-1;
		} else if(iViewer==0){
			iViewerIndex=0;
		} else if(iViewer<0){
			fail("Action failed: Viewer number is required.");
		}		
		try{
			waitElementClickableReady("id","directoryCanvas_hidden_top");
			clickElementWithNoWait("id","directoryCanvas_hidden_top");
			
		} catch (TimeoutException e){
			By oTabIcon=By.xpath("//div[@id='directoryCanvas_hidden_top']");
			mouseOverElementRobotClick(oTabIcon);
		}
		TestActions.robotWait(2);		
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("directoryCanvas_tabs_tab2")));
			waitElementClickableReady("id","directoryCanvas_tabs_tab2");
			clickElementWithNoWait("id","directoryCanvas_tabs_tab2");
			TestActions.robotWait(1);
		} catch (TimeoutException e){
			By oTab2=By.xpath("//div[@id='directoryCanvas']/div[@id='directoryCanvas_tabs']/div[@id='directoryCanvas_tabs_tab2']");
			mouseOverElementRobotClick(oTab2);
		}
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("swapOpacity")));
			waitElementClickableReady("id","swapOpacity");
		} catch (TimeoutException e) {
			By oTab2=By.xpath("//div[@id='swapOpacity']");
			mouseOverElementRobotClick(oTab2);
		}
		WebElement oMyClip;
		oMyClip=ExpandDir(CategoryFolder);
		oMyClip.click();
		By oLocatorDevice=By.xpath("//div[contains(@id,'_viewer" + iViewerIndex + "')]");
		WebElement AddImg=getElement(oLocatorDevice);
		Robot robot2;
		try {
			robot2 = new Robot();
			robot2.delay(700);
			mouseOverElement(oMyClip);
			robot2.delay(300);
			robot2.mousePress(InputEvent.BUTTON1_MASK);
			robot2.delay(300);
			mouseOverElement(AddImg);
			robot2.delay(600);
			robot2.mouseRelease(InputEvent.BUTTON1_MASK);
			robot2.delay(1000);
		} catch (AWTException e) {
			// 	TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(bExpect){
			String TestViewerIDKey="_viewer" + (iViewer-1);
			By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
			By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
			driver.switchTo().defaultContent();
			WebElement TestViewer=driver.findElement(WorkViewer);
			waitElementVisible(TestViewer.findElement(TestViewerMenu));	
		} else {
			By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			String sMessage=driver.findElement(oNoMadMessage).getText();
			String sExpect="Connection failed: Camera '" + CategoryFolder[CategoryFolder.length-1] + "' is blocked.";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed");
		}
	}
	
	
	public static void openSourceDeviceInDetailWindow(String sSourceDeviceName, Integer iViewer, boolean bExpect){
		Integer iViewerIndex=0;
		if(iViewer>0){
			iViewerIndex=iViewer-1;
		} else if(iViewer==0){
			iViewerIndex=0;
		} else if(iViewer<0){
			fail("Action failed: Viewer number is required.");
		}
		waitElementClickableReady("id","directoryCanvas_hidden_top");
		clickElementWithNoWait("id","directoryCanvas_hidden_top");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("directoryCanvas_tabs_tab1")));
		waitElementClickableReady("id","directoryCanvas_tabs_tab1");
		By oListingDetail=By.xpath("//div[@id='detailsTree']");
		By oMyDevicePath=By.xpath("./ul//div/div[contains(.,'" + sSourceDeviceName + " [')]");
		WebElement oMyDevice,oListingTree;
		oListingTree=driver.findElement(oListingDetail);
		oMyDevice=oListingTree.findElement(oMyDevicePath);
		oMyDevice.click();
		By oLocatorDevice=By.xpath("//div[contains(@id,'_viewer" + iViewerIndex + "')]");
		WebElement AddImg=getElement(oLocatorDevice);
		Robot robot2;
		try {
			robot2 = new Robot();
			robot2.delay(700);
			mouseOverElement(oMyDevice);
			robot2.delay(300);
			robot2.mousePress(InputEvent.BUTTON1_MASK);
			robot2.delay(300);
			mouseOverElement(AddImg);
			robot2.delay(600);
			robot2.mouseRelease(InputEvent.BUTTON1_MASK);
			robot2.delay(1000);
		} catch (AWTException e) {
			// 	TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bExpect){
			String TestViewerIDKey="_viewer" + (iViewer-1);
			By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
			By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
			driver.switchTo().defaultContent();
			WebElement TestViewer=driver.findElement(WorkViewer);
			waitElementVisible(TestViewer.findElement(TestViewerMenu));	
		} else {
			By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			String sMessage=driver.findElement(oNoMadMessage).getText();
			String sExpect="Connection failed: Camera '" + sSourceDeviceName + "' is blocked.";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed");
		}
	}
	
	public static void blockOperatorInDetailWindow(String sSourceDeviceName, String sGroup, boolean bBlock){	
		boolean bMessage=false;
		By oListingDetail=By.xpath("//div[@id='detailsTree']");
		By oMyDevicePath=By.xpath("./ul//div/div[contains(.,'" + sSourceDeviceName + " [')]");
		By oBlockPopup=By.xpath("//div[@id='SituationRemoveOptionMenu'][contains(@style,'display: block')]");
		By oBlockLabel=By.xpath(".//td[@id='Block/Unblock Video']");
		By BlockDialog=By.xpath("//div[@class='dialogPopup blockVideoDialog'][contains(@style,'display: block')]");
		By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@title='" + sGroup + "']");
		waitElementClickableReady("id","directoryCanvas_hidden_top");
		clickElementWithNoWait("id","directoryCanvas_hidden_top");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("directoryCanvas_tabs_tab1")));
		PageLoadWait.GenericWaitLoad();
		waitElementClickableReady("id","directoryCanvas_tabs_tab1");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		WebElement oMyDevice,oListingTree,oElementBlockLabel;
		oListingTree=driver.findElement(oListingDetail);
		oMyDevice=oListingTree.findElement(oMyDevicePath);
		oMyDevice.click();
		PageLoadWait.GenericWaitLoad();
		ElementRightClick(oMyDevice);
		PageLoadWait.GenericWaitLoad();
		waitElementVisibility(oBlockPopup);
		oElementBlockLabel=driver.findElement(oBlockPopup).findElement(oBlockLabel);
		waitElementVisibility(oElementBlockLabel);
		oElementBlockLabel.click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		if(logicalXOR(GRPElement.isSelected(),bBlock)){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			ElementJSClick(GRPElement);
			bMessage=true;
		}
		waitElementClickableReady(driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")),1000);
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
		robotWaitMS(100);
		PageLoadWait.GenericWaitLoad();
		if (bMessage){
			String sNoMadMessage;
			if(bBlock){
				sNoMadMessage="blocked. Reloaded Directory and disconnected camera if you do not have permission to view.";
			} else {
				sNoMadMessage="unblocked. Reloaded Directory.";
			}
			String sExpect="Camera " + sSourceDeviceName + " is " + sNoMadMessage;
			Assert.assertTrue(verifySDNoMadMessage(sExpect),
					"***notification verification failed");
			
		}		
	}
	
	
	public static boolean waitElementClickableReady(WebElement oWebElement, int timeOut){
		boolean bReturn=false, bPresent=false;
		try {
			waitForElementToBePresent(oWebElement,timeOut);
			bPresent=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bPresent && ElementVisible(oWebElement) && oWebElement.isEnabled()){
			return true;
		}
		return bReturn;
	}
	
	public static String createSituation(){
		String sExistsSituationID="";
		By oNoSituations=By.xpath("//p[contains(text(),'No situations match the filter criteria')]");
		if(driver.findElements(oNoSituations).size()!=0){
			sExistsSituationID="0";			
		} else {
			sExistsSituationID=getElement("FirstRowSituationID").getText();	
		}
		robotWait(1);
		clickElementWithNoWait(getElement("ShieldNewSituationButton"));
		SwitchToFrame("createDialog");
		waitElementClickableReady("newSituationType");
		//        DropDownSelectedOption("newSituationType", sSituationTypeString);
		clickElementWithNoWait(getElement("newSituationCreate"));
		driver.switchTo().defaultContent();
		robotWait(2);
		waitElementVisible(getElement("FirstRowSituationType"));
		String sNewSituationID=getElement("FirstRowSituationID").getText();
		if(sNewSituationID.equals(sExistsSituationID)){
			fail("Action failed: Error occurred in creating the situation ID= '" + sNewSituationID + "'");
		}
		return sNewSituationID;
	}
	
	public static boolean openFirstRowSituation(Stack<String> sWindowHandlers){
		boolean bActionSucceed=false;
		String mwh1=driver.getWindowHandle();
		WebElement oFirstRow=getElement("FirstRowSituationType");      
		String sNewSituationID=getElement("FirstRowSituationID").getText();
		robotWait(1);
		dblclickElementWithNoWait(oFirstRow);
		robotWait(2);
		if(VBSUtils.WaitWindowTitleAppear(sNewSituationID,200)){
			if(switchToWindowPartialTitle(sNewSituationID,sWindowHandlers)){
			}
		} else {
			System.out.println("Open situation error.");    
		}
		robotWait(2);
		String mwh2=driver.getWindowHandle();
		if(!(mwh1.toString()).equals(mwh2.toString())){
			bActionSucceed=true;
	        System.out.println("Situation detail window opened: "+ driver.getTitle());
		} else {
			fail("Action failed: Error occurred in opening the situation detail window for ID= '" + sNewSituationID + "'");
		}
        return bActionSucceed;
	}
	
	public static void openFirstRowSituationPreview(boolean bExpect){
		WebElement oSummaryTable;
		WebElement listLayer=driver.findElement(By.xpath("//div[@id='canvas_listLayer_tableLayer']/div[@id='canvas_listLayer_headerBody']"));
		if(listLayer.findElements(By.xpath("./table[@id='SummaryTable']")).size()>0){
			oSummaryTable=listLayer.findElement(By.xpath("./table[@id='SummaryTable']"));
			WebElement oFirstRowTR;
			oFirstRowTR=oSummaryTable.findElement(By.xpath("(./tbody/tr)[1]"));
			oFirstRowTR.click();
			robotWait(1);
			driver.findElement(By.xpath("//img[@id='situationSummaryPreview_resizer_icon']")).click();
			robotWait(1);
			driver.findElement(By.xpath("//input[@id='situationSummaryPreview_showPreviewOptionCheckbox']")).click();
			By PreviewPanal=By.xpath("//div[@id='situationSummaryPreview_sourcePreview']");		
			waitElementVisibility(PreviewPanal);
			if(bExpect){
				By preViewer1=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelOne']");
				By preViewer2=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelTwo']");
				By preViewer1Menu=By.xpath(".//div[@title='Viewer Menu'][contains(@id,'_function_menu_mousetrap')]");
				waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu));
				waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer2).findElement(preViewer1Menu));
			}
		}
	}
	
	public static void openFirstRowBlockedSituationPreview(boolean bExpect){
		WebElement oSummaryTable;
		WebElement listLayer=driver.findElement(By.xpath("//div[@id='canvas_listLayer_tableLayer']/div[@id='canvas_listLayer_headerBody']"));
		if(listLayer.findElements(By.xpath("./table[@id='SummaryTable']")).size()>0){
			oSummaryTable=listLayer.findElement(By.xpath("./table[@id='SummaryTable']"));
			WebElement oFirstRowTR;
			oFirstRowTR=oSummaryTable.findElement(By.xpath("(./tbody/tr)[1]"));
			oFirstRowTR.click();
			robotWait(1);
			driver.findElement(By.xpath("//img[@id='situationSummaryPreview_resizer_icon']")).click();
			robotWait(1);
			driver.findElement(By.xpath("//input[@id='situationSummaryPreview_showPreviewOptionCheckbox']")).click();
			By PreviewPanal=By.xpath("//div[@id='situationSummaryPreview_sourcePreview']");		
			waitElementVisibility(PreviewPanal);
			if(bExpect){
				By preViewer1=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelOne']");
				By preViewer2=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelTwo']");
				By preViewer1Menu=By.xpath(".//div[@title='Viewer Menu'][contains(@id,'_function_menu_mousetrap')]");
				waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu));
				waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer2).findElement(preViewer1Menu));
			} else {
				By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
				String sMessage=driver.findElement(oNoMadMessage).getText();
				String sExpect="Connection failed: Camera";
				Assert.assertTrue(sMessage.contains(sExpect),
						"***notification verification failed. Got message '" + sMessage + "' and expected '" + sExpect + "'");
			}
		}
	}
	
	public static void openFirstRowSituationPreview(boolean bVewer1,boolean bVewer2){
		WebElement oSummaryTable;
		WebElement listLayer=driver.findElement(By.xpath("//div[@id='canvas_listLayer_tableLayer']/div[@id='canvas_listLayer_headerBody']"));
		if(listLayer.findElements(By.xpath("./table[@id='SummaryTable']")).size()>0){
			oSummaryTable=listLayer.findElement(By.xpath("./table[@id='SummaryTable']"));
			WebElement oFirstRowTR;
			oFirstRowTR=oSummaryTable.findElement(By.xpath("(./tbody/tr)[1]"));
			oFirstRowTR.click();
			robotWait(1);
			driver.findElement(By.xpath("//img[@id='situationSummaryPreview_resizer_icon']")).click();
			reloadVerifySituationPreview(bVewer1, bVewer2);
		}
	}
	
	public static void reloadVerifySituationPreview(boolean bVewer1,boolean bVewer2){
		waitElementVisible(driver.findElement(By.xpath("//input[@id='situationSummaryPreview_showPreviewOptionCheckbox']")));
		driver.findElement(By.xpath("//input[@id='situationSummaryPreview_showPreviewOptionCheckbox']")).click();
		robotWait(1);
		driver.findElement(By.xpath("//input[@id='situationSummaryPreview_showPreviewOptionCheckbox']")).click();
		robotWait(1);
		verifyVidibleSituationPreview(bVewer1, bVewer2);
	}
	
	public static void verifyVidibleSituationPreview(boolean bVewer1,boolean bVewer2){
		By PreviewPanal=By.xpath("//div[@id='situationSummaryPreview_sourcePreview']");		
		waitElementVisibility(PreviewPanal);
		By preViewer1=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelOne']");
		By preViewer2=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelTwo']");
		By preViewer1Menu=By.xpath(".//div[@title='Viewer Menu'][contains(@id,'_function_menu_mousetrap')]");
		if(bVewer1 && bVewer2){
			waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu));
			waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer2).findElement(preViewer1Menu));
		} else {
			int Point1X,Point1Y,Point2X,Point2Y;
			Point1X=driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu).getLocation().getX();
			Point1Y=driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu).getLocation().getY();
			Point2X=driver.findElement(PreviewPanal).findElement(preViewer2).findElement(preViewer1Menu).getLocation().getX();
			Point2Y=driver.findElement(PreviewPanal).findElement(preViewer2).findElement(preViewer1Menu).getLocation().getY();
			if(bVewer1){
				Assert.assertTrue(Point1X*Point1Y>0,"Failed: Situation preview Viewer1 expected to be displayed but blocked.");
			} else{
				Assert.assertTrue(Point1X==0 && Point1Y==0,"Failed: Situation preview Viewer1 expected to be blocked but displayed.");
			}
			if(bVewer2){
				Assert.assertTrue(Point2X*Point2Y>0,"Failed: Situation preview Viewer2 expected to be displayed but blocked.");	
			} else{
				Assert.assertTrue(Point2X==0 && Point1Y==0,"Failed: Situation preview Viewer2 expected to be blocked but displayed.");
			}
		}
	}
	
	public static void SituationPreviewBlockUser(int viewer, boolean bBlocked, String UserGroup, boolean bExpectBlock){
		By PreviewPanal=By.xpath("//div[@id='situationSummaryPreview_sourcePreview']");		
		waitElementVisibility(PreviewPanal);
		if(!bBlocked){
			By preViewer1=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelOne']");
			By preViewer2=By.xpath(".//div[@id='situationSummaryPreview_sourcePreview_panelTwo']");
			By preViewer1Menu=By.xpath(".//div[@title='Viewer Menu'][contains(@id,'_function_menu_mousetrap')]");
			waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu));
			waitElementVisible(driver.findElement(PreviewPanal).findElement(preViewer2).findElement(preViewer1Menu));
			clickElementWithNoWait(driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu));			
			Robot bot;
			try {
				bot = new Robot();
				bot.delay(200);
				int x = (int) driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu).getLocation().getX();
				int y = (int) driver.findElement(PreviewPanal).findElement(preViewer1).findElement(preViewer1Menu).getLocation().getY();
				bot.mouseMove(x+50, y + 150);
				bot.delay(200);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			By sWorkLabel=By.xpath("//div[@class='menuWidgetGlobalDefault'][contains(@id,'_menu_item14_title')][contains(.,'Block/Unblock Video')]");
//			By sWorkLabel1=By.xpath("//div[@class='menuWidgetGlobalDefault'][contains(@style,'display: inline')]/div[@class='menuWidgetGlobalDefault'][contains(@style,'display: inline')]/div[@class='menuWidgetGlobalDefault'][contains(@style,'display: inline')][contains(.,'Block/Unblock Video')]");//// not working
			List<WebElement> targetWebElements=driver.findElements(sWorkLabel);
			if(ClickVisibleLabel(targetWebElements)){
				robotWait(1);
				Test_PreViewerBlockUser(UserGroup, bExpectBlock);	
				robotWaitMS(100);
			} else {
				Assert.fail("***Failed to open Block/UnBlock Video popup from RiskShild Summary preview.");
			}
		} else {
			By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			String sMessage=driver.findElement(oNoMadMessage).getText();
			String sExpect="Connection failed: Camera";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed. Got message '" + sMessage + "' and expected '" + sExpect + "'");
		}	
	}
	
	public static boolean ClickVisibleLabel(List<WebElement> targetWebElements){
		Point aPoint;
		if(targetWebElements.size()>0){
			for(WebElement targetLabel:targetWebElements){
				aPoint=targetLabel.getLocation();
				robotWaitMS(200);
				if(aPoint.x>0 && aPoint.y>0){
					targetLabel.click();
					PageLoadWait.GenericWaitLoad();
					return true;
				}
			}
		}	
		return false;
	}
	
	
	public static boolean VisibleLabel(List<WebElement> targetWebElements){
		Point aPoint;
		if(targetWebElements.size()>0){
			for(WebElement targetLabel:targetWebElements){
				aPoint=targetLabel.getLocation();
				robotWaitMS(200);
				if(aPoint.x>0 && aPoint.y>0){
					return true;
				}
			}
		}	
		return false;
	}
	
	
	public static boolean VisibleLabel(By targetWebElementLabel){
		Point aPoint;
		List<WebElement> targetWebElements=driver.findElements(targetWebElementLabel);
		if(targetWebElements.size()>0){
			for(WebElement targetLabel:targetWebElements){
				aPoint=targetLabel.getLocation();
				robotWaitMS(200);
				if(aPoint.x>0 && aPoint.y>0){
					return true;
				}
			}
		}	
		return false;
	}
	
	public static WebElement getVisibleLabelElement(By targetWebElementLabel){
		Point aPoint;
		List<WebElement> targetWebElements=driver.findElements(targetWebElementLabel);
		if(targetWebElements.size()>0){
			for(WebElement targetLabel:targetWebElements){
				aPoint=targetLabel.getLocation();
				robotWaitMS(200);
				if(aPoint.x>0 && aPoint.y>0){
					return targetLabel;
				}
			}
		}	
		return null;
	}
	public static WebElement getVisibleLabelElement(List<WebElement> targetWebElements){
		Point aPoint;
		if(targetWebElements.size()>0){
			for(WebElement targetLabel:targetWebElements){
				aPoint=targetLabel.getLocation();
				robotWaitMS(200);
				if(aPoint.x>0 && aPoint.y>0){
					return targetLabel;
				}
			}
		}	
		return null;
	}
	public static void openVT(int iMyVewer,String [] DirTree1){
		TestActions.dragDropDevice(iMyVewer,DirTree1,true);
		PageLoadWait.GenericWaitLoad();
		driver.findElement(By.id("applicationLinks_virtualTracker")).click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		driver.findElement(By.id("confirmVirtualTrackerWindow_submit")).click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}
	public static void exitVT(){
		PageLoadWait.GenericWaitLoad();
		driver.findElement(By.id("applicationLinks_virtualTracker")).click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		driver.findElement(By.id("confirmVirtualTrackerWindow_submit")).click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}

	
	public static void openVTCheckCamera(int iMyVewer,String [] DirTree1,String sCamName, int iVewerPosition, boolean bExpectDisplay){
		openVT(iMyVewer,DirTree1);
		By oTargetVTCamTitle = By.xpath("//div[contains(@id,'_viewer" + (iVewerPosition) + "')]//a[@title='" + sCamName + "']");
		if(bExpectDisplay){
			Assert.assertTrue(TestActions.VisibleLabel(oTargetVTCamTitle),
					"***Verify camera '" + sCamName + "' display in viewer cell '" + iVewerPosition + "' expect true but found false. Failed");
		} else {
			Assert.assertFalse(TestActions.VisibleLabel(oTargetVTCamTitle),
					"***Verify camera '" + sCamName + "' display in viewer cell '" + iVewerPosition + "' expect false but found true. Failed");
		}
	}	
	public static boolean openFirstPinSituation(Stack<String> sWindowHandlers){
		boolean bActionSucceed=false;
		String mwh1=driver.getWindowHandle();
		WebElement oSummaryTable;
		WebElement listLayer=driver.findElement(By.xpath("//div[@id='canvas_listLayer_tableLayer']/div[@id='canvas_listLayer_headerBody']"));
		if(listLayer.findElements(By.xpath("./table[@id='SummaryTable']")).size()>0){
			oSummaryTable=listLayer.findElement(By.xpath("./table[@id='SummaryTable']"));
		}else{
			return false;
		}
		
		WebElement oFirstRowTR;
		oFirstRowTR=oSummaryTable.findElement(By.xpath("(./tbody/tr)[1]"));
		
		oFirstRowTR.click();
//		WebElement oFirstRow=getElement("FirstRowSituationType");
		WebElement oFirstRow=oFirstRowTR.findElement(By.xpath("./td[contains(@id,'_categoryName')]"));
		
		
		
		String sNewSituationID=getElement("FirstRowSituationID").getText();
		robotWait(1);
//		WebElement oFirstPin;
		oFirstRow.click();
		robotWait(1);
//		oFirstPin=driver.findElement(By.xpath("//div[@id='canvas']//div[@class='olMap']")).findElement(By.xpath("(.//img[contains(@src,'map_icon_pushpin_')])[1]"));
		dblclickElementWithNoWait(oFirstRow);
		robotWait(3);
		if(VBSUtils.WaitWindowTitleAppear(sNewSituationID,200)){
			if(switchToWindowPartialTitle(sNewSituationID,sWindowHandlers)){
			}
		} else {
			System.out.println("Open situation error.");    
		}
		robotWait(3);
		String mwh2=driver.getWindowHandle();
		if(!(mwh1.toString()).equals(mwh2.toString())){
			bActionSucceed=true;
	        System.out.println("Situation detail window opened: "+ driver.getTitle());
		} else {
			fail("Action failed: Error occurred in opening the situation detail window for ID= '" + sNewSituationID + "'");
		}
        return bActionSucceed;
	}
	
	public static void robotDragDrop(WebElement el1,WebElement el2,Integer iDelayMS){
//    	Point VImage=location(el1);
    	mouseOverElement(el1);
    	PageLoadWait.GenericWaitLoad();
//    	Dimension toPosition=el2.getSize();
		Robot robot4;
		try {
			robot4 = new Robot();
			robot4.delay(iDelayMS);
			robot4.mousePress(InputEvent.BUTTON1_MASK);
			robot4.delay(iDelayMS);
//			robot4.mouseMove(VImage.x + toPosition.width/2,VImage.y + toPosition.height/2);
//			robot4.mouseMove(el2.x,el2.y);
			new Actions(driver).moveToElement(el2).build().perform();
			
			robot4.delay(iDelayMS);
			robot4.mouseRelease(InputEvent.BUTTON1_MASK);
			robot4.delay(iDelayMS);
			PageLoadWait.GenericWaitLoad();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void SetBrowserSize(String sSizeName, int resX,int resY) {		
		switch (sSizeName.toLowerCase()) {
			case "full":
				driver.manage().window().maximize();
				break;
			case "fullscreen":
				driver.manage().window().maximize();
				break;				
			case "max":
				driver.manage().window().maximize();
				break;
			case "maximize":
				driver.manage().window().maximize();
				break;				
			default:
				Dimension screenResolution = new Dimension(resX,resY);
				driver.manage().window().setSize(screenResolution);
				break;
		}		
	}
	public static void Test_DirectoryBlockUser(String sDir1, String sDir2, String sCam, String sBlockingGroup, boolean bBlock){
		String [] DirTree={sDir1,sDir2,sCam};
		String sDialogLoc="//div[@class='dialogPopup blockVideoDialog']";
		WebElement oMyClip;
		oMyClip=ExpandDir(DirTree);
		oMyClip.click();
		ElementRightClick(oMyClip);		
		waitElementVisibility(By.xpath("//td[@id='blockUnblockMenuAction']"));
		driver.findElement(By.xpath("//td[@id='blockUnblockMenuAction']")).click();
		waitElementVisibility("xpath",sDialogLoc+"//input[@id='selectAllGroups']");
		By BlockDialog=By.xpath(sDialogLoc);
		By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@title='" + sBlockingGroup + "']");
		waitElementVisibility(driver.findElement(BlockDialog).findElement(oUserGroup));		
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		if(logicalXOR(GRPElement.isSelected(),bBlock)){
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			ElementJSClick(GRPElement);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		}
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
		robotWaitMS(1000);
		PageLoadWait.GenericWaitLoad();
		if(bBlock){
			By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			String sMessage=driver.findElement(oNoMadMessage).getText();
			String sExpect="Camera " + sCam + " is blocked. Reloaded Directory and disconnected camera if you do not have permission to view.";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed");			
		}
	}
	
	public static void Test_DirectoryBlockUser(String [] DirTree, String sBlockingGroup, boolean bBlock){
		String sDialogLoc="//div[@class='dialogPopup blockVideoDialog']";
		WebElement oMyClip;
		boolean bClicked=false;
		String sCam=DirTree[DirTree.length-1];
		oMyClip=ExpandDir(DirTree);
		oMyClip.click();
		if(!bBlock && oMyClip.findElement(By.xpath(".././*[1]")).getAttribute("style").contains("display: inline")){
			return;
		}
		ElementRightClick(oMyClip);
		waitElementVisibility(By.xpath("//td[@id='blockUnblockMenuAction']"));
		driver.findElement(By.xpath("//td[@id='blockUnblockMenuAction']")).click();
		waitElementVisibility("xpath",sDialogLoc+"//input[@id='selectAllGroups']");
		By BlockDialog=By.xpath(sDialogLoc);
		By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@title='" + sBlockingGroup + "']");
		waitElementVisibility(driver.findElement(BlockDialog).findElement(oUserGroup));		
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		boolean bBlocked=GRPElement.isSelected();
		if(logicalXOR(bBlocked,bBlock)){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
			robotWaitMS(1000);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			ElementJSClick(GRPElement);
			bClicked=true;
			robotWaitMS(1000);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		}
		boolean unblock=false;
		if(bBlocked && bClicked){
			robotWaitMS(1000);
			unblock=true;
		}
		if(!bBlocked && bClicked){
			robotWaitMS(1000);
			bBlock=true;

		}
		GRPElement=driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
		robotWaitMS(200);
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
		robotWaitMS(1500);
		PageLoadWait.GenericWaitLoad();
		By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
		String sMessage=driver.findElement(oNoMadMessage).getText();
		if(bBlock){
			String sExpect="Camera " + sCam + " is blocked. Reloaded Directory and disconnected camera if you do not have permission to view.";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed");		
		}
		if(unblock){
			String sExpect="Camera " + sCam + " is unblocked. Reloaded Directory.";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed");		
		}
	}
	
	public static void Test_DirectoryAllBlockUser(String sDir1, String sDir2, String sCam, boolean bBlock){
		String [] DirTree={sDir1,sDir2,sCam};
		String sDialogLoc="//div[@class='dialogPopup blockVideoDialog'][@id='blockVideoPopupOnTree']";
		WebElement oMyClip;
		oMyClip=ExpandDir(DirTree);
		oMyClip.click();
		ElementRightClick(oMyClip);		
		waitElementVisibility(By.xpath("//td[@id='blockUnblockMenuAction']"));
		driver.findElement(By.xpath("//td[@id='blockUnblockMenuAction']")).click();
		waitElementVisibility("xpath",sDialogLoc+"//input[@id='selectAllGroups']");
		By BlockDialog=By.xpath(sDialogLoc);
		By oUserGroup=By.xpath("//div[@class='dialogPopup blockVideoDialog']//input[@id='selectAllGroups']");
		waitElementVisibility(driver.findElement(BlockDialog).findElement(oUserGroup));	
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		if(logicalXOR(GRPElement.isSelected(),bBlock)){
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			GRPElement.click();
			PageLoadWait.GenericWaitLoad();
			GRPElement.click();
			PageLoadWait.GenericWaitLoad();
			GRPElement.click();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
			PageLoadWait.GenericWaitLoad();
		} else {
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			GRPElement.click();
			PageLoadWait.GenericWaitLoad();
			GRPElement.click();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
			robotWaitMS(500);
			try{
				driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Cancel Block this device']")).click();
				robotWaitMS(500);		
			} catch (ElementNotVisibleException e){}
		}
		robotWaitMS(1000);
		if(bBlock){
			By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			String sMessage=driver.findElement(oNoMadMessage).getText();
			String sExpect="Camera " + sCam + " is blocked. Reloaded Directory and disconnected camera if you do not have permission to view.";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed");
		}
	}
	public static void dragDropDevice(int iViewer,String [] DirTree,boolean bExpect){
		WebElement oMyClip, AddImg;
		int iMessage=0;
		oMyClip=ExpandDir(DirTree);
		oMyClip.click();
		By myViewer = By.xpath("//div[contains(@id,'_viewer" + (iViewer-1) + "')]");
       	if(ElementExists(myViewer)){
        	AddImg=getElement("xpath","//div[contains(@id,'_viewer" + (iViewer-1) + "')]");
        	Robot robot2;
        	
			try {
				robot2 = new Robot();
				robot2.delay(1000);
				mouseOverElement(oMyClip);
				if (Arrays.asList("bookmark","clip").contains(oMyClip.findElement(By.xpath("..")).getAttribute("elemtype"))){
					if(DirTree.length>2){
						iMessage=DirTree.length-2;						
					} else {
						Assert.fail("***Device is not stored in the directory: failed" + DirTree.length);		
					}
				} else {
					iMessage=DirTree.length-1;
				}
				robot2.delay(200);
				robot2.mousePress(InputEvent.BUTTON1_MASK);
				robot2.delay(200);
				mouseOverElement(AddImg);
				robot2.delay(500);
				robot2.mouseRelease(InputEvent.BUTTON1_MASK);
				robot2.delay(500);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			if(bExpect){
				String TestViewerIDKey="_viewer" + (iViewer-1);
				By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
				By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
				driver.switchTo().defaultContent();
				WebElement TestViewer=driver.findElement(WorkViewer);
				waitElementVisible(TestViewer.findElement(TestViewerMenu));	
			} else {
				By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
				String sMessage=driver.findElement(oNoMadMessage).getText();
				String sExpect="Connection failed: Camera '" + DirTree[iMessage] + "' is blocked.";
				Assert.assertTrue(sMessage.contains(sExpect),
						"***notification verification failed. Got message '" + sMessage + "' and expected '" + sExpect + "'");
			}
    	}
	}
	public static void Test_DirectoryBlockUsers(String[][] List, String sUsrGrp, boolean bBlock){
		for(int iloop=0;iloop<List.length; iloop++){
			Test_DirectoryBlockUser(List[iloop],sUsrGrp,bBlock);
			ExcuteKeyEvent(116);
		}
	}
	
	public static ArrayList<String> verifyblockMessage(String sCam){
		By oNoMadMessage;
		String sMessage;
		int iListLength=0,iLoop=0,iNoMessage=0;
		boolean bCont=true;
		int loopCheck=0;
		ArrayList<String> sMessageList = new ArrayList<>();
		while(bCont){
			oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			sMessage=driver.findElement(oNoMadMessage).getText();
			loopCheck++;
			if(loopCheck>100){
				bCont=false;
				System.out.println("check message time out.");
			}
			if(sMessage.length()>0){
				if(sMessageList.size()==0){
					sMessageList.add(sMessage);
					loopCheck=1;
					iNoMessage=0;
				} else {
					iListLength=sMessageList.size();
					if(sMessageList.get(iListLength-1) == null || sMessageList.get(iListLength-1).equals(sMessage)){
						robotWaitMS(50);
						iNoMessage++;
					} else {
						sMessageList.add(sMessage);
						iNoMessage=0;
						loopCheck=1;
					}
				}
				if(sMessageList.size()>10){
					bCont=false;
				}
				iLoop++;
				if(iNoMessage>50){
					bCont=false;
				}
				if(iLoop>20){
					bCont=false;
				}
			}
		}
		return sMessageList;
	}
	
	public static boolean verifyblockMessage(String sCam, boolean bExpect){
		By oNoMadMessage;
		String sMessage, sExpect;
		int iLoop=0,iNoMessage=0;
		boolean bCont=true, bReturn=false;
		int loopCheck=0;
		if(bExpect){
			sExpect="Camera " + sCam + " is blocked. Reloaded Directory and disconnected camera if you do not have permission to view.";
		} else {
			sExpect="Camera " + sCam + " is unblocked. Reloaded Directory.";
		}
		while(bCont){
			oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			sMessage=driver.findElement(oNoMadMessage).getText();
			loopCheck++;
			if(loopCheck>200){
				bCont=false;
				System.out.println("check message time out.");
			}
			if(sMessage.length()>0){
				if(sExpect.equals(sMessage)){
					robotWaitMS(50);
					bReturn=true;
					bCont=false;
				} else {
					iNoMessage=0;
					loopCheck=1;
				}
			}
			robotWaitMS(50);
			iLoop++;
			if(iNoMessage>50){
				bCont=false;
			}
			if(iLoop>100){
				bCont=false;
			}
		}
		return bReturn;
	}
	
	public static boolean verifySDNoMadMessage(String sExpectMeg){
		By oNoMadMessage;
		String sMessage;
		int iLoop=0,iNoMessage=0;
		boolean bCont=true, bReturn=false;
		int loopCheck=0;
		while(bCont){
			oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			sMessage=driver.findElement(oNoMadMessage).getText();
			loopCheck++;
			if(loopCheck>200){
				bCont=false;
				System.out.println("check message time out.");
			}
			if(sMessage.length()>0){
				if(sMessage.contains(sExpectMeg)){
					robotWaitMS(50);
					bReturn=true;
					bCont=false;
				} else {
					iNoMessage=0;
					loopCheck=1;
				}
			}
			robotWaitMS(50);
			iLoop++;
			if(iNoMessage>50){
				bCont=false;
			}
			if(iLoop>100){
				bCont=false;
			}
		}
		return bReturn;
	}
	
	
	public static void treeCollapse(){
		List<WebElement> targetWebElements=driver.findElements(By.xpath("//li[contains(@class,'completed treeCollapse') or contains(@class,'treeCollapse completed')]"));
		if(targetWebElements.size()>0){
			int iNumb=targetWebElements.size();
			int iLoop;
			for(iLoop=0;iLoop<iNumb;iLoop++){
				targetWebElements.get(iNumb-iLoop-1).click();
				robotWaitMS(200);
			}
		}
	}
	
	public static ArrayList<String> dragDropDevice(int iViewer,WebElement oWebE){
		boolean bCont=true; 
		int iListLength=0,iLoop=0,iNoMessage=0;
		WebElement AddImg;
		By oNoMadMessage;
		String sMessage;
		ArrayList<String> sMessageList = new ArrayList<>();
		oWebE.click();
		robotWaitMS(200);
		By myViewer = By.xpath("//div[contains(@id,'_viewer" + (iViewer-1) + "')]");
       	if(ElementExists(myViewer)){
        	AddImg=getElement("xpath","//div[contains(@id,'_viewer" + (iViewer-1) + "')]");
        	Robot robot2;
			try {
				robot2 = new Robot();
				robot2.delay(1000);
				mouseOverElement(oWebE);
				robot2.delay(500);
				robot2.mousePress(InputEvent.BUTTON1_MASK);
				robot2.delay(500);
				mouseOverElement(AddImg);
				robot2.delay(500);
				robot2.mouseRelease(InputEvent.BUTTON1_MASK);
				robot2.delay(200);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			int loopCheck=0;
			while(bCont){
				oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
				sMessage=driver.findElement(oNoMadMessage).getText();
				loopCheck++;
				if(loopCheck>100){
					bCont=false;
					System.out.println("check message time out.");
				}
				if(sMessage.length()>0){
					if(sMessageList.size()==0){
						sMessageList.add(sMessage);
						loopCheck=1;
						iNoMessage=0;
					} else {
						iListLength=sMessageList.size();
						if(sMessageList.get(iListLength-1) == null || sMessageList.get(iListLength-1).equals(sMessage)){
							robotWaitMS(50);
							iNoMessage++;
						} else {
							sMessageList.add(sMessage);
							iNoMessage=0;
							loopCheck=1;
						}
					}
					if(sMessageList.size()>10){
						bCont=false;
					}
					iLoop++;
					if(iNoMessage>50){
						bCont=false;
					}
					if(iLoop>20){
						bCont=false;
					}
				}
			}			
			if(sMessageList!=null){
				return sMessageList;
			}
    	}
		return null;
	}
	
	public static void Test_DirectoryUnBlockAllUser(String sDir1, String sDir2, String sCam){
			String [] DirTree={sDir1,sDir2,sCam};
			String sDialogLoc="//div[@class='dialogPopup blockVideoDialog']";
			WebElement oMyClip;
			PageLoadWait.GenericWaitLoad();
			oMyClip=ExpandDir(DirTree);
			oMyClip.click();
			ElementRightClick(oMyClip);
			waitElementVisibility(By.xpath("//td[@id='blockUnblockMenuAction']"));
			driver.findElement(By.xpath("//td[@id='blockUnblockMenuAction']")).click();
			By BlockDialog=By.xpath(sDialogLoc);
			By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@checked='']");
			waitElementVisibility("xpath",sDialogLoc+"//input[@id='selectAllGroups']");
			List<WebElement> targetWebElements=driver.findElement(BlockDialog).findElements(oUserGroup);
			if(targetWebElements.size()>0){
				for(WebElement checkBox:targetWebElements){
					if(checkBox.isSelected()){
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkBox);
						robotWaitMS(200);
						ElementJSClick(checkBox);
						PageLoadWait.GenericWaitLoad();
						PageLoadWait.GenericWaitLoad();
					}
				}			
			}
			driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
			robotWaitMS(1000);
			PageLoadWait.GenericWaitLoad();
	}
	public static void Test_DirectoryBlockAtLeast1User(String sDir1, String sDir2, String sCam, String sBlockingGroup, boolean bBlock){
		String [] DirTree={sDir1,sDir2,sCam};
		String sDialogLoc="//div[@class='dialogPopup blockVideoDialog']";
		WebElement oMyClip;
		oMyClip=ExpandDir(DirTree);
		oMyClip.click();
		ElementRightClick(oMyClip);
		waitElementVisibility(By.xpath("//td[@id='blockUnblockMenuAction']"));
		waitElementClickableReady(By.xpath("//td[@id='blockUnblockMenuAction']"));
		driver.findElement(By.xpath("//td[@id='blockUnblockMenuAction']")).click();	
		By BlockDialog=By.xpath(sDialogLoc);
		By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@title='" + sBlockingGroup + "']");
		waitElementVisibility(By.xpath(sDialogLoc + "//div[@class='buttonFactory'][@title='Block this device']"));
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
		robotWaitMS(500);
		By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox']");
		String sMessage=driver.findElement(oNoMadMessage).getText();
		String sExpect="You need to select at least one User Group to block.";
		Assert.assertTrue(sMessage.contains(sExpect),
				"***notification verification failed");
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		if(logicalXOR(GRPElement.isSelected(),bBlock)){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			ElementJSClick(GRPElement);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		}
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Block this device']")).click();
		robotWaitMS(1000);
		PageLoadWait.GenericWaitLoad();
		if(bBlock){
			oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle']");
			sMessage=driver.findElement(oNoMadMessage).getText();
			sExpect="Camera " + sCam + " is blocked. Reloaded Directory and disconnected camera if you do not have permission to view.";
			Assert.assertTrue(sMessage.contains(sExpect),
					"***notification verification failed");			
		}
	}
	public static void Test_BlockUser(int iViewer, String sCam, String sGroup, boolean bBlock){
		String TestView=sCam;
		By oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		boolean bClientButtpn=false;
		if(driver.findElements(oClientButton).size()>0){
			bClientButtpn=true;
		}
		By oQALAB= By.xpath("//*[contains(text(), 'QA Lab')]");
		if(bClientButtpn){
			clickElementWithNoWait(oClientButton);
			MyPrint.myPrint("***","Navigate to client UI screen");
			robotWait(2);
		}
		waitElementVisibility(oQALAB);
		waitElementClickableReady("QALab");
		LoadMyView(TestView,true,true,iViewer);
		robotWait(5);
		driver.switchTo().defaultContent();
		Test_ViewerBlockUser(iViewer, sGroup, bBlock);
	}
	
	public static void Test_MapViewerBlockUser(int iViewer,String [] DirTree2,String sGroup, boolean bBlock){
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		By oCancel=By.xpath("//div[contains(@style,'display: block')]/div/table[contains(@style,'display: inline')]/tbody/tr/td/div/img[contains(@src,'menuitem_icon_redx.png')]");
		if(driver.findElements(oCancel).size()>0){
			driver.findElements(oCancel).get(0).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		}
		Test_OpenCameraInMap(iViewer,DirTree2);
		Test_ViewerBlockUser(iViewer+1, sGroup, bBlock);
	}
	
	public static void Test_OpenCameraInMap(int iViewer,String [] DirTree2){
		By oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		By oQALAB= By.xpath("//*[contains(text(), 'QA Lab')]");
		boolean bClientButtpn=false;
		if(driver.findElements(oClientButton).size()>0){
			bClientButtpn=true;
		}
		if(bClientButtpn){
			clickElementWithNoWait(oClientButton);
			MyPrint.myPrint("***","Navigate to client UI screen");
			robotWait(2);
		}
		waitElementVisibility(oQALAB);
		waitElementClickableReady("QALab");
		TestActions.dragDropDevice(iViewer,DirTree2,true);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		By myOnlyCamMapViewIcon=By.xpath(".//img[contains(@src,'ptz_camera_online.png')]");
		MonitorCameraInMapViewer(myOnlyCamMapViewIcon,iViewer,iViewer+1);
		
	}
	
	public static void Test_ViewerBlockUser(int iViewer, String sGroup, boolean bBlock){
		String TestViewerIDKey="_viewer" + (iViewer-1);
		By sWorkLabel=By.xpath("(//div[@class='menuWidgetGlobalDefault'][contains(@id,'_title')][contains(.,'Block/Unblock Video')])[" + iViewer + "]");
		By BlockDialog=By.xpath("//div[@class='dialogPopup blockVideoDialog'][contains(@style,'display: inline')]");
		By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@title='" + sGroup + "']");
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
		driver.switchTo().defaultContent();
		WebElement TestViewer=driver.findElement(WorkViewer);
		waitElementVisible(TestViewer.findElement(TestViewerMenu));
		mouseOverElementRobotClick(TestViewer.findElement(TestViewerMenu));
		robotWaitMS(200);
		TestViewer=driver.findElement(WorkViewer);
		driver.findElement(sWorkLabel).click();
		waitElementClickableReady(BlockDialog);
		PageLoadWait.GenericWaitLoad();
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		if(logicalXOR(GRPElement.isSelected(),bBlock)){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			ElementJSClick(GRPElement);
		}
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='OK']")).click();
		robotWaitMS(300);
		PageLoadWait.GenericWaitLoad();
	}
	
	public static void Test_PreViewerBlockUser(String sGroup, boolean bBlock){
		By BlockDialog=By.xpath("//div[@class='dialogPopup blockVideoDialog'][contains(@style,'display: inline')]");
		By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@title='" + sGroup + "']");
		driver.switchTo().defaultContent();
		waitElementClickableReady(BlockDialog);
		PageLoadWait.GenericWaitLoad();
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		if(logicalXOR(GRPElement.isSelected(),bBlock)){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			ElementJSClick(GRPElement);
		}
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='OK']")).click();
		robotWaitMS(300);
		
		if(driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Cancel']")).getLocation().getX()>0){
			driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='Cancel']")).click();
		}
		PageLoadWait.GenericWaitLoad();
	}
	
	
	public static void Test_BlockAtLeast1User(int iViewer, String sCam, String sGroup, boolean bBlock){
		String TestView=sCam;
		String TestViewerIDKey="_viewer" + (iViewer-1);
		By sWorkLabel=By.xpath("(//div[@class='menuWidgetGlobalDefault'][contains(@id,'_title')][contains(.,'Block/Unblock Video')])[" + iViewer + "]");
		By BlockDialog=By.xpath("//div[@class='dialogPopup blockVideoDialog'][contains(@style,'display: inline')]");
		By oUserGroup=By.xpath(".//input[@name='blockUserGroup'][@title='" + sGroup + "']");
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
		By oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		boolean bClientButtpn=false;
		if(driver.findElements(oClientButton).size()>0){
			bClientButtpn=true;
		}
		By oQALAB= By.xpath("//*[contains(text(), 'QA Lab')]");
		if(bClientButtpn){
			clickElementWithNoWait(oClientButton);
			MyPrint.myPrint("***","Navigate to client UI screen");
			robotWait(2);
		}
		waitElementVisibility(oQALAB);
		waitElementClickableReady("QALab");
		LoadMyView(TestView,true,true,iViewer);
		driver.switchTo().defaultContent();
		WebElement TestViewer=driver.findElement(WorkViewer);
		waitElementVisible(TestViewer.findElement(TestViewerMenu));		
		mouseOverElementRobotClick(TestViewer.findElement(TestViewerMenu));
		robotWaitMS(200);
		TestViewer=driver.findElement(WorkViewer);
		driver.findElement(sWorkLabel).click();	
		waitElementClickableReady(BlockDialog);
		PageLoadWait.GenericWaitLoad();
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='OK']")).click();
		robotWaitMS(500);
		By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox']");
		String sMessage=driver.findElement(oNoMadMessage).getText();
		String sExpect="You need to select at least one User Group to block.";
		Assert.assertTrue(sMessage.contains(sExpect),
				"***notification verification failed");
		WebElement GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
		if(logicalXOR(GRPElement.isSelected(),bBlock)){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GRPElement);
			robotWaitMS(200);
			GRPElement = driver.findElement(BlockDialog).findElement(oUserGroup);
			ElementJSClick(GRPElement);
		}
		driver.findElement(BlockDialog).findElement(By.xpath(".//div[@class='buttonFactory'][@title='OK']")).click();
		robotWaitMS(300);
		PageLoadWait.GenericWaitLoad();
	}	
	public static void Test_CreateDefaultSeveritySituation(){
		int iViewer;
		iViewer=1;
		String TestViewerIDKey="_viewer" + (iViewer-1);
		String TestView="Default View";
		By oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		By oQALAB= By.xpath("//*[contains(text(), 'QA Lab')]");
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
		By CreateSituationLabel=By.xpath("(//div[contains(.,'Create Situation')][contains(@id,'_title')])[" + iViewer + "]");
		boolean bClientButtpn=false;
		if(driver.findElements(oClientButton).size()>0){
			bClientButtpn=true;
		}
		if(bClientButtpn){
			clickElementWithNoWait(oClientButton);
			MyPrint.myPrint("***","Navigate to client UI screen");
			robotWait(2);
		}
		waitElementVisibility(oQALAB);
		waitElementClickableReady("QALab");
		PageLoadWait.GenericWaitLoad();
		LoadMyView(TestView,true,true,iViewer);
		driver.switchTo().defaultContent();	
		WebElement TestViewer=driver.findElement(WorkViewer);
		waitElementVisible(TestViewer.findElement(TestViewerMenu));
		mouseOverElementRobotClick(TestViewer.findElement(TestViewerMenu));
		robotWaitMS(200);
		TestViewer=driver.findElement(WorkViewer);
		driver.findElement(CreateSituationLabel).click();
		robotWait(2);
		WebElement imgSituation=driver.findElement(By.xpath("//div[@id='createSituationWindow']")).findElement(By.xpath(".//div[@id='createSituationWindow_canvas']")).findElement(By.xpath(".//img[contains(@src,'icon_mapmarker_situation_dynamic_nocolor.png')]"));
		imgSituation.click();
		robotWaitMS(300);
		Robot bot;
		try {
			bot = new Robot();
			bot.delay(200);
			int x = (int) imgSituation.getLocation().getX();
			int y = (int) imgSituation.getLocation().getY();
			bot.mousePress(InputEvent.BUTTON1_MASK);
			bot.delay(200);
			bot.mouseMove(x+400, y + 200);
			bot.delay(100);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.delay(200);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robotWait(1);
		robotWaitMS(200);
		String selectedOption = new Select(driver.findElement(By.id("newSituationSeverity"))).getFirstSelectedOption().getText();
		robotWaitMS(200);
		String callerClassName = new Exception().getStackTrace()[1].getClassName();
		if(!callerClassName.toLowerCase().contains("severity")){
			if(selectedOption.toLowerCase().contains("autop")){
				WebElement severityList=driver.findElement(By.xpath("//select[@id='newSituationSeverity']"));
				if(TestActions.ElementExists(severityList)){
					TestActions.DropDownSelect(severityList,"Low");
					PageLoadWait.GenericWaitLoad();
				}
			}
		}
		robotWait(2);
		robotWait(2);
		robotWait(2);
		PageLoadWait.GenericWaitLoad();
		try{
			driver.findElement(By.xpath("//div[@id='createSituationWindow']")).findElement(By.xpath(".//div[@id='newSituationCreate']")).click();			
		} catch (ElementNotVisibleException ddd){
			ddd.printStackTrace();
		}
		robotWait(2);
		robotWait(2);
		robotWait(2);
		robotWait(2);
		driver.switchTo().defaultContent();
		robotWait(2);
		PageLoadWait.GenericWaitLoad();
		robotWait(2);
	}
	public static void Test_CreateClientUISituation(String[] Dir){
		int iViewer;
		iViewer=1;
		dragDropDevice(iViewer,Dir,true);
		PageLoadWait.GenericWaitLoad();
		robotWait(5);
		PageLoadWait.GenericWaitLoad();
		driver.switchTo().defaultContent();	
		Test_CreateClientUISituation(iViewer);
	}
	
	
	public static void Test_CreateClientUISituation(int iVew){
		int iViewer;
		iViewer=iVew;
		String TestViewerIDKey="_viewer" + (iViewer-1);
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
		By CreateSituationLabel=By.xpath("(//div[contains(.,'Create Situation')][contains(@id,'_title')])[" + iViewer + "]");
		WebElement TestViewer=driver.findElement(WorkViewer);		
		waitElementVisible(TestViewer.findElement(TestViewerMenu));
		robotWaitMS(500);
		mouseOverElementRobotClick(TestViewer.findElement(TestViewerMenu));
		robotWaitMS(400);
//		PageLoadWait.GenericWaitLoad();
//		robotWaitMS(100);
//		PageLoadWait.GenericWaitLoad();
//		robotWaitMS(100);
		TestViewer=driver.findElement(WorkViewer);
		driver.findElement(CreateSituationLabel).click();
		robotWait(1);
		PageLoadWait.GenericWaitLoad();
		waitElementVisible(driver.findElement(By.xpath("//div[@id='createSituationWindow']")).findElement(By.xpath(".//div[@id='newSituationCreate']")));
		robotWait(1);
		waitElementClickableReady(By.xpath("//div[@id='createSituationWindow']//div[@id='newSituationCreate']"));
		mouseOverElementRobotClick(By.xpath("//div[@id='createSituationWindow']//div[@id='newSituationCreate']"));		
//		driver.findElement(By.xpath("//div[@id='createSituationWindow']")).findElement(By.xpath(".//div[@id='newSituationCreate']")).click();
		robotWait(2);
		driver.switchTo().defaultContent();
		PageLoadWait.GenericWaitLoad();
	}
	
	
	public static void Test_CreateDefaultSeveritySituation(PrintStream OutPutStream,String sMessage){
		String TestView="Default View";
		int iViewer;
		iViewer=1;
		By oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		By oQALAB= By.xpath("//*[contains(text(), 'QA Lab')]");
		boolean bClientButtpn=false;
		if(driver.findElements(oClientButton).size()>0){
			bClientButtpn=true;
		}		
		if(bClientButtpn){
			clickElementWithNoWait(oClientButton);
			MyPrint.myPrint("***","Navigate to client UI screen");
			robotWait(2);
		}
		if(getElement("controlsCanvas_tabTwoTitle").getAttribute("style").contains("rgb(0, 0, 0)")){
			driver.findElement(By.id("controlsCanvas_tabOneClickSurface")).click();
			PageLoadWait.GenericWaitLoad();
		}
		waitElementVisibility(oQALAB);
		waitElementClickableReady("QALab");
		LoadMyView(TestView,true,true,iViewer);
		driver.switchTo().defaultContent();
		PageLoadWait.GenericWaitLoad();
		String TestViewerIDKey="_viewer" + (iViewer-1);
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		WebElement TestViewer=driver.findElement(WorkViewer);
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
		waitElementVisible(TestViewer.findElement(TestViewerMenu));
		mouseOverElementRobotClick(TestViewer.findElement(TestViewerMenu));	
		TestViewer=driver.findElement(WorkViewer);
		By CreateSituationLabel=By.xpath("(//div[contains(.,'Create Situation')][contains(@id,'_title')])[" + iViewer + "]");
		driver.findElement(CreateSituationLabel).click();
		robotWait(2);
		WebElement imgSituation=driver.findElement(By.xpath("//div[@id='createSituationWindow']")).findElement(By.xpath(".//div[@id='createSituationWindow_canvas']")).findElement(By.xpath(".//img[contains(@src,'icon_mapmarker_situation_dynamic_nocolor.png')]"));
		imgSituation.click();
		Robot bot;
		try {
			bot = new Robot();
			bot.delay(200);
			int x = (int) imgSituation.getLocation().getX();
			int y = (int) imgSituation.getLocation().getY();
			bot.mousePress(InputEvent.BUTTON1_MASK);
			bot.delay(200);
			bot.mouseMove(x+400, y + 200);
			bot.delay(250);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			bot.delay(240);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robotWaitMS(20);
		String selectedOption = new Select(driver.findElement(By.id("newSituationSeverity"))).getFirstSelectedOption().getText();
		String callerClassName = new Exception().getStackTrace()[1].getClassName();
		if(callerClassName.toLowerCase().contains("severity")){
			if(selectedOption.toLowerCase().contains("autop")){
				WebElement severityList=driver.findElement(By.xpath("//select[@id='newSituationSeverity']"));
				if(TestActions.ElementExists(severityList)){
					TestActions.DropDownSelect(severityList,"Low");
				}
			}
		}
		driver.findElement(By.xpath("//div[@id='createSituationWindow']")).findElement(By.xpath(".//div[@id='newSituationCreate']")).click();
		robotWaitMS(500);
		OutPutStream.println(sMessage);
		robotWait(2);
		driver.switchTo().defaultContent();
		PageLoadWait.GenericWaitLoad();
	}
	public static void Test_CloseSituations(Stack<String> sWindowHandlers) throws Exception{
		WebElement oFirstRow;		
		String sNewSituationID;
		By oNoSituations=By.xpath("//p[@class='loadingErrorMsg'][contains(.,'No situations match the filter criteria')]");
		boolean bCont=true;
		WebElement objWebElmtRiskShild;
		objWebElmtRiskShild=getElement("oLaunchShild2");
		waitElementClickableReady("oLaunchShild2");
		OpenPopupByClickObj(objWebElmtRiskShild, sWindowHandlers,"Summary",120);
		waitElementClickableReady("canvas_tabTwoTitle");
		clickElementWithNoWait(getElement("canvas_tabTwoTitle"));
		PageLoadWait.GenericLongWaitLoad();
		// create a situation
        PageLoadWait.GenericWaitLoad();
        waitElementClickableReady(getElementLocator("SummaryTable"));
        PageLoadWait.GenericWaitLoad();
        PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericLongWaitLoad();
		
		while(bCont){
			if(ElementExists(oNoSituations)){
				break;
			}			
			oFirstRow=getElement("FirstRowSituationType");
			sNewSituationID=getElement("FirstRowSituationID").getText();
			
			dblclickElementWithNoWait(oFirstRow);
			if(VBSUtils.WaitForWindowTitle(sNewSituationID,200)){
				System.out.println("Got the window.");
				if(switchToWindowPartialTitle(sNewSituationID,sWindowHandlers)){
					PageLoadWait.GenericWaitLoad();
				}
			} else {
				System.out.println("Open situation error.");
			}
			waitElementClickableReady("toolbar_commands_editStatusButton");
			waitElementClickableReady("editCanvas_tabOneTitle");
			waitElementClickableReady("editCanvas_tabOneTitle");
			
			if(ElementExists(By.id("removeButton2"))){
				mouseOverElement(By.id("removeButton2"));
				waitElementClickableReady(By.id("removeButton2"));
				mouseOverElementRobotClick(By.id("removeButton2"));
				waitElementClickableReady(By.id("removeAPCancel"));
				clickElementWithNoWait(By.id("removeAPSubmit"));
				waitWindowComplate(20,500);				
			}
			waitElementClickableReady(By.xpath("//div[@id='frame']/iframe"));
			SwitchToFrame(By.name("editCanvas_actionPlanLayerFrame"));
			allCheckBoxes(By.xpath("(//input[@type='checkbox'])"),true);
			driver.switchTo().defaultContent();
			waitElementClickableReady(getElementLocator("toolbar_commands_completeButton"));
			clickElementWithNoWait(getElementLocator("toolbar_commands_completeButton"));
			SwitchToFrame(By.name("completeWindowFrame"));
			waitElementClickableReady("SubmitSituation");
			clickElementWithNoWait(getElementLocator("SubmitSituation"));
			driver.switchTo().defaultContent();
			CloseDriver(sWindowHandlers);
			driver.switchTo().defaultContent();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		}
		CloseDriver(sWindowHandlers);
		driver.switchTo().defaultContent();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}
	
	
	public static  boolean equalLists(ArrayList<String> a, ArrayList<String> b){     
	    // Check for sizes and nulls
	    if ((a.size() != b.size()) || (a == null && b!= null) || (a != null && b== null)){
	        return false;
	    }
	    if (a.isEmpty() && b.isEmpty()) 
	    	return true;
	    // Sort and compare the two lists          
	    Collections.sort(a);
	    Collections.sort(b);
	    return a.equals(b);
	}
	
	
	public static void createBookmarkClip(String sUserName,String sSonyCamName, int iMyVewer,Stack<String> sWindowHandlers,String BookMarkName,boolean bClip){
		String TestViewerIDKey="_viewer" + (iMyVewer-1);
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
//		By RecordButton=By.xpath("//div[@title='Toggle Camera Mode']");
		By RecordButton1=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]//div[@title='Toggle Camera Mode']");
		try {
			login(sUserName, sUserName+1 ,sWindowHandlers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			LoadMyView(sSonyCamName, true, true, iMyVewer);
			waitElementVisibility(RecordButton1);
		} catch (TimeoutException e) {
			Robot robot2;
			try {
				robot2 = new Robot();
				robot2.keyPress(KeyEvent.VK_F5);
				robot2.delay(100);
				robot2.keyRelease(KeyEvent.VK_F5);
				robot2.delay(5000);
				LoadMyView(sSonyCamName, true, true, iMyVewer);
				waitElementVisibility(RecordButton1);
			} catch (AWTException | TimeoutException e2) {
				e2.printStackTrace();
			}
		}
		waitElementClickableReady(RecordButton1);
		mouseOverElementRobotClick(RecordButton1);
		driver.switchTo().defaultContent();
		WebElement TestViewer=driver.findElement(WorkViewer);
		waitElementVisible(TestViewer.findElement(TestViewerMenu));
		robotWait(5);
		createClipBookMark(iMyVewer, BookMarkName, bClip);
		driver.findElement(RecordButton1).click();
		robotWaitMS(500);
	}
	
	
	public static void createBookmarkClip(String sSonyCamName, int iMyVewer,Stack<String> sWindowHandlers,String BookMarkName,boolean bClip){
		String TestViewerIDKey="_viewer" + (iMyVewer-1);
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
//		By RecordButton=By.xpath("//div[@title='Toggle Camera Mode']");
		By RecordButton1=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]//div[@title='Toggle Camera Mode']");
		WebElement TestViewer;
		try {
			LoadMyView(sSonyCamName, true, true, iMyVewer);
			TestViewer=driver.findElement(WorkViewer);
			waitElementVisibility(RecordButton1);
		} catch (TimeoutException e) {
			Robot robot2;
			try {
				robot2 = new Robot();
				robot2.keyPress(KeyEvent.VK_F5);
				robot2.delay(100);
				robot2.keyRelease(KeyEvent.VK_F5);
				robot2.delay(5000);
				LoadMyView(sSonyCamName, true, true, iMyVewer);
				TestViewer=driver.findElement(WorkViewer);
				waitElementVisibility(RecordButton1);
//				waitElementVisibility(RecordButton);
			} catch (AWTException | TimeoutException e2) {
				e2.printStackTrace();
			}
		}
		waitElementClickableReady(RecordButton1);
		mouseOverElementRobotClick(RecordButton1);
		driver.switchTo().defaultContent();
		TestViewer=driver.findElement(WorkViewer);
		waitElementVisible(TestViewer.findElement(TestViewerMenu));
		robotWait(5);
		createClipBookMark(iMyVewer, BookMarkName, bClip);
		driver.findElement(RecordButton1).click();
		robotWaitMS(500);		
	}
	
	public static void createClipBookMark(int iMyVewer, String sName, boolean bClip){
		String sLableTitle="Recorded Options";
		String TestViewerIDKey="_viewer" + (iMyVewer-1);
		By sWorkLabel=By.xpath("(//div[@class='menuWidgetGlobalDefault'][contains(@id,'_title')][contains(.,'" + sLableTitle + "')])[" + iMyVewer + "]");
		By WorkViewer=By.xpath("//div[contains(@id,'" + TestViewerIDKey + "')]");
		By TestViewerMenu=By.xpath(".//div[@title='Viewer Menu']");
		By BookMarkNameField, BookMarkPopOKButton, saveBookmark, BookMarkPopDialog;
		driver.switchTo().defaultContent();
		WebElement TestViewer=driver.findElement(WorkViewer);
		waitElementVisible(TestViewer.findElement(TestViewerMenu));
		robotWait(5);		
		if(bClip){
			BookMarkNameField=By.xpath("//input[@class='formInputStyle'][contains(@id,'clipControl')][contains(@id,'clipname')]");
			BookMarkPopOKButton=By.xpath("//div[@class='dialogPopup'][contains(@id,'clipControl')]/div//img[@src='/vs4/img/menuitems/menuitem_icon_greencheck.png']");
			saveBookmark=By.xpath("//div[contains(@style,'display: inline;')][contains(@style,'z-index: 1010;')]/div/div/div[.='Save Clip']");
			BookMarkPopDialog=By.xpath("//div[@class='dialogPopup'][contains(@id,'clipControl')]");
		} else {
			BookMarkNameField=By.xpath("//input[@class='dialogPopupInput formInputStyle'][contains(@id,'bookmarkControl')][contains(@id,'name')]");
			BookMarkPopOKButton=By.xpath("//div[@class='dialogPopup'][contains(@id,'bookmarkControl')]/div//img[@src='/vs4/img/menuitems/menuitem_icon_greencheck.png']");
			saveBookmark=By.xpath("//div[contains(@style,'display: inline;')][contains(@style,'z-index: 1010;')]/div/div/div[.='Save Bookmark']");
			BookMarkPopDialog=By.xpath("//div[@class='dialogPopup'][contains(@id,'bookmarkControl')]");
		}
		mouseOverElementRobotClick(TestViewer.findElement(TestViewerMenu));
		robotWaitMS(200);
		TestViewer=driver.findElement(WorkViewer);
		driver.findElement(sWorkLabel).click();
		waitElementClickableReady(saveBookmark);
		driver.findElement(saveBookmark).click();
		robotWaitMS(100);
		waitElementVisible(BookMarkPopDialog);
		waitElementVisible(BookMarkNameField);
		robotWaitMS(100);
		mouseOverElementRobotClick(driver.findElement(BookMarkNameField));
		robotWaitMS(100);
		clickElementWithNoWait(driver.findElement(BookMarkNameField));
		SetElementValue(driver.findElement(BookMarkNameField),sName);
		robotWaitMS(200);
		driver.findElement(BookMarkPopOKButton).click();
		robotWaitMS(500);
	}
	
	
	
	public static WebElement ExpandDir(String[] DirTree){
		int iLevel=DirTree.length,iLoop=0, iSubLoop=0;
		By oTabOne=By.xpath("//div[@id='controlsCanvas_tabOneTitle']");
		WebElement TargetElement = null;
		String slastDirName="";
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		if(isElementPresent(oTabOne)){
			String tabOneStyle=driver.findElement(oTabOne).getAttribute("style");			
			if(tabOneStyle.contains("rgb(64, 64, 64)")){
				driver.findElement(oTabOne).click();
				PageLoadWait.GenericWaitLoad();
			}
		}
		if(iLevel>0){
			for(String sDirName : DirTree){
				if(iLoop<iLevel-1){
					By oDirName= By.xpath("//div/div[.='" + sDirName + "   ']");
					By oDirName1= By.xpath("//div/div[.='" + DirTree[iLoop+1] + "   ']");
					waitElementVisibility(oDirName);
					waitElementClickableReady(oDirName);
					driver.findElement(oDirName).click();
					PageLoadWait.GenericWaitLoad();
					PageLoadWait.GenericWaitLoad();
					iSubLoop=0;
					while (!waitElementVisibility(oDirName1,3)){
						driver.findElement(oDirName).click();
						PageLoadWait.GenericWaitLoad();
						PageLoadWait.GenericWaitLoad();
						iSubLoop++;
						if(iSubLoop>9){
							if(slastDirName.length()>1){
								slastDirName=">>>'" + slastDirName + "'>>>'";
							} else {
								slastDirName=">>>'";
							}
							Assert.fail("Directory name " + slastDirName + sDirName + "'>>>'" + DirTree[iLoop+1] + "' not found.");
						}
					};	
					slastDirName=sDirName;
					iLoop++;
				} else{
					if(sDirName.length()==0){
						Assert.fail("Clip name is null, invalid.");
					}
					By oCamName=By.xpath("//div/div[.='" + slastDirName + "   ']/../../../../ul//div[.='" + sDirName + "   ']");
					if(ElementExists(oCamName)){
						waitElementVisibility(oCamName);
						TargetElement=driver.findElement(oCamName);
						return TargetElement;
					} else {
						Assert.fail("Clip with name '" + sDirName + "' not found.");
					}
				}
			}
		}
		return TargetElement;
	}
	
	
	public static void deleteBookmarkClip(String sUserName, Stack<String> sWindowHandlers,String[] DirTree, boolean bClip){
		String sWorkLabel;
		if(bClip){
			sWorkLabel="Delete Clip";
		} else {
			sWorkLabel="Delete Bookmark";
		}		
		By oDeleteBookMark=By.xpath("//div[@class='ctrlPopupMenu'][contains(@style,'display: block')]//a[contains(.,'" + sWorkLabel + "')]");
		WebElement oMyClip;
		try {
			if(sUserName.equalsIgnoreCase("admin")){
				login(ADMIN_USERNAME, ADMIN_PASSWORD,sWindowHandlers);
			} else {
				login(sUserName, sUserName+1 ,sWindowHandlers);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		oMyClip=ExpandDir(DirTree);
		oMyClip.click();
		ElementRightClick(oMyClip);
		waitElementVisibility(oDeleteBookMark);		
		driver.findElement(oDeleteBookMark).click();
		selectOKAlert();
		robotWaitMS(200);		
	}
	
	
	public static void buildCheck(String sWorkBuild, String JIRANumber){
		readWriteINI<String> iniObj7=new readWriteINI<String>("BuildInfo","BuildString");
		String buildString=iniObj7.ini.get(iniObj7.Category,iniObj7.Key,String.class);
		String BuildNumber=buildString.replaceAll("\\D+","");
		BigInteger lBuildNumber=new BigInteger(BuildNumber);
		BigInteger buildChange=new BigInteger(sWorkBuild);
		if(lBuildNumber.compareTo(buildChange)>0){
			Assert.fail("Automation script broke on current build '" + lBuildNumber + "'. See " + JIRANumber + " for detail");			
		}
	}
	
	public static void deleteBookmarkClip(String[] DirTree, boolean bClip){
		String sWorkLabel;
		if(bClip){
			sWorkLabel="Delete Clip";
		} else {
			sWorkLabel="Delete Bookmark";
		}		
		By oDeleteBookMark=By.xpath("//div[@class='ctrlPopupMenu'][contains(@style,'display: block')]//a[contains(.,'" + sWorkLabel + "')]");
		WebElement oMyClip;
		oMyClip=ExpandDir(DirTree);
		oMyClip.click();
		ElementRightClick(oMyClip);
		waitElementVisibility(oDeleteBookMark);		
		driver.findElement(oDeleteBookMark).click();
		selectOKAlert();
		robotWaitMS(200);		
	}
	

	public static void moveMarker(String sMapType,String sMyDeviceName, BigDecimal Lat, BigDecimal Lon, int level, boolean onWorld){
		LocEditor MyLoc = new LocEditor();
		MyLoc.SetConnectorMap(sMapType);
		MyLoc.SetConnectorName(sMyDeviceName);
		MyLoc.SearchConnector();
		MyLoc.SetNewLatLonProperty(Lat, Lon, 0);		
		MyLoc.MoveToNewLatLon(onWorld);
	}

	
	
//	 public static boolean isFileLocked(File file) { 
//		  if (!file.exists()) { 
//		   return false; 
//		  } 
//		 
//		  if (file.isDirectory()) { 
//		   return false; 
//		  } 
//		 
//		  if (isSymlink(file)) { 
//		   return false; 
//		  } 
//		 
//		  RandomAccessFile randomAccessFile = null; 
//		  boolean fileLocked = false; 
//		 
//		  try { 
//		   // Test 1 missing permissions or locked file parts. If File is not readable == locked 
//		   randomAccessFile = new RandomAccessFile(file, "r"); 
//		   randomAccessFile.close(); 
//		  } 
//		  catch (Exception e) { 
//		   fileLocked = true; 
//		  } 
//		 
//		  if (!fileLocked && file.canWrite()) { 
//		   try { 
//		    // Test 2:Locked file parts 
//		    randomAccessFile = new RandomAccessFile(file, "rw"); 
//		 
//		    // Test 3: Set lock and release it again 
//		    FileLock fileLock = randomAccessFile.getChannel().tryLock(); 
//		 
//		    if (fileLock == null) { 
//		     fileLocked = true; 
//		    } 
//		    else { 
//		     try { 
//		      fileLock.release(); 
//		     } 
//		     catch (Exception e) { /* Nothing */ 
//		     } 
//		    } 
//		   } 
//		   catch (Exception e) { 
//		    fileLocked = true; 
//		   } 
//		 
//		   if (randomAccessFile != null) { 
//		    try { 
//		     randomAccessFile.close(); 
//		    } 
//		    catch (IOException e) { /* Nothing */ 
//		    } 
//		   } 
//		  } 
//		 
//		  return fileLocked; 
//		 } 
//	 public static boolean isSymlink(File file) { 
//		  return Files.isSymbolicLink(Paths.get(file.getAbsolutePath())); 
//		 } 

}
