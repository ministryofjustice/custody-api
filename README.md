# New Nomis API
New Tech Nomis: Offender API.

The service provides REST access to the Nomis Oracle DB offender information.

## Continuous Integration
https://circleci.com/gh/noms-digital-studio/new-nomis-api

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
Without additional configuration this mode uses an in memory H2 (empty) database

```
java -jar build/libs/new-nomis-api.jar
```

### Start the application with Nomis Oracle db
```
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@<VM Oracle IP address>:1521:DNDA java -jar build/libs/new-nomis-api.jar
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

### List offenders details
```
curl -X GET http://localhost:8080/offenders'
```

### Application info
```
curl -X GET http://localhost:8080/info
```

### Application health
```
curl -X GET http://localhost:8080/health
```


