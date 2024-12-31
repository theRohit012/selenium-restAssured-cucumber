package ApplicationHook;

import API_Functions.BaseClass.ApiBaseClass;
import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AppHook extends GuiBaseClass {

    private static final Logger logger = LogManager.getLogger(AppHook.class);
    ApiBaseClass apiBaseClass = new ApiBaseClass();

    /**
     * @method to close the browser after the execution
     * @param scenario
     */
    @After
    public void closeBrowser(Scenario scenario){
        if(driver != null){
            if(scenario.isFailed()){
                GuiUtilFunctions.takePageScreenshot(System.getProperty("user.dir") + "/TestScreenshots/"
                + System.currentTimeMillis()+ ".png",logger);
            }
            driver.quit();
        }
    }
}

