package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class MovementReasonPK implements Serializable {
    @Column(name = "MOVEMENT_TYPE")
    @Id
    private String movementType;


    @Column(name = "MOVEMENT_REASON_CODE")
    @Id
    private String movementReasonCode;

}
