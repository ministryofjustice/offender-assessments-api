package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.AssessmentDto;
import uk.gov.justice.digital.oasys.api.AssessmentNeedDto;
import uk.gov.justice.digital.oasys.api.QuestionDto;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSection;
import uk.gov.justice.digital.oasys.jpa.entity.OasysSet;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;
import uk.gov.justice.digital.oasys.jpa.repository.AssessmentRepository;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;
import uk.gov.justice.digital.oasys.service.domain.NeedsConfig;
import uk.gov.justice.digital.oasys.service.domain.Section;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.service.filters.AssessmentFilters;

import java.util.*;
import java.util.stream.Stream;

import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@Service
@Transactional(readOnly = true)
public class AssessmentsService {

    private static final String ROSH_SECTION = "ROSH";
    private static final Set<String> POSITIVE_ANSWERS = Set.of("YES","Y");

    private final OffenderService offenderService;
    private final AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentsService(OffenderService offenderService, AssessmentRepository assessmentRepository) {
        this.offenderService = offenderService;
        this.assessmentRepository = assessmentRepository;
    }

    public Stream<OasysSet> getAssessmentsForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        List<OasysAssessmentGroup> assessmentGroups = offenderService.findOffenderAssessmentGroup(identityType, identity);
        var oasysAssessments = assessmentGroups.stream().flatMap(oasysAssessmentGroup -> oasysAssessmentGroup.getOasysSets().stream());
        return AssessmentFilters.assessmentsFilterOf(oasysAssessments, filterAssessmentStatus, filterAssessmentType, filterGroupStatus, filterVoided);
    }

    public AssessmentDto getLatestAssessmentForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        var allAssessmentsForOffender = getAssessmentsForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        var latestAssessment = allAssessmentsForOffender.max(Comparator.comparing(OasysSet::getCreateDate));
        return populateAssessmentDto(latestAssessment.orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Assessment for Offender %s: %s, not found!", identityType, identity), ASSESSMENT_NOT_FOUND)));
    }

    public Collection<AssessmentNeedDto> getLatestAsessementNeedsForOffender(String identityType, String identity, Optional<String> filterGroupStatus, Optional<String> filterAssessmentType, Optional<Boolean> filterVoided, Optional<String> filterAssessmentStatus) {
        AssessmentDto assessment = getLatestAssessmentForOffender(identityType, identity, filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        return assessment.getLayer3SentencePlanNeeds();
    }

    public AssessmentDto getAssessment(Long oasysSetId) {
        var oasysSet = assessmentRepository.findById(oasysSetId).orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Assessment for OasysSetId %s, not found!", oasysSetId), ASSESSMENT_NOT_FOUND));
        return populateAssessmentDto(oasysSet);
    }

    public QuestionDto getLatestQAndAforOffender(String identityType, String identity, String assessmentType, String sectionRef, String questionRef) {

        final AssessmentDto assessment = getLatestAssessmentForOffender(identityType, identity, Optional.empty(), Optional.of(assessmentType), Optional.empty(), Optional.empty());

        if (assessment.getSections().containsKey(sectionRef)) {
            var section = assessment.getSections().get(sectionRef);
            if (section.getQuestions().containsKey(questionRef)) {
                return section.getQuestions().get(questionRef);
            } else {
                throw new ApplicationExceptions.EntityNotFoundException(String.format("Section %s for Offender %s: %s, Assessment not found!", sectionRef, identityType, identity), SECTION_NOT_FOUND);
            }
        } else {
            throw new ApplicationExceptions.EntityNotFoundException(String.format("Question %s for Section %s for Offender %s: %s, not found!", questionRef, sectionRef, identityType, identity), QUESTION_NOT_FOUND);
        }
    }

    private AssessmentDto populateAssessmentDto(OasysSet set) {
        Boolean childSafeguardingIndicated = calculateChildSafeguardingIndicated(set);
        Stream<AssessmentNeed> needs = calculateNeeds(set);
        return AssessmentDto.from(set, childSafeguardingIndicated, needs);
    }

    private Boolean calculateChildSafeguardingIndicated(OasysSet set) {
        if (set.isNotLayer3()) {
            return null;
        }

        OasysSection roshSection = getSection(set, ROSH_SECTION);
        if(Objects.isNull(roshSection)) {
            return null;
        }

        var answers = roshSection.getRefAnswers("R2.1", "R2.2");
        if (answers.isEmpty()) {
            return null;
        }

        return anyAnswersArePositive(answers.values());
    }

    private Stream<AssessmentNeed> calculateNeeds(OasysSet set) {
        if (set.isNotLayer3()) {
            return Stream.of();
        }

        Stream<OasysSection> needsSections = getSections(set, NeedsConfig.getNeedsSectionHeadings());

        return needsSections.map(this::checkRiskAndThresholdLevels).filter(AssessmentNeed::anyRiskFlagged);
    }

    public AssessmentNeed checkRiskAndThresholdLevels(OasysSection section){
        String sectionCode = section.getRefSection().getRefSectionCode();
        Section sectionName = Section.findByValue(sectionCode);
        var answers = section.getRefAnswers(NeedsConfig.getHarmQuestion(sectionCode), NeedsConfig.getReoffendingQuestion(sectionCode));
        var riskHarm = isPositiveAnswer(answers.get(NeedsConfig.getHarmQuestion(sectionCode)));
        var riskReoffending = isPositiveAnswer(answers.get(NeedsConfig.getReoffendingQuestion(sectionCode)));
        var overThreshold = sectionIsOverThreshold(section);
        var flaggedAsNeed = isPositiveAnswer(section.getLowScoreNeedAttnInd());

        //section type code enum
        return new AssessmentNeed(sectionName,riskHarm, riskReoffending, overThreshold, flaggedAsNeed);
    }

    private static boolean sectionIsOverThreshold(OasysSection section) {
        Long rawScore = section.getSectOtherRawScore();

        if(Objects.isNull(rawScore) || !section.hasRefSection()) {
            return false;
        }

        Long threshold = Optional.of(section.getRefSection()).map(RefSection::getCrimNeedScoreThreshold).orElse(Long.MAX_VALUE);

        return rawScore >= threshold;
    }

    private static boolean anyAnswersArePositive(Collection<String> answers) {
        for(String positiveAnswer : POSITIVE_ANSWERS){
            if(answers.contains(positiveAnswer)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPositiveAnswer(String answer) {
        if(Objects.isNull(answer)) {
            return false;
        }
        var capsAnswer = answer.toUpperCase();
        return POSITIVE_ANSWERS.contains(capsAnswer);
    }

    private static OasysSection getSection(OasysSet set, String section) {
        return getSections(set,Set.of(section)).findFirst().orElse(null);
    }

    private static Stream<OasysSection> getSections(OasysSet set, Set<String> sections) {
        return set.getOasysSections().stream()
                .filter(oasysSection ->
                        oasysSection.hasRefSection() &&
                        sections.contains(oasysSection.getRefSection().getRefSectionCode()));
    }

}
