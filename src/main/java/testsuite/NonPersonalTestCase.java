	
	package testsuite;

	import java.io.IOException;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.interactions.Actions;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;

	import Report.ExceptionScreenshot;
	import Utilities.ControlHandling;
	import Utilities.Driverclass;
import pages.DrillDownPage;
import pages.HomePage;
	import pages.LoginPage;
	import pages.MenuPage;
	import pages.ReviewPage;
	import pages.StartReviewPage;

	public class NonPersonalTestCase  extends Driverclass {
		
		public NonPersonalTestCase(WebDriver driver, ExtentReports Report, ExtentTest Test) {
			super(driver, Report, Test);
			// TODO Auto-generated constructor stub
		}
		
		LoginPage loginpage=new LoginPage(driver, Report, Test);
		MenuPage menupage=new MenuPage(driver, Report, Test);
		StartReviewPage startReviewPage= new StartReviewPage(driver, Report, Test);
		ReviewPage reviewPage=new ReviewPage(driver, Report, Test);
		DrillDownPage drilldownpage=new DrillDownPage(driver, Report, Test);
		List<String> parameters = null;

		//Parameterized username, password, alert message
		public void InvalidLoginValidation(String parameter) throws IOException
		{
			try
			{
			parameters=ControlHandling.getParameters(parameter);	
		  //Enter username and click login.
			String url= driver.getCurrentUrl();
			if(url.indexOf("/?returnTo=projects%2Fdrilldown")>0)
			{
				System.out.println(url);
				String Url1=url.replace("/?returnTo=projects%2Fdrilldown","");
				driver.navigate().to(Url1);
			}
			ControlHandling.sendkeys(loginpage.username_Fld, parameters.get(0), Test);
			ControlHandling.click(loginpage.login_Btn, Test, "login button");
			ControlHandling.waitForElement(loginpage.alert_msg);	  
		 	ControlHandling.trueAssertion(loginpage.alert_msg.getText().toString().contains(parameters.get(2)),Test,"Invalid credentials alert message is displayed");
		 	ControlHandling.click(loginpage.alertClose_btn,Test,"login.alertClose_btn");
		 	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 	
		 //Enter password and click login
		 	loginpage.username_Fld.clear();
		 	ControlHandling.sendkeys(loginpage.password_Fld, parameters.get(1), Test);
		 	ControlHandling.click(loginpage.login_Btn, Test, "Login button");
		 	ControlHandling.waitForElement(loginpage.alert_msg);	  
		 	ControlHandling.trueAssertion(loginpage.alert_msg.getText().toString().contains(parameters.get(2)),Test,"Invalid credentials alert message is displayed");
		 	ControlHandling.click(loginpage.alertClose_btn,Test,"login.alertClose_btn");
		 	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 	
		 //Enter username and wrong password and click login	 	
		 	loginpage.password_Fld.clear();
		 	loginpage.loginUser(parameters.get(0),"Wrong pwd");
		 	ControlHandling.waitForElement(loginpage.alert_msg);	  
		 	ControlHandling.trueAssertion(loginpage.alert_msg.getText().toString().contains(parameters.get(2)),Test,"Invalid credentials alert message is displayed");
		 	ControlHandling.click(loginpage.alertClose_btn,Test,"login.alertClose_btn");
		 	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 	
		 //Enter username and password and click login
		 	loginpage.password_Fld.clear();
		 	loginpage.loginUser(parameters.get(0),parameters.get(1));
		 	HomePage homepage=new HomePage(driver, Report, Test);
		 	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 	Thread.sleep(1000);
		 	ControlHandling.elementIsDisplayedAssertion(homepage.myreviews_tab,Test, "My review tab");
		 	menupage.logout();
		 	Report.flush();
			}
			 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
			
		}

		//Prerequisite Suspended account needed
		//Parameterized suspended username and it password and invalid username
		public void activeUserslogin(String parameter) throws IOException
		{
			try
			{
			parameters=ControlHandling.getParameters(parameter);
			
		  //URL accessible validation
			ControlHandling.trueAssertion(loginpage.username_Fld.isDisplayed(), Test, "username is displayed");
		 	ControlHandling.trueAssertion(loginpage.password_Fld.isDisplayed(), Test, "Password is displayed");
			
		  //Suspended user account validation
		/*	loginpage.loginUser(parameters.get(0),parameters.get(1));
			ControlHandling.waitForElement(loginpage.alert_msg);	  
		 	ControlHandling.trueAssertion(loginpage.alert_msg.getText().toString().contains("Your account has been suspended. Please contact an administrator"),Test,"Suspended account alert message is displayed");
		 	ControlHandling.click(loginpage.alertClose_btn,Test,"login.alertClose_btn");*/
		 	
		 	//Empty username password validation
		 	loginpage.username_Fld.clear();
		 	loginpage.password_Fld.clear();
		 	ControlHandling.click(loginpage.login_Btn, Test, "Login button");
			ControlHandling.trueAssertion(loginpage.username_Fld.isDisplayed(), Test, "username is displayed");
		 	ControlHandling.trueAssertion(loginpage.password_Fld.isDisplayed(), Test, "Password is displayed");
		 	
		 	//Non Existing username password validation
		 	loginpage.loginUser(parameters.get(2),"gfdgfdgf");
		 	ControlHandling.waitForElement(loginpage.alert_msg);	  
		 	ControlHandling.trueAssertion(loginpage.alert_msg.getText().toString().contains("Please check your username and/or password. If they are correct, your account may be suspended."),Test,"Invalid credentials alert message is displayed");
		 	ControlHandling.click(loginpage.alertClose_btn,Test,"login.alertClose_btn");
		 	Report.flush();
			}
			 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
		}

		
		//Prerequisite LoginUser must be run
		//Parameterized project, reviewtype, name and  Entity
		
		public void startReviewValidation(String parameter) throws IOException, InterruptedException
		{
			try
			{
			parameters=ControlHandling.getParameters(parameter);
			
			//Start a new review from top bar
			ControlHandling.click(menupage.tasks_menu, Test, "My task menu");
			ControlHandling.click(menupage.topbarStartnewReview_btn, Test, "Start new review from top bar button");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ControlHandling.clickandselect(startReviewPage.project_dd, parameters.get(0), Test);
			ControlHandling.containsclickandselect(startReviewPage.reviewtype_dd,parameters.get(1), Test); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ControlHandling.click(startReviewPage.name_dd,Test,"name_dd");
			Actions action=new Actions(driver);             
			action.moveToElement(startReviewPage.name_fld).sendKeys(parameters.get(3)).build().perform(); 
			Thread.sleep(5000); 
			ControlHandling.click(startReviewPage.newparty_optn,Test,"newparty_optn");
			ControlHandling.trueAssertion(startReviewPage.type_rbtn.get(0).isDisplayed(), Test, "Entity type");
			ControlHandling.trueAssertion(startReviewPage.type_rbtn.get(1).isDisplayed(), Test, "Person type");
			ControlHandling.trueAssertion(startReviewPage.type_rbtn.get(2).isDisplayed(), Test, "Other type");
			startReviewPage.ChooseType(parameters.get(3)); 
			ControlHandling.click(startReviewPage.add_btn,Test,"Add Button");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			reviewPage.verifyHeader(reviewPage.header.getText().toString(), parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
			ControlHandling.Assertion(reviewPage.cliententity_fld.getText().toString(), parameters.get(3), Test, " Client/Entity name");
			
			//Start review button validation in review page
			reviewPage.clickStartReview_btn();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ControlHandling.trueAssertion(reviewPage.timer.isDisplayed(), Test, " timer button is displayed");
			ControlHandling.trueAssertion(reviewPage.pause_btn.isDisplayed(), Test, "Pause button is displayed");
			ControlHandling.trueAssertion(reviewPage.calculateRisk_btn.isDisplayed(), Test, "Pause button is displayed");
			
			//Pause button validation
			ControlHandling.click(reviewPage.pause_btn,Test,"Pause button");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ControlHandling.falseAssertion(ControlHandling.isdisplayed(reviewPage.timer), Test, " timer button is not displayed");
			ControlHandling.falseAssertion(ControlHandling.isdisplayed(reviewPage.pause_btn), Test, "Pause button is not displayed");
			ControlHandling.falseAssertion(ControlHandling.isdisplayed(reviewPage.calculateRisk_btn), Test, "Pause button is not displayed");
			ControlHandling.Assertion(reviewPage.Start_continuereview_btn.getText().toString(), "Continue review", Test, "Continue review button is displayed");
			
			//Continue button validation
			 ControlHandling.click(reviewPage.Start_continuereview_btn,Test,"Continue review button");
			 ControlHandling.trueAssertion(reviewPage.timer.isDisplayed(), Test, " timer button is displayed");
			 ControlHandling.trueAssertion(reviewPage.pause_btn.isDisplayed(), Test, "Pause button is displayed");
			 ControlHandling.trueAssertion(reviewPage.calculateRisk_btn.isDisplayed(), Test, "Pause button is displayed");
			 Report.flush();
			}
			 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
		}
	
		//parameterised currentURL,DrilldownURL
		public void logoutValidation(String parameter) throws IOException
		{
			try
			{
				parameters=ControlHandling.getParameters(parameter);
				menupage.logout();
				driver.getCurrentUrl();
				ControlHandling.Assertion(driver.getCurrentUrl().toString(),parameters.get(0), Test, parameters.get(0));
				driver.navigate().to(parameters.get(1));
				ControlHandling.Assertion(driver.getCurrentUrl().toString(),parameters.get(1), Test, parameters.get(1));
				ControlHandling.elementIsNotDisplayedAssertion(	drilldownpage.drilldown_table, Test, "drilldown");
			
				Report.flush();
			}
			
			 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
		}
		
	}



