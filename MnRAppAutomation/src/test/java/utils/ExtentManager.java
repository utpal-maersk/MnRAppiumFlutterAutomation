package utils;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {
	
	//public BaseClass baseClass = new BaseClass();
	
	private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter;
    
    public Constants constants = new Constants();
    
    private static String filePath = System.getProperty("user.dir")+"/TestReports/"+DriverTestCase.str_testReportFolderName+"/MaerskAutomationTestReport.html";
  
    
    
	public static ExtentReports GetExtent(){
        if (extent != null)
                   return extent; //avoid creating new instance of html file
               extent = new ExtentReports();        
        extent.attachReporter(getHtmlReporter());
        extent.setSystemInfo("OS", "macOS Catalina");
        extent.setSystemInfo("Domain Name", DriverTestCase.selectedDomain);
        extent.setSystemInfo("Device Name - Android", DriverTestCase.deviceName_android);
        extent.setSystemInfo("Device UDID - Android", DriverTestCase.deviceUDID_android);
        extent.setSystemInfo("Device Name - iOS", DriverTestCase.deviceName_iOS);
        extent.setSystemInfo("Device UDID - iOS", DriverTestCase.deviceUDID_iOS);
        extent.setSystemInfo("App name - Android", DriverTestCase.appName_android);
        extent.setSystemInfo("App name - iOS", DriverTestCase.appName_iOS);
        extent.setSystemInfo("Developed By", "Ahmer Hashmi");
        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
               htmlReporter = new ExtentHtmlReporter(filePath);
               htmlReporter.loadConfig("./extent-config.xml");
               htmlReporter.config().setChartVisibilityOnOpen(true);
               htmlReporter.config().setDocumentTitle("Maersk Mobile Test Automation Report");
               htmlReporter.config().setReportName("Testing cycle");
               htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
               htmlReporter.config().setTheme(Theme.STANDARD);
               
               return htmlReporter;
    }
    
    public static ExtentTest createTest(String name, String description){
        test = extent.createTest(name, description);
        return test;
    }
    

}
