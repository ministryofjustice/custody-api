package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentence;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentencePk;

@Repository
public interface OffenderSentencesRepository extends JpaRepository<OffenderSentence, OffenderSentencePk> {

}
