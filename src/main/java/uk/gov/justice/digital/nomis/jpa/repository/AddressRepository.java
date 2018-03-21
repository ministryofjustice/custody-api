package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
