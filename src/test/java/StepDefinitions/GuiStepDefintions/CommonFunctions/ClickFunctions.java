package StepDefinitions.GuiStepDefintions.CommonFunctions;

import ApplicationHook.AppHook;
import GUI_Functions.BaseClass.GuiBaseClass;
import GUI_Functions.UtilityFunctions.GuiUtilFunctions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClickFunctions extends GuiBaseClass {

    private static final Logger logger = LogManager.getLogger(ClickFunctions.class);

    @When("I click on following elements on {string}")
    public void clickOnMultipleElementsUsingDataTable(String className, DataTable data){
        AppHook.getTest().info ("I click on  '" + data.asList()+"' elements on  '"+className+"'");
        List<String> list = data.asList();
        for(String elementFieldName : list){
            GuiUtilFunctions.clickOnElement(loadWebElement(elementFieldName,className),logger);
        }
    }

    @When("I click on {string} element on {string}")
    public void clickOnSingleElement(String element, String className){
        GuiUtilFunctions.clickOnElement(loadWebElement(element,className),logger);
        AppHook.getTest().info ("I click on  '" + element+"' element on  '"+className+"'");
    }

    @When("I click on following elements on {string} using visible text")
    public void clickOnMultipleElementUsingVisibleText(String webPage, DataTable data){
        AppHook.getTest().info ("I click on  '" + data.asList()+"' elements on  '"+webPage+"'");
        List<String> list = data.asList();
        for(String visibleText : list){
            GuiUtilFunctions.clickOnElement(GuiUtilFunctions.getElementUsingVisibleText(visibleText,logger),logger);
        }
    }

    @When("I click on {string} element on {string} using visible Text")
    public void clickOnSingleElementUsingVisibleText(String visibleText, String webPage){
        AppHook.getTest().info ("I click on  '" + visibleText+"' visible on  '"+webPage+"'");
        GuiUtilFunctions.clickOnElement(GuiUtilFunctions.getElementUsingVisibleText(visibleText,logger),logger);
    }
}
