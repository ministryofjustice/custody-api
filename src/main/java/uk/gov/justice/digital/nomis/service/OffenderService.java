package uk.gov.justice.digital.nomis.service;

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
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffenderService {

    private final OffenderRepository offenderRepository;
    private final OffenderTransformer offenderTransformer;

    @Autowired
    public OffenderService(OffenderRepository offenderRepository, OffenderTransformer offenderTransformer) {
        this.offenderRepository = offenderRepository;
        this.offenderTransformer = offenderTransformer;
    }

    @Transactional
    public Page<Offender> getOffenders(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.Offender> rootOffendersRawPage = offenderRepository.findAllRootOffenders(pageable);

        List<uk.gov.justice.digital.nomis.jpa.entity.Offender> rootOffenders = rootOffendersRawPage.getContent();

        List<Offender> offenderList = rootOffenders.stream().map(
                offender -> Offender.builder()
                        .dateOfBirth(offender.getBirthDate().toLocalDateTime().toLocalDate())
                        .firstName(offender.getFirstName())
                        .middleNames(offenderTransformer.combinedMiddlenamesOf(offender))
                        .surname(offender.getLastName())
                        .bookings(offenderTransformer.bookingsOf(offender.getOffenderBookings()))
                        .identifiers(offenderTransformer.identifiersOf(offender.getOffenderIdentifiers()))
                        .offenderId(offender.getOffenderId())
                        .aliases(offenderTransformer.aliasesOf(offender.getOffenderAliases()))
                        .nomsId(offender.getOffenderIdDisplay())
                        .build()
        ).collect(Collectors.toList());

        return new PageImpl<>(offenderList, pageable, rootOffendersRawPage.getTotalElements());
    }

    private List<OffenderAlias> aliasesOf(List<uk.gov.justice.digital.nomis.jpa.entity.Offender> offenderList) {
        return offenderTransformer.aliasesOf(offenderList);
    }

    private List<Identifier> identifiersOf(List<OffenderIdentifier> offenderIdentifiers) {
        return offenderTransformer.identifiersOf(offenderIdentifiers);
    }

    private List<Booking> bookingsOf(List<OffenderBooking> offenderBookings) {
        return offenderTransformer.bookingsOf(offenderBookings);
    }

    private String combinedMiddlenamesOf(uk.gov.justice.digital.nomis.jpa.entity.Offender offender) {
        return offenderTransformer.combinedMiddlenamesOf(offender);
    }

    private List<String> middleNamesOf(String secondName, String thirdName) {

        return offenderTransformer.middleNamesOf(secondName, thirdName);
    }
}
