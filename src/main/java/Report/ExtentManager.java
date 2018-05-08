package Report;
//Set 1 Commit
//import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.TestAttributeTestContextProvider;
import com.aventstack.extentreports.configuration.ConfigMap;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    
    private static ExtentReports Report;
    
   public static ExtentReports getInstance() {
    	if (Report == null)
    		createInstance("./test-output/KYC Report.html");
    	
        return Report;
    }
    
    public static ExtentReports createInstance(String filename) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Kyc Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("KYC Automation Report");
        Class<? extends ExtentHtmlReporter> c=htmlReporter.getClass();
        TestAttributeTestContextProvider<Category> b3=htmlReporter.getCategoryContextInfo();
        ConfigMap b4=htmlReporter.getConfigContext();
        String b="Test";
        String a=htmlReporter.config().getCSS();
        htmlReporter.config().setCSS(b);       
        Report = new ExtentReports();
        Report.attachReporter(htmlReporter);        
        return Report;
    }
}

