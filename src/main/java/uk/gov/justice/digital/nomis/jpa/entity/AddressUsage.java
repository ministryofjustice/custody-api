package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS_USAGE")
public class AddressUsage {
    @Id
    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "ADDRESS_USAGE")
    private String addressUsage;
}
