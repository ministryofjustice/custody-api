package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.OffenderImprisonmentStatus;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderImprisonStatusesRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.ImprisonStatusTransformer;
import uk.gov.justice.digital.nomis.service.transformer.TypesTransformer;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImprisonStatusService {

    private final ImprisonStatusTransformer imprisonStatusTransformer;
    private final OffenderImprisonStatusesRepository offenderImprisonStatusesRepository;
    private final OffenderRepository offenderRepository;

    private final TypesTransformer typesTransformer;

    @Autowired
    public ImprisonStatusService(ImprisonStatusTransformer imprisonStatusTransformer, OffenderImprisonStatusesRepository offenderImprisonStatusesRepository, OffenderRepository offenderRepository, TypesTransformer typesTransformer) {
        this.imprisonStatusTransformer = imprisonStatusTransformer;
        this.offenderImprisonStatusesRepository = offenderImprisonStatusesRepository;
        this.offenderRepository = offenderRepository;
        this.typesTransformer = typesTransformer;
    }


    @Transactional
    public Page<OffenderImprisonmentStatus> getOffenderImprisonStatuses(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus> rawImprisonStatusesPage = offenderImprisonStatusesRepository.findAll(pageable);

        List<OffenderImprisonmentStatus> offenderImprisonmentStatuses = rawImprisonStatusesPage.getContent()
                .stream()
                .sorted(byEffectiveStatus())
                .map(imprisonStatusTransformer::offenderImprisonStatusOf)
                .collect(Collectors.toList());

        return new PageImpl<>(offenderImprisonmentStatuses, pageable, rawImprisonStatusesPage.getTotalElements());
    }

    @Transactional
    public Optional<List<OffenderImprisonmentStatus>> offenderImprisonStatusForOffenderId(Long offenderId) {

        Optional<Stream<OffenderImprisonStatus>> maybeImprisonStatuses = Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderBookings()
                        .stream()
                        .map(OffenderBooking::getOffenderImprisonStatuses)
                        .flatMap(Collection::stream));

        return maybeImprisonStatuses.map(imprisonStatuses -> imprisonStatuses
                .sorted(byEffectiveStatus())
                .map(imprisonStatusTransformer::offenderImprisonStatusOf)
                .collect(Collectors.toList()));
    }

    public Optional<List<OffenderImprisonmentStatus>> offenderImprisonStatusForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        if (!maybeOffender.isPresent()) {
            return Optional.empty();
        }

        Optional<OffenderBooking> maybeOffenderBooking = maybeOffender.get().getOffenderBookings()
                .stream()
                .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                .findFirst();

        return maybeOffenderBooking.map(ob -> ob.getOffenderImprisonStatuses()
                .stream()
                .sorted(byEffectiveStatus())
                .map(imprisonStatusTransformer::offenderImprisonStatusOf)
                .collect(Collectors.toList()));
    }

    private Comparator<OffenderImprisonStatus> byEffectiveStatus() {
        return Comparator.comparing(OffenderImprisonStatus::getLatestStatus, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(this::getEffectiveDateTime)
                .thenComparing(OffenderImprisonStatus::getImprisonStatusSeq)
                .reversed();
    }

    private LocalDateTime getEffectiveDateTime(OffenderImprisonStatus ois) {
        return typesTransformer.localDateTimeOf(ois.getEffectiveDate(), ois.getEffectiveTime());
    }

}
