package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
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
import uk.gov.justice.digital.nomis.api.OffenderImprisonStatus;
import uk.gov.justice.digital.nomis.service.OffenderImprisonStatusService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class OffenderImprisonStatusController {

    private final OffenderImprisonStatusService offenderImprisonStatusService;

    @Autowired
    public OffenderImprisonStatusController(OffenderImprisonStatusService offenderImprisonStatusService) {
        this.offenderImprisonStatusService = offenderImprisonStatusService;
    }

    @RequestMapping(path = "/imprisonmentStatuses", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page.")})
    public PagedResources<Resource<OffenderImprisonStatus>> getImprisonmentStatuses(
            final @ApiParam Pageable pageable,
            final PagedResourcesAssembler<OffenderImprisonStatus> assembler) {

        Page<OffenderImprisonStatus> imprisonStatuses = offenderImprisonStatusService.getOffenderImprisonStatuses(pageable);
        return assembler.toResource(imprisonStatuses);
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/imprisonmentStatuses", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender or booking not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<OffenderImprisonStatus>> getOffenderImprisonmentStatuses(@PathVariable("offenderId") Long offenderId,
                                                                                        @RequestParam("bookingId") Optional<Long> maybeBookingId) {

        return maybeBookingId
                .map(bookingId -> offenderImprisonStatusService.offenderImprisonStatusForOffenderIdAndBookingId(offenderId, bookingId))
                .orElse(offenderImprisonStatusService.offenderImprisonStatusForOffenderId(offenderId))
                .map(imprisonStatuses -> new ResponseEntity<>(imprisonStatuses, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
