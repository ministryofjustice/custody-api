package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentenceCalcTypePK implements Serializable {
    @Id
    @Column(name = "SENTENCE_CATEGORY")
    private String sentenceCategory;
    @Id
    @Column(name = "SENTENCE_CALC_TYPE")
    private String sentenceCalcType;

}
