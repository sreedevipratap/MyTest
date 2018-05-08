package pages;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Report.ExceptionScreenshot;
import Utilities.ControlHandling;
import Utilities.Driverclass;



public class StartReviewPage extends Driverclass {
	
	
	public StartReviewPage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);	
	}
	
	@FindBy(how =How.XPATH, using = "//a[@class='btn btn-primary']")
    public WebElement startNewReview_btn; 	
	
	
	//for dev environment project xpath
	/*@FindBy(how =How.XPATH, using="//form[@action='https://dev.ie.kycnet.com/projects/startReview/']//span[text()= 'Select a project']") 
    public WebElement project_dd;*/
	
	//for CDDUK env project xpath
	@FindBy(how =How.XPATH, using="//span[text()= 'Select a project']") 
    public WebElement project_dd;
	
	
    @FindBy(how =How.XPATH, using="//span[text()='Select a review type']")  
    public WebElement reviewtype_dd;
    @FindBy(how =How.XPATH, using = "//span[text()= 'Select a party']")     
     public WebElement name_dd;
	
	@FindBy(how =How.XPATH, using = "//input[@role='combobox']")
    public WebElement name_fld;
    
	@FindBy(how =How.XPATH, using = "//div[@role='option']")
    public WebElement newparty_optn; 
		
	@FindBy(how =How.XPATH, using = "//button[text()='Add']")
    public WebElement add_btn;
	
	@FindBy(how =How.XPATH, using = "//div[@class='checkbox']")
    public List<WebElement> type_rbtn;
		
	List<String> parameters = null;
	
	/*public void ChooseprojectReviewType(String project,String review,String name,String type) throws InterruptedException, IOException
	{
		ControlHandling.waitForElement(startNewReview_btn,Test);
		ControlHandling.click(startNewReview_btn,Test,"startNewReview_btn");
		ControlHandling.select(project_dd, project,Test);		
		ControlHandling.select(reviewtype_dd, review,Test);		
		ControlHandling.click(name_dd,Test,"name_dd");
		ControlHandling.sendkeys(name_fld, name,Test);
		ControlHandling.click(newparty_optn,Test,"newparty_optn");	
		ChooseType(type);
		ControlHandling.click(add_btn,Test,"add_btn");						
	}*/
	
	
		
	public void ChooseType(String type) throws IOException
	{
		for(WebElement e: type_rbtn)
		{			
			if(e.getText().toString().contains(type))
			{
				ControlHandling.click(e,Test,"choose type");
				break;
			}
		}
	}
	
	
	/*public void ChooseprojectReviewType(String project,String review,String name,String type) throws InterruptedException, IOException
    { 
            ControlHandling.waitForElement(startNewReview_btn,Test); 
            ControlHandling.click(startNewReview_btn,Test,"startNewReview_btn"); 
            ControlHandling.clickandselect(project_dd,project, Test);                
            ControlHandling.containsclickandselect(reviewtype_dd,review, Test);   
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            ControlHandling.click(name_dd,Test,"name_dd"); 
            Actions action=new Actions(driver);             
            action.moveToElement(name_fld).sendKeys(name).build().perform(); 
            Thread.sleep(5000); 
            ControlHandling.click(newparty_optn,Test,"newparty_optn");      
            ChooseType(type); 
            ControlHandling.click(add_btn,Test,"add_btn");                                          
    } */
   
	//Parameterized project, review type,name,party type
	public void startNewReview(String...args) throws IOException, InterruptedException
    {    
        
        
        
        try
        {
            parameters=ControlHandling.getParameters(args);    
            
            if(parameters.size()==5)
            {
                Test=Report.createTest(parameters.get(4));  
            }
            ControlHandling.waitForElement(startNewReview_btn); 
            ControlHandling.click(startNewReview_btn,Test,"startNewReview_btn"); 
            StartReviewModelWindowhandling(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3));
            Report.flush(); 
        }
        
        catch(Exception e)
        {
            Test.log(Status.FAIL,  e.toString());
            ExceptionScreenshot.getScreenshot();
        }
        
    }
    
    public void StartReviewModelWindowhandling(String...args) throws IOException, InterruptedException
    {
    	try
    	{ 
    		parameters=ControlHandling.getParameters(args);    
    		ControlHandling.clickandselect(project_dd,parameters.get(0), Test);                
    		ControlHandling.containsclickandselect(reviewtype_dd,parameters.get(1), Test);   
    		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    		ControlHandling.click(name_dd,Test,"name_dd"); 
    		Actions action=new Actions(driver);             
    		action.moveToElement(name_fld).sendKeys(parameters.get(2)).build().perform(); 
    		Thread.sleep(5000); 
    		ControlHandling.click(newparty_optn,Test,"newparty_optn");      
    		ChooseType(parameters.get(3)); 
    		ControlHandling.click(add_btn,Test,"add_btn");
    		Report.flush(); 
    	}
    	catch(Exception e)
        {
            Test.log(Status.FAIL,  e.toString());
            ExceptionScreenshot.getScreenshot();
        }
    	
    }
    
 
 
}
