package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/FeaturesFiles/"},
        glue = {"StepDefinitions", "ApplicationHook"},
        plugin = {"json:target/cucumber.json", "html:target/cucumber-report.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
