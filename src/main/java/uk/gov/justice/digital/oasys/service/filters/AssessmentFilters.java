package uk.gov.justice.digital.oasys.service.filters;

import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class AssessmentFilters {

    public static final BiFunction<String, List<OasysSet>, List<OasysSet>> byAssessmentType =
            (type, oasysSets) -> oasysSets.stream()
                    .filter(set -> set.getRefAssVersion().getRefAssVersionCode().equals(type))
                    .collect(Collectors.toList());

    public static final BiFunction<String, List<OasysSet>, List<OasysSet>> byGroupStatus =
            (groupStatus, oasysSets) -> oasysSets.stream()
                    .filter(set -> set.getGroup().getHistoricStatusELm().equals(groupStatus))
                    .collect(Collectors.toList());

    public static final BiFunction<Boolean, List<OasysSet>, List<OasysSet>> byVoided =
            (isVoided, oasysSets) -> oasysSets.stream()
                    .filter(set -> isVoided ? set.getAssessmentVoidedDate() != null : set.getAssessmentVoidedDate() == null)
                    .collect(Collectors.toList());

    public static final BiFunction<String, List<OasysSet>, List<OasysSet>> byAssessmentStatus =
            (assessmentStatus, oasysSets) -> oasysSets.stream()
                    .filter(set -> set.getAssessmentStatus().getRefElementCode().equals(assessmentStatus))
                    .collect(Collectors.toList());

}
