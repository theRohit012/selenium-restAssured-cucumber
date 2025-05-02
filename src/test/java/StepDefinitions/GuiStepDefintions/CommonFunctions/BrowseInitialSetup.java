package StepDefinitions.GuiStepDefintions.CommonFunctions;

import ApplicationHook.AppHook;
import CommonUtilityFunctions.GlobalVariablePlaceHolder;
import GUI_Functions.BaseClass.GuiBaseClass;
import io.cucumber.java.en.Given;

public class BrowseInitialSetup extends GuiBaseClass {

    @Given("I am launching a browser in mode: {string} and opening an {string} url")
    public void launchBrowserAndLoadURL(String runMode,String URL){
        runMode = GlobalVariablePlaceHolder.resolveGlobalVariables(runMode);
        URL = GlobalVariablePlaceHolder.resolveGlobalVariables(URL);
        String browser = System.getProperty("browser");
        AppHook.getTest().info("I am invoking a browser: '"+browser +"' in '"+runMode+"' mode and opening an '"+URL+"' url using " + (prop.getProperty("grid").equalsIgnoreCase("Yes") ? "'GRID'" : "'WebDriver'" ));
        invokeBrowser(browser);
    }

    @Given("I am navigating to {string} URL")
    public void navigateToAURL(String URL){
        URL = GlobalVariablePlaceHolder.resolveGlobalVariables(URL);
        AppHook.getTest().info("I am navigating to '"+URL+"' URL");
        navigateToUrl(URL);
    }
}
