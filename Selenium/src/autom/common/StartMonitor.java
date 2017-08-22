package autom.common;
import java.io.InputStream;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class StartMonitor {
	public String host;
	public String user;
	public String password;
	public String command1;

	public StartMonitor(String sHost, String sUser, String sPassword, String sCommand){
		host=sHost;
		user=sUser;
		password=sPassword;
		command1=sCommand;
		sftRemoteIni();
		ExecMonitor();
	}
	public StartMonitor(){
		readWriteINI<String> oMapNOTIFICATION=new readWriteINI<String>("NOTIFICATION","SlaveIP");
		host=oMapNOTIFICATION.ini.get(oMapNOTIFICATION.Category,
				oMapNOTIFICATION.Key,String.class);
		oMapNOTIFICATION=new readWriteINI<String>("NOTIFICATION",
				"SlaveUser");
		user=oMapNOTIFICATION.ini.get(oMapNOTIFICATION.Category,
				oMapNOTIFICATION.Key,
				String.class);
		oMapNOTIFICATION=new readWriteINI<String>("NOTIFICATION",
				"SlavePass");
		password=oMapNOTIFICATION.ini.get(oMapNOTIFICATION.Category,
				oMapNOTIFICATION.Key,
				String.class);
		command1="RunSlave.bat";
		sftRemoteIni();
		ExecMonitor();
	}
	public StartMonitor(String sMachine){
		readWriteINI<String> oMapNOTIFICATION=new readWriteINI<String>("NOTIFICATION",
				"SlaveIP");
		host=oMapNOTIFICATION.ini.get(oMapNOTIFICATION.Category,
				oMapNOTIFICATION.Key,
				String.class);
		oMapNOTIFICATION=new readWriteINI<String>("NOTIFICATION",
				"SlaveUser");
		user=oMapNOTIFICATION.ini.get(oMapNOTIFICATION.Category,
				oMapNOTIFICATION.Key,
				String.class);
		oMapNOTIFICATION=new readWriteINI<String>("NOTIFICATION",
				"SlavePass");
		password=oMapNOTIFICATION.ini.get(oMapNOTIFICATION.Category,
				oMapNOTIFICATION.Key,
				String.class);
		command1="RunSlave.bat";
		sftRemoteIni();
		ExecMonitor();
	}

	protected void sftRemoteIni() {
		try{
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session=jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");
			String sourcefilename="localtestprefs.xml";
			String sourcefilepath="/cygdrive/C/Projects/trunk/Selenium/";
			String MasterXML="C:/Projects/trunk/Selenium/localtestprefs.xml";
			String sLocalSavedRemoteINI="C:/temp/testdata.ini";
			String sLocalSavedRemoteXML="C:/temp/RemoteXML.xml";
			String sourceini="/cygdrive/C/temp/testdata.ini";
//			String sKeyAttrValue="common.server.url";
			Channel channel2 = session.openChannel("sftp");
			channel2.connect();
			ChannelSftp channelsftp = (ChannelSftp) channel2;
			channelsftp.cd(sourcefilepath);
			channelsftp.get(sourcefilename, sLocalSavedRemoteXML);

			//	    	  createTestNGXML oFileMap= new createTestNGXML();
			//	    	  String MasterXML="C:/Projects/trunk/Selenium/localtestprefs.xml";
			//	    	  oFileMap.alignSysprefs(MasterXML, sLocalSavedRemoteXML, sKeyAttrValue);


			channelsftp.put(MasterXML, 
					sourcefilepath + 
					"/" + 
					sourcefilename);
			channelsftp.put(sLocalSavedRemoteINI, 
					sourceini);
			channelsftp.disconnect();
			channel2.disconnect();
			session.disconnect();
			System.out.println("DONE copy");
			TestActions.robotWait(5);
			try{Thread.sleep(2000);}catch(Exception ee){}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void ExecMonitor() {
		try{
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session=jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			System.out.println("Monitor Connected through SSH.");	    	
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command1);
			channel.setInputStream(null);
			((ChannelExec)channel).setErrStream(System.err);
			InputStream in=channel.getInputStream();
			channel.connect();
			byte[] tmp=new byte[1024];
			while(true){
				while(in.available()>0){
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					System.out.print(new String(tmp, 0, i));
				}
				if(channel.isClosed()){
					System.out.println("exit-status: " + 
							channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}
			channel.disconnect();
			session.disconnect();
			System.out.println("DONE");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void scpfile() {
		try{
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session=jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			System.out.println("Monitor Connected through SSH.");
			String command2 ="scp -r " + 
					user + 
					"@" + 
					host + 
					":/cygdrive/c/temp/testdata333.ini" + 
					" " +  
					"/cygdrive/c/Temp/RemoteINICopy.ini" ; 
			Channel channel=session.openChannel("exec");
			((ChannelExec)channel).setCommand(command2);
			channel.setInputStream(null);
			((ChannelExec)channel).setErrStream(System.err);
			InputStream in=channel.getInputStream();
			channel.connect();
			byte[] tmp=new byte[1024];
			while(true){
				while(in.available()>0){
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					System.out.print(new String(tmp, 0, i));
				}
				if(channel.isClosed()){
					System.out.println("exit-status: " + 
							channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}
			channel.disconnect();
			session.disconnect();
			System.out.println("DONE");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
} ////End of class