package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCharge;

@Repository
public interface OffenderChargesRepository extends JpaRepository<OffenderCharge, Long>,
        JpaSpecificationExecutor<OffenderCharge> {
}
