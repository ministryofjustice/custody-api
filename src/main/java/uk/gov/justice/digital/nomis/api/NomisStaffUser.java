package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@ApiModel(description = "NOMIS Staff User")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NomisStaffUser {

    @ApiModelProperty(required = true, value = "Identifies the NOMIS staff user by username.", example = "ITAG_USER", position = 0)
    @NotBlank
    private String username;

    @ApiModelProperty(required = true, value = "Identifies the NOMIS staff user by staff ID.", example = "3423234", position = 1)
    @NotBlank
    private Long staffId;

    @ApiModelProperty(required = true, value = "First name of the member of NOMIS staff", example = "John", position = 2)
    @NotBlank
    private String firstName;

    @ApiModelProperty(required = true, value = "Last name of the member of staff", example = "Smith", position = 3)
    @NotBlank
    private String lastName;

    @ApiModelProperty(required = true, value = "Holds the status of staff user.", example = "ACTIVE", position = 4)
    @NotBlank
    private String status;

    @ApiModelProperty(value = "Current active NOMIS caseload for this staff user", example = "MDI", position = 5)
    private String activeNomisCaseload;

    @ApiModelProperty(value = "Map of NOMIS Caseload that this staff user has access to", position = 6)
    private Map<String, NomisCaseload> nomisCaseloads;
}

