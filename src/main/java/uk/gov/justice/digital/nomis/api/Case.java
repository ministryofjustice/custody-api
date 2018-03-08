package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Case {
    private Long caseId;
    private String caseInfoNumber;
    private String caseType;
    private String caseStatus;
    private Long combinedCaseId;
    private LocalDate beginDate;
    private String agencyLocationId;
    private String victimLiasonUnit;
    private Integer lidsCaseNumber;
    private Integer nomLegalCaseRef;
    private Integer nomLegalCaseRefTransTo;
    private Integer caseSequence;
}
