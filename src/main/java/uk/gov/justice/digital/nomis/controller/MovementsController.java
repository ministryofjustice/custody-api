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
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.jpa.filters.MovementsFilter;
import uk.gov.justice.digital.nomis.service.MovementsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
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
                    value = "Number of records per page.")})
    public PagedResources<Resource<ExternalMovement>> getMovements(final @ApiParam Pageable pageable,
                                                                   final PagedResourcesAssembler<ExternalMovement> assembler,
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("from") Optional<LocalDateTime> from,
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final @RequestParam("to") Optional<LocalDateTime> to) {

        MovementsFilter movementsFilter = MovementsFilter.builder()
                .from(from)
                .to(to)
                .build();

        Page<ExternalMovement> externalMovements = movementsService.getMovements(pageable, movementsFilter);
        return assembler.toResource(externalMovements);
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/movements", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ExternalMovement>> getOffenderMovements(@PathVariable("offenderId") Long offenderId) {

        return movementsService.getOffenderMovements(offenderId)
                .map(addresses -> new ResponseEntity<>(addresses, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));

    }

}
