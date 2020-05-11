package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.assertj.core.api.BigDecimalAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import uk.gov.justice.digital.oasys.api.Predictor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@Sql(scripts = "classpath:ogr-ovp-ogp/before-test.sql", config = @SqlConfig(transactionMode = ISOLATED))
@Sql(scripts = "classpath:ogr-ovp-ogp/after-test.sql", config = @SqlConfig(transactionMode = ISOLATED), executionPhase = AFTER_TEST_METHOD)
public class PredictorsControllerTest extends IntegrationTest {

    @Value("${sample.token}")
    private String validOauthToken;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));
    }

    @Test
    public void canGetOgpForOffenderCRNs() {
        Predictor[] predictors = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/predictors", "CRN")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Predictor[].class);

        assertThat(predictors).extracting("oasysSetId").containsOnly(5431L, 5430L);
        validatePredictors(predictors);
    }

    @Test
    public void getPredictorsForUnknownOffenderGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/predictors", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetPredictorsForOffenderPnc() {
        Predictor[] predictors = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/predictors", "PNC")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Predictor[].class);

        assertThat(predictors).extracting("oasysSetId").containsOnly(5431L,5430L);
        validatePredictors(predictors);
    }

    @Test
    public void getPredictorsForUnknownOffenderPncGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/predictors", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetPredictorsForOffenderNomisId() {
        Predictor[] predictors = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/predictors", "NOMIS")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Predictor[].class);

        assertThat(predictors).extracting("oasysSetId").containsOnly(5431L, 5430L);
        validatePredictors(predictors);
    }

    @Test
    public void getPredictorsForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/predictors", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetPredictorsForOffenderBookingId() {
        Predictor[] predictors = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/predictors", "BOOKIN")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Predictor[].class);

        assertThat(predictors).extracting("oasysSetId").containsOnly(5431L, 5430L);
        validatePredictors(predictors);
    }

    @Test
    public void getPredictorsForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/predictors", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetPredictorsForOasysOffenderPk() {
        Predictor[] predictors = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/predictors", 1234)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Predictor[].class);

        assertThat(predictors).extracting("oasysSetId").containsOnly(5431L, 5430L);
        validatePredictors(predictors);
    }

    @Test
    public void getPredictorsForUnknownOasysOffenderPkGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/predictors", 2L)
                .then()
                .statusCode(404);
    }

    private void validatePredictors(Predictor[] predictors) {

        var predictor1 = Arrays.stream(predictors).filter(p->p.getOasysSetId().equals(5430L)).findFirst().get();
        var predictor2 = Arrays.stream(predictors).filter(p->p.getOasysSetId().equals(5431L)).findFirst().get();

        //Oasys Set 5430L
        assertThat(predictor1).extracting("oasysSetId").isEqualTo(5430L);
        assertThat(predictor1).extracting("refAssessmentVersionCode").isEqualTo("LAYER3");
        assertThat(predictor1).extracting("refAssessmentVersionNumber").isEqualTo("1");
        assertThat(predictor1).extracting("refAssessmentId").isEqualTo(4L);
        assertThat(predictor1).extracting("completedDate").isEqualTo(null);
        assertThat(predictor1).extracting("voidedDateTime").isEqualTo(null);
        assertThat(predictor1).extracting("assessmentCompleted").isEqualTo(false);

        //OGP
        assertThat(predictor1).extracting("ogp.ogpStaticWeightedScore").isEqualTo(BigDecimal.valueOf(3));
        assertThat(predictor1).extracting("ogp.ogpDynamicWeightedScore").isEqualTo(BigDecimal.valueOf(7));
        assertThat(predictor1).extracting("ogp.ogpTotalWeightedScore").isEqualTo(BigDecimal.valueOf(10));
        assertThat(predictor1).extracting("ogp.ogp1Year").isEqualTo(BigDecimal.valueOf(4));
        assertThat(predictor1).extracting("ogp.ogp2Year").isEqualTo(BigDecimal.valueOf(8));
        assertThat(predictor1).extracting("ogp.ogpRisk.description").isEqualTo("Low");
        assertThat(predictor1).extracting("ogp.ogpRisk.code").isEqualTo("L");

        //OVP
        assertThat(predictor1).extracting("ovp.ovpStaticWeightedScore").isEqualTo(BigDecimal.valueOf(14));
        assertThat(predictor1).extracting("ovp.ovpDynamicWeightedScore").isEqualTo(BigDecimal.valueOf(3));
        assertThat(predictor1).extracting("ovp.ovpTotalWeightedScore").isEqualTo(BigDecimal.valueOf(17));
        assertThat(predictor1).extracting("ovp.ovp1Year").isEqualTo(BigDecimal.valueOf(4));
        assertThat(predictor1).extracting("ovp.ovp2Year").isEqualTo(BigDecimal.valueOf(7));
        assertThat(predictor1).extracting("ovp.ovpRisk.description").isEqualTo("Low");
        assertThat(predictor1).extracting("ovp.ovpRisk.code").isEqualTo("L");
        assertThat(predictor1).extracting("ovp.ovpPreviousWeightedScore").isEqualTo(BigDecimal.valueOf(5));
        assertThat(predictor1).extracting("ovp.ovpViolentWeightedScore").isEqualTo(BigDecimal.valueOf(4));
        assertThat(predictor1).extracting("ovp.ovpNonViolentWeightedScore").isEqualTo(BigDecimal.valueOf(0));
        assertThat(predictor1).extracting("ovp.ovpAgeWeightedScore").isEqualTo(BigDecimal.valueOf(0));
        assertThat(predictor1).extracting("ovp.ovpSexWeightedScore").isEqualTo(BigDecimal.valueOf(5));

        //OGRs
        assertThat(predictor1).extracting("ogr3.ogrs3_1Year").isEqualTo(BigDecimal.valueOf(3));
        assertThat(predictor1).extracting("ogr3.ogrs3_2Year").isEqualTo(BigDecimal.valueOf(5));
        assertThat(predictor1).extracting("ogr3.reconvictionRisk.description").isEqualTo("Low");
        assertThat(predictor1).extracting("ogr3.reconvictionRisk.code").isEqualTo("L");


        //Oasys Set 5431L
        assertThat(predictor2).extracting("oasysSetId").isEqualTo(5431L);
        assertThat(predictor2).extracting("refAssessmentVersionCode").isEqualTo("LAYER3");
        assertThat(predictor2).extracting("refAssessmentVersionNumber").isEqualTo("1");
        assertThat(predictor2).extracting("refAssessmentId").isEqualTo(4L);
        assertThat(predictor2).extracting("completedDate").isEqualTo(LocalDateTime.of(2018,07,21,23,0,9));
        assertThat(predictor2).extracting("voidedDateTime").isEqualTo(LocalDateTime.of(2018,06,20,23,0,9));
        assertThat(predictor2).extracting("assessmentCompleted").isEqualTo(true);

        //OGP
        assertThat(predictor2).extracting("ogp.ogpStaticWeightedScore").isEqualTo(BigDecimal.valueOf(3));
        assertThat(predictor2).extracting("ogp.ogpDynamicWeightedScore").isEqualTo(BigDecimal.valueOf(7));
        assertThat(predictor2).extracting("ogp.ogpTotalWeightedScore").isEqualTo(BigDecimal.valueOf(10));
        assertThat(predictor2).extracting("ogp.ogp1Year").isEqualTo(BigDecimal.valueOf(4));
        assertThat(predictor2).extracting("ogp.ogp2Year").isEqualTo(BigDecimal.valueOf(8));
        assertThat(predictor2).extracting("ogp.ogpRisk.description").isEqualTo("Low");
        assertThat(predictor2).extracting("ogp.ogpRisk.code").isEqualTo("L");

        //OVP
        assertThat(predictor2).extracting("ovp.ovpStaticWeightedScore").isEqualTo(BigDecimal.valueOf(14));
        assertThat(predictor2).extracting("ovp.ovpDynamicWeightedScore").isEqualTo(BigDecimal.valueOf(3));
        assertThat(predictor2).extracting("ovp.ovpTotalWeightedScore").isEqualTo(BigDecimal.valueOf(17));
        assertThat(predictor2).extracting("ovp.ovp1Year").isEqualTo(BigDecimal.valueOf(4));
        assertThat(predictor2).extracting("ovp.ovp2Year").isEqualTo(BigDecimal.valueOf(7));
        assertThat(predictor2).extracting("ovp.ovpRisk.description").isEqualTo("Low");
        assertThat(predictor2).extracting("ovp.ovpRisk.code").isEqualTo("L");
        assertThat(predictor2).extracting("ovp.ovpPreviousWeightedScore").isEqualTo(BigDecimal.valueOf(5));
        assertThat(predictor2).extracting("ovp.ovpViolentWeightedScore").isEqualTo(BigDecimal.valueOf(4));
        assertThat(predictor2).extracting("ovp.ovpNonViolentWeightedScore").isEqualTo(BigDecimal.valueOf(0));
        assertThat(predictor2).extracting("ovp.ovpAgeWeightedScore").isEqualTo(BigDecimal.valueOf(0));
        assertThat(predictor2).extracting("ovp.ovpSexWeightedScore").isEqualTo(BigDecimal.valueOf(5));

        //OGRs
        assertThat(predictor2).extracting("ogr3.ogrs3_1Year").isEqualTo(BigDecimal.valueOf(3));
        assertThat(predictor2).extracting("ogr3.ogrs3_2Year").isEqualTo(BigDecimal.valueOf(5));
        assertThat(predictor2).extracting("ogr3.reconvictionRisk.description").isEqualTo("Low");
        assertThat(predictor2).extracting("ogr3.reconvictionRisk.code").isEqualTo("L");
    }
}