package com.testnepal.tests;

import com.testnepal.reporter.ExtentManager;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    @BeforeTest
    public void setUpBaseURI() {
        baseURI = "https://reqres.in/api";
    }

    public void logResponseInReport(Response response) {
        ExtentManager.addResponseLogToReport(response.asPrettyString());
    }

}
