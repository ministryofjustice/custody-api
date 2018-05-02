package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImprisonmentStatus {
    private Long imprisonmentStatusId;
    private String imprisonmentStatus;
    private String description;
    private String bandCode;
    private Integer rankValue;
    private Integer imprisonmentStatusSeq;
}
