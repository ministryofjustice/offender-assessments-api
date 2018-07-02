package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.justice.digital.oasys.api.Assessment;
import uk.gov.justice.digital.oasys.api.AssessmentResource;
import uk.gov.justice.digital.oasys.jpa.repository.AssessmentRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class AssessmentsControllerTest {

    @LocalServerPort
    int port;

    @MockBean
    private OffenderRepository offenderRepository;

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

        ControllerTestContext.setup(offenderRepository);

        Mockito.when(assessmentRepository.findOne(eq(0L))).thenReturn(ControllerTestContext.anOasysSet(0L));
    }



    @After
    public void tearDown() {
    }

    @Test
    public void canGetAssessmentsForOffenderPk() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderPk() {
        Assessment assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments/latest", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment.class);

        assertThat(assessments).extracting("oasysSetId").containsOnly(2L);
    }

    @Test
    public void getAssessmentForUnknownOffenderPkGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments", 2L)
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderCRN() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderCrn() {
        Assessment assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/assessments/latest", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment.class);

        assertThat(assessments).extracting("oasysSetId").containsOnly(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderCRNGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/assessments", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderPNC() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/assessments", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderPnc() {
        Assessment assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/assessments/latest", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment.class);

        assertThat(assessments).extracting("oasysSetId").containsOnly(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderPNCGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/assessments", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderNomisId() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/assessments", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderNmisId() {
        Assessment assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/assessments/latest", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment.class);

        assertThat(assessments).extracting("oasysSetId").containsOnly(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/assessments", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderBookingId() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/assessments", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderBookingId() {
        Assessment assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/assessments/latest", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment.class);

        assertThat(assessments).extracting("oasysSetId").containsOnly(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/assessments", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFilteredByAssessmentType() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("assessmentType", "oasys")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).extracting("assessment.assessmentType").containsOnly("oasys");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByHistoricStatus() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("historicStatus", "CURRENT")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).extracting("assessment.historicStatus").containsOnly("CURRENT");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByAssessmentStatus() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("assessmentStatus", "COMPLETE")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).extracting("assessment.assessmentStatus").containsOnly("COMPLETE");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredBVoided() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("voided", "true")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).extracting("assessment.voided").containsOnly(true);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredBNotVoided() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("voided", "false")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).extracting("assessment.voided").containsOnly(false);
    }

    @Test
    public void assessmentResourceForValidAssessmentContainsValidLink() {
        AssessmentResource[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("voided", "false")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).extracting("links").isNotEmpty();
    }

    @Test
    public void canLookupAssessmentByOasysSetPk() {
        Assessment assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/assessments/oasysSetId/{0}", 0L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment.class);

        assertThat(assessment).extracting("oasysSetId").containsExactly(0L);

    }

}