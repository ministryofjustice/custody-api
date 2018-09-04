package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.CourseAttendance;
import uk.gov.justice.digital.nomis.api.Exclusion;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCourseAttendance;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderExcludeActsSchds;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AttendanceAndExclusionTransformer;
import uk.gov.justice.digital.nomis.service.transformer.TypesTransformer;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendancesAndExclusionsService {

    private static final Comparator<OffenderExcludeActsSchds> BY_EXCLUSION_PRIORITY = Comparator
            .comparing((OffenderExcludeActsSchds oe) -> Optional.ofNullable(oe.getModifyDatetime()).orElse(new Timestamp(0)))
            .thenComparing((OffenderExcludeActsSchds oe) -> Optional.ofNullable(oe.getCreateDatetime()).orElse(new Timestamp(0)))
            .reversed();
    private static final Comparator<? super OffenderCourseAttendance> BY_COURSE_ATTENDENCE_PRIORITY = Comparator
            .comparing((OffenderCourseAttendance oca) -> Optional.ofNullable(oca.getEventDate()).orElse(new Timestamp(0)))
            .thenComparing((OffenderCourseAttendance oca) -> Optional.ofNullable(oca.getStartTime()).orElse(new Timestamp(0)))
            .reversed();
    private final OffenderRepository offenderRepository;
    private final AttendanceAndExclusionTransformer attendanceAndExclusionTransformer;
    private final TypesTransformer typesTransformer;

    @Autowired
    public AttendancesAndExclusionsService(OffenderRepository offenderRepository, AttendanceAndExclusionTransformer attendanceAndExclusionTransformer, TypesTransformer typesTransformer) {
        this.offenderRepository = offenderRepository;
        this.attendanceAndExclusionTransformer = attendanceAndExclusionTransformer;
        this.typesTransformer = typesTransformer;
    }

    public Optional<List<CourseAttendance>> offenderCourseAttendancessForOffenderId(Long offenderId) {

        final Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        final Optional<List<OffenderBooking>> maybeOffenderBookings = maybeOffender.map(Offender::getOffenderBookings);

        return maybeOffenderBookings.map(bookings -> bookings
                .stream()
                .flatMap(booking -> booking.getOffenderCourseAttendances().stream())
                .sorted(BY_COURSE_ATTENDENCE_PRIORITY)
                .map(attendanceAndExclusionTransformer::courseAttendanceOf)
                .collect(Collectors.toList()));
    }

    public Optional<List<CourseAttendance>> offenderCourseAttendancessForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        return maybeOffender.flatMap(
                offender -> offender.getOffenderBookings()
                        .stream()
                        .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                        .findFirst())
                .map(ob -> ob.getOffenderCourseAttendances()
                        .stream()
                        .sorted(BY_COURSE_ATTENDENCE_PRIORITY)
                        .map(attendanceAndExclusionTransformer::courseAttendanceOf)
                        .collect(Collectors.toList()));
    }

    public Optional<List<Exclusion>> exclusionsForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        return maybeOffender.flatMap(
                offender -> offender.getOffenderBookings()
                        .stream()
                        .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                        .findFirst())
                .map(ob -> ob.getOffenderExcludeActsSchds()
                        .stream()
                        .sorted(BY_EXCLUSION_PRIORITY)
                        .map(attendanceAndExclusionTransformer::exclusionOf)
                        .collect(Collectors.toList()));

    }

    public Optional<List<Exclusion>> exclusionsForOffenderId(Long offenderId) {
        final Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        final Optional<List<OffenderBooking>> maybeOffenderBookings = maybeOffender.map(Offender::getOffenderBookings);

        return maybeOffenderBookings.map(bookings -> bookings
                .stream()
                .flatMap(booking -> booking.getOffenderExcludeActsSchds().stream())
                .sorted(BY_EXCLUSION_PRIORITY)
                .map(attendanceAndExclusionTransformer::exclusionOf)
                .collect(Collectors.toList()));
    }
}
