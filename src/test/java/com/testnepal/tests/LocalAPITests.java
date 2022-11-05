package com.testnepal.tests;

import static com.testnepal.constants.ResponseCodes.*;
import static com.testnepal.utils.Formatter.*;

import com.testnepal.resources.Payload;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;


import static io.restassured.RestAssured.*;

public class LocalAPITests extends BaseTestLocal {

    @Test(priority = 3, description = "Employee Data Test")
    public void getEmployeesTest() {
        Response response = given().when().get("employees").then().extract().response();
        logLocalAPIResponseInReport(response);
        Assert.assertEquals(response.statusCode(), SUCCESS_STATUS_CODE);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(jsonPath.getInt("data[0].id"), 1);
        Assert.assertEquals(jsonPath.getString("data[0].first_name"), "Sebastian");
        Assert.assertEquals(jsonPath.getString("data[0].last_name"), "Eschweiler");
    }

    @Test(priority = 4, description = "Company Information Test")
    public void getCompanyTest() {
        Response response = given().when().get("company").then().extract().response();
        logLocalAPIResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), SUCCESS_STATUS_CODE);
        Assert.assertEquals(jsonPath.getString("name"), "Test Automation Hub");
        Assert.assertEquals(jsonPath.getString("location"), "Nepal");
        Assert.assertEquals(jsonPath.getInt("noOfEmployee"), 200);
    }

    @DataProvider(name = "create-movie-data")
    private Object[][] createMovieData() {
        return new Object[][]{
                {121, "Age of Ultron", "Sci-fi", 8},
                {122, "Wazir", "Thriller", 7},
                {123, "Dhuruvangal Pathinaaru", "Thriller", 9},
                {124, "Agent Sai Srinivasa Athreya", "Thriller", 8},
                {125, "Drishyam", "Thriller", 9}
        };
    }

    @Test(priority = 5, description = "Create movies", dataProvider = "create-movie-data")
    public void createMovies(int id, String name, String genre, int rating) {
        Response response = given().body(Payload.createMoviePayload(id, name, genre, rating))
                .contentType(ContentType.JSON)
                .when().post("movies")
                .then().extract().response();
        logLocalAPIResponseInReport(response);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 6, description = "Movie Information by ID Test")
    public void getMovieByID() {
        int id = 123;
        Response response = given().queryParam("id", id).get("/movies")
                .then().extract().response();
        logLocalAPIResponseInReport(response);
        JsonPath jsonPath = convertResponseToJson(response);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(jsonPath.getInt("[0].id"), id);
        Assert.assertEquals(jsonPath.getString("[0].name"), "Dhuruvangal Pathinaaru");
        Assert.assertEquals(jsonPath.getString("[0].genre"), "Thriller");
        Assert.assertEquals(jsonPath.getInt("[0].rating"), 9);
    }

    @DataProvider(name = "delete-movie-data")
    private Object[][] deleteMovieData() {
        return new Object[][]{{"121"}, {"122"}, {"123"}, {"124"}, {"125"}};
    }

    @Test(priority = 7, description = "Delete Movie by ID", dataProvider = "delete-movie-data")
    public void deleteMovie(String id) {
        Response response = given().when().delete("/movies/" + id).then().extract().response();
        Assert.assertEquals(response.statusCode(), 200);
    }

}
