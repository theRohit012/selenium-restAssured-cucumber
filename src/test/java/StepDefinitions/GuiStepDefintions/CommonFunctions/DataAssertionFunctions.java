package StepDefinitions.GuiStepDefintions.CommonFunctions;

import ApplicationHook.AppHook;
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
        AppHook.getTest().info ("Verify '" + data.asList()+"' texts are visible on '"+webPage+"'");
        for(String elementFieldName : list){
            GuiUtilFunctions.elementIsDisplayed(GuiUtilFunctions.getElementUsingVisibleText(elementFieldName,logger),logger);
        }
    }

    @Then("Verify {string} element is displayed on {string}")
    public void elementIsDisplayed(String element, String className){
        GuiUtilFunctions.elementIsDisplayed(loadWebElement(element,className),logger);
        AppHook.getTest().info (" Verify '"+element+"' element is displayed on '"+className + "'");
    }

    @When("Verify following elements are displayed on {string}")
    public void verifyMultipleElementsAreDisplayedUsingDataTable(String className, DataTable data){
        AppHook.getTest().info ("Verify '" + data.asList()+"' elements are visible on '"+className+"'");
        List<String> list = data.asList();
        for(String elementFieldName : list){
            GuiUtilFunctions.elementIsDisplayed(loadWebElement(elementFieldName,className),logger);
        }
    }

    @Then("Verify {string} element on {string} must be equal to {string}")
    public void elementTextMustBeEqualTo(String element, String className,String expectedText){
        AppHook.getTest().info ("Verify '" + element+"' elements on '"+className+"' must be equal to '" + expectedText);
        String actual = GuiUtilFunctions.getElementText(loadWebElement(element,className),logger);
        Assert.assertEquals(actual,expectedText);
    }

    @Then("Verify Page title must be equal to {string}")
    public void verifyPageTitle(String pageTitle){
        AppHook.getTest().info ("Verify Page title must be equal to '" + pageTitle);
        String actual = GuiUtilFunctions.getPageTitle(logger);
        Assert.assertEquals(actual,pageTitle);
    }

}
