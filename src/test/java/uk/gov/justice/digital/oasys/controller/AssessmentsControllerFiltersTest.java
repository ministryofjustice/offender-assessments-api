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

import java.util.Arrays;
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
public class AssessmentsControllerFiltersTest {

    @LocalServerPort
    int port;

    @Autowired
    @Qualifier("globalObjectMapper")
    private ObjectMapper objectMapper;

    @Value("${sample.token}")
    private String validOauthToken;

    private Long oasysOffenderId = 1234L;

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
    public void shouldGetAssessmentSummariesOasysFilterByAssessmentStatus() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?assessmentStatus={2}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "complete")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        // We only expect to see completed assessments.
        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, voidedAssessmentId, completeAssessmentId));
    }

    @Test
    public void shouldGetAssessmentSummariesOasysFilterByVoided() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?voided={2}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "true")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        // We only expect to see one voided assessment.
        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(voidedAssessmentId));
    }

    @Test
    public void shouldGetAssessmentSummariesOasysFilterByVoidedFalse() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?voided={2}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "false")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        // We expect to see the non voided cases.
        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, openAssessmentId, completeAssessmentId, layerOneAssessmentId));
    }

    @Test
    public void shouldGetAssessmentSummariesOasysFilterByAssessmentType() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?assessmentType={2}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "LayER_3")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId, openAssessmentId, completeAssessmentId, voidedAssessmentId));
    }

    @Test
    public void shouldGetAssessmentSummariesOasysFilterByAssessmentTypeOther() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?assessmentType={2}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "LayER_1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(layerOneAssessmentId));
    }

    @Test
    public void shouldGetAssessmentSummariesOasysFilterByHistoricStatusCurrent() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?historicStatus={2}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "CurreNT")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(openAssessmentId, completeAssessmentId, voidedAssessmentId, layerOneAssessmentId));
    }

    @Test
    public void shouldGetAssessmentSummariesOasysFilterByHistoricStatusOther() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?historicStatus={2}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "OTheR")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(historicAssessmentId));
    }

    @Test
    public void shouldGetAssessmentSummariesOasysFilterByHistoricStatusAssessmentTypeCombined() {
        AssessmentSummaryDto[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}/assessments/summary?historicStatus={2}&assessmentType={3}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId, "CurreNT", "Layer_3")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummaryDto[].class);

        //Lose the layer 1 assessment.
        assertThat(Arrays.stream(assessments).map(AssessmentSummaryDto::getAssessmentId)).containsOnlyElementsOf(Set.of(openAssessmentId, completeAssessmentId, voidedAssessmentId));
    }

}