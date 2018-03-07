package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OFFENDER_BOOKINGS")
public class OffenderBooking {
    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "BOOKING_BEGIN_DATE")
    private Timestamp bookingBeginDate;
    @Column(name = "BOOKING_END_DATE")
    private Timestamp bookingEndDate;
    @Column(name = "BOOKING_NO")
    private String bookingNo;
    @Column(name = "OFFENDER_ID")
    private Long offenderId;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "LIVING_UNIT_ID")
    private Long livingUnitId;
    @Column(name = "DISCLOSURE_FLAG")
    private String disclosureFlag;
    @Column(name = "IN_OUT_STATUS")
    private String inOutStatus;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "BOOKING_STATUS")
    private String bookingStatus;
    @Column(name = "YOUTH_ADULT_CODE")
    private String youthAdultCode;
    @Column(name = "FINGER_PRINTED_STAFF_ID")
    private Long fingerPrintedStaffId;
    @Column(name = "SEARCH_STAFF_ID")
    private Long searchStaffId;
    @Column(name = "PHOTO_TAKING_STAFF_ID")
    private Long photoTakingStaffId;
    @Column(name = "ASSIGNED_STAFF_ID")
    private Long assignedStaffId;
    @Column(name = "CREATE_AGY_LOC_ID")
    private String createAgyLocId;
    @Column(name = "BOOKING_TYPE")
    private String bookingType;
    @Column(name = "BOOKING_CREATED_DATE")
    private Timestamp bookingCreatedDate;
    @Column(name = "ROOT_OFFENDER_ID")
    private Long rootOffenderId;
    @Column(name = "AGENCY_IML_ID")
    private Long agencyImlId;
    @Column(name = "SERVICE_FEE_FLAG")
    private String serviceFeeFlag;
    @Column(name = "EARNED_CREDIT_LEVEL")
    private String earnedCreditLevel;
    @Column(name = "EKSTRAND_CREDIT_LEVEL")
    private String ekstrandCreditLevel;
    @Column(name = "INTAKE_AGY_LOC_ID")
    private String intakeAgyLocId;
    @Column(name = "ACTIVITY_DATE")
    private Timestamp activityDate;
    @Column(name = "INTAKE_CASELOAD_ID")
    private String intakeCaseloadId;
    @Column(name = "INTAKE_USER_ID")
    private String intakeUserId;
    @Column(name = "CASE_OFFICER_ID")
    private Long caseOfficerId;
    @Column(name = "CASE_DATE")
    private Timestamp caseDate;
    @Column(name = "CASE_TIME")
    private Timestamp caseTime;
    @Column(name = "COMMUNITY_ACTIVE_FLAG")
    private String communityActiveFlag;
    @Column(name = "CREATE_INTAKE_AGY_LOC_ID")
    private String createIntakeAgyLocId;
    @Column(name = "COMM_STAFF_ID")
    private Long commStaffId;
    @Column(name = "COMM_STATUS")
    private String commStatus;
    @Column(name = "COMMUNITY_AGY_LOC_ID")
    private String communityAgyLocId;
    @Column(name = "NO_COMM_AGY_LOC_ID")
    private Long noCommAgyLocId;
    @Column(name = "COMM_STAFF_ROLE")
    private String commStaffRole;
    @Column(name = "AGY_LOC_ID_LIST")
    private String agyLocIdList;
    @Column(name = "STATUS_REASON")
    private String statusReason;
    @Column(name = "TOTAL_UNEXCUSED_ABSENCES")
    private Long totalUnexcusedAbsences;
    @Column(name = "REQUEST_NAME")
    private String requestName;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "RECORD_USER_ID")
    private String recordUserId;
    @Column(name = "INTAKE_AGY_LOC_ASSIGN_DATE")
    private Timestamp intakeAgyLocAssignDate;
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
    @Column(name = "BOOKING_SEQ")
    private Long bookingSeq;

    @OneToMany
    @JoinColumn(name = "OFFENDER_BOOK_ID")
    private List<OffenderExternalMovement> offenderExternalMovements;

}
