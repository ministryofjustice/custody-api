package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AgencyLocation {
    private String agencyLocationId;
    private String description;
    private String longDescription;
    private String agencyLocationType;
    private String districtCode;
    private String abbreviation;
    private LocalDate deactivationDate;
    private String contactName;
    private String jurisdictionCode;
    private Boolean bailOffice;
    private Integer listSeq;
    private String housingLev1Code;
    private String housingLev2Code;
    private String housingLev3Code;
    private String housingLev4Code;
    private String propertyLev1Code;
    private String propertyLev2Code;
    private String propertyLev3Code;
    private String commissaryPrivilege;
    private String businessHours;
    private String addressType;
    private Boolean serviceRequired;
    private Boolean active;
    private String disabilityAccessCode;
    private String subAreaCode;
    private String areaCode;
    private String nomsRegionCode;
    private String geographicRegionCode;
    private String justiceAreaCode;
    private String cjitCode;

}
