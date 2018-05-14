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
import uk.gov.justice.digital.nomis.api.HealthProblem;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class ReleaseDetailsControllerTest {

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
    public void canGetAllReleaseDetails() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/releaseDetails")
                .then()
                .statusCode(200)
                .body("page.totalElements", greaterThan(0));
    }

    @Test
    public void canGetOffenderReleaseDetails() {
        HealthProblem[] healthProblems = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/offenderId/-1001/releaseDetails")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(HealthProblem[].class);

        assertThat(healthProblems.length).isGreaterThan(0);
    }

    @Test
    public void releaseDetailsAreAuthorized() {
        given()
                .when()
                .get("/releaseDetails")
                .then()
                .statusCode(401);
    }

    @Test
    public void offenderReleaseDetails() {
        given()
                .when()
                .get("/offenders/offenderId/-1001/releaseDetails")
                .then()
                .statusCode(401);
    }

}