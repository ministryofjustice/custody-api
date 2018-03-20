package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESSES")
@Inheritance
@DiscriminatorColumn(name = "OWNER_CLASS") // OFF,PER,CORP,STF,OFF_EDU,OFF_EMP,AGY,PER_EMP,ADDR
public abstract class Address {
    @Id
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    @Column(name = "OWNER_CLASS", insertable = false, updatable = false)
    private String ownerClass;
    @Column(name = "OWNER_ID")
    private Long ownerId;
    @Column(name = "OWNER_SEQ")
    private Integer ownerSeq;
    @Column(name = "OWNER_CODE")
    private String ownerCode;
    @Column(name = "ADDRESS_TYPE")
    private String addressType;
    @Column(name = "FLAT")
    private String flat;
    @Column(name = "PREMISE")
    private String premise;
    @Column(name = "STREET")
    private String street;
    @Column(name = "LOCALITY")
    private String locality;
    @Column(name = "CITY_CODE")
    private String cityCode;
    @Column(name = "COUNTY_CODE")
    private String countyCode;
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    @Column(name = "VALIDATED_PAF_FLAG")
    private String validatedPafFlag;
    @Column(name = "PRIMARY_FLAG")
    private String primaryFlag;
    @Column(name = "MAIL_FLAG")
    private String mailFlag;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "NO_FIXED_ADDRESS_FLAG")
    private String noFixedAddressFlag;
    @Column(name = "SERVICES_FLAG")
    private String servicesFlag;
    @Column(name = "SPECIAL_NEEDS_CODE")
    private String specialNeedsCode;
    @Column(name = "CONTACT_PERSON_NAME")
    private String contactPersonName;
    @Column(name = "BUSINESS_HOUR")
    private String businessHour;
    @Column(name = "START_DATE")
    private Timestamp startDate;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    @Column(name = "CITY_NAME")
    private String cityName;
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

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private AddressUsage addressUsage;

    public abstract String getType();

    public abstract IdPair getRelationship();
}
