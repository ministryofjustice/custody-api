package uk.gov.justice.digital.nomis.controller;

import com.google.common.collect.ImmutableList;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uk.gov.justice.digital.nomis.api.ReleaseDetails;
import uk.gov.justice.digital.nomis.service.ReleaseDetailsService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Release Detail resources", tags = "Offender Release details")
public class ReleaseDetailsController {

    private final ReleaseDetailsService releaseDetailsService;

    @Autowired
    public ReleaseDetailsController(final ReleaseDetailsService releaseDetailsService) {
        this.releaseDetailsService = releaseDetailsService;
    }

    @RequestMapping(path = "/releaseDetails", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", example = "0", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page.", example = "20", defaultValue = "20")})
    public PagedResources<Resource<ReleaseDetails>> getReleaseDetails(
            final @ApiIgnore Pageable pageable) {

        final var releaseDetails = releaseDetailsService.getReleaseDetails(pageable);
        return new PagedResourcesAssembler<ReleaseDetails>(null, null).toResource(releaseDetails);
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/releaseDetails", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Offender or booking not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<ReleaseDetails>> getOffenderReleaseDetails(@PathVariable("offenderId") final Long offenderId,
                                                                          @RequestParam("bookingId") final Optional<Long> maybeBookingId) {

        return maybeBookingId
                .map(bookingId -> maybeListOf(releaseDetailsService.releaseDetailsForOffenderIdAndBookingId(offenderId, bookingId)))
                .orElse(releaseDetailsService.releaseDetailsForOffenderId(offenderId))
                .map(releaseDetails -> new ResponseEntity<>(releaseDetails, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    private Optional<List<ReleaseDetails>> maybeListOf(final Optional<ReleaseDetails> releaseDetails) {
        return releaseDetails.map(ImmutableList::of);
    }
}
