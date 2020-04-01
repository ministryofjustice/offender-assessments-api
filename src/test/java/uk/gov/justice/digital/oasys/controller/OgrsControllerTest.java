package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class OgrsControllerTest extends IntegrationTest {

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
    public void canGetOgrsForOffenderCRNs() {
        Ogrs[] ogrss = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/ogrs3", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogrs[].class);

        assertThat(ogrss).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgrsForUnknownOffenderGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/ogrs3", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOffenderPnc() {
        Ogrs[] ogrss = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/ogrs3", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogrs[].class);

        assertThat(ogrss).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgrsForUnknownOffenderPncGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/ogrs3", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOffenderNomisId() {
        Ogrs[] ogrss = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/ogrs3", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogrs[].class);

        assertThat(ogrss).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgrsForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/ogrs3", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOffenderBookingId() {
        Ogrs[] ogrss = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/ogrs3", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogrs[].class);

        assertThat(ogrss).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgrsForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/ogrs3", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOasysOffenderPk() {
        Ogrs[] ogrss = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/ogrs3", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogrs[].class);

        assertThat(ogrss).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgrsForUnknownOasysOffenderPkGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/ogrs3", 2L)
                .then()
                .statusCode(404);
    }
}