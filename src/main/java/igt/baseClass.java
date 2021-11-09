package igt;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import report.reporterClass;

public class baseClass 

{
	public  static  WebDriver driver;
	public static ExtentTest test;
	public  static ExtentReports report;
	@BeforeTest
	public void setExtent()
    {
report = new ExtentReports(System.getProperty("user.dir")+"/html/testReport.html",true);
test = report.startTest("ExtentDemo");
}
public static String getScreenshot(WebDriver driver,String Screenshot) throws IOException 
    {
String dateName=new SimpleDateFormat("yyyMMddmmss").format(new Date());
TakesScreenshot tc=(TakesScreenshot)driver;
File source=tc.getScreenshotAs(OutputType.FILE);
String destination=System.getProperty("user.dri")+"/FailedTestsScreenshot/"+Screenshot+dateName+".png";
File finalDestination=new File(destination);
FileUtils.copyFile(source,finalDestination);
return destination;
}

@BeforeMethod

public void setup()
{
System.setProperty("webdriver.chrome.driver","D:\\Software\\chrome Jar\\chromedriver_win32\\chromedriver.exe");
driver = new ChromeDriver();
driver.get("https://www.google.co.in");
driver.manage().window().maximize();
driver.manage().deleteAllCookies();
//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
}
@AfterMethod
public void tearDown(ITestResult result) throws IOException
{
	 if (result.getStatus()==ITestResult.FAILURE)
	 {
		 test.log(LogStatus.FAIL,"TestCseFail"+result.getName());//add name in extent reporter
		 test.log(LogStatus.FAIL,"TestCseFail"+result.getThrowable());//to add error/exception toextent report
		 String screenshotPath=reporterClass.getScreenshot(driver, result.getName());
		 test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));// to add screenshot in extentreporter
	 }
	 else if(result.getStatus()==ITestResult.SKIP)
	 {
		 test.log(LogStatus.SKIP,"TestCaseSkip"+result.getName());
	 }
	 else if(result.getStatus()==ITestResult.SUCCESS)
	 {
		 test.log(LogStatus.PASS,"TestCasePASS"+result.getName());
	 }
	 report.endTest(test); //ending test
	 driver.quit();

}
@AfterTest
public void endReport()
          {
	report.flush();
	report.close();
	}






}


