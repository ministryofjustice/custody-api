package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
