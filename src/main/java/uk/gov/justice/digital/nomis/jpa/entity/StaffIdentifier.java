package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PERSONNEL_IDENTIFICATIONS")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class StaffIdentifier {

    @EmbeddedId
    private StaffIdentifierIdentity id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STAFF_ID", updatable = false, insertable = false)
    private Staff staff;

}
