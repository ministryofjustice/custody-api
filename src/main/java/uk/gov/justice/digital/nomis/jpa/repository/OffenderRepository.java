package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;

import java.util.List;

@Repository
public interface OffenderRepository extends JpaRepository<Offender, Long> {

    @Query("select o from Offender o where o.offenderId <> o.rootOffenderId and o.rootOffenderId in ?1")
    List<Offender> findOffenderAliases(List<Long> offenderIds);

    @Query("select o from Offender o where o.offenderId = o.rootOffenderId")
    Page<Offender> findAllRootOffenders(Pageable pageable);

    Page<Offender> findByRootOffenderIdEqualsAndOffenderId(Pageable pageable);

}
