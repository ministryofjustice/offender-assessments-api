package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.digital.oasys.api.Ogp;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class OgpControllerTest extends IntegrationTest {

    @MockBean
    private OffenderRepository offenderRepository;

    @Value("${sample.token}")
    private String validOauthToken;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));
        ControllerTestContext.setup(offenderRepository);
    }

    @Test
    public void canGetOgpForOffenderCRNs() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/ogp", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/ogp", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOffenderPnc() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/ogp", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderPncGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/ogp", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOffenderNomisId() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/ogp", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/ogp", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOffenderBookingId() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/ogp", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/ogp", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOasysOffenderPk() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/ogp", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOasysOffenderPkGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/ogp", 2L)
                .then()
                .statusCode(404);
    }
}