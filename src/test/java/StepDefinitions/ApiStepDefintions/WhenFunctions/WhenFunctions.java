package StepDefinitions.ApiStepDefintions.WhenFunctions;

import API_Functions.BaseClass.ApiBaseClass;
import CommonUtilityFunctions.SharedTestContext;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class WhenFunctions {

    private final ApiBaseClass apiBaseClass;
    private final SharedTestContext sharedTestContext;

    public WhenFunctions(ApiBaseClass apiBaseClass, SharedTestContext sharedTestContext){
        this.apiBaseClass = apiBaseClass;
        this.sharedTestContext = sharedTestContext;
    }

    @When("I call {string} with {string} request")
    public void userCallRequest(String api, String requestType) {
        switch (requestType.toUpperCase()) {
            case "GET":
                apiBaseClass.response = apiBaseClass.requestSpec.when().get().then().spec(apiBaseClass.response()).extract().response();
                break;

            case "POST":
                apiBaseClass.response = apiBaseClass.requestSpec.when().post(api).then().spec(apiBaseClass.response()).extract().response();
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
