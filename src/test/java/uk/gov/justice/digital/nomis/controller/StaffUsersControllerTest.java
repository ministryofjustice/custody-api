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
import uk.gov.justice.digital.nomis.api.Caseload;
import uk.gov.justice.digital.nomis.api.StaffUser;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class StaffUsersControllerTest {

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
    public void canGetUserDetails() {
        StaffUser staffUser = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/staffusers/ITAG_USER")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(StaffUser.class);

        assertThat(staffUser.getUsername()).isEqualTo("ITAG_USER");
        assertThat(staffUser.getStaffId()).isEqualTo(1);
        assertThat(staffUser.getFirstName()).isEqualTo("ITAG");
        assertThat(staffUser.getLastName()).isEqualTo("USER");
        assertThat(staffUser.getStatus()).isEqualTo("ACTIVE");
        assertThat(staffUser.getCaseloads().values().size()).isEqualTo(2);
        String activeCaseload = staffUser.getActiveCaseload();
        assertThat(activeCaseload).isEqualTo("MDI");
        Caseload mdiCaseload = staffUser.getCaseloads().get(activeCaseload);
        assertThat(mdiCaseload.getAgencies().size()).isEqualTo(1);
        assertThat(mdiCaseload.isCurrentActive()).isTrue();
        assertThat(mdiCaseload.getAgencies().get(0).getAgencyLocationId()).isEqualTo("MDI");
    }

    @Test
    public void canGetUserDetailsMultipleAgencies() {
        StaffUser staffUser = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/staffusers/ITAG_USER_ADM")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(StaffUser.class);

        assertThat(staffUser.getUsername()).isEqualTo("ITAG_USER_ADM");
        assertThat(staffUser.getStaffId()).isEqualTo(1);
        assertThat(staffUser.getFirstName()).isEqualTo("ITAG");
        assertThat(staffUser.getLastName()).isEqualTo("USER");
        assertThat(staffUser.getStatus()).isEqualTo("ACTIVE");
        assertThat(staffUser.getCaseloads().values().size()).isEqualTo(2);
        String activeCaseload = staffUser.getActiveCaseload();
        assertThat(activeCaseload).isNull();
        Caseload mdiCaseload = staffUser.getCaseloads().get("CADM");
        assertThat(mdiCaseload.getAgencies().size()).isEqualTo(4);
    }

    @Test
    public void canGetAllOffenders() {
        given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/staffusers")
                .then()
                .statusCode(200)
                .body("page.totalElements", greaterThan(0));
    }

    @Test
    public void userDetailsAreAuthorized() {
        given()
                .when()
                .get("/staffusers/ITAG_USER")
                .then()
                .statusCode(401);
    }

}