package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectiveMeasure;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext.refElementFrom;

public class ObjectiveMeasureDtoTest {

    @Test
    public void shouldReturnObjectiveMeasureDtoForEntity() {

        var objectiveMeasure = SspObjectiveMeasure.builder().objectiveStatus(
                refElementFrom("R", "Ongoing", null))
                .objectiveStatusComments("Status Comments")
                .sspObjectiveMeasurePk(1l).build();
        var measure = ObjectiveMeasureDto.from(objectiveMeasure);
        assertThat(measure.getComments()).isEqualTo("Status Comments");
        assertThat(measure.getStatus().getDescription()).isEqualTo("Ongoing");
        assertThat(measure.getStatus().getCode()).isEqualTo("R");
    }

}