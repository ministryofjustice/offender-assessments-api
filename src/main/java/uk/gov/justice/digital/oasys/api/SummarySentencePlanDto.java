package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
public class SummarySentencePlanDto {
    private Long oasysSetId;
    private LocalDateTime createdDate;
    private LocalDateTime completedDate;

    public static SummarySentencePlanDto from(Assessment assessment) {
        return new SummarySentencePlanDto(
                assessment.getOasysSetPk(),
                assessment.getCreateDate(),
                assessment.getDateCompleted());
    }
}
