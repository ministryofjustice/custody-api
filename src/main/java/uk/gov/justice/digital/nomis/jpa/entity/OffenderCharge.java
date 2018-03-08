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
@Table(name = "OFFENDER_CHARGES")
public class OffenderCharge {
    @Id
    @Column(name = "OFFENDER_CHARGE_ID")
    private Long offenderChargeId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "NO_OF_OFFENCES")
    private Integer noOfOffences;
    @Column(name = "OFFENCE_DATE")
    private Timestamp offenceDate;
    @Column(name = "OFFENCE_RANGE_DATE")
    private Timestamp offenceRangeDate;
    @Column(name = "PLEA_CODE")
    private String pleaCode;
    @Column(name = "PROPERTY_VALUE")
    private BigDecimal propertyValue;
    @Column(name = "TOTAL_PROPERTY_VALUE")
    private BigDecimal totalPropertyValue;
    @Column(name = "CJIT_OFFENCE_CODE_1")
    private String cjitOffenceCode1;
    @Column(name = "CJIT_OFFENCE_CODE_2")
    private String cjitOffenceCode2;
    @Column(name = "CJIT_OFFENCE_CODE_3")
    private String cjitOffenceCode3;
    @Column(name = "CHARGE_STATUS")
    private String chargeStatus;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "RESULT_CODE_1_INDICATOR")
    private String resultCode1Indicator;
    @Column(name = "RESULT_CODE_2_INDICATOR")
    private String resultCode2Indicator;
    @Column(name = "MOST_SERIOUS_FLAG")
    private String mostSeriousFlag;
    @Column(name = "CHARGE_SEQ")
    private Integer chargeSeq;
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
    @Column(name = "LIDS_OFFENCE_NUMBER")
    private Integer lidsOffenceNumber;
    @OneToOne
    @JoinColumn(name = "CASE_ID", referencedColumnName = "CASE_ID", nullable = false)
    private OffenderCase offenderCase;
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "OFFENCE_CODE", insertable = false),
            @JoinColumn(name = "STATUTE_CODE", insertable = false)
    })
    private Offence offence;
    @OneToOne
    @JoinColumn(name = "RESULT_CODE_1", referencedColumnName = "RESULT_CODE")
    private OffenceResultCode offenceResult1;
    @OneToOne
    @JoinColumn(name = "RESULT_CODE_2", referencedColumnName = "RESULT_CODE")
    private OffenceResultCode offenceResult2;
    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;


}
