package com.gorest.project.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/public/v2/users")
                .then().statusCode(200);
    }

    // Verify if the total record is 20
    @Test
    public void test001() {
        response.body("", hasSize(20));
    }

    // Verify if the name of id = 7605000 is equal to ”Aagney Mehrotra”
    @Test
    public void test002() {
        response.body("find{it.id == 7605000}.name", equalTo("Aagney Mehrotra"));
    }

    // Check the single ‘Name’ in the Array list (Aagney Mehrotra)
    @Test
    public void test003() {
        response.body("name", hasItem("Aagney Mehrotra"));
    }

    // Check the multiple ‘Names’ in the ArrayList (Aagney Mehrotra, Prasad Khanna, Baidehi Khatri )
    @Test
    public void test004() {
        response.body("name", hasItems("Aagney Mehrotra", "Prasad Khanna", "Baidehi Khatri"));
    }

    // Verify the email of userid = 7604991 is equal “gill_preity_miss@ledner.example”
    @Test
    public void test005() {
        response.body("find{it.id == 7604991}.email", equalTo("gill_preity_miss@ledner.example"));
    }

    // Verify the status is “Active” of user name is “Pramila Sethi Ret.”
    @Test
    public void test006() {
        response.body("find{it.name == 'Pramila Sethi Ret.'}.status", equalTo("active"));
    }


    // Verify the Gender = male of user name is “Pramila Sethi Ret.”
    @Test
    public void test007() {
        response.body("find{it.name == 'Pramila Sethi Ret.'}.gender", equalTo("male"));
    }


}
