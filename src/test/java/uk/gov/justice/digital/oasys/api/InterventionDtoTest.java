package uk.gov.justice.digital.oasys.api;


import org.junit.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;

import static org.assertj.core.api.Assertions.assertThat;

public class InterventionDtoTest {


    @Test
    public void shouldReturnInterventionDtosFromEntitySet() {
        var sspObjIntervenePivot = ControllerServiceTestContext.getInterventions(1l);
        var needs = InterventionDto.from(sspObjIntervenePivot);
        assertThat(needs).extracting("interventionCode").containsOnly("V1", "V2");
    }

    @Test
    public void shouldReturnInterventionDtoFromEntity() {

        var sspObjIntervenePivot = ControllerServiceTestContext.getInterventions(1l);

        var intervention = InterventionDto.from(sspObjIntervenePivot).stream()
                .filter(o -> o.getInterventionCode().equals("V1")).findFirst().get();

        assertThat(intervention.getInterventionCode()).isEqualTo("V1");
        assertThat(intervention.getCopiedForward()).isTrue();
        assertThat(intervention.getInterventionComment()).isEqualTo("Intervention Comment");
        assertThat(intervention.getInterventionDescription()).isEqualTo("Intervention 1");
        assertThat(intervention.getInterventionMeasure()).isNotNull();
        assertThat(intervention.getTimescale().getCode()).isEqualTo("ONE_MONTH");
        assertThat(intervention.getTimescale().getDescription()).isEqualTo("One Month");
        assertThat(intervention.getWhoDoingWork()).isNotNull();
    }

}