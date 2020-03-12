package uk.gov.justice.digital.oasys.api.simple;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.AssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static uk.gov.justice.digital.oasys.jpa.entity.simple.QAssessment.assessment;

@RunWith(MockitoJUnitRunner.class)
public class SectionDtoTest {

    @Mock
    Section section;

    @Mock
    RefSection refSection;

    @Before
    public void setUp() {
        setupSection();
        setupRefSection();
    }

    @Test
    public void shouldBuildValidDto() {
        var dto = SectionDto.from(Set.of(section)).stream().findFirst().get();

        assertThat(dto.getSectionId()).isEqualTo(section.getOasysSectionPk());
        assertThat(dto.getAssessmentId()).isEqualTo(section.getOasysSetPk());
        assertThat(dto.getRefAssessmentVersionCode()).isEqualTo(section.getRefSection().getRefAssVersionCode());
        assertThat(dto.getRefSectionVersionNumber()).isEqualTo(section.getRefSection().getVersionNumber());
        assertThat(dto.getRefSectionCode()).isEqualTo(section.getRefSection().getRefSectionCode());
        assertThat(dto.getRefSectionCrimNeedScoreThreshold()).isEqualTo(section.getRefSection().getCrimNeedScoreThreshold());
        assertThat(dto.getStatus()).isEqualTo(section.getSectionStatusElm());
        assertThat(dto.getSectionOgpWeightedScore()).isEqualTo(section.getSectOgpWeightedScore());
        assertThat(dto.getSectionOgpRawScore()).isEqualTo(section.getSectOgpRawScore());
        assertThat(dto.getSectionOvpWeightedScore()).isEqualTo(section.getSectOvpWeightedScore());
        assertThat(dto.getSectionOvpRawScore()).isEqualTo(section.getSectOvpRawScore());
        assertThat(dto.getSectionOtherWeightedScore()).isEqualTo(section.getSectOtherWeightedScore());
        assertThat(dto.getSectionOtherRawScore()).isEqualTo(section.getSectOtherRawScore());
        assertThat(dto.getLowScoreAttentionNeeded()).isEqualTo(section.getLowScoreNeedAttnInd());
        assertThat(dto.getQuestions()).hasSize(section.getOasysQuestions().size());
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = SectionDto.from(null);

        assertThat(dto).isNull();
    }

    @Test
    public void shouldBuildValidDtoSetNull() {
        Set<Section> sections = new HashSet<>();
        sections.add(null);
        var dto = SectionDto.from(sections);

        assertThat(dto).isEmpty();
    }

    private void setupSection() {
        when(section.getOasysSectionPk()).thenReturn(1L);
        when(section.getOasysSetPk()).thenReturn(2L);
        when(section.getSectionStatusElm()).thenReturn("Any Status");
        when(section.getSectOgpWeightedScore()).thenReturn(100L);
        when(section.getSectOgpRawScore()).thenReturn(101L);
        when(section.getSectOvpWeightedScore()).thenReturn(200L);
        when(section.getSectOvpRawScore()).thenReturn(201L);
        when(section.getSectOtherWeightedScore()).thenReturn(300L);
        when(section.getSectOtherRawScore()).thenReturn(301L);
        when(section.getLowScoreNeedAttnInd()).thenReturn("Y");
        when(section.getOasysQuestions()).thenReturn(Set.of());
        when(section.getRefSection()).thenReturn(refSection);
    }

    private void setupRefSection() {
        when(refSection.getRefSectionCode()).thenReturn("Any Ref Sec Code");
        when(refSection.getRefAssVersionCode()).thenReturn("Any Assessment Version");
        when(refSection.getCrimNeedScoreThreshold()).thenReturn(4L);
    }
}