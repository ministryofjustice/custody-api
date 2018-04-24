package uk.gov.justice.digital.nomis.service.transformer;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Booking;
import uk.gov.justice.digital.nomis.api.Identifier;
import uk.gov.justice.digital.nomis.api.OffenderAlias;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderIdentifier;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OffenderTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public OffenderTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public List<OffenderAlias> aliasesOf(List<Offender> offenderList) {
        return Optional.ofNullable(offenderList).map(offenders -> offenders
                .stream()
                .map(offender -> OffenderAlias.builder()
                        .offenderId(offender.getOffenderId())
                        .firstName(offender.getFirstName())
                        .middleNames(combinedMiddlenamesOf(offender))
                        .surname(offender.getLastName())
                        .dateOfBirth(offender.getBirthDate().toLocalDateTime().toLocalDate())
                        .identifiers(identifiersOf(offender.getOffenderIdentifiers()))
                        .nomsId(offender.getOffenderIdDisplay())
                        .sexCode(offender.getSexCode())
                        .raceCode(offender.getRaceCode())
                        .build())
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    public List<Identifier> identifiersOf(List<OffenderIdentifier> offenderIdentifiers) {
        return Optional.ofNullable(offenderIdentifiers).map(
                identifiers -> identifiers.stream().sorted(Comparator.comparing(OffenderIdentifier::getOffenderIdSeq).reversed()).map(identifier ->
                        Identifier.builder()
                                .identifier(identifier.getIdentifier())
                                .identifierType(identifier.getIdentifierType())
                                .sequenceNumber(identifier.getOffenderIdSeq())
                                .createdDateTime(Optional.ofNullable(identifier.getCreateDatetime()).map(Timestamp::toLocalDateTime).orElse(null))
                                .build())
                        .collect(Collectors.toList())
        ).orElse(Collections.emptyList());
    }

    public List<Booking> bookingsOf(List<OffenderBooking> offenderBookings) {
        return offenderBookings.stream().map(this::bookingOf)
                .sorted(byBookingSequence())
                .collect(Collectors.toList());
    }

    public Booking bookingOf(OffenderBooking booking) {
        return Booking.builder()
                .bookingSequence(booking.getBookingSeq())
                .startDate(booking.getBookingBeginDate().toLocalDateTime().toLocalDate())
                .endDate(Optional.ofNullable(booking.getBookingEndDate()).map(end -> end.toLocalDateTime().toLocalDate()))
                .activeFlag(typesTransformer.ynToBoolean(booking.getActiveFlag()))
                .agencyLocationId(booking.getAgyLocId())
                .bookingNo(booking.getBookingNo())
                .bookingStatus(booking.getBookingStatus())
                .inOutStatus(booking.getInOutStatus())
                .livingUnitId(booking.getLivingUnitId())
                .offenderId(booking.getOffenderId())
                .offenderBookingId(booking.getOffenderBookId())
                .rootOffenderId(booking.getRootOffenderId())
                .statusReason(booking.getStatusReason())
                .caseDateTime(typesTransformer.localDateTimeOf(booking.getCaseDate(), booking.getCaseTime()))
                .build();
    }

    public String combinedMiddlenamesOf(Offender offender) {
        return middleNamesOf(offender.getMiddleName(), offender.getMiddleName2()).stream().collect(Collectors.joining(" "));
    }

    public List<String> middleNamesOf(String secondName, String thirdName) {
        Optional<String> maybeSecondName = Optional.ofNullable(secondName);
        Optional<String> maybeThirdName = Optional.ofNullable(thirdName);

        return ImmutableList.of(maybeSecondName, maybeThirdName)
                .stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
    }

    public uk.gov.justice.digital.nomis.api.Offender offenderOf(uk.gov.justice.digital.nomis.jpa.entity.Offender offender) {
        return uk.gov.justice.digital.nomis.api.Offender.builder()
                .dateOfBirth(offender.getBirthDate().toLocalDateTime().toLocalDate())
                .firstName(offender.getFirstName())
                .middleNames(combinedMiddlenamesOf(offender))
                .surname(offender.getLastName())
                .bookings(bookingsOf(offender.getOffenderBookings()))
                .identifiers(identifiersOf(offender.getOffenderIdentifiers()))
                .offenderId(offender.getOffenderId())
                .aliases(aliasesOf(offender.getOffenderAliases()))
                .nomsId(offender.getOffenderIdDisplay())
                .sexCode(offender.getSexCode())
                .raceCode(offender.getRaceCode())
                .build();
    }

    private Comparator<Booking> byBookingSequence() {
        return Comparator
                .comparingLong(Booking::getBookingSequence)
                .thenComparingLong(Booking::getOffenderBookingId);
    }
}