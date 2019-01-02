package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffenderImprisonmentStatus {
    private Long bookingId;
    private Long imprisonStatusSeq;
    private ImprisonmentStatus imprisonmentStatus;
    private String imprisonmentStatusCode;
    private LocalDateTime effectiveDateTime;
    private LocalDate expiryDate;
    private String agencyLocationId;
    private String commentText;
    private Boolean latestStatus;
}
