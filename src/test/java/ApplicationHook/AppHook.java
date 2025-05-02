package ApplicationHook;

import API_Functions.BaseClass.ApiBaseClass;
import CommonUtilityFunctions.ExtentReportManager;
import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;

public class AppHook extends GuiBaseClass {

    private static final Logger logger = LogManager.getLogger(AppHook.class);
    ApiBaseClass apiBaseClass = new ApiBaseClass();
    static ExtentReports extent = ExtentReportManager.getInstance();
    static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    /**
     * @method to close the browser after the execution
     * @param scenario
     */
    @After
    public void closeBrowser(Scenario scenario){
        if(driver.get() != null){
            if(scenario.isFailed()){
                GuiUtilFunctions.takePageScreenshot(System.getProperty("user.dir") + "/TestScreenshots/"
                + System.currentTimeMillis()+ ".png",logger);
            }
            driver.get().quit();
        }
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        testThread.set(test);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            testThread.get().fail("Step failed");
        } else {
            testThread.get().pass("Step passed");
        }
    }

    @After
    public void afterScenario() {
        extent.flush();
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }
}

