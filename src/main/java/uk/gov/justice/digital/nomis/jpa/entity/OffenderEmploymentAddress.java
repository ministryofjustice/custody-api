package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("OFF_EMP")
public class OffenderEmploymentAddress extends Address {

    public Long getOffenderBookId() {
        return getOwnerId();
    }

    @Override
    public String getType() {
        return "offender employment";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("bookingId").value(getOffenderBookId()).build();
    }
}
