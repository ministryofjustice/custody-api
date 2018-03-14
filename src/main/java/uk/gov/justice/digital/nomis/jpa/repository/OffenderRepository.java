package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;

@Repository
public interface OffenderRepository extends JpaRepository<Offender, Long> {

    @Query("select o from Offender o where o.offenderId = o.rootOffenderId")
    Page<Offender> findAllRootOffenders(Pageable pageable);

}
