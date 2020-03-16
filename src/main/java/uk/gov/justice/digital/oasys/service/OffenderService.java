package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OffenderIdentifier;
import uk.gov.justice.digital.oasys.api.simple.OffenderDto;
import uk.gov.justice.digital.oasys.jpa.entity.OasysAssessmentGroup;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;
import uk.gov.justice.digital.oasys.jpa.entity.simple.OffenderSummary;
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

    public Long getOffenderIdByIdentifier(String identityType, String identity) {
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.fromString(identityType);
        if(offenderIdentifier.equals(OffenderIdentifier.OASYS)) {
            return Long.valueOf(identity);
        }
        return getOffenderSummary(identityType, identity).getOffenderPk();
    }

    public OffenderDto getOffender(String identityType, String identity) {
        return OffenderDto.from(getOffenderSummary(identityType, identity));
    }

    // only used for sentence plans now
    //@Deprecated(forRemoval = true)
    public List<OasysAssessmentGroup> findOffenderAssessmentGroup(String identifierType, String identifier) {
        OffenderIdentifier offenderIdentifier = OffenderIdentifier.fromString(identifierType);
        return findOffenderByIdentifier(offenderIdentifier, identifier).getOasysAssessmentGroups();
    }

    private OffenderSummary getOffenderSummary(String identityType, String identity) {
       return simpleOffenderRepository.getOffender(identityType, identity)
                .orElseThrow(() ->new ApplicationExceptions.EntityNotFoundException(String.format("Offender %s: %s, not found!", identityType, identity), OFFENDER_NOT_FOUND));
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