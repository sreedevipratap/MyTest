package Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;//--added interactions
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Report.ExceptionScreenshot;

public class ControlHandling extends Driverclass {	
	public static ArrayList<String> tabs2;
	private static List<String> splittedText;
	
	

	public ControlHandling(WebDriver driver, ExtentReports Report, ExtentTest Test) {
		super(driver, Report, Test);
		// TODO Auto-generated constructor stub
	}

	public static void click(WebElement element,ExtentTest Test,String value) throws IOException
	{
		try
		{
		waitForElement(element);
		movetoelement(element);
		element.click();
		Test.log(Status.PASS, value+" button is clicked");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void doubleclick(WebElement element,ExtentTest Test,String value) throws IOException
	{
		try
		{
		waitForElement(element);
		movetoelement(element);
		Actions action=new Actions(driver);             
        action.moveToElement(element).doubleClick().build().perform();
		Test.log(Status.PASS, value+" button is clicked");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void actionSendkeys(WebElement element, String parameter,ExtentTest Test) throws IOException
	{
		try
		{
			 Actions action=new Actions(driver);             
	         action.moveToElement(element).sendKeys(parameter).build().perform(); 
	         Test.log(Status.PASS,parameter+" is entered");
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+parameter);
			ExceptionScreenshot.getScreenshot();
		}
	}
	public static void Enterclick(WebElement element, String parameter,ExtentTest Test) throws IOException, InterruptedException
	{
		try
		{
		 element.click();
         Actions action=new Actions(driver);             
         action.moveToElement(element).sendKeys(parameter).build().perform(); 
         element.findElement(By.xpath("//span[contains(text(),'"+parameter+"')]")).click();
         Thread.sleep(5000);
         Test.log(Status.PASS,parameter+" is entered in the dropdown and selected");
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+parameter);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void ctrlClick(WebElement el,ExtentTest Test, String value) throws IOException
	{
		try
		{
		Actions action=new Actions(driver);	
		action.keyDown(Keys.CONTROL).click(el).keyUp(Keys.CONTROL).build().perform();		
		Test.log(Status.PASS,value+" drop down value is selected");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
		
	public static void sendkeys(WebElement element, String value,ExtentTest Test) throws IOException
	{
		try
		{
		waitForElement(element);
		movetoelement(element);
		element.sendKeys(value);
		Test.log(Status.PASS,value+" value is entered");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void clickandselect(WebElement element, String value,ExtentTest Test) throws IOException
	{
		try
		{
		waitForElement(element);
		movetoelement(element);
		element.click();
		scrolltoElement(element.findElement(By.xpath("//div[text()='"+value+"']")));
		element.findElement(By.xpath("//div[text()='"+value+"']")).click();
		Test.log(Status.PASS,value+" drop down value is selected");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
		
		
	}
	
	public static void containsclickandselect(WebElement element, String value,ExtentTest Test) throws IOException
	{
		try
		{
			
		waitForElement(element);
		movetoelement(element);
		element.click();
		Thread.sleep(10000);	
		element.findElement(By.xpath("//div[contains(text(),'"+value+"')]")).click();
		Test.log(Status.PASS,value+" drop down value is selected");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
		
		
	} 

	public static void scrollandSelect(WebElement element,String value,ExtentTest Test) throws IOException
	{
		try
		{
		movetoelement(element);
		element.click();		
		JavascriptExecutor je = (JavascriptExecutor) Driverclass.driver;
		je.executeScript("arguments[0].scrollIntoView(true);",element.findElement(By.xpath("//div[@id='select2-drop']//div[contains(text(),'"+value+"')]")));	
		element.findElement(By.xpath("//div[@id='select2-drop']//div[contains(text(),'"+value+"')]")).click();
		Test.log(Status.PASS,value+" drop down value is selected");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void searchandselect(List<WebElement> element,String value,ExtentTest Test) throws IOException
	{
		
		for(int i=0;i<element.size();i++)
			
		{
			if(element.get(i).getText().toString().contains(value))
			{
				try
				{
					scrolltoElement(element.get(i));
					if(handlingStaleElement(element.get(i)))
					{
						element.get(i).click();
						break;
					}
					
					Test.log(Status.PASS, value+" button is clicked");
				}
				
				catch(Exception e)
				{
					Test.log(Status.FAIL, e.toString()+value);
					ExceptionScreenshot.getScreenshot();
				}
			}
		}
	
	}
	
	public static void select(WebElement element, String value,ExtentTest Test) throws IOException
	
	{
		try
		{
		Select select =new Select(element);
		select.selectByVisibleText(value);
		Test.log(Status.PASS,value+" drop down is selected");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void Assertion(Object actual, Object expected,ExtentTest Test, String value) throws IOException
	{
		try
		{
			
			
		Assert.assertEquals(actual, expected);
		Test.log(Status.PASS,"Assert Pass- "+value);
		
		}
		catch(AssertionError e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
		
	}
	
	public static void trueAssertion(boolean result,ExtentTest Test, String value) throws IOException
	{
		try
		{
		Assert.assertTrue(result);
		Test.log(Status.PASS,"Assert- "+value);
		}
		catch(AssertionError e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void CheckOptions(WebElement element, String value,ExtentTest Test) throws IOException	
		{
			boolean result = false;
			try
			{
				Select select =new Select(element);		
				List<WebElement> optionSelect = select.getOptions();     
				for (int i = 0; i < optionSelect.size(); i++)
				{
					if((optionSelect.get(i).getText()).equals(value))
					{
						result=true;
						trueAssertion(result, Test, value);
					}           
				}
			}
			catch(Exception e)
			{
				Test.log(Status.FAIL, e.toString()+value);
				ExceptionScreenshot.getScreenshot();
			}		
	}

	//changed the method name
	public static void elementIsDisplayedAssertion(WebElement e,ExtentTest Test, String value) throws IOException
	{
		
		trueAssertion(e.isDisplayed(),Test,value);
	}
	
	// Changed the method name
	public static void elementIsNotDisplayedAssertion(WebElement e,ExtentTest Test, String value) throws IOException
	{
		if(isdisplayed(e))
		{
		falseAssertion(e.isDisplayed(),Test,value);
		}
		else
		{
			falseAssertion(isdisplayed(e),Test,value);
		}
	}
	
	public static void integerAssert(WebElement e,String parameter,ExtentTest Test) throws IOException
	{
		try
		{
		
			
		
		Assert.assertEquals(e.getAttribute("value").toString().length(), Integer.parseInt(parameter));
		Test.log(Status.PASS,"Assert- "+ "Length is "+e.getAttribute("value").toString().length());
		}
		catch(AssertionError el)
		{
			Test.log(Status.FAIL, e.toString());
			ExceptionScreenshot.getScreenshot();
		}
		
	}
	
	public static void listOfAssertion(List<WebElement> listel, String parameter, ExtentTest Test) throws IOException
	{
		try
		{				 
		  for(WebElement e:listel)
		  {
			  if(!(e.getText().toString().isEmpty()))
				{				 
				  trueAssertion(getParameters(parameter).contains(e.getText().toString()), Test, e.getText().toString());				  
				 }
		  }
		}
		catch(AssertionError e)
		{
			Test.log(Status.FAIL, e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void falseAssertion(boolean result,ExtentTest Test, String value) throws IOException
	{
		try
		{
			Assert.assertFalse(result);
			Test.log(Status.PASS,"Assert- "+value);
		}
		catch(AssertionError e)
		{
			Test.log(Status.FAIL,e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	public static void assertColor(List<WebElement> color,String colorcode,ExtentTest Test) throws IOException
	{
		   for(WebElement e:color)
		   {
			   trueAssertion(getColorCode(e.getCssValue("color")).equals(colorcode), Test, "color: "+e.getText().toString());
			  			   
		   }
	}
	
	public static void colorAssert(WebElement e,String colorcode,ExtentTest Test) throws IOException
	{
		try
		{		
		trueAssertion(getColorCode(e.getCssValue("background-color")).equals(colorcode), Test, "color: "+e.getText().toString());
			
		
		}
		
		catch(AssertionError el)
		{
			Test.log(Status.FAIL, el.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
		
	public static void delete(String value,ExtentTest Test) throws IOException
    {
    	try
    	{
    		List<WebElement> delete;
    		if(IsFieldPresent(driver.findElements(By.xpath("//a[@href='#clear']/ancestor::td/ancestor::tr/td[1]")),value))
    			{
    			 	delete=driver.findElements(By.xpath("//a[@href='#clear']"));
    			 	click(delete.get(getNumber(driver.findElements(By.xpath("//a[@href='#clear']/ancestor::td/ancestor::tr/td[1]")),value)),Test,value+" is deleted");
    			}  
    		else if(IsFieldPresent(driver.findElements(By.xpath("//abbr[@class='select2-search-choice-close']/ancestor::td/ancestor::tr/td[1]")),value))
    			{
    				delete=driver.findElements(By.xpath("//abbr[@class='select2-search-choice-close']"));
    				click(delete.get(getNumber(driver.findElements(By.xpath("//abbr[@class='select2-search-choice-close']/ancestor::td/ancestor::tr/td[1]")),value)),Test,value+" is deleted");
    			}
    		else if(IsFieldPresent(driver.findElements(By.xpath("//table[@class='table full' or @class='table full table-middle']//td//textarea/ancestor::td/ancestor::tr/td[1]")),value))
    			{
    				try
    				{
    					delete=driver.findElements(By.xpath("//table[@class='table full' or @class='table full table-middle']//td//textarea"));
    					delete.get(getNumber(driver.findElements(By.xpath("//table[@class='table full' or @class='table full table-middle']//td//textarea/ancestor::td/ancestor::tr/td[1]")),value)).clear();
    					Test.log(Status.PASS,value+"value is deleted");
    				}
    				catch(Exception e)
    				{
    					Test.log(Status.FAIL, e.toString()+value);
    					ExceptionScreenshot.getScreenshot();
    				}
    			}
    		else
    			{    						
    				delete=driver.findElements(By.xpath("//a[@class='select2-search-choice-close']"));
    				for(WebElement d:delete)
    				{
    					if(IsFieldPresent(d.findElements(By.xpath("//a[@class='select2-search-choice-close']/ancestor::td/ancestor::tr/td[1]")),value))
    						{
    							click(d,Test,value+" is deleted");
    						}   				
    				}    			
    			}   		
    		Report.flush();
    	}
    	catch(Exception el)
		{  		
			Test.log(Status.FAIL, el.toString());
			ExceptionScreenshot.getScreenshot();
		}
    }
	public static void scroll() throws IOException
	{		
		JavascriptExecutor jse = (JavascriptExecutor)Driverclass.driver;
		jse.executeScript("window.scrollBy(0,250)", "");				
	}
    
		//Removed Test parameter to avoid entry in report
	public static void scrolltoElement(WebElement element) throws IOException
	{			
		waitForElement(element);
		JavascriptExecutor je = (JavascriptExecutor) Driverclass.driver;
		je.executeScript("arguments[0].scrollIntoView(true);",element);					
	}
	
	public static void handlingAlert(String parameter,ExtentTest Test) throws IOException
	{	
		try
		{
			Alert alert=Driverclass.driver.switchTo().alert();
			if(parameter.equalsIgnoreCase("Cancel"))
			{
				alert.dismiss();
			}
			if(parameter.equalsIgnoreCase("OK"))
			{
				alert.accept();
			}
			Test.log(Status.PASS,"Alert is handled");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+parameter);
			ExceptionScreenshot.getScreenshot();
		}
		
	
	}
	
	public static void mouseOver(WebElement element,String value, ExtentTest Test) throws IOException
	{
		 
		try
		{
			Actions action=new Actions(Driverclass.driver);
			action.moveToElement(element).build().perform();
			Test.log(Status.PASS, "Mouse over on "+value);
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
		
	}
   
	public static void handlingWindowpopup(WebElement element,ExtentTest Test) throws IOException
	{
		try
		{
		Driverclass.driver.switchTo().activeElement();
		waitForElement(element);
		click(element,Test,"Popup");
		Test.log(Status.PASS,"Window Pop up is handled");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	
	public static void switchToChild(String window,ExtentTest Test) throws IOException
	 {
		 try
		 {
			 for(String childWindow: Driverclass.driver.getWindowHandles())
			 {
				if(!(childWindow.equals(window)))
				{
					Driverclass.driver.switchTo().window(childWindow);
					 Test.log(Status.PASS,"switch to Child window");
				}	
				Report.flush();
			}	
		 }
		 
			catch(Exception e)
			{
				Test.log(Status.FAIL, e.toString());
				ExceptionScreenshot.getScreenshot();
			}
			
	 }
		
	public static void switchToParent(String window,ExtentTest Test) throws IOException
	 {
		 try
		 {
			 driver.switchTo().window(window);
			 Test.log(Status.PASS,"switch to parent window");
			 Report.flush();
		 }
		 catch(Exception e)
			{
				Test.log(Status.FAIL, e.toString());
				ExceptionScreenshot.getScreenshot();
			}
		 
	 }
	 
	public static void documentupload(WebElement element,ExtentTest Test,String value) throws IOException
	{
		try
		{
		waitForElement(element);
		element.click();
		Thread.sleep(10000);
		Runtime.getRuntime().exec(value);
		Test.log(Status.PASS, value+" document uploaded");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
	
	
	
	public static void clickandEnter(WebElement element,String value,ExtentTest Test ) throws InterruptedException, IOException
	{
		try
		{
		waitForElement(element);
		movetoelement(element);
		 element.click();
         Actions action=new Actions(driver);             
         action.moveToElement(element).sendKeys(value).build().perform(); 
		Test.log(Status.PASS,value+" drop down value is selected");
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
	}
			
	public static boolean isAlertPresents() {
		try {
			Driverclass.driver.switchTo().alert();
		return true;
		}// try
		catch (Exception e) {
		return false;
		}// catch
		}
				
	public static String handlingHTML5ErrorMsg(WebElement e)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;	
		String message = (String)js.executeScript("return arguments[0].validationMessage;", e);				
		return message;
	}
	
    public static List<String> getParameters(String... args)
    {
    	splittedText = null;
    	if(args.length==1)
    	{   		
    		splittedText = Arrays.asList(args[0].split(","));    		    	
    	}   	
    	else
    	{
    		for(int i=0;i<args.length;i++)
    		{
    			splittedText = Arrays.asList(args);  			
    		}    		
    	}   	
    	return splittedText;
    }
    
    public static void waitForElement(WebElement element) throws IOException
	{					
		WebDriverWait wait = new WebDriverWait(Driverclass.driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));									
	}
   
    public static void tableAssertion(WebElement table, String parameter,ExtentTest Test) throws IOException
	{		
    	List<String>parameters=getParameters(parameter);
			List<WebElement> row =  table.findElements(By.tagName("tr"));
			int count=0;
			Driverclass.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			for(WebElement e: row)
			{	
				if(count==0)
					{
						List <WebElement> data= e.findElements(By.tagName("td"));					
						for(WebElement d: data)
						{
							if(parameters.size()>0)
								{
				                	for(int i=0;i<parameters.size();i++)
				                	{
				                		if(parameters.get(i).equalsIgnoreCase(d.getText().toString()))
				                            {
				                			ControlHandling.Assertion(d.getText().toString(), parameters.get(i), Test, parameters.get(i));
											count++;
				                        	break;
				                            }
				                	}
								}
							
							else if(d.getText().toString().equals(parameters.get(0)))
								{
									ControlHandling.Assertion(d.getText().toString(), parameters.get(0), Test, parameters.get(0));
									count++;
									break;
								}										  
						}					
					}
					else
					{
						break;
					}
				}
			
		
	}
    
    public static int getTablerow(WebElement table, String parameter)
	{		
		int count = 0;
		int column=0;
		List<WebElement> row =  table.findElements(By.tagName("tr"));		
		for(int j=0;j<row.size();j++)
		{			
			List<WebElement> data=row.get(j).findElements(By.tagName("td"));
			if(count==0)
				{
					for(WebElement d:data)
						{	
						System.out.println(d.getText().toString()); 
							if(d.getText().toString().equals(parameter))
							{
								column=j-1;
								count++;
								break;							
							}
						}
				}				
				else
					{
						break;
					}
		}						
		return column;
	}
		
    
    public static void frame(WebElement el,ExtentTest Test,String parameter, String value) throws IOException
    {
        try
        {
            driver.switchTo().frame(el);
            driver.findElement(By.xpath("//*[@class='wysihtml5-editor']")).sendKeys(parameter);
            driver.switchTo().defaultContent();             
            Test.log(Status.PASS,value+"Comments entered");
        }
        catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
    }
 
	public static void movetoelement(WebElement element)
	{

		int j= element.getLocation().y;
		int i=element.getLocation().x;
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		JavascriptExecutor js =(JavascriptExecutor) Driverclass.driver;
		js.executeScript("scroll(250, 0)");
		Coordinates coordinate = ((Locatable) element)
				.getCoordinates();
				coordinate.onPage().move(i, j);;				
				coordinate.inViewPort();				
	}
    
	public static String getColorCode(String color)
	{
		String[] hexValue = color.replace("rgba(","").replace(")","").split(",");                           
		hexValue[0] = hexValue[0].trim();
		int hexValue1 = Integer.parseInt(hexValue[0]);                   
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);                   
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]); 
		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		return actualColor;
	}
	
	public static boolean isdisplayed(WebElement e)
	{
		boolean result =false;
		try
		{
			e.click();			
			result=true;
		}
		catch(Exception ex)
		{
			result=false;
		}
		
		return result;
	}
	
	public  static int getFieldNumber(String value)
	{				
		List<WebElement> a=driver.findElements(By.xpath("//input[starts-with(@id, 'question') or starts-with(@id, 'attribute')]//ancestor::tr/td[1]|//textarea[starts-with(@id, 'question') or starts-with(@id, 'attribute')]//ancestor::tr/td[1]"));		  	      
	    return getNumber(a,value);
	}
	
	public  static int getDropdownNumber( String value)
	{
		List<WebElement> a=driver.findElements(By.xpath("//table[@class='table full' or @class='table full table-middle']//div[starts-with(@id,'s2')]//ancestor::tr/td[1]"));		
		return getNumber(a,value);
	}

	public static int AssignmentVerify(String parameter)
	{
	  List<WebElement> a=driver.findElements(By.xpath("//table[@class='attributes-box-table']//td[1]|//div[@class='questions-box']//td[1]"));		      
	  return getNumber(a,parameter);
	}
	
	public static boolean IsFieldPresent(List<WebElement> a,String parameter)
	{
		boolean test=false;
		//List<WebElement> a=driver.findElements(By.xpath("//input[starts-with(@id, 'question') or starts-with(@id, 'attribute')]//ancestor::tr/td[1]|//textarea[starts-with(@id, 'question') or starts-with(@id, 'attribute')]//ancestor::tr/td[1]"));
		
			 for(int j=0;j<a.size();j++)
		       {
			 String value1=parameter.trim();	    	   
	    	   String value2=a.get(j).getText().toString().trim();  
				int r= value1.compareToIgnoreCase(value2);
		    	if(r==0)
		    	 {
		    		  test=true;
		    		   break;   
		    	   }
		    	 else if(r==128)
		    	 {
		    		 test=true;
		    		   break; 
		    		 
		    	 }	    	   	    		    	   	    	   
		       }		
		
		return test;
	}
	
	public static int getNumber(List<WebElement> a,String value)	
	{
		int l=0;
		   for(int j=0;j<a.size();j++)
	       {
	    	  
	    	   String value1= value.trim();
	    	   String value2= a.get(j).getText().toString().trim();
			
	    	   int r= value1.compareToIgnoreCase(value2);
	    	   if(r==0)
	    	   {
	    		   l=j;
	    		   break;
	    	   }
	    	   else if(r==128)
	    	   {
	    		   l=j;
	    		   break;
	    	   }	   	   
	       }
		   return l;
	}
	
	public static boolean handlingStaleElement(WebElement e)
		{
			boolean result =false;
			
			for(int i=0;i<3;i++)
			{
				try
				{				
					
					e.getText();
					result=true;
					
					break;
				}
				
				catch(StaleElementReferenceException ex)
				{
					System.out.println("insde exception");
				}
			}
			
			
			return result;
			
		}

	public static List<String> splitTextWithSpace(String text, String parameter)
		{
			 List<String> splittedText = Arrays.asList(text.split(parameter));
			
			 return splittedText;
		}

}
