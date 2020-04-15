package uk.gov.justice.digital.oasys.api;

import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.jpa.entity.SspWhoDoWorkPivot;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.justice.digital.oasys.api.ApiTestContext.refElementFrom;

public class WhoDoingWorkDtoTest {
    @Test
    public void shouldReturnWhoDoingWorkDtoForEntity() {

        var whoDoingWork = Set.of(SspWhoDoWorkPivot.builder().comments("Comment 1").whoDoWork(
                refElementFrom("IX1", "Prison Staff", null)).build(),
                SspWhoDoWorkPivot.builder().comments("Comment 2").whoDoWork(
                        refElementFrom("IX2", "Probation Staff", null)).build());

        var whoDoingWorks = WhoDoingWorkDto.from(whoDoingWork);

        var work = whoDoingWorks.stream().filter(w -> w.getCode().equals("IX1")).findFirst().get();

        assertThat(whoDoingWorks).hasSize(2);
        assertThat(work.getCode()).isEqualTo("IX1");
        assertThat(work.getDescription()).isEqualTo("Prison Staff");
        assertThat(work.getComments()).isEqualTo("Comment 1");
    }
}