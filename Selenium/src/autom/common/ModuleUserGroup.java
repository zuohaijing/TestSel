package autom.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
public class ModuleUserGroup extends MasterTest {
	public String UserGroupName;
	private String myTag1="ClientButton";
	private String myTag2="Access Rights";
	private String myTag3="User Groups";
	public Map<String, String> accessRitNameValueMap;

    public ModuleUserGroup(String sTestGroup) {
    	MyPrint.myPrint("User Group","User group with name '" + sTestGroup + "' has been defined.");
    	this.UserGroupName=sTestGroup;
    	OpenUserGroup();
	}

    protected void loadAccessRitProperty(){
    	accessRitNameValueMap  = new HashMap<String, String>();
    	accessRitNameValueMap.put("Restricted", "Restricted");
    	accessRitNameValueMap.put("restricted", "Restricted");
    	accessRitNameValueMap.put("ViewOnly", "ViewOnly");
    	accessRitNameValueMap.put("viewonly", "ViewOnly");
    	accessRitNameValueMap.put("View Only", "ViewOnly");
    	accessRitNameValueMap.put("view only", "ViewOnly");
    	accessRitNameValueMap.put("View, Control","ViewControl");
    	accessRitNameValueMap.put("view, control","ViewControl");
    	accessRitNameValueMap.put("View,Control","ViewControl");
    	accessRitNameValueMap.put("view,control","ViewControl");
    	accessRitNameValueMap.put("View Control","ViewControl");
    	accessRitNameValueMap.put("view control","ViewControl");
    	accessRitNameValueMap.put("ViewControl","ViewControl");
    	accessRitNameValueMap.put("viewcontrol","ViewControl");
    	accessRitNameValueMap.put("ViewControlBlock", "ViewControlBlock");
    	accessRitNameValueMap.put("viewcontrolblock", "ViewControlBlock");
    	accessRitNameValueMap.put("View, Control, Block", "ViewControlBlock");
    	accessRitNameValueMap.put("view, control, block", "ViewControlBlock");
    	accessRitNameValueMap.put("View,Control,Block", "ViewControlBlock");
    	accessRitNameValueMap.put("view,control,block", "ViewControlBlock");
    	accessRitNameValueMap.put("View Control Block", "ViewControlBlock");
    	accessRitNameValueMap.put("view control block", "ViewControlBlock");
    	accessRitNameValueMap.put("ViewExport", "ViewExport");
    	accessRitNameValueMap.put("viewexport", "ViewExport");
    	accessRitNameValueMap.put("View, Export", "ViewExport");
    	accessRitNameValueMap.put("view, export", "ViewExport");
    	accessRitNameValueMap.put("View,Export", "ViewExport");
    	accessRitNameValueMap.put("view,export", "ViewExport");
    	accessRitNameValueMap.put("View Export", "ViewExport");
    	accessRitNameValueMap.put("view export", "ViewExport");
    	accessRitNameValueMap.put("ViewControlExport", "ViewControlExport");
    	accessRitNameValueMap.put("viewcontrolexport", "ViewControlExport");
    	accessRitNameValueMap.put("View, Control, Export", "ViewControlExport");
    	accessRitNameValueMap.put("view, control, export", "ViewControlExport");
    	accessRitNameValueMap.put("View,Control,Export", "ViewControlExport");
    	accessRitNameValueMap.put("view,control,export", "ViewControlExport");
    	accessRitNameValueMap.put("View Control Export", "ViewControlExport");
    	accessRitNameValueMap.put("view control export", "ViewControlExport");
    	accessRitNameValueMap.put("ViewControlBlockExport", "ViewControlBlockExport");
    	accessRitNameValueMap.put("viewcontrolblockExport", "ViewControlBlockExport");
    	accessRitNameValueMap.put("View, Control, Block, Export", "ViewControlBlockExport");
    	accessRitNameValueMap.put("view, control, block, export", "ViewControlBlockExport");
    	accessRitNameValueMap.put("View,Control,Block,Export", "ViewControlBlockExport");
    	accessRitNameValueMap.put("view,control,block,export", "ViewControlBlockExport");
    	accessRitNameValueMap.put("View Control Block Export", "ViewControlBlockExport");    	
    	accessRitNameValueMap.put("view control block export", "ViewControlBlockExport");
    }
    
    
    public ModuleUserGroup() {
    	MyPrint.myPrint("User Group","Default user group constructor is used without defining any user group name. Navigate to user group.");
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
    	OpenUserGroup();
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();
	}    
    
