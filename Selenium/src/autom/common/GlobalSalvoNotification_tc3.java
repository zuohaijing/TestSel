package autom.common;
//package com.vidsys.autom.test.regression;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import com.vidsys.autom.common.MasterTest;
//import com.vidsys.autom.common.MyPrint;
//import com.vidsys.autom.test.regression.TestActions;

public class GlobalSalvoNotification_tc3 extends MasterTest{
	private Stack<String> sWindowHandlers=new Stack<String>();
	private String startedTime;
	private DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private String sNewUserGroupName="GlobalSalvo";
	private String sLocalTester=sNewUserGroupName + "tc1LocalUser";
	private String sLocalTester2=sNewUserGroupName + "tc1LocalUser2";
	private String GSTC2Salvo1Name="GSTCNotificationSalvo";
	private String sMessage;
	String sLUSName=sLocalTester;
	String sLUSName2=sLocalTester2;
	String sDevice1="Default View";
	String sDevice2="ViewAAA";
	protected String Login;
	protected String Pass;
	protected String NotificationMessage;
	protected boolean bDeleteSalvo=false;
	boolean bProvateSalvo=false;
	protected String sDelemeter="::";
	protected List<String> sSubActionkey= new ArrayList<String>();
	protected Socket clientSocket;
	@BeforeClass
	public void oneTimeSetUp() {
		MyPrint.myPrint("TestCase '" + 
				this.getClass().getSimpleName() + 
				"'","** @TEST - '" + 
						this.getClass().getSimpleName() + 
				"' ***");
		TestActions.LoadGlblData();
		CleanBrowserOpenURL(VIDSHIELD_UI_PATH);
	}
	@SuppressWarnings({ "deprecation"})
	@Test(groups="notification_tc1")
	public void WorkFlow() throws Exception {
		Date date = new Date();
		ServerSocket echoServer;
		String line;
		DataInputStream is;
		PrintStream os;
		try {
			echoServer = new ServerSocket();
			echoServer.setReuseAddress(true);
			echoServer.bind(new InetSocketAddress(9090));
			System.out.println("The server started.");
			startedTime=dateFormat.format(date);
			System.out.println("*** Test was triggered at " + 
					startedTime + 
					" === Test started now at " + 
					dateFormat.format(date) + 
					"***");
			MyPrint.myPrint("Test case '" + 
					this.getClass().getSimpleName() + 
					"' method '"+ 
					Thread.currentThread().getStackTrace()[1].getMethodName()  + 
					"'", "started.");
			Login=ADMIN_USERNAME;
			Pass=ADMIN_PASSWORD;
			TestActions.login(Login, Pass,sWindowHandlers);

			if(bProvateSalvo){
				MyPrint.myPrint("Notification Test","Private salvo '" + GSTC2Salvo1Name + "' notification test.");
			} else {
				MyPrint.myPrint("Notification Test","Public Salvo '" + GSTC2Salvo1Name + "' notification test.");			
			}
			new StartMonitor();
			PageLoadWait.GenericWaitLoad();
			Assert.assertEquals(true,
					driver.findElements(
							By.xpath("//img[@id='salvoPublicIcon'][not(contains(@style,'display: none'))]")
							).size()!=0);
			Assert.assertEquals(true,
					((JavascriptExecutor)driver).executeScript(
							"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 1 && arguments[0].style.display!=\"none\"", 
							driver.findElement(By.id("salvoPublicIcon"))));
			int [] iFoundError= new int[1];
			iFoundError[0]=1;
			int [] itempCount= new int[1];
			itempCount[0]=0;
			boolean bDeleteSalvo=false;
			boolean bCloseSituations=false;
			String sResponseKey;
			clientSocket=new Socket();
			clientSocket.setReuseAddress(true);
			clientSocket=echoServer.accept();
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
			boolean bwait=true;
			while (bwait) {
				line = is.readLine();
				if(line.length()>0){
					sResponseKey="";
					if(line.contains("::")){
						sResponseKey=line.split("::")[0];
					} else {
						sResponseKey=line;
					}
					switch (sResponseKey){
					case "ready":						
						readWriteINI<String> oMapNOTIFICATION=new readWriteINI<String>("NOTIFICATION","NOTIFICATIONTEST");
						sMessage=oMapNOTIFICATION.ini.get(oMapNOTIFICATION.Category,
								oMapNOTIFICATION.Key,String.class);
						notificationCheck(os,sMessage,sSubActionkey,iFoundError);
						break;
					case "dp status position":
						TestActions.clickElementWithNoWait(TestActions.getElementLocator("toolbar_commands_completeButton"));
						TestActions.SwitchToFrame(By.name("completeWindowFrame"));
						TestActions.waitElementClickableReady("SubmitSituation");
						TestActions.clickElementWithNoWait(TestActions.getElementLocator("SubmitSituation"));
						sMessage="check dp status";
						os.println(sMessage.toLowerCase());	
						break;
					case "riskshield":
						int iCont;
						iCont=riskShielCheck(os,sMessage,sSubActionkey, iFoundError,line);
						if(iCont==0){
							bwait=false;
							bCloseSituations=true;
						}
						break;
					case "my status":
						if(line.contains("enabled")){
							System.out.println("enabled" + sDelemeter);
						} 
						if(line.contains("disabled")){
							iFoundError[0]=0;
						} 
						os.println("end");
						bwait=false;
						driver.switchTo().defaultContent();
						try {
							TestActions.CloseDriver(sWindowHandlers);
							TestActions.robotWait(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							TestActions.CloseDriver(sWindowHandlers);
							TestActions.robotWait(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						TestActions.robotWait(5);						
						break;
					case "notification message":
						TestActions.robotWait(1);
						os.println("quit");
						NotificationMessage=getExpectingNotification(sMessage, sSubActionkey, GSTC2Salvo1Name, "");
						System.out.println("got message:" + line);
						if(line.split("::")[1].equalsIgnoreCase(NotificationMessage)){
							iFoundError[0]=0;
						}
						bwait=false;
						break;
					default:
						MyPrint.myPrint("WARNIN", "'" + sResponseKey + 
								"' is not defined, please check the keyword.");
						break;
					}
				}
			}
			os.flush();
			is.close();
			os.close();
			is=null;
			os=null;
			clientSocket.close();
			echoServer.close();
			clientSocket=null;
			echoServer=null;
			System.gc();
			String sToMatch;
			if(bDeleteSalvo){
				sToMatch="deleted.";
			} else {
				sToMatch="added.";
			}
			sToMatch="Reloaded Salvo Panel as '" + 
					GSTC2Salvo1Name + 
					"' Salvo has been " + 
					sToMatch;
			Assert.assertTrue(iFoundError[0]==0,"***notification verification failed");

			if(bCloseSituations){
				//			TestActions.Test_CloseSituations(sWindowHandlers);////////time out through vpn
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		TestActions.logOut();
	}

	public int riskShielCheck(PrintStream os,String sActionKey,List<String> sSubActionkey, int[] iError, String instruction){
		int iReturn;
		iReturn=1;
		String sKeyAction;
		if(sSubActionkey.size()==0){
			sSubActionkey.add(0,"create");
			sKeyAction="create";
		} else {
			sKeyAction=sActionKey.toLowerCase();			
		}
		switch (sKeyAction){
		case "create":
			sMessage="check new situation creation";
			String subKey;
			
			readWriteINI<String> oMapNOTIFICATIONSubkey=new readWriteINI<String>("MONITOR","ResponseKeyWord");
			subKey=oMapNOTIFICATIONSubkey.ini.get(oMapNOTIFICATIONSubkey.Category,
					oMapNOTIFICATIONSubkey.Key,String.class);

			if(subKey.equalsIgnoreCase("add")){
				TestActions.Test_CreateDefaultSeveritySituation(os,sMessage);	
			} else {
				
				TestActions.robotWait(3);
				TestActions.openRiskShieldSplitView(sWindowHandlers);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();
				TestActions.openFirstPinSituation(sWindowHandlers);
				TestActions.robotWait(1);
				PageLoadWait.GenericWaitLoad();
				PageLoadWait.GenericWaitLoad();			
				
				TestActions.clickElementWithNoWait(TestActions.getElementLocator("toolbar_commands_completeButton"));
				TestActions.SwitchToFrame(By.name("completeWindowFrame"));
				TestActions.waitElementClickableReady("SubmitSituation");
				TestActions.clickElementWithNoWait(TestActions.getElementLocator("SubmitSituation"));
				os.println(sMessage.toLowerCase());
				TestActions.robotWait(1);
				driver.switchTo().defaultContent();
				try {
					TestActions.CloseDriver(sWindowHandlers);
					TestActions.robotWait(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					TestActions.CloseDriver(sWindowHandlers);
					TestActions.robotWait(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TestActions.robotWait(5);
			}
			
			
			
			

			break;
		case "check new situation creation":
			if(instruction.contains("::pass")){
				iError[0]=0;
			}
			os.println("close riskshield logout");
			TestActions.robotWait(5);
			iReturn=0;
			break;
		default:
			MyPrint.myPrint("WARNIN", "'" + sActionKey + 
					"' is not defined, please check the keyword.");
			break;
		}
		return iReturn;
	}

	public void notificationCheck(PrintStream os,String sActionKey,List<String> sSubActionkey, int[] iError){
		String sKeyAction;
		sKeyAction=sActionKey.toLowerCase();
		switch (sKeyAction){
		case "situation dp status":
			TestActions.Test_CreateDefaultSeveritySituation();
			TestActions.robotWait(1);
			os.println(sActionKey.toLowerCase());
			TestActions.robotWait(3);
			TestActions.openRiskShieldSplitView(sWindowHandlers);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			TestActions.openFirstPinSituation(sWindowHandlers);
			TestActions.robotWait(1);
			PageLoadWait.GenericWaitLoad();
			PageLoadWait.GenericWaitLoad();
			TestActions.waitElementClickableReady("toolbar_commands_completeButton");
			break;
		case "riskshield summary list count":
			TestActions.Test_CreateDefaultSeveritySituation();
			os.println(sActionKey.toLowerCase());
			//			TestActions.Test_CreateDefaultSeveritySituation(os,sKeyAction);
			//			TestActions.robotWait(3);
			//			TestActions.openRiskShieldSplitView(sWindowHandlers);
			//			PageLoadWait.GenericWaitLoad();
			//			PageLoadWait.GenericWaitLoad();
			//			TestActions.openFirstPinSituation(sWindowHandlers);
			//			TestActions.robotWait(1);
			//			PageLoadWait.GenericWaitLoad();
			//			PageLoadWait.GenericWaitLoad();
			//			TestActions.waitElementClickableReady("toolbar_commands_completeButton");
			break;
		case "convert private salvo to public":
			Map<Integer, String> PublicSalvo = new HashMap<Integer, String>();
			PublicSalvo = new HashMap<Integer, String>();
			PublicSalvo.put(4, sDevice1);
			PublicSalvo.put(3, sDevice2);
			new ModuleCreateSalvo(GSTC2Salvo1Name,true, true,false,PublicSalvo);
			covertSalvoType(GSTC2Salvo1Name, "admin", false);
			os.println("check notification");
			iError[0]=0;
			break;
		case "public salvo creation or delete notification":
			PublicSalvo = new HashMap<Integer, String>();
			PublicSalvo.put(4, sDevice1);
			PublicSalvo.put(3, sDevice2);
			bDeleteSalvo=false;
			String sCommand="check notification";
			ModuleCreateSalvo oNotification=new ModuleCreateSalvo(GSTC2Salvo1Name,
					bProvateSalvo,
					true,
					false,
					PublicSalvo,
					os,
					sCommand,
					iError);
			if(oNotification.bExistingSalvo){
				oNotification.deleteVidsysSalvo();
				os.println(sCommand.toLowerCase());
				bDeleteSalvo=true;
				sSubActionkey.add(0, "delete");
				if(!oNotification.bHaveErrorDeleteSalvo){
					iError[0]=0;
				}
			} else {
				sSubActionkey.add(0,"add");
			}
			os.println("quit");
			TestActions.robotWait(1);
			break;	
		default:
			MyPrint.myPrint("WARNIN", "'" + sActionKey + 
					"' is not defined, please check the keyword.");
			break;
		}
	}

	public String getExpectingNotification(String skey, List<String>sSubActionkey,String sCondition2,String sCondition3){
		String sReturn="";
		switch (skey.toLowerCase()){
		case "public salvo creation or delete notification":
			if(sSubActionkey.get(0).equalsIgnoreCase("delete")){				
				sReturn="Reloaded Salvo Panel as '" + sCondition2 + "' Salvo has been deleted.";
			} else {
				sReturn="Reloaded Salvo Panel as '" + sCondition2 + "' Salvo has been added.";
			}
			break;
		case "situation dp status":
			break;
		default:
			MyPrint.myPrint("WARNIN", "'" + skey + 
					"' is not defined, please check the keyword.");
			break;	
		}
		return sReturn;
	}

	public void covertSalvoType(String SalvoName, String sOwner, boolean bConvertoPrivate){
		By oSalvoElement=By.xpath("//div[@id='salvoListDiv']/div[@id='salvodata']");
		WebElement oTestSalvo=driver.findElement(oSalvoElement).findElement(By.xpath("./div[@title='"+ SalvoName + "']"));
		TestActions.ElementRightClick(oTestSalvo);
		driver.findElement(By.xpath("//div[contains(text(),'Edit')][@class='menuWidgetGlobalDefault'][contains(@id,'_menu_item0_title')]")).click();
		PageLoadWait.GenericWaitLoad();
		PageLoadWait.GenericWaitLoad();
		if(bConvertoPrivate){
			if(((WebElement)((JavascriptExecutor)driver).executeScript("return document.getElementById('publicType') ;")).isSelected()){
				((JavascriptExecutor)driver).executeScript("document.getElementById('privateType').click() ;");
			}	
		} else {
			if(((WebElement)((JavascriptExecutor)driver).executeScript("return document.getElementById('privateType') ;")).isSelected()){
				((JavascriptExecutor)driver).executeScript("document.getElementById('publicType').click() ;");
			} else {
				((JavascriptExecutor)driver).executeScript("document.getElementById('privateType').click() ;");
				driver.findElement(By.xpath("//div[@id='salvoedit_button_ok']")).click();
				PageLoadWait.GenericWaitLoad();
				covertSalvoType(SalvoName, sOwner, bConvertoPrivate);
				return;
			}
		}
		driver.findElement(By.xpath("//div[@id='salvoedit_button_ok']")).click();
		PageLoadWait.GenericWaitLoad();
	}

}	////end of class



//private String GSTC2Salvo2Name="GSTC2Salvo2";
//private String GSTC2Salvo3Name="GSTC2Salvo3";
//private String GSTC2Salvo4Name="GSPublicTC2Salvo4";
//private String GSTC2Salvo5Name="GSPublicTC2Salvo5";
//private String GSTC2Salvo6Name="é½�é½�å“ˆå°”publicaa";
//private String GSTC2Salvo7Name="aaé½�é½�å“ˆå°”aaPrivateaa";
//private String GSTC2Salvo8Name="é½�é½�å“ˆå°”";
//private String GSTC2Salvo9Name="ä¸‰åŒ»é™¢ä¸œåŒº";
//private String GSTC2Salvo10Name="é½�é½�å“ˆå°”ä¸‰åŒ»é™¢";
//		TestActions.login(ADMIN_USERNAME, ADMIN_PASSWORD,sWindowHandlers);
//		String sTestTable1="salvodata";
//		
//		Salvo400.setSalvoName(GSTC2Salvo5Name);
//		Salvo400.deleteVidsysSalvo();
//		
//		By oTableHeader1=By.xpath("//table[@id='salvoTableHeader']");
//		ModuleWebTable oSalvoListing = new ModuleWebTable(sTestTable1,true);
//		oSalvoListing.setHeaderTable(oTableHeader1);
//		oSalvoListing.tableSortingTest(1);
//		driver.findElement(oTableHeader1).findElement(By.xpath(".//th[contains(.,'Type')]")).click();
//		PageLoadWait.GenericWaitLoad();
//		oSalvoListing.tableSortingTest(3);
//		driver.findElement(oTableHeader1).findElement(By.xpath(".//th[contains(.,'Type')]")).click();
//		PageLoadWait.GenericWaitLoad();
//		oSalvoListing.tableSortingTest(3);
//		driver.findElement(oTableHeader1).findElement(By.xpath(".//th[contains(.,'Owner')]")).click();
//		PageLoadWait.GenericWaitLoad();
//		oSalvoListing.tableSortingTest(2);
//		driver.findElement(oTableHeader1).findElement(By.xpath(".//th[contains(.,'Owner')]")).click();
//		PageLoadWait.GenericWaitLoad();
//		oSalvoListing.tableSortingTest(2);		
//		driver.findElement(oTableHeader1).findElement(By.xpath(".//th[contains(.,'Salvo Name')]")).click();
//		PageLoadWait.GenericWaitLoad();
//		driver.findElement(oTableHeader1).findElement(By.xpath(".//th[contains(.,'Salvo Name')]")).click();
//		PageLoadWait.GenericWaitLoad();
//		oSalvoListing.tableSortingTest(1);
//		PublicSalvo = new HashMap<Integer, String>();
//		PublicSalvo.put(4, sDevice1);
//		PublicSalvo.put(3, sDevice2);
//		ModuleCreateSalvo Salvo5 = new ModuleCreateSalvo(GSTC2Salvo5Name,false, true,false,PublicSalvo,echoServer);
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
//		ModuleCreateSalvo Salvo6 = new ModuleCreateSalvo(GSTC2Salvo6Name,false, true,false,PublicSalvo,echoServer);
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
//		ModuleCreateSalvo Salvo7 = new ModuleCreateSalvo(GSTC2Salvo7Name,true, true,false,PublicSalvo,echoServer);
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
//		PageLoadWait.GenericWaitLoad();
//		PublicSalvo.clear();
//		Salvo400.salvoAdmin();
//		Salvo400.adminSetSalvoNameOwner(GSTC2Salvo4Name,sLocalTester);
//		Salvo400.adminSalvoTypeSwitch();
//		Salvo400.adminSalvoTypeSwitch();
//		PageLoadWait.GenericWaitLoad();	
//		PageLoadWait.GenericWaitLoad();
//		ModuleWebTable salvoAdminListing = new ModuleWebTable(oTestTable,oTableHeader);
//		salvoAdminListing.tableSortingTest(oTableNameCol1, iColNum);
//		driver.findElement(oTableHeader).findElement(oTableNameCol1).click();
//		PageLoadWait.GenericWaitLoad();
//		salvoAdminListing.tableSortingTest(oTableNameCol1, iColNum);
//		iColNum=2;
//		sCol1ID="owner";
//		oTableNameCol1=By.xpath(".//th[starts-with(@class,'"+ sCol1ID +"')]");
//		driver.findElement(oTableHeader).findElement(oTableNameCol1).click();
//		PageLoadWait.GenericWaitLoad();
//		salvoAdminListing.tableSortingTest(oTableNameCol1, iColNum);
//		driver.findElement(oTableHeader).findElement(oTableNameCol1).click();
//		PageLoadWait.GenericWaitLoad();
//		salvoAdminListing.tableSortingTest(oTableNameCol1, iColNum);
//		
//		iColNum=3;
//		sCol1ID="type";
//		oTableNameCol1=By.xpath(".//th[starts-with(@class,'"+ sCol1ID +"')]");
//		driver.findElement(oTableHeader).findElement(oTableNameCol1).click();
//		PageLoadWait.GenericWaitLoad();
//		salvoAdminListing.tableSortingTest(oTableNameCol1, iColNum);
//		
//		driver.findElement(oTableHeader).findElement(oTableNameCol1).click();
//		PageLoadWait.GenericWaitLoad();
//		salvoAdminListing.tableSortingTest(oTableNameCol1, iColNum);
//		PageLoadWait.GenericWaitLoad();
//		
//		TestActions.adminDeleteSalvo(GSTC2Salvo5Name,ADMIN_USERNAME);
//		TestActions.adminDeleteSalvo(GSTC2Salvo1Name,ADMIN_USERNAME);
//		TestActions.adminDeleteSalvo(GSTC2Salvo1Name,sLocalTester);
//		TestActions.adminDeleteSalvo(GSTC2Salvo4Name,sLocalTester);
//		Salvo400.adminSalvoSearch(true,GSTC2Salvo8Name);
//		Salvo400.adminSalvoSearch(true,"é½�å“ˆå°”p");
//		Salvo400.adminSalvoSearch(true,GSTC2Salvo7Name);
//		Salvo400.adminSalvoSearch(true,GSTC2Salvo6Name);
//		Salvo400.adminSalvoSearch(true,"aé½�");
//		Salvo400.adminSalvoSearch(false,"aé½�å°”");
//		Salvo400.adminSalvoSearch(false,"é½�a");
//		Salvo400.adminSalvoSearch(false,"é½�é½�å°”");
//		TestActions.adminDeleteSalvo(GSTC2Salvo6Name,ADMIN_USERNAME);
//		TestActions.adminDeleteSalvo(GSTC2Salvo7Name,ADMIN_USERNAME);
//		TestActions.adminDeleteSalvo(GSTC2Salvo8Name,sLocalTester2);
//		TestActions.adminDeleteSalvo(GSTC2Salvo9Name,sLocalTester);		
//		TestActions.logOut();
//		PageLoadWait.GenericWaitLoad();	
//		Date dateFinish = new Date();
//		finishedTime=dateFormat.format(dateFinish);
//		elapsedSeconds = (dateFinish.getTime()-date.getTime())/1000;
//		MyPrint.myPrint(this.getClass().getSimpleName() + " method '"+ Thread.currentThread().getStackTrace()[1].getMethodName()  + "'","** Logout user '" + sLocalTester2 + "' successfully. **");
//		MyPrint.myPrint(this.getClass().getSimpleName() + " method '" + Thread.currentThread().getStackTrace()[1].getMethodName() + "'","** TC Elapsed " + elapsedSeconds + " seonds. Finished ***");
