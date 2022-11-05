package com.testnepal.tests;

import com.testnepal.reporter.ExtentManager;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.baseURI;

public class BaseTestLocal {

    @BeforeMethod
    public void setUpLocal() {
        baseURI = "http://localhost:3000/";
    }

    public void logLocalAPIResponseInReport(Response response) {
        ExtentManager.addResponseLogToReport(response.asPrettyString());
    }
}
