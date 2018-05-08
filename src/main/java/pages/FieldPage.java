package pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
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
import Utilities.TableHandling;

public class FieldPage extends Driverclass 
{
	

	public FieldPage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);
	}
	
	@FindBy(how =How.XPATH, using = "//a[@id='addAttrButton']")
    public WebElement addField_btn;
	
	@FindBy(how =How.XPATH, using = "//button[@id='submitRisk']")
    public WebElement submit_btn;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[3]/a")
    public WebElement row1Fname_lnk;

	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[3]//input[@class='form-control input-sm']")
    public WebElement row1Fname_txt;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[3]//button[@class='btn btn-primary btn-sm editable-submit']")
    public WebElement row1Fname_btn;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[4]/a")
    public WebElement row1Ftt_a;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[4]//input[@class='form-control input-sm']")
    public WebElement row1Ftt_txt;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[4]//button[@class='btn btn-primary btn-sm editable-submit']")
    public WebElement row1Ftt_btn;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[6]/div/a")
    public WebElement row1FVtype_a;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[6]/div//select")
    public WebElement row1FVtype_txt;
	
	@FindBy(how =How.XPATH, using = "//table[@id='groups']//tr[1]/td[6]//button[@class='btn btn-primary btn-sm editable-submit']")
    public WebElement row1FVtype_btn;
	
	@FindBy (how = How.XPATH, using = "//div[@class='col-md-12']//tr[2]/td[2]/input")
	public WebElement fieldNm_txt;
	
	@FindBy (how = How.XPATH, using ="//div[@class='col-md-12']//tr[3]/td[2]/textarea")
	public WebElement fieldTT_txt;
	
	@FindBy (how = How.XPATH, using ="//div[@class='col-md-12']//tr[5]/td[2]/select")
	public WebElement fieldVT_dd;
	
	@FindBy (how = How.XPATH, using ="//div[@class='col-md-12']//tr[8]/td[2]/input[@name='is_public']")
	public WebElement public_cb;
	
	@FindBy (how = How.XPATH, using = "//table[@id='groups']//td[1]")
	public List<WebElement> fieldColumn_cb;
	
	@FindBy (how = How.XPATH, using = "//table[@id='groups']")
	public WebElement field_tbl;
	
	@FindBy (how = How.XPATH, using = "//div/a[@class='btn btn-default dropdown-toggle']")
	public WebElement button_tgl;
	
	@FindBy (how = How.XPATH, using = "//ul/li/a[@id='massDelete']")
	public WebElement obsolete_btn;
	
	@FindBy (how = How.XPATH, using = "//table[@id='groups']//td[3]/a")
	public List<WebElement> fieldnm_a;
	
	@FindBy (how = How.XPATH, using = "//table[@id='groups']//td[4]/a")
	public  List<WebElement>fieldtt_a;
	
	@FindBy (how = How.XPATH, using ="//table[@id='groups']//td[6]")
	public List<WebElement> valueType_clm;
	
	//Create empty field
	public void addEmptyField() throws IOException
    {
    	try
		 {  
    		addField_btn.click();
    		submit_btn.click();
    		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            Report.flush();
		 }
   	
    	catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
    }
	
    List<String> parameters = null;
    
	//Edit row 1 field with Parameterized
    public void editFieldDetails(String...args) throws IOException
    {
    	try
		 {
    		parameters=ControlHandling.getParameters(args);
    		
    		ControlHandling.click(row1Fname_lnk,Test,"row1Fname_lnk");
    		ControlHandling.sendkeys(row1Fname_txt,parameters.get(0),Test);
    		ControlHandling.click(row1Fname_btn,Test,"row1Fname_btn");
    		ControlHandling.click(row1Ftt_a,Test,"row1Ftt_a");
    		ControlHandling.sendkeys(row1Ftt_txt,parameters.get(1),Test);
    		ControlHandling.click(row1Ftt_btn,Test,"row1Ftt_btn");
    		ControlHandling.click(row1FVtype_a,Test,"row1FVtype_a");
    		ControlHandling.sendkeys(row1FVtype_txt,parameters.get(2),Test);
    		ControlHandling.click(row1FVtype_btn,Test,"row1FVtype_btn");
    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    		
		    Report.flush();
		 }
    	
    	catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
    }
    
    public void addFieldDetails(String...args) throws IOException
    {
    	try
		 {
    		parameters=ControlHandling.getParameters(args);
    		
    		ControlHandling.click(addField_btn,Test,"addField_btn");
    		ControlHandling.sendkeys(fieldNm_txt,parameters.get(0),Test);
    		ControlHandling.sendkeys(fieldTT_txt, parameters.get(1), Test);
    		ControlHandling.sendkeys(fieldVT_dd,parameters.get(2),Test);
    		ControlHandling.click(public_cb, Test, "public_cb");
    		ControlHandling.click(submit_btn, Test, "submit_btn");
    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    		
		    Report.flush();
		 }
    	
    	catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
    }
    
    public void deleteField(String...args) throws IOException
    {
    	try
    	{
    		parameters=ControlHandling.getParameters(args);
    		System.out.println(fieldColumn_cb.size());
    		int row = TableHandling.getTableRowUsingColumnHavingSubTable(field_tbl, "Name", parameters.get(0));
    		System.out.println(row);
    		ControlHandling.scrolltoElement(fieldColumn_cb.get(row));
    		ControlHandling.click(fieldColumn_cb.get(row),Test,"fieldColumn_cb");
    		ControlHandling.click(button_tgl,Test,"button_tgl");
    		ControlHandling.click(obsolete_btn, Test, "obsolete_btn");
		    ControlHandling.handlingAlert("Assert",Test);
    		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    		
    		Report.flush();
    	}
    	
    	catch(Exception e)
    	{
    		Test.log(Status.FAIL, e.toString());
    		ExceptionScreenshot.getScreenshot();
    	}
    }
}