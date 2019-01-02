package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employment {
    private Long bookingId;
    private Long employmentSequence;
    private LocalDate employmentDate;
    private KeyValue employmentPostCode;
    private String employmentType;
    private LocalDate terminationDate;
    private String employerName;
    private String supervisorName;
    private String position;
    private String terminationReason;
    private BigDecimal wage;
    private String wagePeriodCode;
    private KeyValue occupationsCode;
    private String comments;
    private String caseloadType;
    private String contactType;
    private String contactNumber;
    private String scheduleType;
    private Integer scheduleHours;
    private Integer hoursWeek;
    private Boolean partialEmploymentDate;
    private Boolean partialTerminationDate;
    private Boolean employerAware;
    private Boolean contactEmployer;
    private Long offenderEmploymentId;
    private String employmentSchedule;
    private String certification;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}
