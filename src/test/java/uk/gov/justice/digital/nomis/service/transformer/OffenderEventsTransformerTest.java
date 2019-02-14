package uk.gov.justice.digital.nomis.service.transformer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import uk.gov.justice.digital.nomis.CustodyApiApplication;
import uk.gov.justice.digital.nomis.xtag.Xtag;
import uk.gov.justice.digital.nomis.xtag.XtagContent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class OffenderEventsTransformerTest {

    @Test
    public void canDeserializeIntoXtagContent() {

        final ObjectMapper objectMapper = new CustodyApiApplication().objectMapper();

        OffenderEventsTransformer transformer = new OffenderEventsTransformer(mock(TypesTransformer.class), objectMapper);

        assertThat(transformer.xtagContentOf(ImmutableMap.of("x", "y"))).isNotNull();
    }

    @Test
    public void xtagEnqueueTimestampIsSeasonallyAdjustedIntoDaylightSavings() {
        LocalDateTime lastSecondOfWinter = LocalDateTime.of(2019, 3, 31, 00, 59, 59);
        LocalDateTime firstSecondOfSummer = lastSecondOfWinter.plusSeconds(1L);
        assertThat(OffenderEventsTransformer.xtagFudgedTimestampOf(lastSecondOfWinter)).isEqualTo(lastSecondOfWinter.minusHours(1L));
        assertThat(OffenderEventsTransformer.xtagFudgedTimestampOf(firstSecondOfSummer)).isEqualTo(firstSecondOfSummer);
    }

    @Test
    public void xtagEnqueueTimestampIsSeasonallyAdjustedIntoUTC() {
        LocalDateTime lastSecondOfSummer = LocalDateTime.of(2019, 10, 27, 01, 59, 59);
        LocalDateTime firstSecondOfWinter = lastSecondOfSummer.plusSeconds(1L);
        assertThat(OffenderEventsTransformer.xtagFudgedTimestampOf(lastSecondOfSummer)).isEqualTo(lastSecondOfSummer);
        assertThat(OffenderEventsTransformer.xtagFudgedTimestampOf(firstSecondOfWinter)).isEqualTo(firstSecondOfWinter.minusHours(1L));
    }

    @Test
    public void externalMovementDescriptorBehavesAppropriately() {
        assertThat(OffenderEventsTransformer.externalMovementEventOf(Xtag.builder().content(XtagContent.builder().p_record_deleted("Y").build()).build())).isEqualTo("EXTERNAL_MOVEMENT_RECORD-DELETED");
        assertThat(OffenderEventsTransformer.externalMovementEventOf(Xtag.builder().content(XtagContent.builder().p_record_deleted("N").build()).build())).isEqualTo("EXTERNAL_MOVEMENT_RECORD-INSERTED");
        assertThat(OffenderEventsTransformer.externalMovementEventOf(Xtag.builder().content(XtagContent.builder().p_record_deleted(UUID.randomUUID().toString()).build()).build())).isEqualTo("EXTERNAL_MOVEMENT_RECORD-UPDATED");
        assertThat(OffenderEventsTransformer.externalMovementEventOf(Xtag.builder().content(XtagContent.builder().build()).build())).isEqualTo("EXTERNAL_MOVEMENT_RECORD-UPDATED");
    }

    @Test
    public void localDateOfBehavesAppropriately() {
        assertThat(OffenderEventsTransformer.localDateOf("2019-02-14 10:11:12")).isEqualTo(LocalDate.of(2019, 02, 14));
        assertThat(OffenderEventsTransformer.localDateOf(null)).isNull();
        assertThat(OffenderEventsTransformer.localDateOf("Some rubbish")).isNull();
    }

    @Test
    public void localDateTimeOfBehavesAppropriately() {
        assertThat(OffenderEventsTransformer.localDateTimeOf("2019-02-14 10:11:12")).isEqualTo(LocalDateTime.of(2019, 02, 14, 10, 11, 12));
        assertThat(OffenderEventsTransformer.localDateOf(null)).isNull();
        assertThat(OffenderEventsTransformer.localDateOf("Some rubbish")).isNull();
    }


    @Test
    public void localDateTimeOfDateAndTimeBehavesAppropriately() {
        assertThat(OffenderEventsTransformer.localDateTimeOf("2019-02-14 00:00:00","1970-01-01 10:11:12")).isEqualTo(LocalDateTime.of(2019, 02, 14, 10, 11, 12));
        assertThat(OffenderEventsTransformer.localDateTimeOf(null, "1970-01-01 10:11:12")).isNull();
        assertThat(OffenderEventsTransformer.localDateTimeOf(null, "Some rubbish")).isNull();
        assertThat(OffenderEventsTransformer.localDateTimeOf(null, null)).isNull();
        assertThat(OffenderEventsTransformer.localDateTimeOf( "2019-02-14 00:00:00", "Some rubbish")).isEqualTo(LocalDateTime.of(2019, 02, 14, 00, 00, 00));
        assertThat(OffenderEventsTransformer.localDateTimeOf( "2019-02-14 00:00:00", null)).isEqualTo(LocalDateTime.of(2019, 02, 14, 00, 00, 00));
    }

}