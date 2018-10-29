package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.nomis.api.OffenderEvent;
import uk.gov.justice.digital.nomis.service.OffenderEventsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender case record events", tags = "Offender Events")
public class OffenderEventsController {

    public static final String NOTES = "**from** and **to** query params are optional.\n" +
            "An awful lot of events occur occur every day. To guard against unintentionally heavy queries, the following rules are applied:\n" +
            "If **both** are absent, scope will be limited to 24 hours starting from midnight yesterday.\n" +
            "If **to** is present but **from** is absent, **from** will be defaulted to 24 hours before **to**.\n" +
            "If **from** is present but **to** is absent, **to** will be defaulted to 24 hours after **from**. ";

    private final OffenderEventsService offenderEventsService;

    @Autowired
    public OffenderEventsController(OffenderEventsService offenderEventsService) {
        this.offenderEventsService = offenderEventsService;
    }

    @RequestMapping(path = "/events", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time without zone or offset (local date time), eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "to", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time without zone or offset (local date time), eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "type", dataType = "string", paramType = "query", allowMultiple = true,
                    value = "Comma separated list of event types to include")
    })
    @ApiOperation(value = "Get events by offender id", notes = NOTES)
    public ResponseEntity<List<OffenderEvent>> getEvents(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("from") Optional<LocalDateTime> maybeFrom,
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("to") Optional<LocalDateTime> maybeTo,
                                                         final @RequestParam("type") Optional<Set<String>> maybeTypeFilter) {
        return offenderEventsService.getEvents(maybeFrom, maybeTo, maybeTypeFilter)
                .map(events -> new ResponseEntity<>(events, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/events", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time without zone or offset (local date time), eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "to", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time without zone or offset (local date time), eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "type", dataType = "string", paramType = "query", allowMultiple = true,
                    value = "Comma separated list of event types to include")
    })
    @ApiOperation(value = "Get events by offender id", notes = NOTES)
    public ResponseEntity<List<OffenderEvent>> getEventsByOffenderId(@PathVariable("offenderId") Long offenderId,
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("from") Optional<LocalDateTime> maybeFrom,
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("to") Optional<LocalDateTime> maybeTo,
                                                                     final @RequestParam("type") Optional<Set<String>> maybeTypeFilter) {
        return offenderEventsService.getEventsForOffenderId(offenderId, maybeFrom, maybeTo, maybeTypeFilter)
                .map(events -> new ResponseEntity<>(events, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
