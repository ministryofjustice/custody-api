package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import springfox.documentation.annotations.ApiIgnore;
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.service.MovementsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api( description = "Offender movement resources", tags = "Offender Movements")
public class MovementsController {

    private final MovementsService movementsService;

    @Autowired
    public MovementsController(MovementsService movementsService) {
        this.movementsService = movementsService;
    }

    @RequestMapping(path = "/movements", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "from", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time, eg 2017-07-24T09:18:15"),
            @ApiImplicitParam(name = "to", dataType = "date", paramType = "query",
                    value = "ISO 8601 Date Time, eg 2017-07-24T09:18:15")})
    public PagedResources<Resource<ExternalMovement>> getMovements(final @ApiIgnore Pageable pageable,
                                                                   final PagedResourcesAssembler<ExternalMovement> assembler,
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("from") Optional<LocalDateTime> maybeFrom,
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("to") Optional<LocalDateTime> maybeTo) {

        Page<ExternalMovement> externalMovements = movementsService.getMovements(pageable, maybeFrom, maybeTo);
        return assembler.toResource(externalMovements);
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/movements", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender or bookingId not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ExternalMovement>> getOffenderMovements(@PathVariable("offenderId") Long offenderId,
                                                                       @RequestParam("bookingId") Optional<Long> maybeBookingId) {

        return maybeBookingId
                .map(bookingId -> movementsService.movementsForOffenderIdAndBookingId(offenderId, bookingId))
                .orElse(movementsService.getOffenderMovements(offenderId))
                .map(movements -> new ResponseEntity<>(movements, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

}