    public void OpenUserGroup() {
    	try {
    		loadAccessRitProperty();
			TestActions.LoadGlblData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Nav2UserGroupFromAdmin();
    	PageLoadWait.GenericWaitLoad();
    }

    public void Nav2UserGroupFromAdmin() {
	  	TestActions.Navigation2(myTag1,myTag2,myTag3);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addButton']")));
//		wait.until(isTrue)
//		WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("userGroupList")));	
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("userGroupList")));
//		wait.until(ExpectedConditions.elementToBeClickable(By.id("usergroup--2")));
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
    }
    
    
    public void AddNewGroup(){
		MyPrint.myPrint("User Group","add new User Group with name '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWait(1);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addButton']")));
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWait(1);
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWait(1);
		if (driver.findElements(By.xpath("//a[text()='" + UserGroupName + "']")).size()!=0) {
			DeleteUserGroup();
			PageLoadWait.GenericWaitLoad();
			Nav2UserGroupFromAdmin();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addButton']")));
		}
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		TestActions.robotWait(3);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementVisibility("name","Name");
		TestActions.waitElementVisible(By.id("td-newexpression"));
		TestActions.waitElementVisible(By.xpath("//ul/li/div[@class='buttonFactory'][@id='saveCreate']"));
		TestActions.robotWait(1);
		WebElement GroupName=driver.findElement(By.name("Name"));
		GroupName.click();
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
		GroupName.sendKeys(UserGroupName);
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)                            
				.withTimeout(20, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		if(UserGroupName.length()>20){
			String sCutoffName=UserGroupName.substring(0,20) + "...";
			fWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'" + sCutoffName + "')]")));
			Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + sCutoffName + "')]")).size()!=0);
		} else {
			fWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")));
			Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);			
		}
    }

    public void AddPrevilegeGroup(String Previlege){
		MyPrint.myPrint("User Group","add Privilege '"+ Previlege +"' to User Group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("link","Privileges");
		PageLoadWait.GenericWaitLoad();
//		Assert.assertEquals(true,driver.findElements(By.xpath("//table[@id='userGroupPermissionTable']//td[contains(text(),'" + Previlege + "')]")).size()!=0);
		
		if(Previlege.equalsIgnoreCase("all")){
			TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
			TestActions.robotWait(1);
			if(!(TestActions.getElement("all_permissions")).isSelected()){
				TestActions.getElement("all_permissions").click();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
			}
			TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
			TestActions.robotWait(2);
			PageLoadWait.GenericWaitLoad();
		} else {
			Assert.assertEquals(true,driver.findElements(By.xpath("//table[@id='userGroupPermissionTable']//td[starts-with(.,'" + Previlege + "')]")).size()!=0);
			if (!driver.findElement(By.xpath("//table[@id='userGroupPermissionTable']//td[starts-with(.,'" + Previlege + "')]/following-sibling::td/input")).isSelected()){
				TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
				TestActions.robotWait(1);
				TestActions.clickElementAndWaitReady("xpath","//table[@id='userGroupPermissionTable']//td[starts-with(.,'" + Previlege + "')]/following-sibling::td/input");
				TestActions.robotWait(1);
				TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
				TestActions.robotWait(2);
				PageLoadWait.GenericWaitLoad();
				TestActions.clickElementAndWaitReady("xpath","//a[starts-with(text(),'" + UserGroupName + "')]");
				TestActions.robotWait(1);
				TestActions.clickElementAndWaitReady("link","Privileges");
				TestActions.robotWait(1);
				Assert.assertEquals(true,driver.findElement(By.xpath("//table[@id='userGroupPermissionTable']//td[starts-with(.,'" + Previlege + "')]/following-sibling::td/input")).isSelected());
			} else {
				MyPrint.myPrint("User Group","The privilege '"+ Previlege +"' exists to User Group '" + UserGroupName + "'");
			}
		}
		

    }
    public void AddAdminOwner(String sOwner){
		MyPrint.myPrint("User Group","add admin group owner '"+ sOwner +"' to User Group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		AssertJUnit.assertTrue("Owner drop-down list found.",TestActions.ElementExists("OwnerDropdown"));
		TestActions.clickElementAndWaitReady("OwnerDropdown");
		WebElement oOwnerDropDown=TestActions.getElement("OwnerDropdown");
		TestActions.ElementSelect(oOwnerDropDown);
		TestActions.clickElementWithNoWait(oOwnerDropDown);
		oOwnerDropDown.findElement(By.xpath("./option[@value='" + sOwner + "']")).click();		
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveCreate");
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementVisibility("id","editButton");
		MyPrint.myPrint("User Group","Successfully added admin group '"+ sOwner +"' as owner of User Group '" + UserGroupName + "'");
    }    
    public void DefineDevicePrivilege(String sRadioDeviceAccessRights){
		MyPrint.myPrint("User Group","add Device Access Rights with definition '" + sRadioDeviceAccessRights + "' to User Group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("link","Device Rights");
		String sDeviceAccessLink="Add new device access rights definition";
		TestActions.waitElementVisibility("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		TestActions.clickElementAndWaitReady("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		TestActions.waitElementClickableReady("id","saveNewItem");		
		WebElement w1=driver.findElement(By.xpath("//div[@id='expression-option']"));
//		WebElement w2=w1.findElement(By.xpath("..//div[@class='newForm'][contains(.,'"+sRadioDeviceAccessRights+"')]"));
		WebElement w9=w1.findElement(By.xpath("..//div[@class='newForm'][contains(.,'"+sRadioDeviceAccessRights+"')]/span/input"));
		w9.click();
		TestActions.clickElementAndWaitReady("id","saveNewItem");
		TestActions.waitElementClickableReady("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		PageLoadWait.GenericWaitLoad();
//		(driver.findElement(By.id("conditionalExpressionTable"))).findElement(By.xpath("..//td[contains(.,'"+sRadioDeviceAccessRights+"')][1]")).getText()
		WebElement w3=driver.findElement(By.id("conditionalExpressionTable"));
//		WebElement w4=w3.findElement(By.xpath("..//td[contains(.,'"+sRadioDeviceAccessRights+"')][1]"));
		
		Assert.assertEquals(true,w3.findElements(By.xpath("..//td[contains(.,'"+sRadioDeviceAccessRights+"')][1]")).size()!=0);
		
		
		WebElement w5=driver.findElement(By.id("buttonRowDIV"));
		WebElement w6=w5.findElement(By.id("saveCreate"));
		w6.click();
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementVisibility("id","editButton");
		TestActions.waitElementVisibility("id","addButton");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
    }

    
    public void ModifyAccessRights(String sDeviceDefinitionString,int iNumberOfDefinition,String sViewControl){
		MyPrint.myPrint("User Group","Modify Access Rights to UserGroup '" + UserGroupName + "' to have the access rights '" + sViewControl + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.waitElementClickableReady("id","saveCreate");
		if (driver.findElements(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select")).size()==0){
			MyPrint.myPrint("Modify User Group with the basic device access right.","");
			TestActions.clickElementAndWaitReady("AddNewDeviceAccess");
			PageLoadWait.GenericWaitLoad();
			TestActions.clickElementAndWaitReady("saveNewItem");
			PageLoadWait.GenericWaitLoad();
			driver.findElement(By.xpath("//span[contains(.,'Device Group')]")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			
			PageLoadWait.GenericWaitLoad();
			driver.findElement(By.xpath("//td[contains(.,'All Devices')]")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			TestActions.clickElementAndWaitReady("saveItem");
			PageLoadWait.GenericWaitLoad();
//			TestActions.clickElementAndWaitReady("saveNewItem");
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			Assert.assertEquals(true,driver.findElements(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select")).size()!=0);
		}
	    if(accessRitNameValueMap.get(sViewControl)!=null){
		    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select")).click();	    
		    PageLoadWait.GenericWaitLoad();
		    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select/option[@value='" + accessRitNameValueMap.get(sViewControl) + "']")).click();
		    PageLoadWait.GenericWaitLoad();	    	
	    } else {
	    	Assert.fail("Access right control '" + sViewControl + "' is not found.");
	    }
	    
	    
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementAndWaitReady("id","saveCreate");
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
//		Assert.assertEquals(true,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
		PageLoadWait.GenericWaitLoad();		    
    }
    
    public void DefineDevicePrivilege() {
		MyPrint.myPrint("User Group","add Device Access Rights to User Group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("link","Device Rights");
		String sDeviceAccessLink="Add new device access rights definition";
		TestActions.waitElementVisibility("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		TestActions.clickElementAndWaitReady("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		TestActions.waitElementClickableReady("id","saveNewItem");
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveNewItem");
		PageLoadWait.GenericWaitLoad();
    }
    
    
    
    public void AddDevicePrivilege(Integer iRowOfWorkingRule,Integer iNumberOfLinkToModify,String sGroupName){
		MyPrint.myPrint("User Group","Define Device Access group '" + sGroupName + "' for User Group '" + UserGroupName + "' to join.");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		PageLoadWait.GenericWaitLoad();	
		WebElement w1=driver.findElement(By.id("conditionalExpressionTable"));
		WebElement w2=w1.findElement(By.xpath("..//tr["+ (iRowOfWorkingRule)+"]//span["+ (iNumberOfLinkToModify)+"]"));
		w2.click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		WebElement w3=driver.findElement(By.id("select-option-table"));
		WebElement w4=w3.findElement(By.xpath("..//td[contains(.,'"+ (sGroupName)+"')][1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", w4);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		w4.click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveItem");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveCreate");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		TestActions.waitUntilBooleanCondition("id","editButton","disabled",true);
		TestActions.waitUntilBooleanCondition("id","addButton","disabled",false);
//		Assert.assertEquals(true,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		Assert.assertEquals(false,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
		PageLoadWait.GenericWaitLoad();		
		Assert.assertEquals(true,(driver.findElement(By.id("conditionalExpressionTable"))).findElements(By.xpath("(//td[contains(.,'"+ (sGroupName) +"')])[1]")).size()!=0);
    }
    
    public void AddNewDeviceGroup(String sGroupName){
		MyPrint.myPrint("User Group","Define Device Access group '" + sGroupName + "' for User Group '" + UserGroupName + "' to join.");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		PageLoadWait.GenericWaitLoad();	
		
		
		
		WebElement w1=driver.findElement(By.id("conditionalExpressionTable"));
		WebElement w2=w1.findElement(By.xpath("..//tr//span[contains(text(),'Add new device access rights def')]"));
		w2.click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		WebElement w3=driver.findElement(By.id("select-option-table"));
		WebElement w4=w3.findElement(By.xpath("..//td[contains(.,'"+ (sGroupName)+"')][1]"));
		w4.click();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveNewItem");
		PageLoadWait.GenericWaitLoad();
//		TestActions.clickElementAndWaitReady("id","saveCreate");
//		PageLoadWait.GenericWaitLoad();
//		TestActions.waitUntilBooleanCondition("id","editButton","disabled",true);
//		Assert.assertEquals(true,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
//		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
//		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
//		Assert.assertEquals(false,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
//		PageLoadWait.GenericWaitLoad();		
//		Assert.assertEquals(true,(driver.findElement(By.id("conditionalExpressionTable"))).findElements(By.xpath("(//td[contains(.,'"+ (sGroupName) +"')])[1]")).size()!=0);
    }

    public void SelectDeviceGroup(Integer iRowOfWorkingRule,Integer iNumberOfLinkToModify,String sGroupName){
		MyPrint.myPrint("User Group","Define Device Access group '" + sGroupName + "' for User Group '" + UserGroupName + "' to join.");
		PageLoadWait.GenericWaitLoad();
		WebElement w1=driver.findElement(By.id("conditionalExpressionTable"));
		WebElement w2=w1.findElement(By.xpath("..//tr["+ (iRowOfWorkingRule)+"]//span["+ (iNumberOfLinkToModify)+"]"));
		w2.click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		WebElement w3=driver.findElement(By.id("select-option-table"));
		WebElement w4=w3.findElement(By.xpath("..//td[contains(.,'"+ (sGroupName)+"')][1]"));
		w4.click();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveItem");
    }
    
    
    public void DeleteUserGroup(){
		MyPrint.myPrint("User Group","delete AutomationTest User Group '" + UserGroupName + "'.");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		if(TestActions.ElementExists(By.xpath("//a[text()='" + UserGroupName + "']"))){
//			Assert.assertEquals(true,(driver.findElement(By.xpath("//a[text()='" + UserGroupName + "']")).isDisplayed()));
			TestActions.clickElementWithNoWait("xpath","//a[text()='" + UserGroupName + "']");
			TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteButton']");
		    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteButton']");
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    TestActions.clickElementAndWaitReady("xpath","//button[contains(text(),'Cancel')]");
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
			TestActions.clickElementWithNoWait("xpath","//a[text()='" + UserGroupName + "']");
			TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteButton']");
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    By oDeleteButton=By.xpath("//ul/li/div[@class='buttonFactory'][@id='deleteButton']");
		    TestActions.mouseOverElementClick(oDeleteButton);
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    TestActions.clickElementAndWaitReady("xpath","//button[contains(text(),'Ok')]");
		    TestActions.robotWait(2);
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();
		    TestActions.clickElementIfExists("lockOKButton");
		    TestActions.waitUntilBooleanCondition("id","addButton","disabled",false);
			TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
//			/// below failed in tc35/tc37 --- take it out for now, the delete button remain enabled after deletion
//			TestActions.waitUntilBooleanCondition("id","editButton","disabled",true);
//			Assert.assertEquals(true,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
			TestActions.robotWait(2);
			TestActions.robotWait(2);
			TestActions.robotWait(2);
			Nav2UserGroupFromAdmin();			
			TestActions.robotWait(2);
			Nav2UserGroupFromAdmin();
			TestActions.robotWait(4);
			Assert.assertEquals(false,driver.findElements(By.xpath("//a[text()='" + UserGroupName + "']")).size()!=0);
//			Assert.assertEquals(true,driver.findElements(By.xpath("//a[text()='" + UserGroupName + "']")).size()==0);
		} else {
			MyPrint.myPrint("User Group deleting users group - " + UserGroupName, UserGroupName + " is not a current listing group. skip deleting and go on.");
		}
		
		

    }
    public void RemoveDeviceDefinition(String sDeviceDefinitionString,Integer iNumberOfDefinition) {
		MyPrint.myPrint("User Group","Remove Device access rights definition '" + sDeviceDefinitionString + "' for user group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    PageLoadWait.GenericWaitLoad();
	    
	    Assert.assertEquals(true,(driver.findElement(By.id("conditionalExpressionTable"))).findElements(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]")).size()!=0);
	    WebElement DeleteElement1=driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]"));
	    
	    DeleteElement1.click();
	    PageLoadWait.GenericWaitLoad();
	    WebElement DeleteElement2=DeleteElement1.findElement(By.xpath("..//td[@class='columnDelete']/a"));
	    DeleteElement2.click();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementAndWaitReady("xpath","//button[contains(text(),'Ok')]");
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementAndWaitReady("id","saveCreate");
	    
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		PageLoadWait.GenericWaitLoad();
//		Assert.assertEquals(false,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
    }

    
    public void ChangeDeviceGroupAccess(String sDeviceDefinitionString,Integer iNumberOfDefinition,String sViewControl) {
		MyPrint.myPrint("User Group","Modify Device access rights privileg to '" + sViewControl + "' for user group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    PageLoadWait.GenericWaitLoad();
//	    Assert.assertEquals(true,(driver.findElement(By.id("conditionalExpressionTable"))).findElements(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]")).size()!=0);
	    PageLoadWait.GenericWaitLoad();
	    if(accessRitNameValueMap.get(sViewControl)!=null){
		    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select")).click();	    
		    PageLoadWait.GenericWaitLoad();
		    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select/option[@value='" + accessRitNameValueMap.get(sViewControl) + "']")).click();
		    PageLoadWait.GenericWaitLoad();	    	
	    } else {
	    	Assert.fail("Access right control '" + sViewControl + "' is not found.");
	    }
//	    PageLoadWait.GenericWaitLoad();
//	    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[1]")).click();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementAndWaitReady("id","saveCreate");
	    PageLoadWait.GenericWaitLoad();
    } 
    
    
    
    public void ChangeUsrGrpName(String sNewName) {
		MyPrint.myPrint("User Group","Modify User Group name to '" + sNewName + "' for user group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    PageLoadWait.GenericWaitLoad();
	    
		TestActions.waitElementVisibility("name","Name");
		TestActions.waitElementVisibility("xpath","//td[@id='td-newexpression']");
		TestActions.waitElementVisibility("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
		WebElement GroupName=driver.findElement(By.name("Name"));
		GroupName.click();
		TestActions.SetElementValue(GroupName,sNewName);		
		PageLoadWait.GenericWaitLoad();
		UserGroupName=sNewName;
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")));
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
    } 
    public void VerifyListingUserGroupErrorImage(boolean bExpectResult){
		String sVisible;
		if (bExpectResult)
    		sVisible="Invisible";
    	else
    		sVisible="Visible";
		MyPrint.myPrint("User Group","Verify User Group List Error image. The error image icon in front of group name '"+ UserGroupName +"' is expected to be " + sVisible +".");
		PageLoadWait.GenericWaitLoad();    	
		By imgError=By.xpath("//a[contains(.,'" + UserGroupName + "')]/span/img");
		if (TestActions.VerifyListingUserGroupErrorVisible(bExpectResult,imgError)){
			MyPrint.myPrint("User Group","Verify User Group List Error image. The error image icon in front of group name '"+ UserGroupName +"' is expected to be " + sVisible +"==>Verified and passed.");
		} else {
			MyPrint.myPrint("User Group","Verify User Group List Error image. The error image icon in front of group name '"+ UserGroupName +"' is expected to be " + sVisible +"==> failed.");
		}
    }
    public void RemoveAllUserRights() {
		MyPrint.myPrint("User Group","Remove AllUsers Group all kind of rights/privileges.");
		Nav2UserGroupFromAdmin();
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'AllUsers')]");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		while(TestActions.ElementExists("UserGroupRemoveRightsXLink")){
			TestActions.clickElementAndWaitReady("UserGroupRemoveRightsXLink");
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			if (TestActions.ElementExists("lockOKButton")){
				TestActions.clickElementWithNoWait(TestActions.getElement("lockOKButton"));
			}			
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		}
		TestActions.clickElementAndWaitReady("link","Privileges");		
		if((TestActions.getElement("all_permissions")).isSelected()){
			TestActions.getElement("all_permissions").click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		}
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.getElement("all_permissions").click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();		
		TestActions.getElement("all_permissions").click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();		
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
    }
    
    
public void InterruptedUserGroupModification(Integer iRowOfWorkingRule,Integer iNumberOfLinkToModify,String sGroupName){
	MyPrint.myPrint("User Group","Define Device Access group '" + sGroupName + "' for User Group '" + UserGroupName + "', but move to other Access Right Tab before saving - interrupt testing.");
	PageLoadWait.GenericWaitLoad();
	Nav2UserGroupFromAdmin();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
	TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + UserGroupName + "')]");
	TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	PageLoadWait.GenericWaitLoad();	
	WebElement w1=driver.findElement(By.id("conditionalExpressionTable"));
	WebElement w2=w1.findElement(By.xpath("..//tr["+ (iRowOfWorkingRule)+"]//span["+ (iNumberOfLinkToModify)+"]"));
	w2.click();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	WebElement w3=driver.findElement(By.id("select-option-table"));
	WebElement w4=w3.findElement(By.xpath("..//td[contains(.,'"+ (sGroupName)+"')][1]"));
	w4.click();
	PageLoadWait.GenericWaitLoad();
	TestActions.clickElementAndWaitReady("id","saveItem");
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	MyPrint.myPrint("***","Moving to other Access Rights tab before saving the changes of User Group ***"); 
			
	TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), '" + "Mapping" + "')]");
	TestActions.clickElementWithNoWait("xpath","//div/ul/li/ul/li/a[contains(text(), '"+ "Map Editor" +"')]");
	TestActions.selectCancelAlert();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	MyPrint.myPrint("***","Moving to other Access Rights tab before saving the changes of User Group - Cancel clicked.");
	TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), '" + "Mapping" + "')]");
	TestActions.clickElementWithNoWait("xpath","//div/ul/li/ul/li/a[contains(text(), '"+ "Map Editor" +"')]");
	TestActions.selectOKAlert();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();
	PageLoadWait.GenericWaitLoad();	
    }

    public void InterruptedUserGroupModification(String sDeviceDefinitionString,Integer iNumberOfDefinition,String sViewControl) {
		MyPrint.myPrint("User Group","Modify Device access rights privileg to '" + sViewControl + "' for user group '" + UserGroupName + "'");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    PageLoadWait.GenericWaitLoad();
	    Assert.assertEquals(true,(driver.findElement(By.id("conditionalExpressionTable"))).findElements(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]")).size()!=0);
	    PageLoadWait.GenericWaitLoad();
	    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select")).click();	    
	    PageLoadWait.GenericWaitLoad();
	    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select/option[@value='" + sViewControl + "']")).click();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), '" + "Mapping" + "')]");
	    TestActions.clickElementWithNoWait("xpath","//div/ul/li/ul/li/a[contains(text(), '"+ "Map Editor" +"')]");
		TestActions.selectCancelAlert();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	    TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), '" + "Mapping" + "')]");
	    TestActions.clickElementWithNoWait("xpath","//div/ul/li/ul/li/a[contains(text(), '"+ "Map Editor" +"')]");
		TestActions.selectOKAlert();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();	
    } 
    
    
    public void IndirectInUserGroupCreationCheck(){
		MyPrint.myPrint("User Group","delete AutomationTest User Group '" + UserGroupName + "'.");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		////new added ->
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");

	    PageLoadWait.GenericWaitLoad();
		////new added ->>>
	    PageLoadWait.GenericWaitLoad();
	    
	    TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), '" + "Mapping" + "')]");
	    TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), '"+ "Map Editor" +"')]");
		PageLoadWait.GenericWaitLoad();
	    

	    TestActions.clickElementAndWaitReady("xpath","//button[contains(text(),'Cancel')]");
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteButton']");
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteButton']");
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementAndWaitReady("xpath","//button[contains(text(),'Ok')]");
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementIfExists("lockOKButton");
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.waitUntilBooleanCondition("id","addButton","disabled",false);
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		TestActions.waitUntilBooleanCondition("id","editButton","disabled",true);		
		Assert.assertEquals(false,driver.findElements(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).size()!=0);
    } 
    public void VerifyOwnerOfGroup(String sGroupOwnerName){
		MyPrint.myPrint("User Group","delete AutomationTest User Group '" + UserGroupName + "'.");
		PageLoadWait.GenericWaitLoad();
		Nav2UserGroupFromAdmin();
//		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
//		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'User Groups')]");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[contains(text(),'" + UserGroupName + "')]")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		////new added ->
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + UserGroupName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		WebElement oOwnerDropDown=driver.findElement(By.xpath("//select[@id='owner']"));
//		WebElement oOwnerDropDown=TestActions.getElement("groupOwner");
		Select selectedValue = new Select(oOwnerDropDown);
		String wantedText =selectedValue.getFirstSelectedOption().getText();
		if(sGroupOwnerName!=null){
			Assert.assertEquals(true,wantedText.equalsIgnoreCase(sGroupOwnerName));			
		} else {
			if(wantedText==null){
				Assert.assertEquals(true,true);
			} else {
				Assert.assertEquals(true,false);
			}
		}
        WebElement clientButton=driver.findElement(By.id("cancelButton"));
        Action hover = builder.moveToElement(clientButton).click().build();
        hover.perform();
        PageLoadWait.GenericWaitLoad();
 		wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
 		
 		if(sGroupOwnerName!=null){
 			if(wantedText.length()==0){
 	 			MyPrint.myPrint("Verified that Device Group '" + UserGroupName + "' owner is empty and matches expected","Test passed.");
 			} else {
 				MyPrint.myPrint("Verified that Device Group '" + UserGroupName + "' owner '" + wantedText + "' and matches expected '" + sGroupOwnerName + "'","Test passed.");	
 			}
 		} else {
 			MyPrint.myPrint("Verified that Device Group '" + UserGroupName + "' owner is empty and matches expected","Test passed.");	
 		}
    }

}
