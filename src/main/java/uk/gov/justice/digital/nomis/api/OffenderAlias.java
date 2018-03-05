package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OffenderAlias {
    private String firstName;
    private String middleNames;
    private String surname;
    private LocalDate dateOfBirth;
    private Long offenderId;
    private List<Identifier> identifiers;
    private String nomsId;

}
