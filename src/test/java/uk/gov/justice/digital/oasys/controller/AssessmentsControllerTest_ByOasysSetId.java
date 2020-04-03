package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import uk.gov.justice.digital.oasys.api.AssessmentDto;
import uk.gov.justice.digital.oasys.service.domain.SectionHeader;

import java.time.LocalDateTime;


import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@Sql(scripts = "classpath:assessments/before-test-full.sql", config = @SqlConfig(transactionMode = ISOLATED))
@Sql(scripts = "classpath:assessments/after-test-full.sql", config = @SqlConfig(transactionMode = ISOLATED), executionPhase = AFTER_TEST_METHOD)
public class AssessmentsControllerTest_ByOasysSetId extends IntegrationTest {

    @Value("${sample.token}")
    private String validOauthToken;

    private Long validOasysSetId = 5433L;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));
    }

    @Test
    public void shouldGetAssessmentByOasysSetId() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/assessments/oasysSetId/{0}", validOasysSetId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);


        validateAssessment(assessment);
    }

    @Test
    public void shouldGetAssessmentSummariesOasysNotFound() {
         given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/assessments/oasysSetId/{0}", validOasysSetId + 5L)
                .then()
                .statusCode(404);

    }

    public void validateAssessment(AssessmentDto assessment) {
        assertThat(assessment.getAssessmentId()).isEqualTo(validOasysSetId);
        assertThat(assessment.getRefAssessmentVersionCode()).isEqualTo("LAYER3");
        assertThat(assessment.getRefAssessmentVersionNumber()).isEqualTo("1");
        assertThat(assessment.getRefAssessmentId()).isEqualTo(4L);
        assertThat(assessment.getAssessmentType()).isEqualTo("LAYER_3");
        assertThat(assessment.getAssessmentStatus()).isEqualTo("OPEN");
        assertThat(assessment.getHistoricStatus()).isEqualTo("CURRENT");
        assertThat(assessment.getRefAssessmentOasysScoringAlgorithmVersion()).isEqualTo(3L);
        assertThat(assessment.getAssessorName()).isEqualTo("Probation Test");
        assertThat(assessment.getCreatedDateTime()).isEqualTo(LocalDateTime.of(2018,5,21, 23,0, 9));
        assertThat(assessment.getCompletedDateTime()).isEqualTo(LocalDateTime.of(2018,6,20, 23,0, 9));
        assertThat(assessment.getVoidedDateTime()).isNull();

        //Sections check
        assertThat(assessment.getSections()).hasSize(17);

        //child safeguarding
        assertThat(assessment.getChildSafeguardingIndicated()).isTrue();

        //Example flagged as need
        var drugs = assessment.getLayer3SentencePlanNeeds().stream().filter(n -> n.getSection().equals(SectionHeader.DRUG_MISUSE)).findFirst().get();
        assertThat(drugs.getFlaggedAsNeed()).isTrue();

        //Example risk of harm
        var emotional = assessment.getLayer3SentencePlanNeeds().stream().filter(n -> n.getSection().equals(SectionHeader.EMOTIONAL_WELL_BEING)).findFirst().get();
        assertThat(emotional.getRiskOfHarm()).isTrue();

        //Example risk of reoffending
        var alcohol = assessment.getLayer3SentencePlanNeeds().stream().filter(n -> n.getSection().equals(SectionHeader.ALCOHOL_MISUSE)).findFirst().get();
        assertThat(alcohol.getRiskOfReoffending()).isTrue();

        //Example over threshold
        var relationships = assessment.getLayer3SentencePlanNeeds().stream().filter(n -> n.getSection().equals(SectionHeader.RELATIONSHIPS)).findFirst().get();
        assertThat(relationships.getOverThreshold()).isTrue();

    }

}