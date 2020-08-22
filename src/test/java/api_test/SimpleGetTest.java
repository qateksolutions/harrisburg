package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest {
    private static final Logger LOGGER = LogManager.getLogger(SimpleGetTest.class);
    @Test
    public void GetAllEmployeeDetails() {
        // Specify the base URL or endpoint of the Rest API
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";


        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET);

        // Now let us print the body of the message to see what response
        // we have received from the server
        LOGGER.debug(response.getBody().asString());

        // Get the status code from the Response. In case of
        // a successful interaction with the web service, we
        // should get a status code of 200.
        int statusCode = response.getStatusCode();
        LOGGER.debug("Actual Status Code: " + statusCode);

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode, 200);

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPath = response.jsonPath();

        // Validate that employee record is not null
        String record  = jsonPath.getString("data[0]");
        Assert.assertNotNull(record, "Employee Record is null");
    }

    @Test
    public void GetSingleEmployeeDetails() {
        // Specify the base URL or endpoint of the Rest API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, "/3");

        // Now let us print the body of the message to see what response
        // we have received from the server
        LOGGER.debug(response.getBody().asString());

        // Get the status code from the Response. In case of
        // a successful interaction with the web service, we
        // should get a status code of 200.
        int statusCode = response.getStatusCode();
        LOGGER.debug("Actual Status Code: " + statusCode);

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode, 200);

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPath = response.jsonPath();

        // Validate that employee name is "Ashton Cox"
        String record  = jsonPath.getString("data.first_name");
        Assert.assertTrue(record.contains("Emma"), "Employee Record does not have the name");
    }
}
