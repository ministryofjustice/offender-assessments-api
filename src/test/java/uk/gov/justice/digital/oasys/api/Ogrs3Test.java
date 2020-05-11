package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssessmentVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class Ogrs3Test {

    @Mock
    RefAssessmentVersion version;

    @Mock
    Assessment assessment;

    @Test
    public void shouldBuildValidDto() {
        setupAssessment();

        var ogr = Ogrs3.from(assessment);
        assertThat(ogr.getOgrs3_1Year()).isEqualTo(assessment.getOgrs31Year());
        assertThat(ogr.getOgrs3_2Year()).isEqualTo(assessment.getOgrs32Year());
        assertThat(ogr.getReconvictionRisk().getDescription()).isEqualTo(assessment.getOgrs3RiskRecon().getRefElementDesc());
        assertThat(ogr.getReconvictionRisk().getCode()).isEqualTo(assessment.getOgrs3RiskRecon().getRefElementCode());
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = Predictor.from(null);

        assertThat(dto).isNull();
    }

    private void setupAssessment() {
        when(assessment.getOgrs31Year()).thenReturn(BigDecimal.valueOf(4));
        when(assessment.getOgrs32Year()).thenReturn(BigDecimal.valueOf(5));
        when(assessment.getOgrs3RiskRecon()).thenReturn(RefElement.builder().refElementDesc("Low").refElementCode("L").build());
    }

}