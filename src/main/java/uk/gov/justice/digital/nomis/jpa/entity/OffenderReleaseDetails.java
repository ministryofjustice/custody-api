package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OFFENDER_RELEASE_DETAILS")
public class OffenderReleaseDetails {
    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "RELEASE_DATE")
    private Timestamp releaseDate;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "MOVEMENT_TYPE")
    private String movementType;
    @Column(name = "MOVEMENT_REASON_CODE")
    private String movementReasonCode;
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
    @Column(name = "EVENT_ID")
    private Long eventId;
    @Column(name = "EVENT_STATUS")
    private String eventStatus;
    @Column(name = "APPROVED_RELEASE_DATE")
    private Timestamp approvedReleaseDate;
    @Column(name = "AUTO_RELEASE_DATE")
    private Timestamp autoReleaseDate;
    @Column(name = "DTO_APPROVED_DATE")
    private Timestamp dtoApprovedDate;
    @Column(name = "DTO_MID_TERM_DATE")
    private Timestamp dtoMidTermDate;
    @Column(name = "VERIFIED_FLAG")
    private String verifiedFlag;

}
