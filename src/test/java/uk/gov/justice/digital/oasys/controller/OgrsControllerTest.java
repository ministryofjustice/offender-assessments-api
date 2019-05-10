package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class OgrsControllerTest {

    @LocalServerPort
    int port;

    @MockBean
    private OffenderRepository offenderRepository;

    @Autowired
    @Qualifier("globalObjectMapper")
    private ObjectMapper objectMapper;

    @Value("${sample.token}")
    private String validOauthToken;

    @Before
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));

        ControllerTestContext.setup(offenderRepository);
    }
    @After
    public void tearDown() {
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

        assertThat(ogrss).extracting("sentencePlanId").containsExactly(1L, 2L);
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

        assertThat(ogrss).extracting("sentencePlanId").containsExactly(1L, 2L);
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

        assertThat(ogrss).extracting("sentencePlanId").containsExactly(1L, 2L);
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

        assertThat(ogrss).extracting("sentencePlanId").containsExactly(1L, 2L);
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

        assertThat(ogrss).extracting("sentencePlanId").containsExactly(1L, 2L);
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