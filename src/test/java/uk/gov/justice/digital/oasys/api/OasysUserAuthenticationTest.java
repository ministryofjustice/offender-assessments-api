package uk.gov.justice.digital.oasys.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class OasysUserAuthenticationTest {

    @Test
    public void shouldConstructOasysUserAuthentication() {
        String userId = "anyUserId";
        String userName = "anyUserName";
        String email = "anyEmail";
        List<String> roles = List.of();
        List<String> regions = List.of();

        var user = new OasysUserAuthentication(userId, userName, email, roles, regions);

        Assertions.assertThat(user.getUserId()).isEqualTo(userId);
        Assertions.assertThat(user.getUserName()).isEqualTo(userName);
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getRoles()).isEqualTo(roles);
        Assertions.assertThat(user.getRegions()).isEqualTo(regions);

    }

}