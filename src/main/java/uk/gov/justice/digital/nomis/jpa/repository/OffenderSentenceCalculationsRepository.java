package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentCalculation;

public interface OffenderSentenceCalculationsRepository extends JpaRepository<OffenderSentCalculation, Long> {
}
