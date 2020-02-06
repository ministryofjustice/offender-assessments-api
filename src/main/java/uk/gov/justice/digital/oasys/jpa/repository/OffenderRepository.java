package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderEntity;

import java.util.Optional;

public interface OffenderRepository extends JpaRepository<OffenderEntity,Long> {

    Optional<OffenderEntity> getByCmsProbNumber(String crn);
    Optional<OffenderEntity> getByCmsPrisNumber(String nomisId);
    Optional<OffenderEntity> getByPrisonNumber(String bookingId);
    Optional<OffenderEntity> getByPnc(String pnc);



}
