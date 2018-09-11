package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.ProgrammeProfile;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderProgramProfilesFilter;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderProgramProfilesRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderProgrammeProfileTransformer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OffenderProgrammeProfilesService {

    private final OffenderRepository offenderRepository;
    private final OffenderProgrammeProfileTransformer offenderProgrammeProfileTransformer;
    private final OffenderProgramProfilesRepository offenderProgramProfilesRepository;

    @Autowired
    public OffenderProgrammeProfilesService(OffenderRepository offenderRepository, OffenderProgrammeProfileTransformer offenderProgrammeProfileTransformer, OffenderProgramProfilesRepository offenderProgramProfilesRepository) {
        this.offenderRepository = offenderRepository;
        this.offenderProgramProfilesRepository = offenderProgramProfilesRepository;
        this.offenderProgrammeProfileTransformer = offenderProgrammeProfileTransformer;
    }

    public Optional<List<ProgrammeProfile>> offenderProgrammeProfilesForOffenderId(Long offenderId, Optional<LocalDateTime> maybeFrom, Optional<LocalDateTime> maybeTo) {
        final Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        final Optional<List<OffenderBooking>> maybeOffenderBookings = maybeOffender.map(Offender::getOffenderBookings);

        return maybeOffenderBookings.map(bookings -> bookings
                .stream()
                .flatMap(ob -> offenderProgramProfilesRepository
                        .findAll(OffenderProgramProfilesFilter.builder()
                                .bookingId(ob.getOffenderBookId())
                                .from(maybeFrom)
                                .to(maybeTo)
                                .build())
                        .stream())
                .map(offenderProgrammeProfileTransformer::programmeProfileOf)
                .collect(Collectors.toList()));
    }

    public Optional<List<ProgrammeProfile>> offenderProgrammeProfilesForOffenderIdAndBookingId(Long offenderId, Long bookingId, Optional<LocalDateTime> maybeFrom, Optional<LocalDateTime> maybeTo) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        return maybeOffender.flatMap(
                offender -> offender.getOffenderBookings()
                        .stream()
                        .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                        .findFirst())
                .map(ob -> offenderProgramProfilesRepository
                        .findAll(OffenderProgramProfilesFilter.builder()
                                .bookingId(ob.getOffenderBookId())
                                .from(maybeFrom)
                                .to(maybeTo)
                                .build())
                        .stream()
                .map(offenderProgrammeProfileTransformer::programmeProfileOf)
                        .collect(Collectors.toList()));
    }
}