package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
@Builder
public class AssessmentResource extends ResourceSupport {
    private AssessmentSummary assessment;
}
