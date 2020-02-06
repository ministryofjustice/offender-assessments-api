package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.*;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderEntity;
import uk.gov.justice.digital.oasys.jpa.repository.AssessmentRepository;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.transformer.AssessmentsTransformer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static uk.gov.justice.digital.oasys.service.filters.AssessmentFilters.curry;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@Service
@Transactional(readOnly = true)
public class AssessmentsService {

    private final OffenderService offenderService;
    private final AssessmentRepository assessmentRepository;
    private final AssessmentsTransformer assessmentsTransformer;

    @Autowired
    public AssessmentsService(OffenderService offenderService, AssessmentRepository assessmentRepository, AssessmentsTransformer assessmentsTransformer) {
        this.offenderService = offenderService;
        this.assessmentRepository = assessmentRepository;
        this.assessmentsTransformer = assessmentsTransformer;
    }

    public Stream<OasysSet> getAssessmentsForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        OffenderEntity offenderEntity = offenderService.findOffender(identityType, identity);

        var assessmentsFilter = assessmentsTransformer.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        return offenderEntity.getOasysAssessmentGroups()
                .stream()
                .flatMap(oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream()));
    }

    public Assessment getLatestAssessmentForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        OffenderEntity offenderEntity = offenderService.findOffender(identityType, identity);

        var assessmentsFilter = assessmentsTransformer.assessmentsFilterOf(filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);

        var oasysSet = offenderEntity.getOasysAssessmentGroups()
                .stream()
                .flatMap(
                        oasysAssessmentGroup -> assessmentsFilter.apply(oasysAssessmentGroup.getOasysSets().stream()))
                .max(Comparator.comparing(OasysSet::getCreateDate));

        return assessmentsTransformer.assessmentOf(oasysSet.orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Assessment for Offender %s: %s, not found!", identityType, identity), ASSESSMENT_NOT_FOUND)));

    }

    public List<AssessmentNeed> getLatestAsessementNeedsForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        Assessment assessment = getLatestAssessmentForOffender(identityType, identity, filterGroupStatus, filterAssessmentType,filterVoided, filterAssessmentStatus);

        return assessment.getLayer3SentencePlanNeeds();
    }

    public Optional<Assessment> getAssessment(Long oasysSetId) {
        return assessmentRepository.findById(oasysSetId).map(assessmentsTransformer::assessmentOf);
    }

    public Question getLatestQAndAforOffender(String identityType, String identity, String assessmentType, String sectionRef, String questionRef) {

        final Assessment assessment = getLatestAssessmentForOffender(identityType, identity, Optional.empty(), Optional.of(assessmentType), Optional.empty(), Optional.empty());

        if(assessment.getSections().containsKey(sectionRef)) {
            var section = assessment.getSections().get(sectionRef);
            if(section.getQuestions().containsKey(questionRef)) {
                return section.getQuestions().get(questionRef);
            } else {
                throw new ApplicationExceptions.EntityNotFoundException(String.format("Section %s for Offender %s: %s, Assessment not found!", sectionRef, identityType, identity), SECTION_NOT_FOUND);
            }
        } else {
            throw new ApplicationExceptions.EntityNotFoundException(String.format("Question %s for Section %s for Offender %s: %s, not found!", questionRef, sectionRef, identityType, identity), QUESTION_NOT_FOUND);
        }


    }
}