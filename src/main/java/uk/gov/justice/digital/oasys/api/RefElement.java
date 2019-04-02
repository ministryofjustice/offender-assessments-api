package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefElement {
    private final String code;
    private final String shortDescription;
    private final String description;
}
