package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

@Data
@Builder
public class ReferenceIntervention {
    private String interventionCode;
    private String shortDescription;
    private String longDescription;

    public static ReferenceIntervention from(RefElement refElement) {
         return new ReferenceIntervention(refElement.getRefElementCode(),
                 refElement.getRefElementShortDesc(),
                 refElement.getRefElementDesc());
    }
}
