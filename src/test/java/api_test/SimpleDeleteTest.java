package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleDeleteTest {
    private static final Logger LOGGER = LogManager.getLogger(SimpleDeleteTest.class);

    @Test
    public void DeleteEmployeeRecord() {
        // Specify the base URL or endpoint of the Rest API
        RestAssured.baseURI = "https://reqres.in/api/users";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type. (Delete employee id "24")
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.DELETE, "/403");

        // Get the status code from the Response. In case of
        // a successful interaction with the web service, we
        // should get a status code of 200.
        int statusCode = response.getStatusCode();
        LOGGER.debug("Actual Status Code: " + statusCode);

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode, 204);
    }
}
