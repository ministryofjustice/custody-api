package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderIdentifier;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderIdentifierPK;

import java.util.List;

@Repository
public interface OffenderIdentifierRepository extends JpaRepository<OffenderIdentifier, OffenderIdentifierPK> {

}
