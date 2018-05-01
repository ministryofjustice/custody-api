package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("ADDR")
public class AddressPhone extends Phone{
    @Override
    public String getType() {
        return "address";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("addressId").value(getOwnerId()).build();
    }
}
