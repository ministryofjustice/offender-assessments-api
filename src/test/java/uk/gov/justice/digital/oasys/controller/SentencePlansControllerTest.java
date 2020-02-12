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
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.jpa.repository.AssessmentRepository;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class SentencePlansControllerTest {

    @LocalServerPort
    int port;

    @MockBean
    private AssessmentRepository assessmentRepository;

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
    }


    @After
    public void tearDown() {
    }

    @Test
    public void endpointsAreAuthorised() {
        given()
                .when()
                .get("/xyz")
                .then()
                .statusCode(401);
    }

    @Test
    public void canGetSentencePlansForOffenderPk() {
        BasicSentencePlan[] sentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/basicSentencePlans", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan[].class);

        assertThat(sentencePlans).hasSize(2);
        assertThat(sentencePlans).extracting("sentencePlanId").containsOnly(1L, 2L);
    }

    @Test
    public void canGetLatestSentencePlanForOffenderPk() {
        BasicSentencePlan sentencePlan = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/basicSentencePlans/latest", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan.class);

        assertThat(sentencePlan).extracting("sentencePlanId").isEqualTo(2L);
    }

    @Test
    public void getSentencePlanForUnknownOffenderPkGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/basicSentencePlans", 2L)
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetSentencePlansForOffenderCRN() {
        BasicSentencePlan[] SentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/basicSentencePlans", "XYZ12345")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan[].class);

        assertThat(SentencePlans).hasSize(2);
    }

    @Test
    public void canGetLatestSentencePlanForOffenderCrn() {
        BasicSentencePlan sentencePlan = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/basicSentencePlans/latest", "XYZ12345")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan.class);

        assertThat(sentencePlan).extracting("sentencePlanId").isEqualTo(2L);
    }


    @Test
    public void getSentencePlansForUnknownOffenderCRNGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/basicSentencePlans", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetSentencePlansForOffenderPNC() {
        BasicSentencePlan[] SentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/basicSentencePlans", "PNC1234")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan[].class);

        assertThat(SentencePlans).hasSize(2);
    }

    @Test
    public void canGetLatestSentencePlansForOffenderPnc() {
        BasicSentencePlan sentencePlan = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/basicSentencePlans/latest", "PNC1234")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan.class);

        assertThat(sentencePlan).extracting("sentencePlanId").isEqualTo(2L);
    }


    @Test
    public void getSentencePlansForUnknownOffenderPNCGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/basicSentencePlans", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetSentencePlansForOffenderNomisId() {
        BasicSentencePlan[] SentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/basicSentencePlans", "NOMIS123456")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan[].class);

        assertThat(SentencePlans).hasSize(2);
    }

    @Test
    public void canGetLatestSentencePlanForOffenderNomisId() {
        BasicSentencePlan sentencePlan = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/basicSentencePlans/latest", "NOMIS123456")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan.class);

        assertThat(sentencePlan).extracting("sentencePlanId").isEqualTo(2L);
    }


    @Test
    public void getSentencePlansForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/basicSentencePlans", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetSentencePlansForOffenderBookingId() {
        BasicSentencePlan[] SentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/basicSentencePlans", "Book12")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan[].class);

        assertThat(SentencePlans).hasSize(2);
    }

    @Test
    public void canGetLatestSentencePlanForOffenderBookingId() {
        BasicSentencePlan sentencePlan = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/basicSentencePlans/latest", "Book12")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan.class);

        assertThat(sentencePlan).extracting("sentencePlanId").isEqualTo(2L);
    }


    @Test
    public void getSentencePlansForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/basicSentencePlans", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetSentencePlansForOffenderCRNFilteredByAssessmentType() {
        BasicSentencePlan[] sentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("assessmentType", "LAYER_3")
                .get("/offenders/crn/{0}/basicSentencePlans", "XYZ12345")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan[].class);

        assertThat(sentencePlans).extracting("sentencePlanId").containsOnly(1l, 2l);
    }

    @Test
    public void canGetSentencePlansForOffenderCRNFlteredByHistoricStatus() {
        BasicSentencePlan[] SentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("historicStatus", "CURRENT")
                .get("/offenders/crn/{0}/basicSentencePlans", "XYZ12345")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(BasicSentencePlan[].class);

        assertThat(SentencePlans).extracting("sentencePlanId").containsOnly(1l, 2l);
    }

}