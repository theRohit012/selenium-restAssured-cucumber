package StepDefinitions.ApiStepDefintions.CustomFunctions;

import API_Functions.BaseClass.ApiBaseClass;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class WhenFunctions {

    private final ApiBaseClass apiBaseClass;

    public WhenFunctions(ApiBaseClass apiBaseClass) {
        this.apiBaseClass = apiBaseClass;
    }

    @When("I call {string} with {string} request")
    public void userCallRequest(String api, String requestType) {
        switch (requestType.toUpperCase()) {
            case "GET":
                apiBaseClass.response = apiBaseClass.requestSpec.when().get().then().extract().response();
            case "POST":
                apiBaseClass.response = apiBaseClass.requestSpec.when().post(api).then().extract().response();
                break;
            case "PUT":
                apiBaseClass.response = apiBaseClass.requestSpec.when().put(api).then().extract().response();
                break;
            case "DELETE":
                apiBaseClass.response = apiBaseClass.requestSpec.when().delete(api).then().extract().response();
                break;
            default:
                Assert.fail("Request type is not found");
        }
    }
}
