FROM openjdk:11-slim
MAINTAINER Mike Jackson <michael.jackson@digital.justice.gov.uk>

COPY build/libs/offender-assessments-api*.jar /root/offender-assessments-api.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/root/offender-assessments-api.jar"]
