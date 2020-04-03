package uk.gov.justice.digital.oasys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uk.gov.justice.digital.oasys.util.MockOAuthServer;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    public static MockOAuthServer mockOAuthServer = new MockOAuthServer();

    @LocalServerPort
    int port;

    @Autowired
    @Qualifier("globalObjectMapper")
    ObjectMapper objectMapper;

    @BeforeAll
    public static void startMocks() {
        mockOAuthServer.start();
        mockOAuthServer.stubJwksSet();
    }

    @AfterAll
    public static void stopMocks() {
        mockOAuthServer.stop();
    }
}
