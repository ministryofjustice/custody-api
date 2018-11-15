package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Caseload {
    private String id;
    private String name;
    private String type;
    private List<AgencyLocation> agencyLocations;
}

