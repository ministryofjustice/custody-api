package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Relation(collectionRelation = "offenders")
public class OffenderActiveBooking {
    private String nomsId;
    private Long offenderId;
    private String firstName;
    private String middleNames;
    private String surname;
    private LocalDate dateOfBirth;
    private Booking activeBooking;
}
