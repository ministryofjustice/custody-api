package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class OffenderImprisonStatus {
    private Long offenderBookId;
    private Long imprisonStatusSeq;
    private String imprisonmentStatus;
    private LocalDateTime effectiveDate;
    private LocalTime effectiveTime;
    private LocalDate expiryDate;
    private String agyLocId;
    private String commentText;
    private Boolean latestStatus;
}
