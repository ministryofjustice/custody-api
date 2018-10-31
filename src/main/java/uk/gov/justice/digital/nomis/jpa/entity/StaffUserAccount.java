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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "STAFF_USER_ACCOUNTS")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"username"})
@ToString(of = {"username", "type"})
public class StaffUserAccount {

    @Id()
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "STAFF_ID")
    private Staff staff;

    @Column(name = "STAFF_USER_TYPE", nullable = false)
    private String type;

    @OneToMany
    @JoinColumn(name = "USERNAME")
    private List<UserCaseloadRole> roles;

    @OneToMany
    @JoinColumn(name = "USERNAME")
    private List<UserAccessibleCaseload> caseloads;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AccountDetail accountDetail;

    public List<UserCaseloadRole> filterRolesByCaseload(String caseload) {
        return roles.stream()
                .filter(r -> r.getId().getCaseload().equals(caseload))
                .collect(Collectors.toList());
    }

    public List<UserAccessibleCaseload> filterByCaseload(String caseload) {
        return caseloads.stream()
                .filter(r -> r.getId().getCaseload().equals(caseload))
                .collect(Collectors.toList());
    }
}
