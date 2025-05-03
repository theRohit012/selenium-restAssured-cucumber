package ApplicationHook;

import API_Functions.BaseClass.ApiBaseClass;
import CommonUtilityFunctions.ConfigReader;
import CommonUtilityFunctions.EmailUtility;
import CommonUtilityFunctions.ExtentReportManager;
import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AppHook extends GuiBaseClass {

    private static final Logger logger = LogManager.getLogger(AppHook.class);
    ApiBaseClass apiBaseClass = new ApiBaseClass();
    static ExtentReports extent = ExtentReportManager.getInstance();
    static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    /**
     * @param scenario
     * @method to create the test case for extent report
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        testThread.set(test);
    }

    /**
     * @param scenario
     * @method to update the test status
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            testThread.get().fail("Step failed");
        } else {
            testThread.get().pass("Step passed");
        }
    }

    /**
     * @param scenario
     * @method to close the browser after the execution
     */
    @After
    public void closeBrowser(Scenario scenario) {
        if (driver.get() != null) {
            if (scenario.isFailed()) {
                GuiUtilFunctions.takePageScreenshot(System.getProperty("user.dir") + "/TestScreenshots/"
                        + System.currentTimeMillis() + ".png", logger);
            }
            driver.get().quit();
        }
    }

    @After
    public void afterScenario() {
        extent.flush();
    }

    /**
     * @method to share the report
     */
    @AfterAll
    public static void tearDown() {
        if (prop.getProperty("shareReportOnEmail").equalsIgnoreCase("Yes")) {

            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

            EmailUtility.sendEmailWithAttachment(
                    prop.getProperty("receiverEmail"),
                    "Automation Test Report",
                    "Please find the attached Extent Report for the latest test run.",
                    reportPath
            );
        }
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }
}

