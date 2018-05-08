package pages;

import java.io.IOException;
import java.util.List;
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

public class LoginPage extends Driverclass {
	

	public LoginPage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);
		
	}
	
	@FindBy(how =How.XPATH, using = "//input[@name ='user_name']")
    public WebElement username_Fld; 
	
    @FindBy(how =How.XPATH, using = "//input[@name ='user_pass']")
    public WebElement password_Fld; 
    
    @FindBy(how = How.XPATH, using = "//button[@id ='loginButton']")
    public WebElement login_Btn; 
    
    @FindBy(how = How.XPATH, using = "//a[@title='Main menu']")
    public WebElement menu_btn;
      
    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']")
    public WebElement alert_msg;
    
    @FindBy(how = How.XPATH, using = " //button[@class='close']")
    public WebElement alertClose_btn;
    
    @FindBy(how = How.XPATH, using = "//div[@id='mineReviews']/h2")
    public WebElement homePage_header;
 
    List<String> parameters = null;
    
    
  //Parameterized username and password
    public void loginUser(String...args) throws IOException
    {
    	try
		 {   		  			
    		parameters=ControlHandling.getParameters(args);	
		    username_Fld.clear();
		    ControlHandling.sendkeys(username_Fld,parameters.get(0),Test);  	
		    password_Fld.clear();
		    ControlHandling.sendkeys(password_Fld,parameters.get(1),Test);
		    ControlHandling.click(login_Btn,Test,"login_Btn"); 
		    if (ControlHandling.isAlertPresents())
       		{
		    	ControlHandling.handlingAlert("Assert",Test);
       		}
            Report.flush();
		 }
   	
    	catch(Exception e)
		{
			Test.log(Status.FAIL,  e.toString());
			ExceptionScreenshot.getScreenshot();
		}
   
    	
    }
     
    	public boolean isInValidCredentials() {
		try {
			
			alert_msg.isDisplayed();
		return true;
		}// try
		catch (Exception e) {
		return false;
		}// catch
		}
  
  
	
}
