package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ASSESSMENTS")
public class Assessment {
    @Id
    @Column(name = "ASSESSMENT_ID")
    private Long assessmentId;
    @Column(name = "ASSESSMENT_CLASS")
    private String assessmentClass;
    @Column(name = "ASSESSMENT_CODE")
    private String assessmentCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "UPDATE_ALLOWED_FLAG")
    private String updateAllowedFlag;
    @Column(name = "EFFECTIVE_DATE")
    private Timestamp effectiveDate;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "SCORE")
    private BigDecimal score;
    @Column(name = "MUTUAL_EXCLUSIVE_FLAG")
    private String mutualExclusiveFlag;
    @Column(name = "DETERMINE_SUP_LEVEL_FLAG")
    private String determineSupLevelFlag;
    @Column(name = "REQUIRE_EVALUATION_FLAG")
    private String requireEvaluationFlag;
    @Column(name = "REQUIRE_APPROVAL_FLAG")
    private String requireApprovalFlag;
    @Column(name = "REVIEW_CYCLE_DAYS")
    private Integer reviewCycleDays;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "REVIEW_FLAG")
    private String reviewFlag;
    @Column(name = "ASSESS_COMMENT")
    private String assessComment;
    @Column(name = "HIGH_VALUE")
    private BigDecimal highValue;
    @Column(name = "LOW_VALUE")
    private BigDecimal lowValue;
    @Column(name = "SEARCH_CRITERIA_FLAG")
    private String searchCriteriaFlag;
    @Column(name = "OVERRIDEABLE_FLAG")
    private String overrideableFlag;
    @Column(name = "ASSESSMENT_TYPE")
    private String assessmentType;
    @Column(name = "CALCULATE_TOTAL_FLAG")
    private String calculateTotalFlag;
    @Column(name = "MEASURE")
    private String measure;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CELL_SHARING_ALERT_FLAG")
    private String cellSharingAlertFlag;
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
    @Column(name = "TOTAL_PERCENT")
    private BigDecimal totalPercent;
    @Column(name = "REVERSE_SCORE")
    private BigDecimal reverseScore;
    @Column(name = "SCREEN_CODE")
    private String screenCode;

}
