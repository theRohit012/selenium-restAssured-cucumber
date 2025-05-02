package StepDefinitions.GuiStepDefintions.CommonFunctions;

import ApplicationHook.AppHook;
import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataHandlingAndInput extends GuiBaseClass {
    private static final Logger logger = LogManager.getLogger(DataHandlingAndInput.class);

    @When("I am entering the {string} on {string} element on {string}")
    public void enterText(String inputText, String elementName, String className){
        AppHook.getTest().info ("I am entering the "+inputText+" on "+elementName+" element on "+className);
        GuiUtilFunctions.dataInputToATextBox(inputText,loadWebElement(elementName,className),logger);
    }
}
