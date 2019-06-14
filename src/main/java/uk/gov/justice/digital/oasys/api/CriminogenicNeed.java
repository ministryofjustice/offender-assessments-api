package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CriminogenicNeed {
    private String code;
    private String description;
    private Integer priority;
}
