package uk.gov.justice.digital.oasys.api;

import org.junit.Test;
import uk.gov.justice.digital.oasys.controller.ControllerServiceTestContext;
import uk.gov.justice.digital.oasys.jpa.entity.BasicSentencePlanObj;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicSentencePlanTest {

    @Test
    public void shouldCreateBasicSentencePlanFromEntity() {
        var assessment = ControllerServiceTestContext.layer3AssessmentWithBasicSentencePlans(1l);
        var plan = BasicSentencePlan.from(assessment);
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

    public static Assessment layer3Assessment(Long id) {
        return Assessment.builder()
                .createDate(LocalDateTime.now().minusDays(1))
                .assessmentType("LAYER_3")
                .basicSentencePlanList(Set.of(aSentencePlan(1), aSentencePlan(2)))
                .oasysSetPk(id).build();
    }


    private static BasicSentencePlanObj aSentencePlan(long l) {
        return BasicSentencePlanObj.builder()
                .basicSentPlanObjPk(1L)
                .includeInPlanInd("Y")
                .createDate(LocalDateTime.now().minusDays(1))
                .objectiveText("obj" + l)
                .measureText("measure" + l)
                .timescalesText("timescales" + l)
                .whoWillDoWorkText("who" + l)
                .whatWorkText("what" + l)
                .dateOpened(LocalDateTime.now().minusDays(10))
                .problemAreaCompInd("Y")
                .offenceBehaviourLink(RefElement.builder().refElementShortDesc("LINK" + l).refElementDesc("Link" + l).build())
                .oasysSetPk(l).build();
    }
}