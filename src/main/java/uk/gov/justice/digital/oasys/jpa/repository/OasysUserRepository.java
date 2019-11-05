package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import java.util.Optional;

@Repository
public interface OasysUserRepository extends CrudRepository<OasysUser, String> {
    Optional<OasysUser> findOasysUserByOasysUserCodeIgnoreCase(String userCode);
}
