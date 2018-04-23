package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class OffenderProfileDetailsPK implements Serializable {
    @Column(name = "OFFENDER_BOOK_ID")
    @Id
    private Long offenderBookId;
    @Column(name = "PROFILE_SEQ")
    @Id
    private Long profileSeq;
    @Column(name = "PROFILE_TYPE")
    @Id
    private String profileType;

}
