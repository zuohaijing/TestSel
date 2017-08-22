package autom.common;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import static org.junit.Assert.fail;

public class LocEditor extends MasterTest{
	private String ConnectorName;
	private static final BigDecimal myCamLat=new BigDecimal(42.335000);
	private static final BigDecimal myCamLon=new BigDecimal(-72.040000);
	protected BigDecimal Lat=new BigDecimal(0);
	protected BigDecimal Lon=new BigDecimal(0);
	protected int Level;
	protected BigDecimal LatNew=new BigDecimal(0);
	protected BigDecimal LonNew=new BigDecimal(0);
	protected int LevelNew;
	protected BigDecimal LatOrig=new BigDecimal(0);
	protected BigDecimal LonOrig=new BigDecimal(0);
	protected int LevelOrig;
	private By OpenLayers=TestActions.getElementLocator("OpenLayers");
	private By ContextMenu=TestActions.getElementLocator("ContextMenu");
	private By SearchField=TestActions.getElementLocator("searchField");
	private By LatField=TestActions.getElementLocator("latField");
	private By LonField=TestActions.getElementLocator("lonField");
	private By levelField=TestActions.getElementLocator("levelField");
	private By markerDeviceList=TestActions.getElementLocator("markerDeviceList");	
	private String MapSelectValue;
	private String MapViewName;
	protected LocPoint ConnectorLocPoint;
	protected int y1;
	protected int y2;
	private String myTag1="ClientButton";
	private String myTag2="Mapping";
	private String myTag3="Location Editor";	
	public LocEditor(String sConnectorName,LocPoint p1){
		ConnectorName=sConnectorName;
		ConnectorLocPoint=p1;
		Navigate();
	}
    public void Navigate() {
    	TestActions.Navigation2(myTag1,myTag2,myTag3);
		TestActions.waitElementClickableReady(OpenLayers);
    }
	public LocEditor(String sName){
		ConnectorName=sName;
		Navigate();
		SearchConnector();
	}
	public void SetMapView(String sMapViewName){
		if(sMapViewName!=null){
			MapViewName=sMapViewName;
			OpenView();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();	
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();	
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();	
		}
	}
	
	public void SetLocPoint(LocPoint testPoint){
		ConnectorLocPoint=testPoint;
	}
	public void OpenView(){
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("DivView");
//		TestActions.clickElementAndWaitReady("DivView");
		TestActions.mouseOverElementRobotClick("DivView");		
		TestActions.clickElementAndWaitReady("ViewsItem0Menu");	
		WebElement oEle=driver.findElement(By.xpath("//div[@id='viewList']/div/table/tbody/tr/td/div[contains(text(),'" + MapViewName + "')]"));

		TestActions.mouseOverElementClick(oEle);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();		
	}
	public LocEditor(){
		Navigate();
	}
	public void SetConnectorName(String sName){
		if(sName!=null){
			if(sName.length()>0){
				ConnectorName=sName;
			}
		}
	}
	
	public void SetConnectorMap(String sMapName){
		if(sMapName!=null){
			if(sMapName.length()>0){
				MapSelectValue=sMapName;		
			}
		}
	}

	public void SetNewLatLonProperty(BigDecimal Lat, BigDecimal Lon,int Level){
		Lat = Lat.setScale(6, BigDecimal.ROUND_DOWN);
		Lon = Lon.setScale(6, BigDecimal.ROUND_DOWN);
		LatNew=Lat;
		LonNew=Lon;
		if (Level!=0){
			LevelNew=Level;	
		}
	}
	
	public void SetLatLonToField(BigDecimal Lat, BigDecimal Lon,int Level){
		Lat = Lat.setScale(6, BigDecimal.ROUND_DOWN);
		Lon = Lon.setScale(6, BigDecimal.ROUND_DOWN);
		TestActions.SetElementValue(LatField,Lat);
		TestActions.SetElementValue(LonField,Lon);
		TestActions.SetElementValue(levelField,Level);
	}

	public void SetLatLonToField(int Level){
		TestActions.SetElementValue(LatField,myCamLat);
		TestActions.SetElementValue(LonField,myCamLon);
		TestActions.SetElementValue(levelField,Level);
	}
	
