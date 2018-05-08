package Report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
//Set 1 Commit
public class Screenshot {
	
	public static String takeScreenshot(WebDriver driver,String filename) throws IOException
	{
		filename=filename+".png";
		String directory="C://Automation//Selenium//workspace//CDDUK";
		File sourcefile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourcefile, new File(directory+filename));
		String dest=directory+filename;
		return dest;
	}

}
