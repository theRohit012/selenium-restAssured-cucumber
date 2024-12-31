package StepDefinitions.ApiStepDefintions.CustomFunctions;

import API_Functions.BaseClass.ApiBaseClass;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class ThenFunctions {

    private final ApiBaseClass apiBaseClass;

    public ThenFunctions(ApiBaseClass apiBaseClass) {
        this.apiBaseClass = apiBaseClass;
    }

    @Then("API call success with status code {int}")
    public void apiSuccessStatus(int status){
        Assert.assertEquals(apiBaseClass.response.getStatusCode(), status);
    }
}