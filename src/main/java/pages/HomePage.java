package pages;

import java.io.IOException;
import java.util.List;
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


public class HomePage extends Driverclass {

	
	public HomePage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);
		
	}	

	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='My reviews']]")
    public WebElement myreviews_tab;
	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='Unassigned']]")
    public WebElement unassigned_tab;
	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='Reviews owned by me']]")
    public WebElement ownbyme_tab;
	
	@FindBy(how =How.XPATH, using = "//h2")
    public WebElement header;
	
	@FindBy(how=How.XPATH,using="//table[@id='availableStepsTable']//th")
	public List<WebElement> table_header;
	
	@FindBy(how=How.XPATH,using="//table[@id='availableStepsTable']//th[text()='Party']")
	public List<WebElement> party_header;
	
	@FindBy(how=How.XPATH,using="//table[@id='availableStepsTable']")
	public WebElement unassignedtable;
	
	@FindBy(how=How.XPATH,using="//table[@id='case_owned_by_me']")
	public WebElement ownedbymetable;
	
	@FindBy(how=How.XPATH,using="//table[@id='myReviewsTable']")
	public WebElement reviewstable;
	
	@FindBy(how=How.XPATH,using="//select[@name='availableStepsTable_length']")
	public WebElement unassignedentries_dd;
	
	@FindBy(how=How.XPATH,using="//select[@name='case_owned_by_me_length']")
	public WebElement ownedByMeEntries_dd;
	
	@FindBy(how=How.XPATH,using="//select[@name='myReviewsTable_length']")
	public WebElement myReviewsEntries_dd;
	
	@FindBy(how=How.XPATH,using="//tr/td[6]/a")
	public List<WebElement> unassignedview_btn;
	
	@FindBy(how=How.XPATH,using="//table[@id='case_owned_by_me']//td[7]/a")
	public List<WebElement> reviewsOwnedbyMeView_btn;
	
	@FindBy(how=How.XPATH,using="//td[8]//a")
	public List<WebElement> myReviewContinue_btn;
	
	@FindBy(how=How.XPATH,using="//div[@id='availableStepsTable_filter']//input")
	public WebElement unassignedsearch;
	
	@FindBy(how=How.XPATH,using="//div[@id='myReviewsTable_filter']//input")
	public WebElement myReviewsearch;
	
	public void selectEntries(String entry, WebElement e) throws IOException, InterruptedException
	{
		ControlHandling.select(e, entry, Test);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
	}
	
	public void clickView(String parameter,WebElement table, List<WebElement> actionbtn) throws IOException
	{		
		ControlHandling.click(actionbtn.get(ControlHandling.getTablerow(table, parameter)), Test, "view button");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void searchclickViewOnUnassignedtab(String searchdata) throws IOException, InterruptedException
	{
		try
		{
		ControlHandling.click(unassigned_tab, Test, "unassigned tab");
		ControlHandling.sendkeys(unassignedsearch, searchdata, Test);
		Thread.sleep(3000);
		ControlHandling.click(unassignedview_btn.get(0), Test, "view button");
		Report.flush();
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
  		
	}

	public void searchclickContinueOnReviewsTab(String searchdata) throws IOException, InterruptedException
	{
		try
		{
		ControlHandling.sendkeys(myReviewsearch, searchdata, Test);
		Thread.sleep(3000);
		Thread.sleep(3000);	
		ControlHandling.click(myReviewContinue_btn.get(0), Test, "Continue button");
		Report.flush();
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
  		
	}
	public void AssertsearchclickContinueOnReviewsTab(String searchdata) throws IOException, InterruptedException
	{
		try
		{
		ControlHandling.sendkeys(myReviewsearch, searchdata, Test);
		Thread.sleep(3000);
		Thread.sleep(3000);	
		
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
  		
	}
    public void verifyHomePageHeader() throws IOException
    {
    	try
    	{
    		ControlHandling.Assertion(header.getText().toString(), "My reviews", Test, "Home page");
    	}
    	
    	catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
    }



}