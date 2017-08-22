package autom.common;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static org.junit.Assert.fail;

import java.util.Stack;

public class ModuleMapPolicy extends MasterTest{
	protected boolean bPrivateSalvoType=false;
	protected String SalvoName="";
	private String myTag1="ClientButton";
	private String myTag2="Mapping";
	private String myTag3="Map Policy";
	private String sOpenStreetMapBaseURL="http://tile.openstreetmap.org";
	private String sOpenStreetMapGEOBaseURL="http://nominatim.openstreetmap.org/";
	private String sBingAPIKey="AkIFZF5ejNdlVPbrp-0_w-IyYS85sFRuRlMGgXtoF2f3_aLQLbiywFSzc6rg4tb_";
	private String ArcGIS_URL="http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/TaxParcel/AssessorsBasemap/MapServer/export";
	private String ArcGIS_Layers="2,0,27,32";
	private String ArcGIS_Projection="epsg:4326";
	private String ArcGIS_MaxZoom="19";
	private String ArcGISAddress="Bloomfield Hills, MI 48302";
	private String sLocalDefault="293 Boston Post Road West,Marlborough";
//	private String sLocalDefault1="2 State Avenue,Fall River, MA";
	private String sLocalDefault1="10 Main St., Acton, MA";
	
	
	//	private String SturtleHouse="2500 Turtle Lake Dr, Bloomfield Hills, MI 48302";

	private String MapTypeGoogleV3Protocol="https";	//http/https
	private String MapTypeGoogleV3APIType="";		//standard/business
	private String MapTypeGoogleV3APIKey=""; 		//
	private String MapTypeGoogleV3Layer="";			//Disabled/Enabled
	private boolean MapTypeGoogleV3Modification=false;			//Disabled by default
	private boolean MapTypeTilecacheModification=false;			//Disabled by default

	private By MapGeocodingTab=By.xpath("//a[@href='geocoding.jsp']");
	private By MapTypeTab=By.xpath("//a[@id='mapconfig'][@href='map-config.jsp']");
	private By ChangeMapType=By.partialLinkText("Change Map");
	private By oEditButton=By.xpath("//div/ul/li/div[@id='editgooglev3'][@class='buttonFactory']");
	private By ChangeMapGeocodingProvider=By.linkText("Change Map Geocoding Provider");
	private By oMapType=By.xpath("//div[@class='mapType']");
	private By oGeocodertypeRadio=By.xpath("//input[@name='geocodertype']");

	private By ChangeMapGeocodingAjaxPopup=By.xpath("//div[@id='TB_ajaxContent']");
	private String BaseURL_Geocoding_openStreet="http://tile.openstreetmap.org";
	private String BaseURL_Geocoding_ArcGIS="http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer";
	private By oGeocodertypeCancelButton=By.xpath(".//div[@class='buttonFactory'][contains(.,'Cancel')]");
	private By oGeocodertypeSaveButton=By.xpath(".//div[@class='buttonFactory'][contains(.,'Save')]");
	private By oGeocodertypeResetButton=By.xpath(".//div[@class='buttonFactory'][contains(.,'Reset')]");
	private By oGeocodertypeSelected=By.xpath(".//input[@disabled='true')]");
	public By oClientButton;
	private String sGeocodingProvider="";

	private String sCurrGEOCodingProvider="";
	private String sPreviousGEOCoding="";
	private String sChangeToGEOCodingProvider="";
	private String sCurrMapType="";
	private String sPreviousMapType="";
	private String sChangeToMapType="";

	private String mapServerMapFiles="C:/ms4w/apps/mass/mass2.map";
	private String mapServerBaseUrl=SERVER_URL;
	private String mapServerPath2MapFile="/ms4w/apps/mass/mass2.map";
	private String mapServerFormat="gif";
	private String mapServerLayers="towns_poly,525x,Streams,Ponds,aerial197894,aerial193898,aerial193894,aerial197898,aerial201894,aerial201898";
	private String mapServerProjection="epsg:4326";
	private String mapServerPort="8085";
	private String mapServerMaxZoom="17";

	private String tileCacheBaseUrl=SERVER_URL;
	private String tileCacheFormat="png";
	private String tileCacheProjection="epsg:4326";
	private String tileCachePort="8085";
	private String tileCacheMaxZoom="17";
	private String tileCacheCheck=tileCacheBaseUrl+":"+ tileCachePort + "/tilecache?LAYERS=vidshield&FORMAT="+ tileCacheFormat;
	private boolean bLocationEditorCheck=false;	
	private By oLocationEditorCheck;
	private By oLocationEditorGoToButton;
	private boolean bLocationEditorGoToButton=false;

