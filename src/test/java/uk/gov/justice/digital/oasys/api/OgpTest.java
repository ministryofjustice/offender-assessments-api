package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OgpTest {

    @Mock
    Assessment assessment;

    @Test
    public void shouldBuildValidDto() {
        setupAssessment();
        var ogp = Ogp.from(assessment);
        assertThat(ogp.getOgp1Year()).isEqualTo(assessment.getOgp1Year());
        assertThat(ogp.getOgp2Year()).isEqualTo(assessment.getOgp2Year());
        assertThat(ogp.getOgpDynamicWeightedScore()).isEqualTo(assessment.getOgpDyWesc());
        assertThat(ogp.getOgpStaticWeightedScore()).isEqualTo(assessment.getOgpStWesc());
        assertThat(ogp.getOgpTotalWeightedScore()).isEqualTo(assessment.getOgpTotWesc());
        assertThat(ogp.getOgpRisk().getDescription()).isEqualTo(assessment.getOgpRiskRecon().getRefElementDesc());
        assertThat(ogp.getOgpRisk().getCode()).isEqualTo(assessment.getOgpRiskRecon().getRefElementCode());
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = Ovp.from(null);
        assertThat(dto).isNull();
    }


    private void setupAssessment() {
        when(assessment.getOgpStWesc()).thenReturn(BigDecimal.valueOf(1));
        when(assessment.getOgpDyWesc()).thenReturn(BigDecimal.valueOf(2));
        when(assessment.getOgpTotWesc()).thenReturn(BigDecimal.valueOf(3));
        when(assessment.getOgp1Year()).thenReturn(BigDecimal.valueOf(4));
        when(assessment.getOgp2Year()).thenReturn(BigDecimal.valueOf(5));
        when(assessment.getOgpRiskRecon()).thenReturn(RefElement.builder().refElementDesc("Low").refElementCode("L").build());

    }

}