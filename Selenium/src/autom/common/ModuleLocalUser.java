package autom.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import autom.common.MasterTest;
import autom.common.MyPrint;
import autom.common.PageLoadWait;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class ModuleLocalUser extends MasterTest {
	public String localUserName;
	public String localUserPass1;
	public String localUserPass2;
	private String UserGroup;
	private String AdmUserGroup;
	private String myTag1="ClientButton";
	private String myTag2="Access Rights";
	private String myTag3="Local Users";
	
	public Map<String , String> objLocalUserPropertytoUse;
	public Map<String , String> findMyLocalUserElementMethod;
	
    public ModuleLocalUser(String sLocalUserName,String spass1,String spass2) {
    	MyPrint.myPrint("Local Users","Local User with name '" + sLocalUserName + "' has been defined.");
    	localUserName=sLocalUserName;
    	localUserPass1=spass1;
    	localUserPass2=spass2;
    	ObjectPropertyMapping();
    	OpenLocalUser();
	}

    public ModuleLocalUser(Map<String , String> myLocalUser) {
    	MyPrint.myPrint("Local Users","Local User with name '" + myLocalUser.get("Login ID") + "' will be created.");
    	ObjectPropertyMapping();
    	OpenLocalUser();
    	
    	localUserName=myLocalUser.get("Login ID");
    	localUserPass1=myLocalUser.get("* Password");
    	localUserPass2=myLocalUser.get("Reenter Password");    	
	}
    
    public ModuleLocalUser(Map<String , String> myLocalUser, String sUserGroup) {
    	LocalUser(myLocalUser,sUserGroup);
	}
    
    public ModuleLocalUser(Map<String , String> myLocalUser, String sUserGroup,String sAdmUserGroup) {
    	if(sAdmUserGroup!=null){
    		if(sAdmUserGroup.length()>0){
    			AdmUserGroup=sAdmUserGroup; 
    		}
    	}
    	LocalUser(myLocalUser,sUserGroup);
	}
    
    private void LocalUser(Map<String, String> myLocalUser, String sUserGroup) {
    	UserGroup=sUserGroup;
    	MyPrint.myPrint("Local Users","Local User with name '" + myLocalUser.get("Login ID") + "' will be created.");
    	ObjectPropertyMapping();
    	OpenLocalUser();
    	localUserName=myLocalUser.get("Login ID");
    	localUserPass1=myLocalUser.get("* Password");
    	localUserPass2=myLocalUser.get("Reenter Password");
	}

	public void ObjectPropertyMapping() {
    	Map<String , String> myLocalUser = new HashMap<String, String>();
    	Map<String , String> findMyLocalUserElementMethod = new HashMap<String, String>();
    	

    	myLocalUser.put("Add_button", "xpath");
    	findMyLocalUserElementMethod.put("Add_button", "//ul/li/div[@class='buttonFactory'][@id='addButton']");
    	myLocalUser.put("User Group Access Table", "xpath");
    	findMyLocalUserElementMethod.put("User Group Access Table", "//table[@id='userGroupAccessTable']");    	
    	myLocalUser.put("Create", "xpath");
    	findMyLocalUserElementMethod.put("Create", "//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
    	myLocalUser.put("Login ID", "id");
    	findMyLocalUserElementMethod.put("Login ID", "UserLoginId");
    	myLocalUser.put("Title/Position", "xpath");
    	findMyLocalUserElementMethod.put("Title/Position", "//div/label[contains(.,'Title/Position')]/following::input");
    	myLocalUser.put("Full Name", "xpath");
    	findMyLocalUserElementMethod.put("Full Name", "//div/label[contains(.,'Full Name')]/following::input");
    	myLocalUser.put("Supervisor", "xpath");
    	findMyLocalUserElementMethod.put("Supervisor", "//div/label[contains(.,'Supervisor')]/following::input");
    	myLocalUser.put("* Password", "xpath");
    	findMyLocalUserElementMethod.put("* Password", "//div/label[contains(.,'* Password')]/following::input");
    	myLocalUser.put("Reenter Password", "xpath");
    	findMyLocalUserElementMethod.put("Reenter Password", "//div/label[contains(.,'Reenter Password')]/following::input");
    	myLocalUser.put("Primary Phone", "xpath");
    	findMyLocalUserElementMethod.put("Primary Phone", "//div/label[contains(.,'Primary Phone')]/following::input");
    	myLocalUser.put("Email Address", "xpath");
    	findMyLocalUserElementMethod.put("Email Address", "//div/label[contains(.,'Email Address')]/following::input");
    	myLocalUser.put("Secondary Phone", "xpath");
    	findMyLocalUserElementMethod.put("Secondary Phone", "//div/label[contains(.,'Secondary Phone')]/following::input");
    	myLocalUser.put("Instant Messenger", "xpath");
    	findMyLocalUserElementMethod.put("Instant Messenger", "//div/label[contains(.,'Instant Messenger')]/following::input");
    	myLocalUser.put("Owner", "id");
    	findMyLocalUserElementMethod.put("Owner", "groupOwner");
    	myLocalUser.put("UG1", "xpath");
    	findMyLocalUserElementMethod.put("UG1", "//td/input[@class='checkbox'][@value='UG1']");
    	myLocalUser.put("UG2", "xpath");
    	findMyLocalUserElementMethod.put("UG2", "//td/input[@class='checkbox'][@value='UG2']");    	
    	myLocalUser.put("TC19UserGroup", "xpath");
    	findMyLocalUserElementMethod.put("TC19UserGroup", "//td/input[@class='checkbox'][@value='TC19UserGroup']");
    	myLocalUser.put("TC20UserGroup", "xpath");
    	findMyLocalUserElementMethod.put("TC20UserGroup", "//td/input[@class='checkbox'][@value='TC20UserGroup']");
    	myLocalUser.put("TC21UserGroup", "xpath");
    	findMyLocalUserElementMethod.put("TC21UserGroup", "//td/input[@class='checkbox'][@value='TC21UserGroup']");
    	myLocalUser.put("TC22UserGroup", "xpath");
    	findMyLocalUserElementMethod.put("TC22UserGroup", "//td/input[@class='checkbox'][@value='TC22UserGroup']");
    	myLocalUser.put("TC23UserGroup", "xpath");
    	findMyLocalUserElementMethod.put("TC23UserGroup", "//td/input[@class='checkbox'][@value='TC23UserGroup']");
    	if(UserGroup!=null){
        	myLocalUser.put(UserGroup, "xpath");
        	findMyLocalUserElementMethod.put(UserGroup, "//td/input[@class='checkbox'][@value='" + UserGroup + "']");    		
    	}
    	if(AdmUserGroup!=null){
    		if(AdmUserGroup.length()>0){
            	myLocalUser.put(AdmUserGroup, "xpath");
            	findMyLocalUserElementMethod.put(AdmUserGroup, "//td/input[@class='checkbox'][@value='" + AdmUserGroup + "']");    		    			
    		}
    	}
    	myLocalUser.put("Owner", "xpath");
    	findMyLocalUserElementMethod.put("Owner", "//div/label[contains(.,'Owner')]/following::select");
    	myLocalUser.put("Save_button", "xpath");
    	findMyLocalUserElementMethod.put("Save_button", "//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
    	this.findMyLocalUserElementMethod=findMyLocalUserElementMethod;
    	this.objLocalUserPropertytoUse=myLocalUser;
    }
    
    public void Nav2LocalUser() {
		MyPrint.myPrint("User Group","Navigate to Local User screen");
		Navigate();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
    }
    
    
    public ModuleLocalUser() {
    	MyPrint.myPrint("Local Users","Default Local Users constructor is used without defining any Local Users name. Navigate to Local Users.");
    	PageLoadWait.GenericWaitLoad();
    	ObjectPropertyMapping();
    	OpenLocalUser();
	}    
    
    public void OpenLocalUser() {
		 Navigate();
    }
	
    public void AddNewGroup(String dfdf)
    {
    	TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");    	
    	TestActions.clickElementWithNoWait("id","logout");
    	TestActions.selectOKAlert();
    }

    public void AddNewLocalUser()
    {
		MyPrint.myPrint("Local Users","add new Local Users with name '" + localUserName + "'");
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addButton']")));
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		PageLoadWait.GenericWaitLoad();
		if (driver.findElements(By.xpath("//a[contains(text(),'" + localUserName + "')]")).size()!=0) {
			DeleteLocalUser();
		}
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		TestActions.waitElementVisibility("name","Login");
		TestActions.waitElementVisibility("xpath","//table[@id='userGroupAccessTable']");
		TestActions.waitElementVisibility("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
		TestActions.ElementSendKeys(objLocalUserPropertytoUse.get("Login ID"), findMyLocalUserElementMethod.get("Login ID"), localUserName);
		TestActions.ElementSendKeys(objLocalUserPropertytoUse.get("* Password"), findMyLocalUserElementMethod.get("* Password"), localUserPass1);
    	TestActions.ElementSendKeys(objLocalUserPropertytoUse.get("Reenter Password"), findMyLocalUserElementMethod.get("Reenter Password"), localUserPass2);
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + localUserName + "')]")));
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + localUserName + "')]")).size()!=0);
    }

    
    public void AddNewLocalUser(LinkedHashMap<String , String>myLocalUser)
    {
		MyPrint.myPrint("Local Users","add new Local Users with name '" + localUserName + "'");
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addButton']")));
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		
		if (driver.findElements(By.xpath("//a[text()='" + localUserName + "']")).size()!=0) {
			MyPrint.myPrint("Local Users","Local Users with name '" + localUserName + "' exists. Delete it now and then add it again.");
			DeleteLocalUser(myLocalUser);
			TestActions.robotWait(1);
			PageLoadWait.GenericWaitLoad();
			TestActions.robotWait(1);
			PageLoadWait.GenericWaitLoad();
		}
		Set<Entry<String, String>>set = myLocalUser.entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
	    while(iterator.hasNext()) {
            Map.Entry<String, String> me = (Map.Entry<String, String>)iterator.next();
//          System.out.print("Key is: "+ me.getKey() + " & Value is: "+ me.getValue()+"\n");
            
            if (this.objLocalUserPropertytoUse.get(me.getKey())==null){
            } else {
            	if ((me.getValue()).toString().length()>0) {

            		switch ((me.getValue()).toString()) {
            		case "action_click":
            			TestActions.clickElementWithNoWait(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            			break;
            		case "action_select":
            			TestActions.ElementSelect(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            			break;
            		case "action_listselect":
            			WebElement myDropDown=TestActions.getElement(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            			TestActions.ElementSelect(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            			TestActions.clickElementWithNoWait(myDropDown);
            			myDropDown.findElement(By.xpath("./option[@value='" + myLocalUser.get(me.getKey()) + "']")).click();
            			PageLoadWait.GenericWaitLoad();
            			break;            			
            		case "action_clickwait":
            			TestActions.clickElementAndWaitReady(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            			break;
            		case "wait_visible":
            			TestActions.waitElementVisibility(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            			break;
            		default:
            			String sTestTag=(TestActions.getElement(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()))).getTagName().toLowerCase();
            			switch (sTestTag){
            			case "select":
            				WebElement oDropDown=TestActions.getElement(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            				TestActions.ElementSelect(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            				TestActions.clickElementWithNoWait(oDropDown);
            				if(!myLocalUser.get(me.getKey()).contains("AllUsers")){
                				oDropDown.findElement(By.xpath("./option[@value='" + myLocalUser.get(me.getKey()) + "']")).click();
                				PageLoadWait.GenericWaitLoad();            					
            				}
//            				oDropDown.findElement(By.xpath("./option[@value='" + myLocalUser.get(me.getKey()) + "']")).click();
//            				PageLoadWait.GenericWaitLoad();            				
            				break;            				
            			default:
            				TestActions.robotWait(1);
            				
            				if(me.getKey().toLowerCase().contains("password")){
            					TestActions.robotWait(1);
            					TestActions.clickElementWithNoWait(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()));
            					TestActions.robotWaitMS(200);
            				}
            				TestActions.ElementSendKeys(this.objLocalUserPropertytoUse.get(me.getKey()), this.findMyLocalUserElementMethod.get(me.getKey()), myLocalUser.get(me.getKey()));
            				break;
            			}
            		}
            	}
            }
	    }
	    TestActions.robotWait(1);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + localUserName + "')]")));
	    Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + localUserName + "')]")).size()!=0);
	    MyPrint.myPrint("Local Users","Local Users with name '" + localUserName + "' has been scuccessfully created.");
    }
    public void DeleteLocalUser()
    {
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.robotWaitMS(800);
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Users')]");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[text()='" + localUserName + "']")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		driver.findElement(By.xpath("//a[text()='" + localUserName + "']")).click();
		TestActions.clickElementAndWaitReady("xpath","//a[text()='" + localUserName + "']");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory delete'][@id='deleteButton']");
	    TestActions.clickElementWithNoWait("xpath","//ul/li/div[@class='buttonFactory delete'][@id='deleteButton']");
	    TestActions.selectOKAlert();
		TestActions.robotWaitMS(200);
		PageLoadWait.GenericWaitLoad();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	    
	    PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(false,driver.findElements(By.xpath("//a[text()='" + localUserName + "']")).size()!=0);
		MyPrint.myPrint("***","Local Users with name '" + localUserName + "' has been deleted successfully.");
    }

    
    public void DeleteLocalUser(LinkedHashMap<String , String>myLocalUser)
    {
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.robotWaitMS(800);
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Users')]");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		
		longWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + myLocalUser.get("Login ID") + "']")));
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[text()='" + myLocalUser.get("Login ID") + "']")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		driver.findElement(By.xpath("//a[text()='" + myLocalUser.get("Login ID") + "']")).click();
		TestActions.clickElementAndWaitReady("xpath","//a[text()='" + myLocalUser.get("Login ID") + "']");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory delete'][@id='deleteButton']");
	    TestActions.clickElementWithNoWait("xpath","//ul/li/div[@class='buttonFactory delete'][@id='deleteButton']");
	    TestActions.selectOKAlert();
		PageLoadWait.GenericWaitLoad();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		Assert.assertEquals(false,driver.findElements(By.xpath("//a[text()='" + myLocalUser.get("Login ID") + "']")).size()!=0);
		MyPrint.myPrint("***","Local Users with name '" + myLocalUser.get("Login ID") + "' has been deleted successfully.");
    }
    
    public void AddPrevilegeGroup(String Previlege)
    {
		MyPrint.myPrint("Local Users","add Privilege '"+ Previlege +"' to Local Users '" + localUserName + "'");
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.robotWaitMS(800);
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Users')]");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[text()='" + localUserName + "']")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//text()='" + localUserName + "']");
		TestActions.clickElementAndWaitReady("link","Privileges");
		Assert.assertEquals(true,driver.findElements(By.xpath("//table[@id='LocalUserPermissionTable']//td[contains(text(),'" + Previlege + "')]")).size()!=0);
		if (!driver.findElement(By.xpath("//table[@id='userGroupPermissionTable']//td[contains(text(),'" + Previlege + "')]/following-sibling::td/input")).isSelected()){
			TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
			TestActions.clickElementAndWaitReady("xpath","//table[@id='userGroupPermissionTable']//td[contains(text(),'" + Previlege + "')]/following-sibling::td/input");
			TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
			TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + localUserName + "')]");
			TestActions.clickElementAndWaitReady("link","Privileges");	
			Assert.assertEquals(true,driver.findElement(By.xpath("//table[@id='userGroupPermissionTable']//td[contains(text(),'" + Previlege + "')]/following-sibling::td/input")).isSelected());
		}
    }
    
    public void Add2UserGroup(String UserGroupName)
    {
		MyPrint.myPrint("Local Users","add this user to User Group '"+ UserGroupName +"' to Local Users '" + localUserName + "'");
		TestActions.robotWaitMS(800);
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Users')]");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWaitMS(800);
		PageLoadWait.GenericWaitLoad();
//		Assert.assertEquals(true,driver.findElements(By.xpath("//a[text()='" + localUserName + "']")).size()!=0);
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[text()='" + localUserName + "']")).size()>0);

		TestActions.robotWaitMS(800);
		TestActions.ElementScrollIntoView(driver.findElement(By.xpath("//a[text()='" + localUserName + "']")));
		TestActions.robotWaitMS(800);
		TestActions.clickElementAndWaitReady("xpath","//a[text()='" + localUserName + "']");
		TestActions.robotWaitMS(800);
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
//		TestActions.clickElementAndWaitReady("xpath","//td/input[@class='checkbox'][@value='" + UserGroupName + "']");
		TestActions.robotWaitMS(1500);
		TestActions.ElementSelect("xpath","//td/input[@class='checkbox'][@value='" + UserGroupName + "']");
		TestActions.robotWaitMS(800);
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='saveCreate']");
//		TestActions.robotWaitMS(800);
		TestActions.robotWaitMS(1800);

		TestActions.clickElementAndWaitReady("xpath","//a[text()='" + localUserName + "']");
		TestActions.robotWaitMS(800);
    }
    
    public void DefineDevicePrivilege(String sRadioDeviceAccessRights)
    {
		MyPrint.myPrint("Local Users","add Device Access Rights with definition '" + sRadioDeviceAccessRights + "' to Local Users '" + localUserName + "'");
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Userss')]");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + localUserName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + localUserName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		TestActions.clickElementAndWaitReady("link","Device Rights");
		String sDeviceAccessLink="Add new device access rights definition";
		TestActions.waitElementVisibility("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		TestActions.clickElementAndWaitReady("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		TestActions.waitElementClickableReady("id","saveNewItem");		
		WebElement w1=driver.findElement(By.xpath("//div[@id='expression-option']"));
		WebElement w2=w1.findElement(By.xpath("..//div[@class='newForm'][contains(.,'"+sRadioDeviceAccessRights+"')]"));
		w2.click();
		TestActions.clickElementAndWaitReady("id","saveNewItem");
//		(driver.findElement(By.id("conditionalExpressionTable"))).findElement(By.xpath("..//td[contains(.,'"+sRadioDeviceAccessRights+"')][1]")).getText()
		TestActions.waitElementClickableReady("xpath","//span[contains(text(),'" + sDeviceAccessLink + "')]");
		PageLoadWait.GenericWaitLoad();
		WebElement w3=driver.findElement(By.id("conditionalExpressionTable"));
		Assert.assertEquals(true,w3.findElements(By.xpath("..//td[contains(.,'"+sRadioDeviceAccessRights+"')][1]")).size()!=0);
		WebElement w5=driver.findElement(By.id("buttonRowDIV"));
		WebElement w6=w5.findElement(By.id("saveCreate"));
		w6.click();
		TestActions.robotWaitMS(300);
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWaitMS(500);
		TestActions.waitElementVisibility("id","editButton");
		Assert.assertEquals(true,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
    }

    public void DefineDevicePrivilege()
    {
		MyPrint.myPrint("Local Users","add Device Access Rights to Local Users '" + localUserName + "'");
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Userss')]");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + localUserName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + localUserName + "')]");
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
    
    public void AddDevicePrivilege(Integer iRowOfWorkingRule,Integer iNumberOfLinkToModify,String sGroupName)
    {
		MyPrint.myPrint("Local Users","Define Device Access group '" + sGroupName + "' for Local Users '" + localUserName + "' to join.");
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Userss')]");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,driver.findElements(By.xpath("//a[contains(text(),'" + localUserName + "')]")).size()!=0);
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + localUserName + "')]");
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
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", w4);
		w4.click();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveItem");
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("id","saveCreate");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
		TestActions.clickElementAndWaitReady("xpath","//a[contains(text(),'" + localUserName + "')]");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
		Assert.assertEquals(false,((JavascriptExecutor)driver).executeScript("return document.getElementById('editButton').disabled;"));
		PageLoadWait.GenericWaitLoad();		
		Assert.assertEquals(true,(driver.findElement(By.id("conditionalExpressionTable"))).findElements(By.xpath("(//td[contains(.,'"+ (sGroupName) +"')])[1]")).size()!=0);
    }

    public void RemoveDeviceDefinition(String sDeviceDefinitionString,Integer iNumberOfDefinition)
    {
		MyPrint.myPrint("Local Users","Remove Device access rights definition '" + sDeviceDefinitionString + "' for Local Users '" + localUserName + "'");
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Userss')]");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[text()='" + localUserName + "']")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//a[contains(text(),'" + localUserName + "')]");
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
//		Assert.assertEquals(false,driver.findElements(By.xpath("//a[contains(text(),'" + LocalUserName + "')]")).size()!=0);	    
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		PageLoadWait.GenericWaitLoad();
    }

    
    public void ChangeDeviceGroupAccess(String sDeviceDefinitionString,Integer iNumberOfDefinition,String sViewControl)
    {
		MyPrint.myPrint("Local Users","Modify Device access rights privileg to '" + sViewControl + "' for Local Users '" + localUserName + "'");
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
		TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Local Userss')]");
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,(driver.findElement(By.xpath("//a[text()='" + localUserName + "']")).isDisplayed()));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait("xpath","//a[text()='" + localUserName + "']");
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='editButton']");
	    PageLoadWait.GenericWaitLoad();
	    Assert.assertEquals(true,(driver.findElement(By.id("conditionalExpressionTable"))).findElements(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]")).size()!=0);
	    WebElement DeleteElement1=driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]"));
	    DeleteElement1.click();
	    
	    PageLoadWait.GenericWaitLoad();
	    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select")).click();
