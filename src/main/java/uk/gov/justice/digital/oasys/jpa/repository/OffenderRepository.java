package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.oasys.jpa.entity.Offender;

import java.util.Optional;

public interface OffenderRepository extends JpaRepository<Offender,Long> {

    Optional<Offender> findByCmsProbNumber(String crn);
    Optional<Offender> findByCmsPrisNumber(String crn);
    Optional<Offender> findByPrisonNumber(String crn);
    Optional<Offender> findByPnc(String pnc);


}
