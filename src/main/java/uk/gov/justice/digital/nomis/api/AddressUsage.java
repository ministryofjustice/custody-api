package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressUsage {
    private KeyValue usage;
    private Boolean active;
}
