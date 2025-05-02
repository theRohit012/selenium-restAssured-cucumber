package GUI_Functions.BaseClass;

import CommonUtilityFunctions.ConfigReader;
import CommonUtilityFunctions.Constant;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class GuiBaseClass {
    public static Properties prop = ConfigReader.initProperties();
    public static String folderName = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());
    private static final String UI_PAGE_OBJECT_PACKAGE = "GUI_Functions.ObjectRepository.";
    public static WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(GuiBaseClass.class);
    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * @method Created to invoke a browser and parameter are taken from configuration file
     */
    public static void invokeBrowser(String browser) {
        WebDriver webDriver = null;
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if(prop.getProperty("runMode").equalsIgnoreCase("headless")){
            chromeOptions.addArguments("--headless=new");
            firefoxOptions.addArguments("-headless");
        }

        try {
            if (prop.getProperty("grid").equalsIgnoreCase("Yes")) {
                logger.info("Tests are running with GRID");
                // RemoteWebDriver for Grid
                switch (browser.toLowerCase()) {
                    case "chrome":
                        webDriver = new RemoteWebDriver(new URL(prop.getProperty("gridHubURL")), chromeOptions);
                        break;
                    case "firefox":
                        webDriver = new RemoteWebDriver(new URL(prop.getProperty("gridHubURL")), firefoxOptions);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            } else {
                // Local WebDriver
                logger.info("Tests are running with WebDriver");
                switch (browser.toLowerCase()) {
                    case "chrome":
                        webDriver = new ChromeDriver(chromeOptions);
                        break;
                    case "firefox":
                        webDriver = new FirefoxDriver(firefoxOptions);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
        driver.set(webDriver);

        driver.get().manage().window().maximize();
        driver.get().manage().deleteAllCookies();

        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constant.PAGE_LOAD_TIMEOUT));
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Constant.IMPLICIT_WAIT));
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(Constant.EXPLICIT_WAIT));

        logger.info("Opening {} browser and loading {}", browser, prop.getProperty("url"));
        driver.get().get(prop.getProperty("url"));
    }

    /**
     * @method Create to navigate to URL
     */
    public static void navigateToUrl(String URL){
        driver.get().navigate().to(URL);
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

        PageFactory.initElements(driver.get(), loadClass);
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

        PageFactory.initElements(driver.get(), loadClass);

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
