package autom.common;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



//import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.TimeoutException;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import Exception.FilloException;
import Fillo.Connection;
import Fillo.Fillo;
import Fillo.Recordset;
public class RegularTC {
	protected boolean ADRun=false;
	public static HashMap<Integer, String> mapSuite;
	public RegularTC() {
		String computerName,sResult,buildString,ServerURL;
		HashMap<Integer, String> mapSuite;
		String sTestNGPath=System.getProperty("user.dir") + "\\testng\\";
		String sTCSuitesFile="C:\\Temp\\debug\\testCaseStatus.xlsx";
		readWriteINI<String> iniObj2,iniObjSuiteIni;
		int iResult;
		try {
			Fillo fillo = new Fillo();
			LinkedHashMap<String , String> mapSuiteTestCases;
			Connection con;
			String query,sRunSummary;
			Recordset recordSet;
			Integer iTestSuite=1;
			mapSuite= new HashMap<Integer, String>();
			HashMap<String, String> mapTCClass;
			HashMap<String, String> mapTCRunMethod;
			HashMap<String, HashMap<String, String>> mapTCRunNotify = null;
			HashMap<String, String> mapTCRunNotifyTemp;
			HashMap<String,  HashMap<String, String>> mapMonitors = null;
			HashMap<String, String> mapMonitorsTmp;
			con = fillo.getConnection(sTCSuitesFile);
			sRunSummary="testSuite";
			query = "Select * from " + sRunSummary;
			recordSet = con.executeQuery(query);
			String sField;
			while (recordSet.next()) {
				if (recordSet.getField("Execute").equalsIgnoreCase("Y")) {
					sField=null;
					sField=recordSet.getField("TestSuite");
					if(sField.length()>0){
						mapSuite.put(iTestSuite,sField);
						iTestSuite++;        		
					}
				}
			}
			recordSet.close();
			con.close();
			boolean bContinueNext=false;
			for (String sSuiteName : mapSuite.values()) {
				fillo = new Fillo();	
				iniObjSuiteIni=new readWriteINI<String>("agent","suiterun");
				iniObjSuiteIni.setValue("true");
				iniObjSuiteIni.WriteINI();
				mapTCClass= new HashMap<String, String>();
				mapTCRunMethod= new HashMap<String, String>();
				mapSuiteTestCases= new LinkedHashMap<String, String>();
				if(sSuiteName.toUpperCase().equals("NOTIFICATION")){
					mapTCRunNotify= new HashMap<String,  HashMap<String, String>>();
					mapMonitors= new HashMap<String,  HashMap<String, String>>();
					con = fillo.getConnection(sTCSuitesFile);
					sRunSummary="Monitors";
					query = "Select * from " + sRunSummary;
					recordSet = con.executeQuery(query);
					while (recordSet.next()) {
						mapMonitorsTmp = new HashMap<String,String>();
						String sTableColumnName="";
						for(int icount=1; icount<recordSet.getFieldNames().size(); icount++){
							sTableColumnName=recordSet.getFieldNames().get(icount).toString();
							mapMonitorsTmp.put(sTableColumnName, recordSet.getField(sTableColumnName));
						}
						mapMonitors.put(recordSet.getField(recordSet.getFieldNames().get(0).toString()),
								mapMonitorsTmp);
					}
					recordSet.close();
					con.close();
				}
				
				if(sSuiteName.toUpperCase().substring(0, 2).equals("AD")){
					ADMatch1(sSuiteName);
					setAD();
					continue;
				} else {
					ADMatch2(sSuiteName);
				}
				mapSuiteTestCases.clear();
				mapTCRunMethod.clear();
				mapTCClass.clear();
				con = fillo.getConnection(sTCSuitesFile);
				query = "Select * from " + sSuiteName;
				recordSet = con.executeQuery(query);
				String sTestCase;
				String sRunMethod="";
				String sInvolvedClass = "";
				while (recordSet.next()) {
					if(!recordSet.getField("Execute").isEmpty()){
						if (recordSet.getField("Execute").equalsIgnoreCase("Y")) {
							sTestCase=recordSet.getField("TestCase");
							String sContent=recordSet.getField("Content");
							mapSuiteTestCases.put(sTestCase,sContent);
							sRunMethod=recordSet.getField("RunMethod");
							sInvolvedClass = "";
							sInvolvedClass=	recordSet.getField("Class");
							mapTCClass.put(sTestCase, sInvolvedClass);
							mapTCRunMethod.put(sTestCase, sRunMethod);
							if(sSuiteName.toUpperCase().equals("NOTIFICATION")){
								mapTCRunNotifyTemp= new HashMap<String, String>();
								mapTCRunNotifyTemp.put("SlaveMachine",recordSet.getField("SlaveMachine"));
								mapTCRunNotifyTemp.put("MasterLoginUser",recordSet.getField("MasterLoginUser"));
								mapTCRunNotifyTemp.put("LoginUser",recordSet.getField("LoginUser"));
								mapTCRunNotifyTemp.put("ResponseKeyWord",recordSet.getField("ResponseKeyWord"));
								mapTCRunNotify.put(sTestCase, mapTCRunNotifyTemp);
							}
							bContinueNext=true;
						}
					} else {
						bContinueNext=true;
						break;
					}
				}
				recordSet.close();
				con.close();
				if(bContinueNext){
					if(mapSuiteTestCases.isEmpty() || mapSuiteTestCases.size()==0){
						bContinueNext=false;
						continue;
					}
					System.out.println("start test suite:'" +sSuiteName + "' testing");
					bContinueNext=false;
				} else {
					if(mapSuiteTestCases.isEmpty() || mapSuiteTestCases.size()==0){
						bContinueNext=false;
						continue;
					}				
				}
				iniObjSuiteIni=new readWriteINI<String>("suiteclean","settingclean");
				iniObjSuiteIni.setValue("true");
				iniObjSuiteIni.WriteINI();
				Date dateOfTesting;
				DateFormat df,tf;
				String TestDate,TestTime;
				df = new SimpleDateFormat("MM-dd-yyyy");
				tf = new SimpleDateFormat("HH:mm:ss");

				for(String sRunTCName : mapSuiteTestCases.keySet() ){
					if(sRunTCName.isEmpty()){
						continue;
					}
					createTestNGXML myTestXML=new createTestNGXML(sSuiteName);
					String sXMLName="runtestsuite",sClassString="";
					sResult="";
					
					myTestXML.setName(sXMLName);
					myTestXML.setPath(sTestNGPath);
					sClassString=mapTCClass.get(sRunTCName);
					myTestXML.createSingleTCXML(sClassString);
					if(mapTCRunMethod.get(sRunTCName).equalsIgnoreCase("content")){
						if(sSuiteName.toUpperCase().equals("MAPCENTERING")){
							iniObj2=new readWriteINI<String>("GEOCODING","code");
							iniObj2.setValue((mapSuiteTestCases.get(sRunTCName).split("-"))[0]);
							iniObj2.WriteINI();
							iniObj2=new readWriteINI<String>("MAPTYPE","type");
							iniObj2.setValue((mapSuiteTestCases.get(sRunTCName).split("-"))[1]);
							iniObj2.WriteINI();
						} else if (sSuiteName.toUpperCase().equals("NOTIFICATION")){
							iniObj2=new readWriteINI<String>(sSuiteName.toUpperCase(),sSuiteName.toUpperCase() + 
									"TEST");
							iniObj2.setValue(mapSuiteTestCases.get(sRunTCName));
							iniObj2.WriteINI();
							
							iniObj2=new readWriteINI<String>(sSuiteName.toUpperCase(),"MasterLoginUser");
							iniObj2.setValue(mapTCRunNotify.get(sRunTCName).get("MasterLoginUser"));
							iniObj2.WriteINI();
							
							iniObj2=new readWriteINI<String>(sSuiteName.toUpperCase(),"SlaveMachine");
							iniObj2.setValue(mapTCRunNotify.get(sRunTCName).get("SlaveMachine"));
							iniObj2.WriteINI();
							String sMachineIDs=mapTCRunNotify.get(sRunTCName).get("SlaveMachine");
							System.out.println("Going to work with slave machine with name:\n'" + 
									mapTCRunNotify.get(sRunTCName).get("SlaveMachine") + 
									"'\n" + 
									"with properties:\n" + 
									mapMonitors.get(sMachineIDs));
							Set<Entry<String, String>> set = mapMonitors.get(sMachineIDs).entrySet();
							Iterator<Entry<String, String>> i = set.iterator();
							while(i.hasNext()) {
								Map.Entry<String, String> me = (Map.Entry<String, String>)i.next();
								iniObj2=new readWriteINI<String>(sSuiteName.toUpperCase(),me.getKey());
								iniObj2.setValue(me.getValue());
								iniObj2.WriteINI();
							}
							iniObj2=new readWriteINI<String>("MONITOR","LoginUser");
							iniObj2.setValue(mapTCRunNotify.get(sRunTCName).get("LoginUser"));
							iniObj2.WriteINI();
							iniObj2=new readWriteINI<String>("MONITOR","ResponseKeyWord");
							iniObj2.setValue(mapTCRunNotify.get(sRunTCName).get("ResponseKeyWord"));
							iniObj2.WriteINI();
							iniObj2=new readWriteINI<String>("MONITOR","MasterIP");
							
//							System.out.println(InetAddress.getLocalHost().toString().split("/")[1]);
							
							iniObj2.setValue(InetAddress.getLocalHost().toString().split("/")[1]);
							iniObj2.WriteINI();
							iniObj2=new readWriteINI<String>("MONITOR","MONITORTEST");
							iniObj2.setValue(mapSuiteTestCases.get(sRunTCName));
							iniObj2.WriteINI();
						} else {
							iniObj2=new readWriteINI<String>(sSuiteName.toUpperCase(),sSuiteName.toUpperCase() + 
									"TEST");
							iniObj2.setValue(mapSuiteTestCases.get(sRunTCName));
							iniObj2.WriteINI();
						}
					}
					List<String> suites = Lists.newArrayList();
					suites.add(sTestNGPath + sXMLName + ".xml");
					//// add multiple xml files from here.....
//					suites.add(sTestNGPath + sXMLName2 + ".xml");
//					suites.add(sTestNGPath + sXMLName3 + ".xml");
					Stopwatch stopwatch = Stopwatch.createStarted();
					dateOfTesting = Calendar.getInstance().getTime();
					TestDate=df.format(dateOfTesting).toString();
					TestTime=tf.format(dateOfTesting).toString();
					stopwatch.reset();
					stopwatch.start();
					try{
						
						iResult=0;
						TestNG sTestRun=new TestNG();
						sTestRun.setTestSuites(suites);
						sTestRun.run();
						iResult=sTestRun.getStatus();
//						sResult=Reporter.getCurrentTestResult().getStatus();
//						sResult=sTestRun.testResult;
						
						/////////// trigger the testing from here.
//						TestNGRunSuites sTestRun= new TestNGRunSuites(suites);
//						sResult=sTestRun.testResult;
//						sTestRun=null;
					} catch (TimeoutException e1){
						sResult="Fail";
					}
					stopwatch.stop();
					readWriteINI<String> iniObj7=new readWriteINI<String>("BuildInfo","BuildString");
					buildString=iniObj7.ini.get(iniObj7.Category,iniObj7.Key,String.class);
					iniObj7=new readWriteINI<String>("Server","ServerURL");
					ServerURL=iniObj7.ini.get(iniObj7.Category,iniObj7.Key,String.class);
					try{
						fillo = new Fillo();
						con = fillo.getConnection(sTCSuitesFile);
						query = "Update " + sSuiteName + 
								" Set TestResult='" + 
								sResult + 
								"' where TestCase='" + 
								sRunTCName + "'";
						try{
							TestActions.robotWait(1);
							con.executeUpdate(query);							
						} catch (FilloException e2){
							TestActions.robotWait(2);							
							con.executeUpdate(query);
						}
						query = "Update " + sSuiteName + 
								" Set TimeElapsed=" + 
								stopwatch.elapsed(TimeUnit.SECONDS) + 
								" where TestCase='" + 
								sRunTCName + "'";
						try{
							TestActions.robotWait(1);
							con.executeUpdate(query);							
						} catch (FilloException e2){
							TestActions.robotWait(2);							
							con.executeUpdate(query);
						}
						query = "Update " + sSuiteName + 
								" Set ServerURL='" + 
								ServerURL + 
								"' where TestCase='" + 
								sRunTCName + "'";
						try{
							TestActions.robotWait(1);
							con.executeUpdate(query);							
						} catch (FilloException e2){
							TestActions.robotWait(2);							
							con.executeUpdate(query);
						}
						query = "Update " + sSuiteName + 
								" Set Build='" + buildString + 
								"' where TestCase='" + 
								sRunTCName + "'";
						try{
							TestActions.robotWait(1);
							con.executeUpdate(query);							
						} catch (FilloException e2){
							TestActions.robotWait(2);							
							con.executeUpdate(query);
						}
						query = "Update " + sSuiteName +
								" Set TestDate='" + 
								TestDate + 
								"' where TestCase='" + 
								sRunTCName + "'";
						con.executeUpdate(query);
						query = "Update " +	sSuiteName + 
								" Set TestTime='" + 
								TestTime + 
								"' where TestCase='" + 
								sRunTCName + "'";
						try{
							TestActions.robotWait(1);
							con.executeUpdate(query);							
						} catch (FilloException e2){
							TestActions.robotWait(2);							
							con.executeUpdate(query);
						}
						computerName = InetAddress.getLocalHost().getHostName();
						query = "Update " + sSuiteName + 
								" Set TestMachine='" + 
								computerName + 
								"' where TestCase='" + 
								sRunTCName + "'";
						try{
							TestActions.robotWait(1);
							con.executeUpdate(query);							
						} catch (FilloException e2){
							TestActions.robotWait(2);							
							con.executeUpdate(query);
						}
						con.close();
						TestActions.robotWait(3);
					} catch (FilloException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e2){
						e2.printStackTrace();
					}
				}
			}
//			System.exit(0);
		} catch (FilloException e) {
			e.printStackTrace();

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
//		System.exit(0);
	}
	
    private static void ADMatch1(String sSuiteName) {
		boolean ADTest=ADTestPrefsCopy.getIsAuthModeTest();
		String filePath=ADTestPrefsCopy.getFileName();
		String sTagName="entry";
		String sAttributeName="key";
		String sTargetKey="common.global.authmodeturnon";
		String sDocType="http://java.sun.com/dtd/properties.dtd";
		if(sSuiteName.toUpperCase().substring(0, 2).equals("AD")){
			if(!ADTest){
		        createTestNGXML myTestPrefXML=new createTestNGXML();
				myTestPrefXML.modifyXMLSysprefs(filePath, sTagName, sAttributeName,sTargetKey,"true",sDocType);
			}
		} else{
			if(ADTest){
		        createTestNGXML myTestPrefXML=new createTestNGXML();
				myTestPrefXML.modifyXMLSysprefs(filePath, sTagName, sAttributeName,sTargetKey,"false",sDocType);
			}
		}
    }
    
    protected void setAD(){
    	ADRun=true;
    }
    
    private static void ADMatch2(String sSuiteName) {
		boolean ADTest=ADTestPrefsCopy2.getIsAuthModeTest();
		String filePath=ADTestPrefsCopy2.getFileName();
		String sTagName="entry";
		String sAttributeName="key";
		String sTargetKey="common.global.authmodeturnon";
		String sDocType="http://java.sun.com/dtd/properties.dtd";
		if(sSuiteName.toUpperCase().substring(0, 2).equals("AD")){
			if(!ADTest){
		        createTestNGXML myTestPrefXML=new createTestNGXML();
				myTestPrefXML.modifyXMLSysprefs(filePath, sTagName, sAttributeName,sTargetKey,"true",sDocType);
			}
		} else{
			if(ADTest){
		        createTestNGXML myTestPrefXML=new createTestNGXML();
				myTestPrefXML.modifyXMLSysprefs(filePath, sTagName, sAttributeName,sTargetKey,"false",sDocType);
			}
		}
    }
}