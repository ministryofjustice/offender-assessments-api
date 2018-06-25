package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
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
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.justice.digital.oasys.api.Assessment;
import uk.gov.justice.digital.oasys.api.Ogp;
import uk.gov.justice.digital.oasys.api.Ogrs;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    @Qualifier("globalObjectMapper")
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));

        Mockito.when(offenderRepository.findByCmsProbNumber(eq("crn1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.findByCmsProbNumber(eq("crn2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.findByCmsPrisNumber(eq("nomisId1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.findByCmsPrisNumber(eq("nomisId2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.findByPrisonNumber(eq("bookingId1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.findByPrisonNumber(eq("bookingId2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.findByPnc(eq("pnc1"))).thenReturn(anOffender());
        Mockito.when(offenderRepository.findByPnc(eq("pnc2"))).thenReturn(Optional.empty());
        Mockito.when(offenderRepository.findOne(eq(1L))).thenReturn(anOffender().get());
        Mockito.when(offenderRepository.findOne(eq(2L))).thenReturn(null);

    }

    private Optional<Offender> anOffender() {
        return Optional.ofNullable(Offender.builder()
                .oasysAssessmentGroups(anAssessmentGroup())
                .build());
    }

    private List<OasysAssessmentGroup> anAssessmentGroup() {
        return ImmutableList.of(OasysAssessmentGroup.builder()
                .oasysAssessmentGroupPk(1L)
                .oasysSets(someOasysSets())
                .build());
    }

    private List<OasysSet> someOasysSets() {
        return ImmutableList.of(OasysSet.builder()
                        .createDate(new Timestamp(System.currentTimeMillis()))
                        .assessmentType(assessmentType("oasys"))
                        .oasysSetPk(1L)
                        .ogrs31Year(BigDecimal.ONE)
                        .ogrs32Year(BigDecimal.TEN)
                        .ogp1Year(BigDecimal.ONE)
                        .ogp2Year(BigDecimal.TEN)
                        .ogpDyWesc(BigDecimal.ONE)
                        .ogpStWesc(BigDecimal.TEN)
                        .ogpTotWesc(BigDecimal.ONE)
                        .ovpStWesc(BigDecimal.TEN)
                        .ovpDyWesc(BigDecimal.ONE)
                        .ovpTotWesc(BigDecimal.TEN)
                        .ovp1Year(BigDecimal.ONE)
                        .ovp2Year(BigDecimal.TEN)
                        .ovpPrevWesc(BigDecimal.ONE)
                        .ovpVioWesc(BigDecimal.TEN)
                        .ovpNonVioWesc(BigDecimal.ONE)
                        .ovpAgeWesc(BigDecimal.TEN)
                        .ovpSexWesc(BigDecimal.ONE)
                        .group(aGroup("HISTORIC"))
                        .assessmentStatus(anAssessmentStatus("OPEN"))
                        .build(),
                OasysSet.builder()
                        .createDate(new Timestamp(System.currentTimeMillis()))
                        .assessmentType(assessmentType("sara"))
                        .oasysSetPk(2L)
                        .ogrs31Year(BigDecimal.TEN)
                        .ogrs32Year(BigDecimal.ONE)
                        .ogp1Year(BigDecimal.TEN)
                        .ogp2Year(BigDecimal.ONE)
                        .ogpDyWesc(BigDecimal.TEN)
                        .ogpStWesc(BigDecimal.ONE)
                        .ogpTotWesc(BigDecimal.TEN)
                        .ovpStWesc(BigDecimal.ONE)
                        .ovpDyWesc(BigDecimal.TEN)
                        .ovpTotWesc(BigDecimal.ONE)
                        .ovp1Year(BigDecimal.TEN)
                        .ovp2Year(BigDecimal.ONE)
                        .ovpPrevWesc(BigDecimal.TEN)
                        .ovpVioWesc(BigDecimal.ONE)
                        .ovpNonVioWesc(BigDecimal.TEN)
                        .ovpAgeWesc(BigDecimal.ONE)
                        .ovpSexWesc(BigDecimal.TEN)
                        .group(aGroup("CURRENT"))
                        .assessmentStatus(anAssessmentStatus("COMPLETE"))
                        .assessmentVoidedDate(new Timestamp(System.currentTimeMillis()))
                        .build());
    }

    private RefElement anAssessmentStatus(String status) {
        return RefElement.builder().refElementCode(status).build();
    }

    private OasysAssessmentGroup aGroup(String status) {
        return OasysAssessmentGroup.builder()
                .historicStatusELm(status)
                .build();
    }

    private RefElement assessmentType(String type) {
        return RefElement.builder().refElementCode(type).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canGetOgrsForOffenderCRNs() {
        Ogrs[] ogrss = given()
                .when()
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
                .get("/offenders/crn/{0}/ogrs3", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOffenderCRNs() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/crn/{0}/ogp", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderGivesNotFound() {
        given()
                .when()
                .get("/offenders/crn/{0}/ogp", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOffenderCRNs() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/crn/{0}/ovp", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderGivesNotFound() {
        given()
                .when()
                .get("/offenders/crn/{0}/ovp", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOffenderPnc() {
        Ogrs[] ogrss = given()
                .when()
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
                .get("/offenders/pnc/{0}/ogrs3", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOffenderPnc() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/pnc/{0}/ogp", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderPncGivesNotFound() {
        given()
                .when()
                .get("/offenders/pnc/{0}/ogp", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOffenderPnc() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/pnc/{0}/ovp", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderPncGivesNotFound() {
        given()
                .when()
                .get("/offenders/pnc/{0}/ovp", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOffenderNomisId() {
        Ogrs[] ogrss = given()
                .when()
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
                .get("/offenders/nomisId/{0}/ogrs3", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOffenderNomisId() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/nomisId/{0}/ogp", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .get("/offenders/nomisId/{0}/ogp", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOffenderNomisId() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/nomisId/{0}/ovp", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .get("/offenders/nomisId/{0}/ovp", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOffenderBookingId() {
        Ogrs[] ogrss = given()
                .when()
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
                .get("/offenders/bookingId/{0}/ogrs3", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOffenderBookingId() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/bookingId/{0}/ogp", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .get("/offenders/bookingId/{0}/ogp", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOffenderBookingId() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/bookingId/{0}/ovp", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .get("/offenders/bookingId/{0}/ovp", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgrsForOasysOffenderPk() {
        Ogrs[] ogrss = given()
                .when()
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
                .get("/offenders/oasysOffenderId/{0}/ogrs3", 2L)
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOgpForOasysOffenderPk() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/oasysOffenderId/{0}/ogp", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOgpForUnknownOasysOffenderPkGivesNotFound() {
        given()
                .when()
                .get("/offenders/oasysOffenderId/{0}/ogp", 2L)
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetOvpForOasysOffenderPk() {
        Ogp[] ogps = given()
                .when()
                .get("/offenders/oasysOffenderId/{0}/ovp", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Ogp[].class);

        assertThat(ogps).extracting("oasysSetId").containsExactly(1L, 2L);
    }

    @Test
    public void getOvpForUnknownOasysOffenderPkGivesNotFound() {
        given()
                .when()
                .get("/offenders/oasysOffenderId/{0}/ovp", 2L)
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderCRN() {
        Assessment[] assessments = given()
                .when()
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByAssessmentType() {
        Assessment[] assessments = given()
                .when()
                .param("assessmentType", "oasys")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).extracting("assessmentType").containsOnly("oasys");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByHistoricStatus() {
        Assessment[] assessments = given()
                .when()
                .param("historicStatus", "CURRENT")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).extracting("historicStatus").containsOnly("CURRENT");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByAssessmentStatus() {
        Assessment[] assessments = given()
                .when()
                .param("assessmentStatus", "COMPLETE")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).extracting("assessmentStatus").containsOnly("COMPLETE");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredBVoided() {
        Assessment[] assessments = given()
                .when()
                .param("voided", "true")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).extracting("voided").containsOnly(true);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredBNotVoided() {
        Assessment[] assessments = given()
                .when()
                .param("voided", "false")
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).extracting("voided").containsOnly(false);
    }

}