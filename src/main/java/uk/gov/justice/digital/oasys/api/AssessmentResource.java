package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class AssessmentResource extends ResourceSupport {
    private AssessmentSummary assessment;
}
