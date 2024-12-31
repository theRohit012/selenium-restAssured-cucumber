package StepDefinitions.GuiStepDefintions.CommonFunctions;

import CommonUtilityFunctions.GlobalVariablePlaceHolder;
import GUI_Functions.BaseClass.GuiBaseClass;
import io.cucumber.java.en.Given;

public class BrowseInitialSetup extends GuiBaseClass {

    @Given("I am launching a browser: {string} in mode: {} and opening an {string} url")
    public void launchBrowserAndLoadURL(String browser,String runMode,String URL){
        browser = GlobalVariablePlaceHolder.resolveGlobalVariables(browser);
        runMode = GlobalVariablePlaceHolder.resolveGlobalVariables(runMode);
        URL = GlobalVariablePlaceHolder.resolveGlobalVariables(URL);

        invokeBrowser(browser,runMode,URL);
    }

    @Given("I am navigating to {} URL")
    public void navigateToAURL(String URL){
        URL = GlobalVariablePlaceHolder.resolveGlobalVariables(URL);
        navigateToUrl(URL);
    }
}
