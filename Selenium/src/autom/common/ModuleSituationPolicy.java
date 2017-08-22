package autom.common;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
//import org.testng.AssertJUnit;

public class ModuleSituationPolicy extends MasterTest{
	public String SituationType;
	private String ActionPlan;
	private String myTag1="ClientButton";
	private String myTag2="Policy";
	private String myTag3="Situation Policy";
	private String ActionPlanTitle="";
	private String ActionPlanType="";
	protected String sTextRGB,sBckgrndRGB;
	protected ArrayList<String> SeverityNameList = new ArrayList<String>();
	public ModuleSituationPolicy(String... SituationPolicy) {
		MyPrint.myPrint("ModuleSituationPolicy","Inside Module Policy constructor with '" + SituationPolicy + "'");
		ActionPlan=SituationPolicy[0];
		try {
			OpenSituationPolicy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ModuleSituationPolicy() {
		PageLoadWait.GenericWaitLoad();
		Navigate();
	}

	public void OpenSituationPolicy() throws Exception{
		Navigate();
	}
	
	public void setSituationType(String sTypeName){
		SituationType=sTypeName;
	}
	public void Navigate() {
		TestActions.Navigation(myTag1,myTag2,myTag3);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='viewCatType']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='resetSoundOrder']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='buttonFactory'][@id='addcat']"))); 		
	}    
	public void SetActionPlan(String sActionPlan){
		ActionPlanTitle="";
		ActionPlanType="";
		ActionPlan=sActionPlan;
	} 

	public void SelectDefaultType(String sNewTypeName){
		By oSituationType=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + sNewTypeName + "']");
		By oSituationTypeDefault=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + sNewTypeName + "']/following::td[@class='defaulttype']");
		By oSituationActionPlanTitle=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + sNewTypeName + "']/following::td[@class='action']");
		if(TestActions.ElementExists(oSituationType)){
			TestActions.clickElementAndWaitReady(oSituationType);
			TestActions.clickElementAndWaitReady(oSituationTypeDefault);
			TestActions.robotWait(1);
		}
		this.SituationType=sNewTypeName;
		this.ActionPlanTitle=TestActions.getElement(oSituationActionPlanTitle).getText();
		PageLoadWait.GenericWaitLoad();
	}
	
	public void SelectDefaultType(){
		By oSituationType=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + SituationType + "']");
		By oSituationTypeDefault=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + SituationType + "']/following::td[@class='defaulttype']");
		By oSituationActionPlanTitle=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + SituationType + "']/following::td[@class='action']");
		
		
		wait.until(ExpectedConditions.elementToBeClickable(oSituationType));
		PageLoadWait.GenericWaitLoad();
		TestActions.clickElementAndWaitReady(oSituationType);
		TestActions.clickElementAndWaitReady(oSituationTypeDefault);
		TestActions.robotWait(1);
		this.ActionPlanTitle=TestActions.getElement(oSituationActionPlanTitle).getText();
		PageLoadWait.GenericWaitLoad();
	}
	
	public void SelectType(String sNewTypeName){
		By oSituationType=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + sNewTypeName + "']");
//		By oSituationTypeDefault=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + sNewTypeName + "']/following::td[@class='defaulttype']");
		By oSituationActionPlanTitle=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + sNewTypeName + "']/following::td[@class='action']");
		if(TestActions.ElementExists(oSituationType)){
			TestActions.clickElementAndWaitReady(oSituationType);
			TestActions.robotWait(1);
		}
		this.SituationType=sNewTypeName;
		this.ActionPlanTitle=TestActions.getElement(oSituationActionPlanTitle).getText();
		PageLoadWait.GenericWaitLoad();
	}

	public void SelectType(){
		By oSituationType=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + SituationType + "']");
