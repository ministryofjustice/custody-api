package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CASELOADS")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name", "type"})
public class Caseload {

    @Id()
    @Column(name = "CASELOAD_ID", nullable = false)
    private String id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String name;

    @Column(name = "CASELOAD_TYPE")
    private String type;

    @OneToMany
    @JoinColumn(name = "CASELOAD_ID")
    private List<CaseloadAgencyLocation> agencyLocations;
}
