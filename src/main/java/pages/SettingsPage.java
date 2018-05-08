package pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.xml.xpath.XPathExpressionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utilities.ControlHandling;
import Utilities.Driverclass;

public class SettingsPage extends Driverclass {
	
	public SettingsPage(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		super(driver,Report,Test);
		
	}
	
	@FindBy(how =How.XPATH, using = "//h1")
    public WebElement header;
	
	@FindBy(how=How.XPATH, using="//form//label")
	public List<WebElement> label;
	
	@FindBy(how =How.ID, using = "username")
    public WebElement username_fld;
	
	@FindBy(how =How.ID, using = "password")
    public WebElement password_fld;
	
	@FindBy(how =How.ID, using = "password2")
    public WebElement password2_fld;
	
	@FindBy(how =How.ID, using = "theme")
    public WebElement theme_dd;
	
	@FindBy(how =How.ID, using = "whitespace_type")
    public WebElement display_dd;
	
	@FindBy(how =How.ID, using = "old_password")
    public WebElement currentpwd_fld;
		
	@FindBy(how =How.ID, using = "firstname")
    public WebElement firstname_fld;
	
	@FindBy(how =How.ID, using = "lastname")
    public WebElement lastname_fld;
	
	@FindBy(how =How.ID, using = "email")
    public WebElement email_fld;
	
	@FindBy(how =How.XPATH, using = "//button[text()='Save your profile']")
    public WebElement save_btn;
	
	@FindBy(how =How.XPATH, using = "//a[contains(text(),'Cancel')]")
    public WebElement cancel_btn;
	
	@FindBy(how =How.XPATH, using = "//div[@class='alert alert-success']")
	public WebElement success_msg;
	
	@FindBy(how =How.XPATH, using = "//div[@class='alert alert-danger']")
	public WebElement failure_msg;
	
	@FindBy(how =How.XPATH, using = "//h2")
	public WebElement Myreview_hdr;
	
	
	
	public void editSettingFields(String firstname,String lastname,String email) throws IOException
	{
		firstname_fld.clear();
		ControlHandling.sendkeys(firstname_fld, firstname, Test);
		lastname_fld.clear();
		ControlHandling.sendkeys(lastname_fld, lastname, Test);		
		//ControlHandling.clear(email_fld);
		//ControlHandling.sendkeys(email_fld, email, Test);	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ControlHandling.select(display_dd, "Comfortable", Test);
		ControlHandling.falseAssertion(username_fld.isEnabled(), Test, "Username field is non editable");
	}
	
	public void enterCurrentPwd(String pwd) throws IOException
	{
		ControlHandling.sendkeys(currentpwd_fld,pwd, Test);		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void changePwd(String newpwd,String curpwd) throws IOException
	{
		ControlHandling.sendkeys(password_fld, newpwd, Test);
		ControlHandling.sendkeys(password2_fld, newpwd, Test);
		enterCurrentPwd(curpwd);
	}
	public void clickOnSave() throws IOException
	{
		ControlHandling.click(save_btn, Test, "save profile");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	public void verifySuccessMsg(String msg) throws IOException
	{
		ControlHandling.trueAssertion(success_msg.getText().toString().contains(msg), Test, "alert message");
	}
	
	public void verifyFailureMsg(String msg) throws IOException, XPathExpressionException
	{
		
		
		ControlHandling.trueAssertion(failure_msg.getText().toString().trim().contains(msg), Test, failure_msg.getText().toString().trim());
	}
	public void clickCancel() throws IOException
	{
		ControlHandling.click(cancel_btn, Test, "Cancel");
		ControlHandling.Assertion(Myreview_hdr.getText().toString(), "My reviews", Test, "My review page is asserted");
	}
}
