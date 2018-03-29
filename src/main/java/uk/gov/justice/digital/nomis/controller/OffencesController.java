package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.nomis.api.Offence;
import uk.gov.justice.digital.nomis.service.OffencesService;

@RestController
@Api(description = "Offence resources", tags = "Offence reference data")
public class OffencesController {

    private final OffencesService offencesService;

    @Autowired
    public OffencesController(OffencesService offencesService) {
        this.offencesService = offencesService;
    }

    @RequestMapping(path = "/offences", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page.")})
    public PagedResources<Resource<Offence>> getOffences(final @ApiParam Pageable pageable,
                                                         final PagedResourcesAssembler<Offence> assembler) {

        Page<Offence> offences = offencesService.getOffences(pageable);
        return assembler.toResource(offences);
    }

}
