package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class CourtEventChargePK implements Serializable {
    @Column(name = "EVENT_ID")
    @Id
    private Long eventId;
    @Column(name = "OFFENDER_CHARGE_ID")
    @Id
    private Long offenderChargeId;

}
