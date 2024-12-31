package StepDefinitions.GuiStepDefintions.CommonFunctions;

import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataHandlingAndInput extends GuiBaseClass {
    private static final Logger logger = LogManager.getLogger(DataHandlingAndInput.class);

    @When("I am entering the {string} on {} element on {}")
    public void enterText(String inputText, String elementName, String className){
        GuiUtilFunctions.dataInputToATextBox(inputText,loadWebElement(elementName,className),logger);
    }
}
