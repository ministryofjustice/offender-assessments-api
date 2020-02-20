package uk.gov.justice.digital.oasys.service.filters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.digital.oasys.api.DtoUtils;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssessmentFilters {

    public static Stream<OasysSet> assessmentsFilterOf(Stream<OasysSet> sets, Optional<String> filterAssessmentStatus, Optional<String> filterAssessmentType, Optional<String> filterGroupStatus, Optional<Boolean> filterVoided) {

        if (filterAssessmentStatus.isPresent()) {
            sets = sets.filter(set -> filterAssessmentStatus.get().equals(DtoUtils.refElementCode(set.getAssessmentStatus())));
        }

        if (filterAssessmentType.isPresent()) {
            sets = sets.filter(set -> filterAssessmentType.get().equals(DtoUtils.refElementCode(set.getAssessmentType())));
        }

        if (filterGroupStatus.isPresent()) {
            sets = sets.filter(set -> filterGroupStatus.get().equals(set.getGroup() == null ? null : set.getGroup().getHistoricStatusELm()));
        }

        if (filterVoided.isPresent()) {
            sets = sets.filter(set -> filterVoided.get() == (Objects.nonNull(set.getAssessmentVoidedDate())));
        }

        return sets;
    }
}
