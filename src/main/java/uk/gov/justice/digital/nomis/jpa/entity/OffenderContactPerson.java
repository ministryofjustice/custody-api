package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_CONTACT_PERSONS")
public class OffenderContactPerson {
    @Id
    @Column(name = "OFFENDER_CONTACT_PERSON_ID")
    private Long offenderContactPersonId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "RELATIONSHIP_TYPE")
    private String relationshipType;
    @Column(name = "CONTACT_TYPE")
    private String contactType;
    @Column(name = "APPROVED_VISITOR_FLAG")
    private String approvedVisitorFlag;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "CASE_INFO_NUMBER")
    private String caseInfoNumber;
    @Column(name = "AWARE_OF_CHARGES_FLAG")
    private String awareOfChargesFlag;
    @Column(name = "CAN_BE_CONTACTED_FLAG")
    private String canBeContactedFlag;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "EMERGENCY_CONTACT_FLAG")
    private String emergencyContactFlag;
    @Column(name = "NEXT_OF_KIN_FLAG")
    private String nextOfKinFlag;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "CONTACT_ROOT_OFFENDER_ID")
    private Long contactRootOffenderId;
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
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    @OneToOne
    @JoinColumns(
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            value = {
                    @JoinColumn(name = "CONTACT_TYPE", referencedColumnName = "CONTACT_TYPE", insertable = false, updatable = false),
                    @JoinColumn(name = "RELATIONSHIP_TYPE", referencedColumnName = "RELATIONSHIP_TYPE", insertable = false, updatable = false)

            })
    private ContactPersonType contactPersonType;

}
