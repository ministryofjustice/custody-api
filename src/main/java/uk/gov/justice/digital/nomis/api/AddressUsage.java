package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressUsage {
    private String usage;
    private Boolean active;
}
