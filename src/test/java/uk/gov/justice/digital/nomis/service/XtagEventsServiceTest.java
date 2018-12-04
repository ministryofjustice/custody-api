package uk.gov.justice.digital.nomis.service;


import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class XtagEventsServiceTest {

    public static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    public void appliesFudgeWhenNotCurrentlyInDaylightSavingsTime() {

        Instant aWinterDate = ZonedDateTime.of(2020,1,1,0,0,0,0,ZoneId.of("Europe/London")).toInstant();

        final LocalDateTime actual = XtagEventsService.asUtcPlusOne(NOW, aWinterDate);

        assertThat(actual).isEqualTo(NOW.plusHours(1L));
    }

    @Test
    public void doesNotApplyFudgeWhenCurrentlyInDaylightSavingsTime() {

        Instant aSummerDate = ZonedDateTime.of(2020,7,1,0,0,0,0,ZoneId.of("Europe/London")).toInstant();

        final LocalDateTime actual = XtagEventsService.asUtcPlusOne(NOW, aSummerDate);

        assertThat(actual).isEqualTo(NOW);
    }

}