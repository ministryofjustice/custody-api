package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class OffenderIdentifierPK implements Serializable {
    @Column(name = "OFFENDER_ID")
    @Id
    private Long offenderId;

    @Column(name = "OFFENDER_ID_SEQ")
    @Id
    private Long offenderIdSeq;

}
