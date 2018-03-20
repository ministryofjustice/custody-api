package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("OFF")
@Data
public class OffenderAddress extends Address {

    public Long getOffenderId() {
        return getOwnerId();
    }

    @Override
    public String getType() {
        return "offender";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("offenderId").value(getOffenderId()).build();
    }
}
