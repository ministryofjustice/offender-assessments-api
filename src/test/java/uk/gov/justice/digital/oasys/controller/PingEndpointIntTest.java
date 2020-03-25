package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PingEndpointIntTest {

    @LocalServerPort
    int port;

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
