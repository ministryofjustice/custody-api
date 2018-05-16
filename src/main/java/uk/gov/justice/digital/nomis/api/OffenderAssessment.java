package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class OffenderAssessment {
    private Long bookingId;
    private Integer assessmentSequence;
    private Long offenderId;
    private LocalDate assessmentDate;
    private AssessmentType assessmentType;
    private BigDecimal score;
    private String assessStatus;
    private String calcSupLevelType;
    private Long assessStaffId;
    private String assessComments;
    private String overrideReasonText;
    private String placeAgencyLocationId;
    private String overridedSupLevelType;
    private String overrideComments;
    private Long overrideStaffId;
    private LocalDate evaluationDate;
    private LocalDate nextReviewDate;
    private String evaluationResultCode;
    private String reviewSupLevelType;
    private String reviewPlacementText;
    private String reviewCommitteeCode;
    private String committeeComments;
    private String reviewPlaceAgencyLocationId;
    private String reviewSupLevelText;
    private String assessCommitteeCode;
    private LocalDate creationDate;
    private String approvedSupLevelType;
    private String assessmentCreateLocationId;
    private Long assessorStaffId;
    private String overrideUserId;
    private String overrideReason;
    private LocalDateTime createDateTime;
    private String createUserId;
}
