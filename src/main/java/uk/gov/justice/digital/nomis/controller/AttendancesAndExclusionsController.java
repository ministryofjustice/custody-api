package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.nomis.api.CourseAttendance;
import uk.gov.justice.digital.nomis.api.Exclusion;
import uk.gov.justice.digital.nomis.service.AttendancesAndExclusionsService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender course attendances and exclusions", tags = "Course attendances and exclusions")
public class AttendancesAndExclusionsController {

    private final AttendancesAndExclusionsService attendancesAndExclusionsService;

    @Autowired
    public AttendancesAndExclusionsController(AttendancesAndExclusionsService attendancesAndExclusionsService) {
        this.attendancesAndExclusionsService = attendancesAndExclusionsService;
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/courseAttendances", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<CourseAttendance>> getOffenderCourseAttendances(@PathVariable("offenderId") Long offenderId,
                                                                               @RequestParam("bookingId") Optional<Long> maybeBookingId) {

        return maybeBookingId
                .map(bookingId -> attendancesAndExclusionsService.offenderCourseAttendancessForOffenderIdAndBookingId(offenderId, bookingId))
                .orElse(attendancesAndExclusionsService.offenderCourseAttendancessForOffenderId(offenderId))
                .map(attendances -> new ResponseEntity<>(attendances, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/courseExclusions", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Exclusion>> getOffenderExclusions(@PathVariable("offenderId") Long offenderId,
                                                                 @RequestParam("bookingId") Optional<Long> maybeBookingId) {

        return maybeBookingId
                .map(bookingId -> attendancesAndExclusionsService.exclusionsForOffenderIdAndBookingId(offenderId, bookingId))
                .orElse(attendancesAndExclusionsService.exclusionsForOffenderId(offenderId))
                .map(programmeProfiles -> new ResponseEntity<>(programmeProfiles, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

}