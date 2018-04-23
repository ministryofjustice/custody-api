package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_PROFILE_DETAILS")
@IdClass(OffenderProfileDetailsPK.class)
public class OffenderProfileDetails {
    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Id
    @Column(name = "PROFILE_SEQ")
    private Long profileSeq;
    @Id
    @Column(name = "PROFILE_TYPE")
    private String profileType;
    @Column(name = "PROFILE_CODE")
    private String profileCode;
    @Column(name = "LIST_SEQ")
    private Long listSeq;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
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
