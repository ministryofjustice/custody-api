package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OffenderContactPerson {
    @JsonIgnore
    private String primaryAddressBias;
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

    public Address getPrimaryAddress() {
        return Optional.ofNullable(addresses)
                .flatMap(addresses -> addresses
                        .stream()
                        .filter(a -> primaryAddressBias != null && primaryAddressBias.equals(a.getAddressType()))
                        .findFirst())
                .orElse(null);
    }
}
