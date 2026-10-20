
package com.vedansh.qa;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostsApiTest {

    @Test
    public void verifyGetPostById() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

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
}
