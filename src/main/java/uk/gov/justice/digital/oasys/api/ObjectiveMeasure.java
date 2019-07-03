package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ObjectiveMeasure {
    private String comments;
    private RefElement status;
}
