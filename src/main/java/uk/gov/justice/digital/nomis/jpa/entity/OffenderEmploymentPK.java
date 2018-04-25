package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class OffenderEmploymentPK implements Serializable {
    @Column(name = "OFFENDER_BOOK_ID")
    @Id
    private Long offenderBookId;
    @Column(name = "EMPLOY_SEQ")
    @Id
    private Long employSeq;

}
