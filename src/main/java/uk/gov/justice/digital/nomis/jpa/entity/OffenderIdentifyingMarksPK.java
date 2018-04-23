package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class OffenderIdentifyingMarksPK implements Serializable {
    @Column(name = "OFFENDER_BOOK_ID")
    @Id
    private Long offenderBookId;
    @Column(name = "ID_MARK_SEQ")
    @Id
    private Long idMarkSeq;

}
