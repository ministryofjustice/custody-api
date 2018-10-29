package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"staffId", "type", "identificationNumber"})
@Embeddable
@ToString(of = {"staffId", "type", "identificationNumber"})
public class StaffIdentifierIdentity implements Serializable {

    @Column(name = "STAFF_ID", nullable = false)
    private Long staffId;

    @Column(name = "IDENTIFICATION_TYPE", nullable = false)
    private String type;

    @Column(name = "IDENTIFICATION_NUMBER", nullable = false)
    private String identificationNumber;


}
