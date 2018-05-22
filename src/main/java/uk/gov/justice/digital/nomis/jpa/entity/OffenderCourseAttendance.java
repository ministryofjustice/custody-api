package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_COURSE_ATTENDANCES")
public class OffenderCourseAttendance {
    @Id
    @Column(name = "EVENT_ID")
    private Long eventId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "EVENT_DATE")
    private Timestamp eventDate;
    @Column(name = "START_TIME")
    private Timestamp startTime;
    @Column(name = "END_TIME")
    private Timestamp endTime;
    @Column(name = "EVENT_SUB_TYPE")
    private String eventSubType;
    @Column(name = "EVENT_STATUS")
    private String eventStatus;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "HIDDEN_COMMENT_TEXT")
    private String hiddenCommentText;
    @Column(name = "TO_INTERNAL_LOCATION_ID")
    private Long toInternalLocationId;
    @Column(name = "CRS_SCH_ID")
    private Integer crsSchId;
    @Column(name = "OUTCOME_REASON_CODE")
    private String outcomeReasonCode;
    @Column(name = "PIECE_WORK")
    private BigDecimal pieceWork;
    @Column(name = "ENGAGEMENT_CODE")
    private String engagementCode;
    @Column(name = "UNDERSTANDING_CODE")
    private String understandingCode;
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "CREDITED_HOURS")
    private BigDecimal creditedHours;
    @Column(name = "AGREED_TRAVEL_HOUR")
    private BigDecimal agreedTravelHour;
    @Column(name = "SUPERVISOR_NAME")
    private String supervisorName;
    @Column(name = "BEHAVIOUR_CODE")
    private String behaviourCode;
    @Column(name = "ACTION_CODE")
    private String actionCode;
    @Column(name = "SICK_NOTE_RECEIVED_DATE")
    private Timestamp sickNoteReceivedDate;
    @Column(name = "SICK_NOTE_EXPIRY_DATE")
    private Timestamp sickNoteExpiryDate;
    @Column(name = "OFF_PRGREF_ID")
    private Long offPrgrefId;
    @Column(name = "IN_TIME")
    private Timestamp inTime;
    @Column(name = "OUT_TIME")
    private Timestamp outTime;
    @Column(name = "PERFORMANCE_CODE")
    private String performanceCode;
    @Column(name = "REFERENCE_ID")
    private Long referenceId;
    @Column(name = "TO_ADDRESS_OWNER_CLASS")
    private String toAddressOwnerClass;
    @Column(name = "TO_ADDRESS_ID")
    private Long toAddressId;
    @Column(name = "EVENT_OUTCOME")
    private String eventOutcome;
    @Column(name = "OFF_CRS_SCH_REF_ID")
    private Integer offCrsSchRefId;
    @Column(name = "SUPERVISOR_STAFF_ID")
    private Long supervisorStaffId;
    @Column(name = "CRS_APPT_ID")
    private Long crsApptId;
    @Column(name = "OFFENDER_COURSE_APPT_RULE_ID")
    private Long offenderCourseApptRuleId;

    @OneToOne
    @JoinColumn(name = "CRS_ACTY_ID")
    private CourseActivity courseActivity;

    @Column(name = "EVENT_TYPE")
    private String eventType;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "EVENT_CLASS")
    private String eventClass;
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
    @Column(name = "UNEXCUSED_ABSENCE_FLAG")
    private String unexcusedAbsenceFlag;
    @Column(name = "TO_AGY_LOC_ID")
    private String toAgyLocId;
    @Column(name = "SESSION_NO")
    private Integer sessionNo;
    @Column(name = "OFFENDER_PRG_OBLIGATION_ID")
    private Long offenderPrgObligationId;
    @Column(name = "PROGRAM_ID")
    private Long programId;
    @Column(name = "BONUS_PAY")
    private BigDecimal bonusPay;
    @Column(name = "TXN_ID")
    private Integer txnId;
    @Column(name = "TXN_ENTRY_SEQ")
    private Integer txnEntrySeq;
    @Column(name = "PAY_FLAG")
    private String payFlag;
    @Column(name = "AUTHORISED_ABSENCE_FLAG")
    private String authorisedAbsenceFlag;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "CRS_ACTY_ID", referencedColumnName = "CRS_ACTY_ID", insertable = false, updatable = false),
            @JoinColumn(name = "EVENT_DATE", referencedColumnName = "SCHEDULE_DATE", insertable = false, updatable = false),
            @JoinColumn(name = "CRS_SCH_ID", referencedColumnName = "CRS_SCH_ID", insertable = false, updatable = false)
    })
    private CourseSchedule courseSchedule;

}
