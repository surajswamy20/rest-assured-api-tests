package com.surajswamy.api.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.blankOrNullString;

public class UsersApiTest extends BaseApiTest {

    @Test
    public void shouldFindAUserByUsername() {
        Response response = given()
                .spec(requestSpec)
                .queryParam("username", "Bret")
        .when()
                .get("/users")
        .then()
                .statusCode(200)
                .body("size()", equalTo(1))
                .body("[0].address.city", not(blankOrNullString()))
                .extract()
                .response();

        int userId = response.jsonPath().getInt("[0].id");
        String latitude = response.jsonPath().getString("[0].address.geo.lat");

        Assert.assertTrue(userId > 0);
        Assert.assertNotNull(latitude);
    }
}
