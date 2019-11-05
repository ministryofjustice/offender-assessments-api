package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.justice.digital.oasys.api.*;
import uk.gov.justice.digital.oasys.jpa.repository.AssessmentRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysAuthenticationRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;

import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class AuthenticationControllerTest {

    @LocalServerPort
    int port;

    @MockBean
    private OffenderRepository offenderRepository;

    @MockBean
    private OasysUserRepository oasysUserRepository;


    @MockBean
    private OasysAuthenticationRepository oasysAuthenticationRepository;

    @Autowired
    @Qualifier("globalObjectMapper")
    private ObjectMapper objectMapper;

    @Value("${sample.token}")
    private String validOauthToken;

    @Before
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));

        ControllerTestContext.setup(offenderRepository);

        Mockito.when(oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase(eq("USER_CODE"))).thenReturn(Optional.ofNullable(ControllerTestContext.oasysUser("USER_CODE")));
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
    public void canGetuserForUserCode() {
        OasysUserAuthentication oasysUser = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/authentication/user/{0}", "USER_CODE")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OasysUserAuthentication.class);

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


}