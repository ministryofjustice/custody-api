package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.AgencyLocation;

import java.util.Optional;

@Repository
public interface AgencyLocationsRepository extends JpaRepository<AgencyLocation, String> {

    Optional<AgencyLocation> getByAgyLocId(String agencyLocationId);
}
