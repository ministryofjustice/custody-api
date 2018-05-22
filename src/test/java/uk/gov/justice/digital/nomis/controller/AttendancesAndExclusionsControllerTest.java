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
import uk.gov.justice.digital.nomis.api.CourseAttendance;
import uk.gov.justice.digital.nomis.api.Exclusion;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class AttendancesAndExclusionsControllerTest {

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
    public void canGetOffenderCourseAttendances() {
        CourseAttendance[] courseAttendances = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/offenderId/-1001/courseAttendances")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CourseAttendance[].class);

        assertThat(courseAttendances.length).isGreaterThan(0);
    }


    @Test
    public void canGetOffenderCourseAttendancesForBooking() {
        CourseAttendance[] courseAttendances = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -1)
                .get("/offenders/offenderId/-1001/courseAttendances")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CourseAttendance[].class);

        assertThat(courseAttendances.length).isGreaterThan(0);
    }

    @Test
    public void noSuchOffenderForCOurseAttendancesGives404() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -1)
                .get("/offenders/offenderId/-6666/courseAttendances")
                .then()
                .statusCode(404);
    }

    @Test
    public void noSuchBookingForCourseAttendancesGives404() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -155555)
                .get("/offenders/offenderId/-1001/courseAttendances")
                .then()
                .statusCode(404);
    }

    @Test
    public void offenderCourseAttendancesAreAuthorized() {
        given()
                .when()
                .get("/offenders/offenderId/-1001/courseAttendances")
                .then()
                .statusCode(401);
    }

    @Test
    public void canGetOffenderCourseExclusions() {
        Exclusion[] exclusions = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/offenders/offenderId/-1004/courseExclusions")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Exclusion[].class);

        assertThat(exclusions.length).isGreaterThan(0);
    }


    @Test
    public void canGetOffenderCourseExclusionsForBooking() {
        Exclusion[] exclusions = given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -4)
                .get("/offenders/offenderId/-1004/courseExclusions")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Exclusion[].class);

        assertThat(exclusions.length).isGreaterThan(0);
    }

    @Test
    public void noSuchOffenderForExclusionsGives404() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -1)
                .get("/offenders/offenderId/-6666/courseExclusions")
                .then()
                .statusCode(404);
    }

    @Test
    public void noSuchBookingForExclusionsGives404() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .param("bookingId", -155555)
                .get("/offenders/offenderId/-1004/courseExclusions")
                .then()
                .statusCode(404);
    }

    @Test
    public void offenderCourseExclusionsAreAuthorized() {
        given()
                .when()
                .get("/offenders/offenderId/-1001/courseExclusions")
                .then()
                .statusCode(401);
    }

}