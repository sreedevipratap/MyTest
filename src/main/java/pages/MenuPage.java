package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Report.ExceptionScreenshot;
import Utilities.ControlHandling;
import Utilities.Driverclass;

public class MenuPage extends Driverclass {

	
	public MenuPage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);
	}	
	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='Tasks']]")
    public WebElement tasks_menu;
	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='My tasks']]")
    public WebElement mytasks_menu;
	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='Grids']]")
    public WebElement grids_menu;
	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='Drill down']]")
    public WebElement drilldown_menu;
	
	@FindBy(how =How.XPATH, using = "//a[@class='logo']/img")
    public WebElement logo;
	
	@FindBy(how =How.XPATH, using = "//a[@title='My settings']")
    public WebElement settings_btn;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Log off from Passport']")
	public WebElement logout_btn;
	
	@FindBy(how = How.XPATH, using = "//a[text()=' Start new review']")
	public WebElement topbarStartnewReview_btn;
	
	@FindBy(how = How.XPATH, using = "//a[text()][normalize-space()='Set-up']")
	public WebElement setup_menu;
	
	@FindBy(how = How.XPATH, using = "//a[text()=' Fields']")
	public WebElement field_menu;
	
	@FindBy(how = How.XPATH, using = "//a[text()][normalize-space()='Review types']")
	public WebElement reviewType_menu;
	
	
	public void clickonFields() throws IOException
	{
		try
		{
		ControlHandling.click(setup_menu, Test, "setup menu");
		ControlHandling.click(field_menu, Test, "field menu");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		Report.flush();
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public void clickonMyTasks() throws IOException
	{
		try
		{
		ControlHandling.click(tasks_menu, Test, "Task menu");
		ControlHandling.click(mytasks_menu, Test, "My task menu");
		Report.flush();
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public void clickdrilldown() throws IOException
	{
		try
		{
		ControlHandling.click(grids_menu, Test, "Grids menu");
		ControlHandling.click(drilldown_menu, Test, "Drill down");	
		Report.flush();
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public void clickOnLogo() throws IOException
	{
		ControlHandling.click(logo, Test, "logo button");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	  public void logout() throws IOException
	    {
	    	ControlHandling.click(logout_btn,Test,"logout_btn");
	    }
	  
	  public void clickSettings() throws IOException
		{
			ControlHandling.click(settings_btn, Test, "Settings");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}
	  
		
}
