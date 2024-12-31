package GUI_Functions.BaseClass;

import CommonUtilityFunctions.ConfigReader;
import CommonUtilityFunctions.Constant;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class GuiBaseClass {
    public static WebDriver driver;
    public static Properties prop = null;
    public static String folderName = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());
    private static final String UI_PAGE_OBJECT_PACKAGE = "GUI_Functions.ObjectRepository.";
    public static WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(GuiBaseClass.class);

    /**
     * @method Created to invoke a browser and parameter are taken from configuration file
     */
    public static void invokeBrowser(String browser, String runMode,String URL) {
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (runMode.equalsIgnoreCase("headless")) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constant.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.IMPLICIT_WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constant.EXPLICIT_WAIT));

        logger.info("Opening {} browser and loading {}", browser, URL);
        driver.get(URL);
    }

    /**
     * @method Create to navigate to URL
     */
    public static void navigateToUrl(String URL){
        driver.navigate().to(URL);
    }

    /**
     * @method Method Created to load an element store in object repository
     * @param elementName
     * @param className
     * @return
     */
    public WebElement loadWebElement(String elementName, String className){
        return loadAndFindElement(elementName, className);
    }

    private WebElement loadAndFindElement(String locator, String pageClassName){
        WebElement element = null;
        Class<?> loadClass = null;

        try {
            loadClass = Class.forName(UI_PAGE_OBJECT_PACKAGE + pageClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PageFactory.initElements(driver, loadClass);
        Field field = null;

        try {
            field = loadClass.getDeclaredField(locator);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            element = (WebElement) field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return element;
    }

    /**
     * @method Method Created to load an element store in object repository
     * @param elementName
     * @param className
     * @return
     */
    public List<WebElement> loadWebElements(String elementName, String className){
        return loadAndFindElements(elementName, className);
    }

    private List<WebElement> loadAndFindElements(String locator, String pageClassName){
        List<WebElement> element = null;
        Field field = null;
        Class<?> loadClass = null;

        try {
            loadClass = Class.forName(UI_PAGE_OBJECT_PACKAGE + pageClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PageFactory.initElements(driver, loadClass);

        try {
            field = loadClass.getDeclaredField(locator);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            element = (List<WebElement>) field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return element;
    }
}
