package com.surajswamy.api.tests;

import com.surajswamy.api.model.Post;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;

public class PostsApiTest extends BaseApiTest {

    @Test
    public void shouldGetAnExistingPost() {
        given()
                .spec(requestSpec)
                .pathParam("postId", 1)
        .when()
                .get("/posts/{postId}")
        .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", not(blankOrNullString()));
    }
    @Test
    public void shouldReturnPostsForUser() {
        given()
                .spec(requestSpec)
                .queryParam("userId", 1)
        .when()
                .get("/posts")
        .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("userId", everyItem(equalTo(1)));
    }

    @Test
    public void shouldReturnNotFoundForAnUnknownPost() {
        given()
                .spec(requestSpec)
                .pathParam("postId", 999999)
        .when()
                .get("/posts/{postId}")
        .then()
                .statusCode(404);
    }

    @Test
    public void shouldCreateAPost() {
        Post requestBody = new Post(
                null,
                7,
                "API automation practice",
                "Creating a post with REST Assured"
        );

        Post createdPost = given()
                .spec(requestSpec)
                .body(requestBody)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .extract()
                .as(Post.class);

        Assert.assertNotNull(createdPost.getId());
        Assert.assertEquals(createdPost.getUserId(), requestBody.getUserId());
        Assert.assertEquals(createdPost.getTitle(), requestBody.getTitle());
        Assert.assertEquals(createdPost.getBody(), requestBody.getBody());
    }

    @Test
    public void shouldUpdateAPost() {
        Post requestBody = new Post(
                1,
                7,
                "Updated API automation practice",
                "Updating a post with REST Assured"
        );

        Post updatedPost = given()
                .spec(requestSpec)
                .pathParam("postId", 1)
                .body(requestBody)
        .when()
                .put("/posts/{postId}")
        .then()
                .statusCode(200)
                .extract()
                .as(Post.class);

        Assert.assertEquals(updatedPost.getId(), requestBody.getId());
        Assert.assertEquals(updatedPost.getUserId(), requestBody.getUserId());
        Assert.assertEquals(updatedPost.getTitle(), requestBody.getTitle());
        Assert.assertEquals(updatedPost.getBody(), requestBody.getBody());
    }

    @Test
    public void shouldDeleteAPost() {
        given()
                .spec(requestSpec)
                .pathParam("postId", 1)
        .when()
                .delete("/posts/{postId}")
        .then()
                .statusCode(200);
    }
}
