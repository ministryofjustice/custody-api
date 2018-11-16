package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CASELOAD_AGENCY_LOCATIONS")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"caseload", "agencyLocation"})
@ToString(of = {"caseload", "agencyLocation", "updateAllowedFlag"})
public class CaseloadAgencyLocation implements Serializable {

    @EmbeddedId
    private CaseloadAgencyIdentity id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASELOAD_ID", updatable = false, insertable = false)
    private Caseload caseload;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGY_LOC_ID", updatable = false, insertable = false)
    private AgencyLocation agencyLocation;

    @Column(name = "UPDATE_ALLOWED_FLAG")
    private String updateAllowedFlag;
}
