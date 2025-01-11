package StepDefinitions.ApiStepDefintions.GivenFuncions;

import API_Functions.BaseClass.ApiBaseClass;
import CommonUtilityFunctions.GlobalVariablePlaceHolder;
import CommonUtilityFunctions.SharedTestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GivenFunctions {
    private final ApiBaseClass apiBaseClass;
    private final SharedTestContext sharedTestContext;

    public GivenFunctions(ApiBaseClass apiBaseClass, SharedTestContext sharedTestContext){
        this.apiBaseClass = apiBaseClass;
        this.sharedTestContext = sharedTestContext;
    }

    @Given("I have a {string}")
    public void createARequest(String baseURI){
        String URI = GlobalVariablePlaceHolder.resolveGlobalVariables(baseURI);
        apiBaseClass.requestSpec = given().spec(apiBaseClass.request(URI));
    }

    @Given("I have path parameter")
    public void setPathParameterWithRequest(DataTable table){
        List<Map<String,String>> pathParam = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        pathParam = table.asMaps();
        for(Map<String,String> row : pathParam){
            map.put(row.get("pathParam"),row.get("Value"));
        }
        apiBaseClass.requestSpec.pathParams(map);
    }

    @Given("I have a query parameter")
    public void setQueryParameter(DataTable table){
        List<Map<String,String>> queryParam = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        queryParam = table.asMaps();
        for(Map<String,String> row : queryParam){
            map.put(row.get("queryParam"),row.get("Value"));
        }
        apiBaseClass.requestSpec.queryParams(map);
    }
}
