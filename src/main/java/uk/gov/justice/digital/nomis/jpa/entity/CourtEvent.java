package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "COURT_EVENTS")
public class CourtEvent {

    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;

    @Id
    @Column(name = "EVENT_ID")
    private Long eventId;
    @Column(name = "EVENT_STATUS")
    private String eventStatus;
    @Column(name = "EVENT_DATE")
    private Timestamp eventDate;
    @Column(name = "START_TIME")
    private Timestamp startTime;
    @Column(name = "END_TIME")
    private Timestamp endTime;
    @Column(name = "EVENT_OUTCOME")
    private String eventOutcome;

    @Column(name = "CASE_ID")
    private Long caseId;
    @Column(name = "COURT_EVENT_TYPE")
    private String courtEventType;
    @Column(name = "JUDGE_NAME")
    private String judgeName;
    @Column(name = "PARENT_EVENT_ID")
    private Long parentEventId;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "OUTCOME_REASON_CODE")
    private String outcomeReasonCode;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "ORDER_REQUESTED_FLAG")
    private String orderRequestedFlag;
    @Column(name = "RESULT_CODE")
    private String resultCode;
    @Column(name = "NEXT_EVENT_REQUEST_FLAG")
    private String nextEventRequestFlag;
    @Column(name = "NEXT_EVENT_DATE")
    private Timestamp nextEventDate;
    @Column(name = "NEXT_EVENT_START_TIME")
    private Timestamp nextEventStartTime;
    @Column(name = "OUTCOME_DATE")
    private Timestamp outcomeDate;
    @Column(name = "OFFENDER_PROCEEDING_ID")
    private Long offenderProceedingId;
    @Column(name = "DIRECTION_CODE")
    private String directionCode;
    @Column(name = "HOLD_FLAG")
    private String holdFlag;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;

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

    @OneToMany
    @BatchSize(size = 1000)
    @JoinColumn(name = "EVENT_ID")
    private List<CourtEventCharge> courtEventCharges;

    @OneToOne
    @JoinColumn(name = "AGY_LOC_ID")
    private AgencyLocation agencyLocation;
}
