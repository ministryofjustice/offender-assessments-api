package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;
import static org.assertj.core.api.Assertions.assertThat;

public class CriminogenicNeedDtoTest {

    @Test
    public void shouldReturnCriminogenicNeedDtosFromEntitySet() {
        var sspCrimNeedObjPivots = ControllerServiceTestContext.getNeeds();
        var needs = CriminogenicNeedDto.from(sspCrimNeedObjPivots);
        assertThat(needs).extracting("code").containsOnly("I10", "I20");
    }

    @Test
    public void shouldReturnCriminogenicNeedDtoFromEntity() {

        var sspCrimNeedObjPivots = ControllerServiceTestContext.getNeeds();

        var need = CriminogenicNeedDto.from(sspCrimNeedObjPivots).stream()
                .filter(o -> o.getCode().equals("I10")).findFirst().get();

        assertThat(need.getCode()).isEqualTo("I10");
        assertThat(need.getDescription()).isEqualTo("Need 1");
        assertThat(need.getPriority()).isEqualTo(1l);
    }

}