	public void MoveToNewLatLon(){
		boolean bNewPosition=false;
		if(LatNew.compareTo(BigDecimal.ZERO) == 0){
			bNewPosition=true;
		} else {
			if(LonNew.compareTo(BigDecimal.ZERO) == 0){
				bNewPosition=true;	
			}
		}
		if(bNewPosition){
			SetLatLonToField(LevelNew);
		} else{
			SetLatLonToField(LatNew, LonNew, LevelNew);	
		}				
		TestActions.clickElementWithNoWait(levelField);
		EnterKey("moveto");		
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait(levelField);
		EnterKey("save");
		if(TestActions.isAlertPresent()) {
			TestActions.selectOKAlert();	
		}
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		MyPrint.myPrint("Location Editor - move connector '" + ConnectorName + "' to\n" + "Lat= " + LatNew + "\n" + "Lon=" + LonNew + "\n" +  "Level=" + LevelNew + "\n", "Test Passed.");
	}
	
	
	public void MoveToNewLatLon(boolean onWorldMap){
		boolean bNewPosition=false;
		if(LatNew.compareTo(BigDecimal.ZERO) == 0){
			bNewPosition=true;
		} else {
			if(LonNew.compareTo(BigDecimal.ZERO) == 0){
				bNewPosition=true;	
			}
		}
		if(bNewPosition){
			SetLatLonToField(LevelNew);
		} else{
			SetLatLonToField(LatNew, LonNew, LevelNew);	
		}
		TestActions.clickElementWithNoWait(levelField);
		EnterKey("moveto");		
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementWithNoWait(levelField);
		
		if(onWorldMap){
			driver.findElement(By.name("worldCheckbox")).click();
			PageLoadWait.GenericWaitLoad();
		}
		
		EnterKey("save");
		if(TestActions.isAlertPresent()) {
			TestActions.selectOKAlert();	
		}
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		MyPrint.myPrint("Location Editor - move connector '" + ConnectorName + "' to\n" + "Lat= " + LatNew + "\n" + "Lon=" + LonNew + "\n" +  "Level=" + LevelNew + "\n", "Test Passed.");
	}
	
	
	public void EnterKey(String skey){
			Robot robot;
			try {
				robot = new Robot();
				switch (skey.toLowerCase()){
				case "cancel":
					for(int iLoop=0; iLoop<4; iLoop++){
					    robot.keyPress(KeyEvent.VK_TAB);
					    robot.keyRelease(KeyEvent.VK_TAB);
					    robot.delay(200);						
					}
				    robot.keyPress(KeyEvent.VK_ENTER);
				    robot.keyRelease(KeyEvent.VK_ENTER);
				    robot.delay(2000);
					break;
				case "save":
					for(int iLoop=0; iLoop<3; iLoop++){
					    robot.keyPress(KeyEvent.VK_TAB);
					    robot.keyRelease(KeyEvent.VK_TAB);
					    robot.delay(200);						
					}
				    robot.keyPress(KeyEvent.VK_ENTER);
				    robot.keyRelease(KeyEvent.VK_ENTER);
				    robot.delay(2000);
					break;
				case "moveto":					
					for(int iLoop=0; iLoop<2; iLoop++){
					    robot.keyPress(KeyEvent.VK_TAB);
					    robot.keyRelease(KeyEvent.VK_TAB);
					    robot.delay(200);						
					}
				    robot.keyPress(KeyEvent.VK_ENTER);
				    robot.keyRelease(KeyEvent.VK_ENTER);
				    robot.delay(2000);
					break;
				default:
				}
			} catch (AWTException e) {
				e.printStackTrace();
			}
	}
	
	
	public void getLatLon(){
		Lat=new BigDecimal(driver.findElement(LonField).getAttribute("value"));
		Lon=new BigDecimal(driver.findElement(LonField).getAttribute("value"));
		Level=Integer.parseInt(driver.findElement(LonField).getAttribute("value"));
	}
	
	public void CurrentLatLon(){
		Lat=new BigDecimal(driver.findElement(By.name("latField")).getAttribute("value"));
		Lon=new BigDecimal(driver.findElement(By.name("lonField")).getAttribute("value"));
	}
	
