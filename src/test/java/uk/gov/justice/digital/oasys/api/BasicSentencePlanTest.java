package uk.gov.justice.digital.oasys.api;

import org.junit.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class BasicSentencePlanTest {

    @Test
    public void shouldCreateBasicSentencePlanFromEntity() {
        OasysSet oasysSet = ControllerServiceTestContext.layer3AssessmentOasysSet(1l);
        var plan = BasicSentencePlan.from(oasysSet);
        assertThat(plan).isNotNull();
        assertThat(plan.getBasicSentencePlanItems()).hasSize(2);
        assertThat(plan.getSentencePlanId()).isEqualTo(1);
        assertThat(plan.getCreatedDate()).isEqualTo(LocalDate.now().minusDays(1));
        assertThat(plan.getBasicSentencePlanItems()).extracting("basicSentPlanObjId").containsOnly(1l);
        assertThat(plan.getBasicSentencePlanItems()).extracting("includeInPlan").containsOnly(true);
        assertThat(plan.getBasicSentencePlanItems()).extracting("objectiveText").containsOnly("obj1", "obj2");
        assertThat(plan.getBasicSentencePlanItems()).extracting("measureText").containsOnly("measure1", "measure2");
        assertThat(plan.getBasicSentencePlanItems()).extracting("whatWorkText").containsOnly("what1", "what2");
        assertThat(plan.getBasicSentencePlanItems()).extracting("whoWillDoWorkText").containsOnly("who1", "who2");
        assertThat(plan.getBasicSentencePlanItems()).extracting("timescalesText").containsOnly("timescales1", "timescales2");
        assertThat(plan.getBasicSentencePlanItems()).extracting("dateOpened").containsOnly(LocalDate.now().minusDays(10));
        assertThat(plan.getBasicSentencePlanItems()).extracting("dateCompleted").containsOnlyNulls();
        assertThat(plan.getBasicSentencePlanItems()).extracting("problemAreaCompInd").containsOnly(true);
        assertThat(plan.getBasicSentencePlanItems()).extracting("oasysSetId").containsOnly(1l, 2l);
        assertThat(plan.getBasicSentencePlanItems()).extracting("offenceBehaviourLink.shortDescription").containsOnly("LINK1", "LINK2");
        assertThat(plan.getBasicSentencePlanItems()).extracting("offenceBehaviourLink.description").containsOnly("Link1", "Link2");
    }
}