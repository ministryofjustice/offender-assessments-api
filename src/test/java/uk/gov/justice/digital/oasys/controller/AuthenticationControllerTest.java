package uk.gov.justice.digital.oasys.controller;


import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.digital.oasys.api.*;
import uk.gov.justice.digital.oasys.jpa.entity.AreaEstUserRole;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OasysAuthenticationRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static uk.gov.justice.digital.oasys.api.OffenderPermissionLevel.WRITE;
import static uk.gov.justice.digital.oasys.api.OffenderPermissionResource.SENTENCE_PLAN;


public class AuthenticationControllerTest extends IntegrationTest {

    @MockBean
    private OasysUserRepository oasysUserRepository;


    @MockBean
    private OasysAuthenticationRepository oasysAuthenticationRepository;

    @Value("${sample.token}")
    private String validOauthToken;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));
    }

    @Test
    public void endpointsAreAuthorised() {
        given()
                .when()
                .get("/authentication/user/{0}", "USER_CODE")
                .then()
                .statusCode(401);
    }

    @Test
    public void canGetUserForUserCode() {
        Mockito.when(oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase(eq("USER_CODE"))).thenReturn(Optional.ofNullable(oasysUser("USER_CODE")));
        OasysUserAuthenticationDto oasysUser = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/authentication/user/{0}", "USER_CODE")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OasysUserAuthenticationDto.class);

        assertThat(oasysUser.getUserName()).isEqualTo("USER_CODE");
    }

    @Test
    public void returnsNotFoundWhenUserDoesNotExist() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/authentication/user/{0}", "NON_EXISTING_USER")
                .then()
                .statusCode(404);

    }


    @Test
    public void returns200WhenUserIsAuthenticated() {

        Mockito.when(oasysAuthenticationRepository.validateCredentials(eq("USER_CODE"), eq("PASSWORD"))).thenReturn(Optional.ofNullable("{STATE: \"SUCCESS\"}"));
        ValidateUserRequest request = new ValidateUserRequest("USER_CODE", "PASSWORD");

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .header("Content-Type", "application/json")
                .body(request)
                .post("/authentication/user/validate")
                .then()
                .statusCode(200);

    }

    @Test
    public void returns401WhenUserCredentialsInvalid() {

        Mockito.when(oasysAuthenticationRepository.validateCredentials(eq("INVALID_USER"), eq("INVALID_PASSWORD"))).thenReturn(Optional.ofNullable("[{STATE: \"FAILURE\"}]"));
        ValidateUserRequest request = new ValidateUserRequest("INVALID_USER", "INVALID_PASSWORD");

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .header("Content-Type", "application/json")
                .body(request)
                .post("/authentication/user/validate")
                .then()
                .statusCode(401);
    }

    @Test
    public void returns401WhenInvalidJSONResponseReturnedFromOASys() {

        Mockito.when(oasysAuthenticationRepository.validateCredentials(eq("INVALID_USER"), eq("INVALID_PASSWORD"))).thenReturn(Optional.ofNullable(null));
        ValidateUserRequest request = new ValidateUserRequest("INVALID_USER", "INVALID_PASSWORD");

        given()
                .when()
                .auth().oauth2(validOauthToken)
                .header("Content-Type", "application/json")
                .body(request)
                .post("/authentication/user/validate")
                .then()
                .statusCode(401);
    }

    @Test
    public void canGetUserAuthorisationForSentencePlanWithPassedInSessionKey() {
        Mockito.when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession(eq("USER_CODE"),eq(1l),eq(123456l))).thenReturn(Optional.of("[{STATE: \"EDIT\"}]"));
        AuthorisationDto authorisationDto = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/authentication/user/{0}/offender/{1}/SENTENCE_PLAN?sessionId={2}", "USER_CODE", 1l, 123456l)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AuthorisationDto.class);

        assertThat(authorisationDto.getOffenderPermissionLevel()).isEqualTo(WRITE);
        assertThat(authorisationDto.getOasysUserCode()).isEqualTo("USER_CODE");
        assertThat(authorisationDto.getOasysOffenderId()).isEqualTo(1l);
        assertThat(authorisationDto.getOffenderPermissionResource()).isEqualTo(SENTENCE_PLAN);
    }

    @Test
    public void canGetUserAuthorisationForSentencePlanWithoutPassedInSessionKey() {
        Mockito.when(oasysAuthenticationRepository.validateUserSentencePlanAccessWithSession(eq("USER_CODE"),eq(1l),eq(123456l))).thenReturn(Optional.of("[{STATE: \"EDIT\"}]"));
        Mockito.when(oasysUserRepository.findCurrentUserSessionForOffender(eq(1l),eq("USER_CODE"))).thenReturn(Optional.of(123456l));

        AuthorisationDto authorisationDto = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/authentication/user/{0}/offender/{1}/SENTENCE_PLAN", "USER_CODE", 1l)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AuthorisationDto.class);

        assertThat(authorisationDto.getOffenderPermissionLevel()).isEqualTo(WRITE);
        assertThat(authorisationDto.getOasysUserCode()).isEqualTo("USER_CODE");
        assertThat(authorisationDto.getOasysOffenderId()).isEqualTo(1l);
        assertThat(authorisationDto.getOffenderPermissionResource()).isEqualTo(SENTENCE_PLAN);
    }

    public OasysUser oasysUser(String userCode) {
        return OasysUser.builder()
                .oasysUserCode(userCode)
                .userForename1("Test")
                .userFamilyName("User")
                .emailAddress("test@test.com")
                .userStatus(RefElement.builder().refCategoryCode("USER_STATUS").refElementCode("ACTIVE").refElementDesc("Active").build())
                .roles(of(AreaEstUserRole.builder().ctAreaEstCode("1234").build()))
                .build();
    }


}