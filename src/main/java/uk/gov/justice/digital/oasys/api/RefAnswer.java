package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefAnswer {
    private Long refAnswerId;
    private String refAssVersionCode;
    private Long refVersionId;
    private String refAnswerCode;
    private Long refDisplaySort;
    private String refSectionCode;

}
