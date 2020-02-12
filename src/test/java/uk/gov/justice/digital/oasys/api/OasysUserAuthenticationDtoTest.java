package uk.gov.justice.digital.oasys.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;

import java.util.Set;

public class OasysUserAuthenticationDtoTest {

    @Test
    public void shouldConstructOasysUserAuthentication() {
        String userId = "anyUserId";
        String userName = "anyUserName";
        String firstName = "first name";
        String lastName = "last name";
        String email = "anyEmail";
        boolean enabled = true;
        Set<String> regions = Set.of();

        var user = new OasysUserAuthenticationDto(userId, userName,firstName, lastName, email, regions, enabled);

        Assertions.assertThat(user.getUserId()).isEqualTo(userId);
        Assertions.assertThat(user.getUserName()).isEqualTo(userName);
        Assertions.assertThat(user.getFirstName()).isEqualTo(firstName);
        Assertions.assertThat(user.getLastName()).isEqualTo(lastName);
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getRegions()).isEqualTo(regions);
        Assertions.assertThat(user.isEnabled()).isTrue();


    }

}