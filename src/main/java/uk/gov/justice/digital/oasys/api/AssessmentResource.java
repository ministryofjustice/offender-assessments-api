package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;

@Value
@EqualsAndHashCode(callSuper = false)
@Builder
public class AssessmentResource extends ResourceSupport {
    private AssessmentSummary assessment;
}
