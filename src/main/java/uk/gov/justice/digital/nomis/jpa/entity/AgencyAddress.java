package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("AGY")
public class AgencyAddress extends Address {

    public String getAgencyLocationId() {
        return getOwnerCode();
    }

    @Override
    public String getType() {
        return "agency";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("agencyLocationId").value(getAgencyLocationId()).build();
    }
}
