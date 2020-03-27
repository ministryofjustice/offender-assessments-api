package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PingEndpointIntTest extends IntegrationTest {

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void healthEndpointIsUnauthorised() {
        var reponseBody = given()
                .when()
                .get("/ping")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.TEXT)
                .extract()
                .asString();

        assertThat(reponseBody).isEqualTo("pong");

    }
}
