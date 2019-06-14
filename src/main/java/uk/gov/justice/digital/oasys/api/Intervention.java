package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Intervention {
    private Boolean copiedForward;
    private String interventionComment;
    private RefElement timescale;
    private String interventionCode;
    private String interventionDescription;
}
