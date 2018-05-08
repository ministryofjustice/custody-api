package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class OffenderImprisonmentStatus {
    private Long offenderBookId;
    private Long imprisonStatusSeq;
    private ImprisonmentStatus imprisonmentStatuses;
    private LocalDateTime effectiveDateTime;
    private LocalDate expiryDate;
    private String agyLocId;
    private String commentText;
    private Boolean latestStatus;
}
