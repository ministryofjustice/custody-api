package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class OffencePK implements Serializable {
    @Column(name = "OFFENCE_CODE")
    @Id
    private String offenceCode;
    @Column(name = "STATUTE_CODE")
    @Id
    private String statuteCode;

}
