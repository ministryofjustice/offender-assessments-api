package uk.gov.justice.digital.oasys.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.justice.digital.oasys.api.OasysUserAuthentication;
import uk.gov.justice.digital.oasys.jpa.entity.AreaEstUserRole;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {


    @Mock
    OasysUserRepository oasysUserRepository;

    AuthenticationService service;

    @Before
    public void setup() {
        service = new AuthenticationService(oasysUserRepository);
    }

    @Test
    public void getUserShouldReturnAUserWhenExists() {

        OasysUser user = OasysUser.builder()
                .oasysUserCode("TEST_USER")
                .userForename1("Test")
                .userFamilyName("User")
                .emailAddress("test@test.com")
                .userStatus(RefElement.builder().refCategoryCode("USER_STATUS").refElementCode("ACTIVE").refElementDesc("Active").build())
                .roles(List.of(AreaEstUserRole.builder().ctAreaEstCode("1234").build()))
                .build();

        when(oasysUserRepository.findOasysUserByOasysUserCode("TEST_USER")).thenReturn(Optional.ofNullable(user));

        Optional<OasysUserAuthentication> result = service.getUserByUserId("TEST_USER");

        assertThat(result.get().getFirstName()).isEqualTo("Test");
        assertThat(result.get().getLastName()).isEqualTo("User");
        assertThat(result.get().getEmail()).isEqualTo("test@test.com");
        assertThat(result.get().getUserName()).isEqualTo("TEST_USER");
        assertThat(result.get().getUserId()).isEqualTo("TEST_USER");
        assertThat(result.get().getRegions()).contains("1234");
    }

    @Test
    public void getUserShouldSetEnabledToFalseWhenStatusIsNotActive() {

        OasysUser user = OasysUser.builder()
                .oasysUserCode("TEST_USER")
                .roles(Collections.emptyList())
                .userStatus(RefElement.builder().refCategoryCode("USER_STATUS").refElementCode("DISABLED").refElementDesc("Active").build())
                .build();

        when(oasysUserRepository.findOasysUserByOasysUserCode("TEST_USER")).thenReturn(Optional.ofNullable(user));

        Optional<OasysUserAuthentication> result = service.getUserByUserId("TEST_USER");
        assertThat(result.get().isEnabled()).isFalse();
    }

    @Test
    public void getUserShouldSetEnabledeWhenStatusIsActive() {

        OasysUser user = OasysUser.builder()
                .oasysUserCode("TEST_USER")
                .roles(Collections.emptyList())
                .userStatus(RefElement.builder().refCategoryCode("USER_STATUS").refElementCode("ACTIVE").refElementDesc("Active").build())
                .build();

        when(oasysUserRepository.findOasysUserByOasysUserCode("TEST_USER")).thenReturn(Optional.ofNullable(user));

        Optional<OasysUserAuthentication> result = service.getUserByUserId("TEST_USER");
        assertThat(result.get().isEnabled()).isTrue();
    }


    @Test
    public void validateUserShouldReturnTrueWhenFunctionReturnsTrue() {
        when(oasysUserRepository.validateCredentials("TEST_USER", "PASSWORD")).thenReturn(true);
        boolean result = service.validateUserCredentials("TEST_USER", "PASSWORD");
        assertThat(result).isTrue();
    }

    @Test
    public void validateUserShouldReturnFalseWhenFunctionReturnsFalse() {
        when(oasysUserRepository.validateCredentials("TEST_USER", "INVALIDPASSWORD")).thenReturn(false);
        boolean result = service.validateUserCredentials("TEST_USER", "INVALIDPASSWORD");
        assertThat(result).isFalse();
    }
}