package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import uk.gov.justice.digital.nomis.api.Charge;
import uk.gov.justice.digital.nomis.api.Offender;
import uk.gov.justice.digital.nomis.service.OffenderService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class OffenderController {

    private final OffenderService offenderService;

    @Autowired
    public OffenderController(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    @RequestMapping(path = "/offenders", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page.")})
    public PagedResources<Resource<Offender>> getOffenders(final Pageable pageable,
                                                           final PagedResourcesAssembler<Offender> assembler) {

        Page<Offender> offenders = offenderService.getOffenders(pageable);
        return assembler.toResource(offenders);
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender or booking not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<Offender> getOffender(@PathVariable("offenderId") Long offenderId) {

        return offenderService.getOffender(offenderId)
                .map(offender -> new ResponseEntity<>(offender, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }
}
