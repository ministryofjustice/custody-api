package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderRehabProvider;

import java.util.List;

@Repository
public interface OffenderRehabProviderRepository extends JpaRepository<OffenderRehabProvider, Long> {
    List<OffenderRehabProvider> findByOffenderRehabDecisionId(Long id);
}
