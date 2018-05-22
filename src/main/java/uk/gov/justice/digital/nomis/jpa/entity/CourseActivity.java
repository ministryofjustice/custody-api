package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "COURSE_ACTIVITIES")
public class CourseActivity {
    @Id
    @Column(name = "CRS_ACTY_ID")
    private Long crsActyId;
    @Column(name = "CASELOAD_ID")
    private String caseloadId;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "SCHEDULE_START_DATE")
    private Timestamp scheduleStartDate;
    @Column(name = "SCHEDULE_END_DATE")
    private Timestamp scheduleEndDate;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "SERVICES_ADDRESS_ID")
    private Integer servicesAddressId;
    @Column(name = "PROGRAM_ID")
    private Integer programId;
    @Column(name = "PARENT_CRS_ACTY_ID")
    private Integer parentCrsActyId;
    @Column(name = "INTERNAL_LOCATION_ID")
    private Integer internalLocationId;
    @Column(name = "PROVIDER_PARTY_CLASS")
    private String providerPartyClass;
    @Column(name = "PROVIDER_PARTY_ID")
    private Integer providerPartyId;
    @Column(name = "PROVIDER_PARTY_CODE")
    private String providerPartyCode;
    @Column(name = "BENEFICIARY_NAME")
    private String beneficiaryName;
    @Column(name = "BENEFICIARY_CONTACT")
    private String beneficiaryContact;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "PLACEMENT_CORPORATE_ID")
    private Integer placementCorporateId;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "AGENCY_LOCATION_TYPE")
    private String agencyLocationType;
    @Column(name = "PROVIDER_TYPE")
    private String providerType;
    @Column(name = "BENEFICIARY_TYPE")
    private String beneficiaryType;
    @Column(name = "PLACEMENT_TEXT")
    private String placementText;
    @Column(name = "CODE")
    private String code;
    @Column(name = "HOLIDAY_FLAG")
    private String holidayFlag;
    @Column(name = "COURSE_CLASS")
    private String courseClass;
    @Column(name = "COURSE_ACTIVITY_TYPE")
    private String courseActivityType;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "IEP_LEVEL")
    private String iepLevel;
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
    @Column(name = "NO_OF_SESSIONS")
    private Integer noOfSessions;
    @Column(name = "SESSION_LENGTH")
    private Integer sessionLength;
    @Column(name = "MULTI_PHASE_SCHEDULING_FLAG")
    private String multiPhaseSchedulingFlag;
    @Column(name = "SCHEDULE_NOTES")
    private String scheduleNotes;
    @Column(name = "OUTSIDE_WORK_FLAG")
    private String outsideWorkFlag;
    @Column(name = "PAY_PER_SESSION")
    private String payPerSession;
    @Column(name = "PIECE_WORK_FLAG")
    private String pieceWorkFlag;

    @OneToMany
    @BatchSize(size = 1000)
    @JoinColumn(name = "CRS_ACTY_ID", referencedColumnName = "CRS_ACTY_ID")
    private List<CourseSchedule> courseSchedules;

}
