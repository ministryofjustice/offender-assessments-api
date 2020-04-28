package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.SspObjectiveMeasure;

import java.util.Objects;

@Builder(access = AccessLevel.PRIVATE)
@Value
public class ObjectiveMeasureDto {
    String comments;
    RefElementDto status;

    public static ObjectiveMeasureDto from(SspObjectiveMeasure sspObjectiveMeasure) {
        if (Objects.isNull(sspObjectiveMeasure)) {
            return null;
        }
        return builder()
                .comments(sspObjectiveMeasure.getObjectiveStatusComments())
                .status(RefElementDto.from(sspObjectiveMeasure.getObjectiveStatus()))
                .build();
    }
}
