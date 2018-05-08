package keyword;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Report.ExceptionScreenshot;
import Utilities.Driverclass;
import excelRead.PropertyFileReader;
import pages.DrillDownPage;
import pages.FieldPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MenuPage;
import pages.ReviewPage;
import pages.SettingsPage;
import pages.StartReviewPage;
import testsuite.DataVali;
import testsuite.GeneralTestCase;
import testsuite.NonPersonalTestCase;


public class ActionSwitch extends Driverclass{
	
	public ActionSwitch(WebDriver driver, ExtentReports Report, ExtentTest Test) {
		super(driver, Report, Test);
		// TODO Auto-generated constructor stub
	}
	
	public static String reviewID;
	
	public void pommethods(String locatortype,String value,String keyword,String parameter,String tcTitle,String status) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
	{
		if((!(tcTitle.isEmpty())&& status.equalsIgnoreCase("Ready")) || tcTitle.isEmpty() && status.isEmpty()|| (tcTitle.isEmpty()&& status.equalsIgnoreCase("Ready")))
		{
			if(!tcTitle.isEmpty())
			{
				Test=Report.createTest(tcTitle);
			}
			if(locatortype.equalsIgnoreCase("Testmethod"))
			{
				locateTestMethod(keyword,value,parameter,tcTitle);
			}
			else if(locatortype.equalsIgnoreCase("ActionMethod"))
			{
				locateActionMethod(keyword,value,parameter,tcTitle);
			}
		}
		
		
	}
	public void locateActionMethod(String keyword, String methodname,String parameter,String testname) throws IOException
	{
		try
		{
			
			
			switch(keyword)
			{
				case "LoginPage":											
					LoginPage loginpage=new LoginPage(driver, Report, Test);	
					passActionArguments(loginpage,methodname,parameter);				
				    Report.flush();
					break;
					
				case "MenuPage":
					MenuPage menuPage=new MenuPage(driver, Report, Test);
					passActionArguments(menuPage,methodname,parameter);
					Report.flush();
					break;
					
				case "HomePage":
					HomePage homePage=new HomePage(driver, Report, Test);
					if(methodname.equals("searchclickViewOnUnassignedtab"))
					{
						
						homePage.searchclickViewOnUnassignedtab(PropertyFileReader.getPropertyValue(parameter));
						Report.flush();
					}
					else if(methodname.equals("searchclickContinueOnReviewsTab"))
					{
						homePage.searchclickContinueOnReviewsTab(PropertyFileReader.getPropertyValue(parameter));
						Report.flush();
					}
					else if(methodname.equals("AssertsearchclickContinueOnReviewsTab"))
					{
						homePage.AssertsearchclickContinueOnReviewsTab(PropertyFileReader.getPropertyValue(parameter));
						Report.flush();
					}
					
					else
					{
					passActionArguments(homePage,methodname,parameter);
					Report.flush();
					}
					break;	
				
				case "DrillDownPage":
					DrillDownPage drilldownPage=new DrillDownPage(driver, Report, Test);
					if(methodname.equals("clickSearch"))
					{
						drilldownPage.clickSearch(PropertyFileReader.getPropertyValue(parameter));
					}
					else
					{
						passActionArguments(drilldownPage,methodname,parameter);
					}
					Report.flush();
					break;
				
				case "ReviewPage":
					ReviewPage reviewPage=new ReviewPage(driver, Report, Test);
					if(methodname.equals("getReviewID"))
					{
						reviewPage.getReviewID(parameter);
						Report.flush();
					}
					else
					{
						passActionArguments(reviewPage,methodname,parameter);
						Report.flush();
					}
					break;
					
				case "SettingsPage":
					SettingsPage settingsPage=new SettingsPage(driver, Report, Test);
					passActionArguments(settingsPage,methodname,parameter);
					Report.flush();
					break;

				case "StartReviewPage":
					StartReviewPage startReviewPage=new StartReviewPage(driver, Report, Test);
					passActionArguments(startReviewPage,methodname,parameter);
					Report.flush();
					break;	

				case "FieldPage":
					FieldPage fieldPage=new FieldPage(driver, Report, Test);
					passActionArguments(fieldPage,methodname,parameter);
					Report.flush();
					break;
			}
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}	

	public void locateTestMethod(String keyword,String methodname,String parameter,String testname) throws IOException
	{
		try
		{
			
			switch(keyword)
			{
				case "GeneralTestCase":
					GeneralTestCase generalTestCase =new GeneralTestCase(driver, Report, Test);
					passTestArguments(generalTestCase,methodname,parameter);
					Report.flush();
					break;
				case "NonPersonalTestCase":
					NonPersonalTestCase nonPersonalTestCase =new NonPersonalTestCase(driver, Report, Test);
					passTestArguments(nonPersonalTestCase,methodname,parameter);
					Report.flush();
					break;
					
				case "DataVali":
					DataVali dataVali =new DataVali(driver, Report, Test);
					passTestArguments(dataVali,methodname,parameter);
					Report.flush();
					break;
			}
			
		}
		
		catch(Exception e)
		{
			Test.log(Status.FAIL, e.toString());
			ExceptionScreenshot.getScreenshot();
		}
	}
	

	public void passActionArguments(Object obj, String methodname,String parameter)
	{
		try
		{
			if(!(parameter.isEmpty()))
			{			
				Class[] parameterTypes = new Class[] { String[].class };
				Method m=obj.getClass().getMethod(methodname,parameterTypes);						
				Object[] arguments = new Object[]  {new String[]{parameter}};			
				m.invoke(obj,arguments); 
			}
			else
			{
				Method m=obj.getClass().getMethod(methodname);								
				m.invoke(obj); 
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void passTestArguments(Object obj, String methodname,String parameter)
	{
		try
		{
			if(!(parameter.isEmpty()))
			{
			
				Method m=obj.getClass().getMethod(methodname,String.class);								
				m.invoke(obj,parameter); 
			}
			else
			{
				
				Method m=obj.getClass().getMethod(methodname);								
				m.invoke(obj); 
			}
		}
		catch(Exception e)
		{
		
		}
	}
}
