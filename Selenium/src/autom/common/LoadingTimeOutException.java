package autom.common;

import org.testng.Reporter;

@SuppressWarnings("serial")
public class LoadingTimeOutException extends Exception {
    public LoadingTimeOutException (String logString){
    	super(logString);
    	System.out.println(": " + logString);
    	Reporter.log(": " + logString);
    	
    }
}
