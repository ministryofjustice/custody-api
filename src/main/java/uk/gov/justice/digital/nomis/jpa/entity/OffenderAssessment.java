package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_ASSESSMENTS")
public class OffenderAssessment {

    @Embeddable
    @Data
    @ToString(exclude = "offenderBooking")
    public static class Pk implements Serializable {
        @ManyToOne
        @JoinColumn(name = "OFFENDER_BOOK_ID")
        private OffenderBooking offenderBooking;

        @Column(name = "ASSESSMENT_SEQ")
        private Integer assessmentSeq;
    }

    @EmbeddedId
    private OffenderAssessment.Pk id;

    @Column(name = "ASSESSMENT_DATE")
    private Timestamp assessmentDate;

    @JoinColumn(name = "ASSESSMENT_TYPE_ID", referencedColumnName = "ASSESSMENT_ID")
    @ManyToOne
    private Assessment assessment;

    @Column(name = "SCORE")
    private BigDecimal score;
    @Column(name = "ASSESS_STATUS")
    private String assessStatus;
    @Column(name = "CALC_SUP_LEVEL_TYPE")
    private String calcSupLevelType;
    @Column(name = "ASSESS_STAFF_ID")
    private Long assessStaffId;
    @Column(name = "ASSESS_COMMENT_TEXT")
    private String assessCommentText;
    @Column(name = "OVERRIDE_REASON_TEXT")
    private String overrideReasonText;
    @Column(name = "PLACE_AGY_LOC_ID")
    private String placeAgyLocId;
    @Column(name = "OVERRIDED_SUP_LEVEL_TYPE")
    private String overridedSupLevelType;
    @Column(name = "OVERRIDE_COMMENT_TEXT")
    private String overrideCommentText;
    @Column(name = "OVERRIDE_STAFF_ID")
    private Long overrideStaffId;
    @Column(name = "EVALUATION_DATE")
    private Timestamp evaluationDate;
    @Column(name = "NEXT_REVIEW_DATE")
    private Timestamp nextReviewDate;
    @Column(name = "EVALUATION_RESULT_CODE")
    private String evaluationResultCode;
    @Column(name = "REVIEW_SUP_LEVEL_TYPE")
    private String reviewSupLevelType;
    @Column(name = "REVIEW_PLACEMENT_TEXT")
    private String reviewPlacementText;
    @Column(name = "REVIEW_COMMITTE_CODE")
    private String reviewCommitteCode;
    @Column(name = "COMMITTE_COMMENT_TEXT")
    private String committeCommentText;
    @Column(name = "REVIEW_PLACE_AGY_LOC_ID")
    private String reviewPlaceAgyLocId;
    @Column(name = "REVIEW_SUP_LEVEL_TEXT")
    private String reviewSupLevelText;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "ASSESS_COMMITTE_CODE")
    private String assessCommitteCode;
    @Column(name = "CREATION_DATE")
    private Timestamp creationDate;
    @Column(name = "CREATION_USER")
    private String creationUser;
    @Column(name = "APPROVED_SUP_LEVEL_TYPE")
    private String approvedSupLevelType;
    @Column(name = "ASSESSMENT_CREATE_LOCATION")
    private String assessmentCreateLocation;
    @Column(name = "ASSESSOR_STAFF_ID")
    private Long assessorStaffId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "OVERRIDE_USER_ID")
    private String overrideUserId;
    @Column(name = "OVERRIDE_REASON")
    private String overrideReason;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "AUDIT_TIMESTAMP")
    private Timestamp auditTimestamp;
    @Column(name = "AUDIT_USER_ID")
    private String auditUserId;
    @Column(name = "AUDIT_MODULE_NAME")
    private String auditModuleName;
    @Column(name = "AUDIT_CLIENT_USER_ID")
    private String auditClientUserId;
    @Column(name = "AUDIT_CLIENT_IP_ADDRESS")
    private String auditClientIpAddress;
    @Column(name = "AUDIT_CLIENT_WORKSTATION_NAME")
    private String auditClientWorkstationName;
    @Column(name = "AUDIT_ADDITIONAL_INFO")
    private String auditAdditionalInfo;

}
