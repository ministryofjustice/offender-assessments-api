package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.OffenderDto;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderLink;
import uk.gov.justice.digital.oasys.jpa.entity.simple.OffenderSummary;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderLinkRepository;
import uk.gov.justice.digital.oasys.jpa.repository.simple.SimpleOffenderRepository;
import uk.gov.justice.digital.oasys.service.exception.ApplicationExceptions;

import java.util.Objects;

import static uk.gov.justice.digital.oasys.utils.LogEvent.OFFENDER_NOT_FOUND;

@Service
public class OffenderService {

    private final SimpleOffenderRepository simpleOffenderRepository;
    private OffenderLinkRepository offenderLinkRepository;

    @Autowired
    public OffenderService(SimpleOffenderRepository simpleOffenderRepository, OffenderLinkRepository offenderLinkRepository) {
        this.simpleOffenderRepository = simpleOffenderRepository;
        this.offenderLinkRepository = offenderLinkRepository;
    }

    public Long getOffenderIdByIdentifier(String identityType, String identity) {
        return getOffenderSummary(identityType, identity).getOffenderPk();
    }

    public OffenderDto getOffender(String identityType, String identity) {
        return OffenderDto.from(getOffenderSummary(identityType, identity));
    }

    private OffenderSummary getOffenderSummary(String identityType, String identity) {
       return checkForOffenderMerge(simpleOffenderRepository.getOffender(identityType, identity)
                .orElseThrow(() ->new ApplicationExceptions.EntityNotFoundException(String.format("Offender %s: %s, not found!", identityType, identity), OFFENDER_NOT_FOUND)));
    }

    private OffenderSummary checkForOffenderMerge(OffenderSummary offender) {
        if(Objects.nonNull(offender.getMergeIndicated()) && offender.getMergeIndicated().equals("Y")) {
            var linkedOffender = offenderLinkRepository.findMergedOffender(offender.getOffenderPk());
            if(linkedOffender.isPresent()) {
                var mergedOffenderPK = findMergedOffenderPK(linkedOffender.get());
                offender.setMergedOffenderPK(mergedOffenderPK);
            }
        }
        return offender;
    }

    private Long findMergedOffenderPK(OffenderLink mergedOffender) {
            var linkedOffender = offenderLinkRepository.findMergedOffender(mergedOffender.getMergedOffenderPK());
            if(linkedOffender.isPresent()) {
                return findMergedOffenderPK(linkedOffender.get());
            }
            return mergedOffender.getMergedOffenderPK();
        }
}