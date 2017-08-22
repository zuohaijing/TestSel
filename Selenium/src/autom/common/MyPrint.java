package autom.common;

import org.testng.Reporter;

public class MyPrint {

    public static void myPrint (String test,String logString){
    	System.out.println(test + ": " + logString);
    	Reporter.log(test + ": " + logString);
    }
}
