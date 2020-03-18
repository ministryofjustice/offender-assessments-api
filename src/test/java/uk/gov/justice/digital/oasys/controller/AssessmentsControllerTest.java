package uk.gov.justice.digital.oasys.controller;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;

@Ignore
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class AssessmentsControllerTest {

/*    @LocalServerPort
    int port;

    @MockBean
    private OffenderService offenderService;

    @MockBean
    private AssessmentRepository assessmentRepository;

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

        ControllerServiceTestContext.setup(offenderService);

        Mockito.when(assessmentRepository.findById(eq(0L))).thenReturn(Optional.ofNullable(ControllerTestContext.anOasysSet(0L)));
    }


    @After
    public void tearDown() {
    }

    @Test
    public void endpointsAreAuthorised() {
        given()
                .when()
                .get("/xyz")
                .then()
                .statusCode(401);
    }

    @Test
    public void healthEndpointIsUnauthorised() {
        given()
                .when()
                .get("/health")
                .then()
                .statusCode(200);
    }

    @Test
    public void canGetAssessmentsForOffenderPk() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments/summary", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderPk() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments/latest", 1L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }

    @Test
    public void getAssessmentForUnknownOffenderPkGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments/summary", 2L)
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderCRN() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/assessments/summary", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderCrn() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/assessments/latest", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderCRNGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/crn/{0}/assessments/summary", "crn2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderPNC() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/assessments/summary", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderPnc() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/assessments/latest", "pnc1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderPNCGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/pnc/{0}/assessments/summary", "pnc2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderNomisId() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/assessments/summary", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderNmisId() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/assessments/latest", "nomisId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderNomisIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/nomisId/{0}/assessments/summary", "nomisId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderBookingId() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/assessments/summary", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).hasSize(2);
    }

    @Test
    public void canGetLatestAssessmentForOffenderBookingId() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/assessments/latest", "bookingId1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(2L);
    }


    @Test
    public void getAssessmentForUnknownOffenderBookingIdGivesNotFound() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/bookingId/{0}/assessments/summary", "bookingId2")
                .then()
                .statusCode(404);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFilteredByAssessmentType() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("assessmentType", "oasys")
                .get("/offenders/crn/{0}/assessments/summary", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).extracting("assessmentType").containsOnly("oasys");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByHistoricStatus() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("historicStatus", "CURRENT")
                .get("/offenders/crn/{0}/assessments/summary", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).extracting("historicStatus").containsOnly("CURRENT");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredByAssessmentStatus() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("assessmentStatus", "COMPLETE")
                .get("/offenders/crn/{0}/assessments/summary", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).extracting("assessmentStatus").containsOnly("COMPLETE");
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredBVoided() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("voided", "true")
                .get("/offenders/crn/{0}/assessments/summary", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).extracting("voided").containsOnly(true);
    }

    @Test
    public void canGetAssessmentsForOffenderCRNFlteredBNotVoided() {
        AssessmentSummary[] assessments = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("voided", "false")
                .get("/offenders/crn/{0}/assessments/summary", "crn1")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentSummary[].class);

        assertThat(assessments).extracting("voided").containsOnly(false);
    }

    @Test
    public void canLookupAssessmentByOasysSetPk() {
        AssessmentDto assessment = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/assessments/oasysSetId/{0}", 0L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentDto.class);

        assertThat(assessment).extracting("oasysSetId").isEqualTo(0L);

    }

    @Test
    public void canGetLatestAsessementNeedsForOffenderPk() {
        AssessmentNeedDto[] needs = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/oasysOffenderId/{0}/assessments/latest/needs", 3L)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(AssessmentNeedDto[].class);

        assertThat(needs).hasSize(1);
        assertThat(needs).extracting("name").containsExactly("Emotional Wellbeing");
    }*/

}