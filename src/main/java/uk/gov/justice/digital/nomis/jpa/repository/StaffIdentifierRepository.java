package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import uk.gov.justice.digital.nomis.jpa.entity.StaffIdentifier;
import uk.gov.justice.digital.nomis.jpa.entity.StaffIdentifierIdentity;

public interface StaffIdentifierRepository extends CrudRepository<StaffIdentifier, StaffIdentifierIdentity> {

    StaffIdentifier findById_TypeAndId_IdentificationNumber(String type, String identificationNumber);

}