//		By oSituationTypeDefault=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + SituationType + "']/following::td[@class='defaulttype']");
		By oSituationActionPlanTitle=By.xpath("//table[@id='category-list-noneorder']//tr/td[@title='" + SituationType + "']/following::td[@class='action']");
		if(TestActions.ElementExists(oSituationType)){
			TestActions.clickElementAndWaitReady(oSituationType);
			TestActions.robotWait(1);
		}
		this.ActionPlanTitle=TestActions.getElement(oSituationActionPlanTitle).getText();
		PageLoadWait.GenericWaitLoad();
	}

	public void ModifyType(){
		By oTypeEditButton=By.xpath("//ul/li/div[@class='buttonFactory'][@id='editcat']");
		TestActions.waitElementClickableReady(oTypeEditButton);
		TestActions.clickElementAndWaitReady(oTypeEditButton);
//		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='create-new-cat']/div[@class='buttonwrapper']/ul/li/div[@id='saveCreate']")));
	}
	
	public void SetSameStyle(boolean bSame){
		By oSameStyle=By.xpath("//select[@name='useoneicon']");
		String sOption;
		if(bSame){
			sOption="Yes";
		} else {
			sOption="No";
		}
		TestActions.DropDownSelect(oSameStyle, sOption);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='create-new-cat']/div[@class='buttonwrapper']/ul/li/div[@id='saveCreate']")));
	}
	
	public void setSeveritySameStyle(){
		By oUseOneIconTable=By.xpath("//div[@id='useoneicon'][contains(@style,'display: block')]//table[@id='sitTypeSeverityUseOneIcon']");		
		By oDefaultSeverityImg=By.xpath(".//td[@class='sitTypeSeverityIcon']/*[1]");
		By oChangeSeverityImg=By.xpath(".//img[@class='iconselector']");
		SetSameStyle(true);
		TestActions.waitElementpresence(oUseOneIconTable);
		if(driver.findElement(oUseOneIconTable).findElement(oDefaultSeverityImg).getAttribute("src").contains("na.png")){
			MyPrint.myPrint("WARNING","Default situation icon is not loaded. Select pushpin_red as the default.");
			driver.findElement(oUseOneIconTable).findElement(oChangeSeverityImg).click();
			PageLoadWait.GenericWaitLoad();
			By SituationIconListPopup = By.xpath("//div[@id='situationIconPopUpNew'][contains(@style,'display: block')]");
			By SituationIconListPopupIconTable = By.xpath("//div[@id='situationIconPopUpNew'][contains(@style,'display: block')]/div/table[@class='allIcon']");
			TestActions.waitElementpresence(SituationIconListPopup);
			TestActions.waitElementpresence(SituationIconListPopupIconTable);
			driver.findElement(SituationIconListPopupIconTable).findElement(By.xpath(".//td[.='map_icon_pushpin_red.png']")).click();
			PageLoadWait.GenericWaitLoad();
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='create-new-cat']/div[@class='buttonwrapper']/ul/li/div[@id='saveCreate']")));
		driver.findElement(By.id("saveCreate")).click();
		PageLoadWait.GenericWaitLoad();
	}




	
	public void setSeverityIconSameDefaultStyle(){
		By oUseOneIconTable=By.xpath("//div[@id='useoneicon'][contains(@style,'display: block')]//table[@id='sitTypeSeverityUseOneIcon']");		
		By oDefaultSeverityImg=By.xpath(".//td[@class='sitTypeSeverityIcon']/*[1]");
		By oChangeSeverityImg=By.xpath(".//img[@class='iconselector']");
		SetSameStyle(true);
		
		
		By oUseOneIconTable2=By.xpath("//div[@id='useoneicon'][contains(@style,'display: block')]/div[@id='default-section']/table[@id='sitTypeSeverityUseOneIcon']");
		
		
		
		TestActions.waitElementpresence(oUseOneIconTable2);
		if(driver.findElement(oUseOneIconTable2).findElement(oDefaultSeverityImg).getAttribute("src").contains("na.png")){
//			driver.findElement(oUseOneIconTable).findElement(oChangeSeverityImg).click();
//			PageLoadWait.GenericWaitLoad();
//			By SituationIconListPopup = By.xpath("//div[@id='situationIconPopUpNew'][contains(@style,'display: block')]");
//			By SituationIconListPopupIconTable = By.xpath("//div[@id='situationIconPopUpNew'][contains(@style,'display: block')]/div/table[@class='allIcon']");
//			TestActions.waitElementpresence(SituationIconListPopup);
//			TestActions.waitElementpresence(SituationIconListPopupIconTable);
//			driver.findElement(SituationIconListPopupIconTable).findElement(By.xpath(".//td[.='map_icon_pushpin_red.png']")).click();
//			PageLoadWait.GenericWaitLoad();
			Assert.fail("Default situation icon is not loaded.");
			
		}
		driver.findElement(By.id("saveCreate")).click();
		PageLoadWait.GenericWaitLoad();
	}
	
	
	public void EnableListView(boolean bEnable, String sServerityName, String[]sColor){
		By oListViewTable=By.xpath("//table[@id='sitTypeSeverityUseThreeIcon']");
		By oTypeTRRow=By.xpath(".//tr/td[@class='sitTypeSeverityName'][contains(.,'" + sServerityName + "')]/..");
		By oTextJpicker=By.xpath("(//div[@class='jPicker Container'][contains(@style, '299999')])");
		String sListViewFlagString="Text Color:";
		List<WebElement> Optionlists;
//		ArrayList<String> SeverityNameList = new ArrayList<String>();

		boolean bCustListViewEnabled=false;
		
//		oTypeTRRow=By.xpath(".//tr/td[@class='sitTypeSeverityName'][contains(.,'" + "Low" + "')]/..");
		
		if(driver.findElement(oListViewTable).findElement(oTypeTRRow).getText().contains(sListViewFlagString)){
			bCustListViewEnabled=true;
		}
		WebElement oEnableViewCheckBox=driver.findElement(oListViewTable).findElement(oTypeTRRow).findElement(By.xpath(".//input"));
		if(bEnable){
			if(!bCustListViewEnabled){
				driver.findElement(oListViewTable).findElement(oTypeTRRow).findElements(By.xpath(".//input")).get(1).click();
			}
//				TestActions.robotWait(3);
				TestActions.waitElementVisibility(driver.findElement(oListViewTable).findElement(oTypeTRRow).findElements(By.xpath(".//span[@class='Color']")).get(0));
				driver.findElement(oListViewTable).findElement(oTypeTRRow).findElements(By.xpath(".//span[@class='Color']")).get(0).click();
				TestActions.robotWait(1);
				driver.findElement(oTextJpicker).findElement(By.xpath(".//input[@class='Hex']")).clear();
				driver.findElement(oTextJpicker).findElement(By.xpath(".//input[@class='Hex']")).sendKeys(sColor[0]);
				TestActions.robotWait(1);
				driver.findElement(oTextJpicker).findElement(By.xpath(".//input[@class='Ok']")).click();
				TestActions.robotWait(1);
				driver.findElement(oListViewTable).findElement(oTypeTRRow).findElements(By.xpath(".//span[@class='Color']")).get(1).click();
				TestActions.robotWait(1);
				
				driver.findElement(oTextJpicker).findElement(By.xpath(".//input[@class='Hex']")).clear();
				driver.findElement(oTextJpicker).findElement(By.xpath(".//input[@class='Hex']")).sendKeys(sColor[1]);
				TestActions.robotWait(1);
				driver.findElement(oTextJpicker).findElement(By.xpath(".//input[@class='Ok']")).click();
				TestActions.robotWait(1);
				sTextRGB=(driver.findElement(oListViewTable).findElement(oTypeTRRow).findElements(By.xpath(".//span[@class='Color']")).get(0).getAttribute("style")).split(":\\s+")[1];
				sBckgrndRGB=(driver.findElement(oListViewTable).findElement(oTypeTRRow).findElements(By.xpath(".//span[@class='Color']")).get(1).getAttribute("style")).split(":\\s+")[1];
				System.out.println("textfont color=" + sTextRGB);
				System.out.println("background color=" + sBckgrndRGB);
				
				WebElement list=driver.findElement(By.xpath("//select[@name='soundLoopThreshold']"));//.findElements(By.xpath(".//option"));
				Select dropdown = new Select(list);
				Optionlists=dropdown.getOptions();
				String sOptionText;
				for (WebElement option : Optionlists) {
					sOptionText=option.getText().trim();
					if(sOptionText.length()>0){
						if(!sOptionText.equalsIgnoreCase("none")){
							SeverityNameList.add(sOptionText.toLowerCase());
						}
					}
				}
				driver.findElement(By.id("saveCreate")).click();
		} else {
			if(oEnableViewCheckBox.isSelected()){
				driver.findElement(oListViewTable).findElement(oTypeTRRow).findElements(By.xpath(".//input")).get(1).click();
			}
		}

	}
	
	
	
	
	
	
	public void AddNewType(String sNewTypeName){
		MyPrint.myPrint("ModuleSituationPolicy","add new policy type: " + sNewTypeName);
		PageLoadWait.GenericWaitLoad();
		this.SituationType=sNewTypeName;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addcat']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='resetSoundOrder']")));
		if (driver.getPageSource().contains(sNewTypeName)) {
			DeleteNewType(sNewTypeName);
		}
		TestActions.clickElementAndWaitReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		TestActions.waitElementVisibility("name","upload-actionplan");
		TestActions.waitElementVisibility("xpath","//div[@id='tourDirectoryTable']//td");
		String FirstDeviceName;
		FirstDeviceName=TestActions.getElement("xpath","//div[@id='tourDirectoryTable']//td").getText();
		WebElement SituationTypeElement=driver.findElement(By.name("cattype"));
		SituationTypeElement.click();
		PageLoadWait.GenericWaitLoad();
		SituationTypeElement.sendKeys(sNewTypeName);
		TestActions.SetElementValue(SituationTypeElement,sNewTypeName);

		PageLoadWait.GenericWaitLoad();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + FirstDeviceName + "')]")));
		WebElement ListDev=driver.findElement(By.xpath("//td[contains(text(), '" + FirstDeviceName + "')]"));
		ListDev.click();
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '" + FirstDeviceName + "')]")));
		WebElement AddImg=driver.findElement(By.xpath("//img[@src='/vs4/admin/img/interface/icon_right_arrow_black.png']"));
		AddImg.click();
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@id='formToursList']//td[contains(text(), '" + FirstDeviceName + "')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("saveCreate")));
		WebElement clientButton=driver.findElement(By.id("saveCreate"));
		Action hover = builder.moveToElement(clientButton).click().build();
		hover.perform();
		PageLoadWait.GenericWaitLoad();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteButton")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), '"+ sNewTypeName + "')]")));
		Assert.assertEquals(true,driver.getPageSource().contains(sNewTypeName));
	}

	public void SearchActionPlan(boolean bExpectedResult){
		SearchActionPlan(ActionPlan,bExpectedResult);
	}

	public void SearchActionPlan(String sActionPlanName,boolean bExpectedResult){
		By oSearchButton=By.linkText("Apply");
		By oSearchField=By.xpath("//input[@id='Search']");
		By oActionPlan=By.xpath("//td[@class='apname'][contains(text(), '" + sActionPlanName + "')]");
		By oResetSearch=By.linkText("Reset");
		openActionPlan();
		TestActions.waitElementClickableReady(oResetSearch);
		TestActions.clickElementWithNoWait(oResetSearch);
		TestActions.waitElementClickableReady(oSearchField);
		TestActions.clickElementWithNoWait(oSearchField);
		TestActions.SetElementValue(oSearchField,sActionPlanName);
		TestActions.clickElementWithNoWait(oSearchButton);
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(bExpectedResult,TestActions.ElementExists(oActionPlan));
	}

	public void openActionPlan(){
		String sActionPlanTabString="Action Plans";
		By oActionPlanUnselectedTab=By.xpath("//a[@class='linkUnselected '][contains(text(), '" + sActionPlanTabString + "')]");
		By oActionPlanSelectedTab=By.xpath("//a[@class='linkSelected '][contains(text(), '" + sActionPlanTabString + "')]");
		if(TestActions.ElementExists(oActionPlanUnselectedTab)){
			TestActions.clickElementWithNoWait(oActionPlanUnselectedTab);
			TestActions.waitElementCssValue(oActionPlanSelectedTab, "visibility","visible");
		} else {
			if(!TestActions.ElementExists(oActionPlanSelectedTab)){
				Navigate();
				TestActions.waitElementClickableReady(oActionPlanUnselectedTab);
				TestActions.clickElementWithNoWait(oActionPlanUnselectedTab);
				PageLoadWait.GenericWaitLoad();
				TestActions.waitElementCssValue(oActionPlanSelectedTab, "visibility","visible");
			}
		}
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
	}

	public void UploadActionPlan(String sActionPlanNameFileName){
		String sActionPlanName=sActionPlanNameFileName.split("\\.")[0];
		By oFilePathField=By.xpath("//form[@name='uploadActionPlan']//input[@name='uploadAP']");
		By oLoadedFileElement =By.xpath("//form[@name='uploadActionPlan']//span[contains(@title,'" + sActionPlanNameFileName+ "')]");
		By oUploadButton=By.xpath("//input[@id='uploadAPButton'][@value='Upload']");
		ActionPlan=sActionPlanName;
		openActionPlan();
		PageLoadWait.GenericWaitLoad();
		File flrPlnNeTWorkImage= new File("\\\\mafileserver\\GroupStorageNoBackup\\HZuo\\test\\testfiles\\" + sActionPlanNameFileName);
		String flrPlnLocalImagePath="C:\\temp\\"+sActionPlanNameFileName;
		File flrPlnLocalImage= new File(flrPlnLocalImagePath);
		if(!TestActions.exists(flrPlnLocalImage)){
			try {
				TestActions.copyFile(flrPlnNeTWorkImage, flrPlnLocalImage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(oFilePathField).sendKeys(flrPlnLocalImagePath);
		PageLoadWait.GenericWaitLoad();
		Assert.assertEquals(true,TestActions.ElementExists(oLoadedFileElement));
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		TestActions.mouseOverElementRobotClick(oUploadButton);
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();

		By oLocatorSuccessLoadLable=By.xpath("//div[@class='uploadstatus']");
		Assert.assertEquals(true,driver.findElement(oLocatorSuccessLoadLable).getText().toLowerCase().contains(sActionPlanName.toLowerCase()));
		Assert.assertEquals(true,driver.findElement(oLocatorSuccessLoadLable).getText().toLowerCase().contains("file was successfully uploaded"));
		By oActionPlanDelete=By.xpath("//tr[contains(@id,'iconid')][contains(., '" + sActionPlanName + "')]//following::td[@class='deleteap']//img");
		TestActions.waitElementClickableReady(oActionPlanDelete);
		MyPrint.myPrint("Upload action plan with name:" + sActionPlanName + " successfully"," This test plan is listed.");
	}
	public void DeleteActionPlan(){
		DeleteActionPlan(ActionPlan);
	}   
	public void DeleteActionPlan(String sActionPlanName){
		By oActionPlan=By.xpath("//tr[contains(@id,'iconid')][contains(., '" + sActionPlanName + "')]");    	
		By oActionPlanDelete=By.xpath(".//following::td[@class='deleteap']//img");
		openActionPlan();
		WebElement oDeleteIcon=TestActions.getElement(oActionPlan,oActionPlanDelete);
		if(oDeleteIcon==null){
			MyPrint.myPrint("Delete action plan with name:" + sActionPlanName + " error."," This test plan doesn't exist at currently page.");
		} else if(!oDeleteIcon.getAttribute("title").toLowerCase().contains("is not available")){
			TestActions.ElementScrollIntoView(oDeleteIcon);
			TestActions.mouseOverElementRobotClick(oDeleteIcon);
			TestActions.selectOKAlert();
			wait.until(ExpectedConditions.stalenessOf(oDeleteIcon));
			Assert.assertEquals(false,TestActions.ElementExists(oActionPlan,oActionPlanDelete));
			MyPrint.myPrint("Verified that action plan with name:" + sActionPlanName + " was deleted successfully"," This test plan doesn't exist at current page any more.");
		} else {
			MyPrint.myPrint("Delete action plan with name:" + sActionPlanName + " error"," This test plan cannot be deleted.");
		}
	}
	public void SetActionPlanMandatory(boolean bTurnOn){
		boolean bTurnedOnNow=false,bClickOnemore=false;
		String imgSrc; 
		String ActionPlanBridgeString = "";
		String PropertyName = "";
		By oSituationActionPlanTitle=null;
		WebElement MandaryImg;
		openActionPlan();
		TestActions.robotWaitMS(200);
		PageLoadWait.GenericWaitLoad();
		if(ActionPlanTitle.length()>0){
			ActionPlanBridgeString=ActionPlanTitle;
			PropertyName="title";
		} else if(ActionPlanType.length()>0){
			ActionPlanBridgeString=ActionPlanType;
			PropertyName="title";
		} else if(ActionPlan.length()>0){
			System.out.println("Assume that action plan is the same as the type.");
			PropertyName="apname";
			ActionPlanBridgeString=ActionPlan;
		} else {
			Assert.fail("Action plan is not defined.");
		}
		oSituationActionPlanTitle=By.xpath("//table[@id='ap-tablelist']//tr/td[@class='" + PropertyName + "'][text()='" + ActionPlanBridgeString + "']/following::td[@class='mandatory']//img");
		imgSrc=TestActions.getElementCssAttribute(oSituationActionPlanTitle,"src");
		if(imgSrc.contains("_off")){
			bTurnedOnNow=false;
		} else if(imgSrc.contains("_on")){
			bTurnedOnNow=true;
		} else {
			Assert.fail("Unknow status of action plan mandatory status.");
		}
		if(TestActions.logicalXOR(bTurnedOnNow, bTurnOn)){
			bClickOnemore=true;
		}
		if(bClickOnemore){
			MandaryImg=TestActions.getElement(oSituationActionPlanTitle);
			By oSituationActionPlanTitle2=By.xpath("//table[@id='ap-tablelist']//tr/td[@class='" + PropertyName + "'][text()='" + ActionPlanBridgeString + "']");
			WebElement MandaryImg2=TestActions.getElement(oSituationActionPlanTitle2);
			TestActions.ElementScrollIntoView(MandaryImg2);
			MandaryImg.click();
			TestActions.robotWaitMS(200);
			PageLoadWait.GenericWaitLoad();
			oSituationActionPlanTitle=By.xpath("//table[@id='ap-tablelist']//tr/td[@class='" + PropertyName + "'][text()='" + ActionPlanBridgeString + "']/following::td[@class='mandatory']//img");
			String imgSrc2=TestActions.getElementCssAttribute(oSituationActionPlanTitle,"src");
			if(imgSrc2.contains("_off")){
				bTurnedOnNow=false;
			} else if(imgSrc2.contains("_on")){
				bTurnedOnNow=true;
			} else {
				Assert.fail("Unknow status of action plan mandatory status.");
			}
			Assert.assertEquals(true,TestActions.logicalEqual(bTurnedOnNow, bTurnOn));
		}
	}
   
	public void DeleteNewType(String SituationType)
	{
		MyPrint.myPrint("Situation Types","delete situation type");
		PageLoadWait.GenericWaitLoad();
		Navigate();
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("xpath","//td[contains(text(), '" + SituationType + "')]");
		Assert.assertEquals(true,driver.getPageSource().contains(SituationType));
		TestActions.clickElementWithNoWait("xpath","//td[contains(text(), '"+ SituationType + "')]");
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		WebElement oDeleteButton=TestActions.getElement("deleteButtonID");
		Action hover = builder.moveToElement(oDeleteButton).click().build();
		hover.perform();
		TestActions.selectOKAlert();
		PageLoadWait.GenericWaitLoad();
		TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='addButton']");
		Assert.assertEquals(false,driver.getPageSource().contains(SituationType));
		MyPrint.myPrint("ModuleSituationPolicy","Delete situation type:'" + SituationType + "' successfully.");
	}
}
