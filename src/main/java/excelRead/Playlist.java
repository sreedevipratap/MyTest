package excelRead;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//Set 1 Commit
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utilities.Driverclass;
import keyword.ColumnName;

public class Playlist extends Driverclass {
	ColumnName cn=new ColumnName(driver,Report,Test);
	
	public Playlist(WebDriver driver, ExtentReports Report, ExtentTest Test)
	{
		
		super(driver, Report, Test);
	}
	public void Plistcall(String Sheet)
	{
		
		cn.excelcall(Sheet,PropertyFileReader.Playlist());
			  
	}
	public void Plistcall1(String Sheet,String Method,String Status) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, InterruptedException
	{
		if (Status.equalsIgnoreCase("Ready"))
		{
			cn.calltestmethod(Sheet,Method);
			PropertyFileReader.deleteProperty();
		}
		else if(Status.equalsIgnoreCase("Skip"))
		{
			Test=Report.createTest(Method);
			Test.log(com.aventstack.extentreports.Status.SKIP, "Skipped"+Method);
			
		}
		
	}
}