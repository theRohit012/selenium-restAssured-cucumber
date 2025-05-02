package CommonUtilityFunctions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setReportName("Cucumber Automation Report");
            reporter.config().setDocumentTitle("Test Execution");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // 🟢 Inject custom info
            extent.setSystemInfo("Browser", System.getProperty("browser"));
            extent.setSystemInfo("Base URL", ConfigReader.configProp.getProperty("url"));
            extent.setSystemInfo("run Mode", ConfigReader.configProp.getProperty("headless"));
            extent.setSystemInfo("grid", ConfigReader.configProp.getProperty("grid"));
        }
        return extent;
    }
}

