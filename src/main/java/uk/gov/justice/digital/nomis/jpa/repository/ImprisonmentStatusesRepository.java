package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.ImprisonmentStatus;

import java.util.List;

@Repository
public interface ImprisonmentStatusesRepository extends JpaRepository<ImprisonmentStatus, Long> {

    List<ImprisonmentStatus> findByImprisonmentStatus(String imprisonmentStatus);
}
