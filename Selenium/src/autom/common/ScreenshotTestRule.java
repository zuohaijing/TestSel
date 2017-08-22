package autom.common;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
public class ScreenshotTestRule extends TestListenerAdapter {

    private static final String SCREENSHOT_FILE_EXTENSION = ".png";
    private static final String SCREENSHOT_FILE_PREFIX = "screenshot";
    private static final String SCREENSHOT_DIR = "C:\\Projects\\trunk\\Selenium\\screenshots\\";
    private static final int MAX_SCREENSHOTS = 20;
    private static int screenshotCount = 0;
    
    @Override
    public void onTestFailure(ITestResult testResult)
    {
        String methodName = testResult.getMethod().getMethodName();
        captureScreenshot(methodName);
    }  

    public void captureScreenshot(String methodName) {
        try {
            if (screenshotCount > MAX_SCREENSHOTS) return;
            String filename = getFileName(screenshotCount++);
            MyPrint.myPrint("ScreenshotTestRule", "Taking screenshot for " + methodName + ", filename: " + filename);
            File screenshot = ((TakesScreenshot) MasterTest.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filename));
        } catch (Exception e) {
            MyPrint.myPrint("ScreenshotTestRule", e.toString());
        }
    }

    private static String getFileName(int index) {
        return SCREENSHOT_DIR+SCREENSHOT_FILE_PREFIX + index + SCREENSHOT_FILE_EXTENSION;
    }
    
    /**
     * Cleans any screenshots from previous tests
     */
    protected synchronized static void cleanScreenshots() {
        if (screenshotCount == 0) {
            for (int i = 0; i < MAX_SCREENSHOTS; i++) {
                String filename = getFileName(i);
                File screenshot = new File(filename);
                StackTraceElement[] traceClassName=Thread.currentThread().getStackTrace();
                boolean bADRun=false;
                for (int iLevel=1; iLevel<traceClassName.length; iLevel++) {
                	StackTraceElement ste = traceClassName[iLevel];
                	bADRun=false;
                	if (ste.getClassName().contains("ADTest")) {
                		bADRun=true;
                		if (screenshot.exists()){
                			screenshotCount++;
                		}
                		break;
                	}
                }
                if(!bADRun){
                	if (screenshot.exists()) screenshot.delete();
                }
            }  
        }
    }
}