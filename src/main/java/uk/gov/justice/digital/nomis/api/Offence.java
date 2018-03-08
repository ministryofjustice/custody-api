package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offence {
    private String offenceCode;
    private String description;
    private KeyValue statute;
    private KeyValue oldStatute;
    private String defaultOffenceType;
    private Boolean active;
    private KeyValue hoCode;
}
