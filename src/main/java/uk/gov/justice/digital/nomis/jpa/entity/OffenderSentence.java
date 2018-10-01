package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@ToString(exclude = "offenderBooking")
@Entity
@Table(name = "OFFENDER_SENTENCES")
@IdClass(OffenderSentencePk.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffenderSentence {

    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookingId;

    @Id
    @Column(name = "SENTENCE_SEQ")
    private Integer sentenceSeq;

    @Column(name = "SENTENCE_STATUS")
    private String sentenceStatus;
    @Column(name = "CONSEC_TO_SENTENCE_SEQ")
    private Integer consecToSentenceSeq;
    @Column(name = "START_DATE")
    private Timestamp startDate;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "TERMINATION_REASON")
    private String terminationReason;
    @Column(name = "NO_OF_UNEXCUSED_ABSENCE")
    private Integer noOfUnexcusedAbsence;
    @Column(name = "ETD_CALCULATED_DATE")
    private Timestamp etdCalculatedDate;
    @Column(name = "MTD_CALCULATED_DATE")
    private Timestamp mtdCalculatedDate;
    @Column(name = "LTD_CALCULATED_DATE")
    private Timestamp ltdCalculatedDate;
    @Column(name = "ARD_CALCULATED_DATE")
    private Timestamp ardCalculatedDate;
    @Column(name = "CRD_CALCULATED_DATE")
    private Timestamp crdCalculatedDate;
    @Column(name = "PED_CALCULATED_DATE")
    private Timestamp pedCalculatedDate;
    @Column(name = "APD_CALCULATED_DATE")
    private Timestamp apdCalculatedDate;
    @Column(name = "NPD_CALCULATED_DATE")
    private Timestamp npdCalculatedDate;
    @Column(name = "LED_CALCULATED_DATE")
    private Timestamp ledCalculatedDate;
    @Column(name = "SED_CALCULATED_DATE")
    private Timestamp sedCalculatedDate;
    @Column(name = "PRRD_CALCULATED_DATE")
    private Timestamp prrdCalculatedDate;
    @Column(name = "TARIFF_CALCULATED_DATE")
    private Timestamp tariffCalculatedDate;
    @Column(name = "AGG_SENTENCE_SEQ")
    private Integer aggSentenceSeq;
    @Column(name = "SENTENCE_CATEGORY")
    private String sentenceCategory;
    @Column(name = "FINE_AMOUNT")
    private BigDecimal fineAmount;
    @Column(name = "HDCED_CALCULATED_DATE")
    private Timestamp hdcedCalculatedDate;
    @Column(name = "SENTENCE_TEXT")
    private String sentenceText;
    @Column(name = "REVOKED_DATE")
    private Timestamp revokedDate;
    @Column(name = "REVOKED_STAFF_ID")
    private Integer revokedStaffId;
    @Column(name = "BREACH_LEVEL")
    private Integer breachLevel;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "TERMINATION_DATE")
    private Timestamp terminationDate;
    @Column(name = "AGGREGATE_TERM")
    private Integer aggregateTerm;
    @Column(name = "AGGREGATE_ADJUST_DAYS")
    private Integer aggregateAdjustDays;
    @Column(name = "SENTENCE_LEVEL")
    private String sentenceLevel;
    @Column(name = "EXTENDED_DAYS")
    private Integer extendedDays;
    @Column(name = "COUNTS")
    private Integer counts;
    @Column(name = "DISCHARGE_DATE")
    private Timestamp dischargeDate;
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
    @Column(name = "STATUS_UPDATE_REASON")
    private String statusUpdateReason;
    @Column(name = "STATUS_UPDATE_COMMENT")
    private String statusUpdateComment;
    @Column(name = "STATUS_UPDATE_DATE")
    private Timestamp statusUpdateDate;
    @Column(name = "STATUS_UPDATE_STAFF_ID")
    private Integer statusUpdateStaffId;
    @Column(name = "NOMSENTDETAILREF")
    private Integer nomsentdetailref;
    @Column(name = "NOMCONSTOSENTDETAILREF")
    private Integer nomconstosentdetailref;
    @Column(name = "NOMCONSFROMSENTDETAILREF")
    private Integer nomconsfromsentdetailref;
    @Column(name = "NOMCONCWITHSENTDETAILREF")
    private Integer nomconcwithsentdetailref;
    @Column(name = "WORKFLOW_ID")
    private Long workflowId;
    @Column(name = "LINE_SEQ")
    private Integer lineSeq;
    @Column(name = "HDC_EXCLUSION_FLAG")
    private String hdcExclusionFlag;
    @Column(name = "HDC_EXCLUSION_REASON")
    private String hdcExclusionReason;
    @Column(name = "CJA_ACT")
    private String cjaAct;
    @Column(name = "DPRRD_CALCULATED_DATE")
    private Timestamp dprrdCalculatedDate;
    @Column(name = "START_DATE_2CALC")
    private Timestamp startDate2Calc;
    @Column(name = "SLED_2CALC")
    private Timestamp sled2Calc;
    @Column(name = "TUSED_CALCULATED_DATE")
    private Timestamp tusedCalculatedDate;
    @Column(name = "SENTENCE_CALC_TYPE")
    private String sentenceCalcType;
    @Column(name = "CASE_ID")
    private Long caseId;

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "OFFENDER_BOOK_ID", insertable = false, updatable = false)
    private OffenderBooking offenderBooking;
}
