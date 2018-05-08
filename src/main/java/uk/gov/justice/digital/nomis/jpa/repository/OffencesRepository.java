package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.Offence;
import uk.gov.justice.digital.nomis.jpa.entity.OffencePK;

public interface OffencesRepository extends JpaRepository<Offence, OffencePK> {
}
