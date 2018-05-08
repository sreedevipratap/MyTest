package testsuite;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Report.ExceptionScreenshot;
import Utilities.ControlHandling;
import Utilities.Driverclass;
import pages.FieldPage;
import pages.MenuPage;

public class DataVali  extends Driverclass
{
	
	public DataVali(WebDriver driver, ExtentReports Report, ExtentTest Test) 
	{
		super(driver,Report,Test);
	}

	MenuPage menuPage=new MenuPage(driver,Report,Test);
	FieldPage fieldPage=new FieldPage(driver,Report,Test);
	
	List<String> parameters = null;
	String parentwindow;
	 //Parameterized current password,first name,last name, email
	 
	public void editField(String parameter) throws IOException
	 {		
		 try
		 {
			parameters=ControlHandling.getParameters(parameter);
			
			menuPage.clickonFields();
			//fieldPage.addEmptyField();
			//fieldPage.editFieldDetails(parameters.get(0),parameters.get(1),parameters.get(2));
			Thread.sleep(1000);
			fieldPage.deleteField(parameters.get(0));
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	 }

	 public void addField(String parameter) throws IOException
	 {		
		 try
		 {
			parameters=ControlHandling.getParameters(parameter);
			
			menuPage.clickonFields();
			fieldPage.addFieldDetails(parameters.get(0),parameters.get(1),parameters.get(2));
			
			fieldPage.deleteField(parameters.get(0));
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	 }
}