package autom.common;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
public class ModuleAuthMode extends MasterTest {
		public Map<String , String> objPropertytoUse;
		public Map<String , String> findElementMethod;
		protected String DomainGroupName;
		protected String Domain;
		protected String [] UserGroups;
		protected String DefaultDomin="QALAB.com";
		protected String DefaultDominGroup="qalab_g1";
		private String myTag1="ClientButton";
		private String myTag2="Access Rights";
		private String myTag3="Auth Mode";
		@SuppressWarnings("unused")
		private boolean ADTurnedOn=false;
		public ModuleAuthMode(Map<String , String> myDomain) {
	    	MyPrint.myPrint("Auth Mode","Domain with name '" + myDomain.get("Domain Name:_text") + "' will be created.");
	    	OpenAuthMode();	    	
		}
		public ModuleAuthMode(String sLocalUserGroup) {
			if(getAuthModeStatus()){
				Domain=DefaultDomin;
				UserGroups=null;				
				DomainGroupName=DefaultDominGroup;
				UserGroups = new String[1];
				UserGroups[0]=sLocalUserGroup;
				String iniFlag;
				Navigate();
		        readWriteINI<String> iniObj1=new readWriteINI<String>("AD","TURNON");
		        if(driver.findElement(By.name("CurrentMode")).getAttribute("value").toLowerCase().equalsIgnoreCase("remote using active directory")){
		        	iniFlag=iniObj1.ini.get(iniObj1.Category,iniObj1.Key,String.class);
		        	if(iniFlag==null || iniFlag.isEmpty() || iniFlag.equals("false")){
				        iniObj1.setValue("true");
				        iniObj1.WriteINI();
		        	}
		        	
		        } else {
		        	if(driver.findElement(By.name("CurrentMode")).getAttribute("value").toLowerCase().equalsIgnoreCase("local")){
			        	iniFlag=iniObj1.ini.get(iniObj1.Category,iniObj1.Key,String.class);
			        	if(iniFlag==null || iniFlag.isEmpty() || iniFlag.equals("true")){
					        iniObj1.setValue("false");
					        iniObj1.WriteINI();
			        	}	        		
		        	}
		        }
		        MapDomainGroup(sLocalUserGroup);
			}
		}
		
		public ModuleAuthMode() {
			if(getAuthModeStatus()){
				Domain=DefaultDomin;
				UserGroups=null;				
				DomainGroupName=DefaultDominGroup;
				UserGroups = new String[1];
//				UserGroups[0]=sLocalUserGroup;
				String iniFlag;
				Navigate();
		        readWriteINI<String> iniObj1=new readWriteINI<String>("AD","TURNON");
		        if(driver.findElement(By.name("CurrentMode")).getAttribute("value").toLowerCase().equalsIgnoreCase("remote using active directory")){
		        	iniFlag=iniObj1.ini.get(iniObj1.Category,iniObj1.Key,String.class);
		        	if(iniFlag==null || iniFlag.isEmpty() || iniFlag.equals("false")){
				        iniObj1.setValue("true");
				        iniObj1.WriteINI();
		        	}
		        	
		        } else {
		        	if(driver.findElement(By.name("CurrentMode")).getAttribute("value").toLowerCase().equalsIgnoreCase("local")){
			        	iniFlag=iniObj1.ini.get(iniObj1.Category,iniObj1.Key,String.class);
			        	if(iniFlag==null || iniFlag.isEmpty() || iniFlag.equals("true")){
					        iniObj1.setValue("false");
					        iniObj1.WriteINI();
			        	}	        		
		        	}
		        }
//		        MapDomainGroup(sLocalUserGroup);
			}
		}
		
		@SuppressWarnings("static-access")
		public boolean getAuthModeStatus() {
			return ADTurnedOn=super.AD;
		}
		
