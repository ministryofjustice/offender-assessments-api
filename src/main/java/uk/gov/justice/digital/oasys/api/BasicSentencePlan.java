package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class BasicSentencePlan {
    private final Long sentencePlanId;
    private final LocalDate createdDate;
    private List<BasicSentencePlanItem> basicSentencePlanItems;
}
