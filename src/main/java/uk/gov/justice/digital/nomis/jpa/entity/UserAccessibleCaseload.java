package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "USER_ACCESSIBLE_CASELOADS")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"caseload", "staffUser"})
@ToString(of = {"caseload", "staffUser", "startDate"})
public class UserAccessibleCaseload implements Serializable {

    @EmbeddedId
    private UserCaseloadIdentity id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASELOAD_ID", updatable = false, insertable = false)
    private Caseload caseload;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME", updatable = false, insertable = false)
    private StaffUserAccount staffUser;

    @Column(name = "START_DATE")
    private LocalDate startDate;
}
