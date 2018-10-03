package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_PROGRAM_PROFILES")
public class OffenderProgramProfile {
    @Id
    @Column(name = "OFF_PRGREF_ID")
    private Long offPrgrefId;
    @Column(name = "CRS_ACTY_ID")
    private Long crsActyId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "OFFENDER_START_DATE")
    private Timestamp offenderStartDate;
    @Column(name = "OFFENDER_PROGRAM_STATUS")
    private String offenderProgramStatus;
    @Column(name = "REFERRAL_PRIORITY")
    private String referralPriority;
    @Column(name = "REFERRAL_DATE")
    private Timestamp referralDate;
    @Column(name = "REFERRAL_COMMENT_TEXT")
    private String referralCommentText;
    @Column(name = "OFFENDER_END_REASON")
    private String offenderEndReason;
    @Column(name = "AGREED_TRAVEL_FARE")
    private BigDecimal agreedTravelFare;
    @Column(name = "AGREED_TRAVEL_HOUR")
    private BigDecimal agreedTravelHour;
    @Column(name = "OFFENDER_END_COMMENT_TEXT")
    private String offenderEndCommentText;
    @Column(name = "REJECT_DATE")
    private Timestamp rejectDate;
    @Column(name = "WAITLIST_DECISION_CODE")
    private String waitlistDecisionCode;
    @Column(name = "REFERRAL_STAFF_ID")
    private Integer referralStaffId;
    @Column(name = "OFFENDER_END_DATE")
    private Timestamp offenderEndDate;
    @Column(name = "CREDIT_WORK_HOURS")
    private BigDecimal creditWorkHours;
    @Column(name = "CREDIT_OTHER_HOURS")
    private BigDecimal creditOtherHours;
    @Column(name = "SUSPENDED_FLAG")
    private String suspendedFlag;
    @Column(name = "REJECT_REASON_CODE")
    private String rejectReasonCode;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "REVIEWED_BY")
    private String reviewedBy;
    @Column(name = "OFFENDER_SENT_CONDITION_ID")
    private Integer offenderSentConditionId;
    @Column(name = "SENTENCE_SEQ")
    private Integer sentenceSeq;
    @Column(name = "HOLIDAY_FLAG")
    private String holidayFlag;
    @Column(name = "START_SESSION_NO")
    private Integer startSessionNo;
    @Column(name = "PARENT_OFF_PRGREF_ID")
    private Integer parentOffPrgrefId;
    @Column(name = "OFFENDER_PRG_OBLIGATION_ID")
    private Integer offenderPrgObligationId;
    @Column(name = "PROGRAM_OFF_PRGREF_ID")
    private Long programOffPrgrefId;
    @Column(name = "PROFILE_CLASS")
    private String profileClass;
    @Column(name = "COMPLETION_DATE")
    private Timestamp completionDate;
    @Column(name = "NEEDED_FLAG")
    private String neededFlag;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "EARLY_END_REASON")
    private String earlyEndReason;

    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;

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

    @OneToOne
    @BatchSize(size = 1000)
    @JoinColumn(name = "CRS_ACTY_ID", insertable = false, updatable = false)
    private CourseActivity courseActivity;

    @OneToOne
    @JoinColumn(name = "AGY_LOC_ID", insertable = false, updatable = false)
    private AgencyLocation agencyLocation;

}
