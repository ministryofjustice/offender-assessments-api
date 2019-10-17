package uk.gov.justice.digital.oasys.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;

import java.util.Optional;

@Repository
public interface OasysUserRepository extends CrudRepository<OasysUser, String> {
    Optional<OasysUser> findOasysUserByOasysUserCode(String userCode);

    @Query(nativeQuery = true, value = "SELECT authentication_pkg.api_authenicate_user(:p_username, :p_password) FROM dual")
    boolean validateCredentials(@Param("p_username") String username, @Param("p_password") String password);
}
