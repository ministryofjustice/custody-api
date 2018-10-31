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
import uk.gov.justice.digital.nomis.api.CaseNote;
import uk.gov.justice.digital.nomis.service.CaseNotesService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender case notes", tags = "Offender case note")
public class CaseNotesController {

    private final CaseNotesService caseNotesService;

    @Autowired
    public CaseNotesController(CaseNotesService caseNotesService) {

        this.caseNotesService = caseNotesService;
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/caseNotes", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK"),
    })

    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time without zone or offset (local date time), eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "to", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time without zone or offset (local date time), eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "type", dataType = "string", paramType = "query", allowMultiple = true,
                    value = "Comma separated list of case note types to include"),
            @ApiImplicitParam(name = "subtype", dataType = "string", paramType = "query", allowMultiple = true,
                    value = "Comma separated list of case note sub types to include")
    })
    public ResponseEntity<List<CaseNote>> getOffenderCaseNotes(@PathVariable("offenderId") Long offenderId,
                                                               @RequestParam("bookingId") Optional<Long> maybeBookingId,
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("from") Optional<LocalDateTime> maybeFrom,
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("to") Optional<LocalDateTime> maybeTo,
                                                               @RequestParam("type") Optional<Set<String>> maybeTypes,
                                                               @RequestParam("subtype") Optional<Set<String>> maybeSubTypes) {
        return maybeBookingId
                .map(bookingId -> caseNotesService.getCaseNotesForOffenderIdAndBookingId(offenderId, bookingId, maybeFrom, maybeTo, maybeTypes, maybeSubTypes))
                .orElse(caseNotesService.getCaseNotesForOffenderId(offenderId, maybeFrom, maybeTo, maybeTypes, maybeSubTypes))
                .map(caseNotes -> new ResponseEntity<>(caseNotes, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
