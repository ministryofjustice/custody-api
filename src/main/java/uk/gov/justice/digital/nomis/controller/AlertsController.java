package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.nomis.api.Alert;
import uk.gov.justice.digital.nomis.jpa.filters.AlertsFilter;
import uk.gov.justice.digital.nomis.service.AlertsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Offender alerts", tags = "Offender Alerts")
public class AlertsController {

    private final AlertsService alertsService;

    @Autowired
    public AlertsController(AlertsService alertsService) {
        this.alertsService = alertsService;
    }

    @RequestMapping(path = "/alerts", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page.")})
    public PagedResources<Resource<Alert>> getAlerts(final @ApiParam Pageable pageable,
                                                                       final PagedResourcesAssembler<Alert> assembler,
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("from") Optional<LocalDateTime> from,
                                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("to") Optional<LocalDateTime> to) {

        AlertsFilter alertsFilter = AlertsFilter.builder()
                .from(from)
                .to(to)
                .build();

        Page<Alert> addresses = alertsService.getAlerts(pageable, alertsFilter);
        return assembler.toResource(addresses);
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/alerts", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender or booking not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Alert>> getOffenderAlerts(@PathVariable("offenderId") Long offenderId,
                                                         @RequestParam("bookingId") Optional<Long> maybeBookingId,
                                                         @RequestParam("alertType") Optional<String> maybeAlertType,
                                                         @RequestParam("alertCode") Optional<String> maybeAlertCode,
                                                         @RequestParam("alertStatus") Optional<String> maybeAlertStatus) {

        return maybeBookingId
                .map(bookingId -> alertsService.offenderAlertsForOffenderIdAndBookingId(offenderId, bookingId, maybeAlertCode, maybeAlertStatus, maybeAlertType))
                .orElse(alertsService.getOffenderAlerts(offenderId, maybeAlertCode, maybeAlertStatus, maybeAlertType))
                .map(attendances -> new ResponseEntity<>(attendances, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

}
