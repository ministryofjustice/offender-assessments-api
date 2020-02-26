package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;

import java.util.Optional;

@Repository
public interface OasysUserRepository extends CrudRepository<OasysUser, String> {
    Optional<OasysUser> findOasysUserByOasysUserCodeIgnoreCase(String userCode);

    @Query(value = "SELECT SESSION_SNAPSHOT_PK FROM SESSION_SNAPSHOT WHERE SESSION_STATE_KEY='OFFENDER_PK' AND SESSION_STATE_VALUE=?1 AND SESSION_SNAPSHOT_PK IN (\n" +
            "SELECT SESSION_SNAPSHOT_PK from SESSION_SNAPSHOT where SESSION_STATE_KEY='UNAME' AND SESSION_STATE_VALUE='STUARTWHITLAM')\n" +
            "AND ROWNUM <= 1\n" +
            "ORDER BY TIME_STAMP DESC", nativeQuery = true)
    Optional<Long> findCurrentUserSessionForOffender(long oasysOffenderId, String userCode);

}
