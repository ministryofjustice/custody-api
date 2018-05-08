package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class OffenderSentenceChargePK implements Serializable {
    @Column(name = "OFFENDER_BOOK_ID")
    @Id
    private Long offenderBookId;
    @Column(name = "SENTENCE_SEQ")
    @Id
    private Long sentenceSeq;
    @Column(name = "OFFENDER_CHARGE_ID")
    @Id
    private Long offenderChargeId;

}
