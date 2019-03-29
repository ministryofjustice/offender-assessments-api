package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OasysUser {
    private String oasysUserId;
    private String userName;
}
