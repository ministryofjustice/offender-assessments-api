# Offender Assessments API  
  
The service provides REST access to the OASYS Oracle DB offender assessment information.  
  
The source for this service can be found on [GitHub](https://github.com/ministryofjustice/offender-assessments-api.git).  
  
  
## Continuous Integration  
https://circleci.com/gh/ministryofjustice/offender-assessments-api/  
  
## Running locally  
  
### Prerequisites  
* Java JDK 11+  
* An editor/IDE - (the service uses Lombok and so annotation processors must be [turned on within the IDE](https://www.baeldung.com/lombok-ide)).  
* Gradle  
* Docker  
* OAuth  [(running in a container)](#oauth-security)
  
#### OAuth security  
In order to run the service locally, [Nomis OAuth Service](https://github.com/ministryofjustice/nomis-oauth2-server/) is required. This can be run locally using the [docker-compose.yml](docker-compose.yml) file which will pull down the latest version.  From the command line run:
  
```
 docker-compose up 
```  
  
### Build service and run tests  
  
This service is built using Gradle. In order to build the project from the command line and run the tests, use:
```  
./gradlew clean build  
```  
The created JAR file will be named "`offender-assessment-api-<yyyy-mm-dd>.jar`", using the date that the build takes place in the format `yyyy-mm-dd`.  
  
  
### Start the application with H2 database  
  
The configuration can be changed for the api to use an in-memory H2 database by using the spring boot profile `dev`. On the command line run:
  ```  
SPRING_PROFILES_ACTIVE=dev 
java -jar build/libs/offender-assessment-api-<yyyy-mm-dd>.jar  
```  
  
### Start the application with Oracle db  
This configuration can be changed to use a VM Oracle database using the spring boot profile `oracle`.  On the command line run:
  ```  
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@<VM Oracle IP address>:1521:XYZ
SPRING_PROFILES_ACTIVE=oracle 
java -jar build/libs/offender-assessments-api-<yyyy-mm-dd>.jar  
```  
  
### Additional configuration  
The application is configurable with conventional Spring parameters.  
The Spring documentation can be found here: https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html  
  
#### Default port  
By default the application starts on port '8080'.   To override, set server.port (e.g. `SERVER_PORT=8099 java -jar build/libs/offender-assessments-api-<yyyy-mm-dd>.jar` )  
  
### Documentation  
The generated documentation for the api can be viewed at http://localhost:8080/api/swagger-ui.html  
  
### Health  
  
- `/ping`: will respond `pong` to all requests.  This should be used by dependent systems to check connectivity to   
offender assessment service, rather than calling the `/health` endpoint.  
- `/health`: provides information about the application health and its dependencies.  This should only be used  
by offender assessment service health monitoring (e.g. pager duty) and not other systems who wish to find out the   
state of offender assessment service.  
- `/info`: provides information about the version of deployed application.  
  
#### Health and info Endpoints (curl)  
  
##### Application info  
```  
curl -X GET http://localhost:8080/info  
```  
  
##### Application health  
```  
curl -X GET http://localhost:8080/health  
```  
  
##### Application ping  
```  
curl -X GET http://localhost:8080/ping  
```  
[comment]: <> (mention H2 console endpoint)

## Using the api  
  
### Authentication using OAuth
In order to make queries to the api, first a client credential access token must be generated from OAuth. Send a POST request to the [OAuth container](#oauth-security) with the client name:secret, encoded in base64 (`sentence-plan-api-client:clientsecret`)

Firstly, to encode in base64: `echo -n 'sentence-plan-api-client:clientsecret' | openssl base64` to output `c2VudGVuY2UtcGxhbi1hcGktY2xpZW50OmNsaWVudHNlY3JldA==`. 

 Then, POST the request for the access token from OAuth, using the encoded secret as authorisation:
 ```
 curl --location --request POST 'http://localhost:9090/auth/oauth/token?grant_type=client_credentials' \
 --header 'Authorization: Basic c2VudGVuY2UtcGxhbi1hcGktY2xpZW50OmNsaWVudHNlY3JldA=='
```

#### Get TBD  (WIP)
```  
curl -X GET http://localhost:8080/api/TBD/xyz -H 'Authorization: <token>'  
```


