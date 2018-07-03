package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.Alert;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAlert;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AlertsTransformer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertsService {

    private static final Comparator<OffenderAlert> ALERTS_BY = Comparator
            .comparing(OffenderAlert::getOffenderBookId)
            .thenComparing(OffenderAlert::getAlertSeq)
            .reversed();

    private final OffenderRepository offenderRepository;
    private final AlertsTransformer alertsTransformer;

    @Autowired
    public AlertsService(OffenderRepository offenderRepository, AlertsTransformer alertsTransformer) {
        this.offenderRepository = offenderRepository;
        this.alertsTransformer = alertsTransformer;
    }

    public Optional<List<Alert>> offenderAlertsForOffenderId(Long offenderId, Optional<String> maybeAlertCode, Optional<String> maybeAlertStatus, Optional<String> maybeAlertType) {

        final Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        final Optional<List<OffenderBooking>> maybeOffenderBookings = maybeOffender.map(Offender::getOffenderBookings);

        return maybeOffenderBookings.map(bookings -> bookings
                .stream()
                .flatMap(booking -> booking.getOffenderAlerts().stream())
                .sorted(ALERTS_BY)
                .filter(alert -> shouldInclude(alert, maybeAlertCode, maybeAlertStatus, maybeAlertType))
                .map(alertsTransformer::alertOf)
                .collect(Collectors.toList()));
    }

    private boolean shouldInclude(OffenderAlert offenderAlert, Optional<String> maybeAlertCode, Optional<String> maybeAlertStatus, Optional<String> maybeAlertType) {
        return maybeAlertCode.map(
                alertCode -> alertCode.equals(offenderAlert.getAlertCode())
        ).orElse(true)

                && maybeAlertStatus.map(
                alertStatus -> alertStatus.equals(offenderAlert.getAlertStatus())
        ).orElse(true)

                && maybeAlertType.map(
                alertType -> alertType.equals(offenderAlert.getAlertType())

        ).orElse(true);
    }

    public Optional<List<Alert>> offenderAlertsForOffenderIdAndBookingId(Long offenderId, Long bookingId, Optional<String> maybeAlertCode, Optional<String> maybeAlertStatus, Optional<String> maybeAlertType) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        return maybeOffender.flatMap(
                offender -> offender.getOffenderBookings()
                        .stream()
                        .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                        .findFirst())
                .map(ob -> ob.getOffenderAlerts()
                        .stream()
                        .sorted(ALERTS_BY)
                        .filter(alert -> shouldInclude(alert, maybeAlertCode, maybeAlertStatus, maybeAlertType))
                        .map(alertsTransformer::alertOf)
                        .collect(Collectors.toList()));
    }

}
