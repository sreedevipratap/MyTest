package Report;
//Set 1 Commit
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utilities.Driverclass;

public class ExceptionScreenshot extends Driverclass {
	
	public ExceptionScreenshot(WebDriver driver,ExtentReports Report,ExtentTest Test)
	{
		super(driver,Report,Test);
		
	}
	
	public static void getScreenshot() throws IOException
	{
		
		/*String path=Screenshot.takeScreenshot(driver, "screen1");
		String imagePath = Test.addScreenCaptureFromPath(path).toString();
		Test.log(Status.FAIL, imagePath);
		Test.addScreenCaptureFromPath("screenshot.jpeg");*/		
		String path=Screenshot.takeScreenshot(driver, "screen1");
		Test.log(Status.FAIL,"Failed",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		Report.flush();
	}

}
