package GUI_Functions.UtilityFunctions;

import GUI_Functions.BaseClass.GuiBaseClass;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GuiUtilFunctions extends GuiBaseClass {
    public static JavascriptExecutor jse;
    public static Actions actions;

    /**
     * @param element
     * @method To verify whether element is displayed or not
     */
    public static void elementIsDisplayed(WebElement element,Logger logger) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.info("{} is displayed and ready for interaction", element);
    }

    /**
     * Method to verify the invisibility of an element
     * @param element
     */
    public static void validateElementInvisibility(WebElement element,Logger logger){
        wait.until(ExpectedConditions.invisibilityOf(element));
        logger.info("{} is invisible and not present on page", element);
    }

    /**
     * @param element
     * @param selectBy
     * @param obj
     * @method select the value from DropDwon
     */
    public static void selectValueFromDropDown(WebElement element, String selectBy, Object obj,Logger logger) {
        elementIsDisplayed(element, logger);
        Select select = new Select(element);

        switch (selectBy) {
            case "value":
                select.selectByValue(obj.toString());
                logger.info("Value: {} selected from Dropdown for element:  {}", obj.toString(), element.toString());
                break;
            case "index":
                select.selectByIndex((Integer) obj);
                logger.info("Index: {} selected from Dropdown element:  {}", (Integer) obj, element.toString());
                break;
            case "visibleText":
                select.selectByVisibleText(obj.toString());
                logger.info("Visible Text: {} selected from Dropdown element: {}", obj.toString(), element.toString());
                break;
            case "containsVisibleText":
                select.selectByContainsVisibleText(obj.toString());
                logger.info("Visible Text Contains: {} selected from Dropdown element: {}", obj.toString(), element.toString());
                break;
            default:
                assertThat("Action not found", true, is(false));
                break;

        }
    }

    /**
     * Method Deselect values from dropdown
     * @param element
     * @param deSelectBy
     * @param obj
     */
    public static void deSelectValueFromDropDown(WebElement element, String deSelectBy, Object obj,Logger logger) {
        elementIsDisplayed(element,logger);
        Select deSelect = new Select(element);

        switch (deSelectBy) {
            case "value":
                deSelect.deselectByValue(obj.toString());
                logger.info("Value: {} deSelected from Dropdown for element:  {}", obj.toString(), element.toString());
                break;
            case "index":
                deSelect.deselectByIndex((Integer) obj);
                logger.info("Index: {} deSelected from Dropdown element:  {}", (Integer) obj, element.toString());
                break;
            case "visibleText":
                deSelect.deselectByVisibleText(obj.toString());
                logger.info("Visible Text: {} deSelected from Dropdown element: {}", obj.toString(), element.toString());
                break;
            case "containsVisibleText":
                deSelect.deSelectByContainsVisibleText(obj.toString());
                logger.info("Visible Text Contains: {} deSelected from Dropdown element: {}", obj.toString(), element.toString());
                break;
            case "ALL":
                deSelect.deselectAll();
                logger.info("Deselected ALL for element: {}", element.toString());
            default:
                assertThat("Action not found", true, is(false));
                break;
        }
    }

    /**
     * Method to click on element
     * @param element
     */
    public static void clickOnElement(WebElement element,Logger logger){
        elementIsDisplayed(element,logger);
        element.click();
        logger.info("{} element clicked",element.toString());
    }

    /**
     * @method to click on element using javascriptexector
     * @param element
     */
    public static void clickOnElementUsingJSE(WebElement element,Logger logger){
        jse = (JavascriptExecutor) driver;
        elementIsDisplayed(element,logger);
        jse.executeScript("argument[0].click();",element);
        logger.info("{} element clicked using JavaScriptExecutor",element.toString());
    }

    /**
     * Method to switch to child window and return main window ID
     * @return
     */
    public static String switchToWindow(){
        String mainWindow = driver.getWindowHandle();

        Set<String> windows = driver.getWindowHandles();

        for (String currentWindow : windows) {
            if (!mainWindow.equalsIgnoreCase(currentWindow)) {
                driver.switchTo().window(currentWindow);
                break;
            }
        }
        return mainWindow;
    }

    /**
     * Method to swtich to window when name is known
     * @param mainWindow
     */
    public static void switchToWindowUsingName(String mainWindow){
        driver.switchTo().window(mainWindow);
    }

    /**
     * @method to capture a screenshot of and page
     * @param location
     */
    public static void takePageScreenshot(String location,Logger logger){
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(srcFile, new File(location));
            logger.info("Screenshot capture and stored at {}", location);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @method to get the title of a page
     * @return
     */
    public static String getPageTitle(Logger logger){
        String title =  "";

        title = driver.getTitle();
        logger.info("Title of the page has been found as : {}", title);

        return title;
    }

    /**
     * @method to get a text of an element
     * @param element
     * @return
     */
    public static String getElementText(WebElement element,Logger logger){
        String elementText = "";

        elementIsDisplayed(element,logger);
        elementText = element.getText();
        logger.info("Text is fetched for element: {}", element.toString());

        return elementText;
    }

    /**
     * method created to fetch the attribute value
     * @param attributeName
     * @param element
     * @return
     */
    public static String getParameter(String attributeName, WebElement element,Logger logger){
        String param = "";

        elementIsDisplayed(element,logger);
        param = element.getAttribute(attributeName);
        logger.info("{}attribute of an element: {} has fetched",attributeName,element.toString());

        return param;

    }

    /**
     * @method enter a data into the text box
     * @param text
     * @param element
     */
    public static void dataInputToATextBox(String text, WebElement element,Logger logger){
        elementIsDisplayed(element,logger);
        element.sendKeys(text);
        logger.info("{} has entered into the text box whose element is {}",text,element.toString());
    }

    /**
     * @method data input into a text box using Javascriptexecutor
     * @param text
     * @param element
     */
    public static void dataInputToATextBoxUsingJS(String text, WebElement element,Logger logger){
        jse = (JavascriptExecutor) driver;
        elementIsDisplayed(element,logger);
        jse.executeScript("argument[0].value='"+text+"';",element);
        logger.info("{} has entered into the text box whose element is {} using JS",text,element.toString());

    }

    /**
     * @method swtich to a frame
     * @param element
     */
    public static void switchToFrameUsingWebElement(WebElement element,Logger logger){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        logger.info("switched to frame {}", element.toString());
    }

    /**
     * @method Switch back the default content
     */
    public static void switchToDefaultContent(Logger logger){
        driver.switchTo().defaultContent();
        logger.info("Switch out of frame");
    }

    /**
     * @method scroll to the element
     * @param element
     */
    public static void scrollToAView(WebElement element,Logger logger){
        jse = (JavascriptExecutor) driver;
        jse.executeScript("argument[0].scrollIntoView()", element);
        logger.info("Scroll into the view: {}", element.toString());
    }

    /**
     * @method move to an element
     * @param element
     */
    public static void moveToTheElement(WebElement element,Logger logger){
        actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        logger.info("Move to an element: {}", element.toString());
    }

    /**
     * @method wait for a fixed interval
     * @param seconds
     */
    public static void waitForFixedInterval(long seconds){
        try {
            Thread.sleep(1000*seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @method to get a text out of alert
     * @return
     */
    public static String getAlertText(Logger logger){
        String alertMessage = "";
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alertMessage = alert.getText();
        logger.info("Alert text has been found");

        return alertMessage;
    }

    /**
     * @method to accept a alert
     */
    public static void acceptAlert(Logger logger){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        logger.info("Alert has been accepted");
    }

    /**
     * @method to dismiss a alert
     */
    public static void dismissAlert(Logger logger){
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        logger.info("Alert has been dismissed");
    }

    /**
     * @method to scroll to the end of the page
     */
    public static void scrollToEndOfPage(){
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)","");
    }

    /**
     * @method get an element using the visible text
     * @param text
     * @return
     */
    public static WebElement getElementUsingVisibleText(String text,Logger logger){
        WebElement element =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+text+"']")));
        logger.info("element: {} has been fetch using visible text: {}", element.toString(), text);
        return element;
    }
}
