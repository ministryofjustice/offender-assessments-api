package uk.gov.justice.digital.oasys.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.AssessmentGroup;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentSummaryDtoTest {

    @Mock
    AssessmentGroup assessmentGroup;

    @Mock
    RefAssessmentVersion version;

    @Mock
    Assessment assessment;

    @Before
    public void setUp() {
        setupVersion();
        setupAssessment();
    }

    @Test
    public void shouldBuildValidDto() {
        var dto = AssessmentSummaryDto.from(Set.of(assessment)).stream().findFirst().get();

        assertThat(dto.getAssessmentId()).isEqualTo(assessment.getOasysSetPk());
        assertThat(dto.getAssessorName()).isEqualTo(assessment.getAssessorName());
        assertThat(dto.getAssessmentType()).isEqualTo(assessment.getAssessmentType());
        assertThat(dto.getHistoricStatus()).isEqualTo(assessment.getGroup().getHistoricStatus());
        assertThat(dto.getAssessmentStatus()).isEqualTo(assessment.getAssessmentStatus());
        assertThat(dto.getCreatedDateTime()).isEqualTo(assessment.getCreateDate());
        assertThat(dto.getRefAssessmentId()).isEqualTo(assessment.getAssessmentVersion().getRefAssVersionUk());
        assertThat(dto.getRefAssessmentVersionCode()).isEqualTo(assessment.getAssessmentVersion().getRefAssVersionCode());
        assertThat(dto.getRefAssessmentVersionNumber()).isEqualTo(assessment.getAssessmentVersion().getVersionNumber());
        assertThat(dto.getRefAssessmentOasysScoringAlgorithmVersion()).isEqualTo(assessment.getAssessmentVersion().getOasysScoringAlgVersion());
        assertThat(dto.getCompletedDateTime()).isEqualTo(assessment.getDateCompleted());
        assertThat(dto.getVoidedDateTime()).isEqualTo(assessment.getAssessmentVoidedDate());
    }

    @Test
    public void shouldBuildValidNull() {
        var dto = AssessmentSummaryDto.from(null);

        assertThat(dto).isEmpty();
    }

    @Test
    public void shouldBuildValidSetNull() {
        Set<Assessment> assessments = new HashSet<>();
        assessments.add(null);
        var dto = AssessmentSummaryDto.from(assessments);

        assertThat(dto).isEmpty();
    }

    private void setupVersion() {
        when(version.getRefAssVersionUk()).thenReturn(1L);
        when(version.getRefAssVersionCode()).thenReturn("Any Ref Version Code");
        when(version.getVersionNumber()).thenReturn("Any Version");
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
        when(assessment.getGroup()).thenReturn(assessmentGroup);
    }
}