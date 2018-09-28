package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderImage;

import java.util.List;

@Repository
public interface OffenderImagesRepository extends JpaRepository<OffenderImage, Long>, JpaSpecificationExecutor<OffenderImage> {
    List<OffenderImage> findByOffenderBookingId(Long offenderBookingId);
}
