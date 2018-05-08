package keyword;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Report.ExceptionScreenshot;
import Utilities.ControlHandling;
import Utilities.Driverclass;
import Utilities.Elementclass;
import excelRead.PropertyFileReader;


public class Keywordswitch extends Driverclass {
	public WebElement elm1;
	public List<WebElement> listel;
	String CurrentWindow;
	String getText;
	public Keywordswitch(WebDriver driver, ExtentReports Report, ExtentTest Test) {
		super(driver, Report, Test);
		// TODO Auto-generated constructor stub
	}
public void locate(String locatorTpye, String value,String keyword,String parameter,String tcTitle,String status) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, NoSuchMethodException, SecurityException, InterruptedException
	{
		try
		{
			if((!(tcTitle.isEmpty())&& status.equalsIgnoreCase("Ready")) || tcTitle.isEmpty() && status.isEmpty() || (!(tcTitle.isEmpty())&& status.equalsIgnoreCase("Utilities")))
				{
					if(!(tcTitle.isEmpty()) && !(status.equalsIgnoreCase("Utilities")))
						{
							Test=Report.createTest(tcTitle);
						}  
					if(!(locatorTpye.equals("XpathGroup")) )
						{
							if(!(locatorTpye.isEmpty()))
							{
								elm1=(WebElement) createWebElement(locatorTpye, value, keyword, parameter);
							}
						}	
					else if(locatorTpye.equalsIgnoreCase("XpathGroup"))
						{					
							listel=(List<WebElement>) createWebElement(locatorTpye, value, keyword, parameter);						
						}			 				
					switch(keyword)
						{
						 case "click":	    		   
							 ControlHandling.click(elm1, Test, value);
	    			         Report.flush();		    		 		
	    			         break;
	    			         
						 case "doubleclick":
							 ControlHandling.doubleclick(elm1, Test, value);
							 Report.flush();
							 break;
							 
						 case "Enterclick":
							 ControlHandling.Enterclick(elm1, parameter, Test);
							 Report.flush();
							 break;
						
						 case "clickAndEnter":
							 ControlHandling.clickandEnter(elm1, parameter, Test);
							 Report.flush();
							 break;
							 
						 case "ctrlClick":
							 ControlHandling.ctrlClick(elm1, Test, value);
							 Report.flush();
							 break;
							 
						 case "sendkeys":
							 ControlHandling.sendkeys(elm1, parameter,Test);
							 Report.flush();
							 break;	
						 
						 case "actionSendkeys":
							 ControlHandling.actionSendkeys(elm1, parameter, Test);
							 Report.flush();
							 break;	
						 case "clickandselect":
							 ControlHandling.clickandselect(elm1, parameter, Test);
							 Report.flush();
							 break;
							 
						 case "containsselect":
							 ControlHandling.containsclickandselect(elm1, parameter, Test);
							 Report.flush();
							 break;	
							 
						 case"scrollandselect":
							 ControlHandling.scrollandSelect(elm1, parameter, Test);
							 Report.flush();
							 break;		
							 
						 case "searchandselect":
							 ControlHandling.searchandselect(listel, parameter, Test);
							 Report.flush();
							 break; 
							 
						 case "select":
							 ControlHandling.select(elm1, value, Test);
							 Report.flush();
							 break;
							 
						 case "assert":
							 if(elm1.getText().toString().equals(null) && parameter.equals(null))
							 {
								 Test.log(Status.PASS, "Pass");
							 }	
							 else
							 {
								 ControlHandling.Assertion(elm1.getText().toString(), parameter, Test, parameter);
							 }
							 Report.flush();
							 break;
							 
						 case "assertSavedValue":
							 ControlHandling.Assertion(elm1.getAttribute("value").toString(),parameter, Test, parameter);
							 Report.flush();
							 break;	    	
							 
						 case "EditableDAssert":							
							 ControlHandling.trueAssertion(elm1.getText().toString().trim().contains(parameter), Test, parameter);
							 Report.flush();
							 break;
							 
						 case "dropdownAssert":
							 ControlHandling.CheckOptions(elm1, parameter, Test);
							 Report.flush();
							 break;
							 
						 case "elementisDisplayedAssert":
							 ControlHandling.elementIsDisplayedAssertion(elm1, Test, value);
							 Report.flush();
							 break;
							 
						 case "elementNotDisplayedAssert":
							 ControlHandling.elementIsNotDisplayedAssertion(elm1, Test, value);
							 Report.flush();
							 break; 
							 
						 case "integerAssert":
							 ControlHandling.integerAssert(elm1, parameter, Test);
							 Report.flush();
							 break;
							 
						 case "groupAssert":
							 ControlHandling.listOfAssertion(listel, parameter, Test);
							 Report.flush(); 
							 break;
							 
						 case "falseAssert":
							 ControlHandling.falseAssertion(elm1.getText().toString().equals(parameter), Test, value);
							 Report.flush();
							 break;
						
						 case "AssertColor":
							 if(!(locatorTpye.equals("XpathGroup")))
							 {
								 ControlHandling.colorAssert(elm1, parameter, Test);								 
							 }						
							 else
							 {
								 ControlHandling.assertColor(listel, parameter, Test);								 
							 }		
							 Report.flush();
							 break;    		
							 
						 case "expandAssert":
							 ControlHandling.trueAssertion(elm1.getAttribute("aria-expanded").toString().equals("false"), Test, value);
							 Report.flush();
							 break;		
							 
						 case "delete":
							 ControlHandling.delete(value, Test);
							 Report.flush();
							 break;
							 
						 case "Clear":
							 elm1.clear();
							 Report.flush();
							 break;
							 
						 case "scroll":
							 ControlHandling.scroll();
							 Report.flush();
							 break;
							 
						 case"scrolltoselect":
							 ControlHandling.scrolltoElement(elm1);
							 Report.flush();
							 break;	
							 
						 case "Wait":							  
							 Thread.sleep(Long.parseLong(parameter));
							 Report.flush();
							 break;
							 
						 case "alert":
							 if(ControlHandling.isAlertPresents())
							 {ControlHandling.handlingAlert(parameter, Test);}
							 Report.flush();
							 break;
							 
						 case "mouseOver":
							 ControlHandling.mouseOver(elm1, value, Test);
							 Report.flush();
							 break;  
							 
						 case "popup":
							 ControlHandling.handlingWindowpopup(elm1, Test);
							 Report.flush();
							 break;

						 case "Iframe":
							 ControlHandling.frame(elm1,Test, parameter, status);
							 Report.flush();
							 break;				  
												 
						 case "switchToChild":
							 PropertyFileReader.updatePropertyFile(parameter,Driverclass.driver.getWindowHandle());
							 ControlHandling.switchToChild(Driverclass.driver.getWindowHandle(),Test);
							 Report.flush();
							 break;
							 
						 case "switchToParent":							
							 ControlHandling.switchToParent( PropertyFileReader.getPropertyValue(parameter),Test);
							 Report.flush();
							 break;
							 
						 case "closeWindow":
							 Driverclass.driver.close();
							 Report.flush();
							 break;
							 
						 case "Upload":
							 ControlHandling.documentupload(elm1, Test, parameter);
							 Report.flush();
							 break;
							 
						 case "storeVariable":						
							 PropertyFileReader.updatePropertyFile(parameter, elm1.getText().toString());
							 Report.flush();
							 break;
				  
						 case "readStoredVariableForAssert":
							 ControlHandling.Assertion(elm1.getText().toString(), PropertyFileReader.getPropertyValue(parameter), Test, value);
							 Report.flush();
							 break;
						
						 case "tableassert":
			                 ControlHandling.tableAssertion(elm1, parameter, Test);			                
			                 Report.flush();
			                 break;																		
	    	  }
	    	  
	    	  
		      
	      }
				
	      
		
		}
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString()+value);
			ExceptionScreenshot.getScreenshot();
		}
}
	
public Object createWebElement(String locatortpye,String value,String keyword,String parameter) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException
{
        

    Object ob1;
    Elementclass cli=new Elementclass(driver,Report,Test);
    
      
     if(locatortpye.equalsIgnoreCase("DXpath"))
      {
       ob1=Elementclass.DXpath(keyword, value);
     
      }
     
      else if(locatortpye.equals("XpathGroup"))
      {
          ob1=Elementclass.XpathGroup(value);
      }
      
      else
      {             
          Method m=cli.getClass().getMethod(locatortpye,String.class);
          ob1=(WebElement) m.invoke(cli,value);             
      }
      
      
      return ob1;
}

  
 
}
				 
		
	


