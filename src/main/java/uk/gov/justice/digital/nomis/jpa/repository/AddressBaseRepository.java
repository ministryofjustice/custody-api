package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.Address;

public interface AddressBaseRepository<T extends Address> extends JpaRepository<T, Long> {
}
