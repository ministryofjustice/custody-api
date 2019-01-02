package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthProblem {
    private Long offenderHealthProblemId;
    private Long bookingId;
    private Long offenderId;
    private String problemType;
    private KeyValue problemCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String caseloadType;
    private String description;
    private String problemStatus;
}
