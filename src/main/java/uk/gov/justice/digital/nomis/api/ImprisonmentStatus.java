package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImprisonmentStatus {
    private Long imprisonmentStatusId;
    private String imprisonmentStatus;
    private String description;
    private String bandCode;
    private Integer rankValue;
    private Integer imprisonmentStatusSeq;
}
