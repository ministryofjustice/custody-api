package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.ReferenceCode;
import uk.gov.justice.digital.nomis.jpa.entity.ReferenceCodePK;

@Repository
public interface ReferenceCodesRepository extends JpaRepository<ReferenceCode, ReferenceCodePK> {
}
