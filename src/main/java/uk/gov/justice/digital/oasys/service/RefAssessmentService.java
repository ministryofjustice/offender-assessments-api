package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.*;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersion;
import uk.gov.justice.digital.oasys.jpa.entity.RefAssVersionPK;
import uk.gov.justice.digital.oasys.jpa.repository.RefAssessmentRepository;

import java.util.Optional;

@Service
public class RefAssessmentService {
    private final RefAssessmentRepository refAssessmentRepository;

    @Autowired
    public RefAssessmentService(RefAssessmentRepository refAssessmentRepository) {
        this.refAssessmentRepository = refAssessmentRepository;
    }

    public Optional<ReferenceAssessmentDto> getReferenceAssessmentOf(String type, String revision) {

        Optional<RefAssVersion> maybeRefAssVersion = refAssessmentRepository.findById(RefAssVersionPK.builder()
                .refAssVersionCode(type)
                .versionNumber(revision)
                .build());

        return maybeRefAssVersion.map(ReferenceAssessmentDto::from);
    }
}