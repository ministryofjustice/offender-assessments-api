package uk.gov.justice.digital.oasys.api.simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;
import uk.gov.justice.digital.oasys.service.domain.SectionHeader;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AssessmentNeedDto {
    @JsonProperty("section")
    private SectionHeader section;
    @JsonProperty("overThreshold")
    private Boolean overThreshold;
    @JsonProperty("riskOfHarm")
    private Boolean riskOfHarm;
    @JsonProperty("riskOfReoffending")
    private Boolean riskOfReoffending;
    @JsonProperty("flaggedAsNeed")
    private Boolean flaggedAsNeed;

    public static Collection<AssessmentNeedDto> from(Collection<AssessmentNeed> needs) {
        return Optional.ofNullable(needs)
                .map(n -> n
                        .stream()
                        .filter(Objects::nonNull)
                        .map(AssessmentNeedDto::from)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    private static AssessmentNeedDto from(AssessmentNeed need) {
        return new AssessmentNeedDto(
                need.getSection(),
                need.getOverThreshold(),
                need.getRiskOfHarm(),
                need.getRiskOfReoffending(),
                need.getFlaggedAsNeed());
    }


}
