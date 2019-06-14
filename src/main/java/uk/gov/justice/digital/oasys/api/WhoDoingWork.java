package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WhoDoingWork {
    private String code;
    private String description;
    private String comments;
}
