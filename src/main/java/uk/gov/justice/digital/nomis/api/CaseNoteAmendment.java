package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class CaseNoteAmendment {
    private String text;
    private LocalDateTime creationDateTime;
    private String authorName;
}
