package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatus;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderImprisonStatusPK;

public interface OffenderImprisonStatusesRepository extends JpaRepository<OffenderImprisonStatus, OffenderImprisonStatusPK> {
}
