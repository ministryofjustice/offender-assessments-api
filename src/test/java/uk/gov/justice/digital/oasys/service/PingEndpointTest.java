package uk.gov.justice.digital.oasys.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.gov.justice.digital.oasys.controller.PingEndpoint;

public class PingEndpointTest {
    @Test
    public void ping() {
        Assertions.assertThat(new PingEndpoint().ping()).isEqualTo("pong");
    }
}
