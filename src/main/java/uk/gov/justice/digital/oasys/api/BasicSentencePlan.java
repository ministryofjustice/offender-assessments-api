package uk.gov.justice.digital.oasys.api;

import lombok.Builder;
import lombok.Value;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder(toBuilder = true)
public class BasicSentencePlan {
    private final Long sentencePlanId;
    private final LocalDate createdDate;
    private List<BasicSentencePlanItem> basicSentencePlanItems;

    public static BasicSentencePlan from(OasysSet oasysSet) {
        final List<BasicSentencePlanItem> basicSentencePlanItems = oasysSet.getBasicSentencePlanList()
                .stream()
                .map(BasicSentencePlanItem::from)
                .collect(Collectors.toList());

        if(basicSentencePlanItems.isEmpty()) {
            return null;
        }

        return BasicSentencePlan.builder()
                .basicSentencePlanItems(basicSentencePlanItems)
                .sentencePlanId(oasysSet.getOasysSetPk())
                .createdDate(oasysSet.getCreateDate().toLocalDateTime().toLocalDate()).build();
    }
}
