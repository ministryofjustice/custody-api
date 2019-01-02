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
public class Identifier {
    private String identifierType;
    private String identifier;
    private Long sequenceNumber;
    private LocalDateTime createdDateTime;
}
