package uk.gov.justice.digital.oasys.service.filters;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssessmentFilters {

    public static Stream<OasysSet> assessmentsFilterOf(Stream<OasysSet> sets, String filterAssessmentStatus, String filterAssessmentType, String filterGroupStatus, Boolean filterVoided) {

        if (Optional.ofNullable(filterAssessmentStatus).isPresent()) {
            sets = sets.filter(set -> filterAssessmentStatus.equals(
                    Objects.nonNull(set.getAssessmentStatus()) ? set.getAssessmentStatus().getRefElementCode() : null));
        }

        if (Optional.ofNullable(filterAssessmentType).isPresent()) {
            sets = sets.filter(set -> filterAssessmentType.equals(
                    Objects.nonNull(set.getAssessmentStatus()) ? set.getAssessmentType().getRefElementCode() : null));
        }

        if (Optional.ofNullable(filterGroupStatus).isPresent()) {
            sets = sets.filter(set -> filterGroupStatus.equals(set.getGroup() == null ? null : set.getGroup().getHistoricStatusELm()));
        }

        if (Optional.ofNullable(filterVoided).isPresent()) {
            sets = sets.filter(set -> filterVoided == (Objects.nonNull(set.getAssessmentVoidedDate())));
        }

        return sets;
    }
}