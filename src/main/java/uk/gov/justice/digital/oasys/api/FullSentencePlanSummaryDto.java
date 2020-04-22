package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Section;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class FullSentencePlanSummaryDto {
    private Long oasysSetId;
    private LocalDateTime createdDate;
    private LocalDateTime completedDate;

    public static FullSentencePlanSummaryDto from(Assessment assessment, Optional<Section> section) {
        if ((assessment.getSspObjectivesInSets().isEmpty()) && section.isEmpty()) {
            return null;
        }

        return new FullSentencePlanSummaryDto(
                assessment.getOasysSetPk(),
                assessment.getCreateDate(),
                assessment.getDateCompleted());
    }
}
