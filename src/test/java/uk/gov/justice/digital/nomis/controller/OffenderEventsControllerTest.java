package uk.gov.justice.digital.nomis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.justice.digital.nomis.api.OffenderEvent;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;
import uk.gov.justice.digital.nomis.service.XtagEventsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class OffenderEventsControllerTest {

    @MockBean
    public XtagEventsService xtagEventsService;
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
    public void canAccessBothOffenderAndXtagEvents() {
        LocalDateTime from = LocalDateTime.of(2018,10,29,0,0);
        LocalDateTime to = from.plusDays(1L);

        OffenderEventsFilter filter = OffenderEventsFilter.builder().from(from).to(to).build();
        Mockito.when(xtagEventsService.findAll(ArgumentMatchers.eq(filter))).thenReturn(someXtagEvents(from));

        final OffenderEvent[] offenderEvents = given()
                .when()
                .auth().oauth2(validOauthToken)
                .queryParam("from", from.toString())
                .queryParam("to", to.toString())
                .get("/events")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderEvent[].class);

        final ImmutableList<OffenderEvent> events = ImmutableList.<OffenderEvent>builder().add(offenderEvents).build();


        assertThat(events).extracting("nomisEventType").contains("BOOK_UPD_OASYS");
        assertThat(events).extracting("eventType").contains("KA-KS");
        assertThat(events).extracting("offenderId").contains(-1001L, -1002L);

    }

    @Test
    public void canFilterOffenderEvents() {
        LocalDateTime from = LocalDateTime.of(2018,10,29,0,0);
        LocalDateTime to = from.plusDays(1L);

        OffenderEventsFilter filter = OffenderEventsFilter.builder().from(from).to(to).build();
        Mockito.when(xtagEventsService.findAll(ArgumentMatchers.eq(filter))).thenReturn(someXtagEvents(from));

        final OffenderEvent[] offenderEvents = given()
                .when()
                .auth().oauth2(validOauthToken)
                .queryParam("from", from.toString())
                .queryParam("to", to.toString())
                .queryParam("type", "KA-KS")
                .get("/events")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderEvent[].class);

        final ImmutableList<OffenderEvent> events = ImmutableList.<OffenderEvent>builder().add(offenderEvents).build();
        assertThat(events).extracting("eventType").containsOnly("KA-KS");

    }

    @Test
    public void canAccessOffenderEventsForSpecificOffender() {
        LocalDateTime from = LocalDateTime.of(2018,10,29,0,0);
        LocalDateTime to = from.plusDays(1L);

        OffenderEventsFilter filter = OffenderEventsFilter.builder().from(from).to(to).offenderId(Optional.of(-1001L)).build();
        Mockito.when(xtagEventsService.findAll(ArgumentMatchers.eq(filter))).thenReturn(someXtagEvents(from));

        final OffenderEvent[] offenderEvents = given()
                .when()
                .auth().oauth2(validOauthToken)
                .queryParam("from", from.toString())
                .queryParam("to", to.toString())
                .get("/offenders/offenderId/-1001/events")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(OffenderEvent[].class);

        final ImmutableList<OffenderEvent> events = ImmutableList.<OffenderEvent>builder().add(offenderEvents).build();

        assertThat(events).extracting("rootOffenderId").containsOnly(-1001L);
        assertThat(events).extracting("nomisEventType").contains("BOOK_UPD_OASYS");
        assertThat(events).extracting("eventType").contains("KA-KS");


    }

    private List<OffenderEvent> someXtagEvents(LocalDateTime now) {
        return ImmutableList.of(
                OffenderEvent.builder()
                        .nomisEventType("BOOK_UPD_OASYS")
                        .eventType("BOOKING_NUMBER-CHANGED")
                        .eventDatetime(now.plusSeconds(1L))
                        .offenderId(-1001L)
                        .rootOffenderId(-1001L)
                        .build(),
                OffenderEvent.builder()
                        .nomisEventType("OFF_RECEP_OASYS")
                        .eventType("OFFENDER_MOVEMENT-RECEPTION")
                        .eventDatetime(now.plusSeconds(2L))
                        .offenderId(-1001L)
                        .rootOffenderId(-1001L)
                        .build(),
                OffenderEvent.builder()
                        .nomisEventType("OFF_DISCH_OASYS")
                        .eventType("OFFENDER_MOVEMENT-DISCHARGE")
                        .eventDatetime(now.plusSeconds(3L))
                        .offenderId(-1001L)
                        .rootOffenderId(-1001L)
                        .build(),
                OffenderEvent.builder()
                        .nomisEventType("OFF_UPD_OASYS")
                        .eventType("OFFENDER_DETAILS-CHANGED")
                        .eventDatetime(now.plusSeconds(4L))
                        .offenderId(-1001L)
                        .rootOffenderId(-1001L)
                        .build(),
                OffenderEvent.builder()
                        .nomisEventType("OFF_UPD_OASYS")
                        .eventType("OFFENDER_DETAILS-CHANGED")
                        .eventDatetime(now.plusSeconds(5L))
                        .offenderId(-1002L)
                        .rootOffenderId(-1002L)
                        .build());
    }
}