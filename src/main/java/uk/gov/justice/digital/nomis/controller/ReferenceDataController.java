package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import uk.gov.justice.digital.nomis.api.AgencyLocation;
import uk.gov.justice.digital.nomis.service.ReferenceDataService;

@RestController
@Api(description = "Reference data resources", tags = "Reference data sets")
public class ReferenceDataController {

    private final ReferenceDataService referenceDataService;

    @Autowired
    public ReferenceDataController(final ReferenceDataService referenceDataService) {
        this.referenceDataService = referenceDataService;
    }

    @RequestMapping(path = "/agencyLocations", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", example = "0", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page.", example = "20", defaultValue = "20")})
    public Page<AgencyLocation> getAgencyLocations(final @ApiIgnore Pageable pageable) {

        return referenceDataService.getLocations(pageable);
    }
}
