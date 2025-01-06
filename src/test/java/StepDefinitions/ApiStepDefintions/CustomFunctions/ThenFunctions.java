package StepDefinitions.ApiStepDefintions.CustomFunctions;

import API_Functions.BaseClass.ApiBaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ThenFunctions {

    private final ApiBaseClass apiBaseClass;

    public ThenFunctions(ApiBaseClass apiBaseClass) {
        this.apiBaseClass = apiBaseClass;
    }

    @Then("API call success with status code {int}")
    public void apiSuccessStatus(int status) {
        Assert.assertEquals(apiBaseClass.response.getStatusCode(), status);
    }

    @When("I call {string} with {string} request to verify the {string} JSON schema")
    public void userCallRequest(String api, String requestType, String jsonSchema) {

        String JsonSchemas = "";

        switch (requestType.toUpperCase()) {
            case "GET":
                apiBaseClass.requestSpec.when().get(api).then().body(matchesJsonSchemaInClasspath(JsonSchemas));
            case "POST":
                apiBaseClass.requestSpec.when().post(api).then().body(matchesJsonSchemaInClasspath(JsonSchemas));
                break;
            case "PUT":
                apiBaseClass.requestSpec.when().put(api).then().body(matchesJsonSchemaInClasspath(JsonSchemas));
                break;
            case "DELETE":
                apiBaseClass.requestSpec.when().delete(api).then().body(matchesJsonSchemaInClasspath(JsonSchemas));
                break;
            default:
                Assert.fail("Request type is not found");
        }
    }
}