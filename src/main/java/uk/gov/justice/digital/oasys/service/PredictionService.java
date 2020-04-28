package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Predictor;
import uk.gov.justice.digital.oasys.jpa.repository.SimpleAssessmentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PredictionService {

    private final OffenderService offenderService;
    private final SimpleAssessmentRepository simpleAssessmentRepository;

    @Autowired
    public PredictionService(OffenderService offenderService, SimpleAssessmentRepository simpleAssessmentRepository) {
        this.offenderService = offenderService;
        this.simpleAssessmentRepository = simpleAssessmentRepository;
    }

    public Set<Predictor> getAllPredictorsForOffender(String identityType, String identity) {
        var offenderId = offenderService.getOffenderIdByIdentifier(identityType, identity);
        var assessments = simpleAssessmentRepository.getAssessmentsForOffender(offenderId);
        return assessments.stream()
                .map(Predictor::from).filter(Objects::nonNull).collect(Collectors.toSet());
    }

}

