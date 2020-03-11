package uk.gov.justice.digital.oasys.config;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.boot.dependencies.apachecommons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Application insights now controlled by the spring-boot-starter dependency.  However when the key is not specified
 * we don't get a telemetry bean and application won't start.  Therefore need this backup configuration.
 */
@Configuration
@Slf4j
public class ApplicationInsightsConfiguration {


    @Bean
    @Conditional(AppInsightKeyAbsentCondition.class)
    public TelemetryClient telemetryClient() {
        return new TelemetryClient();
    }

    public static class AppInsightKeyAbsentCondition implements Condition {

        @Override
        public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
            final var telemetryKey = context.getEnvironment().getProperty("appinsights.instrumentationkey");
            if(com.microsoft.applicationinsights.boot.dependencies.apachecommons.lang3.StringUtils.isBlank(telemetryKey)) {
                log.warn("Application Insights Implementation Key is blank");
            }
            return StringUtils.isBlank(telemetryKey);
        }
    }
}
