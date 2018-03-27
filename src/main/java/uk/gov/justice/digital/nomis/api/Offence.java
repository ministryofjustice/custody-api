package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Offence {
    private String offenceCode;
    private String description;
    private KeyValue statute;
    private KeyValue oldStatute;
    private String defaultOffenceType;
    private Integer maxSentenceLength;
    private String sentenceUnitCode;
    private String offenceGroup;
    private LocalDate repealedDate;
    private Boolean active;
    private Integer listSeq;
    private LocalDate expiryDate;
    private String offenseDegree;
    private Integer maxGoodTimePerc;
    private KeyValue hoCode;
    private List<String> offenceIndicators;
}
