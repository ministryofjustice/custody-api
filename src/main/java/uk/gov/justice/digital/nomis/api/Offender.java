package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Relation(collectionRelation = "offenders")
public class Offender {

    private List<Booking> bookings;
    private List<Identifier> identifiers;
    private String firstName;
    private String middleNames;
    private String surname;
    private LocalDate dateOfBirth;
    private Long offenderId;
    private String sexCode;
    private String raceCode;

}
