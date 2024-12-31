package API_Functions.BaseClass;

import CommonUtilityFunctions.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

public class ApiBaseClass {

    public RequestSpecification requestSpec;
    public Response response;
    public Properties prop = ConfigReader.initProperties();


    /**
     * @method for Setup request
     * @param basURI
     * @return
     */
    public RequestSpecification request(String basURI) {
        PrintStream pos = null;
        try {
            pos = new PrintStream(new FileOutputStream("logging.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        requestSpec = new RequestSpecBuilder().
                setBaseUri(basURI).setRelaxedHTTPSValidation().setUrlEncodingEnabled(true)
                .addFilter(RequestLoggingFilter.logRequestTo(pos))
                .addFilter(ResponseLoggingFilter.logResponseTo(pos)).log(LogDetail.ALL).build();

        return requestSpec;
    }

    /**
     * @method to get the JSON value
     * @param response
     * @param key
     * @return key value
     */
    public String getJsonValue(Response response, String key){
        JsonPath jsPath = new JsonPath(response.toString());
        return jsPath.get(key).toString();
    }
}
