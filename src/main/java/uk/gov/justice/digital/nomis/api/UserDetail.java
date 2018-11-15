package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDetail {
    private String username;
    private Caseload activeCaseload;
    private List<Caseload> caseloads;
}

