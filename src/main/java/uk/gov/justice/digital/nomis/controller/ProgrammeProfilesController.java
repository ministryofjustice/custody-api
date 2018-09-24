package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.nomis.api.ProgrammeProfile;
import uk.gov.justice.digital.nomis.service.OffenderProgrammeProfilesService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender Program Profiles and associated schedules", tags = "Offender Programme Profiles")
public class ProgrammeProfilesController {

    private final OffenderProgrammeProfilesService offenderProgrammeProfilesService;

    @Autowired
    public ProgrammeProfilesController(OffenderProgrammeProfilesService offenderProgrammeProfilesService) {
        this.offenderProgrammeProfilesService = offenderProgrammeProfilesService;
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/programmeProfiles", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", dataType = "date", paramType = "query", value = "ISO 8601 Date Time, eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "to", dataType = "date", paramType = "query", value = "ISO 8601 Date Time, eg 2017-07-24T09:18:15")})
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ProgrammeProfile>> getOffenderProgramProfiles(@PathVariable("offenderId") Long offenderId,
                                                                             @RequestParam("bookingId") Optional<Long> maybeBookingId,
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("from") Optional<LocalDateTime> maybeFrom,
                                                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("to") Optional<LocalDateTime> maybeTo) {

        return maybeBookingId
                .map(bookingId -> offenderProgrammeProfilesService.offenderProgrammeProfilesForOffenderIdAndBookingId(offenderId, bookingId, maybeFrom, maybeTo))
                .orElse(offenderProgrammeProfilesService.offenderProgrammeProfilesForOffenderId(offenderId, maybeFrom, maybeTo))
                .map(programmeProfiles -> new ResponseEntity<>(programmeProfiles, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
