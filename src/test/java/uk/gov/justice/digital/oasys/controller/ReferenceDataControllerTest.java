package uk.gov.justice.digital.oasys.controller;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.gov.justice.digital.oasys.api.RefElementDto;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;
import uk.gov.justice.digital.oasys.jpa.repository.ReferenceDataRepository;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class ReferenceDataControllerTest extends IntegrationTest {

    @MockBean
    private ReferenceDataRepository referenceDataRepository;

    @Value("${sample.token}")
    private String validOauthToken;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (aClass, s) -> objectMapper
        ));

        Mockito.when(referenceDataRepository.findAllByRefCategoryCodeAndEndDateIsBefore(eq("INTERVENTION"), any())).thenReturn(interventions());
    }

    @Test
    public void canGetAllInterventions() {
        RefElementDto[] sentencePlans = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/referencedata/INTERVENTION")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(RefElementDto[].class);

        assertThat(sentencePlans).hasSize(2);
    }

    public List<RefElement> interventions() {
        return List.of(RefElement.builder()
                        .refElementCode("INV1")
                        .refElementDesc("Intervention 1")
                        .refElementShortDesc("Inv 1").build(),
                RefElement.builder()
                        .refElementCode("INV1")
                        .refElementDesc("Intervention 1")
                        .refElementShortDesc("Inv 1").build());

    }

}