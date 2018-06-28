package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Address {
    private Long addressId;
    private String type;

    @JsonIgnore
    private IdPair relationship;
    @JsonAnyGetter
    private Map<String, Object> serializeRelationship() {
        return relationship.asMap();
    }

    private String addressType;
    private List<AddressUsage> addressUsages;
    private String ownerClass;
    private Integer ownerSeq;
    private String flat;
    private String premise;
    private String street;
    private String locality;
    private String cityName;
    private String cityCode;
    private String countyCode;
    private String postalCode;
    private String countryCode;
    private Boolean validatedPaf;
    private Boolean primary;
    private Boolean mail;
    private Integer capacity;
    private String comments;
    private Boolean noFixedAddress;
    private Boolean services;
    private String specialNeedsCode;
    private String contactPersonName;
    private String businessHour;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
    private List<Phone> phones;
}
