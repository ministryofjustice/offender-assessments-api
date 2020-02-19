package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectiveMeasure;

@Builder(access = AccessLevel.PRIVATE)
@Value
public class ObjectiveMeasureDto {
    private String comments;
    private RefElementDto status;

    public static ObjectiveMeasureDto from(SspObjectiveMeasure sspObjectiveMeasure) {
        if(sspObjectiveMeasure == null) {
            return null;
        }
       return builder()
                .comments(sspObjectiveMeasure.getObjectiveStatusComments())
                .status(RefElementDto.from(sspObjectiveMeasure.getObjectiveStatus()))
                .build();
    }
}
