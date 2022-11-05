package com.testnepal.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testnepal.constants.ResponseCodes.*;
import static com.testnepal.resources.Payload.*;
import static com.testnepal.services.Endpoints.*;
import static com.testnepal.utils.Formatter.*;
import static io.restassured.RestAssured.given;

public class PutCallSampleTests extends BaseTest {

    @Test(priority = 3, description = "Update user by ID PUT")
    public void updateUserPutTest() {
        int userID = 2;

        String user = "MD SAQIB";
        String job = "SDET";

        Response response = given()
                .pathParam("id", userID)
                .body(createUpdateUserPayload(user, job))
                .contentType(ContentType.JSON)
                .when().put(USER_BY_ID)
                .then().extract().response();
        logResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), SUCCESS_STATUS_CODE);
        Assert.assertEquals(jsonPath.getString("name"), user);
        Assert.assertEquals(jsonPath.getString("job"), job);
        Assert.assertNotNull(jsonPath.getString("updatedAt"));
    }
}