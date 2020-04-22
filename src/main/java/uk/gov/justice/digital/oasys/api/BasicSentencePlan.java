package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Value
@Builder(toBuilder = true)
public class BasicSentencePlan {
    private final Long sentencePlanId;
    private final LocalDate createdDate;
    private List<BasicSentencePlanItem> basicSentencePlanItems;

    public static BasicSentencePlan from(Assessment assessment) {

        if(Objects.isNull(assessment)) {
            return null;
        }

        final List<BasicSentencePlanItem> basicSentencePlanItems = assessment.getBasicSentencePlanList()
                .stream()
                .map(BasicSentencePlanItem::from)
                .collect(Collectors.toList());

        if (basicSentencePlanItems.isEmpty()) {
            return null;
        }

        return BasicSentencePlan.builder()
                .basicSentencePlanItems(basicSentencePlanItems)
                .sentencePlanId(assessment.getOasysSetPk())
                .createdDate(assessment.getCreateDate().toLocalDate()).build();
    }

    public static Set<BasicSentencePlan> from(Collection<Assessment> assessments) {
        return Optional.ofNullable(assessments)
                .map(as -> as
                        .stream()
                        .map(BasicSentencePlan::from)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }
}
