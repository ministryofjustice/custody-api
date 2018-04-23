package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_SENT_CALCULATIONS")
public class OffenderSentCalculation {
    @Id
    @Column(name = "OFFENDER_SENT_CALCULATION_ID")
    private Long offenderSentCalculationId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "CALCULATION_DATE")
    private Timestamp calculationDate;
    @Column(name = "STAFF_ID")
    private Long staffId;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "HDCED_CALCULATED_DATE")
    private Timestamp hdcedCalculatedDate;
    @Column(name = "HDCED_OVERRIDED_DATE")
    private Timestamp hdcedOverridedDate;
    @Column(name = "HDCAD_CALCULATED_DATE")
    private Timestamp hdcadCalculatedDate;
    @Column(name = "HDCAD_OVERRIDED_DATE")
    private Timestamp hdcadOverridedDate;
    @Column(name = "ETD_CALCULATED_DATE")
    private Timestamp etdCalculatedDate;
    @Column(name = "ETD_OVERRIDED_DATE")
    private Timestamp etdOverridedDate;
    @Column(name = "MTD_CALCULATED_DATE")
    private Timestamp mtdCalculatedDate;
    @Column(name = "MTD_OVERRIDED_DATE")
    private Timestamp mtdOverridedDate;
    @Column(name = "LTD_CALCULATED_DATE")
    private Timestamp ltdCalculatedDate;
    @Column(name = "LTD_OVERRIDED_DATE")
    private Timestamp ltdOverridedDate;
    @Column(name = "ARD_CALCULATED_DATE")
    private Timestamp ardCalculatedDate;
    @Column(name = "ARD_OVERRIDED_DATE")
    private Timestamp ardOverridedDate;
    @Column(name = "CRD_CALCULATED_DATE")
    private Timestamp crdCalculatedDate;
    @Column(name = "CRD_OVERRIDED_DATE")
    private Timestamp crdOverridedDate;
    @Column(name = "PED_CALCULATED_DATE")
    private Timestamp pedCalculatedDate;
    @Column(name = "PED_OVERRIDED_DATE")
    private Timestamp pedOverridedDate;
    @Column(name = "APD_CALCULATED_DATE")
    private Timestamp apdCalculatedDate;
    @Column(name = "APD_OVERRIDED_DATE")
    private Timestamp apdOverridedDate;
    @Column(name = "NPD_CALCULATED_DATE")
    private Timestamp npdCalculatedDate;
    @Column(name = "NPD_OVERRIDED_DATE")
    private Timestamp npdOverridedDate;
    @Column(name = "LED_CALCULATED_DATE")
    private Timestamp ledCalculatedDate;
    @Column(name = "LED_OVERRIDED_DATE")
    private Timestamp ledOverridedDate;
    @Column(name = "SED_CALCULATED_DATE")
    private Timestamp sedCalculatedDate;
    @Column(name = "SED_OVERRIDED_DATE")
    private Timestamp sedOverridedDate;
    @Column(name = "PRRD_CALCULATED_DATE")
    private Timestamp prrdCalculatedDate;
    @Column(name = "PRRD_OVERRIDED_DATE")
    private Timestamp prrdOverridedDate;
    @Column(name = "TARIFF_CALCULATED_DATE")
    private Timestamp tariffCalculatedDate;
    @Column(name = "TARIFF_OVERRIDED_DATE")
    private Timestamp tariffOverridedDate;
    @Column(name = "CALC_REASON_CODE")
    private String calcReasonCode;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "RECORD_DATETIME")
    private Timestamp recordDatetime;
    @Column(name = "RECORD_USER_ID")
    private String recordUserId;
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
    @Column(name = "EFFECTIVE_SENTENCE_END_DATE")
    private Timestamp effectiveSentenceEndDate;
    @Column(name = "HDC_ELIGIBLE_WF")
    private String hdcEligibleWf;
    @Column(name = "WORKFLOW_HISTORY_ID")
    private Long workflowHistoryId;
    @Column(name = "EFFECTIVE_SENTENCE_LENGTH")
    private String effectiveSentenceLength;
    @Column(name = "JUDICIALLY_IMPOSED_SENT_LENGTH")
    private String judiciallyImposedSentLength;
    @Column(name = "DPRRD_CALCULATED_DATE")
    private Timestamp dprrdCalculatedDate;
    @Column(name = "DPRRD_OVERRIDED_DATE")
    private Timestamp dprrdOverridedDate;
    @Column(name = "ERSED_OVERRIDED_DATE")
    private Timestamp ersedOverridedDate;
    @Column(name = "TERSED_OVERRIDED_DATE")
    private Timestamp tersedOverridedDate;
    @Column(name = "ROTL_OVERRIDED_DATE")
    private Timestamp rotlOverridedDate;
    @Column(name = "TUSED_CALCULATED_DATE")
    private Timestamp tusedCalculatedDate;
    @Column(name = "TUSED_OVERRIDED_DATE")
    private Timestamp tusedOverridedDate;

}
