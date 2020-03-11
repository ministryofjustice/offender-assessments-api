package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.FullSentencePlanDto;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uk.gov.justice.digital.oasys.utils.LogEvent.ASSESSMENT_NOT_FOUND;


@Service
@Transactional
public class SentencePlanService {

    private final OffenderService offenderService;

    @Autowired
    public SentencePlanService(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    public Optional<BasicSentencePlan> getLatestBasicSentencePlanForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType, identity);
        return latestBasicSentencePlanOf(assessmentGroups, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
    }

    public List<BasicSentencePlan> getBasicSentencePlansForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType, identity);
        return basicSentencePlansOf(assessmentGroups, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
    }

    public List<FullSentencePlanDto> getFullSentencePlansForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType, identity);
        return fullSentencePlansOf(assessmentGroups, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
    }

    public FullSentencePlanDto getFullSentencePlan(String identityType, String identity, Long oasysSetPk) {
        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType, identity);
        return fullSentencePlanOf(assessmentGroups, oasysSetPk);
    }

    private Optional<BasicSentencePlan> latestBasicSentencePlanOf(List<OasysAssessmentGroup> assessmentGroups, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        return getOasysSetStream(assessmentGroups, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus).max(Comparator.comparing(OasysSet::getCreateDate))
                .map(BasicSentencePlan::from);
    }

    private List<BasicSentencePlan> basicSentencePlansOf(List<OasysAssessmentGroup> assessmentGroups, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        return getOasysSetStream(assessmentGroups, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus)
                .map(BasicSentencePlan::from)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<FullSentencePlanDto> fullSentencePlansOf(List<OasysAssessmentGroup> assessmentGroups, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        return getOasysSetStream(assessmentGroups, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus)
                .map(FullSentencePlanDto::from)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private FullSentencePlanDto fullSentencePlanOf(List<OasysAssessmentGroup> assessmentGroups, Long oasysSetPk) {
        return FullSentencePlanDto.from(assessmentGroups.stream()
                .flatMap(oasysAssessmentGroup -> oasysAssessmentGroup.getOasysSets().stream())
                .filter(s-> s.getOasysSetPk().equals(oasysSetPk))
                .findFirst().orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Assessment %s, not found!", oasysSetPk), ASSESSMENT_NOT_FOUND)));
    }

    private Stream<OasysSet> getOasysSetStream(List<OasysAssessmentGroup> assessmentGroups, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        var oasysSets = assessmentGroups.stream().flatMap(oasysAssessmentGroup -> oasysAssessmentGroup.getOasysSets().stream());
        return AssessmentFilters.assessmentsFilterOf(oasysSets, filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);
    }


}