package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.ProgrammeProfile;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderProgrammeProfileTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OffenderProgrammeProfilesService {

    private final OffenderRepository offenderRepository;
    private final OffenderProgrammeProfileTransformer offenderProgrammeProfileTransformer;

    @Autowired
    public OffenderProgrammeProfilesService(OffenderRepository offenderRepository, OffenderProgrammeProfileTransformer offenderProgrammeProfileTransformer) {
        this.offenderRepository = offenderRepository;
        this.offenderProgrammeProfileTransformer = offenderProgrammeProfileTransformer;
    }


    public Optional<List<ProgrammeProfile>> offenderProgrammeProfilesForOffenderId(Long offenderId) {

        final Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        final Optional<List<OffenderBooking>> maybeOffenderBookings = maybeOffender.map(Offender::getOffenderBookings);

        return maybeOffenderBookings.map(bookings -> bookings
                .stream()
                .flatMap(booking -> booking.getOffenderProgramProfiles().stream())
                .map(offenderProgrammeProfileTransformer::programmeProfileOf)
                .collect(Collectors.toList()));
    }

    public Optional<List<ProgrammeProfile>> offenderProgrammeProfilesForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        if (!maybeOffender.isPresent()) {
            return Optional.empty();
        }

        Optional<OffenderBooking> maybeOffenderBooking = maybeOffender.get().getOffenderBookings()
                .stream()
                .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                .findFirst();

        return maybeOffenderBooking.map(ob -> ob.getOffenderProgramProfiles()
                .stream()
                .map(offenderProgrammeProfileTransformer::programmeProfileOf)
                .collect(Collectors.toList()));
    }
}