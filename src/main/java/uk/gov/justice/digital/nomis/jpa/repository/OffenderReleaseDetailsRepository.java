package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderReleaseDetails;

public interface OffenderReleaseDetailsRepository extends JpaRepository<OffenderReleaseDetails, Long> {
}
