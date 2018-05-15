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
    private Boolean isActive;
    private Integer consecToSentenceSeq;
    private String comments;
    private Integer numberOfUnexcusedAbsences;
    private String terminationReason;
    private String sentenceCategory;
    private String sentenceCalcType;
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
    private LocalDate etdCalculatedDate;
    private LocalDate mtdCalculatedDate;
    private LocalDate ltdCalculatedDate;
    private LocalDate ardCalculatedDate;
    private LocalDate crdCalculatedDate;
    private LocalDate pedCalculatedDate;
    private LocalDate apdCalculatedDate;
    private LocalDate npdCalculatedDate;
    private LocalDate ledCalculatedDate;
    private LocalDate sedCalculatedDate;
    private LocalDate prrdCalculatedDate;
    private LocalDate tariffCalculatedDate;
    private LocalDate hdcedCalculatedDate;
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
