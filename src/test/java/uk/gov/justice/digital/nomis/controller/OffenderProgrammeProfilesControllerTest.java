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
import uk.gov.justice.digital.nomis.api.ProgrammeProfile;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class OffenderProgrammeProfilesControllerTest {

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
    public void canGetOffenderProgrammeProfiles() {
        ProgrammeProfile[] programmeProfiles = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/offenderId/-1001/programmeProfiles")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ProgrammeProfile[].class);

        assertThat(programmeProfiles.length).isEqualTo(4);
    }


    @Test
    public void canGetOffenderProgrammeProfilesForBooking() {
        ProgrammeProfile[] programmeProfiles = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -1)
                .get("/offenders/offenderId/-1001/programmeProfiles")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ProgrammeProfile[].class);

        assertThat(programmeProfiles.length).isEqualTo(4);
    }

    @Test
    public void noSuchOffenderGives404() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -1)
                .get("/offenders/offenderId/-6666/programmeProfiles")
                .then()
                .statusCode(404);
    }

    @Test
    public void noSuchBookingGives404() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -155555)
                .get("/offenders/offenderId/-1001/programmeProfiles")
                .then()
                .statusCode(404);
    }

    @Test
    public void offenderProgrammeProfilesAreAuthorized() {
        given()
                .when()
                .get("/offenders/offenderId/-1001/programmeProfiles")
                .then()
                .statusCode(401);
    }

}