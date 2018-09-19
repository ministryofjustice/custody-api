package uk.gov.justice.digital.nomis.jpa.entity;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OffenderSentenceCalculationTest {

    @Test
    public void greaterOfBehavesAppropriately() {
        final OffenderSentCalculation offenderSentenceCalculation = OffenderSentCalculation.builder().build();

        final Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        final Timestamp later = Timestamp.valueOf(LocalDateTime.now().plusDays(1L));

        assertThat(offenderSentenceCalculation.greaterOf(Optional.empty(), Optional.empty())).isNull();
        assertThat(offenderSentenceCalculation.greaterOf(Optional.of(now), Optional.empty())).isEqualTo(now);
        assertThat(offenderSentenceCalculation.greaterOf(Optional.empty(), Optional.of(now))).isEqualTo(now);
        assertThat(offenderSentenceCalculation.greaterOf(Optional.of(now), Optional.of(later))).isEqualTo(later);
        assertThat(offenderSentenceCalculation.greaterOf(Optional.of(later), Optional.of(now))).isEqualTo(later);
    }

    @Test
    public void firstNonNullDateOBehavesAppropriately() {
        final OffenderSentCalculation offenderSentenceCalculation = OffenderSentCalculation.builder().build();

        final Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        final Timestamp later = Timestamp.valueOf(LocalDateTime.now().plusDays(1L));

        assertThat(offenderSentenceCalculation.firstNonNullDateOf(null, null)).isEqualTo(Optional.empty());
        assertThat(offenderSentenceCalculation.firstNonNullDateOf(null, now)).isEqualTo(Optional.of(now));
        assertThat(offenderSentenceCalculation.firstNonNullDateOf(now, null)).isEqualTo(Optional.of(now));
        assertThat(offenderSentenceCalculation.firstNonNullDateOf(later, now)).isEqualTo(Optional.of(later));
        assertThat(offenderSentenceCalculation.firstNonNullDateOf(now, later)).isEqualTo(Optional.of(now));
    }

    @Test
    public void midTermDateIsCorrect() {

        final Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        final Timestamp later = Timestamp.valueOf(LocalDateTime.now().plusDays(1L));

        OffenderSentCalculation offenderSentenceCalculation = OffenderSentCalculation.builder()
                .mtdCalculatedDate(now)
                .build();

        assertThat(offenderSentenceCalculation.calculateMidTermDate()).isEqualTo(Optional.of(now));

        offenderSentenceCalculation = OffenderSentCalculation
                .builder()
                .mtdCalculatedDate(now)
                .mtdOverridedDate(later)
                .build();

        assertThat(offenderSentenceCalculation.calculateMidTermDate()).isEqualTo(Optional.of(later));
    }

    @Test
    public void confirmedReleaseDateIsCorrect() {

        final Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        final Timestamp later = Timestamp.valueOf(LocalDateTime.now().plusDays(1L));

        final OffenderBooking offenderBooking = OffenderBooking.builder()
                .offenderReleaseDetails(OffenderReleaseDetails
                        .builder()
                        .autoReleaseDate(now)
                        .build())
                .build();

        final OffenderSentCalculation offenderSentence = OffenderSentCalculation.builder()
                .offenderBooking(offenderBooking)
                .build();

        assertThat(offenderSentence.calculateConfirmedReleaseDate()).isEqualTo(Optional.of(now));

        offenderBooking.setOffenderReleaseDetails(OffenderReleaseDetails
                .builder()
                .autoReleaseDate(now)
                .releaseDate(later)
                .build());

        assertThat(offenderSentence.calculateConfirmedReleaseDate()).isEqualTo(Optional.of(later));
    }


}