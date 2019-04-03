package uk.gov.justice.digital.oasys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.oasys.api.Offender;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.oasys.transformer.OffenderTransformer;

import java.util.Optional;

@Service
public class OffenderService {

    private final OffenderRepository offenderRepository;
    private final OffenderTransformer offenderTransformer;

    @Autowired
    public OffenderService(OffenderRepository offenderRepository, OffenderTransformer offenderTransformer) {
        this.offenderRepository = offenderRepository;
        this.offenderTransformer = offenderTransformer;
    }

    public Optional<Offender> findOffenderByOasysOffenderId(Long oasysId) {
        return offenderRepository.findById(oasysId).map(
                offenderTransformer::offenderOf
        );
    }

    public Optional<Offender> findOffenderByNomisId(String nomsId) {
        return offenderRepository.getByCmsPrisNumber(nomsId).map(
                offenderTransformer::offenderOf
        );
    }

    public Optional<Offender> findOffenderByCrnId(String crn) {
        return offenderRepository.getByCmsProbNumber(crn).map(
                offenderTransformer::offenderOf
        );
    }

    public Optional<Offender> findOffenderBypnc(String nomsId) {
        return offenderRepository.getByPnc(nomsId).map(
                offenderTransformer::offenderOf
        );
    }

    public Optional<Offender> findOffenderByBookingNumber(String bookingNumber) {
        return offenderRepository.getByPrisonNumber(bookingNumber).map(
                offenderTransformer::offenderOf
        );
    }
}
