package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.CourseActivity;

@Repository
public interface CourseActivitiesRepository extends JpaRepository<CourseActivity, Long>, JpaSpecificationExecutor<CourseActivity> {
}

