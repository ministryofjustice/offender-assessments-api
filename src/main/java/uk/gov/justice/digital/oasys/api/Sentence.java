package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Sentence {
    private String sentenceCode;
    private String sentenceDescription;
    private Boolean custodial;
    private Boolean cja;
    private String startDate;
    private String endDate;
    private String orderType;
    private Long cjaUnpaidHours;
    private Long cjaSupervisionMonths;
    private String activity;
}
