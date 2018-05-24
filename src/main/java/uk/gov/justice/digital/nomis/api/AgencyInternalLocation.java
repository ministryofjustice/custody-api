package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AgencyInternalLocation {
    private Long internalLocationId;
    private String internalLocationCode;
    private String agencyLocationId;
    private String internalLocationType;
    private String description;
    private String securityLevelCode;
    private Integer capacity;
    private Long parentInternalLocationId;
    private Boolean active;
    private Integer listSequence;
    private Long cnaNumber;
    private Boolean certified;
    private LocalDate deactivateDate;
    private LocalDate reactivateDate;
    private String deactivateReasonCode;
    private String comments;
    private String userDesc;
    private Long acaCapRating;
    private String unitType;
    private Integer operationCapacity;
    private Integer numberOfOccupants;
    private String trackingFlag;

}
