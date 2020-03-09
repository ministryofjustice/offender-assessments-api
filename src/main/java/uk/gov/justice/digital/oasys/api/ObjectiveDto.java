package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjective;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@Builder(access = AccessLevel.PRIVATE)
public class ObjectiveDto {
    private Set<CriminogenicNeedDto> criminogenicNeeds;
    private Set<InterventionDto> interventions;
    private ObjectiveMeasureDto objectiveMeasure;
    private RefElementDto objectiveType;
    private String objectiveCode;
    private String objectiveDescription;
    private String howMeasured;

    public static Set<ObjectiveDto> from(Set<SspObjectivesInSet> sspObjectivesInSets) {

        return sspObjectivesInSets.stream()
                .map(ObjectiveDto::from)
                .collect(Collectors.toSet());
    }

    private static ObjectiveDto from(SspObjectivesInSet sspo) {
        if (sspo == null) {
            return null;
        }
        return ObjectiveDto
                .builder()
                .criminogenicNeeds(CriminogenicNeedDto.from(sspo.getSspCrimNeedObjPivots()))
                .howMeasured(sspo.getHowProgressMeasured())
                .interventions(InterventionDto.from(sspo.getSspObjIntervenePivots()))
                .objectiveCode(objectiveCodeOf(sspo.getSspObjective()))
                .objectiveDescription(objectiveDescriptionOf(sspo.getSspObjective()))
                .objectiveMeasure(ObjectiveMeasureDto.from(sspo.getSspObjectiveMeasure()))
                .objectiveType(RefElementDto.from(sspo.getObjectiveType()))
                .build();
    }

    private static String objectiveDescriptionOf(SspObjective sspObjective) {
        if (sspObjective == null || sspObjective.getObjective() == null) {
            return null;
        }
        return sspObjective.getObjective().getObjectiveDesc();
    }

    private static String objectiveCodeOf(SspObjective sspObjective) {
        if (sspObjective == null || sspObjective.getObjective() == null) {
            return null;
        }
        return sspObjective.getObjective().getObjectiveCode();
    }
}
