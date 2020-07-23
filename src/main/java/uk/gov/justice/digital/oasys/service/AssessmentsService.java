package uk.gov.justice.digital.oasys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.AssessmentDto;
import uk.gov.justice.digital.oasys.api.AssessmentSummaryDto;
import uk.gov.justice.digital.oasys.jpa.entity.RefSection;
import uk.gov.justice.digital.oasys.jpa.entity.Assessment;
import uk.gov.justice.digital.oasys.jpa.entity.Section;
import uk.gov.justice.digital.oasys.jpa.repository.SimpleAssessmentRepository;
import uk.gov.justice.digital.oasys.service.domain.AssessmentNeed;
import uk.gov.justice.digital.oasys.service.domain.NeedsConfig;
import uk.gov.justice.digital.oasys.service.domain.SectionHeader;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.util.*;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.value;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AssessmentsService {

    private static final String LAYER_3 = "LAYER_3";
    private static final String ROSH_SECTION = "ROSH";
    private static final Set<String> POSITIVE_ANSWERS = Set.of("YES","Y");

    private final SimpleAssessmentRepository simpleAssessmentRepository;
    private final OffenderService offenderService;
    private final SectionService sectionService;


    @Autowired
    public AssessmentsService(SimpleAssessmentRepository simpleAssessmentRepository, OffenderService offenderService, SectionService sectionService) {
        this.simpleAssessmentRepository = simpleAssessmentRepository;
        this.offenderService = offenderService;
        this.sectionService = sectionService;
    }

    public Collection<AssessmentSummaryDto> getAssessmentsForOffender(String identityType, String identity, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {
        var offenderId = offenderService.getOffenderIdByIdentifier(identityType, identity);
        Collection<Assessment> assessments = simpleAssessmentRepository.getAssessmentsForOffender(offenderId,filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found {} Assessments for identity: ({},{})", assessments.size(), identity, identityType, value(EVENT, LogEvent.GET_ASSESSMENTS));
        return AssessmentSummaryDto.from(assessments);
    }

    public AssessmentDto getLatestAssessmentForOffender(String identityType, String identity, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {
        var offenderId = offenderService.getOffenderIdByIdentifier(identityType, identity);
        Assessment assessment = simpleAssessmentRepository.getLatestAssessment(offenderId,filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus)
                .orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Latest Assessment for Offender %s, not found!", offenderId), GET_LATEST_ASSESSMENT_NOT_FOUND));
        log.info("Found Latest Assessment type: {} status: {} for identity: ({},{})", assessment.getAssessmentType(), assessment.getAssessmentStatus(), identity, identityType, value(EVENT, LogEvent.GET_LATEST_ASSESSMENT_FOUND));
        return populateAssessmentDto(assessment);
    }

    public AssessmentDto getAssessment(Long oasysSetId) {
        Assessment assessment = simpleAssessmentRepository.getAssessment(oasysSetId)
                .orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Assessment for OasysSetId %s, not found!", oasysSetId), GET_ASSESSMENT_NOT_FOUND));
        log.info("Found Assessment type: {} status: {} for oasysSetId: {}", assessment.getAssessmentType(), assessment.getAssessmentStatus(), oasysSetId, value(EVENT, LogEvent.GET_ASSESSMENT_FOUND));
        return populateAssessmentDto(assessment);
    }

    private AssessmentDto populateAssessmentDto(Assessment assessment) {
        Boolean childSafeguardingIndicated = calculateChildSafeguardingIndicated(assessment.getAssessmentType(), assessment.getOasysSetPk());
        Collection<AssessmentNeed> needs = calculateNeeds(assessment.getAssessmentType(), assessment.getOasysSetPk());
        return AssessmentDto.from(assessment, childSafeguardingIndicated, needs);
    }

    private Boolean calculateChildSafeguardingIndicated(String assessmentType, Long oasysSetId) {
        var roshSection = sectionService.getSectionForAssessment(oasysSetId, ROSH_SECTION);
        if(Objects.isNull(roshSection)) {
            return null;
        }

        var answers = roshSection.getRefAnswers("R2.1", "R2.2");
        if (answers.isEmpty()) {
            return null;
        }

        return anyAnswersArePositive(answers.values());
    }

    private Collection<AssessmentNeed> calculateNeeds(String assessmentType, Long oasysSetId) {
        if (!LAYER_3.equals(assessmentType)) {
            return Set.of();
        }

        Collection<Section> needsSections = sectionService.getSectionsForAssessment(oasysSetId, NeedsConfig.getNeedsSectionHeadings());

        return needsSections.stream().map(AssessmentsService::checkRiskAndThresholdLevels).filter(AssessmentNeed::anyRiskFlagged).collect(Collectors.toSet());
    }

    private static AssessmentNeed checkRiskAndThresholdLevels(Section section){
        String sectionCode;
        String shortDesc;
        if (section.hasRefSection()) {
            sectionCode = section.getRefSection().getRefSectionCode();
            shortDesc = section.getRefSection().hasSectionType() ? section.getRefSection().getSectionType().getRefElementShortDesc() : null;
        }
        else {
            sectionCode = null;
            shortDesc = null;
        }
        SectionHeader sectionName = SectionHeader.findByValue(sectionCode);
        var answers = section.getRefAnswers(NeedsConfig.getHarmQuestion(sectionCode), NeedsConfig.getReoffendingQuestion(sectionCode));
        var riskHarm = isPositiveAnswer(answers.get(NeedsConfig.getHarmQuestion(sectionCode)));
        var riskReoffending = isPositiveAnswer(answers.get(NeedsConfig.getReoffendingQuestion(sectionCode)));
        var overThreshold = sectionIsOverThreshold(section);
        var flaggedAsNeed = isPositiveAnswer(section.getLowScoreNeedAttnInd());

        //section type code enum
        return new AssessmentNeed(sectionName, shortDesc, riskHarm, riskReoffending, overThreshold, flaggedAsNeed);
    }

    private static boolean sectionIsOverThreshold(Section section) {
        Long rawScore = Optional.ofNullable(section.getSectOtherRawScore()).orElse(0L);
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

    private static boolean isPositiveAnswer(String answer) {
        if(Objects.isNull(answer)) {
            return false;
        }
        var capsAnswer = answer.toUpperCase();
        return POSITIVE_ANSWERS.contains(capsAnswer);
    }

}
