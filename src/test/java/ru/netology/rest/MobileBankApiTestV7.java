package ru.netology.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiTestV7 {

    private RequestSpecification recSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost")
        .setBasePath("/api/v1")
        .setPort(9999)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();

    @Test
    void numberIsCorrect(){
        given()
                .spec(recSpec)
                .when()
                .get("/demo/accounts")
                .then()
                .body("[0].number", is("•• 0668"))
                .body("[1].number", is("•• 9192"))
                .body("[2].number", is("•• 5257"))
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }
}
