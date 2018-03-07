package uk.gov.justice.digital.nomis.controller;

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
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.service.MovementsService;

@RestController
public class MovementsController {

    private final MovementsService movementsService;

    @Autowired
    public MovementsController(MovementsService movementsService) {
        this.movementsService = movementsService;
    }

    @RequestMapping(path = "/movements", method = RequestMethod.GET)
    @ResponseBody
    public PagedResources<Resource<ExternalMovement>> getMovements(Pageable pageable, PagedResourcesAssembler<ExternalMovement> assembler) {
        Page<ExternalMovement> externalMovements = movementsService.getMovements(pageable);
        return assembler.toResource(externalMovements);
    }
}
