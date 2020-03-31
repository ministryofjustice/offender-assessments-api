package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RefAssessmentDtoTest {

    @Mock
    RefAssessmentVersion version;

    @BeforeEach
    public void setUp() {
        setupVersion();
    }

    @Test
    public void shouldBuildValidDto() {
        var dto = RefAssessmentDto.from(version);

        assertThat(dto.getRefAssessmentVersionId()).isEqualTo(version.getRefAssVersionUk());
        assertThat(dto.getRefAssVersionCode()).isEqualTo(version.getRefAssVersionCode());
        assertThat(dto.getVersionNumber()).isEqualTo(version.getVersionNumber());
        assertThat(dto.getOasysScoringAlgorithmVersion()).isEqualTo(version.getOasysScoringAlgVersion());
        assertThat(dto.getRefModuleCode()).isEqualTo(version.getRefModuleCode());
        assertThat(dto.getRefSections().size()).isEqualTo(version.getRefSections().size());
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = RefAssessmentDto.from(null);

        assertThat(dto).isNull();
    }

    private void setupVersion() {
        when(version.getRefAssVersionUk()).thenReturn(12L);
        when(version.getRefAssVersionCode()).thenReturn("Any Code");
        when(version.getVersionNumber()).thenReturn("Any Version");
        when(version.getOasysScoringAlgVersion()).thenReturn(2L);
        when(version.getRefModuleCode()).thenReturn("Any Module Code");
        when(version.getRefSections()).thenReturn(List.of());
    }
}