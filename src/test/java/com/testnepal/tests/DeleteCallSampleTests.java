package com.testnepal.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testnepal.constants.ResponseCodes.DELETED_STATUS_CODE;
import static com.testnepal.services.Endpoints.*;
import static io.restassured.RestAssured.*;

public class DeleteCallSampleTests extends  BaseTest {

    @Test(priority = 5, description = "Delete user by ID")
    public static void deleteUserTest() {
        Response response = given()
                .when().pathParam("id", 2).delete(USER_BY_ID).then().extract().response();
        Assert.assertEquals(response.statusCode(), DELETED_STATUS_CODE);
    }
}
