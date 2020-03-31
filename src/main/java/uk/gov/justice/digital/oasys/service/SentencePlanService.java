package uk.gov.justice.digital.oasys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.oasys.api.BasicSentencePlan;
import uk.gov.justice.digital.oasys.api.FullSentencePlanDto;
import uk.gov.justice.digital.oasys.jpa.entity.simple.Assessment;
import uk.gov.justice.digital.oasys.jpa.repository.simple.SimpleAssessmentRepository;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;
import uk.gov.justice.digital.oasys.utils.LogEvent;

import java.util.*;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.value;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@Slf4j
@Service
@Transactional
public class SentencePlanService {

    private final OffenderService offenderService;
    private final SimpleAssessmentRepository simpleAssessmentRepository;
    private final SectionService sectionService;

    @Autowired
    public SentencePlanService(OffenderService offenderService, SimpleAssessmentRepository simpleAssessmentRepository, SectionService sectionService) {
        this.offenderService = offenderService;
        this.simpleAssessmentRepository = simpleAssessmentRepository;
        this.sectionService = sectionService;
    }

    public BasicSentencePlan getLatestBasicSentencePlanForOffender(String identityType, String identity, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {

        var offenderId = offenderService.getOffenderIdByIdentifier(identityType, identity);
        Assessment assessment = simpleAssessmentRepository.getLatestAssessment(offenderId,filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus)
                .orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Latest Basic Sentence Plan for Offender %s, not found!", offenderId), GET_BASIC_SP_NOT_FOUND));

        log.info("Found Latest Assessment type: {} status: {} for identity: ({},{})", assessment.getAssessmentType(), assessment.getAssessmentStatus(), identity, identityType, value(EVENT, LogEvent.GET_LATEST_BASIC_SP));
        return BasicSentencePlan.from(assessment);
    }

    public Collection<BasicSentencePlan> getBasicSentencePlansForOffender(String identityType, String identity, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {
        var offenderId = offenderService.getOffenderIdByIdentifier(identityType, identity);
        Collection<Assessment> assessments = simpleAssessmentRepository.getAssessmentsForOffender(offenderId,filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found {} Assessments for identity: ({},{})", assessments.size(), identity, identityType, value(EVENT, LogEvent.GET_BASIC_SP));
        return BasicSentencePlan.from(assessments);
    }

    public Collection<FullSentencePlanDto> getFullSentencePlansForOffender(String identityType, String identity, String filterGroupStatus, String filterAssessmentType, Boolean filterVoided, String filterAssessmentStatus) {
        var offenderId = offenderService.getOffenderIdByIdentifier(identityType, identity);
        Collection<Assessment> assessments = simpleAssessmentRepository.getAssessmentsForOffender(offenderId,filterGroupStatus, filterAssessmentType, filterVoided, filterAssessmentStatus);
        log.info("Found {} Assessments for identity: ({},{})", assessments.size(), identity, identityType, value(EVENT, GET_FULL_SP));
        return assessments.stream().map(this::fullSentencePlanFrom).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public FullSentencePlanDto getFullSentencePlan(Long oasysSetPk) {
        Assessment assessment = simpleAssessmentRepository.getAssessment(oasysSetPk)
                .orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Full Sentence Plan %s, not found!", oasysSetPk), GET_FULL_SP_NOT_FOUND));
        log.info("Found Latest Assessment: {}", assessment.getAssessmentType(), oasysSetPk, value(EVENT, LogEvent.GET_LATEST_FULL_SP));
        return fullSentencePlanFrom(assessment);
    }

    private FullSentencePlanDto fullSentencePlanFrom(Assessment assessment) {
        var section = sectionService.getSectionsForAssessment(assessment.getOasysSetPk(), "ISP", "RSP").stream().findFirst();
        return FullSentencePlanDto.from(assessment, section);
    }
}