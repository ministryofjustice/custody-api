package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.CourseAttendance;
import uk.gov.justice.digital.nomis.api.Exclusion;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AttendanceAndExclusionTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendancesAndExclusionsService {

    private final OffenderRepository offenderRepository;
    private final AttendanceAndExclusionTransformer attendanceAndExclusionTransformer;

    @Autowired
    public AttendancesAndExclusionsService(OffenderRepository offenderRepository, AttendanceAndExclusionTransformer attendanceAndExclusionTransformer) {
        this.offenderRepository = offenderRepository;
        this.attendanceAndExclusionTransformer = attendanceAndExclusionTransformer;
    }

    public Optional<List<CourseAttendance>> offenderCourseAttendancessForOffenderId(Long offenderId) {

        final Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        final Optional<List<OffenderBooking>> maybeOffenderBookings = maybeOffender.map(Offender::getOffenderBookings);

        return maybeOffenderBookings.map(bookings -> bookings
                .stream()
                .flatMap(booking -> booking.getOffenderCourseAttendances().stream())
                .map(attendanceAndExclusionTransformer::courseAttendanceOf)
                .collect(Collectors.toList()));
    }

    public Optional<List<CourseAttendance>> offenderCourseAttendancessForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        if (!maybeOffender.isPresent()) {
            return Optional.empty();
        }

        Optional<OffenderBooking> maybeOffenderBooking = maybeOffender.get().getOffenderBookings()
                .stream()
                .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                .findFirst();

        return maybeOffenderBooking.map(ob -> ob.getOffenderCourseAttendances()
                .stream()
                .map(attendanceAndExclusionTransformer::courseAttendanceOf)
                .collect(Collectors.toList()));
    }

    public Optional<List<Exclusion>> exclusionsForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        if (!maybeOffender.isPresent()) {
            return Optional.empty();
        }

        Optional<OffenderBooking> maybeOffenderBooking = maybeOffender.get().getOffenderBookings()
                .stream()
                .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                .findFirst();

        return maybeOffenderBooking.map(ob -> ob.getOffenderExcludeActsSchds()
                .stream()
                .map(attendanceAndExclusionTransformer::exclusionOf)
                .collect(Collectors.toList()));

    }

    public Optional<List<Exclusion>> exclusionsForOffenderId(Long offenderId) {
        final Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        final Optional<List<OffenderBooking>> maybeOffenderBookings = maybeOffender.map(Offender::getOffenderBookings);

        return maybeOffenderBookings.map(bookings -> bookings
                .stream()
                .flatMap(booking -> booking.getOffenderExcludeActsSchds().stream())
                .map(attendanceAndExclusionTransformer::exclusionOf)
                .collect(Collectors.toList()));    }
}
