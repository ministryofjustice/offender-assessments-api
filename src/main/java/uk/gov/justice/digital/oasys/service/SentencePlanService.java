package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.ProperSentencePlan;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.transformer.AssessmentsTransformer;
import uk.gov.justice.digital.oasys.transformer.BasicSentencePlanTransformer;
import uk.gov.justice.digital.oasys.transformer.SentencePlanTransformer;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Transactional
public class SentencePlanService {

    private final OffenderService offenderService;
    private final BasicSentencePlanTransformer basicSentencePlanTransformer;
    private final AssessmentsTransformer assessmentsTransformer;
    private final SentencePlanTransformer properSentencePlanTransformer;

    @Autowired
    public SentencePlanService(OffenderService offenderService, BasicSentencePlanTransformer basicSentencePlanTransformer, AssessmentsTransformer assessmentsTransformer, SentencePlanTransformer properSentencePlanTransformer) {
        this.offenderService = offenderService;
        this.basicSentencePlanTransformer = basicSentencePlanTransformer;
        this.assessmentsTransformer = assessmentsTransformer;
        this.properSentencePlanTransformer = properSentencePlanTransformer;
    }

    private Optional<BasicSentencePlan> latestBasicSentencePlanOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return getOasysSetStream(assessmentsFilter, assessmentGroups)
                .max(Comparator.comparing(OasysSet::getCreateDate))
                .flatMap(basicSentencePlanTransformer::basicSentencePlanOf);
    }

    private Stream<OasysSet> getOasysSetStream(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return assessmentGroups
                .stream()
                .flatMap(oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream()));
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

    private List<BasicSentencePlan> basicSentencePlansOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return getOasysSetStream(assessmentsFilter, assessmentGroups)
                .map(basicSentencePlanTransformer::basicSentencePlanOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<ProperSentencePlan> getFullSentencePlansForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        final Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter =
                assessmentsTransformer.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType,identity);
        return fullSentencePlansOf(assessmentsFilter, assessmentGroups);

    }

    private List<ProperSentencePlan> fullSentencePlansOf(Function<Stream<OasysSet>, Stream<OasysSet>> assessmentsFilter, List<OasysAssessmentGroup> assessmentGroups) {
        return getOasysSetStream(assessmentsFilter, assessmentGroups)
                .map(properSentencePlanTransformer::sentencePlanOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }




}