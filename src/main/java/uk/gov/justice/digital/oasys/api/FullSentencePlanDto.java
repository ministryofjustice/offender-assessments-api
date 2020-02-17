package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class FullSentencePlanDto {
    private Long oasysSetId;
    private LocalDateTime createdDate;
    private LocalDateTime completedDate;
    private List<ObjectiveDto> objectives;

    public static FullSentencePlanDto from(OasysSet oasysSet) {

        var sp = new FullSentencePlanDto(
                oasysSet.getOasysSetPk(),
                earliestSspObjectiveOf(oasysSet.getSspObjectivesInSets()),
                oasysSet.getDateCompleted(),
                ObjectiveDto.from(oasysSet.getSspObjectivesInSets()));

        if(sp.getObjectives() == null || sp.getObjectives().isEmpty()){
            return null;
        }

        return sp;
    }

    private static LocalDateTime earliestSspObjectiveOf(Set<SspObjectivesInSet> sspObjectivesInSets) {
        return sspObjectivesInSets.stream()
                .min(Comparator.comparing(SspObjectivesInSet::getCreateDate))
                .map(SspObjectivesInSet::getCreateDate)
                .orElse(null);
    }
}
