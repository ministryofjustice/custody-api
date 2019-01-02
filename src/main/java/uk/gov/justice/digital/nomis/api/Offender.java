package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Relation(collectionRelation = "offenders")
@NoArgsConstructor
@AllArgsConstructor
public class Offender {
    private String nomsId;
    private Long offenderId;
    private String firstName;
    private String middleNames;
    private String surname;
    private LocalDate dateOfBirth;
    private KeyValue gender;
    private KeyValue ethnicity;
    private List<OffenderAlias> aliases;
    private List<Booking> bookings;
    private List<Identifier> identifiers;

}
