package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentenceCalculationType {
    private String sentenceCategory;
    private String sentenceCalculationType;
    private String sentenceType;
    private String description;
    private LocalDateTime expiryDate;
    private Boolean active;
    private String ProgramMethod;
    private String functionType;
}
