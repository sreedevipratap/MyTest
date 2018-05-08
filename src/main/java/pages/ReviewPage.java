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
import excelRead.PropertyFileReader;

public class ReviewPage extends Driverclass {

	
	public ReviewPage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);
		
	}

	@FindBy(how =How.XPATH, using = "//h1")
    public WebElement header;
	
	@FindBy(how =How.XPATH, using = "//h1/i[@class='glyphicon glyphicon-home']")
    public WebElement entity_icon;
	
	@FindBy(how =How.XPATH, using = "//h1/i[@class='glyphicon glyphicon-user']")
    public WebElement person_icon;
	
	@FindBy(how =How.XPATH, using = "//a[text()='Take over']")
    public WebElement takeOver;
	
	@FindBy(how =How.XPATH, using = "//a[@data-original-title='You are currently in view mode. Click here to start working on this review.']")
    public WebElement Start_continuereview_btn;
	
	@FindBy(how =How.XPATH, using = "//button[text()='OK']")
    public WebElement Confirm_model;
	
	@FindBy(how =How.XPATH, using = "//td[text()[normalize-space()='Client/entity name *']]//following-sibling::td//a")
	public WebElement cliententity_fld;
	
	@FindBy(how =How.XPATH, using = "//span[@id='timer']")
	public WebElement timer;
	
	@FindBy(how =How.XPATH, using = "//a[@data-original-title='Click here to stop working on this review.']")
	public WebElement pause_btn;
	
	@FindBy(how =How.XPATH, using = "//a[text()='Save all']")
	public WebElement saveAll_btn;
	
	@FindBy(how =How.XPATH, using = "//a[text()[normalize-space()='Calculate risk']]")
	public WebElement calculateRisk_btn;
	
	//@FindBy(how =How.XPATH, using = "//div[@class='alert alert-danger')]")
	@FindBy(how =How.XPATH, using = "//div[starts-with(@class,'alert alert-danger')]//li")
	public WebElement prd_alert;
	
	@FindBy(how =How.XPATH, using = "//div[@class='alert alert-success']")
	public WebElement save_success;
	
	@FindBy(how =How.XPATH, using = "//div[@id='automatedRiskAssessmentResults']//table//td[1]")
	public List<WebElement> riskAssementtype;
	
	@FindBy(how =How.XPATH, using = "//div[@id='automatedRiskAssessmentResults']//table//td[2]")
	public List<WebElement> riskAssementName;
	
	@FindBy(how =How.XPATH, using = "//div[@id='automatedRiskAssessmentResults']//table//td[3]")
	public List<WebElement> riskAssementValue;
	
	@FindBy(how =How.XPATH, using = "//div[@id='automatedRiskAssessmentResults']//table//td[4]")
	public List<WebElement> resultRisk;
	
	@FindBy(how =How.XPATH, using = "//div[@id='automatedRiskAssessmentResults']//table")
	public WebElement resultTable;
	
	@FindBy(how =How.XPATH, using = "//a[text()=' Add']")
	public WebElement documentUploadAdd_btn;
	
	@FindBy(how =How.XPATH, using = "//a[@id='uploadbtn']")
	public WebElement uploadNewDocument_btn;
	
	@FindBy(how =How.XPATH, using = "//a[normalize-space(text())='Drop or select a file...']")
	public WebElement dropFile_btn;

	@FindBy(how=How.NAME, using = "submitNewUpload")
	public WebElement newUpload_btn;
	
	@FindBy(how = How.CLASS_NAME, using= "alert alert-success")
	public WebElement uploadSuccess_msg;
	
	@FindBy(how =How.XPATH, using = "//button[@class='btn btn-primary update-check-btn']")
	public WebElement applyCheck_btn;
	
	@FindBy(how =How.XPATH, using = "//td[text()='Risk level:']/following-sibling::td//a[@href='#']")
	public List<WebElement> uploadRisk_btn;
	
	
	List<String> parameters = null;
	String parentwindow=null;
	String Childwindow=null;
	public boolean verifyHeader(String header,String project,String review,String name,String type)
	{
		boolean result=false;
		if(header.contains(project)&&header.contains(review)&&header.contains(name))
		{
			if(type.equals("Entity"))
			{
				result=entity_icon.isDisplayed();				
			}			
			else
			{				
				result=person_icon.isDisplayed();
			}		
		}		
		return result;		
	}
	
	public void getReviewID(String key) throws IOException
	{		
		List<String> text=ControlHandling.splitTextWithSpace(header.getText().toString(),"\\s+");	
		PropertyFileReader.updatePropertyFile(key,text.get(1).toString());
		
	}
	  public void clickTakeOver() throws IOException
	   {
		  try
		  {
		   ControlHandling.click(takeOver, Test, "TakeOver");
		   ControlHandling.click(Confirm_model, Test, "click OK");
		   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		   Report.flush();
		  }
		  
		  catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}
		   
	   }
	  
	  public void clickStartReview_btn() throws InterruptedException, IOException
		{
		  try
		  {
			ControlHandling.click(Start_continuereview_btn,Test,"StartReview_btn");
			Thread.sleep(1000);
			if(ControlHandling.isAlertPresents())
			{
				ControlHandling.trueAssertion(ControlHandling.isAlertPresents(), Test, "Alert message is displayed");
				ControlHandling.handlingAlert("OK",Test);
			}
			Report.flush();
		  }
		  
		  catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}

		}
	  
	  public void clickCalculateRisk() throws IOException, InterruptedException
	  	{
		  try
		  {
			  ControlHandling.click(calculateRisk_btn, Test, "Calculate risk");
			  ControlHandling.handlingAlert("OK",Test);
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  Thread.sleep(1000);
			  Report.flush();
		  }
		  
		  catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}
		}
	 
	//Parameterized PRD alert message
	  public void validatePRDMessage(String...args) throws IOException
	  {
		  try
		  {
			 // System.out.println(prd_alert.getText().toString());
			 // String msg=prd_alert.getText().toString().substring(prd_alert.getText().toString().indexOf("Not all primary risk drivers are entered. You can't start the calculation until all primary risk drivers are filled."));
			// System.out.println(msg);
			//  ControlHandling.Assertion(msg, args[0], Test, "PRD alert message");
			  ControlHandling.Assertion(prd_alert.getText().toString(),  args[0], Test, "PRD alert message");
			  Report.flush();
		  }
		  catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}
	  }
	
	//Parameterized save success message 
	 public void saveSuccessAssert(String...args) throws IOException
	 {
		 try
		 {
			 String msg=save_success.getText().toString().substring(save_success.getText().toString().indexOf(args[0]));
			 ControlHandling.Assertion(msg,args[0], Test, args[0]);
			 Report.flush();
		 }
		 catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}
	 }
	 
	 //parameterized as fieldname,type,value,risk,color
	 public void automatedriskResultAssert(String...args) throws IOException
	 {
		 try
		 {
			 int j=0;
			
			 parameters=ControlHandling.getParameters(args);	
			 j=ControlHandling.getTablerow(resultTable,parameters.get(0)); 
			 ControlHandling.Assertion(riskAssementName.get(j).getText().toString(), parameters.get(0), Test, parameters.get(0)+" is present in the result table");
			 ControlHandling.Assertion(riskAssementtype.get(j).getText().toString(), parameters.get(1), Test, parameters.get(0)+"-"+ parameters.get(1));
			 ControlHandling.Assertion(riskAssementValue.get(j).getText().toString(), parameters.get(2), Test, parameters.get(0)+"-"+ parameters.get(2));
			 ControlHandling.Assertion(resultRisk.get(j).getText().toString(), parameters.get(3), Test, parameters.get(0)+"-"+ parameters.get(3));
			 ControlHandling.colorAssert(resultRisk.get(j).findElement(By.tagName("span")), parameters.get(4), Test);
			 Report.flush();
		 }
		 
		 catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}
	 }
	 

	 //Parameterized as XPAth of element
	 public void documentUploadWithoutRisk(String...args) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(args);	
			 documentUploadCommonMethod(parameters.get(0));
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  ControlHandling.click(applyCheck_btn, Test, "Apply check");
			  ControlHandling.switchToParent(parentwindow,Test);	
		 }
		 
		 catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}
	 }

	 //Parameterized as XPAth of element and type of risk
	 public void documentUploadWithRisk(String...args) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(args);
			 documentUploadCommonMethod(parameters.get(0));
			 Thread.sleep(1000);
			 ControlHandling.searchandselect(uploadRisk_btn, parameters.get(1), Test);
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 ControlHandling.click(applyCheck_btn, Test, "Apply check");
			 ControlHandling.switchToParent(parentwindow,Test);
		 }
		 catch(Exception e)
			{
				Test.log(Status.FAIL,  e.toString());
				ExceptionScreenshot.getScreenshot();
			}
	 }
	 
	 public void documentUploadCommonMethod(String Xpath) throws IOException, InterruptedException
	 {
		 ControlHandling.doubleclick(driver.findElement(By.xpath(Xpath)), Test, Xpath);			
		 parentwindow= Driverclass.driver.getWindowHandle();
		 ControlHandling.switchToChild(parentwindow,Test);
		 ControlHandling.click(documentUploadAdd_btn, Test, "Document upload add");
		 Childwindow=Driverclass.driver.getWindowHandle();
		 ControlHandling.switchToChild(Childwindow,Test);		
		 ControlHandling.click(uploadNewDocument_btn, Test, "upload new document");
		 ControlHandling.documentupload(dropFile_btn, Test, "C:\\AutoITScripts\\upload1.exe");
		 ControlHandling.click(newUpload_btn, Test, "new upload");
		 ControlHandling.switchToParent(Childwindow,Test);
		 Thread.sleep(1000);
	 }
	 
	
}