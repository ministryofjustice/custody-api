package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    private String code;
    private String description;
    private String indicator;
}
