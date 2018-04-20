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
import uk.gov.justice.digital.nomis.api.ExternalMovement;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class MovementsControllerTest {

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
    public void canGetAllMovements() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/movements")
                .then()
                .statusCode(200)
                .body("page.totalElements", greaterThan(0));
    }

    @Test
    public void canGetOffenderMovements() {
        ExternalMovement[] movements = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/offenderId/-1017/movements")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ExternalMovement[].class);

        assertThat(movements.length).isGreaterThan(0);
    }

    @Test
    public void movementsAreAuthorized() {
        given()
                .when()
                .get("/movements")
                .then()
                .statusCode(401);
    }

    @Test
    public void offenderMovementsAreAuthorized() {
        given()
                .when()
                .get("/offenders/offenderId/-1017/movements")
                .then()
                .statusCode(401);
    }

}