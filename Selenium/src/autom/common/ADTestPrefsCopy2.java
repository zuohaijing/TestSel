package autom.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ADTestPrefsCopy2 extends Properties {

    private static final long serialVersionUID = -3774252132735187891L;
    
    private static final ADTestPrefsCopy2 INSTANCE = new ADTestPrefsCopy2();
    private static final String LOCAL_PREFS_FILE_NAME = "localtestprefs";
    private static final String PREFS_FILE_NAME = "testprefs";
    private static final String XML_SUFFIX  = ".xml";

    private ADTestPrefsCopy2() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
        load();
    }

    public static ADTestPrefsCopy2 getPrefs() {
        return INSTANCE;
    }

    public void load()
    {
 
        String filename = PREFS_FILE_NAME + XML_SUFFIX;
        try {
            loadFromXML(new FileInputStream(filename));           
        } catch (FileNotFoundException fnfe) {
//            MyPrint.myPrint("TestPrefs", "Tried to load \"" + filename + "\", but it disappeared");
        } catch (IOException ioe) {
//            MyPrint.myPrint("TestPrefs", "Tried to load \"" + filename + "\", but encountered an I/O error");
        } catch (Exception e) {
//            MyPrint.myPrint("TestPrefs", "Tried to load \"" + filename + "\", but failed: " + e.getMessage());
        }
        
        filename = LOCAL_PREFS_FILE_NAME + XML_SUFFIX;
        try {
            File localTestPrefs = new File(filename);
            if (localTestPrefs.exists()) {
                loadFromXML(new FileInputStream(filename));
//                MyPrint.myPrint("TestPrefs.load", "Loaded local test prefs");
            }
            
        } catch (FileNotFoundException fnfe) {
//            MyPrint.myPrint("TestPrefs", "Tried to load \"" + filename + "\", but it disappeared");
        } catch (IOException ioe) {
//            MyPrint.myPrint("TestPrefs", "Tried to load \"" + filename + "\", but encountered an I/O error");
        } catch (Exception e) {
//            MyPrint.myPrint("TestPrefs", "Tried to load \"" + filename + "\", but failed: " + e.getMessage());
        }
        
    }
    
    public static String getServerURL() {	
    	if(getIsAuthModeTest()){
    		return getValue("common.activedirectory.url", "http://localhost");
    	} else {
    		return getValue("common.server.url", "http://localhost");
    	}
    }
    
    public static String getServerUserName() {
    	return getValue("common.server.user", "user");
    }

    public static String getServerPassword() {
    	return getValue("common.server.password", "password");
    }
    
	public static int getSSHConnectTimeoutMilliSeconds() {
		return getValue("common.ssh.connect.timeout.milliseconds", 10 * 1000);
	}
	
    public static String getSeleniumClientType() {
    	return getValue("selenium.client.type", "standalone");
    }
    
    public static String getSeleniumGridClient() {
    	return getValue("selenium.grid.client", "http://localhost:4444/wd/hub");
    }
    
    public static int getGlobalTimeoutSeconds() {
        return getValue("common.global.timeout.seconds", 60);
    }

    public static int getGlobalLongTimeoutSeconds() {
        return getValue("common.global.longtimeout.seconds", 600);
    }
    
    public static int getNVRElementID() {
        return getValue("config.nvr.elementid", 228);
    }
    
    public static float getNVRRediscoveryBaselineSeconds() {
    	return getValue("report.nvr.rediscovery.baseline.seconds", 40f);
    }
    
    public static int getNVRRediscoveryTimeoutSeconds() {
    	return getValue("report.nvr.rediscovery.timeout.seconds", 120);
    }
    
    public static int getNVRRediscoveryOverPercentChangeForError() {
    	return getValue("report.nvr.rediscovery.percent.over", 20);
    }
    
    public static int getNVRRediscoveryUnderPercentChangeForError() {
    	return getValue("report.nvr.rediscovery.percent.under", 20);
    }
    
    public static String getIEVersion() {
    	return getValue("browser.ie.version", "10");
    }
    
    public static String getIEDriver() {
    	return getValue("browser.ie.driverpath", "bin\\IEDriverServer.exe");
    }
    
    public static int getStartupTimeOverPercentChangeForError() {
    	return getValue("report.startuptime.percent.over", 20);
    }
    
    public static int getStartupTimeUnderPercentChangeForError() {
    	return getValue("report.startuptime.percent.under", 20);
    }
    
    public static float getStartupTimeBaselineSeconds() {
    	return getValue("report.startuptime.baseline.seconds", 75f);
    }

    public static float getProxyRediscoveryTimeBaselineSeconds() {
        return getValue("report.proxy.rediscovery.baseline.seconds", 750f);
    }
    
    public static int getProxyRediscoveryTimeOverPercentChangeForError() {
        return getValue("report.proxy.rediscovery.percent.over", 30);
    }
    
    public static int getProxyRediscoveryTimeUnderPercentChangeForError() {
        return getValue("report.proxy.rediscovery.percent.under", 20);
    }

    public static String getRemoteProxyIpAddress() {
       
        return getValue("remote.proxy.ip.address", "10.11.71.158");
    }

    public static int getMaxPerfFileLinesAmount() {
        return getValue("max.performance.file.line.amount", 10);
    }
    public static int getMaxPerfFileLinesAmountCurrentRelease() {
        return getValue("max.performance.file.line.amount.current.release", 100);
    }
    
    public static String getMapDirectoryFolder() {
        return getValue("map.directory.folder", "Maps");
    }
    
    public static String getMapOverlayAssetName() {
        return getValue("map.overlay.asset.name", "MapOverlayAsset");
    }
    
    public static String getMapOverlayAssetType() {
        return getValue("map.overlay.asset.type", "MapOverlayType");
    }

    public static boolean getIsAuthModeTest() {
    	if(getValue("common.global.authmodeturnon", "false").equals("false")){
    		return false;
    	} else {
    		return true;
    	}
    }
    
    public static String getValue(String key, String defaultValue)
    {
        String s = getPrefs().getProperty(key);
        if (s == null) {
            s = defaultValue; 
        } 
        return s;
    }
    
    public static int getValue(String key, int defaultValue)
    {
        String s = getPrefs().getProperty(key);
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public static float getValue(String key, float defaultValue)
    {
        String s = getPrefs().getProperty(key);
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    public static String getFileName() {
    	return PREFS_FILE_NAME + XML_SUFFIX;
    }

}
