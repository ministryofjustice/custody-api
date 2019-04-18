package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EarliestSentenceAndConvictionCDE {
    private final CourtEvent earliestConviction;
    private final Sentence earliestSentence;
}
