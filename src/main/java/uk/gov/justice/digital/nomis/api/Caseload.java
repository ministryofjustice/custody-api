package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@ApiModel(description = "Caseload")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Caseload {
    @ApiModelProperty(required = true, value = "Identifier for the caseload", example = "CADM_I", position = 0)
    @NotBlank
    private String id;

    @ApiModelProperty(required = true, value = "Name of the caseload.", example = "Central Caseload", position = 1)
    @NotBlank
    private String name;

    @ApiModelProperty(required = true, value = "Type of caseload", example = "INST", position = 2)
    @NotBlank
    private String type;

    @ApiModelProperty(value = "Indicates that this caseload in the context of a staff member is the current active", example = "false", position = 3)
    private boolean currentActive;

    @ApiModelProperty(value = "List of linked agencies to this caseload", position = 4)
    private List<AgencyLocation> agencies;
}

