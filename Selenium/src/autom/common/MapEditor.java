package autom.common;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MapEditor extends MasterTest{
	private String FloorPlanName;
	private String ActiveAreaName;
	protected int x1;
	protected int x2;
	protected int y1;
	protected int y2;
	private String myTag1="ClientButton";
	private String myTag2="Mapping";
	private String myTag3="Map Editor";
	private String MapViewName;
	private String floorPlanZoom;
	private String floorPlanLevel;
	private String floorPlanOwner;
	private String flrPlnLocalImagePath="C:\\temp\\flrpln1test.gif";
	protected File flrPlnLocalImage= new File(flrPlnLocalImagePath);
	protected File flrPlnNeTWorkImage= new File("\\\\mafileserver\\GroupStorageNoBackup\\HZuo\\VidSys\\VidShield\\tomcat\\webapps\\vs4\\img\\icons\\icon_eventlog_stacked.gif");

	public MapEditor(String sName,int a1,int a2,int b1,int b2){
		x1=a1;
		x2=a2;
		y1=b1;
		y2=b2;
		if(sName!=null){
			FloorPlanName=sName;			
		}
	}
	public MapEditor(String sName,LocPoint oStart,LocPoint oEnd){
		x1=oStart.x1;
		x2=oEnd.x1;
		y1=oStart.y1;
		y2=oEnd.y1;
		if(sName!=null){
			FloorPlanName=sName;			
		}
	}
	public void SetImage(int numberfield){
		if(!TestActions.exists(flrPlnLocalImage)){
			try {
				TestActions.copyFile(flrPlnNeTWorkImage, flrPlnLocalImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id("mapMenu-addFloorPlanImage" + numberfield)).sendKeys(flrPlnLocalImagePath);
	}
	public MapEditor(String sName){
		if(sName!=null){
			FloorPlanName=sName;			
		}
	}
	public MapEditor(){
		Navigate2();
	}
	public void SetMapView(String sMapViewName){
		if(sMapViewName!=null){
			MapViewName=sMapViewName;
		}
	}
	public void SetZoom(String sZoom){
		if(sZoom!=null){
			floorPlanZoom=sZoom;			
		}
	}
	public void SetOwner(String sOwner){
		if(sOwner!=null){
			floorPlanOwner=sOwner;
		}
	}
	public void SetLevel(String sLevel){
		if(sLevel!=null){
			floorPlanLevel=sLevel;
		}
	}
	public void OpenView(){
		
		// new addition
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(TestActions.getElementLocator("DivView")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(TestActions.getElementLocator("DivView")));
		
		
		
		
//		TestActions.waitElementClickableReady("DivView");
//		TestActions.clickElementAndWaitReady("DivView");
//		---- midified to:

//		TestActions.waitElementClickableReady("DivView");
//		TestActions.clickElementAndWaitReady("DivView");
		TestActions.clickElementWithNoWait("DivView");
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Views ...')][@class='menuWidgetGlobalDefault'][contains(@id,'_menu_item2_title')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Views ...')][@class='menuWidgetGlobalDefault'][contains(@id,'_menu_item2_title')]")));
		driver.findElement(By.xpath("//div[contains(text(),'Views ...')][@class='menuWidgetGlobalDefault'][contains(@id,'_menu_item2_title')]")).click();;
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='viewList']//tr/td/div[contains(text(),'" + MapViewName + "')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='viewList']//tr/td/div[contains(text(),'" + MapViewName + "')]")));
		driver.findElement(By.xpath("//div[@id='viewList']//tr/td/div[contains(text(),'" + MapViewName + "')]")).click();;
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}
	
	protected void CreateFloorPlan(){
		MyPrint.myPrint("Create FloorPlan","started.");
		PageLoadWait.GenericWaitLoad();
		Navigate();
		if (MapViewName!=null){
			OpenView();
		}
		DeleteFloorPlan();
		TestActions.robotWait(2);
		TestActions.clickElementWithNoWait("DivEdit");		
		TestActions.clickElementWithNoWait("DivEdit");
		TestActions.robotWaitMS(80);
		try {
			Robot bot = new Robot();
			PointerInfo a = MouseInfo.getPointerInfo();
			int x = (int) a.getLocation().getX();
			int y = (int) a.getLocation().getY();
			bot.delay(50);
			bot.mouseMove(x + 5, y + 30);
		} catch (StaleElementReferenceException e) {
			System.out.println("WebElement is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("WebElement was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("WebElement  was not clickable "
					+ e.getStackTrace());
		}
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("DivAddFloorPlan");
		TestActions.robotWaitMS(80);
		TestActions.waitElementClickableReady("AddNewFloorPlanNameField");
		
//		TestActions.waitElementClickableReady("DivEdit");
//		TestActions.clickElementWithNoWait("DivEdit");
//		TestActions.robotWaitMS(20);
//		TestActions.clickElementWithNoWait("DivAddFloorPlan");
//		TestActions.robotWaitMS(20);
//		TestActions.waitElementClickableReady("AddNewFloorPlanNameField");
		PageLoadWait.GenericWaitLoad();
		TestActions.SetElementValue(TestActions.getElementLocator("AddNewFloorPlanNameField"),FloorPlanName);
		PageLoadWait.GenericWaitLoad();
		if(floorPlanZoom!=null){
			WebElement oOwnerDropDown=TestActions.getElement("FloorPlanZoom");
			TestActions.ElementSelect(oOwnerDropDown);
			TestActions.clickElementWithNoWait(oOwnerDropDown);
			oOwnerDropDown.findElement(By.xpath("./option[@value='" + floorPlanZoom + "']")).click();		
			PageLoadWait.GenericWaitLoad();
			TestActions.clickElementWithNoWait("AddNewFloorPlanNameField");
			
			for(int iMyImage=1; iMyImage-1<Integer.parseInt(floorPlanZoom);iMyImage++){
				SetImage(iMyImage);
			}
		}
		if(floorPlanLevel!=null){
			WebElement oOwnerDropDown=TestActions.getElement("FloorPlanLevel");
			TestActions.ElementSelect(oOwnerDropDown);
			TestActions.clickElementWithNoWait(oOwnerDropDown);
			TestActions.SetElementValue(oOwnerDropDown,floorPlanLevel);			
			PageLoadWait.GenericWaitLoad();
		}
		if(floorPlanOwner!=null){
			WebElement oOwnerDropDown=TestActions.getElement("FloorPlanOwner");
			TestActions.ElementSelect(oOwnerDropDown);
			TestActions.clickElementWithNoWait(oOwnerDropDown);
			oOwnerDropDown.findElement(By.xpath("./option[@value='" + floorPlanOwner + "']")).click();		
			PageLoadWait.GenericWaitLoad();
			TestActions.clickElementWithNoWait("AddNewFloorPlanNameField");
		}
		
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("FloorPlanLocateButton");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Robot bot;
		try {
			bot = new Robot();
			Thread.sleep(100);
			bot.mouseMove(x1, y1);
			bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.mouseMove(x2, y2);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(500);
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();	    
		    TestActions.clickElementWithNoWait(TestActions.getElement("FloorPlanSaveButton"));
		    Thread.sleep(500);
		    TestActions.robotWait(2);
		    PageLoadWait.GenericLongWaitLoad();
		    TestActions.robotWait(2);
		    PageLoadWait.GenericLongWaitLoad();
		} catch (AWTException e) {
			MyPrint.myPrint("Create FloorPlan '" + FloorPlanName + "'", "Found error/exception.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyPrint.myPrint("FloorPlan '" + FloorPlanName + "'", " has been successfully created.");
	}
	
	protected void deletePreviousActiveArea(){
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("DivEdit");
		TestActions.clickElementAndWaitReady("DivEdit");
		TestActions.clickElementAndWaitReady("EditActiveArea");
		TestActions.waitElementClickableReady("SelectActiveAreaName");
		
		WebElement oSelect=TestActions.getElement("SelectActiveAreaName");
		List<WebElement> oOptions=oSelect.findElements(By.xpath("./option"));
		PageLoadWait.GenericWaitLoad();
		if(oOptions.size()>0){
			TestActions.clickElementWithNoWait("DeleteActiveArea");
			TestActions.selectOKAlert();
		} else {
			TestActions.clickElementWithNoWait("CancelCreateActiveArea");
		}
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}
	
	protected void CreateActiveArea(String sActiveAreaName){
		MyPrint.myPrint("Create Active Area","started.");
		
		Navigate2();
		if (MapViewName!=null){
			OpenView();			
		}
		PageLoadWait.GenericWaitLoad();
		ActiveAreaName=sActiveAreaName;
		TestActions.waitElementClickableReady("DivEdit");
		TestActions.clickElementAndWaitReady("DivEdit");
		TestActions.clickElementAndWaitReady("AddActiveArea");
		TestActions.waitElementClickableReady("AddNewActiveAreaNameField");
		PageLoadWait.GenericWaitLoad();
		TestActions.SetElementValue(TestActions.getElementLocator("AddNewActiveAreaNameField"),ActiveAreaName);
		PageLoadWait.GenericWaitLoad();

		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady("LocateActiveAreabutton");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		Robot bot;
		try {
			bot = new Robot();
			bot.mouseMove(x1, y1); 
		    bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(100);
		    bot.mouseMove(x1, y2);
		    Thread.sleep(100);
		    bot.mousePress(InputEvent.BUTTON1_MASK);
		    Thread.sleep(100);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(100);
		    bot.mouseMove(x2, y2);
		    Thread.sleep(100);
		    bot.mousePress(InputEvent.BUTTON1_MASK);
		    Thread.sleep(100);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);

		    Thread.sleep(100);
		    bot.mouseMove(x2, y1);
		    Thread.sleep(100);
		    bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    Thread.sleep(100);
		    PageLoadWait.GenericWaitLoad();
		    PageLoadWait.GenericWaitLoad();	    
		    TestActions.clickElementWithNoWait(TestActions.getElement("SaveActiveAreabutton"));
		    Thread.sleep(2000);
		    PageLoadWait.GenericLongWaitLoad();
		    PageLoadWait.GenericLongWaitLoad();
		} catch (AWTException e) {
			MyPrint.myPrint("Create FloorPlan '" + FloorPlanName + "'", "Found error/exception.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyPrint.myPrint("Active Area '" + ActiveAreaName + "'", " has been successfully created.");
	}
	
	
	
//	protected void ChangeFloorPlanName(String sNewName){
//		MyPrint.myPrint("Edit FloorPlan","started.");
//		String sOrigName=FloorPlanName;
//		PageLoadWait.GenericWaitLoad();
//		Navigate();
//		PageLoadWait.GenericWaitLoad();
//		TestActions.waitElementClickableReady("DivEdit");
//		TestActions.clickElementAndWaitReady("DivEdit");
//		TestActions.clickElementAndWaitReady("DivEditRegion");
//		TestActions.waitElementClickableReady("EditRegionNameSelect");
//		TestActions.clickElementAndWaitReady("EditRegionNameSelect");
//		PageLoadWait.GenericWaitLoad();
//		Select dropdown = new Select(driver.findElement(TestActions.getElementLocator("EditRegionNameSelect")));
//		dropdown.selectByVisibleText(FloorPlanName);
//		driver.findElement(TestActions.getElementLocator("EditRegionNameSelect")).sendKeys(Keys.ENTER);
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
//		TestActions.clickElementAndWaitReady("selectRegionEditButton");
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
//		TestActions.SetElementValue(TestActions.getElementLocator("EditRegionNameField"),sNewName);		
//		TestActions.clickElementAndWaitReady("EditRegionSaveButton");
//		TestActions.clickElementIfExists("nomadPromptOKButton");
//		FloorPlanName=sNewName;
//		MyPrint.myPrint("Changed FloorPlan with name '" + sOrigName + "' to name", "'" + sNewName + "' successfully.");
//		
//	}
	protected void DeleteFloorPlan(){
		MyPrint.myPrint("Delete FloorPlan:'" + FloorPlanName + "'"," started.");
		PageLoadWait.GenericWaitLoad();
		Navigate();
		if (MapViewName!=null){
			OpenView();			
		}
		TestActions.waitElementClickableReady("DivEdit");
		TestActions.clickElementAndWaitReady("DivEdit");
		TestActions.clickElementAndWaitReady("DivEditFloorPlan");
		TestActions.waitElementClickableReady("EditFloorPlanSelectName");		
		WebElement oOwnerDropDown=TestActions.getElement("EditFloorPlanSelectName");
		TestActions.ElementSelect(oOwnerDropDown);
		By oFPOption=By.xpath("./option[text()='" + FloorPlanName + "']");
//		if (TestActions.ElementExists(By.xpath("//select[@id='mapMenu-selectFloorPlanName']/option[text()='" + FloorPlanName + "']"))){
		if (TestActions.ElementExists(oOwnerDropDown,oFPOption)){
			TestActions.clickElementWithNoWait(oOwnerDropDown, oFPOption);
			PageLoadWait.GenericWaitLoad();
			TestActions.ExcuteKeyEvent(10);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			TestActions.waitElementClickableReady("EditFloorPlanDeleteBTN");
			TestActions.clickElementWithNoWait("EditFloorPlanDeleteBTN");
			TestActions.selectOKAlert();
			PageLoadWait.GenericWaitLoad();
			if (MapViewName!=null){
				OpenView();			
			}
			MyPrint.myPrint("Deleted FloorPlan '" + FloorPlanName + "'", "successfully.");			
		} else {
			TestActions.ExcuteKeyEvent(10);
			TestActions.clickElementWithNoWait("EditFloorPlanCanlcelBTN");
			PageLoadWait.GenericWaitLoad();
			MyPrint.myPrint("FloorPlan '" + FloorPlanName + "' doesn't exist", "Canceled delete.");
		}
	}
    public void Navigate() {
    	TestActions.Navigation(myTag1,myTag2,myTag3);
		TestActions.waitElementClickableReady("DivEdit");
    }
    public void Navigate2() {
    	TestActions.Navigation2(myTag1,myTag2,myTag3);
		TestActions.waitElementClickableReady("DivEdit");
    }
    static <T> boolean isInstance(Object myVar, Class<T> expectedType) {
        return expectedType.isInstance(myVar);
    }
    static boolean isInstance(Object myVar) {
        return (myVar instanceof MapEditor);
    }
    
}
