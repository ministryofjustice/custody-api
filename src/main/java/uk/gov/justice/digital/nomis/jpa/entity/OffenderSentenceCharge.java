package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
//@Entity
//@Table(name = "OFFENDER_SENTENCE_CHARGES")
//@IdClass(OffenderSentenceChargePK.class)
public class OffenderSentenceCharge {
    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Id
    @Column(name = "SENTENCE_SEQ")
    private Long sentenceSeq;
    @Id
    @Column(name = "OFFENDER_CHARGE_ID")
    private Long offenderChargeId;
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

}
