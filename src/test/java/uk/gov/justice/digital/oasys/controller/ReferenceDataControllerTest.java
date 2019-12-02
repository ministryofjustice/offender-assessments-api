package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
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
import uk.gov.justice.digital.oasys.api.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.ReferenceDataRepository;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class ReferenceDataControllerTest {

    @LocalServerPort
    int port;

    @MockBean
    private ReferenceDataRepository referenceDataRepository;

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

        Mockito.when(referenceDataRepository.findAllByRefCategoryCodeAndEndDateIsBefore(eq("INTERVENTION"), any())).thenReturn(ControllerTestContext.interventions());
    }

    @Test
    public void canGetAllInterventions() {
        RefElement[] sentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/referencedata/INTERVENTION")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(RefElement[].class);

        assertThat(sentencePlans).hasSize(2);
    }

}