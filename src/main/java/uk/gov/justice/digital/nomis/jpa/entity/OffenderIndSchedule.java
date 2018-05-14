package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_IND_SCHEDULES")
public class OffenderIndSchedule {
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
    @Column(name = "EVENT_CLASS")
    private String eventClass;
    @Column(name = "EVENT_TYPE")
    private String eventType;
    @Column(name = "EVENT_SUB_TYPE")
    private String eventSubType;
    @Column(name = "EVENT_STATUS")
    private String eventStatus;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "HIDDEN_COMMENT_TEXT")
    private String hiddenCommentText;
    @Column(name = "APPLICATION_DATE")
    private Timestamp applicationDate;
    @Column(name = "PARENT_EVENT_ID")
    private Integer parentEventId;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "TO_AGY_LOC_ID")
    private String toAgyLocId;
    @Column(name = "TO_INTERNAL_LOCATION_ID")
    private Integer toInternalLocationId;
    @Column(name = "FROM_CITY")
    private String fromCity;
    @Column(name = "TO_CITY")
    private String toCity;
    @Column(name = "CRS_SCH_ID")
    private Integer crsSchId;
    @Column(name = "ORDER_ID")
    private Integer orderId;
    @Column(name = "SENTENCE_SEQ")
    private Integer sentenceSeq;
    @Column(name = "OUTCOME_REASON_CODE")
    private String outcomeReasonCode;
    @Column(name = "JUDGE_NAME")
    private String judgeName;
    @Column(name = "CHECK_BOX_1")
    private String checkBox1;
    @Column(name = "CHECK_BOX_2")
    private String checkBox2;
    @Column(name = "IN_CHARGE_STAFF_ID")
    private Integer inChargeStaffId;
    @Column(name = "CREDITED_HOURS")
    private Integer creditedHours;
    @Column(name = "REPORT_IN_DATE")
    private Timestamp reportInDate;
    @Column(name = "PIECE_WORK")
    private BigDecimal pieceWork;
    @Column(name = "ENGAGEMENT_CODE")
    private String engagementCode;
    @Column(name = "UNDERSTANDING_CODE")
    private String understandingCode;
    @Column(name = "DETAILS")
    private String details;
    @Column(name = "CREDITED_WORK_HOUR")
    private BigDecimal creditedWorkHour;
    @Column(name = "AGREED_TRAVEL_HOUR")
    private BigDecimal agreedTravelHour;
    @Column(name = "UNPAID_WORK_SUPERVISOR")
    private String unpaidWorkSupervisor;
    @Column(name = "UNPAID_WORK_BEHAVIOUR")
    private String unpaidWorkBehaviour;
    @Column(name = "UNPAID_WORK_ACTION")
    private String unpaidWorkAction;
    @Column(name = "SICK_NOTE_RECEIVED_DATE")
    private Timestamp sickNoteReceivedDate;
    @Column(name = "SICK_NOTE_EXPIRY_DATE")
    private Timestamp sickNoteExpiryDate;
    @Column(name = "COURT_EVENT_RESULT")
    private String courtEventResult;
    @Column(name = "UNEXCUSED_ABSENCE_FLAG")
    private String unexcusedAbsenceFlag;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "ESCORT_CODE")
    private String escortCode;
    @Column(name = "CONFIRM_FLAG")
    private String confirmFlag;
    @Column(name = "DIRECTION_CODE")
    private String directionCode;
    @Column(name = "TO_CITY_CODE")
    private String toCityCode;
    @Column(name = "FROM_CITY_CODE")
    private String fromCityCode;
    @Column(name = "OFF_PRGREF_ID")
    private Integer offPrgrefId;
    @Column(name = "IN_TIME")
    private Timestamp inTime;
    @Column(name = "OUT_TIME")
    private Timestamp outTime;
    @Column(name = "PERFORMANCE_CODE")
    private String performanceCode;
    @Column(name = "TEMP_ABS_SCH_ID")
    private Integer tempAbsSchId;
    @Column(name = "REFERENCE_ID")
    private Integer referenceId;
    @Column(name = "TRANSPORT_CODE")
    private String transportCode;
    @Column(name = "APPLICATION_TIME")
    private Timestamp applicationTime;
    @Column(name = "TO_COUNTRY_CODE")
    private String toCountryCode;
    @Column(name = "OJ_LOCATION_CODE")
    private String ojLocationCode;
    @Column(name = "CONTACT_PERSON_NAME")
    private String contactPersonName;
    @Column(name = "TO_ADDRESS_OWNER_CLASS")
    private String toAddressOwnerClass;
    @Column(name = "TO_ADDRESS_ID")
    private Integer toAddressId;
    @Column(name = "RETURN_DATE")
    private Timestamp returnDate;
    @Column(name = "RETURN_TIME")
    private Timestamp returnTime;
    @Column(name = "TO_CORPORATE_ID")
    private Integer toCorporateId;
    @Column(name = "TA_ID")
    private Integer taId;
    @Column(name = "EVENT_OUTCOME")
    private String eventOutcome;
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
    @Column(name = "OFFENDER_PRG_OBLIGATION_ID")
    private Integer offenderPrgObligationId;
    @Column(name = "OFFENDER_MOVEMENT_APP_ID")
    private Integer offenderMovementAppId;

}
