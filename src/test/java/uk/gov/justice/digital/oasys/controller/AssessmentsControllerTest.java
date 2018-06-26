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
import uk.gov.justice.digital.oasys.api.AssessmentResource;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.AssessmentRepository;
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

    @MockBean
    private AssessmentRepository assessmentRepository;

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

        Mockito.when(assessmentRepository.findOne(eq(0L))).thenReturn(anOasysSet(0L));

    }

    private OasysSet anOasysSet(Long id) {
        return OasysSet.builder()
                .assessmentType(RefElement.builder().build())
                .group(OasysAssessmentGroup.builder().build())
                .assessmentStatus(RefElement.builder().build())
                .oasysSetPk(id).build();
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
    public void canGetAssessmentsForOffenderPk() {
        Assessment[] assessments = given()
                .when()
                .get("/offenders/oasysOffenderId/{0}/assessments", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void getAssessmentForUnknownOffenderPkGivesNotFound() {
        given()
                .when()
                .get("/offenders/oasysOffenderId/{0}/assessments", 2L)
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderCRN() {
        AssessmentResource[] assessments = given()
                .when()
                .get("/offenders/crn/{0}/assessments", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentResource[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByAssessmentType() {
        AssessmentResource[] assessments = given()
    public void getAssessmentForUnknownOffenderCRNGivesNotFound() {
        given()
                .when()
                .get("/offenders/crn/{0}/assessments", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderPNC() {
        Assessment[] assessments = given()
                .when()
                .get("/offenders/pnc/{0}/assessments", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void getAssessmentForUnknownOffenderPNCGivesNotFound() {
        given()
                .when()
                .get("/offenders/pnc/{0}/assessments", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderNomisId() {
        Assessment[] assessments = given()
                .when()
                .get("/offenders/nomisId/{0}/assessments", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void getAssessmentForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .get("/offenders/nomisId/{0}/assessments", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderBookingId() {
        Assessment[] assessments = given()
                .when()
                .get("/offenders/bookingId/{0}/assessments", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void getAssessmentForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .get("/offenders/bookingId/{0}/assessments", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFilteredByAssessmentType() {
        Assessment[] assessments = given()
                .when()
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
                .get("/assessments/oasysSetId/{0}", 0L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Assessment.class);

        assertThat(assessment).extracting("oasysSetId").containsExactly(0L);

    }

}