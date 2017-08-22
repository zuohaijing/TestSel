package autom.common;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.io.PrintStream;


public class ModuleCreateSalvo extends MasterTest{
	protected boolean bPrivateSalvoType=false;
	protected boolean bExistingSalvo=false;
	protected boolean bHaveErrorDeleteSalvo=false;
	String SalvoName="";
	private String myTag1="ClientButton";
	private String myTag2="System";
	private String myTag3="Manage Salvos";
	private String SalvoOwner="";
	By oSolvoAdminRow;
	private By oAdminSearchReset=By.linkText("Reset");
	private By oContextDeletePopUp = By.xpath("//div[@class='menuWidgetGlobalDefault'][contains(.,'Delete')]");
//	private By oContextEditPopUp = By.xpath("//div[@class='menuWidgetGlobalDefault'][contains(.,'Edit')]");
	private WebElement oParentSalvoRow;
	public String sNotificationMessage;
	public ModuleCreateSalvo() {
		
	}
	
    public ModuleCreateSalvo(String sSalvoName, boolean bMySalvoType, boolean ResetViewer, boolean RebuildViewers, Map<Integer, String> SalvoDevicePositionMap){
    	String sType;
    	String sImg;
    	if(bMySalvoType){
    		sType="private";
    		sImg="Private Salvo";
    	} else {
    		sType="public";
    		sImg="Public Salvo";
    	}
    	MyPrint.myPrint("Salvo creation","Salvos creation module constructor is going to create '" + sType + "' salvo with name '" + sSalvoName + "'.");
    	bPrivateSalvoType=bMySalvoType;
    	SalvoName=sSalvoName;
    	SalvoOwner=TestActions.getElement("logout").getAttribute("title").replace("'","").replace("Logout User ","");
    	if(ResetViewer){
        	ReSetViewers();    		
    	}
    	if(RebuildViewers){
    		RebuildViewers();
    	}
    	if(driver.findElements(By.xpath("//div[@title='" + sSalvoName + "'][contains(.,'" + sSalvoName + "')]/following::div")).size()==0){
    		MyPrint.myPrint("Salvo creation","Salvos with type '" + sType + "' and with name '" + sSalvoName + "' doesn't exist currently.");
    		setNewSalvoName(sSalvoName);
			TestActions.robotWait(2);
    		if(SetSalvoType(bMySalvoType)){
    			Set<Entry<Integer, String>>set = SalvoDevicePositionMap.entrySet();
    			Iterator<Entry<Integer, String>> iterator = set.iterator();
    		    while(iterator.hasNext()) {
    	            Map.Entry<Integer, String> me = (Map.Entry<Integer, String>)iterator.next();
    	            if(me.getKey()>0){
    	            	if(me.getValue().length()>0){
    	            		TestActions.LoadMyView(me.getValue(),true,false,me.getKey());		
    	            	}
    	            }
    		    }
				TestActions.robotWait(2);
    			TestActions.mouseOverElementClick(By.id("btnSalvo"));
    			try {
			        WebDriverWait wait = new WebDriverWait(driver, 1);
			        wait.until(ExpectedConditions.alertIsPresent());
			        Alert alert = driver.switchTo().alert();
			        alert.accept();
				} catch (Exception  e) {
				
				}
    			By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][contains(.,'is already used. Please choose different name.') or contains(.,'Salvo has been added.') ]");
    			TestActions.waitElementVisibility(oNoMadMessage);
				System.out.println("*** NOMAD message: '" + driver.findElement(oNoMadMessage).getText() +"' ***");
    			TestActions.waitLoaded(driver);
    			TestActions.robotWait(2);
    			By oExistNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][contains(.,'is already used. Please choose different name.')]");
    			By nomadOK=By.id("nomadPrompt_ok");
    			By nomadOKInfo=By.id("nomadPrompt_canvas");
    			if(TestActions.ElementExists(oExistNoMadMessage,false)){
    				System.out.println("*** It is an existing name. Refresh the browser and re-check passed.***");
        			TestActions.robotWait(2);
    			} else if(TestActions.ElementVisible(nomadOK)){
    				System.out.println("*** Message of Nomad Prompt window with OK button:' " + driver.findElement(nomadOKInfo).getText() + " '.***");
    				TestActions.clickElementAndWaitReady(nomadOK);
    				TestActions.robotWait(2);
    			} else if(!salvoListingAssertion(true)){
    				System.out.println("*** Refresh the browser and re-check.***");
    				RebuildViewers();
    				PageLoadWait.GenericWaitLoad();
    				PageLoadWait.GenericWaitLoad();
    				PageLoadWait.GenericWaitLoad();
    				Assert.assertEquals(true,driver.findElements(By.xpath("//div[@title='" + sSalvoName + "'][contains(.,'" + sSalvoName + "')]/following::div")).size()>0);
    				Assert.assertEquals(true,driver.findElement(By.xpath("//div[@title='" + sSalvoName + "'][contains(.,'" + sSalvoName + "')]/following::div[2]/img")).getAttribute("title").equalsIgnoreCase(sImg));
    				System.out.println("*** Refresh the browser and re-check passed.***");
    			} else {
    				System.out.println("*** Successfully created salvo '" + SalvoName + "'  for user " + driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","") + "  ***");
    			}
    		} else {
    			System.out.println("*** Issue in creating salvo '" + SalvoName + "' for user " + driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","") + "  ***" );
    		}
    	} else {
    		System.out.println("*** Salvo with name '" + SalvoName + "' is an existing salvo for user " + driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","") + "  ***" );
    		bExistingSalvo=true;
    	}
	}
    
    
    
    public ModuleCreateSalvo(String sSalvoName, boolean bMySalvoType, boolean ResetViewer, boolean RebuildViewers, Map<Integer, String> SalvoDevicePositionMap,PrintStream os, String sMessage,int[] iReturn){
    	String sType, sImg;

    	if(bMySalvoType){
    		sType="private";
    		sImg="Private Salvo";
    	} else {
    		sType="public";
    		sImg="Public Salvo";
    	}
    	MyPrint.myPrint("Salvo creation","Salvos creation module constructor is going to create '" + sType + "' salvo with name '" + sSalvoName + "'.");
    	bPrivateSalvoType=bMySalvoType;
    	SalvoName=sSalvoName;
    	SalvoOwner=TestActions.getElement("logout").getAttribute("title").replace("'","").replace("Logout User ","");
    	if(ResetViewer){
        	ReSetViewers();
    	}
    	if(RebuildViewers){
    		RebuildViewers();
    	}
    	if(driver.findElements(By.xpath("//div[@title='" + sSalvoName + "'][contains(.,'" + sSalvoName + "')]/following::div")).size()==0){
    		MyPrint.myPrint("Salvo creation","Salvos with type '" + sType + "' and with name '" + sSalvoName + "' doesn't exist currently.");
    		setNewSalvoName(sSalvoName);
    		TestActions.robotWait(2);
    		if(SetSalvoType(bMySalvoType)){
    			Set<Entry<Integer, String>>set = SalvoDevicePositionMap.entrySet();
    			Iterator<Entry<Integer, String>> iterator = set.iterator();
    		    while(iterator.hasNext()) {
    	            Map.Entry<Integer, String> me = (Map.Entry<Integer, String>)iterator.next();
    	            if(me.getKey()>0){
    	            	if(me.getValue().length()>0){
    	            		TestActions.LoadMyView(me.getValue(),true,false,me.getKey());		
    	            	}
    	            }
    		    }
    		    TestActions.robotWait(2);
    			TestActions.mouseOverElementClick(By.id("btnSalvo"));
    			TestActions.robotWaitMS(300);
//    			if(!bMySalvoType){
        			os.println(sMessage.toLowerCase());
        			iReturn[0]=0;
//    			}
    			try {
			        WebDriverWait wait = new WebDriverWait(driver, 1);
			        wait.until(ExpectedConditions.alertIsPresent());
			        Alert alert = driver.switchTo().alert();
			        alert.accept();
				} catch (Exception  e) {
				
				}
    			By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][contains(.,'is already used. Please choose different name.') or contains(.,'Salvo has been added.') ]");
    			TestActions.waitElementVisibility(oNoMadMessage);
				System.out.println("*** NOMAD message: '" + driver.findElement(oNoMadMessage).getText() +"' ***");
    			TestActions.waitLoaded(driver);
    			TestActions.robotWait(2);
    			By oExistNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox'][contains(.,'is already used. Please choose different name.')]");
    			By nomadOK=By.id("nomadPrompt_ok");
    			By nomadOKInfo=By.id("nomadPrompt_canvas");
    			if(TestActions.ElementExists(oExistNoMadMessage,false)){
    				System.out.println("*** It is an existing name. Refresh the browser and re-check passed.***");
        			TestActions.robotWait(2);
    			} else if(TestActions.ElementVisible(nomadOK)){
    				System.out.println("*** Message of Nomad Prompt window with OK button:' " + driver.findElement(nomadOKInfo).getText() + " '.***");
    				TestActions.clickElementAndWaitReady(nomadOK);
    				TestActions.robotWait(2);
    			} else if(!salvoListingAssertion(true)){
    				System.out.println("*** Refresh the browser and re-check.***");
    				RebuildViewers();
    				PageLoadWait.GenericWaitLoad();
    				PageLoadWait.GenericWaitLoad();
    				PageLoadWait.GenericWaitLoad();
    				Assert.assertEquals(true,driver.findElements(By.xpath("//div[@title='" + sSalvoName + "'][contains(.,'" + sSalvoName + "')]/following::div")).size()>0);
    				Assert.assertEquals(true,driver.findElement(By.xpath("//div[@title='" + sSalvoName + "'][contains(.,'" + sSalvoName + "')]/following::div[2]/img")).getAttribute("title").equalsIgnoreCase(sImg));
    				System.out.println("*** Refresh the browser and re-check passed.***");
    			} else {
    				System.out.println("*** Successfully created salvo '" + SalvoName + "'  for user " + driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","") + "  ***");
    			}
    		} else {
    			System.out.println("*** Issue in creating salvo '" + SalvoName + "' for user " + driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","") + "  ***" );
    		}
    	} else {
    		System.out.println("*** Salvo with name '" + SalvoName + "' is an existing salvo for user " + driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","") + "  ***" );
    		bExistingSalvo=true;
    	}
	}
    
    
    public void deleteVidsysSalvo() {
    	MyPrint.myPrint("Salvo","Delete salvo with name '" + SalvoName + "' .");
    	String sNoMadDeleteMessage="Reloaded Salvo Panel as '"+ SalvoName + "' Salvo has been deleted.";
    	By oNoMadDeleteMessage=By.xpath("//div[@class='NOMADStyle'][@id='NOMAD_contentBox'][contains(.,'" + SalvoName + "')]");
    	if(driver.findElements(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]/following::div[contains(@id,'salvoowner')]")).size()>0){
    		driver.findElement(By.xpath("(//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]/following::div[contains(@id,'salvoowner')])[1]")).click();
			TestActions.robotWaitMS(500);
    		TestActions.ElementRobotRightClick(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]/following::div[contains(@id,'salvoowner')]"));
			try {
				if(TestActions.waitForElementToBePresent(oContextDeletePopUp,1000)){
					System.out.println("***" + driver.findElement(oContextDeletePopUp).getCssValue("style"));
					driver.findElement(oContextDeletePopUp).click();
					(wait.until(ExpectedConditions.alertIsPresent())).accept();
					wait.until(ExpectedConditions.visibilityOfElementLocated(oNoMadDeleteMessage));
					Assert.assertEquals(true,(TestActions.waitTilElementVisible(driver.findElement(oNoMadDeleteMessage))));
					Assert.assertEquals(true,driver.findElement(oNoMadDeleteMessage).getText().equalsIgnoreCase(sNoMadDeleteMessage));
					Assert.assertEquals(true,driver.findElement(By.id("NOMAD_contentBox")).getText().equalsIgnoreCase(sNoMadDeleteMessage));
					MyPrint.myPrint("Salvo deletion NOMAD notification message verification","NOMAD notification message verified '" + sNoMadDeleteMessage + "' was verified.");
				}
				if(!TestActions.waitForElementToBePresent(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]/following::div[contains(@id,'salvoowner')]"),1000)){
					MyPrint.myPrint("Salvo deletion","Deleted salvo with name '" + SalvoName + "' successfully.");
				} else {
					MyPrint.myPrint("Salvo deletion","Error on deleting salvo with name '" + SalvoName + "'.");
					bHaveErrorDeleteSalvo=true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				bHaveErrorDeleteSalvo=true;
			}
    	} else {
    		System.out.println("*** Salvo with name '" + SalvoName + "' is not found for user " + driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","") + "  ***" );
    	}
	}
    public boolean salvoListingAssertion(boolean bexist) {
    	MyPrint.myPrint("Check salvo listing status","Expect salvo with name '" + SalvoName + "' exists = '" + bexist + "'");
    	if(TestActions.logicalXOR(driver.findElements(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]/following::div[contains(@id,'salvoowner')]")).size()>0,bexist)){
    		MyPrint.myPrint("***not match","Salvo with name '" + SalvoName + "' expect exists = '" + bexist + "', test result ='" + !bexist + "' ***");
    		return false;
    	} else {
    		MyPrint.myPrint("***match","Salvo with name '" + SalvoName + "' expect exists = '" + bexist + "', test result ='" + bexist + "' ***");
    		return true;
    	}
	}
    
	public ArrayList<String> openExistSalvo(boolean bexist) {
    	ArrayList<String> sMessageList;
    	MyPrint.myPrint("Check salvo listing status","Expect salvo with name '" + SalvoName + "' exists = '" + bexist + "'");
    	if(TestActions.logicalXOR(driver.findElements(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]")).size()>0,bexist)){
    		MyPrint.myPrint("***not match","Salvo with name '" + SalvoName + "' expect exists = '" + bexist + "', test result ='" + !bexist + "' ***");
    		return null;
    	} else {
    		if(driver.findElements(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]")).size()==1){
    			driver.findElements(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]")).get(0).click();
    			WebElement oTestSalvo= driver.findElements(By.xpath("//div[@title='" + SalvoName + "'][contains(.,'" + SalvoName + "')]")).get(0);
    			sMessageList=TestActions.dragDropDevice(1,oTestSalvo);
        		MyPrint.myPrint("***match","Salvo with name '" + SalvoName + "' expect exists = '" + bexist + "', test result ='" + bexist + "' ***");
        		return sMessageList;
    		} else {
    			MyPrint.myPrint("***Not found.","Salvos with name '" + SalvoName + "' expect exists = '" + bexist + "', test result ='false' ***");
    			return null;
    		}
    	}
	}
    
	public void salvoAdmin() {
		NavSalvoAdmin();
	}
	
	public void salvoAdminDelete() {
		salvoAdmin();
	}
	
	public void salvoAdmin(String sSalvoName) {
		adminSetSalvoName(sSalvoName);
		NavSalvoAdmin();
		if(TestActions.ElementExists(oSolvoAdminRow)){
			oParentSalvoRow=driver.findElement(oSolvoAdminRow);
			oParentSalvoRow=oParentSalvoRow.findElement(oSolvoAdminRow);
			if(oParentSalvoRow.findElements(By.xpath("..//input")).size()==2){
				if(oParentSalvoRow.findElements(By.xpath("..//input")).get(0).isSelected()){
						bPrivateSalvoType=true;
				}
			} else {
				System.out.println("*** Salvo Admin with name '" + SalvoName + "' private/public icon disply error. ***");
			}
		} else {
			System.out.println("*** Salvo Admin with name '" + SalvoName + "' not found. ***");
		}
	}
	public void NavSalvoAdmin() {
    	TestActions.Navigation(myTag1,myTag2,myTag3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='viewsalvo']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Apply")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='editsalvo']")));  
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='managesalvo']")));
	    PageLoadWait.GenericWaitLoad();
	}
	
	public void adminSetSalvoType() {
		MyPrint.myPrint("Salvo","Start modifying salvo type for '" + SalvoName + "'.");
		if(TestActions.ElementExists(oSolvoAdminRow)){
			oParentSalvoRow=driver.findElement(oSolvoAdminRow);
			if(oParentSalvoRow.findElements(By.xpath("..//input")).size()==2){
				if(bPrivateSalvoType){
					if(!oParentSalvoRow.findElements(By.xpath("..//input")).get(0).isSelected()) {
						oParentSalvoRow.findElements(By.xpath("..//input")).get(0).click();
						TestActions.selectOKAlert();
						MyPrint.myPrint("Salvo","Modified salvo '" + SalvoName + "' to Private type successfully.");
					}
				} else {
					if(!oParentSalvoRow.findElements(By.xpath("..//input")).get(1).isSelected()) {
						oParentSalvoRow.findElements(By.xpath("..//input")).get(1).click();
						TestActions.selectOKAlert();
						MyPrint.myPrint("Salvo","Modified salvo '" + SalvoName + "' to Public type successfully.");
					}
				}
			} else {
				System.out.println("*** Salvo Admin with name '" + SalvoName + "' private/public icon disply error. ***");
			}
				
		} else {
			System.out.println("*** Salvo Admin with name '" + SalvoName + "' is missing. ***");
		}
	}
	public void adminEditSalvo(String sNewName,boolean bNewTypePrivate) {
		oParentSalvoRow.click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		driver.findElement(By.id("editsalvo")).click();
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("salvoNameForEdit")));
		if(sNewName.length()>0){
			driver.findElement(By.id("salvoNameForEdit")).click();
			driver.findElement(By.id("salvoNameForEdit")).clear();
			driver.findElement(By.id("salvoNameForEdit")).sendKeys(sNewName);
			adminSetSalvoName(sNewName);
		}

		MyPrint.myPrint("Salvo","Start modifying salvo type for '" + SalvoName + "'.");
		By oPrivateInput=By.id("privateSalvoType");
		By oPublicInput=By.id("publicSalvoType");
		By oSaveAdminSalvoEdit=By.id("saveCreate");
		boolean bReset=false;
		if(TestActions.logicalXOR(bPrivateSalvoType,bNewTypePrivate)){
			if(bNewTypePrivate){
				bPrivateSalvoType=true;
			} else {
				bPrivateSalvoType=false;
			}
			bReset=true;
		}
		if(bReset){
			if(driver.findElement(oPrivateInput).isSelected()) {
				driver.findElement(oPublicInput).click();
				PageLoadWait.GenericWaitLoad();
				MyPrint.myPrint("Salvo","Modified salvo '" + SalvoName + "' to public type successfully.");
			} else {
				driver.findElement(oPrivateInput).click();
    		    PageLoadWait.GenericWaitLoad();
				MyPrint.myPrint("Salvo","Modified salvo '" + SalvoName + "' to Private type successfully.");
			}
		} else {
			MyPrint.myPrint("Salvo","Salvo '" + SalvoName + "' keeps the original type without changing.");
		}
		driver.findElement(oSaveAdminSalvoEdit).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='viewsalvo']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Apply")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='editsalvo']")));  
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='managesalvo']")));
		Assert.assertEquals(true,TestActions.ElementExists(oSolvoAdminRow));
	}
	public void adminSetSalvoName(String sName) {
		SalvoName=sName;
	}
	public void adminSetSalvoNameOwner(String sName, String owner) {
		MyPrint.myPrint("Salvo","Set working salvo as name '" + sName + "' and owner '" + owner + "'.");
		adminSetSalvoName(sName);
		SalvoOwner=owner;
		if(TestActions.ElementVisible(oAdminSearchReset)){
			TestActions.clickElementAndWaitReady(oAdminSearchReset);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		} else {
			MyPrint.myPrint("Open salvo listing page:","Expecting to open the salvo listing page.");
			Assert.fail("***Open salvo listing page:Failed - reset button not found.");
			return;
			
		}
		oSolvoAdminRow=By.xpath("//tr[contains(.,'" + SalvoName + "')][contains(.,'" + SalvoOwner + "')]");
		if(TestActions.ElementExists(oSolvoAdminRow)){
			TestActions.mouseOverElementClick(oSolvoAdminRow);
			oParentSalvoRow=driver.findElement(oSolvoAdminRow);
		}
	}
	public void adminSetSalvoPrivate(boolean bType) {
		if(TestActions.logicalXOR(bPrivateSalvoType,bType)){
			if(bType){
				bPrivateSalvoType=true;
			} else {
				bPrivateSalvoType=false;
			}
			adminSetSalvoType();
		}
	}
	public void adminSalvoTypeSwitch() {
		By nomadOK=By.id("nomadPrompt_ok");
		if (SalvoOwner.length()==0){
			SalvoOwner=driver.findElement(oSolvoAdminRow).findElement(By.xpath(".//td[2]")).getText();			
		}
		oParentSalvoRow=driver.findElement(oSolvoAdminRow);
		if(oParentSalvoRow.findElement(By.xpath(".//input")).isSelected()){

			TestActions.mouseOverElementRobotClick(oParentSalvoRow.findElement(By.xpath("(.//input)[2]")));

		} else {
			TestActions.mouseOverElementRobotClick(oParentSalvoRow.findElement(By.xpath(".//input")));
		}			
		TestActions.selectOKAlert();
		oParentSalvoRow=driver.findElement(oSolvoAdminRow);
		if(TestActions.ElementExists(nomadOK)){
			if(TestActions.ElementVisible(nomadOK)){
				TestActions.clickElementAndWaitReady(nomadOK);
				if(bPrivateSalvoType){
					bPrivateSalvoType=false;
				} else {
					bPrivateSalvoType=true;
				}
				MyPrint.myPrint("*** The salvo with name " + SalvoName + " of owner " + SalvoOwner + " is in bad status. ***","Test Failed.");
				Assert.fail("*** The salvo with name " + SalvoName + " of owner " + SalvoOwner + " is in bad status. ***");
				return;
			} else {
				MyPrint.myPrint("*** The salvo with name " + SalvoName + " of owner " + SalvoOwner + " is in bad status. ***","Test Failed.");
				Assert.fail("*** The salvo with name " + SalvoName + " of owner " + SalvoOwner + " is in bad status. ***");
			}
			MyPrint.myPrint("*** The salvo with name " + SalvoName + " of owner " + SalvoOwner + " is in bad status. ***","Test Failed.");
			Assert.fail("*** The salvo with name " + SalvoName + " of owner " + SalvoOwner + " is in bad status. ***");
			return;
		}
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		if(bPrivateSalvoType){
			Assert.assertEquals(oParentSalvoRow.findElement(By.xpath(".//input")).isSelected(),false);
			bPrivateSalvoType=false;
			MyPrint.myPrint("Change solve type:","Salvo type for '" + SalvoName + "' was successffully changed from Private to Public");
		} else {
			Assert.assertEquals(oParentSalvoRow.findElement(By.xpath("(.//input)[2]")).isSelected(),false);
			bPrivateSalvoType=true;
			MyPrint.myPrint("Change solve type:","Salvo type for '" + SalvoName + "' was successffully changed from Public to Private");
		}
	}

	public void adminSalvoDelete() {
		MyPrint.myPrint("Deleting solve:","Deleting salvo with name '" + SalvoName + "' of owner '" + SalvoOwner + "' started.");
		By nomadOK=By.id("nomadPrompt_ok");
		if(TestActions.ElementExists(oSolvoAdminRow)){
			WebElement oTrashCan=driver.findElement(oSolvoAdminRow).findElement(By.xpath(".//td/img"));
			if(TestActions.ElementExists(oTrashCan)){
				oTrashCan.click();
				TestActions.selectOKAlert();
				if(TestActions.ElementVisible(nomadOK)){
					if(TestActions.ElementExists(nomadOK)){
						TestActions.clickElementAndWaitReady(nomadOK);
						Assert.fail("*** The salvo with name " + SalvoName + " of owner " + SalvoOwner + " is in bad status. ***");
					}
					PageLoadWait.GenericWaitLoad();
					salvoListingAssertion(false);					
				} else{
					PageLoadWait.GenericWaitLoad();
					salvoListingAssertion(false);					
				}
				MyPrint.myPrint("Delete solve:","Salvo with name '" + SalvoName + "' of owner '" + SalvoOwner + "' was successfully deleted.");
				SalvoName="";
				SalvoOwner="";
				oSolvoAdminRow=null;
				oParentSalvoRow=null;
			} else {
				MyPrint.myPrint("Delete solve:","Salvo with name '" + SalvoName + "' not exists.");
			}
		} else {
			MyPrint.myPrint("Delete solve:","Salvo with name '" + SalvoName + "' of owner '" + SalvoOwner + "' not exists.");
		}
	}


	public void adminSalvoSearch(boolean expectExists,String sSearch) {
		SalvoOwner="";
		oSolvoAdminRow=By.xpath("//tr[contains(.,'" + sSearch + "')]");
		oParentSalvoRow=null;
		if(TestActions.ElementVisible(oAdminSearchReset)){
			TestActions.clickElementAndWaitReady(oAdminSearchReset);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
		} else {
			MyPrint.myPrint("Search solve with keyword '" + sSearch + "':","Failed. Expecting to open the search page and reset first.");
			Assert.fail("***Search solve with keyword '" + sSearch + "':Failed - reset button not found.");
			return;
		}
		TestActions.SetElementValue(TestActions.getElementLocator("searchSalvoField"),sSearch);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();		
		
		TestActions.clickElementWithNoWait("searchApply");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		try{
			boolean bTestReturnResult=false;
			int iCountSearchResult=driver.findElements(oSolvoAdminRow).size();
			if(iCountSearchResult>0){
				bTestReturnResult=true;
				oParentSalvoRow=driver.findElements(oSolvoAdminRow).get(0);
				MyPrint.myPrint("Search solve with keyword '" + sSearch + "':","Got result(s): " + iCountSearchResult + ". Expecting exists=true, test result=true");
			} else {
				MyPrint.myPrint("Search solve with keyword '" + sSearch + "':","Got no result.");
			}
			if(TestActions.logicalNotXOR(expectExists,bTestReturnResult)){
				MyPrint.myPrint("Search solve with keyword '" + sSearch + "':","Passed. Expecting result ('" + expectExists + "') = test result ('" + bTestReturnResult + "')");
			} else {
				MyPrint.myPrint("Search solve with keyword '" + sSearch + "':","Failed. Expecting result ('" + expectExists + "') != test result ('" + bTestReturnResult + "')");
				Assert.fail("***Search solve with keyword '" + sSearch + "':Failed.");
			}
		} catch (NoSuchElementException|NullPointerException e) {
			if(!expectExists){
				MyPrint.myPrint("Search solve with keyword '" + sSearch + "':","Passed. Expecting exists=false, test result=false");
			} else {
				MyPrint.myPrint("Search solve with keyword '" + sSearch + "':","Failed. Expecting exists=true, test result=false");
				Assert.fail("***Search solve with keyword '" + sSearch + "':Failed.");
			}
		}

	}

	public boolean SetSalvoType(boolean bPrivateType){
		boolean bValue1, bSuccess=false;
		String sLoggedin;
		bPrivateSalvoType=bPrivateType;
		List<WebElement> oRadioButton = driver.findElements(By.name("salvoAccessType"));
		bValue1 = oRadioButton.get(0).isSelected();
    	if(bPrivateType){
    		if(!bValue1){
    			oRadioButton.get(0).click();
    		}
    		bSuccess=true;
    	} else {
    		if(driver.findElements(By.xpath("//img[@id='salvoPublicIcon'][not(contains(@style,'display: none'))]")).size()!=0){
    			driver.findElements(By.xpath("//img[@id='salvoPublicIcon'][not(contains(@style,'display: none'))]")).get(0).click();
    			oRadioButton = driver.findElements(By.name("salvoAccessType"));
    			Assert.assertEquals(true,oRadioButton.get(1).isSelected());
    			bSuccess=true;
    		} else {
    			sLoggedin=driver.findElement(By.id("logoutButton")).getAttribute("title").replace("Logout User ","");
    			System.out.println("*** The user " + sLoggedin + " doesn't have the privilege creating public Salvos. ***" );
    		}
    	}
    	return bSuccess;
	}
	public void setNewSalvoName(String sSalvoName){
		SalvoName=sSalvoName;
//		driver.navigate().refresh();/////////////// too long time wait through VPN
		longWait.until(ExpectedConditions.elementToBeClickable(By.id("vidshieldLogo")));
		longWait.until(ExpectedConditions.elementToBeClickable(By.id("helpButton")));
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
	    PageLoadWait.GenericWaitLoad();
    	driver.findElement(By.id("nameSalvo")).clear();
        TestActions.clickElementWithNoWait(TestActions.getElement("nameSalvo"));
        TestActions.getElement("nameSalvo").clear();
		TestActions.getElement("nameSalvo").sendKeys(SalvoName);
        PageLoadWait.GenericWaitLoad();
        PageLoadWait.GenericWaitLoad();
	}
    public void setSalvoName(String sSalvoName){
    	SalvoName=sSalvoName;
	}
	public void ReSetViewers(){
		driver.findElement(By.id("toolbar_gridModes_clearGrid")).click();   	
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}

	public void RebuildViewers(){
		int iterations;
		PageLoadWait.GenericWaitLoad();
		driver.navigate().refresh();
		iterations=0;
    	while (!((JavascriptExecutor)driver).executeScript("return document.readyState").toString().trim().equalsIgnoreCase("complete")){
    		TestActions.robotWait(1);
    		iterations++;
    		if (iterations>120){
				try {
					throw new LoadingTimeOutException("failed to load window content within the time given frame.time out.");

				} catch (LoadingTimeOutException e) {
					e.printStackTrace();
				}
    		}
    	}
    	PageLoadWait.GenericWaitLoad();
    	PageLoadWait.GenericWaitLoad();    	
		longWait.until(ExpectedConditions.elementToBeClickable(By.id("vidshieldLogo")));
		longWait.until(ExpectedConditions.elementToBeClickable(By.id("helpButton")));
		longWait.until(ExpectedConditions.elementToBeClickable(By.id("controlsCanvas_tabOneClickSurface")));
	}
}
