package StepDefinitions.GuiStepDefintions.CommonFunctions;

import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class DataAssertionFunctions extends GuiBaseClass {
    private static final Logger logger = LogManager.getLogger(DataAssertionFunctions.class);

    @Then("Verify {string} text on a {string}")
    public void verifyText(String visibleText, String webPage){
        GuiUtilFunctions.elementIsDisplayed(GuiUtilFunctions.getElementUsingVisibleText(visibleText,logger),logger);
    }

    @Then("Verify following texts are visible on {string}")
    public void verifyLinkText(String webPage, DataTable data){
        List<String> list = data.asList();
        for(String elementFieldName : list){
            GuiUtilFunctions.elementIsDisplayed(GuiUtilFunctions.getElementUsingVisibleText(elementFieldName,logger),logger);
        }
    }

    @Then("Verify {string} element is displayed on {string}")
    public void elementIsDisplayed(String element, String className){
        GuiUtilFunctions.elementIsDisplayed(loadWebElement(element,className),logger);
    }

    @When("Verify following elements are displayed on {string}")
    public void verifyMultipleElementsAreDisplayedUsingDataTable(String className, DataTable data){
        List<String> list = data.asList();
        for(String elementFieldName : list){
            GuiUtilFunctions.elementIsDisplayed(loadWebElement(elementFieldName,className),logger);
        }
    }

    @Then("Verify {string} element on {string} must be equal to")
    public void elementTextMustBeEqualTo(String element, String className,String expectedText){
        String actual = GuiUtilFunctions.getElementText(loadWebElement(element,className),logger);
        Assert.assertEquals(actual,expectedText);
    }

    @Then("Verify Page title must be equal to {string}")
    public void verifyPageTitle(String pageTitle){
        String actual = GuiUtilFunctions.getPageTitle(logger);
        Assert.assertEquals(actual,pageTitle);
    }

}
