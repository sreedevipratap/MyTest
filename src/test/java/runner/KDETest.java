package runner;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Report.ExtentManager;
import excelRead.Playlist;
import excelRead.PropertyFileReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
//Set 1 Commit
public class KDETest {
	
	WebDriver driver;
	ExtentReports Report;
	ExtentTest Test;
	
  @Test
  public void ff() throws InterruptedException
  {
	  Playlist pl= new Playlist(driver,Report,Test);
	  pl.Plistcall("Plist");
	//cn1.excelcall("Drill Down");
	
	//cn1.excelcall("Sheet3");
	Thread.sleep(6000);
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() throws InterruptedException, IOException {
	  
	  PropertyFileReader.property();
	  System.setProperty(PropertyFileReader.getChrome(),PropertyFileReader.getChromePath());
	  /*System.setProperty(chrome,ChromePath);--PropertyFileREader.java
	   * System.setProperty(webdriver.chrome.driver,D\://chromedriver.exe)--from the Config.properties file */
	  
	 // System.setProperty(PropertyFileReader.getPath(),PropertyFileReader.getHappypathSheet());
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();	
	  Report=ExtentManager.getInstance();
	  Thread.sleep(1000);
	  driver.navigate().to(PropertyFileReader.getURL());
	  //https\://test.cdduk.kycnet.com/
	  Thread.sleep(5000);
  }

  @AfterSuite
  public void afterSuite() {
	  driver.close();
	  driver.quit();
  }

}
