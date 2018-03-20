package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.gov.justice.digital.nomis.api.IdPair;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("STF")
public class StaffAddress extends Address {

    public Long getStaffId() {
        return getOwnerId();
    }

    @Override
    public String getType() {
        return "staff";
    }

    @Override
    public IdPair getRelationship() {
        return IdPair.builder().key("staffId").value(getStaffId()).build();
    }
}
