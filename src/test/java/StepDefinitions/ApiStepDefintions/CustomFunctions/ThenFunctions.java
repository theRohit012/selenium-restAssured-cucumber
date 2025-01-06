package StepDefinitions.ApiStepDefintions.CustomFunctions;

import API_Functions.BaseClass.ApiBaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
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
    public void userCallRequest(String api, String requestType, String schema) throws IOException {

        switch (requestType.toUpperCase()) {
            case "GET":
            apiBaseClass.requestSpec.when().get(api).then().spec(apiBaseClass.response()).assertThat()
                        .body(matchesJsonSchemaInClasspath(schema));
                break;

            case "POST":
                apiBaseClass.requestSpec.when().post(api).then().spec(apiBaseClass.response()).assertThat()
                        .body(matchesJsonSchemaInClasspath(schema));
                break;

            case "PUT":
                apiBaseClass.requestSpec.when().put(api).then().spec(apiBaseClass.response()).assertThat()
                        .body(matchesJsonSchemaInClasspath(schema));
                break;

            case "DELETE":
                apiBaseClass.requestSpec.when().delete(api).then().spec(apiBaseClass.response()).assertThat()
                        .body(matchesJsonSchemaInClasspath(schema));
                break;

            default:
                Assert.fail("Request type is not found");
        }
    }
}