package autom.common;
//import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups="videoblock_tc1")

public class VideoBlock_tc1 extends MasterTest{
	private Stack<String> sWindowHandlers=new Stack<String>();
	private String startedTime="";
	protected String finishedTime;
	private DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private long elapsedSeconds;
	protected ModuleUserGroup MyUserGrp1,MyUserGrp2,MyUserGrp3;
//	protected String sNewDVGroupName= "RestrictedSit";
	private ModuleLocalUser LocalUser1,LocalUser2,LocalUser3;
	private String UserGroupName1= ((this.getClass().getSimpleName()).split("_")[0]) + "1";
	private String UserGroupName2= ((this.getClass().getSimpleName()).split("_")[0]) + "2";
	private String UserGroupName3= ((this.getClass().getSimpleName()).split("_")[0]) + "3";
	private String sUserName1=UserGroupName1 + "user";
	private String sUserName2=UserGroupName2 + "user";
	private String sUserName3=UserGroupName3 + "user";
	protected boolean bCleanUp=false;
	//	private String sSituationTypeString="General Alarm";


	@BeforeClass
	public void oneTimeSetUp() {
		MyPrint.myPrint("TestCase '" 
				+ this.getClass().getSimpleName() 
				+ "'","** @TEST - '" 
						+ this.getClass().getSimpleName() 
						+ "' ***");
		TestActions.LoadGlblData();
		CleanBrowserOpenURL(VIDSHIELD_UI_PATH);
	}
	@Test
	public void WorkFlow() throws Exception {
		Date date = new Date();
		int iMyVewer=1;
		int iBockedTourCam=3;
		startedTime=dateFormat.format(date);
		System.out.println("*** Test was triggered at " 
				+ startedTime + " === Test started now at " 
				+ dateFormat.format(date) + "***");
		MyPrint.myPrint("Test case '" 
				+ this.getClass().getSimpleName() 
				+ "' method '" 
				+ Thread.currentThread().getStackTrace()[1].getMethodName()  
				+ "'", "started.");
		PageLoadWait.GenericWaitLoad();
		String sCamName="AmerDynDirect";
		String sCamName2="Axis215";
		String sCamName3="autoPTZ";
		String sBlockVTCamName="typeOther";
		String sBlockTourName="axisTour";
		String sSonyCamName="Sony_Camera9001";
		String sMapViewWithCam="ViewAmerdynDirect";
		String sDirTourCam="Digital";
		String sDir1="QA Lab";
		String sTestBookmarkName="testBookmark";
		String sTestClipName="testClip";
		String sDir2="Analog Direct PTZ";
		String sMessage,sExpect;
		String sBlockedTourCam="axis93TourCam" + iBockedTourCam;
		String [] TourCamDirectory={sDir1,sDirTourCam,sBlockedTourCam};
		String [] BookMarkDirTree={sDir1,sDir2,sSonyCamName,sTestBookmarkName};
		String [] ClipDirTree={sDir1,sDir2,sSonyCamName,sTestClipName};
		String [] DirTree1={sDir1,sDir2,sCamName};
		String [] DirTree2={sDir1,sDir2,sMapViewWithCam};
		String [] TourDirTree={sDir1,"Tours",sBlockTourName};
		String [] DirTree3={sDir1,sDirTourCam,sCamName2};
		String [] DirTree4={sDir1,sDir2,sCamName3};
		String [] DirTree5={sDir1,sDir2,sSonyCamName};		
		By oNoMadMessage=By.xpath("//div[@id='NOMAD_contentBox']");
		By oNoMadContainer=By.xpath("//div[@id='NOMAD_container'][@style='display: none;']");
		readWriteINI <String> oOverlayLocation=new readWriteINI<String>("VIDEOBLOCK","VIDEOBLOCKTEST");
		StackTraceElement[] traceClassName=Thread.currentThread().getStackTrace();
		readWriteINI <String> iniObjSuiteIni=new readWriteINI<String>("agent","suiterun");
		String agentRunFlag=iniObjSuiteIni.ini.get(iniObjSuiteIni.Category,iniObjSuiteIni.Key,String.class);
		if(!StringUtils.isEmpty(agentRunFlag) && agentRunFlag.equalsIgnoreCase("true")){
			for (int i=1; i<traceClassName.length; i++) {
				StackTraceElement ste = traceClassName[i];
				if (ste.getClassName().contains("RegularTC")) {
					String sAllUserGrp="AllUsers";
					TestActions.login(ADMIN_USERNAME,ADMIN_PASSWORD,sWindowHandlers);
					ModuleUserGroup MyAllUsers = new ModuleUserGroup(sAllUserGrp);
					MyAllUsers.RemoveAllUserRights();
					String tempMapType="Google Maps (V3)";
					ModuleMapPolicy mapTypeGeoCodingTest = new ModuleMapPolicy();
					mapTypeGeoCodingTest.NavAdminMapPolicy();
					mapTypeGeoCodingTest.mapTypeModification(tempMapType);
					String sSituationTypeString2="General Alarm";
					ModuleSituationPolicy MyActionPlan2 = new ModuleSituationPolicy();
					MyActionPlan2.SelectDefaultType(sSituationTypeString2);
					
					
//					
//					BigDecimal Lat=new BigDecimal(42.343528);
//					BigDecimal Lon=new BigDecimal(-71.593684);
//					TestActions.moveMarker("All",sBlockVTCamName, Lat, Lon, 0, true);
//					
//					
					MyUserGrp1 = new ModuleUserGroup(UserGroupName1);
					MyUserGrp1.AddNewGroup();
					MyUserGrp1.AddPrevilegeGroup("all");
					MyUserGrp1.DefineDevicePrivilege("Devices are in Device Group?");
					MyUserGrp1.AddDevicePrivilege(1,1,sCamName);
					MyUserGrp1.ChangeDeviceGroupAccess("Devices are in " + sCamName,1,"ViewControlBlock");
					MyUserGrp2 = new ModuleUserGroup(UserGroupName2);
					MyUserGrp2.AddNewGroup();
					MyUserGrp2.AddPrevilegeGroup("all");
					MyUserGrp2.DefineDevicePrivilege("Devices are in Device Group?");
					MyUserGrp2.AddDevicePrivilege(1,1,sCamName);
					MyUserGrp2.ChangeDeviceGroupAccess("Devices are in " + sCamName,1,"ViewControl");
					MyUserGrp3 = new ModuleUserGroup(UserGroupName3);
					MyUserGrp3.AddNewGroup();
					MyUserGrp3.AddPrevilegeGroup("all");
					MyUserGrp3.DefineDevicePrivilege("Devices are in Device Group?");
					MyUserGrp3.AddDevicePrivilege(1,1,sCamName);
					MyUserGrp3.ChangeDeviceGroupAccess("Devices are in " + sCamName,1,"View Only");
					LocalUser1=TestActions.tclocalUser("",sUserName1,UserGroupName1,"");
					LocalUser1.Nav2LocalUser();
					LocalUser1.Add2UserGroup(UserGroupName1);
					LocalUser2=TestActions.tclocalUser("",sUserName2,UserGroupName2,"");
					LocalUser2.Nav2LocalUser();
					LocalUser2.Add2UserGroup(UserGroupName2);
					LocalUser3=TestActions.tclocalUser("",sUserName3,UserGroupName3,"");
					LocalUser3.Nav2LocalUser();
					LocalUser3.Add2UserGroup(UserGroupName3);
					TestActions.logOut();
					break;
				}
			}
			iniObjSuiteIni.setValue("false");
			iniObjSuiteIni.WriteINI();
		}
		String sTestingMapType=oOverlayLocation.ini.get(oOverlayLocation.Category,
				oOverlayLocation.Key,
				String.class);
		if(sTestingMapType.length()>0){
			MyPrint.myPrint("=====Starting test case branch with name '" + sTestingMapType.toLowerCase() + "'", "=====");
			switch (sTestingMapType.toLowerCase()){
			case "block individual user group":
				TestActions.login(ADMIN_USERNAME,ADMIN_PASSWORD,sWindowHandlers);
				TestActions.Test_BlockUser(2,sCamName,UserGroupName1,true);
				break;
			case "verify user group blocked":
				TestActions.login(sUserName1,sUserName1+1 ,sWindowHandlers);
				TestActions.LoadMyView(sCamName,true,false,iMyVewer);
				sMessage=driver.findElement(oNoMadMessage).getText();
				sExpect="Camera '" + sCamName + "' is";
				Assert.assertTrue(sMessage.contains(sExpect),
						"***notification verification failed. expect '" + 
								sExpect + 
								"', actual '" +  
								sMessage + "'.");				
				break;

			case "admin reset user group to access":
				TestActions.login(ADMIN_USERNAME,ADMIN_PASSWORD,sWindowHandlers);
				TestActions.Test_BlockUser(2,sCamName,UserGroupName1,false);
				break;
			case "operator block individual user group":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_BlockUser(2,sCamName,UserGroupName2,true);
				break;
			case "operator select at least one user group to block":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_BlockAtLeast1User(2,sCamName,UserGroupName2,true);
				break;
			case "operator right click select at least one user group to block":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryBlockAtLeast1User(sDir1,
						sDir2,
						sCamName,
						UserGroupName1,
						true);
				break;
			case "verify operator blocked":
				TestActions.login(sUserName2,sUserName2+1 ,sWindowHandlers);
				TestActions.LoadMyView(sCamName,true,false,iMyVewer);
				MyPrint.myPrint("Note",
						"Accessing to the searching device is currently blocked to some user." 
								+ " Nomad may show or may not show");
				sMessage=driver.findElement(oNoMadMessage).getText();
				sExpect="Camera '" + sCamName + "' is blocked.";
				Assert.assertTrue(sMessage.contains(sExpect),
						"***notification verification failed. expect '" + 
								sExpect + 
								"', actual '" +  
								sMessage + "'."
						);
				break;
			case "operator unblock individual user group":
				TestActions.login(sUserName1,
						sUserName1+1,
						sWindowHandlers);
				TestActions.Test_BlockUser(2,
						sCamName,
						UserGroupName2,
						false);
				break;
			case "clean up":
				bCleanUp=true;
				break;
			case "verify operator is unblocked":
				TestActions.login(sUserName2,
						sUserName2+1 ,
						sWindowHandlers);
				TestActions.LoadMyView(sCamName,
						true,
						true,
						iMyVewer);
				Assert.assertTrue(TestActions.ElementExists(oNoMadContainer),
						"***notification verification failed");
				break;
			case "operator record clip":
				TestActions.createBookmarkClip(sUserName2,
						sSonyCamName,
						iMyVewer,
						sWindowHandlers,
						sTestClipName,
						false);
				break;
			case "operator delete clip":
				TestActions.deleteBookmarkClip(sUserName2,
						sWindowHandlers,
						ClipDirTree,
						false);
				break;
			case "operator create bookmark":
				TestActions.createBookmarkClip(sUserName2,sSonyCamName,
						iMyVewer,
						sWindowHandlers,
						sTestBookmarkName,
						true);
				break;
			case "operator delete bookmark":
				TestActions.deleteBookmarkClip(sUserName2,
						sWindowHandlers,
						BookMarkDirTree,
						true);
				break;
			case "operator block bookmark":
				TestActions.login(sUserName1,
						sUserName1+1,
						sWindowHandlers);
				TestActions.Test_DirectoryBlockUser(sDir1,
						sDir2,
						sSonyCamName,
						UserGroupName2,
						false);
				TestActions.logOut();
				TestActions.createBookmarkClip(sUserName2,
						sSonyCamName,
						iMyVewer,
						sWindowHandlers,
						sTestBookmarkName,
						false);
				TestActions.dragDropDevice(iMyVewer+2,
						BookMarkDirTree,
						true);
				TestActions.logOut();
				TestActions.login(sUserName1,
						sUserName1+1,
						sWindowHandlers);
				TestActions.Test_BlockUser(2,
						sSonyCamName,
						UserGroupName2,
						true);
				TestActions.logOut();
				TestActions.login(sUserName3,
						sUserName3+1,
						sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,
						BookMarkDirTree,
						true);
				TestActions.logOut();
				TestActions.login(sUserName2,
						sUserName2+1,
						sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,
						BookMarkDirTree,
						false);
				TestActions.logOut();
				TestActions.deleteBookmarkClip(sUserName1,
						sWindowHandlers,
						BookMarkDirTree,
						false);
				TestActions.Test_BlockUser(2,
						sSonyCamName,
						UserGroupName2,
						false);
				break;
			case "operator block clip":
				TestActions.login(sUserName1,
						sUserName1+1,
						sWindowHandlers);
				TestActions.Test_DirectoryBlockUser(sDir1,
						sDir2,
						sSonyCamName,
						UserGroupName2,
						false);
				TestActions.logOut();
				TestActions.createBookmarkClip(sUserName2,
						sSonyCamName,
						iMyVewer,
						sWindowHandlers,
						sTestClipName,
						true);
				TestActions.dragDropDevice(iMyVewer+2,
						ClipDirTree,
						true);
				TestActions.logOut();
				TestActions.login(sUserName1,
						sUserName1+1,
						sWindowHandlers);
				TestActions.Test_BlockUser(2,
						sSonyCamName,
						UserGroupName2,
						true);
				TestActions.logOut();
				TestActions.login(sUserName3,
						sUserName3+1,
						sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,
						ClipDirTree,
						true);
				TestActions.logOut();
				TestActions.login(sUserName2,
						sUserName2+1,
						sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,
						ClipDirTree,
						false);
				TestActions.logOut();
				TestActions.deleteBookmarkClip(sUserName1,
						sWindowHandlers,
						ClipDirTree,true);
				TestActions.Test_BlockUser(2,
						sSonyCamName,
						UserGroupName2,false);
				break;

			case "operator block clip and bookmark":
				TestActions.login(sUserName1,
						sUserName1+1,
						sWindowHandlers);
				TestActions.Test_DirectoryBlockUser(sDir1,
						sDir2,
						sSonyCamName,
						UserGroupName2,
						false);
				TestActions.logOut();
				TestActions.createBookmarkClip(sUserName2,
						sSonyCamName,
						iMyVewer,
						sWindowHandlers,
						sTestBookmarkName,
						true);
				TestActions.createBookmarkClip(sSonyCamName,
						iMyVewer+1,
						sWindowHandlers,
						sTestClipName,
						false);
				TestActions.dragDropDevice(iMyVewer+2,
						ClipDirTree,
						true);
				TestActions.dragDropDevice(iMyVewer+3,
						BookMarkDirTree,
						true);
				TestActions.logOut();
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_BlockUser(2,sSonyCamName,UserGroupName2,true);
				TestActions.logOut();
				TestActions.login(sUserName3,sUserName3+1 ,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer+1,BookMarkDirTree,true);
				TestActions.dragDropDevice(iMyVewer+2,ClipDirTree,true);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1 ,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,BookMarkDirTree,false);
				TestActions.dragDropDevice(iMyVewer,ClipDirTree,false);
				TestActions.logOut();
				TestActions.deleteBookmarkClip(sUserName1,sWindowHandlers,ClipDirTree,false);
				TestActions.deleteBookmarkClip(BookMarkDirTree,true);
				TestActions.Test_BlockUser(2,sSonyCamName,UserGroupName2,false);
				break;

			case "admin right click block individual user group":
				TestActions.login(ADMIN_USERNAME,ADMIN_PASSWORD,sWindowHandlers);
				TestActions.Test_DirectoryBlockUser( sDir1,sDir2,sCamName,UserGroupName1,true);
				break;
			case "operator right click block individual user group":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryBlockUser( sDir1,sDir2,sCamName,UserGroupName2,true);
				break;
			case "operator right click unblock individual user group":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryBlockUser( sDir1,sDir2,sCamName,UserGroupName2,false);
				break;
			case "operator right click unblock all user group":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryUnBlockAllUser( sDir1,sDir2,sCamName);
				break;
			case "operator create situation associate with camera":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_CreateClientUISituation(DirTree1);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.openDeviceInDetailWindow(DirTree1,3,true);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);				
				break;
			case "verify operator create situation associate with camera":
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,false);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.openDeviceInDetailWindow(DirTree1,3,false);
				//				TestActions.openDeviceInDetailWindow("Cameras",sCamName,1);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				break;
			case "operator check situation source camera":			
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_CreateClientUISituation(DirTree1);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.openSourceDeviceInDetailWindow(sCamName,4,true);
				//				TestActions.openDeviceInDetailWindow("Cameras",sCamName,1);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);				
				break;
			case "verify operator blocking situation source camera":
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,false);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.openSourceDeviceInDetailWindow(sCamName,4,false);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				break;
			case "verify operator unblocking situation source camera":
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,true);				
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.openSourceDeviceInDetailWindow(sCamName,4,true);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				break;
			case "operator block local user from situation source camera":			
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_CreateClientUISituation(DirTree1);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.blockOperatorInDetailWindow(sCamName,UserGroupName2,true);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				break;
			case "operator unblock local user from situation source camera":			
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_CreateClientUISituation(DirTree1);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.blockOperatorInDetailWindow(sCamName,UserGroupName2,false);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				TestActions.CloseTestDriver(sWindowHandlers);
				TestActions.robotWait(2);
				break;
			case "operator drag drop camera in a mapviewer":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryBlockUser( sDir1,sDir2,sCamName,UserGroupName2,false);
				TestActions.Test_MapViewerBlockUser(iMyVewer,DirTree2,UserGroupName2,true);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,false);
				TestActions.logOut();
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_MapViewerBlockUser(iMyVewer,DirTree2,UserGroupName2,false);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,true);
				break;
			case "verify situation camera video in preview match video block setting":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryAllBlockUser(sDir1,sDir2,sCamName,false);
				TestActions.Test_MapViewerBlockUser(iMyVewer,DirTree2,UserGroupName2,true);
				TestActions.Test_CreateClientUISituation(iMyVewer+1);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituationPreview(false);
				TestActions.CloseDriver(sWindowHandlers);
				driver.switchTo().defaultContent();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				TestActions.logOut();
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituationPreview(true);
				TestActions.CloseDriver(sWindowHandlers);
				driver.switchTo().defaultContent();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				TestActions.Test_DirectoryAllBlockUser(sDir1,sDir2,sCamName,false);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,true);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituationPreview(true);
				TestActions.CloseDriver(sWindowHandlers);
				driver.switchTo().defaultContent();
				PageLoadWait.GenericWaitLoad();
				break;

			case "situation ptz preview viewer menu block user group":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_MapViewerBlockUser(iMyVewer,DirTree2,UserGroupName2,false);
				TestActions.Test_CreateClientUISituation(iMyVewer+1);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituationPreview(true);
				TestActions.CloseDriver(sWindowHandlers);
				TestActions.logOut();
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituationPreview(true);
				TestActions.SituationPreviewBlockUser(iMyVewer,false,UserGroupName2,true);
				TestActions.CloseDriver(sWindowHandlers);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,false);
				TestActions.logOut();
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituationPreview(true);
				TestActions.SituationPreviewBlockUser(iMyVewer,false,UserGroupName2,false);
				TestActions.CloseDriver(sWindowHandlers);
				TestActions.logOut();
				TestActions.login(sUserName2,sUserName2+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,DirTree1,true);
				break;
			case "verify blocked camera from situation ptz preview viewer":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryBlockUser(sDir1,sDir2,sCamName,UserGroupName1,false);
				TestActions.ExcuteKeyEvent(116);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				TestActions.Test_OpenCameraInMap(iMyVewer,DirTree2);
				TestActions.Test_CreateClientUISituation(iMyVewer+1);
				TestActions.RiskShieldListView(sWindowHandlers);
				TestActions.openFirstRowSituationPreview(true);
				TestActions.openFirstRowSituation(sWindowHandlers);
				TestActions.blockOperatorInDetailWindow(sCamName,UserGroupName1,true);
				TestActions.CloseDriver(sWindowHandlers);
				TestActions.verifyVidibleSituationPreview(false,true);
				TestActions.reloadVerifySituationPreview(false,true);
				TestActions.CloseDriver(sWindowHandlers);
				///verify here too.
				TestActions.Test_DirectoryBlockUser(sDir1,sDir2,sCamName,UserGroupName1,false);
				break;
			case "verify blocked vt camera":
				String [] QALabDirectory={sDir1,sBlockVTCamName};
				
				
				
