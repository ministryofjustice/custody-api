package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.justice.digital.nomis.jpa.entity.StaffIdentifier;
import uk.gov.justice.digital.nomis.jpa.entity.StaffIdentifierIdentity;

public interface StaffIdentifierRepository extends JpaRepository<StaffIdentifier, StaffIdentifierIdentity> {

}
