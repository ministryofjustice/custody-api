package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "OFFENDER_REHAB_PROVIDERS")
public class OffenderRehabProvider {
    @Id
    @Column(name = "OFFENDER_REHAB_PROVIDER_ID")
    private Long offenderRehabProviderId;
    @Column(name = "OFFENDER_REHAB_DECISION_ID")
    private Long offenderRehabDecisionId;
    @Column(name = "PROVIDER_CODE")
    private String providerCode;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "COMMENT_TEXT")
    private String commentText;

}
