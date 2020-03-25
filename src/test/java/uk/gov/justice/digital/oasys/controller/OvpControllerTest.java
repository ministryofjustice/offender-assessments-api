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


public class OvpControllerTest extends IntegrationTest {

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
    public void canGetOvpForOffenderCRNs() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/ovp", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/ovp", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOffenderPnc() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/ovp", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderPncGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/ovp", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOffenderNomisId() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/ovp", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/ovp", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOffenderBookingId() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/ovp", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/ovp", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOasysOffenderPk() {
        Ogp[] ogps = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/ovp", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOasysOffenderPkGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/ovp", 2L)
                .then()
                .statusCode(404);
    }
}