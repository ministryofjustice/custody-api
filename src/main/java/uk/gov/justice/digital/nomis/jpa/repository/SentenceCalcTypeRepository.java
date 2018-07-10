package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.SentenceCalcType;
import uk.gov.justice.digital.nomis.jpa.entity.SentenceCalcTypePK;

@Repository
public interface SentenceCalcTypeRepository extends JpaRepository<SentenceCalcType, SentenceCalcTypePK> {
}