	public ModuleMapPolicy() {
		NavAdminMapPolicy();
	}
	public void NavAdminMapPolicy() {
		TestActions.Navigation2(myTag1,myTag2,myTag3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MapGeocodingTab));
		wait.until(ExpectedConditions.visibilityOfElementLocated(MapTypeTab));
	}
	public void setGoogleV3MapType(String sField, String sValue){
		if(!MapTypeGoogleV3Modification){
			MapTypeGoogleV3Modification=true;
		}
		switch (sField.toLowerCase()){
		case "protocal":
			MapTypeGoogleV3Protocol=sValue;	//http/https
			break;
		case "api":
		case "api type":
			MapTypeGoogleV3APIType=sValue;
			break;
		case "key":
		case "api key":
			MapTypeGoogleV3APIKey=sValue;
			break;
		case "layer":
		case "transit layer":
		case "transit":
			MapTypeGoogleV3Layer=sValue;
			break;
		default:
			break;
		}
	}

	public void setMapServerTypes(String sField, String sValue){
		switch (sField.toLowerCase()){
		case "map files":
			mapServerMapFiles=sValue;
			break;
		case "base url":
		case "url":
			mapServerBaseUrl=sValue;
			break;
		case "path":
		case "path to map file":
			mapServerPath2MapFile=sValue;
			break;
		case "format":
		case "server format":
			mapServerFormat=sValue;
			break;
		case "layers":
		case "server layers":
			mapServerLayers=sValue;
			break;	
		case "projection":
			mapServerProjection=sValue;
			break;	
		case "port":
		case "server port":
			mapServerPort=sValue;
			break;
		case "server max zoom":
		case "zoom":
			mapServerMaxZoom=sValue;
			break;
		default:
			break;
		}
	}
	public void setTilecacheTypes(String sField, String sValue){
		if(!MapTypeTilecacheModification){
			MapTypeTilecacheModification=true;
		}
		switch (sField.toLowerCase()){
		case "base url":
		case "url":
			tileCacheBaseUrl=sValue;
			break;
		case "format":
			tileCacheFormat=sValue;
			break;
		case "projection":
			tileCacheProjection=sValue;
			break;	
		case "port":
			tileCachePort=sValue;
			break;
		case "max zoom":
		case "zoom":
			tileCacheMaxZoom=sValue;
			break;
		default:
			break;
		}
	}

	public void PageVerification(){
		openMapType();
		changeMapType("ArcGIS");
		openGEOCoding();
		System.out.println("*** sCurrMapType:" + sCurrMapType);
		System.out.println("***sCurrGEOCodingProvider:" + sCurrGEOCodingProvider);
		openGEOCoding();
		openMapType();
		System.out.println("*** sCurrMapType:" + sCurrMapType);
		System.out.println("***sCurrGEOCodingProvider:" + sCurrGEOCodingProvider);
		TestActions.clickElementAndWaitReady(MapGeocodingTab);
		wait.until(ExpectedConditions.elementToBeClickable(ChangeMapType));
		TestActions.waitTilElementVisible(driver.findElement(ChangeMapGeocodingProvider));
		Assert.assertEquals(true,driver.findElement(oMapType).getText().toLowerCase().contains(("Current Map Geocoding Provider").toLowerCase()));		
		TestActions.clickElementAndWaitReady(ChangeMapGeocodingProvider);
		Assert.assertEquals(true,driver.findElements(oGeocodertypeRadio).size()>0);
		sGeocodingProvider=driver.findElement(ChangeMapGeocodingAjaxPopup).findElement(By.xpath(".//input[@disabled='true']")).getAttribute("value");
		System.out.println("*** sGeocodingProvider= '" + sGeocodingProvider + "' ***" );
		if(driver.findElements(oGeocodertypeRadio).get(0).getAttribute("disabled")==null){

		} else {
			Assert.assertEquals(true,driver.findElements(oGeocodertypeRadio).get(0).getAttribute("disabled").contentEquals("true"));
		}

		driver.findElement(ChangeMapGeocodingAjaxPopup).findElement(oGeocodertypeCancelButton).click();	
	}

	public void mapTypeModification(String sNewMapTypeName){
		openMapType();
		changeMapType(sNewMapTypeName);
	}

	public void getCurrMapTypeGeoCoding(){
		openMapType();
		openGEOCoding();
	}
	public String getCurrGeoCoding(){
		openGEOCoding();
		return sCurrGEOCodingProvider;
	}
	public String getCurrMapType(){
		openMapType();
		return sCurrMapType;
	}
	public boolean mapTypeVerification(boolean bExpectGoToMapButtonDisplay){
		boolean bResult=false;
		boolean bResultTemp=false;
		bResultTemp=verifyMapTypeInMapLocEditor(bExpectGoToMapButtonDisplay);
		return bResult;
	}

	public boolean verifyMapTypeInMapLocEditor(boolean bExpectGoToMapButtonDisplay){
		boolean bLocation1=false;
		String sGoToAddress=ArcGISAddress;
		MyPrint.myPrint("Check Map Type in Admin UI>Mapping>Map Locator>Map Views","started.");
		LocEditor MyLoc = new LocEditor();
		bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
		bLocationEditorCheck=bLocation1;
		bLocationEditorGoToButton=driver.findElements(oLocationEditorGoToButton).size()>0;
		if(!sCurrMapType.equalsIgnoreCase("arcgis")){
			sGoToAddress=sLocalDefault1;
		}
		if(TestActions.GoToAddress(sGoToAddress,bExpectGoToMapButtonDisplay)){
			bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
			this.bLocationEditorCheck=bLocation1 && this.bLocationEditorCheck;
		}
		if(this.bLocationEditorCheck){
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in Location Editor image check: Passed");
		} else {
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in Location Editor image check: Failed");
		}
		return bLocationEditorCheck;
	}

	public boolean verifyMapTypeInMapEditor(boolean bExpectGoToMapButtonDisplay){
		boolean bLocation1=false;
		String sGoToAddress=ArcGISAddress;
		MyPrint.myPrint("Check Map Type in Admin UI>Mapping>Map Editor>Map Views","started.");
		MapEditor MyMapEditor = new MapEditor();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
		bLocationEditorCheck=bLocation1;

		if(bExpectGoToMapButtonDisplay){
			TestActions.waitElementpresence(oLocationEditorGoToButton);
			bLocationEditorGoToButton=driver.findElements(oLocationEditorGoToButton).size()>0;
		}
		if(!sCurrMapType.equalsIgnoreCase("arcgis")){
			sGoToAddress=sLocalDefault1;
		}

		if(TestActions.GoToAddress(sGoToAddress,bExpectGoToMapButtonDisplay)){
			bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
			this.bLocationEditorCheck=bLocation1 && this.bLocationEditorCheck;
		}
		if(this.bLocationEditorCheck){
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in Map Editor image check: Passed");
		} else {
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in Map Editor image check: Failed");
		}
		return bLocationEditorCheck;
	}

	public boolean verifyMapTypeInMapViewer(String sMapViewName,boolean bExpectGoToMapButtonDisplay){
		boolean bLocation1=false;
		String sGoToAddress=ArcGISAddress;
		oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		driver.switchTo().defaultContent();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(oClientButton));
		wait.until(ExpectedConditions.elementToBeClickable(oClientButton));
		TestActions.clickElementWithNoWait(oClientButton);
		MyPrint.myPrint("***","Navigate to client UI screen");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("QALab");
		PageLoadWait.GenericWaitLoad();
		MyPrint.myPrint("Check Map Type in Client UI Viewer","started.");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.LoadMyView(sMapViewName,true,true,1);
		bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
		bLocationEditorCheck=bLocation1;
		bLocationEditorGoToButton=driver.findElements(oLocationEditorGoToButton).size()>0;
		if(!sCurrMapType.equalsIgnoreCase("arcgis")){
			sGoToAddress=sLocalDefault1;
		}

		if(TestActions.GoToAddress(sGoToAddress,bExpectGoToMapButtonDisplay)){
			bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
			this.bLocationEditorCheck=bLocation1 && this.bLocationEditorCheck;
		}
		if(this.bLocationEditorCheck){
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in Client UI map viewer image check: Passed");
		} else {
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in Client UI map viewer image check: Failed");
		}
		return bLocationEditorCheck;
	}

	
	public void openMapView(String sMapViewName){
		oClientButton=By.xpath("//div[@id='topNavigationButtons']/table/tbody/tr/td/div[@id='clientButton']");
		driver.switchTo().defaultContent();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(oClientButton));
		wait.until(ExpectedConditions.elementToBeClickable(oClientButton));
		TestActions.clickElementWithNoWait(oClientButton);
		MyPrint.myPrint("***","Navigate to client UI screen");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("QALab");
		PageLoadWait.GenericWaitLoad();
		MyPrint.myPrint("Check Map Type in Client UI Viewer","started.");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.LoadMyView(sMapViewName,true,true,1);
	}

	public boolean verifyMapTypeInFloorPlan(String sFloorPlan,boolean bExpectGoToMapButtonDisplay){
		boolean bLocation1=false;
		String sGoToAddress=ArcGISAddress;
		MyPrint.myPrint("Check Map Type in Admin UI>Mapping>Map Editor>Align Floor Plan","started.");
		MapEditor MyLoc = new MapEditor();
		MyLoc.SetMapView(sFloorPlan);
		MyLoc.OpenView();
		TestActions.waitElementClickableReady("DivEdit");
		TestActions.clickElementAndWaitReady("DivEdit");
		TestActions.clickElementAndWaitReady("DivAlignFloorPlan");

		bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
		bLocationEditorCheck=bLocation1;
		bLocationEditorGoToButton=driver.findElements(oLocationEditorGoToButton).size()>0;
		if(!sCurrMapType.equalsIgnoreCase("arcgis")){
			sGoToAddress=sLocalDefault1;
		}

		if(TestActions.GoToAddress(sGoToAddress,bExpectGoToMapButtonDisplay)){
			bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
			this.bLocationEditorCheck=bLocation1 && this.bLocationEditorCheck;
		}
		if(this.bLocationEditorCheck){
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in floor plan image check: Passed");
		} else {
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in floor plan image check: Failed");
		}
		driver.navigate().refresh();
		return bLocationEditorCheck;
	}

	public boolean verifyMapTypeInDetachView(boolean bExpectGoToMapButtonDisplay,Stack<String> sWindowHandlers){
		boolean bLocation1=false;
		String sGoToAddress=ArcGISAddress;
		MyPrint.myPrint("Check Map Type in detached viewer","started.");
		TestActions.DetachMyView(1,sWindowHandlers);
		bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
		bLocationEditorCheck=bLocation1;
		bLocationEditorGoToButton=driver.findElements(oLocationEditorGoToButton).size()>0;
		if(!sCurrMapType.equalsIgnoreCase("arcgis")){
			sGoToAddress=sLocalDefault1;
		}

		if(TestActions.GoToAddress(sGoToAddress,bExpectGoToMapButtonDisplay)){
			bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
			this.bLocationEditorCheck=bLocation1 && this.bLocationEditorCheck;
		}
		if(this.bLocationEditorCheck){
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in detached viewer image check: Passed");
		} else {
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in detached viewer image check: Failed");
		}
		return bLocationEditorCheck;
	}

	public boolean verifyMapType(boolean bExpectGoToMapButtonDisplay){
		boolean bLocation1=false;
		String sGoToAddress=ArcGISAddress;
		String sWinTitle=driver.getTitle();
		MyPrint.myPrint("Check Map Type in window title '" + sWinTitle + "'" ," started.");
		bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
		bLocationEditorCheck=bLocation1;
		bLocationEditorGoToButton=driver.findElements(oLocationEditorGoToButton).size()>0;
		if(!sCurrMapType.equalsIgnoreCase("arcgis")){
			sGoToAddress=sLocalDefault1;
		}
		if(TestActions.GoToAddress(sGoToAddress,bExpectGoToMapButtonDisplay)){
			bLocation1=driver.findElements(oLocationEditorCheck).size()>0;
			this.bLocationEditorCheck=bLocation1 && this.bLocationEditorCheck;
		}
		if(this.bLocationEditorCheck){
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in window title '" + sWinTitle + "' map image check: Passed");
		} else {
			System.out.println("*** Map Type of '" + sCurrMapType + "' displaying in in window title '" + sWinTitle + "' map image check: Failed");
		}
		return bLocationEditorCheck;
	}

	public void geoCenteringModification(String sNewMapTypeName){
		openGEOCoding();
		changeGEOCoding(sNewMapTypeName);
		System.out.println("*** sCurrMapType:" + sCurrMapType);
		System.out.println("***new GEO coding:" + sCurrGEOCodingProvider);
	}	

	public void openMapType(){
		if(!TestActions.ElementExists(By.xpath("//a[@id='geocoding']"))){
			NavAdminMapPolicy();
		}
		if((driver.findElement(By.xpath("//a[@id='mapconfig']/..")).getAttribute("class")).length()==0){
			driver.findElement(By.xpath("//a[@id='mapconfig']")).click();
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(ChangeMapType));
			Assert.assertEquals(true,driver.findElement(By.xpath("//a[@id='mapconfig']/..")).getAttribute("class").equalsIgnoreCase("selected"));
		} else if(driver.findElement(By.xpath("//a[@id='mapconfig']/..")).getAttribute("class").equalsIgnoreCase("selected")) {
			System.out.println("*** Opented Map Type screen.***");
		} else{
			fail("Failed to open Map Type page.");
		}
		sCurrMapType=driver.findElement(By.xpath("//span[@id='currmaptypevalue']")).getText();
		String sNewType=sCurrMapType;
		System.out.println("*** The current Map Type is set to '" + sNewType + "'***");
		oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
		tileCacheCheck="";
		switch (sNewType.toLowerCase()){
		case "arcgis":			
			tileCacheCheck=ArcGIS_URL+"?LAYERS=show";					
			oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
			break;
		case "bing maps":
			tileCacheCheck="tiles.virtualearth.net";
			oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");		
			break;
		case "earth2d":
			break;
		case "open street map":
			tileCacheCheck="http://tile.openstreetmap.org";
			oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
			break;
		case "tilecache":
			oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
			break;
		case "google maps (v3)":
			oLocationEditorCheck=By.xpath("//div[@class='olLayerGooglePoweredBy olLayerGoogleV3 gmnoprint']");
			break;
		case "map server":
			tileCacheCheck=SERVER_URL+ ":" + mapServerPort + "/cgi-bin/mapserv.exe?layers=towns_poly";
			oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
			break;
		case "google maps (v2)":
			System.out.println("*** Changing the Map Type to '" + sNewType + "' is not supported.***");
			break;
		case "none":				
			tileCacheCheck="/vs4/map/images/DefaultMapTile.png";
			oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
			break;
		default:
			break;
		}
		if(sCurrMapType.length()==0){
			System.out.println("******* Failed to get the current map type. ******");
		}
	}
	public void changeMapType(String sNewType){
		By oPopupContent=By.xpath("//div[@id='TB_ajaxContent']");
		By oMapTypesListing=By.xpath("//div[@id='TB_ajaxContent']/div[@class='changemaptype']");
		boolean bCancel=false;
		Assert.assertEquals(true,driver.findElement(By.xpath("//a[@id='mapconfig']/..")).getAttribute("class").equalsIgnoreCase("selected"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='mapHeader']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mapHeader']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(ChangeMapType));
		wait.until(ExpectedConditions.elementToBeClickable(ChangeMapType));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady(ChangeMapType);
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.elementToBeClickable(oPopupContent));
		wait.until(ExpectedConditions.elementToBeClickable(oMapTypesListing));
		if(sNewType.length()>0){
			if(sCurrMapType.length()>0){				
				if(driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='map-option']//label[@class='grey'][contains(.,'" + sNewType + "')]")).size()==0){
					Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='map-option']//label[@class='grey'][contains(.,'" + sCurrMapType + "')]")).size()>0);
					Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='map-option']//label[contains(.,'" + sNewType + "')]")).size()>0);
					driver.findElement(oMapTypesListing).findElement(By.xpath(".//div[@id='map-option']//label[contains(.,'" + sNewType + "')]")).click();
					PageLoadWait.GenericWaitLoad();
					Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElement(By.xpath(".//div[@id='map-option']//label[contains(.,'" + sNewType + "')]/../input")).isSelected());
					Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='map-option']//label[contains(.,'" + sNewType + "')]")).size()>0);
				} else {
					bCancel=true;
					System.out.println("******* WARNING *******: Current Map Type has been set to type of '" + sNewType + "'***** Please review the test case workflow.");
				}
			} else {
				System.out.println("******* Failed to get the current map type. ******");
			}
		} else {
			fail("*** Please use valid Map Type. The current map type using '" + sNewType + "' is invalid.***");
		}
		if(bCancel){
			driver.findElement(oPopupContent).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Cancel')]")).click();
		} else {
			driver.findElement(oPopupContent).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Save')]")).click();
		}
		PageLoadWait.GenericWaitLoad();
		sPreviousMapType=sCurrMapType;
		sCurrMapType=sNewType;
		Assert.assertEquals(true,driver.findElement(By.xpath("//span[@id='currmaptypevalue']")).getText().equalsIgnoreCase(sNewType));
		wait.until(ExpectedConditions.elementToBeClickable(ChangeMapType));
		TestActions.clickElementAndWaitReady(ChangeMapType);
		TestActions.robotWait(3);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.elementToBeClickable(oPopupContent));
		wait.until(ExpectedConditions.elementToBeClickable(oMapTypesListing));
		Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='map-option']//label[@class='grey'][contains(.,'" + sCurrMapType + "')]")).size()>0);
		if(!sCurrMapType.equalsIgnoreCase("none")){
			Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElement(By.xpath(".//div[@id='map-option']/div/input[@name='maptype']")).getAttribute("checked").equalsIgnoreCase("true"));
		} else {
			Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='map-option']//input[@name='maptype']")).get(1).getAttribute("checked").equalsIgnoreCase("true"));
		}
		driver.findElement(oPopupContent).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Cancel')]")).click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		switch (sNewType.toLowerCase()){
		case "arcgis":
			if(changeMapTypeArcGIS()){
				System.out.println("*** Changed the Map Type to '" + sNewType + "'***");					
				tileCacheCheck=ArcGIS_URL+"?LAYERS=show";					
				oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
				oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
			} else {
				fail("*** Error in changing the Map Type to '" + sNewType + "'***");
			}
			break;
		case "bing maps":
			if(changeMapTypeBingMap()){
				System.out.println("*** Changed the Map Type to '" + sNewType + "'***");
				tileCacheCheck="tiles.virtualearth.net";
				oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
				oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
			} else {
				fail("*** Error in changing the Map Type to '" + sNewType + "'***");
			}
			break;
		case "earth2d":
			//				fail("*** We don't know how to configure in the test environment for map type '" + sNewType + "'.***");
			System.out.println("*** We don't know how to configure in the test environment for map type '" + sNewType + "'.***");
			break;
		case "open street map":
			if(changeMapTypeOpenStreet()){

				tileCacheCheck="http://tile.openstreetmap.org";
				System.out.println("*** Changed the Map Type to '" + sNewType + "'***");
				oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
				oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
			} else {
				fail("*** Error in changing the Map Type to '" + sNewType + "'***");
			}
			break;
		case "tilecache":
			if(changeMapTypeTileCache()){
				System.out.println("*** Changed the Map Type to '" + sNewType + "'***");
				oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
				oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
			} else {
				fail("*** Error in changing the Map Type to '" + sNewType + "'***");
			}
			break;
		case "google maps (v3)":
			if(changeMapTypeGoogleV3()){
				System.out.println("*** Changed the Map Type to '" + sNewType + "'***");
				oLocationEditorCheck=By.xpath("//div[@class='olLayerGooglePoweredBy olLayerGoogleV3 gmnoprint']");
				oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
			} else {
				fail("*** Error in changing the Map Type to '" + sNewType + "'***");
			}
			break;
		case "map server":
			if(changeMapTypeMapServer()){
				System.out.println("*** Changed the Map Type to '" + sNewType + "'***");
				tileCacheCheck=SERVER_URL+ ":" + mapServerPort + "/cgi-bin/mapserv.exe?layers=towns_poly";
				oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
				oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
			} else {
				fail("*** Error in changing the Map Type to '" + sNewType + "'***");
			}
			break;
		case "google maps (v2)":
			System.out.println("*** Changing the Map Type to '" + sNewType + "' is not supported.***");
			break;
		case "none":
			if(changeMapTypeNone()){
				System.out.println("*** Changed the Map Type to '" + sNewType + "'***");
				tileCacheCheck="/vs4/map/images/DefaultMapTile.png";
				oLocationEditorCheck=By.xpath("//img[contains(@src,'"+tileCacheCheck+"')]");			
				oLocationEditorGoToButton=By.xpath("//div[@class='olControlGeocodeButtonItemActive olButton']");
			} else {
				fail("*** Error in changing the Map Type to '" + sNewType + "'***");
			}
			break;
		default:
			break;
		}
	}


	public void openGEOCoding(){		
		if(!TestActions.ElementExists(By.xpath("//a[@id='geocoding']"))){
			NavAdminMapPolicy();
		}
		if((driver.findElement(By.xpath("//a[@id='geocoding']/..")).getAttribute("class")).length()==0){
			driver.findElement(By.xpath("//a[@id='geocoding']")).click();
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(ChangeMapGeocodingProvider));
			Assert.assertEquals(true,driver.findElement(By.xpath("//a[@id='geocoding']/..")).getAttribute("class").equalsIgnoreCase("selected"));
		} else if(driver.findElement(By.xpath("//a[@id='geocoding']/..")).getAttribute("class").equalsIgnoreCase("selected")) {
			System.out.println("*** Opened Map Geocoding screen.***");
		} else{
			fail("Failed to open Map Geocoding.");
		}
		sCurrGEOCodingProvider=driver.findElement(By.xpath("//span[@id='currgeocodertypevalue']")).getText();
	}

	public boolean changeGEOCoding(String sNewGEOCoding){
		boolean bReturn=false;
		boolean bCancel=false;
		By oPopupContent=By.xpath("//div[@id='TB_ajaxContent']");
		By oMapTypesListing=By.xpath("//div[@id='TB_ajaxContent']/div[@class='changeGeocoderType']");
		By inputData=By.id("mapGeocodingDIV");
		By inputForm;
		try{
			TestActions.waitTilElementVisible(driver.findElement(inputData).findElement(ChangeMapGeocodingProvider));
			Assert.assertEquals(true,driver.findElement(inputData).findElement(oMapType).getText().toLowerCase().contains(("Current Map Geocoding Provider").toLowerCase()));
			if(sCurrGEOCodingProvider.equalsIgnoreCase(sNewGEOCoding)){
				System.out.println("*** Current system GEO coding is set to '" + sCurrGEOCodingProvider + "'. No action needed.***" );
//				return true;
			} else {
				driver.findElement(inputData).findElement(ChangeMapGeocodingProvider).click();				
				wait.until(ExpectedConditions.elementToBeClickable(oPopupContent));
				wait.until(ExpectedConditions.elementToBeClickable(oMapTypesListing));
				if(sNewGEOCoding.length()>0){
					if(driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='geocoder-option']//label[@class='grey'][contains(.,'" + sNewGEOCoding + "')]")).size()==0){
						Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='geocoder-option']//label[@class='grey'][contains(.,'" + sCurrGEOCodingProvider + "')]")).size()>0);
						Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='geocoder-option']//label[contains(.,'" + sNewGEOCoding + "')]")).size()>0);
						if(sCurrGEOCodingProvider.equalsIgnoreCase("none")){
							Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//input")).get(1).isSelected());
						} else {
							Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//input")).get(0).isSelected());
						}						
						driver.findElement(oMapTypesListing).findElement(By.xpath(".//div[@id='geocoder-option']//label[contains(.,'" + sNewGEOCoding + "')]")).click();
						PageLoadWait.GenericWaitLoad();
						Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElement(By.xpath(".//div[@id='geocoder-option']//label[contains(.,'" + sNewGEOCoding + "')]/../input")).isSelected());
						Assert.assertEquals(true,driver.findElement(oMapTypesListing).findElements(By.xpath(".//div[@id='geocoder-option']//label[contains(.,'" + sNewGEOCoding + "')]")).size()>0);
					} else {
						bCancel=true;
						System.out.println("******* WARNING *******: Current Map GEO Coding Type has been set to type of '" + sNewGEOCoding + "'***** Please review the test case workflow.");
					}
				} else {
					fail("*** Please use valid Map GEO Coding Type. The current map GEO Coding type using '" + sNewGEOCoding + "' is invalid.***");
				}
				if(bCancel){
					driver.findElement(oPopupContent).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Cancel')]")).click();
				} else {
					driver.findElement(oPopupContent).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Save')]")).click();
				}
				PageLoadWait.GenericWaitLoad();
				sPreviousGEOCoding=sCurrGEOCodingProvider;
				sCurrGEOCodingProvider=sNewGEOCoding;
				Assert.assertEquals(true,driver.findElement(By.xpath("//span[@id='currgeocodertypevalue']")).getText().equalsIgnoreCase(sCurrGEOCodingProvider));
			}
			switch (sNewGEOCoding.toLowerCase()){
			case "arcgis":
				inputForm=By.id("arcgis");
				try{
					driver.findElement(inputData).findElement(inputForm).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Edit')]")).click();
					PageLoadWait.GenericWaitLoad();
					driver.findElement(inputData).findElement(inputForm).findElement(By.name("arcGISUrl")).clear();
					driver.findElement(inputData).findElement(inputForm).findElement(By.name("arcGISUrl")).sendKeys(BaseURL_Geocoding_ArcGIS);
					driver.findElement(inputData).findElement(inputForm).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Save')]")).click();
					System.out.println("*** Changed the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
				} catch (Exception ignore) {
					fail("*** Error in changing the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
				}
				break;
			case "none":
				System.out.println("*** Changed the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
				break;
			case "google":
				inputForm=By.id("googlev3");
				try{
					driver.findElement(inputData).findElement(inputForm).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Edit')]")).click();
					PageLoadWait.GenericWaitLoad();
					PageLoadWait.GenericWaitLoad();
					driver.findElement(inputData).findElement(inputForm).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Save')]")).click();
					System.out.println("*** Changed the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
				} catch (Exception ignore) {
					fail("*** Error in changing the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
				}
				break;
			case "open street map":
				inputForm=By.id("osm");
				try{
					driver.findElement(inputData).findElement(inputForm).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Edit')]")).click();
					PageLoadWait.GenericWaitLoad();
					driver.findElement(inputData).findElement(inputForm).findElement(By.name("osmUrl")).clear();
					driver.findElement(inputData).findElement(inputForm).findElement(By.name("osmUrl")).sendKeys(sOpenStreetMapGEOBaseURL);
					driver.findElement(inputData).findElement(inputForm).findElement(By.xpath(".//div[@class='buttonFactory'][contains(.,'Save')]")).click();
					System.out.println("*** Changed the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
				} catch (Exception ignore) {
					fail("*** Error in changing the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
				}
				break;
			default:
				break;
			}
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			Assert.assertEquals(true,driver.findElement(By.xpath("//span[@id='currgeocodertypevalue']")).getText().equalsIgnoreCase(sCurrGEOCodingProvider));
			bReturn=true;
		} catch (Exception ignore){
			fail("*** Error in changing the Map GEO Coding Type to '" + sNewGEOCoding + "'***");
		}
		return bReturn;	
	}
	public boolean changeMapTypeBingMap(){
		boolean bReturn=false;
		By inputData=By.id("bingdata");
		try{
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Edit')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(By.name("bingApiKey")).clear();
			driver.findElement(By.name("bingApiKey")).sendKeys(sBingAPIKey);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Save')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("editbing")));
			Assert.assertEquals(true,driver.findElement(By.id("currmaptypevalue")).getText().equalsIgnoreCase("Bing Maps"));
			bReturn=true;
		} catch (Exception ignore){}
		return bReturn;
	}
	public boolean changeMapTypeTileCache(){
		boolean bReturn=false;
		By inputData=By.id("tilecachedata");
		try{
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Edit')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tilecachedata']//li/div[contains(.,'Save')][@class='buttonFactory']")));
			Assert.assertEquals(true,driver.findElement(inputData).findElements(By.xpath(".//li/div[contains(.,'Save')][@class='buttonFactory']")).size()==1);
			if(MapTypeTilecacheModification){
				driver.findElement(inputData).findElement(By.name("mapserverHostName")).clear();
				driver.findElement(inputData).findElement(By.name("mapserverHostName")).sendKeys(tileCacheBaseUrl);
				driver.findElement(inputData).findElement(By.name("mapserverFormat")).clear();
				driver.findElement(inputData).findElement(By.name("mapserverFormat")).sendKeys(tileCacheFormat);
				driver.findElement(inputData).findElement(By.name("mapserverProjection")).clear();
				driver.findElement(inputData).findElement(By.name("mapserverProjection")).sendKeys(tileCacheProjection);
				driver.findElement(inputData).findElement(By.name("mapserverPort")).clear();
				driver.findElement(inputData).findElement(By.name("mapserverPort")).sendKeys(tileCachePort);
				driver.findElement(inputData).findElement(By.name("mapserverMaxZoom")).clear();
				driver.findElement(inputData).findElement(By.name("mapserverMaxZoom")).sendKeys(tileCacheMaxZoom);
			}
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Save')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Edit')][@class='buttonFactory']"))));
			Assert.assertEquals(true,driver.findElement(By.id("currmaptypevalue")).getText().equalsIgnoreCase("Tilecache"));
			bReturn=true;
		} catch (Exception ignore){}
		return bReturn;
	}

	public boolean changeMapTypeOpenStreet(){
		boolean bReturn=false;
		By inputData=By.id("osmdata");
		try{
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Edit')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(By.name("osmHostName")).clear();
			driver.findElement(By.name("osmHostName")).sendKeys(sOpenStreetMapBaseURL);
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Save')][@class='buttonFactory']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("editosm")));
			Assert.assertEquals(true,driver.findElement(By.id("currmaptypevalue")).getText().equalsIgnoreCase("Open Street Map"));
			bReturn=true;
		} catch (Exception ignore){}
		return bReturn;
	}

	public boolean changeMapTypeArcGIS(){
		boolean bReturn=false;
		By inputData=By.id("arcgisdata");
		try{
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Edit')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(inputData).findElement(By.name("arcGisExportUrl")).clear();
			driver.findElement(inputData).findElement(By.name("arcGisExportUrl")).sendKeys(ArcGIS_URL);
			driver.findElement(inputData).findElement(By.name("arcGisLayers")).clear();
			driver.findElement(inputData).findElement(By.name("arcGisLayers")).sendKeys(ArcGIS_Layers);
			driver.findElement(inputData).findElement(By.name("arcGisProjection")).clear();
			driver.findElement(inputData).findElement(By.name("arcGisProjection")).sendKeys(ArcGIS_Projection);
			driver.findElement(inputData).findElement(By.xpath(".//input[@name='mapserverMaxZoom']")).clear();
			driver.findElement(inputData).findElement(By.xpath(".//input[@name='mapserverMaxZoom']")).sendKeys(ArcGIS_MaxZoom);
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Save')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("editarcgis")));
			Assert.assertEquals(true,driver.findElement(By.id("currmaptypevalue")).getText().equalsIgnoreCase("ArcGIS"));
			bReturn=true;
		} catch (Exception ignore){}
		return bReturn;
	}

	public boolean changeMapTypeNone(){
		Assert.assertEquals(true,driver.findElement(By.id("currmaptypevalue")).getText().equalsIgnoreCase("None"));
		return true;
	}

	public boolean changeMapTypeMapServer(){
		boolean bReturn=false;
		By inputData=By.id("mapserverdata");
		try{
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Edit')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(inputData).findElement(By.xpath(".//select[@name='mapfile-location']//option[contains(.,'" + mapServerMapFiles + "')]")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Save')][@class='buttonFactory']")).click();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("editmapserver")));
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			Assert.assertEquals(true,driver.findElement(By.id("currmaptypevalue")).getText().equalsIgnoreCase("Map Server"));
			bReturn=true;
		} catch (Exception ignore){}
		return bReturn;
	}

	public boolean changeMapTypeGoogleV3(){
		boolean bReturn=false;
		By inputData=By.id("googlev3data");
		try{
			if(MapTypeGoogleV3Modification){
				//////			By default GoogleV3 is selected and enabled.
				driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Edit')][@class='buttonFactory']")).click();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				driver.findElement(By.name("googleV3ApiProtocol")).findElement(By.xpath(".//option[contains(.," + MapTypeGoogleV3Protocol + ")]")).click();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				driver.findElement(By.name("googleV3ApiType")).findElement(By.xpath(".//option[contains(.," + MapTypeGoogleV3APIType + ")]")).click();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				driver.findElement(By.name("mapserverMaxZoom")).clear();
				driver.findElement(By.name("mapserverMaxZoom")).sendKeys(MapTypeGoogleV3APIKey);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				driver.findElement(By.name("googleV3TransitLayer")).findElement(By.xpath(".//option[contains(.," + MapTypeGoogleV3Layer + ")]")).click();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				driver.findElement(inputData).findElement(By.xpath(".//li/div[contains(.,'Save')][@class='buttonFactory']")).click();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				wait.until(ExpectedConditions.elementToBeClickable(By.id("editgooglev3")));			
			}
			Assert.assertEquals(true,driver.findElement(By.id("currmaptypevalue")).getText().equalsIgnoreCase("Google Maps (V3)"));
			bReturn=true;
		} catch (Exception ignore){}
		return bReturn;
	}
}
