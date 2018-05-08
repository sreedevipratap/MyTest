package testsuite;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import pages.SettingsPage;
import pages.StartReviewPage;

public class GeneralTestCase  extends Driverclass{
	
	public GeneralTestCase(WebDriver driver, ExtentReports Report, ExtentTest Test) {
		super(driver,Report,Test);
	}
	 SettingsPage settingsPage=new SettingsPage(driver,Report,Test);
	 HomePage homePage=new HomePage(driver,Report,Test);
	 StartReviewPage startReviewPage = new StartReviewPage(driver,Report,Test);
	 DrillDownPage drilldownPage=new DrillDownPage(driver,Report,Test);
	 LoginPage loginPage=new LoginPage(driver,Report,Test);
	 ReviewPage reviewPage=new ReviewPage(driver,Report,Test);
	 MenuPage menuPage=new MenuPage(driver,Report,Test);
	 String reviewId;
	 List<String> parameters = null;
	 String parentwindow;
	 //Parameterized current password,first name,last name, email
	 
	 public void changeProfileInfo_Right_Pwd(String parameter) throws IOException
	 {		
		 try
		 {
			parameters=ControlHandling.getParameters(parameter);				    
		    menuPage.clickSettings();
			ControlHandling.trueAssertion(settingsPage.header.getText().toString().contains("Change your profile"), Test, "header");
			ControlHandling.listOfAssertion(settingsPage.label,"Username:,Change your password:,Repeat password:,Theme:,Display:,First name:,Last name:,Email address:,Current password:",Test);
			settingsPage.editSettingFields(parameters.get(1),parameters.get(2),parameters.get(3));
			settingsPage.enterCurrentPwd(parameters.get(0));
			settingsPage.clickOnSave();
			settingsPage.clickCancel();		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
		 
		 
	 }

	//Parameterized new password,current password,first name,last name, email
	 public void changeAccountPwd(String parameter) throws IOException
	 {
		 try
		 {
			parameters=ControlHandling.getParameters(parameter);
		    menuPage.clickSettings();
			ControlHandling.trueAssertion(settingsPage.header.getText().toString().contains("Change your profile"), Test, "header");
			settingsPage.changePwd(parameters.get(0),parameters.get(1));
			settingsPage.clickOnSave();
			settingsPage.verifySuccessMsg("Your profile has been updated!");
			settingsPage.editSettingFields(parameters.get(2),parameters.get(3),parameters.get(4));
			settingsPage.enterCurrentPwd(parameters.get(1));
			settingsPage.clickOnSave();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			settingsPage.verifySuccessMsg("Your profile has been updated!");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	 }
	 
	//Parameterized current password,first name,last name, email
	 public void correctPwdValidation(String parameter) throws IOException
	 {
		 try
		 {
			parameters=ControlHandling.getParameters(parameter);	
			menuPage.clickSettings();
			settingsPage.editSettingFields(parameters.get(1),parameters.get(2),parameters.get(3));
			settingsPage.enterCurrentPwd(parameters.get(0));
			settingsPage.clickOnSave();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			settingsPage.verifySuccessMsg("Your profile has been updated!");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	 }
	 
	//Parameterized new password current password 
	 public void blankPwdValidation(String parameter) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(parameter);
			 menuPage.clickSettings();
		    ControlHandling.sendkeys(settingsPage.password_fld, parameters.get(0), Test);
		    ControlHandling.sendkeys(settingsPage.currentpwd_fld, parameters.get(1), Test);
			settingsPage.clickOnSave();
			settingsPage.verifyFailureMsg("Not all required fields are filled in");	
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	 }
	 
	//Parameterized current password 
	 public void differentValuesPwdValidation(String parameter) throws IOException
	 {
		 try
		 {
			parameters=ControlHandling.getParameters(parameter);	
			menuPage.clickSettings();
		    ControlHandling.sendkeys(settingsPage.password_fld, "KYCnet1234", Test);
		    ControlHandling.sendkeys(settingsPage.password2_fld, "KYCnet123", Test);
		    ControlHandling.sendkeys(settingsPage.currentpwd_fld, parameters.get(0), Test);			
			settingsPage.clickOnSave();
			settingsPage.verifyFailureMsg("The submitted passwords do not match!");	
			settingsPage.changePwd("KYC12",parameters.get(0));
			settingsPage.clickOnSave();
			settingsPage.verifyFailureMsg("The password did not conform to the security requirements:"+"\n"+"Password needs to have at least 8 characters!");	
			settingsPage.changePwd("KYCnetwet",parameters.get(0));
			settingsPage.clickOnSave();
			settingsPage.verifyFailureMsg("The password did not conform to the security requirements:"+"\n"+"Password must include at least one number!");
			settingsPage.changePwd("kycnet123",parameters.get(0));
			settingsPage.clickOnSave();
			settingsPage.verifyFailureMsg("The password did not conform to the security requirements:"+"\n"+"Password must include at least one uppercase character!");
			settingsPage.changePwd("12345678",parameters.get(0));
			settingsPage.clickOnSave();
			settingsPage.verifyFailureMsg("The password did not conform to the security requirements:"+"\n"+"Password must include at least one letter character!"+"\n"+"Password must include at least one uppercase character!");
			settingsPage.changePwd("KYCNET123",parameters.get(0));
			settingsPage.clickOnSave();
			settingsPage.verifyFailureMsg("The password did not conform to the security requirements:"+"\n"+"Password must include at least one letter character!");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());
		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	 }
	 
	//Parameterized current password, first name
	 public void empty_field_validation(String parameter) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(parameter);
			 menuPage.clickSettings();
			 ControlHandling.trueAssertion(settingsPage.header.getText().toString().contains("Change your profile"), Test, "header");		
			 settingsPage.clickOnSave();
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 ControlHandling.Assertion(ControlHandling.handlingHTML5ErrorMsg(settingsPage.currentpwd_fld), "Please fill out this field.", Test, "Please fill out this field.");
		
			 settingsPage.firstname_fld.clear();;
			 settingsPage.enterCurrentPwd(parameters.get(0));
			 settingsPage.clickOnSave();
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 ControlHandling.Assertion(ControlHandling.handlingHTML5ErrorMsg(settingsPage.firstname_fld), "Please fill out this field.", Test, "Please fill out this field.");
		
			 ControlHandling.sendkeys(settingsPage.firstname_fld, parameters.get(1), Test);
			 settingsPage.lastname_fld.clear();
			 settingsPage.enterCurrentPwd(parameters.get(0));
			 settingsPage.clickOnSave();
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 ControlHandling.Assertion(ControlHandling.handlingHTML5ErrorMsg(settingsPage.lastname_fld), "Please fill out this field.", Test, "Please fill out this field.");
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	
	 }
	 
	//Parameterized project,review type,party name, party type 
	 public void unassignedTabValidation(String parameter) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(parameter);			
			startReviewPage.startNewReview(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
			 Thread.sleep(1000);
			 System.out.println(reviewPage.header.getText().toString());
			 List<String> text=ControlHandling.splitTextWithSpace(reviewPage.header.getText().toString(),"\\s+");
			 reviewId= text.get(1).toString();
			 menuPage.clickonMyTasks();	
			 ControlHandling.Assertion(homePage.header.getText().toString(), "My reviews", Test, "My Review header is displayed");
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 ControlHandling.click(homePage.unassigned_tab, Test, "unassigned tab");
			 ControlHandling.listOfAssertion(homePage.table_header, "Party,Review ID,Review type,Step,Started,Action", Test);
			 homePage.selectEntries("50",homePage.unassignedentries_dd);
			 ControlHandling.tableAssertion(homePage.unassignedtable,reviewId, Test);
			 homePage.clickView(reviewId,homePage.unassignedtable,homePage.unassignedview_btn);
			 reviewPage.verifyHeader(reviewPage.header.getText().toString(),parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));			 
			 verifyDrillDownTable(reviewId,"In progress", "-"); 
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 Report.flush();
			
		 }		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
	 }
	 
	//Parameterized project,review type,party name, party type,assigned user 
	 public void  reviewsOwnedtabValidation(String parameter) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(parameter);	
			 menuPage.clickonMyTasks();	
			 startReviewPage.startNewReview(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
			 Thread.sleep(1000);
			 reviewPage.clickStartReview_btn();
			 List<String> text=ControlHandling.splitTextWithSpace(reviewPage.header.getText().toString(),"\\s+");
			 reviewId= text.get(1).toString();
			 menuPage.clickonMyTasks();	
			 ControlHandling.Assertion(homePage.header.getText().toString(), "My reviews", Test, "My Review header is displayed");
			 ControlHandling.click(homePage.ownbyme_tab, Test, "Reviews owned by me tab");
			 ControlHandling.listOfAssertion(homePage.table_header, "Party,Review ID,Review type,Step,Started,Due date,Action", Test);
			 homePage.selectEntries("50",homePage.ownedByMeEntries_dd);
			 ControlHandling.waitForElement(homePage.ownedbymetable);
			 ControlHandling.tableAssertion(homePage.ownedbymetable,reviewId, Test);
			 homePage.clickView(reviewId,homePage.ownedbymetable,homePage.reviewsOwnedbyMeView_btn);
			 reviewPage.verifyHeader(reviewPage.header.getText().toString(), parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));			 
			 verifyDrillDownTable(reviewId,"In progress",parameters.get(4) ); 	 
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 Report.flush();
			 
		 }
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());		 		
		 		ExceptionScreenshot.getScreenshot();
		 	}
		 
	}
	//Parameterized project,review type,party name, party type,username, pwd, previous user, assigned user  	 
	 public void myReviewsTabValidation(String parameter) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(parameter);
			 menuPage.clickonMyTasks();	
			 startReviewPage.startNewReview(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
			 Thread.sleep(1000);
			 reviewPage.clickStartReview_btn();
			 List<String> text=ControlHandling.splitTextWithSpace(reviewPage.header.getText().toString(),"\\s+");
			 reviewId= text.get(1).toString();
			 menuPage.clickonMyTasks();				 
			 ControlHandling.listOfAssertion(homePage.table_header, "Party,Review type,Step,Action,Last action,Due date,Action", Test);
			 homePage.selectEntries("50",homePage.myReviewsEntries_dd);
			 ControlHandling.waitForElement(homePage.reviewstable);
			 ControlHandling.tableAssertion(homePage.reviewstable,reviewId, Test);
			 homePage.searchclickContinueOnReviewsTab(reviewId);
			 reviewPage.verifyHeader(reviewPage.header.getText().toString(), parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
			 menuPage.logout();
			 loginPage.loginUser(parameters.get(4), parameters.get(5));		
			 verifyDrillDownTable(reviewId,"In progress", parameters.get(6)); 	
			 ControlHandling.click(drilldownPage.review_column.get(ControlHandling.getTablerow(drilldownPage.drilldown_table,reviewId)), Test, "Review");
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 parentwindow= Driverclass.driver.getWindowHandle();
			 ControlHandling.switchToChild(parentwindow,Test);
			 reviewPage.clickTakeOver();
			 driver.close();
			 ControlHandling.switchToParent(parentwindow,Test);
			 verifyDrillDownTable(reviewId,"In progress", parameters.get(7)); 
			 menuPage.clickOnLogo();
			 ControlHandling.Assertion(homePage.header.getText().toString(), "My reviews", Test, "My Review header is displayed");
			 ControlHandling.waitForElement(homePage.reviewstable);
			 ControlHandling.tableAssertion(homePage.reviewstable,reviewId, Test);
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());		 		
		 		ExceptionScreenshot.getScreenshot();
		 	} 
	 }
	 
	 public void verifyDrillDownTable(String reviewID, String status, String assuser) throws IOException, InterruptedException
	 {		 
		 menuPage.clickdrilldown();
		 drilldownPage.mouseOverSidebar();	
		 drilldownPage.clickMainOrganisation();		
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 //drilldownPage.clickSearch(reviewID);
		 ControlHandling.click(drilldownPage.clicktoload, Test, "click to load");
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		 Thread.sleep(3000);
		 ControlHandling.waitForElement(drilldownPage.drilldown_table);
		 Thread.sleep(1000);
		 ControlHandling.tableAssertion(drilldownPage.drilldown_table, reviewID, Test);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 int row=ControlHandling.getTablerow(drilldownPage.drilldown_table, reviewID);
		 ControlHandling.Assertion(drilldownPage.assigned_column.get(row).getText().toString(), assuser, Test, "Assigned user");	
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 ControlHandling.Assertion(drilldownPage.status_column.get(row).getText().toString(), status, Test, "status column");	
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);		
	 }
	
	//Parameterized main Organisation names, sub Organisation names
	 public void displayMainOrganisationReview(String parameter) throws IOException 
	 {
		 try
		 {
						 
			String mainOrg=parameter.substring(0, 15);
			String subOrg= parameter.substring(parameter.indexOf(",")+1);				
			//Drilldown - Access to the project I am authorized to
			 menuPage.clickdrilldown();
			 drilldownPage.mouseOverSidebar();		 
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 ControlHandling.Assertion(drilldownPage.mainorganisation.getText().toString(), mainOrg, Test, "Bank of Ireland");
			 ControlHandling.listOfAssertion(drilldownPage.subOrganisation, subOrg, Test);
			 
			 //Drilldown - Reviews display main organisation
			 drilldownPage.clickMainOrganisation();
			 ControlHandling.Assertion(drilldownPage.title.getText().toString(), "Drilldown grid for "+mainOrg+" parties", Test, drilldownPage.title.getText().toString());
			 ControlHandling.elementIsDisplayedAssertion(drilldownPage.clicktoload, Test, "click to load data");
			 ControlHandling.click(drilldownPage.clicktoload, Test, "Click to load data");
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 ControlHandling.listOfAssertion(drilldownPage.table_hdr, "Sub,Party ID,External ID,Party Name,Party type,Party added on,Current review,External Review ID,Current review type,Date current review started,Current review step,Last review status,Assigned user,Last manual risk,Last automated risk,Status", Test);
			 
			 //Drilldown - Review ID redirection
			 reviewId=drilldownPage.review_column.get(0).getText().toString();
			 ControlHandling.click(drilldownPage.review_column.get(0), Test, "review");
			 parentwindow= Driverclass.driver.getWindowHandle();
			 ControlHandling.switchToChild(parentwindow,Test);
			 List<String> text=ControlHandling.splitTextWithSpace(reviewPage.header.getText().toString(),"\\s+");
			 reviewId= text.get(1).toString();
			 ControlHandling.Assertion(text.get(1).toString(), reviewId, Test, "review id");
			 driver.close();
			 ControlHandling.switchToParent(parentwindow,Test);	
			 
			//Drilldown - Parties display main organisation
			 drilldownPage.mouseOverSidebar();
			 drilldownPage.clickMainOrganisation();
			 ControlHandling.click(drilldownPage.parties_btn, Test, "Parties");
			 ControlHandling.elementIsDisplayedAssertion(drilldownPage.clicktoload, Test, "click to load all the data");
			 ControlHandling.click(drilldownPage.clicktoload, Test, "click to load all the data");
			 ControlHandling.listOfAssertion(drilldownPage.table_hdr, "Sub,Party ID,External ID,Party Name,Party type,Party added on,Current review,External Review ID,Current review type,Date current review started,Current review step,Last review status,Assigned user,Last manual risk,Last automated risk,Status", Test);
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 Report.flush();
			 
			 // Review ID redirection from parties view
			 reviewId=drilldownPage.review_column.get(0).getText().toString();
			 ControlHandling.click(drilldownPage.review_column.get(0), Test, "review");
			 parentwindow= Driverclass.driver.getWindowHandle();
			 ControlHandling.switchToChild(parentwindow,Test);
			 List<String> text1=ControlHandling.splitTextWithSpace(reviewPage.header.getText().toString(),"\\s+");
			 reviewId= text1.get(1).toString();
			 ControlHandling.Assertion(text.get(1).toString(), reviewId, Test, "review id");
			 driver.close();
			 ControlHandling.switchToParent(parentwindow,Test);
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());		 		
		 		ExceptionScreenshot.getScreenshot();
		 	} 
	 }
	 
	//Parameterized sub organisation name, project name
	 public void displayProjectReview(String parameter) throws IOException
	 {
		 try
		 {
			 parameters=ControlHandling.getParameters(parameter);
			 //Drilldown - Reviews in an organisation
			 menuPage.clickdrilldown();
			 drilldownPage.mouseOverSidebar();
			 drilldownPage.clicksidebarpin();
			// drilldownPage.listOfClick(drilldownPage.suborg_expand);	 
			 Thread.sleep(1000);			 		
			// driver.navigate().refresh();
			 
			 drilldownPage.chooseOrganisation(parameters.get(0));
			 JavascriptExecutor je = (JavascriptExecutor)driver;
			 je.executeScript("return document.readyState")
			            .toString().equals("complete");
			 //driver.navigate().refresh();
			 Thread.sleep(1000);
			 ControlHandling.handlingStaleElement(drilldownPage.title);
			 ControlHandling.waitForElement(drilldownPage.title);
			 ControlHandling.Assertion(drilldownPage.title.getText().toString(), "Drilldown grid for "+parameters.get(0)+" parties", Test, "title");
			 ControlHandling.elementIsDisplayedAssertion(drilldownPage.clicktoload, Test, "click to load all the data");
			 ControlHandling.click(drilldownPage.clicktoload, Test, "click to load all the data");
			 ControlHandling.click(drilldownPage.showCol_btn, Test, "show column");
			 ControlHandling.click(drilldownPage.orgcol_btn, Test, "organisation column");
			 ControlHandling.click(drilldownPage.hideCol_btn, Test, "hide column");
			 Thread.sleep(1000);
			 ControlHandling.elementIsDisplayedAssertion(drilldownPage.org_hdr, Test, "Organisatiom header");			 
			 ControlHandling.tableAssertion(drilldownPage.drilldown_table, parameters.get(0), Test);
			 Thread.sleep(1000);
			 
			 //Drilldown - Reviews in a project						 
			 drilldownPage.chooseProject(parameters.get(1));
			 ControlHandling.Assertion(drilldownPage.title.getText().toString(), "Drilldown grid for "+ parameters.get(0)+" parties in project: "+ parameters.get(1), Test, "title");
			 ControlHandling.elementIsDisplayedAssertion(drilldownPage.clicktoload, Test, "click to load all the data");
			 ControlHandling.click(drilldownPage.clicktoload, Test, "click to load all the data");
			 ControlHandling.click(drilldownPage.showCol_btn, Test, "show column");
			 ControlHandling.click(drilldownPage.projcol_btn, Test, "Project column");
			 ControlHandling.click(drilldownPage.hideCol_btn, Test, "hide column");
			 Thread.sleep(1000);
			 ControlHandling.elementIsDisplayedAssertion(drilldownPage.proj_hdr, Test, "project header");
			 ControlHandling.tableAssertion(drilldownPage.drilldown_table, parameters.get(1), Test);
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 Report.flush();
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());		 		
 		 		ExceptionScreenshot.getScreenshot();
		 	} 
	 }
	 
	 public void validationOfSidebarPin_treeview() throws IOException
	 {
		 
		 try
		 {
			
			 menuPage.clickdrilldown();
			 //Drill down - side bar pinning
			 drilldownPage.mouseOverSidebar();
			 drilldownPage.clicksidebarpin();
			 drilldownPage.mouseOverSidebar();
			 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			 ControlHandling.elementIsDisplayedAssertion(drilldownPage.sidebar_pin, Test, "side bar pin");
			 
			// Drill Down - Organisation tree view			 
			 drilldownPage.SubOrganisationIsPresent(drilldownPage.subOrganisation);
			 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
			 // Drilldown - Projects tree view
			 ControlHandling.click(drilldownPage.mainorganisation, Test, "main organisation");
			 Thread.sleep(1000);
			 ControlHandling.click(drilldownPage.mainorg_expand, Test, "main organisation expand");
			 Thread.sleep(1000);			
			 drilldownPage.listOfClick(drilldownPage.suborg_expand);	 
			 Thread.sleep(1000);			 			 
			 drilldownPage.listOfClick(drilldownPage.proj_expand);		
			 Thread.sleep(1000);			 
			 drilldownPage.listOfClick(drilldownPage.projOfProj_expand);
			 Thread.sleep(1000);			 
			 Report.flush();			 
			 
			 //Drilldown - Pinned sidebar scrolling
			 ControlHandling.scrolltoElement(drilldownPage.sidebar_pin);
			 ControlHandling.click(drilldownPage.sidebar_pin, Test, "Pinned side bar");
			 drilldownPage.mouseOverSidebar();
			 ControlHandling.mouseOver(drilldownPage.startNewReview_btn, "start new review", Test);
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 Report.flush();
			 
		 }
		 
		 catch(Exception e)
		 	{
		 		Test.log(Status.FAIL, e.toString());		 		
		 		ExceptionScreenshot.getScreenshot();
		 	} 
	 }
	
}
