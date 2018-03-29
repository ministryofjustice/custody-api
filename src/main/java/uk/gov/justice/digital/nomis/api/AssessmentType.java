package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssessmentType {
    private Long assessmentId;
    private String assessmentClass;
    private Long parentAssessmentId;
    private String assessmentCode;
    private String description;
    private Boolean cellSharingAlertFlag;
    private Boolean determineSupLevelFlag;
}
