package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.digital.oasys.jpa.entity.AreaEstUserRole;
import uk.gov.justice.digital.oasys.jpa.entity.OasysUser;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.OasysUserRepository;
import uk.gov.justice.digital.oasys.jpa.repository.OffenderRepository;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static java.util.List.of;
import static org.mockito.ArgumentMatchers.eq;

public class OAuthIntegrationTest extends IntegrationTest {

    @MockBean
    private OffenderRepository offenderRepository;

    @MockBean
    private OasysUserRepository oasysUserRepository;

    @Value("${sample.token}")
    private String readonlyOauthToken;

    @Value("${sample.invalidRoleToken}")
    private String invalidRoleToken;

    @Value("${sample.authRoleToken}")
    private String authRoleToken;

    @BeforeEach
    public void setup() {

        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));
    }

    @Test
    public void endpointReturnsUnauthorisedWhenNoTokenProvided() {
        given()
                .when()
                .get("/offenders/oasysOffenderId/{0}", 1L)
                .then()
                .statusCode(401);
    }

    @Test
    public void authorisationEndpointReturnsUnauthorisedWhenNoTokenProvided() {
        given()
                .when()
                .get("/authentication/user")
                .then()
                .statusCode(401);
    }

    @Test
    public void returnsForbiddenWhenRoleIsINVALID_ROLE() {
        given()
                .when()
                .auth().oauth2(invalidRoleToken)
                .get("/offenders/oasysOffenderId/{0}", 1L)
                .then()
                .statusCode(403);
    }

    @Test
    public void authorisationEndpointReturnsOKWhenRoleIsOASYS_READ_ONLY() {
        Mockito.when(oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase(eq("USER_CODE"))).thenReturn(Optional.ofNullable(oasysUser("USER_CODE")));

        given()
                .when()
                .auth().oauth2(readonlyOauthToken)
                .get("/authentication/user/{0}", "USER_CODE")
                .then()
                .statusCode(200);
    }

    @Test
    public void authorisationEndpointReturnsOKWhenRoleIsOASYS_AUTHENTICATION() {
        Mockito.when(oasysUserRepository.findOasysUserByOasysUserCodeIgnoreCase(eq("USER_CODE"))).thenReturn(Optional.ofNullable(oasysUser("USER_CODE")));
        given()
                .when()
                .auth().oauth2(authRoleToken)
                .get("/authentication/user/{0}", "USER_CODE")
                .then()
                .statusCode(200);
    }

    @Test
    public void returnsForbiddenWhenRoleIsOASYS_AUTHENTICATIONAndEndpointIsOffenderData() {
        given()
                .when()
                .auth().oauth2(authRoleToken)
                .get("/offenders/oasysOffenderId/{0}", 1L)
                .then()
                .statusCode(403);
    }

    @Test
    public void returnsOKWhenRoleIsOASYS_READ_ONLYAndEndpointIsOffenderData() {
        Mockito.when(offenderRepository.findById(eq(1l))).thenReturn(ControllerTestContext.anOffender());
        given()
                .when()
                .auth().oauth2(readonlyOauthToken)
                .get("/offenders/oasysOffenderId/{0}", 1L)
                .then()
                .statusCode(200);
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