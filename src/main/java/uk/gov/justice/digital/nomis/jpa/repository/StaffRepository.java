package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

}
