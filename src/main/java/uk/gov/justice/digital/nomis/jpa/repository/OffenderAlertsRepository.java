package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAlert;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAlertPK;

@Repository
public interface OffenderAlertsRepository extends JpaRepository<OffenderAlert, OffenderAlertPK>, JpaSpecificationExecutor<OffenderAlert> {
}
