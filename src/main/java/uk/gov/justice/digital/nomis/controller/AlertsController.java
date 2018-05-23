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
import uk.gov.justice.digital.nomis.api.Alert;
import uk.gov.justice.digital.nomis.service.AlertsService;

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
                .orElse(alertsService.offenderAlertsForOffenderId(offenderId, maybeAlertCode, maybeAlertStatus, maybeAlertType))
                .map(attendances -> new ResponseEntity<>(attendances, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

}
