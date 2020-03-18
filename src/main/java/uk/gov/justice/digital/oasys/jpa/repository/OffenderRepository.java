package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;

import java.util.Optional;

//@Deprecated(forRemoval = true)
public interface OffenderRepository extends JpaRepository<Offender, Long> {

    Optional<Offender> getByCmsProbNumber(String crn);

    Optional<Offender> getByCmsPrisNumber(String nomisId);

    Optional<Offender> getByPrisonNumber(String bookingId);

    Optional<Offender> getByPnc(String pnc);


}
