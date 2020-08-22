package api_test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplePutTest {
    private static final Logger LOGGER = LogManager.getLogger(SimplePutTest.class);

    @Test
    public void updateExistingUser() {
        RestAssured.baseURI ="https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();

        JSONObject reqBody = new JSONObject();
        reqBody.put("name", "Md. Masud Alam");
        reqBody.put("job", "Sr. Programmer");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(reqBody.toJSONString());
        LOGGER.debug(reqBody);
        Response response = httpRequest.request(Method.PUT, "403");

        LOGGER.debug(response.asString());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
