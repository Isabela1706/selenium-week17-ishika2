package com.gorest.project.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/public/v2/posts")
                .then().statusCode(200);
    }

    // Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("", hasSize(25));
    }

    // Verify the if the title of id = 184438 is equal to ”Coerceo audentia adaugeo qui atque utor.”
    @Test
    public void test002() {
        response.body("find{it.id == 184438}.title", equalTo("Coerceo audentia adaugeo qui atque utor."));
    }

    // Check the single user_id in the Array list (7605010)
    @Test
    public void test003() {
        response.body("user_id", hasItem(7605010));
    }

    // Check the multiple ids in the ArrayList (7605010, 7605003, 7605001)
    @Test
    public void test004() {
        response.body("user_id", hasItems(7605010, 7605003, 7605001));
    }

    // Verify the body of userid = 7604998 is equal "Arcus ventosus delectatio. Adhuc carus campana. Una venustas suscipio. Commodi capitulus hic. Succedo velum rerum. Aspernatur verecundia quis. Umerus vinum curis. Assumenda nobis aggero. Clementia thorax alii. Adiuvo cogito defendo. Subito omnis cognomen."
    @Test
    public void test005() {
        response.body("find{it.user_id == 7604998}.body", equalTo("Arcus ventosus delectatio. Adhuc carus campana. Una venustas suscipio. Commodi capitulus hic. Succedo velum rerum. Aspernatur verecundia quis. Umerus vinum curis. Assumenda nobis aggero. Clementia thorax alii. Adiuvo cogito defendo. Subito omnis cognomen."));
    }


}
