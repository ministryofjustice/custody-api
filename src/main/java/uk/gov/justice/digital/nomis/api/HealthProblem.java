package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HealthProblem {
    private Long offenderHealthProblemId;
    private Long bookingId;
    private Long offenderId;
    private String problemType;
    private String problemCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String caseloadType;
    private String description;
    private String problemStatus;
}
