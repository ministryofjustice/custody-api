package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import uk.gov.justice.digital.nomis.jpa.entity.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long> {

}
