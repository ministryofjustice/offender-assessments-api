package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.jpa.entity.SspInterventionMeasure;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.justice.digital.oasys.api.ApiTestContext.refElementFrom;

public class InterventionMeasureDtoTest {
    @Test
    public void shouldReturnInterventionMeasureDtoForEntity() {

        var interventionMeasure = SspInterventionMeasure.builder().interventionStatus(
                refElementFrom("R", "Ongoing", null))
                .InterventionStatusComments("Status Comments")
                .sspInterventionMeasurePk(1l).build();
        var measure = InterventionMeasureDto.from(interventionMeasure);
        assertThat(measure.getComments()).isEqualTo("Status Comments");
        assertThat(measure.getStatus().getDescription()).isEqualTo("Ongoing");
        assertThat(measure.getStatus().getCode()).isEqualTo("R");

    }

}