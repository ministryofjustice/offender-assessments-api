package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.BasicSentencePlanObj;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Value
@Builder
public class BasicSentencePlanItem {
    Long basicSentPlanObjId;
    Boolean includeInPlan;
    RefElementDto offenceBehaviourLink;
    String objectiveText;
    String measureText;
    String whatWorkText;
    String whoWillDoWorkText;
    String timescalesText;
    LocalDate dateOpened;
    LocalDate dateCompleted;
    Boolean problemAreaCompInd;

    public static BasicSentencePlanItem from(BasicSentencePlanObj basicSentencePlanObj) {

        if(Objects.isNull(basicSentencePlanObj)){
            return null;
        }

        return new BasicSentencePlanItem(
                basicSentencePlanObj.getBasicSentPlanObjPk(),
                Optional.ofNullable(basicSentencePlanObj.getIncludeInPlanInd()).map("Y"::equalsIgnoreCase).orElse(null),
                Optional.ofNullable(basicSentencePlanObj.getOffenceBehaviourLink()).map(RefElementDto::from).orElse(null),
                basicSentencePlanObj.getObjectiveText(),
                basicSentencePlanObj.getMeasureText(),
                basicSentencePlanObj.getWhatWorkText(),
                basicSentencePlanObj.getWhoWillDoWorkText(),
                basicSentencePlanObj.getTimescalesText(),
                Optional.ofNullable(basicSentencePlanObj.getDateOpened()).map(LocalDateTime::toLocalDate).orElse(null),
                Optional.ofNullable(basicSentencePlanObj.getDateCompleted()).map(LocalDateTime::toLocalDate).orElse(null),
                Optional.ofNullable(basicSentencePlanObj.getProblemAreaCompInd()).map("Y"::equalsIgnoreCase).orElse(null));
    }
}
