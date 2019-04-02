package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class BasicSentencePlan {
    private final Long oasysSetId;
    private List<BasicSentencePlanItem> basicSentencePlanItemItems;
}
