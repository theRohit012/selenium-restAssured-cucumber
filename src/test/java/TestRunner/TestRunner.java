package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static GUI_Functions.BaseClass.GuiBaseClass.prop;

@CucumberOptions(features = {"src/test/resources/FeaturesFiles/GuiTestCases"},
        glue = {"StepDefinitions", "ApplicationHook"},
        plugin = {"json:target/cucumber.json", "html:target/cucumber-report.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void setBrowser(@Optional("chrome") String browser) {
        System.setProperty("browser", browser);
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}