
package com.vedansh.qa;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class PostsApiTest {

    @BeforeClass
    public void setupApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        RestAssured.config = config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 5000)
                        .setParam("http.socket.timeout", 10000));
    }

    @Test
    public void verifyGetPostById() {

        Response response =
                given()
                        .when()
                        .get("/posts/1")
                        .then()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("userId"), 1);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertNotNull(response.jsonPath().getString("title"));

        System.out.println("API Status Code: " + response.getStatusCode());
        System.out.println("API Response: " + response.asPrettyString());
    }

    @Test
    public void verifyGetNonexistentPost() {

        Response response =
                given()
                        .when()
                        .get("/posts/999999")
                        .then()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.asString().trim(), "{}");

        System.out.println(
                "Nonexistent Post API Status Code: "
                        + response.getStatusCode()
        );
    }
}
