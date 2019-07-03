package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
@Value
public class ProperSentencePlan {
    private LocalDate createdDate;
    private List<Objective> objectives;
}
