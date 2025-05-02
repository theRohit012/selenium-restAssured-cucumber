package StepDefinitions.GuiStepDefintions.CommonFunctions;

import ApplicationHook.AppHook;
import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WindowActions extends GuiBaseClass {
    private static final Logger logger = LogManager.getLogger(WindowActions.class);

    @When("Scroll the web page to the {string} element on {string}")
    public void scrollInTheWebPage(String element, String className){
        AppHook.getTest().info ("Scroll the web page to the "+element+" element on "+className);
        GuiUtilFunctions.scrollToAView(loadWebElement(element,className),logger);
    }

    @When("Take a screenshot and store at {string}")
    public void takeScreenshot(String location){
        AppHook.getTest().info ("Take a screenshot and store at "+location);
        GuiUtilFunctions.takePageScreenshot(location,logger);
    }

    @When("I am switching to a {string} frame on {string}")
    public void switchToFrame(String element,String className){
        AppHook.getTest().info ("I am switching to a "+element+" frame on " + className);
        GuiUtilFunctions.switchToFrameUsingWebElement(loadWebElement(element,className),logger);
    }

    @When("I am coming out of frame")
    public void comingOutOfFrame(){
        AppHook.getTest().info ("I am coming out of frame");
        GuiUtilFunctions.switchToDefaultContent(logger);
    }
}
