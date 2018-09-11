package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderProgramProfile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Builder
@EqualsAndHashCode
public class OffenderProgramProfilesFilter implements Specification<OffenderProgramProfile> {

    @Builder.Default
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Optional<LocalDateTime> from = Optional.empty();

    @Builder.Default
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Optional<LocalDateTime> to = Optional.empty();

    private Long bookingId;

    @Override
    public Predicate toPredicate(Root<OffenderProgramProfile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        predicateBuilder.add(cb.equal(root.get("offenderBookId"), bookingId));
        from.ifPresent(localDateTime -> predicateBuilder.add(cb.lessThanOrEqualTo(root.get("offenderStartDate"), Timestamp.valueOf(localDateTime))));
        from.ifPresent(localDateTime -> predicateBuilder.add(cb.greaterThan(root.get("offenderEndDate"), Timestamp.valueOf(localDateTime))));
        from.ifPresent(localDateTime -> predicateBuilder.add(cb.lessThanOrEqualTo(root.join("courseActivity").get("scheduleStartDate"), Timestamp.valueOf(localDateTime))));
        from.ifPresent(localDateTime -> predicateBuilder.add(cb.greaterThan(root.join("courseActivity").get("scheduleEndDate"), Timestamp.valueOf(localDateTime))));
        from.ifPresent(localDateTime -> predicateBuilder.add(cb.lessThanOrEqualTo(root.join("courseActivity").join("courseSchedules").get("scheduleDate"), Timestamp.valueOf(localDateTime))));
        from.ifPresent(localDateTime -> predicateBuilder.add(cb.greaterThan(root.join("courseActivity").join("courseSchedules").get("scheduleDate"), Timestamp.valueOf(localDateTime))));

        ImmutableList<Predicate> predicates = predicateBuilder.build();

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
