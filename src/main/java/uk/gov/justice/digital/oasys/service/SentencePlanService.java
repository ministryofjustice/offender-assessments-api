package uk.gov.justice.digital.oasys.service;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.ProperSentencePlanDto;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.transformer.AssessmentsTransformer;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Transactional
public class SentencePlanService {

    private final OffenderService offenderService;
    private final AssessmentsTransformer assessmentsTransformer;

    @Autowired
    public SentencePlanService(OffenderService offenderService, AssessmentsTransformer assessmentsTransformer) {
        this.offenderService = offenderService;
        this.assessmentsTransformer = assessmentsTransformer;
    }

    public Optional<BasicSentencePlan> getLatestBasicSentencePlanForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {

        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsTransformer.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType,identity);
        return latestBasicSentencePlanOf(assessmentsFilter, assessmentGroups);
    }

    public List<BasicSentencePlan> getBasicSentencePlansForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsTransformer.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType,identity);
        return basicSentencePlansOf(assessmentsFilter, assessmentGroups);
    }

    public List<ProperSentencePlanDto> getFullSentencePlansForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsTransformer.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType,identity);
        return fullSentencePlansOf(assessmentsFilter, assessmentGroups);

    }

    private Optional<BasicSentencePlan> latestBasicSentencePlanOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return getOasysSetStream(assessmentsFilter, assessmentGroups).max(Comparator.comparing(OasysSet::getCreateDate))
                .map(BasicSentencePlan::from);
    }

    private List<BasicSentencePlan> basicSentencePlansOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return getOasysSetStream(assessmentsFilter, assessmentGroups)
                .map(BasicSentencePlan::from)
                .collect(Collectors.toList());
    }

    private List<ProperSentencePlanDto> fullSentencePlansOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return getOasysSetStream(assessmentsFilter, assessmentGroups)
                .map(ProperSentencePlanDto::from)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Stream<OasysSet> getOasysSetStream(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return assessmentGroups
                .stream()
                .flatMap(oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream()));
    }
}