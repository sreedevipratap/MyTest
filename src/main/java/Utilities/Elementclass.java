package Utilities;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utilities.Driverclass;

public class Elementclass extends Driverclass {
	
	static List <WebElement> el;
	public Elementclass(WebDriver driver, ExtentReports Report, ExtentTest Test) {
		super(driver, Report, Test);
		// TODO Auto-generated constructor stub
	}

	public static WebElement id(String value)
	{		
		WebElement test1=driver.findElement(By.id(value));
		return test1;		
	}
	
	// Java script executor added
	public static WebElement Xpath(String value)
	{		
		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("return document.readyState")
		.toString().equals("complete");		
		WebElement test1=driver.findElement(By.xpath(value));
		return test1;		
	}
	
	public static WebElement css(String value)
	{		
		WebElement test1=driver.findElement(By.cssSelector(value));
		return test1;		
	}
	
	public static WebElement linktext(String value)
	{		
		WebElement test1=driver.findElement(By.linkText(value));
		return test1;		
	}
	
	public static WebElement partiallinktext(String value)
	{		
		WebElement test1=driver.findElement(By.partialLinkText(value));
		return test1;		
	}
	
	public static WebElement name(String value)
	{		
		WebElement test1=driver.findElement(By.name(value));
		return test1;		
	}
		
	public static List<WebElement> XpathGroup(String value)
	{
		 el=driver.findElements(By.xpath(value));
		return el;
	}
	 
	public static WebElement DXpath(String keyword,String value) throws InterruptedException 
	 {
		  WebElement test1 = null;		 
		  if(ControlHandling.IsFieldPresent(driver.findElements(By.xpath("//input[starts-with(@id, 'question') or starts-with(@id, 'attribute')]//ancestor::tr/td[1]|//textarea[starts-with(@id, 'question') or starts-with(@id, 'attribute')]//ancestor::tr/td[1]")),value))
		  {
			  JavascriptExecutor je = (JavascriptExecutor)driver;
			  je.executeScript("return document.readyState")
			            .toString().equals("complete");
			  List<WebElement> attribute=null;
			  attribute=driver.findElements(By.xpath("//input[starts-with(@id, 'question') or starts-with(@id, 'attribute')]|//textarea[starts-with(@id, 'question') or starts-with(@id, 'attribute')]"));
			  test1= attribute.get(ControlHandling.getFieldNumber(value));
		  }		 
		  else if(keyword.equals("assert"))
		  {
			  JavascriptExecutor je = (JavascriptExecutor)driver;
			  je.executeScript("return document.readyState")
				            .toString().equals("complete");
			  Thread.sleep(1000);				 				
			  List<WebElement> a=driver.findElements(By.xpath("//table[@class='attributes-box-table']//ancestor::tr/td[2]//a[1]|//div[@class='questions-box']//ancestor::tr/td[2]//a[1]"));
			  test1=a.get(ControlHandling.AssignmentVerify(value));				
			}
		 
		 else if(!(keyword.contains("elementNotDisplayedAssert")))
		 { 
			 JavascriptExecutor je = (JavascriptExecutor)driver;
			 je.executeScript("return document.readyState")
			            .toString().equals("complete");			
			 List<WebElement> dropdown=driver.findElements(By.xpath("//table[@class='table full' or @class='table full table-middle']//div[starts-with(@id, 's2')]"));
			 for(int i=0;i<=dropdown.size();)
			 {						
				test1=dropdown.get(ControlHandling.getDropdownNumber(value));								
				break;					
			 }
		 }		 		 
		return test1;		 
	 }	
}