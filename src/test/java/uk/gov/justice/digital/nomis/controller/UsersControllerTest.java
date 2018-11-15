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
import uk.gov.justice.digital.nomis.api.UserDetail;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class UsersControllerTest {

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
        UserDetail user = given()
                .when()
                .auth().oauth2(validOauthToken)
                .get("/users/ITAG_USER")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(UserDetail.class);

        assertThat(user.getUsername()).isEqualTo("ITAG_USER");
        assertThat(user.getActiveCaseload().getId()).isEqualTo("MDI");
        assertThat(user.getActiveCaseload().getAgencyLocations().get(0).getAgencyLocationId()).isEqualTo("MDI");
    }

    @Test
    public void userDetailsAreAuthorized() {
        given()
                .when()
                .get("/users/ITAG_USER")
                .then()
                .statusCode(401);
    }

}