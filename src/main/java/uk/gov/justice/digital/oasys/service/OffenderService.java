package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import uk.gov.justice.digital.oasys.api.simple.OffenderDto;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.jpa.repository.simple.SimpleOffenderRepository;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;

import java.util.List;
import java.util.Optional;

import static uk.gov.justice.digital.oasys.utils.LogEvent.OFFENDER_NOT_FOUND;

@Service
public class OffenderService {

    private final SimpleOffenderRepository simpleOffenderRepository;
    private final OffenderRepository offenderRepository;

    @Autowired
    public OffenderService(OffenderRepository offenderRepository, SimpleOffenderRepository simpleOffenderRepository) {
        this.offenderRepository = offenderRepository;
        this.simpleOffenderRepository = simpleOffenderRepository;
    }

    public Long getOffenderIdByIdentifier(String identifierType, String identifier) {
        return simpleOffenderRepository.getOffender(identifierType, identifier).getOffenderPk();
    }

    public OffenderDto getOffender(String identifierType, String identifier) {
        return OffenderDto.from(simpleOffenderRepository.getOffender(identifierType, identifier));
    }

    // only used for sentence plans now
    @Deprecated(forRemoval = true)
    public List<OasysAssessmentGroup> findOffenderAssessmentGroup(String identifierType, String identifier) {
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.fromString(identifierType);
        return findOffenderByIdentifier(offenderIdentifier, identifier).getOasysAssessmentGroups();
    }

    private Offender findOffenderByIdentifier(OffenderIdentifier identifierType, String identifier) {

        Optional<Offender> offender;
        switch (identifierType) {
            case CRN:
                offender = offenderRepository.getByCmsProbNumber(identifier);
                break;
            case PNC:
                offender = offenderRepository.getByPnc(identifier);
                break;
            case NOMIS:
                offender = offenderRepository.getByCmsPrisNumber(identifier);
                break;
            case OASYS:
                offender = offenderRepository.findById(Long.valueOf(identifier));
                break;
            case BOOKING:
                offender = offenderRepository.getByPrisonNumber(identifier);
                break;
            default:
                offender = Optional.empty();
                break;
        }
        return offender.orElseThrow(() -> new ApplicationExceptions.EntityNotFoundException(String.format("Offender %s: %s, not found!", identifierType, identifier), OFFENDER_NOT_FOUND));
    }

}