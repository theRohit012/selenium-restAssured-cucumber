package StepDefinitions.ApiStepDefintions.CustomFunctions;

import API_Functions.BaseClass.ApiBaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GivenFunctions {
    private final ApiBaseClass apiBaseClass;

    public GivenFunctions(ApiBaseClass apiBaseClass){
        this.apiBaseClass = apiBaseClass;
    }

    @Given("I have a {}")
    public void createARequest(String baseURI){
        apiBaseClass.requestSpec = given().spec(apiBaseClass.request(baseURI)).auth().basic("","");
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
