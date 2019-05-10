package uk.gov.justice.digital.oasys.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.BasicSentencePlanItem;
import uk.gov.justice.digital.oasys.api.RefElement;
import uk.gov.justice.digital.oasys.jpa.entity.BasicSentencePlanObj;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SentencePlanTransformer {

    public Optional<BasicSentencePlan> sentencePlanOf(OasysSet oasysSet) {
        final Optional<List<BasicSentencePlanItem>> basicSentencePlanItems = Optional.ofNullable(oasysSet.getBasicSentencePlanList())
                .map(basicSentencePlanObjs -> basicSentencePlanObjs
                        .stream()
                        .map(this::basicSentencePlanItemOf)
                        .collect(Collectors.toList()));

        return basicSentencePlanItems
                .map(this::basicSentencePlanOf);
    }

    public BasicSentencePlan basicSentencePlanOf(List<BasicSentencePlanItem> basicSentencePlanItems) {
        return BasicSentencePlan.builder()
                .sentencePlanId(basicSentencePlanItems.stream().map(BasicSentencePlanItem::getOasysSetId).findFirst().orElse(null))
                .basicSentencePlanItemItems(basicSentencePlanItems)
                .build();
    }

    public BasicSentencePlanItem basicSentencePlanItemOf(BasicSentencePlanObj basicSentencePlanObj) {
        return BasicSentencePlanItem
                .builder()
                .basicSentPlanObjId(basicSentencePlanObj.getBasicSentPlanObjPk())
                .dateCompleted(Optional.ofNullable(basicSentencePlanObj.getDateCompleted()).map(Date::toLocalDate).orElse(null))
                .dateOpened(Optional.ofNullable(basicSentencePlanObj.getDateOpened()).map(Date::toLocalDate).orElse(null))
                .includeInPlan(basicSentencePlanObj.getIncludeInPlanInd())
                .measureText(basicSentencePlanObj.getMeasureText())
                .oasysSetId(basicSentencePlanObj.getOasysSetPk())
                .objectiveText(basicSentencePlanObj.getObjectiveText())
                .offenceBehaviourLink(refElementOf(basicSentencePlanObj.getOffenceBehaviourLink()))
                .problemAreaCompInd(basicSentencePlanObj.getProblemAreaCompInd())
                .timescalesText(basicSentencePlanObj.getTimescalesText())
                .whatWorkText(basicSentencePlanObj.getWhatWorkText())
                .whoWillDoWorkText(basicSentencePlanObj.getWhoWillDoWorkText())
                .build();
    }

    public RefElement refElementOf(uk.gov.justice.digital.oasys.jpa.entity.RefElement offenceBehaviourLink) {
        return Optional.ofNullable(offenceBehaviourLink).map(obl -> RefElement
                .builder()
                .code(obl.getRefElementCode())
                .description(obl.getRefElementDesc())
                .shortDescription(obl.getRefElementShortDesc())
                .build())
                .orElse(null);

    }
}
