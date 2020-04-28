package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PredictorTest {

    @Mock
    RefAssessmentVersion version;

    @Mock
    Assessment assessment;

    @BeforeEach
    public void setUp() {
        setupVersion();
    }

    @Test
    public void shouldBuildValidDto() {
        setupAssessment();

        var predictor = Predictor.from(assessment);

        assertThat(predictor.getOasysSetId()).isEqualTo(assessment.getOasysSetPk());
        assertThat(predictor.getRefAssessmentVersionCode()).isEqualTo(version.getRefAssVersionCode());
        assertThat(predictor.getRefAssessmentVersionNumber()).isEqualTo(version.getVersionNumber());
        assertThat(predictor.getRefAssessmentId()).isEqualTo(version.getRefAssVersionUk());
        assertThat(predictor.getCompletedDate()).isEqualTo(assessment.getDateCompleted());
        assertThat(predictor.isAssessmentCompleted()).isTrue();
        assertThat(predictor.getOtherRisk().getCode()).isEqualTo(assessment.getOtherRiskRecon().getRefElementCode());
        assertThat(predictor.getOtherRisk().getDescription()).isEqualTo(assessment.getOtherRiskRecon().getRefElementDesc());
    }

    @Test
    public void shouldNotBeCompletedWhenCompletedDateIsNull() {
        var predictor = Predictor.from(assessment);
        assertThat(predictor.getCompletedDate()).isNull();
        assertThat(predictor.isAssessmentCompleted()).isFalse();
    }

    @Test
    public void shouldBeCompletedWhenCompletedDateIsNotNull() {
        when(assessment.getDateCompleted()).thenReturn(LocalDateTime.MAX);
        var predictor = Predictor.from(assessment);
        assertThat(predictor.getCompletedDate()).isNotNull();
        assertThat(predictor.isAssessmentCompleted()).isTrue();
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = RefAssessmentDto.from(null);

        assertThat(dto).isNull();
    }

    private void setupVersion() {
        when(version.getRefAssVersionUk()).thenReturn(1L);
        when(version.getVersionNumber()).thenReturn("Any Version");
        when(version.getRefAssVersionCode()).thenReturn("Any Ref Version Code");
        when(version.getOasysScoringAlgVersion()).thenReturn(2L);
    }

    private void setupAssessment() {
        LocalDateTime created = LocalDateTime.now();
        LocalDateTime completed = created.plusMonths(3);
        LocalDateTime voided = created.plusMonths(4);

        when(assessment.getOasysSetPk()).thenReturn(1234L);
        when(assessment.getAssessorName()).thenReturn("Any Name");
        when(assessment.getAssessmentStatus()).thenReturn("STATUS");
        when(assessment.getAssessmentType()).thenReturn("LAYER_3");
        when(assessment.getCreateDate()).thenReturn(created);
        when(assessment.getDateCompleted()).thenReturn(completed);
        when(assessment.getAssessmentVoidedDate()).thenReturn(voided);
        when(assessment.getAssessmentVersion()).thenReturn(version);
        when(assessment.getOtherRiskRecon()).thenReturn(RefElement.builder().refElementDesc("Low").refElementCode("L").build());

    }

}