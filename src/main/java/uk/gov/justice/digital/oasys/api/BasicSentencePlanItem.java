package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BasicSentencePlanItem {
    private final Long basicSentPlanObjId;
    private final String includeInPlan;
    private final RefElement offenceBehaviourLink;
    private final String objectiveText;
    private final String measureText;
    private final String whatWorkText;
    private final String whoWillDoWorkText;
    private final String timescalesText;
    private final LocalDate dateOpened;
    private final LocalDate dateCompleted;
    private final String problemAreaCompInd;
    @JsonIgnore
    private final Long oasysSetId;

}
