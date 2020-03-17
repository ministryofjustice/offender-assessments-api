package uk.gov.justice.digital.oasys.api.simple;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;
import uk.gov.justice.digital.oasys.service.domain.SectionHeader;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssessmentNeedDtoTest {

    @Mock
    AssessmentNeed assessmentNeed;


    @Before
    public void setUp() {
        setupAssessmentNeed();
    }

    @Test
    public void shouldBuildValidDto() {
        var dto = AssessmentNeedDto.from(Set.of(assessmentNeed)).stream().findFirst().get();

        assertThat(dto.getSection()).isEqualTo(assessmentNeed.getSection());
        assertThat(dto.getName()).isEqualTo(assessmentNeed.getName());
        assertThat(dto.getOverThreshold()).isEqualTo(assessmentNeed.getOverThreshold());
        assertThat(dto.getRiskOfHarm()).isEqualTo(assessmentNeed.getRiskOfHarm());
        assertThat(dto.getRiskOfReoffending()).isEqualTo(assessmentNeed.getRiskOfReoffending());
        assertThat(dto.getFlaggedAsNeed()).isEqualTo(assessmentNeed.getFlaggedAsNeed());
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = AssessmentNeedDto.from(null);

        assertThat(dto).isEmpty();
    }

    @Test
    public void shouldBuildValidDtoSetNull() {
        Set<AssessmentNeed> needs = new HashSet<>();
        needs.add(null);
        var dto = AssessmentNeedDto.from(needs);

        assertThat(dto).isEmpty();
    }

    private void setupAssessmentNeed() {
        when(assessmentNeed.getSection()).thenReturn(SectionHeader.DRUG_MISUSE);
        when(assessmentNeed.getName()).thenReturn("Drug Misuse");
        when(assessmentNeed.getOverThreshold()).thenReturn(true);
        when(assessmentNeed.getRiskOfHarm()).thenReturn(true);
        when(assessmentNeed.getRiskOfReoffending()).thenReturn(true);
        when(assessmentNeed.getFlaggedAsNeed()).thenReturn(true);
    }
}