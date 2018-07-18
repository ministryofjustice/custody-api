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
@Table(name = "SENTENCE_CALC_TYPES")
@IdClass(SentenceCalcTypePK.class)
public class SentenceCalcType {
    @Id
    @Column(name = "SENTENCE_CATEGORY")
    private String sentenceCategory;
    @Id
    @Column(name = "SENTENCE_CALC_TYPE")
    private String sentenceCalcType;

    @Column(name = "SENTENCE_TYPE")
    private String sentenceType;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "PROGRAM_METHOD")
    private String ProgramMethod;
    @Column(name = "HEADER_SEQ")
    private Integer headerSeq;
    @Column(name = "HEADER_LABEL")
    private String headerLabel;
    @Column(name = "FUNCTION_TYPE")
    private String functionType;
    @Column(name = "REORDER_SENTENCE_SEQ")
    private Integer reorderSentenceSeq;

    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
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
}
