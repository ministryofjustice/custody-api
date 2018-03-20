package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("PER_EMP")
public class PersonEmploymentAddress extends Address {

    public Long getPersonId() {
        return getOwnerId();
    }

    @Override
    public String getType() {
        return "person employment";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("personId").value(getPersonId()).build();
    }
}
