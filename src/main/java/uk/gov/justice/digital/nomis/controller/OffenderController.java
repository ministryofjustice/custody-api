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
import uk.gov.justice.digital.nomis.api.Offender;
import uk.gov.justice.digital.nomis.service.OffenderService;

@RestController
public class OffenderController {

    private final OffenderService offenderService;

    @Autowired
    public OffenderController(OffenderService offenderService) {
        this.offenderService = offenderService;
    }

    @RequestMapping(path = "/offenders", method = RequestMethod.GET)
    @ResponseBody
    public PagedResources<Resource<Offender>> getOffenders(Pageable pageable, PagedResourcesAssembler<Offender> assembler) {
        Page<Offender> offenders = offenderService.getOffenders(pageable);
        return assembler.toResource(offenders);

//        PagedResources<Resource<Offender>> userPagedResources =
//                assembler.toResource(
//                        offenders,
//                        linkTo(methodOn(OffenderController.class).getOffenders(pageable, assembler)).withSelfRel());
//
//        return userPagedResources;
    }
}
