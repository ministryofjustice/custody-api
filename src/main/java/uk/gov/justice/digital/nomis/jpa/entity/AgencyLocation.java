package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "AGENCY_LOCATIONS")
public class AgencyLocation {
    @Id
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "AGENCY_LOCATION_TYPE")
    private String agencyLocationType;
    @Column(name = "DISTRICT_CODE")
    private String districtCode;
    @Column(name = "UPDATED_ALLOWED_FLAG")
    private String updatedAllowedFlag;
    @Column(name = "ABBREVIATION")
    private String abbreviation;
    @Column(name = "DEACTIVATION_DATE")
    private Timestamp deactivationDate;
    @Column(name = "CONTACT_NAME")
    private String contactName;
    @Column(name = "PRINT_QUEUE")
    private String printQueue;
    @Column(name = "JURISDICTION_CODE")
    private String jurisdictionCode;
    @Column(name = "BAIL_OFFICE_FLAG")
    private String bailOfficeFlag;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "HOUSING_LEV_1_CODE")
    private String housingLev1Code;
    @Column(name = "HOUSING_LEV_2_CODE")
    private String housingLev2Code;
    @Column(name = "HOUSING_LEV_3_CODE")
    private String housingLev3Code;
    @Column(name = "HOUSING_LEV_4_CODE")
    private String housingLev4Code;
    @Column(name = "PROPERTY_LEV_1_CODE")
    private String propertyLev1Code;
    @Column(name = "PROPERTY_LEV_2_CODE")
    private String propertyLev2Code;
    @Column(name = "PROPERTY_LEV_3_CODE")
    private String propertyLev3Code;
    @Column(name = "LAST_BOOKING_NO")
    private Integer lastBookingNo;
    @Column(name = "COMMISSARY_PRIVILEGE")
    private String commissaryPrivilege;
    @Column(name = "BUSINESS_HOURS")
    private String businessHours;
    @Column(name = "ADDRESS_TYPE")
    private String addressType;
    @Column(name = "SERVICE_REQUIRED_FLAG")
    private String serviceRequiredFlag;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "DISABILITY_ACCESS_CODE")
    private String disabilityAccessCode;
    @Column(name = "INTAKE_FLAG")
    private String intakeFlag;
    @Column(name = "SUB_AREA_CODE")
    private String subAreaCode;
    @Column(name = "AREA_CODE")
    private String areaCode;
    @Column(name = "NOMS_REGION_CODE")
    private String nomsRegionCode;
    @Column(name = "GEOGRAPHIC_REGION_CODE")
    private String geographicRegionCode;
    @Column(name = "JUSTICE_AREA_CODE")
    private String justiceAreaCode;
    @Column(name = "CJIT_CODE")
    private String cjitCode;
    @Column(name = "LONG_DESCRIPTION")
    private String longDescription;

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