		public ModuleAuthMode(String [] sLocalUserGroups) {
//	    	System.out.println("Local user will be mapped to ddddddddd" +sLocalUserGroups.length+".");
	    	TestActions.clickElementAndWaitReady(By.xpath("//td[contains(text(),'QALAB.COM')]"));
			PageLoadWait.GenericWaitLoad();
	    	TestActions.clickElementAndWaitReady(By.xpath("//td[contains(text(),'qalab_g1')]"));
	    	PageLoadWait.GenericWaitLoad();
	    	TestActions.clickElementAndWaitReady(By.id("editDomainGroupButton"));
	    	PageLoadWait.GenericWaitLoad();
	    	PageLoadWait.GenericWaitLoad();
	    	By oUserGrptbl =By.id("userGroupTable");
	    	By oUserSave =By.id("saveNewItem");
	    	By oSaveConfig =By.id("saveCreate");	    	
	    	WebElement userGroupTable=driver.findElement(oUserGrptbl);
	    	List<WebElement> userGroupCheckBoxs=userGroupTable.findElements(By.xpath(".//input"));
//	    	int totalGrps;
//	    	totalGrps=userGroupCheckBoxs.size();
//	    	Iterator<WebElement> itr = userGroupCheckBoxs.iterator();
//	    	while(itr.hasNext()) {
//	    	    System.out.println(itr.next());
//	    	}
	    	String sGrpName;
	        for ( WebElement thisGrop: userGroupCheckBoxs) {
	        	sGrpName="";
	        	sGrpName=thisGrop.getAttribute("value");
	        	if(sGrpName.equals("AllUsers")){
	        		
	        	} else {
	        		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thisGrop);
	        		try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	        		if(Arrays.asList(sLocalUserGroups).contains(sGrpName)){
	        			if(!thisGrop.isSelected()){
	        				thisGrop.click();
	    	        		try {
	    						Thread.sleep(500);
	    					} catch (InterruptedException e) {
	    						e.printStackTrace();
	    					}
	        				PageLoadWait.GenericWaitLoad();	        				
	        			}
	        		} else {
	        			if(thisGrop.isSelected()){
	        				thisGrop.click();
	    	        		try {
	    						Thread.sleep(500);
	    					} catch (InterruptedException e) {
	    						e.printStackTrace();
	    					}
	        				PageLoadWait.GenericWaitLoad();
	        			}	        			
	        		}
	        	}
	        }
	        TestActions.clickElementAndWaitReady(oUserSave);
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();
	        TestActions.clickElementAndWaitReady(oSaveConfig);
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();
		}
		
		public void setDomainGrpName (String sName){
			this.DomainGroupName=sName;
		}
	    public void OpenAuthMode(){
			MyPrint.myPrint("Auth Mode","Navigate to Auth Mode screen");
			PageLoadWait.GenericWaitLoad();
			TestActions.clickElementAndWaitReady("id","adminSiteButton");
			PageLoadWait.GenericWaitLoad();
			TestActions.mouseOverElement("xpath","//div/ul/li/a[contains(text(), 'Access Rights')]");
			PageLoadWait.GenericWaitLoad();
			TestActions.clickElementAndWaitReady("xpath","//div/ul/li/ul/li/a[contains(text(), 'Auth Mode')]");
			PageLoadWait.GenericWaitLoad();
	    }
	    public void MapDomainGroup(String... args){
//	    	System.out.println("Local user will be mapped to '" +args.length+"' local users group.");
	    	TestActions.clickElementAndWaitReady(By.xpath("//td[contains(text(),'QALAB.COM')]"));
			PageLoadWait.GenericWaitLoad();
	    	TestActions.clickElementAndWaitReady(By.xpath("//td[contains(text(),'qalab_g1')]"));
	    	PageLoadWait.GenericWaitLoad();	    	
	    	TestActions.clickElementAndWaitReady(By.id("editDomainGroupButton"));
	    	TestActions.robotWait(1);
	    	PageLoadWait.GenericWaitLoad();
	    	PageLoadWait.GenericWaitLoad();
	    	
	    	if (!driver.findElement(By.xpath("//input[@name='domainGroupSituationAssignee']")).isSelected()) {
	    	     driver.findElement(By.xpath("//input[@name='domainGroupSituationAssignee']")).click();
	    	     TestActions.robotWait(1);
	    	}
	    	By oUserGrptbl =By.id("userGroupTable");
	    	By oUserSave =By.id("saveNewItem");
	    	By oSaveConfig =By.id("saveCreate");	    	
	    	WebElement userGroupTable=driver.findElement(oUserGrptbl);
	    	List<WebElement> userGroupCheckBoxs=userGroupTable.findElements(By.xpath(".//input"));
	    	String sGrpName;
	    	@SuppressWarnings("unused")
			boolean thisgroupvalue;
	        for ( WebElement thisGrop: userGroupCheckBoxs) {
	        	sGrpName="";
	        	thisgroupvalue=false;
	        	sGrpName=thisGrop.getAttribute("value");
	        	if(sGrpName.equals("AllUsers")){
	        		
	        	} else {
	        		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thisGrop);
	        		try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		if(Arrays.asList(args).contains(sGrpName)){
	        			if(!thisGrop.isSelected()){
	        				thisGrop.click();
	    	        		try {
	    						Thread.sleep(500);
	    					} catch (InterruptedException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	        				PageLoadWait.GenericWaitLoad();
	        			}
	        		} else {
	        			if(thisGrop.isSelected()){
	        				thisGrop.click();
	    	        		try {
	    						Thread.sleep(500);
	    					} catch (InterruptedException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	        				PageLoadWait.GenericWaitLoad();
	        			}	        			
	        		}
	        	}
	        }
	        TestActions.clickElementAndWaitReady(oUserSave);
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();	        
//	        TestActions.clickElementAndWaitReady(By.xpath("//td[contains(text(),'testUsers')]"));
//	    	PageLoadWait.GenericWaitLoad();
//	    	TestActions.clickElementAndWaitReady(By.id("editDomainGroupButton"));
//	    	PageLoadWait.GenericWaitLoad();
//	    	PageLoadWait.GenericWaitLoad();
//	    	WebElement userGroupTable2=driver.findElement(oUserGrptbl);
//	    	List<WebElement> userGroupCheckBoxs2=userGroupTable2.findElements(By.xpath(".//input"));	        
//	        
//	    	String sGrpName2;
//	        for ( WebElement thisGrop2: userGroupCheckBoxs2) {
//	        	sGrpName2="";
//	        	thisgroupvalue=false;
//	        	sGrpName2=thisGrop2.getAttribute("value");
//	        	if(sGrpName2.equals("AllUsers")){
//	        		
//	        	} else {
//	        		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", thisGrop2);
//	        		try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//        			if(thisGrop2.isSelected()){
//        				thisGrop2.click();
//        				PageLoadWait.GenericWaitLoad();
//        			}
//	        	}
//	        }
//	        TestActions.clickElementAndWaitReady(oUserSave);
	        TestActions.robotWait(1);
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();	        
	        TestActions.clickElementAndWaitReady(oSaveConfig);
	        TestActions.robotWait(1);
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();
	        driver.findElement(By.xpath("//form[@id='remoteAuthFormFORM']")).findElement(By.xpath(".//div[@id='editDomainButton']")).click();
	        PageLoadWait.GenericWaitLoad();
	        PageLoadWait.GenericWaitLoad();
	        driver.findElement(By.xpath("//div[@id='TB_window']")).findElement(By.xpath(".//input[@name='domainUpdateCache']")).click();
	        
	        PageLoadWait.GenericWaitLoad();
	        driver.findElement(By.xpath("//div[@id='TB_window']")).findElement(By.xpath(".//div[@id='saveNewItem']")).click();
	        PageLoadWait.GenericWaitLoad();
	        
	        
	    }
	    
	    public void AddDomain(LinkedHashMap<String , String>myDomain){
			MyPrint.myPrint("Auth Mode","add new Domain with name '" + myDomain.get("Domain Name:_text") + "'");
			PageLoadWait.GenericWaitLoad();
			if (driver.findElements(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + myDomain.get("Domain Name:").toLowerCase() +"')]")).size()!=0) {
				DeleteDomain(myDomain);
			}		
			Set<Entry<String, String>>set = myDomain.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
//			Set set = myLocalUser.entrySet();
//			Iterator iterator = set.iterator();		
		    while(iterator.hasNext()) {
	            Map.Entry<String, String> me = (Map.Entry<String, String>)iterator.next();
	            
	            if ((me.getValue()).toString().length()>0) {
	            	if (((me.getValue()).toString()).contains("_")) {
	            	switch ((me.getValue()).toString()) {
	            		case "action_click":
	            			TestActions.clickElementWithNoWait(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "action_clickwait":
	            			TestActions.clickElementAndWaitReady(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "wait_visible":
	            			TestActions.waitElementVisibility(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "action_enter":
	            			TestActions.textInput(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            		default:
	            			TestActions.ElementSendKeys(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()), myDomain.get(me.getKey()));
	            			break;
	            	}
	            } 
	            	else {
		            	switch ((me.getValue()).toString()) {
	            			case "checktrue":
	            				TestActions.checkBoxInput(me.getKey(), "true");
	            				break;
	            			case "checkfalse":
	            				TestActions.checkBoxInput(me.getKey(), "false");
	            				break;        				
	            			default:
	            				TestActions.textInput(me.getKey(), (me.getValue()).toString());
	            				break;
	            	}
	            }
	         }
		    }
	    }
	    
	    public void AddDomain2(LinkedHashMap<String , String>myDomain)
	    {	
			MyPrint.myPrint("Auth Mode","add new Domain with name '" + myDomain.get("Domain Name:_text") + "'");
			PageLoadWait.GenericWaitLoad();
			if (driver.findElements(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + myDomain.get("Domain Name:_text").toLowerCase() +"')]")).size()!=0) {
				DeleteDomain(myDomain);
			}
			
			Set<Entry<String, String>>set = myDomain.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
		    while(iterator.hasNext()) {
	            Map.Entry<String, String> me = (Map.Entry<String, String>)iterator.next();
	            if ((me.getValue()).toString().length()>0) {
	            	if (((me.getKey()).toString()).contains("_")) {
	            	switch ((((me.getKey()).split("_"))[1]).toLowerCase()) {
	            		case "action_click":
	            			TestActions.clickElementWithNoWait(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "buttonid":
	            			TestActions.ElementButtonClick("id",(me.getKey().split("_"))[0],1);
	            			break;
	            		case "button":
	            			TestActions.ElementButtonClick("id",(me.getKey().split("_"))[0],1);
	            			break;
	            		case "action_clickwait":
	            			TestActions.clickElementAndWaitReady(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "text":
	            			TestActions.textInput((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			break;            			
	            		case "label":
			            	switch ((me.getValue()).toString().toLowerCase()) {
	            				case "click":
	            					TestActions.labelClick((me.getKey().split("\\_", -1))[0]);
	            					break;
	            				case "verify":
	            					TestActions.checkBoxInput(me.getKey(), "false");
	            					break;        				
	            				default:
	            					TestActions.textInput(me.getKey(), (me.getValue()).toString());
	            					break;
			            	}	            			
	            			break;	            			
	            		case "checkbox":
	            			if ((me.getValue().toString()).toLowerCase().contains("true") || me.getValue().toString().toLowerCase().contains("false")){
	            				TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			} else {
	            				if ((me.getValue().toString()).toLowerCase().contains("1")) {
	            					TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],"true");
	            				} else {
	            					TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],"false");
	            				}
	            			}
	            			break;
	            		case "checkboxbeforetext":
	            			if ((me.getValue().toString()).toLowerCase().contains("true") || me.getValue().toString().toLowerCase().contains("false")){
	            				TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			} else {
	            				if ((me.getValue().toString()).toLowerCase().contains("1")) {
	            					TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],"true");
	            				} else {
	            					TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],"false");
	            				}
	            			}
	            			break;
	            		case "wait_visible":
	            			TestActions.waitElementVisibility(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "action_enter":
	            			TestActions.textInput(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            		default:
	            			TestActions.ElementSendKeys(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()), myDomain.get(me.getKey()));
	            			break;
	            	}
	            } 
	            	else {
		            	switch ((me.getValue()).toString()) {
	            			case "checktrue":
	            				TestActions.checkBoxInput(me.getKey(), "true");
	            				break;
	            			case "checkfalse":
	            				TestActions.checkBoxInput(me.getKey(), "false");
	            				break;        				
	            			default:
	            				TestActions.textInput(me.getKey(), (me.getValue()).toString());
	            				break;
	            	}
	            }
	         }
		    }
	    }	    
	    
	    
	    public void AddDomainGroup(LinkedHashMap<String , String>myDomain){
			PageLoadWait.GenericWaitLoad();
			Set<Entry<String, String>>set = myDomain.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
		    while(iterator.hasNext()) {
	            Map.Entry<String, String> me = (Map.Entry<String, String>)iterator.next();
	            if ((me.getValue()).toString().length()>0) {
	            	if (((me.getKey()).toString()).contains("_")) {
	            	switch ((((me.getKey()).split("_"))[1]).toLowerCase()) {
	            		case "action_click":
	            			TestActions.clickElementWithNoWait(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "buttonid":
	            			TestActions.ElementButtonClick("id",(me.getKey().split("_"))[0],1);
	            			break;
	            		case "button":
	            			TestActions.ElementButtonClick("id",(me.getKey().split("_"))[0],1);
	            			break;
	            		case "action_clickwait":
	            			TestActions.clickElementAndWaitReady(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "text":
	            			TestActions.textInput((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			break;            			
	            		case "label":
			            	switch ((me.getValue()).toString().toLowerCase()) {
	            				case "click":
	            					TestActions.labelClick((me.getKey().split("\\_", -1))[0]);
	            					break;
	            				case "verify":
	            					TestActions.checkBoxInput(me.getKey(), "false");
	            					break;        				
	            				default:
	            					TestActions.textInput(me.getKey(), (me.getValue()).toString());
	            					break;
			            	}	            			
	            			break;	            			
	            		case "checkbox":
	            			if ((me.getValue().toString()).toLowerCase().contains("true") || me.getValue().toString().toLowerCase().contains("false")){
	            				TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			} else {
	            				if ((me.getValue().toString()).toLowerCase().contains("1")) {
	            					TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],"true");
	            				} else {
	            					TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],"false");
	            				}
	            			}
	            			break;
	            		case "checkboxbeforetext":
	            			if ((me.getValue().toString()).toLowerCase().contains("true") || me.getValue().toString().toLowerCase().contains("false")){
	            				TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			} else {
	            				if ((me.getValue().toString()).toLowerCase().contains("1")) {
	            					TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],"true");
	            				} else {
	            					TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],"false");
	            				}
	            			}
	            			break;
	            		case "wait_visible":
	            			TestActions.waitElementVisibility(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "action_enter":
	            			TestActions.textInput(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            		default:
	            			TestActions.ElementSendKeys(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()), myDomain.get(me.getKey()));
	            			break;
	            	}
	            } 
	            	else {
		            	switch ((me.getValue()).toString()) {
	            			case "checktrue":
	            				TestActions.checkBoxInput(me.getKey(), "true");
	            				break;
	            			case "checkfalse":
	            				TestActions.checkBoxInput(me.getKey(), "false");
	            				break;        				
	            			default:
	            				TestActions.textInput(me.getKey(), (me.getValue()).toString());
	            				break;
	            	}
	            }
	         }
		    }
	    }
	    
	    
	    public void EditDomainGroup(LinkedHashMap<String , String>myDomain)
	    {	
			PageLoadWait.GenericWaitLoad();
			Set<Entry<String, String>>set = myDomain.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
		    while(iterator.hasNext()) {
	            Map.Entry<String, String> me = (Map.Entry<String, String>)iterator.next();
	            if ((me.getValue()).toString().length()>0) {
	            	if (((me.getKey()).toString()).contains("_")) {
	            	switch ((((me.getKey()).split("_"))[1]).toLowerCase()) {
	            		case "action_click":
	            			TestActions.clickElementWithNoWait(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "buttonid":
	            			TestActions.ElementButtonClick("id",(me.getKey().split("_"))[0],1);
	            			break;
	            		case "button":
	            			TestActions.ElementButtonClick("id",(me.getKey().split("_"))[0],1);
	            			break;
	            		case "buttonclicknowait":
	            			TestActions.ElementButtonClick("id",(me.getKey().split("_"))[0]);
	            			break;
	            		case "waitstatetrue":
	            			TestActions.waitUntilBooleanCondition("id",(me.getKey().split("_"))[0],(me.getValue()).toString(),true);
	            			break;	            			
	            		case "waitstatefalse":
	            			TestActions.waitUntilBooleanCondition("id",(me.getKey().split("_"))[0],(me.getValue()).toString(),false);
	            			break;         			
	            		case "popupokbutton":	
	            			TestActions.selectOKAlert();
	            		    PageLoadWait.GenericWaitLoad();
	            		    break;
	            		case "action_clickwait":
	            			TestActions.clickElementAndWaitReady(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "text":
	            			TestActions.textInput((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			break;            			
	            		case "label":
			            	switch ((me.getValue()).toString().toLowerCase()) {
	            				case "click":
	            					TestActions.labelClick((me.getKey().split("\\_", -1))[0]);
	            					break;
	            				case "verify":
	            					TestActions.checkBoxInput(me.getKey(), "false");
	            					break;        				
	            				default:
	            					TestActions.textInput(me.getKey(), (me.getValue()).toString());
	            					break;
			            	}	            			
	            			break;	            			
	            		case "checkbox":
	            			if ((me.getValue().toString()).toLowerCase().contains("true") || me.getValue().toString().toLowerCase().contains("false")){
	            				TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			} else {
	            				if ((me.getValue().toString()).toLowerCase().contains("1")) {
	            					TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],"true");
	            				} else {
	            					TestActions.checkBoxInput((me.getKey().split("\\_", -1))[0],"false");
	            				}
	            			}
	            			break;
	            		case "checkboxbeforetext":
	            			if ((me.getValue().toString()).toLowerCase().contains("true") || me.getValue().toString().toLowerCase().contains("false")){
	            				TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],(me.getValue()).toString());
	            			} else {
	            				if ((me.getValue().toString()).toLowerCase().contains("1")) {
	            					TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],"true");
	            				} else {
	            					TestActions.checkBoxInputBeforeText((me.getKey().split("\\_", -1))[0],"false");
	            				}
	            			}
	            			break;
	            		case "wait_visible":
	            			TestActions.waitElementVisibility(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            			break;
	            		case "action_enter":
	            			TestActions.textInput(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()));
	            		default:
	            			TestActions.ElementSendKeys(this.objPropertytoUse.get(me.getKey()), this.findElementMethod.get(me.getKey()), myDomain.get(me.getKey()));
	            			break;
	            	}
	            } 
	            	else {
		            	switch ((me.getValue()).toString()) {
	            			case "checktrue":
	            				TestActions.checkBoxInput(me.getKey(), "true");
	            				break;
	            			case "checkfalse":
	            				TestActions.checkBoxInput(me.getKey(), "false");
	            				break;        				
	            			default:
	            				TestActions.textInput(me.getKey(), (me.getValue()).toString());
	            				break;
	            	}
	            }
	         }
		    }
	    }
	    public void DeleteDomain(LinkedHashMap<String , String>myDomain){
			MyPrint.myPrint("Auth Mode","delete AutomationTest Auth Mode Domain '" + myDomain.get("Domain Name:_text") + "'.");
			PageLoadWait.GenericWaitLoad();
			driver.findElement(By.xpath("//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + myDomain.get("Domain Name:_text").toLowerCase() +"')]")).click();
			PageLoadWait.GenericWaitLoad();
			String aaa="//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + myDomain.get("Domain Name:_text").toLowerCase() +"')]";
			TestActions.clickElementAndWaitReady("xpath",aaa);
			TestActions.waitElementClickableReady("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteDomainButton']");
		    TestActions.clickElementWithNoWait("xpath","//ul/li/div[@class='buttonFactory'][@id='deleteDomainButton']");
		    TestActions.selectOKAlert();
			PageLoadWait.GenericWaitLoad();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    PageLoadWait.GenericWaitLoad();
	    }
	    public void Navigate() {
	    	TestActions.Navigation(myTag1,myTag2,myTag3);
			TestActions.waitElementClickableReady(By.id("editDomainButton"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("domainGroupTable")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("domainTable")));	
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul/li/div[@class='buttonFactory'][@id='addDomainGroupButton']")));    	
	    }  
}
