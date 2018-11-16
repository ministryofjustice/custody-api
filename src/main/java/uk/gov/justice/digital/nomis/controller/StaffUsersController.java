package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.justice.digital.nomis.api.StaffUser;
import uk.gov.justice.digital.nomis.service.StaffUsersService;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api(description = "Staff User Details", tags = "Staff User Details")
public class StaffUsersController {

    private final StaffUsersService staffUsersService;

    @Autowired
    public StaffUsersController(StaffUsersService userService) {
        this.staffUsersService = userService;
    }

    @RequestMapping(path = "/staffusers/{username}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<StaffUser> getStaffUser(@PathVariable("username") String username) {

        return staffUsersService.staffUserForUsername(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/staffusers", method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                    value = "Number of records per page.")})
    public PagedResources<Resource<StaffUser>> getStaffUsers(final Pageable pageable,
                                                           final PagedResourcesAssembler<StaffUser> assembler) {

        Page<StaffUser> staffUsers = staffUsersService.getStaffUsers(pageable);
        return assembler.toResource(staffUsers);
    }
}
