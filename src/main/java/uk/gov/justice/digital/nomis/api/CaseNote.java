package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CaseNote {
    private Long caseNoteId;
    private Long offenderBookId;
    private Long eventId;

    private LocalDateTime creationDateTime;
    private LocalDateTime contactDateTime;
    private KeyValue type;
    private KeyValue subType;
    private String text;
    private Boolean isAmendment;
    private Boolean isIwp;

    private Long staffId;
    private KeyValue source;

    private List<CaseNoteAmendment> amendments;
}

