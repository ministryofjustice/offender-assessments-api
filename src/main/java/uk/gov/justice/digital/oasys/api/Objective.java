package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Objective {
    private List<CriminogenicNeed> criminogenicNeeds;
    private List<Intervention> interventions;
    private ObjectiveMeasure objectiveMeasure;
    private RefElement objectiveType;
    private WhoDoingWork whoDoingWork;
    private String objectiveCode;
    private String objectiveDescription;
    private String howMeasured;

}
