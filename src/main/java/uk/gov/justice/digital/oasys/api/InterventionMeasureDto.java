package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspInterventionMeasure;

@Builder(access = AccessLevel.PRIVATE)
@Value
public class InterventionMeasureDto {
    private String comments;
    private RefElementDto status;

    public static InterventionMeasureDto from(SspInterventionMeasure sspInterventionMeasure) {
        if (sspInterventionMeasure == null) {
            return null;
        }
        return builder()
                .comments(sspInterventionMeasure.getInterventionStatusComments())
                .status(RefElementDto.from(sspInterventionMeasure.getInterventionStatus()))
                .build();
    }
}
