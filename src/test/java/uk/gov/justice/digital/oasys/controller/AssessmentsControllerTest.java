package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.justice.digital.oasys.api.AssessmentSummaryDto;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:assessments/before-test.sql", config = @SqlConfig(transactionMode = ISOLATED))
@Sql(scripts = "classpath:assessments/after-test.sql", config = @SqlConfig(transactionMode = ISOLATED), executionPhase = AFTER_TEST_METHOD)
public class AssessmentsControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    @Qualifier("globalObjectMapper")
    private ObjectMapper objectMapper;

    @Value("${sample.token}")
    private String validOauthToken;

    private Long oasysOffenderId = 1234L;
    private String pnc = "PNC";
    private String crn = "CRN";
    private String nomis = "NOMIS";
    private String booking = "BOOKIN";

    private Long layerOneAssessmentId = 5434L;
    private Long openAssessmentId = 5433L;
    private Long completeAssessmentId = 5432L;
    private Long voidedAssessmentId = 5431L;
    private Long historicAssessmentId = 5430L;

    @Before
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));
    }

    @Test
    public void shouldGetAssessmentSummariesOasys() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.OASYS.getValue(), oasysOffenderId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, openAssessmentId, completeAssessmentId, voidedAssessmentId, layerOneAssessmentId));

        Optional<AssessmentSummaryDto> validAssessment = Arrays.stream(assessments).filter(a -> a.getAssessmentId().equals(openAssessmentId)).findFirst();
        assertThat(validAssessment.isPresent()).isTrue();

        validateOpenAssessmentSummary(validAssessment.get());
    }

    @Test
    public void shouldGetAssessmentSummariesOasysNotFound() {
         given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.OASYS.getValue(),oasysOffenderId + 5L)
                .then()
                .statusCode(404);

    }

    @Test
    public void shouldGetAssessmentSummariesBooking() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.BOOKING.getValue(), booking)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, openAssessmentId, completeAssessmentId, voidedAssessmentId, layerOneAssessmentId));

        Optional<AssessmentSummaryDto> validAssessment = Arrays.stream(assessments).filter(a -> a.getAssessmentId().equals(openAssessmentId)).findFirst();
        assertThat(validAssessment.isPresent()).isTrue();

        validateOpenAssessmentSummary(validAssessment.get());
    }

    @Test
    public void shouldGetAssessmentSummariesBookingNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.BOOKING.getValue(),booking + "n")
                .then()
                .statusCode(404);

    }

    @Test
    public void shouldGetAssessmentSummariesCRN() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.CRN.getValue(), crn)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, openAssessmentId, completeAssessmentId, voidedAssessmentId, layerOneAssessmentId));

        Optional<AssessmentSummaryDto> validAssessment = Arrays.stream(assessments).filter(a -> a.getAssessmentId().equals(openAssessmentId)).findFirst();
        assertThat(validAssessment.isPresent()).isTrue();

        validateOpenAssessmentSummary(validAssessment.get());
    }

    @Test
    public void shouldGetAssessmentSummariesCRNNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.CRN.getValue(),crn + "n")
                .then()
                .statusCode(404);

    }

    @Test
    public void shouldGetAssessmentSummariesNomis() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.NOMIS.getValue(), nomis)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, openAssessmentId, completeAssessmentId, voidedAssessmentId, layerOneAssessmentId));

        Optional<AssessmentSummaryDto> validAssessment = Arrays.stream(assessments).filter(a -> a.getAssessmentId().equals(openAssessmentId)).findFirst();
        assertThat(validAssessment.isPresent()).isTrue();

        validateOpenAssessmentSummary(validAssessment.get());
    }

    @Test
    public void shouldGetAssessmentSummariesNomisNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.NOMIS.getValue(),nomis + "n")
                .then()
                .statusCode(404);

    }

    @Test
    public void shouldGetAssessmentSummariesPNC() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.PNC.getValue(), pnc)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, openAssessmentId, completeAssessmentId, voidedAssessmentId, layerOneAssessmentId));

        Optional<AssessmentSummaryDto> validAssessment = Arrays.stream(assessments).filter(a -> a.getAssessmentId().equals(openAssessmentId)).findFirst();
        assertThat(validAssessment.isPresent()).isTrue();

        validateOpenAssessmentSummary(validAssessment.get());
    }

    @Test
    public void shouldGetAssessmentSummariesPNCNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary", OffenderIdentifier.PNC.getValue(),pnc + "n")
                .then()
                .statusCode(404);

    }

    public void validateOpenAssessmentSummary(AssessmentSummaryDto assessmentSummary) {
        assertThat(assessmentSummary.getAssessmentId()).isEqualTo(openAssessmentId);
        assertThat(assessmentSummary.getAssessmentStatus()).isEqualTo("OPEN");
        assertThat(assessmentSummary.getAssessmentType()).isEqualTo("LAYER_3");
        assertThat(assessmentSummary.getAssessorName()).isEqualTo("Probation Test");
        assertThat(assessmentSummary.getCompletedDateTime()).isEqualTo(LocalDateTime.of(2018,6,20, 23,0, 9));
        assertThat(assessmentSummary.getCreatedDateTime()).isEqualTo(LocalDateTime.of(2018,5,21, 23,0, 9));
        assertThat(assessmentSummary.getHistoricStatus()).isEqualTo("CURRENT");
        assertThat(assessmentSummary.getOasysScoringAlgorithmVersion()).isEqualTo(3L);
        assertThat(assessmentSummary.getRefAssessmentId()).isEqualTo(4L);
        assertThat(assessmentSummary.getRefAssessmentVersionCode()).isEqualTo("LAYER3");
        assertThat(assessmentSummary.getRefAssessmentVersionNumber()).isEqualTo("1");
        assertThat(assessmentSummary.getVoidedDateTime()).isNull();
    }

/*
    @Test
    public void canGetLatestAssessmentForOffenderPk() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments/latest", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }


    @Test
    public void canGetLatestAssessmentForOffenderCrn() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/assessments/latest", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }

    @Test
    public void canGetLatestAssessmentForOffenderPnc() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/assessments/latest", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }


    @Test
    public void canGetLatestAssessmentForOffenderNmisId() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/assessments/latest", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }


    @Test
    public void canGetLatestAssessmentForOffenderBookingId() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/assessments/latest", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }

    @Test
    public void canLookupAssessmentByOasysSetPk() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/assessments/oasysSetId/{0}", 0L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(0L);

    }*/

}