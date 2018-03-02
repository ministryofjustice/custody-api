package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OFFENDER_IDENTIFIERS")
@IdClass(OffenderIdentifierPK.class)
public class OffenderIdentifier {
    @Id
    @Column(name = "OFFENDER_ID")
    private Long offenderId;
    @Id
    @Column(name = "OFFENDER_ID_SEQ")
    private Long offenderIdSeq;
    @Column(name = "IDENTIFIER_TYPE")
    private String identifierType;
    @Column(name = "IDENTIFIER")
    private String identifier;
    @Column(name = "ISSUED_AUTHORITY_TEXT")
    private String issuedAuthorityText;
    @Column(name = "ISSUED_DATE")
    private Timestamp issuedDate;
    @Column(name = "ROOT_OFFENDER_ID")
    private Long rootOffenderId;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "VERIFIED_FLAG")
    private String verifiedFlag;
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
