package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class SentenceCalculation {
    private Long sentenceCalculationId;
    private Long bookingId;
    private LocalDateTime calculationDate;
    private Long staffId;
    private String comments;
    private LocalDateTime hdcedCalculatedDate;
    private LocalDateTime hdcedOverridedDate;
    private LocalDateTime hdcadCalculatedDate;
    private LocalDateTime hdcadOverridedDate;
    private LocalDateTime etdCalculatedDate;
    private LocalDateTime etdOverridedDate;
    private LocalDateTime mtdCalculatedDate;
    private LocalDateTime mtdOverridedDate;
    private LocalDateTime ltdCalculatedDate;
    private LocalDateTime ltdOverridedDate;
    private LocalDateTime ardCalculatedDate;
    private LocalDateTime ardOverridedDate;
    private LocalDateTime crdCalculatedDate;
    private LocalDateTime crdOverridedDate;
    private LocalDateTime pedCalculatedDate;
    private LocalDateTime pedOverridedDate;
    private LocalDateTime apdCalculatedDate;
    private LocalDateTime apdOverridedDate;
    private LocalDateTime npdCalculatedDate;
    private LocalDateTime npdOverridedDate;
    private LocalDateTime ledCalculatedDate;
    private LocalDateTime ledOverridedDate;
    private LocalDateTime sedCalculatedDate;
    private LocalDateTime sedOverridedDate;
    private LocalDateTime prrdCalculatedDate;
    private LocalDateTime prrdOverridedDate;
    private LocalDateTime tariffCalculatedDate;
    private LocalDateTime tariffOverridedDate;
    private String calcReasonCode;
    private LocalDateTime effectiveSentenceEndDate;
    private Boolean hdcEligibleWf;
    private Long workflowHistoryId;
    private String effectiveSentenceLength;
    private String judiciallyImposedSentenceLength;
    private LocalDateTime dprrdCalculatedDate;
    private LocalDateTime dprrdOverridedDate;
    private LocalDateTime ersedOverridedDate;
    private LocalDateTime tersedOverridedDate;
    private LocalDateTime rotlOverridedDate;
    private LocalDateTime tusedCalculatedDate;
    private LocalDateTime tusedOverridedDate;
    private LocalDate releaseDate;
    private String releaseType;
    private LocalDate confirmedReleaseDate;
    private LocalDate nonDtoReleaseDate;
    private LocalDate midTermDate;
}
