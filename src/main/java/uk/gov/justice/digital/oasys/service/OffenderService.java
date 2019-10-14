package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;

import java.util.Optional;

import static uk.gov.justice.digital.oasys.utils.LogEvent.OFFENDER_NOT_FOUND;

@Service
public class OffenderService {

    private final OffenderRepository offenderRepository;

    @Autowired
    public OffenderService(OffenderRepository offenderRepository) {
        this.offenderRepository = offenderRepository;
    }

    public Offender findOffender(String identifierType, String identifier) {

        OffenderIdentifier offenderIdentifier = OffenderIdentifier.fromString(identifierType);

        Optional<Offender> offender;
        switch (offenderIdentifier) {
            case CRN:
                offender = offenderRepository.getByCmsProbNumber(identifier);
                break;
            case PNC:
                offender =  offenderRepository.getByPnc(identifier);
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