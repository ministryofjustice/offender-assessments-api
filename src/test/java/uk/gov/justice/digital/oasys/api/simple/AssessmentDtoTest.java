package uk.gov.justice.digital.oasys.api.simple;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.AssessmentGroup;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentDtoTest {

    @Mock
    AssessmentGroup assessmentGroup;

    @Mock
    RefAssessmentVersion version;

    @Mock
    Assessment assessment;

    @Before
    public void setUp() {
        setupAssessmentGroup();
        setupVersion();
        setupAssessment();
    }

    @Test
    public void shouldBuildValidDto() {
        var needs = new HashSet<AssessmentNeed>();
        var dto = AssessmentDto.from(assessment, true, needs);

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
        assertThat(dto.getSections()).hasSize(assessment.getOasysSections().size());
        assertThat(dto.getChildSafeguardingIndicated()).isTrue();
        assertThat(dto.getLayer3SentencePlanNeeds()).hasSize(needs.size());
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = AssessmentDto.from(null, null, null);

        assertThat(dto).isNull();
    }

    @Test
    public void shouldBuildValidDtoSafeguardingNull() {
        var needs = new HashSet<AssessmentNeed>();
        var dto = AssessmentDto.from(assessment, null, needs);

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
        assertThat(dto.getSections()).hasSize(assessment.getOasysSections().size());
        assertThat(dto.getChildSafeguardingIndicated()).isNull();
        assertThat(dto.getLayer3SentencePlanNeeds()).hasSize(needs.size());
    }

    @Test
    public void shouldBuildValidDtoNeedsNull() {
        var needs = new HashSet<AssessmentNeed>();
        var dto = AssessmentDto.from(assessment, true, null);

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
        assertThat(dto.getSections()).hasSize(assessment.getOasysSections().size());
        assertThat(dto.getChildSafeguardingIndicated()).isTrue();
        assertThat(dto.getLayer3SentencePlanNeeds()).isEmpty();
    }

    private void setupAssessmentGroup() {
        when(assessmentGroup.getHistoricStatus()).thenReturn("Any HistoricStatus");
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
        when(assessment.getOasysSections()).thenReturn(Set.of());
        //when(assessment.getOasysBcsParts()).thenReturn(Set.of());
        when(assessment.getGroup()).thenReturn(assessmentGroup);
    }
}