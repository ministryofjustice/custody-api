package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("CORP")
public class CorporateAddress extends Address {

    public Long getCorporateId() {
        return getOwnerId();
    }

    @Override
    public String getType() {
        return "corporate";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("corporateId").value(getCorporateId()).build();
    }
}
