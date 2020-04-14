package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import uk.gov.justice.digital.oasys.api.ErrorResponse;
import uk.gov.justice.digital.oasys.api.OffenderDto;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@Sql(scripts = "classpath:offender/before-test.sql", config = @SqlConfig(transactionMode = ISOLATED))
@Sql(scripts = "classpath:offender/after-test.sql", config = @SqlConfig(transactionMode = ISOLATED), executionPhase = AFTER_TEST_METHOD)
public class OffenderControllerTest extends IntegrationTest {

    @Value("${sample.token}")
    private String validOauthToken;
    private Long oasysOffenderId = 1234L;
    private Long deletedOasysOffenderId = 4321L;
    private String pnc = "PNC";
    private String crn = "CRN";
    private String nomis = "NOMIS";
    private String booking = "BOOKIN";
    private String deletedPnc = "DPNC";
    private String deletedCrn = "DCRN";
    private String deletedNomis = "DNOMIS";
    private String deletedBooking = "DBOOK";

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));
    }

    @Test
    public void shouldGetOffenderOasys() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        validateOffender(offender);
    }

    @Test
    public void shouldGetDeletedOffenderOasys() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.OASYS.getValue(), deletedOasysOffenderId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        assertThat(offender.getOasysOffenderId()).isEqualTo(deletedOasysOffenderId);
        assertThat(offender.isLimitedAccessOffender()).isTrue();
        assertThat(offender.getFamilyName()).isEqualTo("Offender");
        assertThat(offender.getForename1()).isEqualTo("Mike");
        assertThat(offender.getForename2()).isEqualTo("Tom");
        assertThat(offender.getForename3()).isEqualTo("Steve");
        assertThat(offender.getRiskToOthers()).isEqualTo("Y");
        assertThat(offender.getRiskToSelf()).isEqualTo("N");
        assertThat(offender.getPnc()).isEqualTo(pnc);
        assertThat(offender.getCrn()).isEqualTo(crn);
        assertThat(offender.getNomisId()).isEqualTo(nomis);
        assertThat(offender.getLegacyCmsProbNumber()).isEqualTo("LEGACYCMS");
        assertThat(offender.getCroNumber()).isEqualTo("CRO");
        assertThat(offender.getBookingNumber()).isEqualTo(booking);
        assertThat(offender.getMergePncNumber()).isEqualTo("MPNC");
    }

    @Test
    public void shouldGetOffenderOasysNotFound() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.OASYS.getValue(), oasysOffenderId + 1)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderBooking() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.BOOKING.getValue(), booking)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        validateOffender(offender);
    }

    @Test
    public void shouldNotGetDeletedOffenderBooking() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.BOOKING.getValue(), deletedBooking)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderBookingNotFound() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.BOOKING.getValue(), booking + "n")
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderCRN() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.CRN.getValue(), crn)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        validateOffender(offender);
    }

    @Test
    public void shouldNotGetDeletedOffenderCRN() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.CRN.getValue(), deletedCrn)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderCRNNotFound() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.CRN.getValue(), crn + "n")
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderNomis() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.NOMIS.getValue(), nomis)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        validateOffender(offender);
    }

    @Test
    public void shouldNotGetDeletedOffenderNomis() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.NOMIS.getValue(), deletedNomis)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderNomisNotFound() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.NOMIS.getValue(), nomis + "n")
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderPNC() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.PNC.getValue(), pnc)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        validateOffender(offender);
    }

    @Test
    public void shouldNotGetDeletedOffenderPNC() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.PNC.getValue(), deletedPnc)
                .then()
                .statusCode(404);
    }

    @Test
    public void shouldGetOffenderPNCNotFound() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.PNC.getValue(), pnc + "n")
                .then()
                .statusCode(404);
    }


    @Test
    public void shouldGetMergedOffender() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.OASYS.getValue(), 100)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        assertThat(offender.getOasysOffenderId()).isEqualTo(300);
        assertThat(offender.getMergedOasysOffenderId()).isEqualTo(100);
    }

    @Test
    public void shouldGetMergedTwiceOffender() {

        var offender = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.OASYS.getValue(), 400)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderDto.class);

        assertThat(offender.getOasysOffenderId()).isEqualTo(800);
        assertThat(offender.getMergedOasysOffenderId()).isEqualTo(400);
    }

    @Test
    public void shouldReturnErrorWhenDuplicatePNCOffendersFound() {

        var result = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.PNC.getValue(), "PNCD")
                .then()
                .statusCode(409)
                .extract()
                .body()
                .as(ErrorResponse.class);

        assertThat(result.getDeveloperMessage()).contains("Duplicate offender found for PNC PNCD");
        assertThat(result.getUserMessage()).contains("Duplicate offender found for PNC PNCD");
        assertThat(result.getStatus()).isEqualTo(409);
    }

    @Test
    public void shouldReturnErrorWhenDuplicateCRNOffendersFound() {

        var result = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.CRN.getValue(), "CRND")
                .then()
                .statusCode(409)
                .extract()
                .body()
                .as(ErrorResponse.class);

        assertThat(result.getDeveloperMessage()).contains("Duplicate offender found for CRN CRND");
        assertThat(result.getUserMessage()).contains("Duplicate offender found for CRN CRND");
        assertThat(result.getStatus()).isEqualTo(409);
    }

    @Test
    public void shouldReturnErrorWhenDuplicateNomisOffendersFound() {

        var result = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.NOMIS.getValue(), "NOMISD")
                .then()
                .statusCode(409)
                .extract()
                .body()
                .as(ErrorResponse.class);

        assertThat(result.getDeveloperMessage()).contains("Duplicate offender found for NOMIS NOMISD");
        assertThat(result.getUserMessage()).contains("Duplicate offender found for NOMIS NOMISD");
        assertThat(result.getStatus()).isEqualTo(409);
    }

    private void validateOffender(OffenderDto offender) {
        assertThat(offender.getOasysOffenderId()).isEqualTo(oasysOffenderId);
        assertThat(offender.isLimitedAccessOffender()).isTrue();
        assertThat(offender.getFamilyName()).isEqualTo("Offender");
        assertThat(offender.getForename1()).isEqualTo("Mike");
        assertThat(offender.getForename2()).isEqualTo("Tom");
        assertThat(offender.getForename3()).isEqualTo("Steve");
        assertThat(offender.getRiskToOthers()).isEqualTo("Y");
        assertThat(offender.getRiskToSelf()).isEqualTo("N");
        assertThat(offender.getPnc()).isEqualTo(pnc);
        assertThat(offender.getCrn()).isEqualTo(crn);
        assertThat(offender.getNomisId()).isEqualTo(nomis);
        assertThat(offender.getLegacyCmsProbNumber()).isEqualTo("LEGACYCMS");
        assertThat(offender.getCroNumber()).isEqualTo("CRO");
        assertThat(offender.getBookingNumber()).isEqualTo(booking);
        assertThat(offender.getMergePncNumber()).isEqualTo("MPNC");
    }

}
