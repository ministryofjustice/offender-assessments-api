package uk.gov.justice.digital.oasys.service;


import com.microsoft.applicationinsights.TelemetryClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.api.OasysUserAuthenticationDto;
import uk.gov.justice.digital.oasys.api.OffenderPermissionResource;
import uk.gov.justice.digital.oasys.jpa.entity.AreaEstUserRole;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OasysAuthenticationRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static uk.gov.justice.digital.oasys.api.OffenderPermissionLevel.*;

@ExtendWith(SpringExtension.class)
public class AuthenticationServiceTest {


    @Mock
    OasysUserRepository oasysUserRepository;

    @Mock
    OasysAuthenticationRepository oasysAuthenticationRepository;

    @Mock
    TelemetryClient telemetryClient;

    AuthenticationService service;

    @BeforeEach
    public void setup() {
        service = new AuthenticationService(oasysUserRepository, oasysAuthenticationRepository, telemetryClient);
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

        when(oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase("TEST_USER")).thenReturn(Optional.ofNullable(user));

        Optional<OasysUserAuthenticationDto> result = service.getUserByUserId("TEST_USER");

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

        when(oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase("TEST_USER")).thenReturn(Optional.ofNullable(user));

        Optional<OasysUserAuthenticationDto> result = service.getUserByUserId("TEST_USER");
        assertThat(result.get().isEnabled()).isFalse();
    }

    @Test
    public void getUserShouldSetEnabledWhenStatusIsActive() {

        OasysUser user = OasysUser.builder()
                .oasysUserCode("TEST_USER")
                .roles(Collections.emptyList())
                .userStatus(RefElement.builder().refCategoryCode("USER_STATUS").refElementCode("ACTIVE").refElementDesc("Active").build())
                .build();

        when(oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase("TEST_USER")).thenReturn(Optional.ofNullable(user));

        Optional<OasysUserAuthenticationDto> result = service.getUserByUserId("TEST_USER");
        assertThat(result.get().isEnabled()).isTrue();
    }


    @Test
    public void validateUserShouldReturnTrueWhenFunctionReturnsTrue() {
        when(oasysAuthenticationRepository.validateCredentials("TEST_USER", "PASSWORD")).thenReturn(Optional.ofNullable("{STATE: \"SUCCESS\"}"));
        boolean result = service.validateUserCredentials("TEST_USER", "PASSWORD");
        assertThat(result).isTrue();
    }

    @Test
    public void validateUserShouldReturnFalseWhenFunctionReturnsFalse() {
        when(oasysAuthenticationRepository.validateCredentials("TEST_USER", "INVALIDPASSWORD")).thenReturn(Optional.ofNullable("[{STATE: \"FAILED\"}]"));
        boolean result = service.validateUserCredentials("TEST_USER", "INVALIDPASSWORD");
        assertThat(result).isFalse();
    }

    @Test
    public void validateUserShouldReturnFalseWhenFunctionCallReturnsInvalidResponse(){
        when(oasysAuthenticationRepository.validateCredentials("TEST_USER", "INVALIDPASSWORD")).thenReturn(Optional.ofNullable(""));
        boolean result = service.validateUserCredentials("TEST_USER", "INVALIDPASSWORD");
        assertThat(result).isFalse();
    }

    @Test
    public void validateUserShouldReturnFalseIfUsernameIsNullOrEmptyAndNotCallDatabase() {
        boolean result = service.validateUserCredentials("", "TEST" );
        assertThat(result).isFalse();
        verifyNoMoreInteractions(oasysAuthenticationRepository);

        result = service.validateUserCredentials(null, "TEST" );
        assertThat(result).isFalse();
        verifyNoMoreInteractions(oasysAuthenticationRepository);
    }

    @Test
    public void validateUserShouldReturnFalseIfPasswordIsNullOrEmptyAndNotCallDatabase() {

        boolean result = service.validateUserCredentials("TEST_USER", "" );
        assertThat(result).isFalse();
        verifyNoMoreInteractions(oasysAuthenticationRepository);

        result = service.validateUserCredentials("TEST_USER", null );
        assertThat(result).isFalse();
        verifyNoMoreInteractions(oasysAuthenticationRepository);
    }

    @Test
    public void validateUserShouldReturnFalseIfUsernameAndPasswordAreNullOrEmptyAndNotCallDatabase() {

        boolean result = service.validateUserCredentials(null,null );
        assertThat(result).isFalse();
        verifyNoMoreInteractions(oasysAuthenticationRepository);

        result = service.validateUserCredentials(null, null );
        assertThat(result).isFalse();
        verifyNoMoreInteractions(oasysAuthenticationRepository);
    }

