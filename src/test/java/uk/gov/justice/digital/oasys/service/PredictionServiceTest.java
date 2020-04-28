package uk.gov.justice.digital.oasys.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import uk.gov.justice.digital.oasys.jpa.repository.SimpleAssessmentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PredictionServiceTest {

    @Mock
    OffenderService offenderService;

    @Mock
    Assessment assessment;

    @Mock
    RefAssessmentVersion version;

    @Mock
    SimpleAssessmentRepository simpleAssessmentRepository;

    PredictionService predictionService;

    @BeforeEach
    public void setUp(){
        predictionService = new PredictionService(offenderService, simpleAssessmentRepository);
        when(offenderService.getOffenderIdByIdentifier("OASYS", "1")).thenReturn(1L);
        setupVersion();
    }

    @Test
    public void shouldGetAllOgpForOffender() {
        setupAssessment();

        when(simpleAssessmentRepository.getAssessmentsForOffender(1L)).thenReturn(Set.of(assessment));

        var predictors = predictionService.getAllPredictorsForOffender("OASYS", "1");

        assertThat(predictors).extracting("oasysSetId").containsOnly(assessment.getOasysSetPk());
        assertThat(predictors).extracting("refAssessmentVersionCode").containsOnly(version.getRefAssVersionCode());
        assertThat(predictors).extracting("refAssessmentVersionNumber").containsOnly(version.getVersionNumber());
        assertThat(predictors).extracting("refAssessmentId").containsOnly(version.getRefAssVersionUk());
        assertThat(predictors).extracting("completedDate").containsOnly(assessment.getDateCompleted());
        assertThat(predictors).extracting("voidedDateTime").containsOnly(assessment.getAssessmentVoidedDate());
        assertThat(predictors).extracting("assessmentCompleted").containsOnly(true);

        //OGP
        assertThat(predictors).extracting("ogp.ogpStaticWeightedScore").containsOnly(assessment.getOgpStWesc());
        assertThat(predictors).extracting("ogp.ogpDynamicWeightedScore").containsOnly(assessment.getOgpDyWesc());
        assertThat(predictors).extracting("ogp.ogpTotalWeightedScore").containsOnly(assessment.getOgpTotWesc());
        assertThat(predictors).extracting("ogp.ogp1Year").containsOnly(assessment.getOgp1Year());
        assertThat(predictors).extracting("ogp.ogp2Year").containsOnly(assessment.getOgp2Year());
        assertThat(predictors).extracting("ogp.ogpRisk.description").containsOnly(assessment.getOgpRiskRecon().getRefElementDesc());

        //OVP
        assertThat(predictors).extracting("ovp.ovpStaticWeightedScore").containsOnly(assessment.getOvpStWesc());
        assertThat(predictors).extracting("ovp.ovpDynamicWeightedScore").containsOnly(assessment.getOvpDyWesc());
        assertThat(predictors).extracting("ovp.ovpTotalWeightedScore").containsOnly(assessment.getOvpTotWesc());
        assertThat(predictors).extracting("ovp.ovp1Year").containsOnly(assessment.getOvp1Year());
        assertThat(predictors).extracting("ovp.ovp2Year").containsOnly(assessment.getOvp2Year());
        assertThat(predictors).extracting("ovp.ovpRisk.description").containsOnly(assessment.getOvpRiskRecon().getRefElementDesc());
        assertThat(predictors).extracting("ovp.ovpPreviousWeightedScore").containsOnly(assessment.getOvpPrevWesc());
        assertThat(predictors).extracting("ovp.ovpViolentWeightedScore").containsOnly(assessment.getOvpVioWesc());
        assertThat(predictors).extracting("ovp.ovpNonViolentWeightedScore").containsOnly(assessment.getOvpNonVioWesc());
        assertThat(predictors).extracting("ovp.ovpAgeWeightedScore").containsOnly(assessment.getOvpAgeWesc());
        assertThat(predictors).extracting("ovp.ovpSexWeightedScore").containsOnly(assessment.getOvpSexWesc());


        //OGRs
        assertThat(predictors).extracting("ogr3.ogrs3_1Year").containsOnly(assessment.getOgrs31Year());
        assertThat(predictors).extracting("ogr3.ogrs3_2Year").containsOnly(assessment.getOgrs32Year());
        assertThat(predictors).extracting("ogr3.reconvictionRisk.description").containsOnly(assessment.getOgrs3RiskRecon().getRefElementDesc());


    }


    private void setupVersion() {
        when(version.getRefAssVersionUk()).thenReturn(1L);
        when(version.getVersionNumber()).thenReturn("Any Version");
        when(version.getRefAssVersionCode()).thenReturn("Any Ref Version Code");
    }

    private void setupAssessment() {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime completed = created.plusMonths(3);
        LocalDateTime voided = created.plusMonths(4);

        when(assessment.getOasysSetPk()).thenReturn(1234L);
        when(assessment.getAssessmentStatus()).thenReturn("STATUS");
        when(assessment.getAssessmentType()).thenReturn("LAYER_3");
        when(assessment.getCreateDate()).thenReturn(created);
        when(assessment.getDateCompleted()).thenReturn(completed);
        when(assessment.getAssessmentVoidedDate()).thenReturn(voided);
        when(assessment.getAssessmentVersion()).thenReturn(version);

        //OGP
        when(assessment.getOgpStWesc()).thenReturn(BigDecimal.valueOf(1));
        when(assessment.getOgpDyWesc()).thenReturn(BigDecimal.valueOf(2));
        when(assessment.getOgpTotWesc()).thenReturn(BigDecimal.valueOf(3));
        when(assessment.getOgp1Year()).thenReturn(BigDecimal.valueOf(4));
        when(assessment.getOgp2Year()).thenReturn(BigDecimal.valueOf(5));
        when(assessment.getOgpRiskRecon()).thenReturn(RefElement.builder().refElementDesc("Low").refElementCode("L").build());

        //OVP
        when(assessment.getOvp1Year()).thenReturn(BigDecimal.valueOf(1));
        when(assessment.getOvp2Year()).thenReturn(BigDecimal.valueOf(2));
        when(assessment.getOvpAgeWesc()).thenReturn(BigDecimal.valueOf(3));
        when(assessment.getOvpDyWesc()).thenReturn(BigDecimal.valueOf(4));
        when(assessment.getOvpNonVioWesc()).thenReturn(BigDecimal.valueOf(5));
        when(assessment.getOvpPrevWesc()).thenReturn(BigDecimal.valueOf(6));
        when(assessment.getOvpSexWesc()).thenReturn(BigDecimal.valueOf(7));
        when(assessment.getOvpStWesc()).thenReturn(BigDecimal.valueOf(8));
        when(assessment.getOvpTotWesc()).thenReturn(BigDecimal.valueOf(9));
        when(assessment.getOvpVioWesc()).thenReturn(BigDecimal.valueOf(10));
        when(assessment.getOvpRiskRecon()).thenReturn(RefElement.builder().refElementDesc("Low").refElementCode("L").build());

        //OGRs
        when(assessment.getOgrs31Year()).thenReturn(BigDecimal.valueOf(4));
        when(assessment.getOgrs32Year()).thenReturn(BigDecimal.valueOf(5));
        when(assessment.getOgrs3RiskRecon()).thenReturn(RefElement.builder().refElementDesc("Low").refElementCode("L").build());

    }
}