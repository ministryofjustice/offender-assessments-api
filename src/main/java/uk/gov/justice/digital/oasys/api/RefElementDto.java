package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

import java.util.Optional;

@Value
@Builder(access =  AccessLevel.PRIVATE)
public class RefElementDto {

    private final String code;
    private final String shortDescription;
    private final String description;

    public static RefElementDto from(RefElement refElement) {
        return Optional.ofNullable(refElement).map(r -> RefElementDto
                .builder()
                .code(r.getRefElementCode())
                .description(r.getRefElementDesc())
                .shortDescription(r.getRefElementShortDesc())
                .build())
                .orElse(null);
    }
}
