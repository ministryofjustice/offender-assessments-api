package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectivesInSet;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Builder(access = AccessLevel.PRIVATE)
@Value
public class ProperSentencePlanDto {
    private Long oasysSetId;
    private LocalDate createdDate;
    private LocalDate completedDate;
    private List<ObjectiveDto> objectives;

    public static ProperSentencePlanDto from(OasysSet oasysSet) {
        return Optional.ofNullable(oasysSet)
                .map(os ->
                        ProperSentencePlanDto
                                .builder()
                                .oasysSetId(oasysSet.getOasysSetPk())
                                .completedDate(oasysSet.getDateCompleted() == null ? null : oasysSet.getDateCompleted().toLocalDateTime().toLocalDate())
                                .objectives(ObjectiveDto.from(oasysSet.getSspObjectivesInSets()))
                                .createdDate(earliestSspObjectiveOf(os.getSspObjectivesInSets()))
                                .build())
                .filter(psp -> psp.getObjectives() != null && !psp.getObjectives().isEmpty()).orElse(null);
    }

    private static LocalDate earliestSspObjectiveOf(Set<SspObjectivesInSet> sspObjectivesInSets) {
        return sspObjectivesInSets.stream()
                .min(Comparator.comparing(SspObjectivesInSet::getCreateDate))
                .map(SspObjectivesInSet::getCreateDate)
                .orElse(null);
    }
}