    @Test
    public void authoriseUserSentencePlanShouldReturnWRITEWhenUserHasEditPermission() {
        when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession("TEST_USER", 1, 123456)).thenReturn(Optional.ofNullable("{STATE: \"EDIT\"}"));
        var result = service.userCanAccessOffenderRecord("TEST_USER", 1l, Optional.ofNullable(123456l), OffenderPermissionResource.SENTENCE_PLAN);
        assertThat(result.getOffenderPermissionLevel()).isEqualTo(WRITE);
        verify(oasysAuthenticationRepository, times(1)).validateUserSentencePlanAccessWithSession("TEST_USER", 1l, 123456l);
    }

    @Test
    public void authoriseUserSentencePlanShouldReturnREAD_ONLYWhenUserHasReadPermission() {
        when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession("TEST_USER", 1, 123456)).thenReturn(Optional.ofNullable("{STATE: \"READ\"}"));
        var result = service.userCanAccessOffenderRecord("TEST_USER", 1l, Optional.ofNullable(123456l), OffenderPermissionResource.SENTENCE_PLAN);
        assertThat(result.getOffenderPermissionLevel()).isEqualTo(READ_ONLY);
        verify(oasysAuthenticationRepository, times(1)).validateUserSentencePlanAccessWithSession("TEST_USER", 1l, 123456l);
    }

    @Test
    public void authoriseUserSentencePlanShouldReturnUNAUTHORISEDWhenUserHasNoAccess() {
        when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession("TEST_USER", 1, 123456)).thenReturn(Optional.ofNullable("{STATE: \"NO_ACCESS\"}"));
        var result = service.userCanAccessOffenderRecord("TEST_USER", 1l, Optional.ofNullable(123456l), OffenderPermissionResource.SENTENCE_PLAN);
        assertThat(result.getOffenderPermissionLevel()).isEqualTo(UNAUTHORISED);
        verify(oasysAuthenticationRepository, times(1)).validateUserSentencePlanAccessWithSession("TEST_USER", 1l, 123456l);
    }

    @Test
    public void authoriseUserSentencePlanShouldReturnUNAUTHORISEDWhenResultNotParsed() {
        when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession("TEST_USER", 1, 123456)).thenReturn(Optional.ofNullable("{STATE: \"INVALID_RESULT\"}"));
        var result = service.userCanAccessOffenderRecord("TEST_USER", 1l, Optional.ofNullable(123456l), OffenderPermissionResource.SENTENCE_PLAN);
        assertThat(result.getOffenderPermissionLevel()).isEqualTo(UNAUTHORISED);
        verify(oasysAuthenticationRepository, times(1)).validateUserSentencePlanAccessWithSession("TEST_USER", 1l, 123456l);
    }

    @Test
    public void authoriseUserSentencePlanShouldAttemptToRetrieveCurentSessionWhenNoSessionProvided() {
        when(oasysUserRepository.findCurrentUserSessionForOffender(1, "TEST_USER")).thenReturn(Optional.ofNullable(123456l));
        when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession("TEST_USER", 1, 123456)).thenReturn(Optional.ofNullable("{STATE: \"READ\"}"));
        var result = service.userCanAccessOffenderRecord("TEST_USER", 1l, Optional.empty(), OffenderPermissionResource.SENTENCE_PLAN);
        assertThat(result.getOffenderPermissionLevel()).isEqualTo(READ_ONLY);
        verify(oasysUserRepository, times(1)).findCurrentUserSessionForOffender(1, "TEST_USER");
        verify(oasysAuthenticationRepository, times(1)).validateUserSentencePlanAccessWithSession("TEST_USER", 1l, 123456l);
    }

    @Test
    public void authoriseUserSentencePlanShouldNotAttemptToRetrieveCurentSessionWhenSessionIdProvided() {
        when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession("TEST_USER", 1, 123456)).thenReturn(Optional.ofNullable("{STATE: \"READ\"}"));
        var result = service.userCanAccessOffenderRecord("TEST_USER", 1l, Optional.ofNullable(123456l), OffenderPermissionResource.SENTENCE_PLAN);
        assertThat(result.getOffenderPermissionLevel()).isEqualTo(READ_ONLY);
        verify(oasysUserRepository, never()).findCurrentUserSessionForOffender(1, "TEST_USER");
        verify(oasysAuthenticationRepository, times(1)).validateUserSentencePlanAccessWithSession("TEST_USER", 1l, 123456l);
    }
}