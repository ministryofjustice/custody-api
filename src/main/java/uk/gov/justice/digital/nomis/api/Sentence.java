package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Sentence {
    private Long offenderId;
    private Long bookingId;
    private Integer sentenceSequenceNumber;
    private String sentenceStatus;
    private Integer consecToSentenceSeq;
    private String comments;
    private Integer numberOfUnexcusedAbsences;
    private String terminationReason;
    private String sentenceCategory;
    private String sentenceText;
    private BigDecimal fineAmount;
    private Integer breachLevel;
    private Integer aggregateTerm;
    private Integer aggregateAdjustDays;
    private String sentenceLevel;
    private Integer extendedDays;
    private Integer counts;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate etdCalculateDate;
    private LocalDate mtdCalculateDate;
    private LocalDate ltdCalculateDate;
    private LocalDate ardCalculateDate;
    private LocalDate crdCalculateDate;
    private LocalDate pedCalculateDate;
    private LocalDate apdCalculateDate;
    private LocalDate npdCalculateDate;
    private LocalDate ledCalculateDate;
    private LocalDate sedCalculateDate;
    private LocalDate prrdCalculateDate;
    private LocalDate tariffCalculateDate;
    private LocalDate hdcedCalculateDate;
    private LocalDate revokedDate;
    private LocalDateTime createdAt;
    private LocalDate terminationDate;
    private LocalDate dischargeDate;
    private LocalDate dprrdCalculatedDate;
    private LocalDate startDate2Calc;
    private LocalDate sled2CalcDate;
    private LocalDate tusedCalculatedDate;
    private Boolean hdcExclusion;
    private String hdcExclusionReason;

}