//				TestActions.login(ADMIN_USERNAME,ADMIN_PASSWORD,sWindowHandlers);
//				BigDecimal Lat=new BigDecimal(42.343528);
//				BigDecimal Lon=new BigDecimal(-71.593684);
//				TestActions.moveMarker("World",sBlockVTCamName, Lat, Lon, 0, true);
//				TestActions.logOut();
				
				
				
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.Test_DirectoryBlockUser(QALabDirectory,UserGroupName1,false);
				PageLoadWait.GenericWaitLoad();
				TestActions.ExcuteKeyEvent(116);
				PageLoadWait.GenericWaitLoad();
				TestActions.Test_DirectoryBlockUser(DirTree1,UserGroupName1,false);
				PageLoadWait.GenericWaitLoad();
				TestActions.ExcuteKeyEvent(116);
				PageLoadWait.GenericWaitLoad();
				TestActions.openVTCheckCamera(iMyVewer,DirTree1,sBlockVTCamName,iMyVewer,true);				
				TestActions.exitVT();
				TestActions.ExcuteKeyEvent(116);
				//				TestActions.Test_ViewerBlockUser(iMyVewer, UserGroupName1, true);
				TestActions.Test_DirectoryBlockUser(QALabDirectory,UserGroupName1,true);
				TestActions.ExcuteKeyEvent(116);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				TestActions.openVTCheckCamera(iMyVewer,DirTree1,sBlockVTCamName,iMyVewer,false);
				TestActions.exitVT();
				break;

			case "verify blocked camera in tour":
				TestActions.login(ADMIN_USERNAME,ADMIN_PASSWORD,sWindowHandlers);
				TestActions.Test_DirectoryBlockUser(TourCamDirectory,UserGroupName1,true);
				TestActions.logOut();
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				TestActions.dragDropDevice(iMyVewer,TourDirTree,true);
				String text="Connection failed: Camera '" + sBlockedTourCam + "' is blocked.";
				By oNoMadMessageDyn=By.xpath("//div[@id='NOMAD_contentBox'][@class='NOMADStyle'][contains(.,\"" + text + "\")]");
				int iTimeSecondsCheckPeriod=20;
				Assert.assertTrue(TestActions.waitUntilBooleanCondition(oNoMadMessageDyn, true, iTimeSecondsCheckPeriod),
						"***Failed to get the expected blocked message in " + iTimeSecondsCheckPeriod +  " seconds.");
				break;

			case "verify blocked camera in salvo":
				TestActions.login(sUserName1,sUserName1+1,sWindowHandlers);
				String[][] List1=new String[][]{DirTree1,DirTree3,DirTree4,DirTree5};
				TestActions.Test_DirectoryBlockUsers(List1, UserGroupName1, false);
				ModuleCreateSalvo oTestSalvo;
				oTestSalvo= new ModuleCreateSalvo();
				oTestSalvo.setNewSalvoName("oTestSalvo");
				PageLoadWait.GenericWaitLoad();
				oTestSalvo.deleteVidsysSalvo();
				PageLoadWait.GenericWaitLoad();
				TestActions.dragDropDevice(iMyVewer,DirTree1,true);
				TestActions.treeCollapse();
				TestActions.dragDropDevice(iMyVewer+1,DirTree3,true);
				TestActions.treeCollapse();
				TestActions.dragDropDevice(iMyVewer+2,DirTree4,true);
				TestActions.treeCollapse();
				TestActions.dragDropDevice(iMyVewer+3,DirTree5,true);
				TestActions.treeCollapse();
				oTestSalvo.setNewSalvoName("oTestSalvo");
				TestActions.mouseOverElementClick(By.id("btnSalvo"));
				TestActions.robotWait(2);
				PageLoadWait.GenericWaitLoad();
				String[][] List2=new String[][]{DirTree1,DirTree3};
				TestActions.Test_DirectoryBlockUsers(List2, UserGroupName1, true);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				ArrayList<String> sMessageList;
				String sAllWarning,sExpectMsg1,sExpectMsg2;
				sMessageList=oTestSalvo.openExistSalvo(true);
				sExpectMsg1="Connection failed: Camera '" + sCamName + "' is blocked.";
				sExpectMsg2="Connection failed: Camera '" + sCamName2 + "' is blocked.";
				Assert.assertTrue(sMessageList!=null,
						"***Failed to get the all expected blocked messages.");
				sAllWarning=sMessageList.toString().replaceAll("\\[\\]", "");
				Assert.assertTrue(sAllWarning.contains(sExpectMsg1),
						"***Failed to get the expected blocked messages 1.");
				Assert.assertTrue(sAllWarning.contains(sExpectMsg2),
						"***Failed to get the expected blocked messages 2.");

				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				String[][] List3=new String[][]{DirTree4,DirTree5};
				TestActions.Test_DirectoryBlockUsers(List2, UserGroupName1, false);
				TestActions.Test_DirectoryBlockUsers(List3, UserGroupName1, true);

				//				By oPopupCancelButton=By.xpath("//div[@id='blockVideoCancelPopupOnTree']/img");
				//				TestActions.clickDisplyObj(oPopupCancelButton);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				sMessageList=oTestSalvo.openExistSalvo(true);
				sExpectMsg1="Connection failed: Camera '" + sCamName3 + "' is blocked.";
				sExpectMsg2="Connection failed: Camera '" + sSonyCamName + "' is blocked.";
				Assert.assertTrue(sMessageList!=null,
						"***Failed to get the all expected blocked messages.");
				sAllWarning=sMessageList.toString().replaceAll("\\[\\]", "");
				Assert.assertTrue(sAllWarning.contains(sExpectMsg1),
						"***Failed to get the expected blocked messages 3.");
				Assert.assertTrue(sAllWarning.contains(sExpectMsg2),
						"***Failed to get the expected blocked messages 4.");
				TestActions.Test_DirectoryBlockUsers(List3, UserGroupName1, false);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				break;
			default:
				MyPrint.myPrint("WARNIN", "'" + sTestingMapType 
						+ "' is not included in the loop, please check the name entry.");
				break;
			}
		}

		iniObjSuiteIni=new readWriteINI<String>("suiteclean","settingclean");
		String agentRunFlag2=iniObjSuiteIni.ini.get(iniObjSuiteIni.Category,iniObjSuiteIni.Key,String.class);
		if(!StringUtils.isEmpty(agentRunFlag) && agentRunFlag2.equalsIgnoreCase("true") && bCleanUp){
			////do clean
//			if(driver.findElements(By.xpath("//div[contains(@title,'Logout User')][contains(@title,'admin')]")).size()<1){
//				TestActions.logOut();
//				TestActions.login(ADMIN_USERNAME, ADMIN_PASSWORD,sWindowHandlers);
//				PageLoadWait.GenericWaitLoad();
//			}
			TestActions.login(ADMIN_USERNAME, ADMIN_PASSWORD,sWindowHandlers);
			PageLoadWait.GenericWaitLoad();
////////////////need to reset the flowing devices from blocking status to none ////////////				
//			String sCamName="AmerDynDirect";
//			String sCamName2="Axis215";
//			String sCamName3="autoPTZ";
//			String sBlockVTCamName="typeOther";
//			String sBlockTourName="axisTour";
//			String sSonyCamName="Sony_Camera9001";
//			sBlockedTourCam			
			MyUserGrp1 = new ModuleUserGroup(UserGroupName1);
			MyUserGrp1.DeleteUserGroup();
			MyUserGrp2 = new ModuleUserGroup(UserGroupName2);
			MyUserGrp2.DeleteUserGroup();
			MyUserGrp3 = new ModuleUserGroup(UserGroupName3);
			MyUserGrp3.DeleteUserGroup();
			TestActions.tclocalUserDelete("",sUserName1,UserGroupName1,"");
			TestActions.tclocalUserDelete("",sUserName2,UserGroupName2,"");
			TestActions.tclocalUserDelete("",sUserName3,UserGroupName3,"");
			iniObjSuiteIni.setValue("false");
			iniObjSuiteIni.WriteINI();
		}
		TestActions.logOut();
		Date dateFinish = new Date();
		finishedTime=dateFormat.format(dateFinish);
		elapsedSeconds = (dateFinish.getTime()-date.getTime())/1000;
		MyPrint.myPrint(this.getClass().getSimpleName() + " method '"
				+ Thread.currentThread().getStackTrace()[1].getMethodName()  
				+ "'","** Logout user '" + ADMIN_USERNAME + "' successfully. **");
		MyPrint.myPrint(this.getClass().getSimpleName() + " method '" 
				+ Thread.currentThread().getStackTrace()[1].getMethodName() 
				+ "'","** TC Elapsed " 
						+ elapsedSeconds 
						+ " seconds. Finished at '" + dateFormat.format(date) + "' ***");
	}
}