//	    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select"))).selectByValue("View, Control");	    
	    PageLoadWait.GenericWaitLoad();
	    driver.findElement(By.xpath("(//td[contains(.,'"+ (sDeviceDefinitionString) +"')])[" + iNumberOfDefinition + "]/following::td/select/option[@value='" + sViewControl + "']")).click();
	    PageLoadWait.GenericWaitLoad();
	    TestActions.clickElementAndWaitReady("id","saveCreate");
    }

    
    public void VerifyListingUserGroupErrorImage(boolean bExpectResult){
		String sVisible;
		if (bExpectResult)
    		sVisible="Invisible";
    	else
    		sVisible="Visible";
		MyPrint.myPrint("Local Users","Verify Local Users List Error image. The error image icon in front of group name '"+ localUserName +"' is expected to be " + sVisible +".");
		PageLoadWait.GenericWaitLoad();
		if (VerifyListingUserGroupErrorVisible(bExpectResult,"//a[contains(.,'" + localUserName + "')]/span/img")){
			MyPrint.myPrint("Local Users","Verify Local Users List Error image. The error image icon in front of group name '"+ localUserName +"' is expected to be " + sVisible +"==>Verified and passed.");
		} else {
			MyPrint.myPrint("Local Users","Verify Local Users List Error image. The error image icon in front of group name '"+ localUserName +"' is expected to be " + sVisible +"==> failed.");
		}
    }

    public boolean VerifyListingUserGroupErrorVisible(boolean bExpectResult,String sXPath){
		boolean bErrorVisible=true;
		String sWidth;
		if (driver.findElements(By.xpath(sXPath)).size()==0)
			return false||bExpectResult;
		sWidth=driver.findElement(By.xpath(sXPath)).getCssValue("width");
		if (sWidth.length()>0) {
			if (sWidth.equals("0px"))
				bErrorVisible=false;
			if (sWidth.equals("1px"))
				bErrorVisible=false;
			return bExpectResult||bErrorVisible;
		} else {
			return false;
		}
    }
    public void Navigate() {
    	TestActions.Navigation(myTag1,myTag2,myTag3);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addButton']")));
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("userList")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("user-206")));		
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
		TestActions.robotWait(1);
		PageLoadWait.GenericWaitLoad();
    }    
}

