package StepDefinitions.ApiStepDefintions.ThenFunctions;

import API_Functions.BaseClass.ApiBaseClass;
import CommonUtilityFunctions.SharedTestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ThenFunctions {

    private final ApiBaseClass apiBaseClass;
    private final SharedTestContext sharedTestContext;

    public ThenFunctions(ApiBaseClass apiBaseClass, SharedTestContext sharedTestContext) {
        this.apiBaseClass = apiBaseClass;
        this.sharedTestContext = sharedTestContext;
    }

    @Then("API call success with status code {int}")
    public void apiSuccessStatus(int status) {
        Assert.assertEquals(apiBaseClass.response.getStatusCode(), status);
    }

    @Then("I call {string} with {string} request to verify the {string} JSON schema")
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

    @Then("I stored the response in {string} POJO class")
    public void storedResponseInPojoClass(String pojoPath) {
        Class<?> loadClass = null;

        try {
            loadClass = Class.forName("API_Functions.PojoObjectClasses.ResponsePojoObjects." + pojoPath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Object obj = new Object();

        ObjectMapper mapper = new ObjectMapper();

        try {
            obj = mapper.readValue(apiBaseClass.response.asInputStream(), loadClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}