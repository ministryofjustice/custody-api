package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffenderContactPerson {
    private Long offenderContactPersonId;
    private Long bookingId;
    private Person person;
    private Boolean approvedVisitor;
    private String caseloadType;
    private String comments;
    private String caseInfoNumber;
    private Boolean awareOfCharges;
    private Boolean canBeContacted;
    private Boolean emergencyContact;
    private Boolean nextOfKin;
    private Boolean active;
    private Long contactRootOffenderId;
    private List<Address> addresses;
    private ContactPersonType contactPersonType;
}
