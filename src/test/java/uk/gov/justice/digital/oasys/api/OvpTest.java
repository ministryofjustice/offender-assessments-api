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
class OvpTest {

    @Mock
    Assessment assessment;

    @Test
    public void shouldBuildValidDto() {
        setupAssessment();
        var ovp = Ovp.from(assessment);
        assertThat(ovp.getOvp1Year()).isEqualTo(assessment.getOvp1Year());
        assertThat(ovp.getOvp2Year()).isEqualTo(assessment.getOvp2Year());
        assertThat(ovp.getOvpAgeWeightedScore()).isEqualTo(assessment.getOvpAgeWesc());
        assertThat(ovp.getOvpDynamicWeightedScore()).isEqualTo(assessment.getOvpDyWesc());
        assertThat(ovp.getOvpNonViolentWeightedScore()).isEqualTo(assessment.getOvpNonVioWesc());
        assertThat(ovp.getOvpPreviousWeightedScore()).isEqualTo(assessment.getOvpPrevWesc());
        assertThat(ovp.getOvpSexWeightedScore()).isEqualTo(assessment.getOvpSexWesc());
        assertThat(ovp.getOvpStaticWeightedScore()).isEqualTo(assessment.getOvpStWesc());
        assertThat(ovp.getOvpTotalWeightedScore()).isEqualTo(assessment.getOvpTotWesc());
        assertThat(ovp.getOvpViolentWeightedScore()).isEqualTo(assessment.getOvpVioWesc());
        assertThat(ovp.getOvpRisk().getDescription()).isEqualTo(assessment.getOvpRiskRecon().getRefElementDesc());
        assertThat(ovp.getOvpRisk().getCode()).isEqualTo(assessment.getOvpRiskRecon().getRefElementCode());
    }

    @Test
    public void shouldBuildValidDtoNull() {
        var dto = Ovp.from(null);
        assertThat(dto).isNull();
    }


    private void setupAssessment() {
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

    }

}