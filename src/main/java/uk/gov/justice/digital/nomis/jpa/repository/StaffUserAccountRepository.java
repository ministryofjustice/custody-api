package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.StaffUserAccount;

public interface StaffUserAccountRepository extends JpaRepository<StaffUserAccount, String> {

}
