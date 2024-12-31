package StepDefinitions.GuiStepDefintions.CommonFunctions;

import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WindowActions extends GuiBaseClass {
    private static final Logger logger = LogManager.getLogger(WindowActions.class);

    @When("Scroll the web page to the {string} element on {string}")
    public void scrollInTheWebPage(String element, String className){
        GuiUtilFunctions.scrollToAView(loadWebElement(element,className),logger);
    }

    @When("Take a screenshot and store at {string}")
    public void takeScreenshot(String location){
        GuiUtilFunctions.takePageScreenshot(location,logger);
    }

    @When("I am switching to a {string} frame on {string}")
    public void switchToFrame(String element,String className){
        GuiUtilFunctions.switchToFrameUsingWebElement(loadWebElement(element,className),logger);
    }

    @When("I am coming out of frame")
    public void comingOutOfFrame(){
        GuiUtilFunctions.switchToDefaultContent(logger);
    }
}
