package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Identifier {
    private String identifierType;
    private String identifier;
    private Long sequenceNumber;
}
