package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    Integer status;
    Integer errorCode;
    String userMessage;
    String developerMessage;
    String moreInfo;
}