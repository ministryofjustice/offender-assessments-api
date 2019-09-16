package uk.gov.justice.digital.oasys.api;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class OasysUserAuthenticationTest {

    @Test
    public void shouldConstructOasysUserAuthentication() {
        String userId = "anyUserId";
        String userName = "anyUserName";
        String firstName = "first name";
        String lastName = "last name";
        String email = "anyEmail";
        boolean enabled = true;
        List<String> regions = List.of();

        var user = new OasysUserAuthentication(userId, userName,firstName, lastName, email, regions, enabled);

        Assertions.assertThat(user.getUserId()).isEqualTo(userId);
        Assertions.assertThat(user.getUserName()).isEqualTo(userName);
        Assertions.assertThat(user.getFirstName()).isEqualTo(firstName);
        Assertions.assertThat(user.getLastName()).isEqualTo(lastName);
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getRegions()).isEqualTo(regions);
        Assertions.assertThat(user.isEnabled()).isTrue();


    }

}