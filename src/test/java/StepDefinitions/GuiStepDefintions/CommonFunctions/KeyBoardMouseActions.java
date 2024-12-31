package StepDefinitions.GuiStepDefintions.CommonFunctions;

import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeyBoardMouseActions extends GuiBaseClass {
    private static final Logger logger = LogManager.getLogger(KeyBoardMouseActions.class);

    @When("Move the cursor to {string} element on {string}")
    public void moveToAElement(String element,String className){
        GuiUtilFunctions.moveToTheElement(loadWebElement(element,className),logger);
    }
}
