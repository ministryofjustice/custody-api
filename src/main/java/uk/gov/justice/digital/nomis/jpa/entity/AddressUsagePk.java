package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class AddressUsagePk implements Serializable {
    @Id
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    @Id
    @Column(name = "ADDRESS_USAGE")
    private String addressUsage;

}
