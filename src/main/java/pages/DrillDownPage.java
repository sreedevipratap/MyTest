//Drilldown Page changes
package pages;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class DrillDownPage  extends Driverclass{

	public DrillDownPage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);
		
	}	
	
	
	@FindBy(how =How.XPATH, using = "//a[@title='Show/hide this sidebar']")
    public WebElement sidebar;
	
	@FindBy(how =How.XPATH, using = "//span[@class='tree-toggle first-toggle']/following-sibling::a")
    public WebElement mainorganisation;
	
	@FindBy(how =How.XPATH, using = "//ul[@class='nav']/li/ul/li")
    public List<WebElement> subOrganisation;
	
	@FindBy(how =How.XPATH, using = "//h1[@class='review-title']")
    public WebElement title;
	
	@FindBy(how =How.XPATH, using = "//a[text()='click here to load all data']")
    public WebElement clicktoload;
	
	@FindBy(how =How.XPATH, using = "//input[@type='search']")
    public WebElement search_fld;
	
	@FindBy(how =How.XPATH, using = "//table[@id='project_customer_index']")
    public WebElement drilldown_table;
	
	@FindBy(how =How.XPATH, using = "//table[@id='project_customer_index']//th")
    public List<WebElement> table_hdr;
	
	@FindBy(how =How.XPATH, using = "//td[8]/a")
    public List<WebElement>  review_column;
	
	@FindBy(how =How.XPATH, using = "//td[14]/a")
    public List<WebElement>  user_column;
	
	@FindBy(how =How.XPATH, using = "//td[17]")
    public List<WebElement> status_column;
	
	@FindBy(how =How.XPATH, using = "//td[14]")
    public List<WebElement> assigned_column;
	
	@FindBy(how =How.XPATH, using = "//a[@id='pin']")
    public WebElement sidebar_pin;
	
	@FindBy(how =How.XPATH, using = "//ul[@class='nav']/li/span/b")
    public WebElement mainorg_expand;
	
	@FindBy(how =How.XPATH, using = "//ul[@class='nav']/li/ul/li/span/b")
    public List<WebElement> suborg_expand;
	
	@FindBy(how =How.XPATH, using = "//ul[@class='nav']/li/ul/li/ul/li/span/b")
    public List<WebElement> proj_expand;
	
	@FindBy(how =How.XPATH, using = "//ul[@class='nav']/li/ul/li/ul/li/ul/li//b")
    public List<WebElement> projOfProj_expand;
	
	@FindBy(how =How.XPATH, using = "//div[@class='ColVis']/button")
    public WebElement showCol_btn;
	
	@FindBy(how =How.XPATH, using = "//div[@class='ColVis_catcher']")
    public WebElement hideCol_btn;
	
	@FindBy(how =How.XPATH, using = "//ul[@class='ColVis_collection']/li[1]/label/input")
    public WebElement orgcol_btn;
	
	@FindBy(how =How.XPATH, using = "//ul[@class='ColVis_collection']/li[2]/label/input")
    public WebElement projcol_btn;
		
	@FindBy(how =How.XPATH, using = "//th[text()='Organisation']")
    public WebElement org_hdr;
	
	@FindBy(how =How.XPATH, using = "//th[text()='Project']")
    public WebElement proj_hdr;
	
	@FindBy(how =How.XPATH, using = "//tr/td[3]")
    public WebElement org_value;
	
	@FindBy(how =How.XPATH, using = "//a[text()= ' Testing Department']/parent::li//ul/li[@class='sublist ']/a")
    public List<WebElement> chooseProject;
	
	@FindBy(how =How.XPATH, using = "//div[@id='actionBtnBoxSource']//a[text()=' Start new review']")
    public WebElement startNewReview_btn;
	
	@FindBy(how =How.XPATH, using = "//a[text()= 'Parties']")
    public WebElement parties_btn;
	 List<String> parameters = null;
	
	public void mouseOverSidebar() throws IOException
	{
		try
		{
		ControlHandling.mouseOver(sidebar, "side bar", Test);	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Report.flush();
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public void clickSearch(String...args) throws IOException
	{
		try
		{
		parameters=ControlHandling.getParameters(args);	
		ControlHandling.sendkeys(search_fld, parameters.get(0), Test);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Report.flush();
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public void clickMainOrganisation() throws IOException
	{
		try
		{
		ControlHandling.click(mainorganisation, Test, "main organisation");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Report.flush();
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public void clicksidebarpin() throws IOException
	{
		ControlHandling.click(sidebar_pin, Test, "side bar pin");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public  void SubOrganisationIsPresent(List<WebElement> el) throws IOException
	
	{
		
		for(WebElement e: el)
		{
			if(ControlHandling.isdisplayed(e))
			{
				ControlHandling.click(mainorg_expand, Test, "mainorganisation is clicked");
				break;
			}
			else
			{
				break;
			}
		}
	 		
	}
    
	public void chooseOrganisation(String orgname) throws IOException
	{
		for(WebElement e: subOrganisation)
		{
			ControlHandling.handlingStaleElement(e.findElement(By.tagName("a")));
			if(e.findElement(By.tagName("a")).getText().toString().equals(orgname))
			{
				
				ControlHandling.click(e.findElement(By.tagName("a")), Test, "sub organisation");
				break;
			}
		}
	}
	
	public void chooseProject(String projname) throws IOException
	{
		
		for (WebElement e: chooseProject)
		{
			ControlHandling.scrolltoElement(e);
			
			if(e.getText().toString().endsWith(projname))
			{
				if(ControlHandling.handlingStaleElement(e))
				{
					ControlHandling.click(e, Test, "Project");
					break;
				}
				
			}
		}
	}
	
	public void listOfClick(List<WebElement> el) throws IOException, InterruptedException
	{
		
		for (WebElement e: el)
		{
			
			//ControlHandling.scrolltoElement(e, Test);
			ControlHandling.click(e, Test, "Organisation expand");
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}
	}
}
