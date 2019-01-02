package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RehabProvider {
    private Long offenderRehabProviderId;
    private String providerCode;
    private Boolean active;
    private String comments;
    private RehabilitationDecisionProvider provider;
}

