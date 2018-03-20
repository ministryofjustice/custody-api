package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("OFF_EDU")
public class OffenderEducationAddress extends Address {

    public Long getOffenderBookId() {
        return getOwnerId();
    }

    @Override
    public String getType() {
        return "offender education";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("offenderBookId").value(getOffenderBookId()).build();
    }
}
