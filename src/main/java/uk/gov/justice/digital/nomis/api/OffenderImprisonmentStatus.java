package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class OffenderImprisonmentStatus {
    private Long offenderBookId;
    private Long imprisonStatusSeq;
    private String imprisonmentStatus;
    private LocalDateTime effectiveDateTime;
    private LocalDate expiryDate;
    private String agyLocId;
    private String commentText;
    private Boolean latestStatus;
}
