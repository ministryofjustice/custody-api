package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RehabProvider {
    private Long offenderRehabProviderId;
    private String providerCode;
    private Boolean active;
    private String comments;
    private RehabilitationDecisionProvider provider;
}

