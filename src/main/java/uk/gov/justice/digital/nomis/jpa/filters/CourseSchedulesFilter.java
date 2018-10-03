package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import uk.gov.justice.digital.nomis.jpa.entity.CourseSchedule;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@EqualsAndHashCode
public class CourseSchedulesFilter implements Specification<CourseSchedule> {

    @Builder.Default
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime from = null;

    @Builder.Default
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime to = null;

    private Long courseActivityId;

    @Override
    public Predicate toPredicate(Root<CourseSchedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        Timestamp tsFrom = Timestamp.valueOf(from);
        Timestamp tsTo = Timestamp.valueOf(to);

        if (tsFrom.after(tsTo)) {
            Timestamp tsTemp = tsFrom;
            tsFrom = tsTo;
            tsTo = tsTemp;
        }

        Root courseSchedulesTable = root;

        predicateBuilder.add(cb.equal(courseSchedulesTable.get("crsActyId"), courseActivityId))
                .add(cb.or(cb.lessThanOrEqualTo(courseSchedulesTable.get("scheduleDate"), tsTo), courseSchedulesTable.get("scheduleDate").isNull()))

                .add(cb.or(cb.greaterThanOrEqualTo(courseSchedulesTable.get("scheduleDate"), tsFrom), courseSchedulesTable.get("scheduleDate").isNull()));

        ImmutableList<Predicate> predicates = predicateBuilder.build();

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
