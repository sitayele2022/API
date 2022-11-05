package com.testnepal.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testnepal.constants.ResponseCodes.*;
import static com.testnepal.services.Endpoints.*;
import static io.restassured.RestAssured.*;
import static com.testnepal.resources.Payload.*;
import static com.testnepal.utils.Formatter.*;

public class PostCallSampleTests extends BaseTest {

    @Test(priority = 2, description = "Create User")
    public void createUserTest() {
        String user = "MD SADAB";
        String job = "Tester";

        Response response = given()
                .body(createUpdateUserPayload(user, job))
                .contentType(ContentType.JSON)
                .when().post(USERS)
                .then().extract().response();
        logResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), CREATED_STATUS_CODE);
        Assert.assertEquals(jsonPath.getString("name"), user);
        Assert.assertEquals(jsonPath.getString("job"), job);
        Assert.assertNotNull(jsonPath.getString("id"));
        Assert.assertNotNull(jsonPath.getString("createdAt"));
    }
}