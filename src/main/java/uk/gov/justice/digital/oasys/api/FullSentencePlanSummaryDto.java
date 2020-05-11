package uk.gov.justice.digital.oasys.api;

import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.Section;
import java.time.LocalDateTime;
import java.util.Optional;

@Value
public class FullSentencePlanSummaryDto {
    Long oasysSetId;
    LocalDateTime createdDate;
    LocalDateTime completedDate;

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
