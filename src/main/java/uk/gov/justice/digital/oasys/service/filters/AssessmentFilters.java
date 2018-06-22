package uk.gov.justice.digital.oasys.service.filters;

import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class AssessmentFilters {

    public static final BiFunction<String, Stream<OasysSet>, Stream<OasysSet>> byAssessmentType =
            (type, oasysSets) -> oasysSets
                    .filter(set -> set.getAssessmentType().getRefElementCode().equals(type));

    public static final BiFunction<String, Stream<OasysSet>, Stream<OasysSet>> byGroupStatus =
            (groupStatus, oasysSets) -> oasysSets
                    .filter(set -> set.getGroup().getHistoricStatusELm().equals(groupStatus));

    public static final BiFunction<Boolean, Stream<OasysSet>, Stream<OasysSet>> byVoided =
            (isVoided, oasysSets) -> oasysSets
                    .filter(set -> isVoided ? set.getAssessmentVoidedDate() != null : set.getAssessmentVoidedDate() == null);

    public static final BiFunction<String, Stream<OasysSet>, Stream<OasysSet>> byAssessmentStatus =
            (assessmentStatus, oasysSets) -> oasysSets
                    .filter(set -> set.getAssessmentStatus().getRefElementCode().equals(assessmentStatus));

    public static final Function<Stream<OasysSet>, Stream<OasysSet>> identity = (oasysSets -> oasysSets);

    public static Function<Stream<OasysSet>, Stream<OasysSet>> curry(BiFunction<String, Stream<OasysSet>, Stream<OasysSet>> f, String x) {
        return y -> f.apply(x, y);
    }

    public static Function<Stream<OasysSet>, Stream<OasysSet>> curry(BiFunction<Boolean, Stream<OasysSet>, Stream<OasysSet>> f, Boolean x) {
        return y -> f.apply(x, y);
    }

}
