package uk.gov.justice.digital.oasys.api;

import org.junit.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

public class ObjectiveDtoTest {

    @Test
    public void shouldReturnObjectiveDtosFromObjectivesInSet() {

        var objectivesInSet = ControllerServiceTestContext.getObjectivesInSet();

        var objectives = ObjectiveDto.from(objectivesInSet);

        assertThat(objectives).hasSize(2);
    }

    @Test
    public void shouldReturnObjectiveDtoFromObjectivesInSet() {

        var objectiveInSet = ControllerServiceTestContext.getObjectivesInSet().stream()
                .filter(o -> o.getSspObjectivesInSetPk().equals(1l)).findFirst();

        var objective = ObjectiveDto.from(objectiveInSet.get());
        assertThat(objective.getCriminogenicNeeds()).hasSize(2);
        assertThat(objective.getInterventions()).hasSize(2);
        assertThat(objective.getHowMeasured()).isEqualTo("Progress measured");
        assertThat(objective.getObjectiveCode()).isEqualTo("100");
        assertThat(objective.getObjectiveMeasure()).isNotNull();
        assertThat(objective.getObjectiveDescription()).isEqualTo("Objective 1");
        assertThat(objective.getObjectiveComment()).isEqualTo("Objective 1 description");
        assertThat(objective.getObjectiveHeading()).isEqualTo("Objective 1 Heading");
        assertThat(objective.getObjectiveType().getCode()).isEqualTo("CURRENT");
        assertThat(objective.getObjectiveType().getDescription()).isEqualTo("Current");
        assertThat(objective.getCreatedDate()).isEqualToIgnoringSeconds(LocalDateTime.of(2019, 12,28, 9, 00));
    }
}