	public void SearchConnector(){
		if(ConnectorName!=null){
			TestActions.SetElementValue(SearchField,ConnectorName);
			if(MapSelectValue!=null){
				TestActions.DropDownSelect(TestActions.getElement("selectMapOption"), MapSelectValue);
			}
			TestActions.clickElementWithNoWait("searchGroupApply");
			PageLoadWait.GenericWaitLoad();
			
			WebElement oTable=driver.findElement(markerDeviceList);
			int iRows=oTable.findElements(By.xpath(".//tr")).size();

			if(iRows==0){
				MyPrint.myPrint("Location Editor - Device name '" + ConnectorName + "'.", " Not Found.");
			} else {
				MyPrint.myPrint("Location Editor - Device name '" + ConnectorName + "' search.", " Found.");
			}
		} else {
			MyPrint.myPrint("Location Editor - Device name is not defined", " Test failed.");
		}
		MyPrint.myPrint("Location Editor - Search connector " + ConnectorName +"'", " Test passed.");
	}
	
	public void EditConnectorLoc(Double offset){
		String sContextMenu="Edit Location";
		BigDecimal sOffset=new BigDecimal(offset);
		sOffset = sOffset.setScale(6, BigDecimal.ROUND_DOWN);		
		SearchSelectContext(sContextMenu);		
		CurrentLatLon();
		if(offset!=null){
			SetNewLatLonProperty(Lat.add(sOffset), Lon.add(sOffset), Level);
			MoveToNewLatLon();
		} else {
			SetNewLatLonProperty(Lat, Lon, Level);
			MoveToNewLatLon();
		}
	}
	
	public void EditConnectorLoc(BigDecimal Lat, BigDecimal Lon,int Level, boolean bOnWorld){
		String sContextMenu="Edit Location";
		SearchSelectContext(sContextMenu);		
		CurrentLatLon();
		SetNewLatLonProperty(Lat, Lon, Level);
		MoveToNewLatLon();
	}
	
	public void SearchSelectContext(String sContextMenu){
		PageLoadWait.GenericWaitLoad();
		try{
			WebElement oTable=driver.findElement(markerDeviceList);
			WebElement oRow1=oTable.findElement(By.xpath(".//tr/td"));
			TestActions.ElementRightClick(oRow1);
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();
			TestActions.SelectContextMenu(ContextMenu,sContextMenu);			
		} catch (NoSuchElementException e){
			MyPrint.myPrint("Location Editor - No result found on searching connector '" + ConnectorName + "'", " WARNING.");
			e.printStackTrace();
		} catch (Exception e){
			MyPrint.myPrint("Location Editor - Error found on searching connector '" + ConnectorName + "'", " Test failed.");
			e.printStackTrace();
		}
		PageLoadWait.GenericWaitLoad();
	}	
	public void DeleteMarker(){
		String sContextMenu="Delete Marker(s)";			
		SearchSelectContext(sContextMenu);
		MyPrint.myPrint("Location Editor - Deleted marker connector '" + ConnectorName + "'", " Test Passed.");
	}
	public void AddMarker(){
		String sContextMenu="Add Marker";			
		SearchSelectContext(sContextMenu);
		MyPrint.myPrint("Location Editor - Added marker for connector '" + ConnectorName + "'", " Test Passed.");
	}
	public void ClearMarker(){
		String sContextMenu="Clear Location";			
		SearchSelectContext(sContextMenu);
		MyPrint.myPrint("Location Editor - Cleared marker for connector '" + ConnectorName + "'", " Test Passed.");
	}
	public void DragDropConnector(){
		PageLoadWait.GenericWaitLoad();
		WebElement oTable=driver.findElement(markerDeviceList);
		WebElement oRow1=oTable.findElement(By.xpath(".//tr/td"));
		Actions builder = new Actions(driver);   
		builder.moveToElement(oRow1, 10, 5).click().build().perform();
		Robot bot;
		try {
			bot = new Robot();
			bot.delay(1000);
		    bot.mousePress(InputEvent.BUTTON1_MASK);
		    bot.delay(100);
		    bot.mouseMove(ConnectorLocPoint.x1, ConnectorLocPoint.y1);
		    bot.delay(100);
		    bot.mouseRelease(InputEvent.BUTTON1_MASK);
		    bot.delay(500);
		    Thread.sleep(100);
		} catch (AWTException|InterruptedException e) {
			MyPrint.myPrint("Drag Drop '" + ConnectorName + "'", " Failed - Found error/exception.");
			e.printStackTrace();
		}	
		PageLoadWait.GenericWaitLoad();
		MyPrint.myPrint("Location Editor - Drag & Drop connector '" + ConnectorName + "' to point (" + ConnectorLocPoint.x1 + "," + ConnectorLocPoint.y1 + ")", " Test Passed.");
	}
}
