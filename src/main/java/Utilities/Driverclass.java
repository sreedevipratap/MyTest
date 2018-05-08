package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Driverclass {
	public static WebDriver driver;
	protected static ExtentReports Report;
	protected static ExtentTest Test;
	
	public Driverclass(WebDriver driver, ExtentReports Report, ExtentTest Test)
	{
		Driverclass.driver=driver;
		Driverclass.Report=Report;
		Driverclass.Test=Test;
		PageFactory.initElements(driver,this);
	}
		
	

}