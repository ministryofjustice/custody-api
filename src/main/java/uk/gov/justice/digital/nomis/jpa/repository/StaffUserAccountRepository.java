package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import uk.gov.justice.digital.nomis.jpa.entity.StaffUserAccount;

public interface StaffUserAccountRepository extends CrudRepository<StaffUserAccount, String> {

}
