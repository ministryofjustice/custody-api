package uk.gov.justice.digital.nomis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class ReferenceDataControllerTest {

    @LocalServerPort
    int port;

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
    }

    @Test
    public void canGetAgencyLocations() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/agencyLocations")
                .then()
                .statusCode(200)
                .body("page.totalElements", greaterThan(0));

    }

    @Test
    public void agencyLocationsAreAuthorized() {
        given()
                .when()
                .get("/agencyLocations")
                .then()
                .statusCode(401);
    }

    @Test
    public void canGetAgencyInternalLocations() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/agencyInternalLocations")
                .then()
                .statusCode(200)
                .body("page.totalElements", greaterThan(0));

    }

    @Test
    public void agencyInternalLocationsAreAuthorized() {
        given()
                .when()
                .get("/agencyInternalLocations")
                .then()
                .statusCode(401);
    }

}