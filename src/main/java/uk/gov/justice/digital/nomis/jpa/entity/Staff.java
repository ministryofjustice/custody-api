package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STAFF_MEMBERS")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"staffId"})
@ToString(of = {"staffId", "firstName", "lastName"})
public class Staff {

    private static final String STAFF_STATUS_ACTIVE = "ACTIVE";

    @Id()
    @Column(name = "STAFF_ID", nullable = false)
    private Long staffId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "STATUS")
    private String status;

    @OneToMany(mappedBy = "staff")
    private List<StaffUserAccount> users;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "STAFF_ID")
    private List<StaffIdentifier> identifiers;

    public StaffIdentifier addIdentifier(String type, String identificationNumber) {
        if (identifiers == null) {
            identifiers = new ArrayList<>();
        }
        StaffIdentifier id = StaffIdentifier.builder()
                .id(StaffIdentifierIdentity.builder()
                        .type(type)
                        .identificationNumber(identificationNumber)
                        .staffId(getStaffId())
                        .build())
                .staff(this)
                .build();
        identifiers.add(id);
        return id;
    }

    public StaffIdentifier findIdentifier(String type) {
        return identifiers.stream()
                .filter(r -> r.getId().getType().equals(type))
                .findFirst().orElse(null);
    }

    public StaffUserAccount getAccountByType(String type) {
        return users.stream()
                .filter(r -> r.getType().equals(type))
                .findFirst().orElse(null);
    }

    public boolean isActive() {
        return STAFF_STATUS_ACTIVE.equals(status);
    }
}
