package autom.common;

import java.util.LinkedHashMap;

public class LocalUserDataMap {
	private String sUserGrp;
	@SuppressWarnings("unused")
	private String sUserName;
	private String sUsrPWD;
	protected LinkedHashMap<String , String> mTestLocalUser;
	
	public LocalUserDataMap(String sLclUsrGrp, String sLocalTester){
		sUserGrp=sLclUsrGrp;
		sUserName=sLocalTester;
		sUsrPWD=sLocalTester+1;
		LinkedHashMap<String , String> myTestLocalUser = new LinkedHashMap<String, String>();
		myTestLocalUser.put("Add_button", "action_click");
		myTestLocalUser.put("Login ID", "wait_visible");
		myTestLocalUser.put("User Group Access Table", "wait_visible");
		myTestLocalUser.put("Create", "wait_visible");
		myTestLocalUser.put("Login ID", sLocalTester);
		myTestLocalUser.put("Title/Position", "AutoTestLocalUser05");
		myTestLocalUser.put("Full Name", "Auto TestLocal User05");
		myTestLocalUser.put("Supervisor", "AutoLocalUserSupervisor");
		myTestLocalUser.put("* Password", sUsrPWD);
		myTestLocalUser.put("Reenter Password", sUsrPWD);
		myTestLocalUser.put("Primary Phone", "");
		myTestLocalUser.put("Email Address", "Dave@gmail.com");
		myTestLocalUser.put("Secondary Phone", "");
		myTestLocalUser.put("Instant Messenger", "dfgdfg@sdf");
		if(sUserGrp!=null){
			if(sUserGrp.length()>0){
				myTestLocalUser.put(sUserGrp, "action_select");	
			}
		}
		myTestLocalUser.put("Owner", "");
		myTestLocalUser.put("Save_button", "action_click");
		mTestLocalUser=myTestLocalUser;
	}
	public void SetUserWithData(String sFieldName, String sFieldData){
		mTestLocalUser.put(sFieldName, sFieldData);

	}
	public void AddUserGroup(String sGroupName){
		mTestLocalUser.put(sGroupName, "action_select");

	}
}
