package uk.gov.justice.digital.nomis.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Booking;
import uk.gov.justice.digital.nomis.api.Identifier;
import uk.gov.justice.digital.nomis.api.Offender;
import uk.gov.justice.digital.nomis.api.OffenderAlias;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderIdentifier;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderIdentifierRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OffenderService {

    private final OffenderRepository offenderRepository;

    @Autowired
    public OffenderService(OffenderRepository offenderRepository) {
        this.offenderRepository = offenderRepository;
    }

    @Transactional
    public Page<Offender> getOffenders(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.Offender> rootOffendersRawPage = offenderRepository.findAllRootOffenders(pageable);

        List<uk.gov.justice.digital.nomis.jpa.entity.Offender> rootOffenders = rootOffendersRawPage.getContent();

        List<Offender> offenderList = rootOffenders.stream().map(
                offender -> Offender.builder()
                        .dateOfBirth(offender.getBirthDate().toLocalDateTime().toLocalDate())
                        .firstName(offender.getFirstName())
                        .middleNames(combinedMiddlenamesOf(offender))
                        .surname(offender.getLastName())
                        .bookings(bookingsOf(offender.getOffenderBookings()))
                        .identifiers(identifiersOf(offender.getOffenderIdentifiers()))
                        .offenderId(offender.getOffenderId())
                        .aliases(aliasesOf(offender.getOffenderAliases()))
                        .nomsId(offender.getOffenderIdDisplay())
                        .build()
        ).collect(Collectors.toList());

        return new PageImpl<>(offenderList, pageable, rootOffendersRawPage.getTotalElements());
    }

    private List<OffenderAlias> aliasesOf(List<uk.gov.justice.digital.nomis.jpa.entity.Offender> offenderList) {
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
                        .build())
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    private List<Identifier> identifiersOf(List<OffenderIdentifier> offenderIdentifiers) {
        return Optional.ofNullable(offenderIdentifiers).map(
                identifiers -> identifiers.stream().sorted(Comparator.comparing(OffenderIdentifier::getOffenderIdSeq).reversed()).map(identifier ->
                        Identifier.builder()
                                .identifier(identifier.getIdentifier())
                                .identifierType(identifier.getIdentifierType())
                                .sequenceNumber(identifier.getOffenderIdSeq())
                                .createdDateTime(identifier.getCreateDatetime().toLocalDateTime())
                                .build())
                        .collect(Collectors.toList())
        ).orElse(Collections.emptyList());
    }

    private List<Booking> bookingsOf(List<OffenderBooking> offenderBookings) {
        return offenderBookings.stream().map(
                booking -> Booking.builder()
                        .bookingSequence(booking.getBookingSeq())
                        .startDate(booking.getBookingBeginDate().toLocalDateTime().toLocalDate())
                        .endDate(Optional.ofNullable(booking.getBookingEndDate()).map(end -> end.toLocalDateTime().toLocalDate()))
                        .activeFlag(booking.getActiveFlag())
                        .agencyLocationId(booking.getAgyLocId())
                        .bookingNo(booking.getBookingNo())
                        .bookingStatus(booking.getBookingStatus())
                        .inOutStatus(booking.getInOutStatus())
                        .livingUnitId(booking.getLivingUnitId())
                        .offenderId(booking.getOffenderId())
                        .offenderBookingId(booking.getOffenderBookId())
                        .rootOffenderId(booking.getRootOffenderId())
                        .statusReason(booking.getStatusReason())
                        .build())
                .collect(Collectors.toList());
    }

    private String combinedMiddlenamesOf(uk.gov.justice.digital.nomis.jpa.entity.Offender offender) {
        return middleNamesOf(offender.getMiddleName(), offender.getMiddleName2()).stream().collect(Collectors.joining(" "));
    }

    private List<String> middleNamesOf(String secondName, String thirdName) {
        Optional<String> maybeSecondName = Optional.ofNullable(secondName);
        Optional<String> maybeThirdName = Optional.ofNullable(thirdName);

        return ImmutableList.of(maybeSecondName, maybeThirdName)
                .stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toList());
    }
}
