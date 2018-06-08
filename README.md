# Offender Assesments API

The service provides REST access to the OASYS Oracle DB offender assessment information.

## Continuous Integration
https://circleci.com/gh/noms-digital-studio/offender-assessment-api

## Gradle commands

### Build and run tests
```
./gradlew build
```

### Assessmble the app
```
./gradlew assemble
```

This makes the JAR executable by including a manifest. 

### Start the application default profile
Without additional configuration this mode uses an in memory H2 database.

```
java -jar build/libs/offender-assessment-api.jar
```

### Start the application with Delius Oracle db

set SPRING_PROFILES_ACTIVE=oracle
```
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@<VM Oracle IP address>:1521:XYZ SPRING_PROFILES_ACTIVE=oracle java -jar build/libs/offender-assessments-api.jar
```

### Additional configuration
The application is conigured with conventional Spring parameters.
The Spring documentation can be found here:

https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

### Default port
Starts the application on port '8080'.
To override, set server.port (eg SERVER_PORT=8099 java -jar etc etc)

## Documentation
http://localhost:8080/api/swagger-ui.html

## Endpoints curl examples

### Get TBD
```
curl -X GET http://localhost:8080/api/TBD/xyz -H 'Authorization: <token>'
```

### Application info
```
curl -X GET http://localhost:8080/api/info
```

### Application health
```
curl -X GET http://localhost:8080/api/health
```


