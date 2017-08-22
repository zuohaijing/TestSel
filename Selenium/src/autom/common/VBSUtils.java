package autom.common;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;
import org.joda.time.DateTime;

public class VBSUtils {

	private VBSUtils() {  }

	public static List<String> listRunningProcesses() {
		List<String> processList = new ArrayList<String>();
		try {

			File file = File.createTempFile("MyOntheFlyVBS",".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
					+ "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
					+ "Set service = locator.ConnectServer()\n"
					+ "Set processes = service.ExecQuery _\n"
					+ " (\"select name from Win32_Process\")\n"
					+ "For Each process in processes\n"
					+ "wscript.echo process.Name \n"
					+ "Next\n"
					+ "Set WSHShell = Nothing\n";

			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input =
					new BufferedReader
					(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return processList;
	}

	//	  public static void main(String[] args){
	//	    List<String> processes = VBSUtils.listRunningProcesses();
	//	    String result = "";
	//
	//	    Iterator<String> it = processes.iterator();
	//	    int i = 0;
	//	    while (it.hasNext()) {
	//	       result += it.next() +",";
	//	       i++;
	//	       if (i==10) {
	//	           result += "\n";
	//	           i = 0;
	//	       }
	//	    }
	//	    msgBox("Running processes : " + result);
	//	  }
	//
	//	  public static void msgBox(String msg) {
	//	    javax.swing.JOptionPane.showConfirmDialog((java.awt.Component)
	//	       null, msg, "VBSUtils", javax.swing.JOptionPane.DEFAULT_OPTION);
	//	  }

	public static List<String> WindowExist(String sTitle,int iSec) {
		List<String> processList = new ArrayList<String>();
		//		  	boolean bWindowExist=false;
		try {
			File file = File.createTempFile("MyOntheFlyVBS",".vbs");
			file.deleteOnExit();
			FileWriter fw = new java.io.FileWriter(file);

			String vbs = "dim objShell,oWShell, objWindow, strTitle\n"
					+ "set objShell = CreateObject(\"Shell.Application\")\n"

		                   + "strTitle = "+ sTitle +"\n"

		                   + "for each objWindow in objShell.Windows\n"
		                   + "if InStr(objWindow.FullName,\"IEXPLORE.EXE\") then\n"
		                   + "wscript.echo objWindow.document.title \n"
		                   //						   + "CreateObject(\"WScript.Shell\").Popup \"Existing IE Window with title\", 3, objWindow.FullName\n"
		                   + "End If\n"
		                   + "Next\n"		                   
		                   + "Set objShell = Nothing\n";

			fw.write(vbs);
			fw.close();
			Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
			BufferedReader input =
					new BufferedReader
					(new InputStreamReader(p.getInputStream()));
			String line;

			while ((line = input.readLine()) != null) {
				processList.add(line);
			}


			input.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return processList;
	}

	public static boolean WaitForWindowTitle(String sTitle,int iSec) {
		boolean bWindowExist=false;
		DateTime current;
		DateTime now = new DateTime();
		DateTime limit = now.plusSeconds(iSec);
		try {
			File file = File.createTempFile("MyOntheFlyVBS",".vbs");
			file.deleteOnExit();
			while(!bWindowExist){   
				FileWriter fw = new java.io.FileWriter(file);
				String vbs = "dim objShell,oWShell, objWindow, strTitle\n"
						+ "set objShell = CreateObject(\"Shell.Application\")\n"
						+ "strTitle = "+ sTitle +"\n"
						+ "for each objWindow in objShell.Windows\n"
						+ "if InStr(objWindow.FullName,\"IEXPLORE.EXE\") then\n"
						+ "wscript.echo objWindow.document.title \n"
						+ "End If\n"
						+ "Next\n"		                   
						+ "Set objShell = Nothing\n";

				fw.write(vbs);
				fw.close();
				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
				BufferedReader input =
						new BufferedReader
						(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = input.readLine()) != null) {
					if(line.toLowerCase().contains(sTitle.toLowerCase())){
						bWindowExist=true;
						break;
					}
					if(line.toLowerCase().equals(sTitle.toLowerCase())){
						bWindowExist=true;
						break;
					}					
				}		        
				input.close();
				if(!bWindowExist){
					current=new DateTime();
					if(current.isAfter(limit)){
						break;
					}		        	
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return bWindowExist;
	}
	
	public static boolean WaitWindowTitleAppear(String sTitle,int iSec) {
		boolean bWindowExist=false;
		DateTime current;
		DateTime now = new DateTime();
		DateTime limit = now.plusSeconds(iSec);
		try {
			File file = File.createTempFile("MyOntheFlyVBS",".vbs");
			file.deleteOnExit();
			while(!bWindowExist){   
				FileWriter fw = new java.io.FileWriter(file);
				String vbs = "dim WshShell,f,fso\n"
						+ "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
						+ "Filename = \"tlist.txt\"\n"
						+ "WshShell.Run \"cmd /c tasklist.exe -v /fi \" & chr(34) & \"imagename eq iexplore.exe\" & chr(34) & \">tlist.txt\", 2, True\n"
						+ "Set fso = CreateObject(\"Scripting.FileSystemObject\")\n"
						+ "Set f = fso.OpenTextFile(FileName, 1, True)\n"
						+ "FileText = f.ReadAll\n"
						+ "wscript.echo FileText\n"              
						+ "f.close\n"
						+ "Set f_objFile = fso.GetFile(FileName)\n"
						+ "f_objFile.Delete\n"
						+ "Set f_objFile = Nothing\n"
						+ "Set WshShell = Nothing\n"
						+ "Set f = Nothing\n"
						+ "Set fso = Nothing\n";

				fw.write(vbs);
				fw.close();
				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
				BufferedReader input =
						new BufferedReader
						(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = input.readLine()) != null) {
					if(line.toLowerCase().contains(sTitle.toLowerCase())){
						bWindowExist=true;
						break;
					}
					if(line.toLowerCase().equals(sTitle.toLowerCase())){
						bWindowExist=true;
						break;
					}					
				}		        
				input.close();
				if(!bWindowExist){
					current=new DateTime();
					if(current.isAfter(limit)){
						break;
					}		        	
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return bWindowExist;
	}
	
	
	public static void ClickButtonByID(String sTitle,int iSec,String SButtonId) {
		boolean bWindowExist=false;
//		DateTime current;
//		DateTime now = new DateTime();
//		DateTime limit = now.plusSeconds(iSec);
		try {
			File file = File.createTempFile("MyOntheFlyVBS",".vbs");
			file.deleteOnExit();
			while(!bWindowExist){   
				FileWriter fw = new java.io.FileWriter(file);
//				String vbs = "dim objShell,oWShell, objWindow, strTitle\n"
//						+ "set objShell = CreateObject(\"Shell.Application\")\n"
//						+ "strTitle = "+ sTitle +"\n"
//						+ "for each objWindow in objShell.Windows\n"
//						+ "if InStr(objWindow.FullName,\"IEXPLORE.EXE\") then\n"
//						+ "wscript.echo objWindow.document.title \n"
//						+ "End If\n"
//						+ "Next\n"		                   
//						+ "Set objShell = Nothing\n";
				
				String vbs ="Dim ie\n"
				+ "Set ie = GetIEApp(\"" + sTitle + "\")\n"
				+ "If TypeName(ie) = \"Nothing\" Then\n"
				+ "Set ie = CreateObject(\"internetexplorer.application\")\n"
				+ "Else\n"				
				+ "ie.Document.getElementById(\""+ SButtonId +"\").Click\n"
				+ "wscript.echo objWindow.document.title \n"
				+ "End If\n"
				+ "Function GetIEApp(ByVal sBrowserTitleKeyWord)\n"
				+ "Dim objShell,colLinks\n"
				+ "Dim objWindows\n"
				+ "Dim objWindow\n"
				+ "Set objShell = CreateObject(\"Shell.Application\")\n"
				+ "Set objWindows = objShell.Windows\n"
				+ "For Each objWindow In objWindows\n"
				+ "If LCase(Right(objWindow.FullName, 12)) = \"iexplore.exe\" Then\n"
				+ "if InStr(objWindow.document.title,sBrowserTitleKeyWord) Then\n"
				+ "Set GetIEApp = objWindow\n"
				+ "end if\n"
				+ "End If\n"
				+ "Next\n"
				+ "Set objWindow = Nothing\n"
				+ "Set objWindows = Nothing\n"
				+ "Set objShell = Nothing\n"
				+ "End Function\n";

				fw.write(vbs);
				fw.close();
//				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
				Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
//				BufferedReader input =
//						new BufferedReader
//						(new InputStreamReader(p.getInputStream()));
//				String line;
//				while ((line = input.readLine()) != null) {
//					if(line.toLowerCase().contains(sTitle.toLowerCase())){
//						bWindowExist=true;
//						break;
//					}
//					if(line.toLowerCase().equals(sTitle.toLowerCase())){
//						bWindowExist=true;
//						break;
//					}					
//				}		        
//				input.close();
//				if(!bWindowExist){
//					current=new DateTime();
//					if(current.isAfter(limit)){
//						break;
//					}		        	
//				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
//		return bWindowExist;
	}
	
	
}
