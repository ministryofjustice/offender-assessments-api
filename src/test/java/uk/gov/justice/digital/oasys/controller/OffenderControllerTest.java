package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
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
    private String pnc = "PNC";
    private String crn = "CRN";
    private String nomis = "NOMIS";
    private String booking = "BOOKIN";

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
    public void shouldGetOffenderPNCNotFound() {

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/{0}/{1}", OffenderIdentifier.PNC.getValue(), pnc + "n")
                .then()
                .statusCode(404);
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
