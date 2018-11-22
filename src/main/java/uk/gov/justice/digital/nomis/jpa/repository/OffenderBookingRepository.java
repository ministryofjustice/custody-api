package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.ImprisonmentStatus;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAssessment;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderBookingFilter;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffenderBookingRepository extends JpaRepository<OffenderBooking, Long>, JpaSpecificationExecutor<OffenderBooking> {

}