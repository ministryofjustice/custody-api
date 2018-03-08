package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderExternalMovement;

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
public class MovementsFilter implements Specification<OffenderExternalMovement> {

    @Builder.Default
    private Optional<List<String>> contactTypes = Optional.empty();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private Optional<LocalDateTime> from = Optional.empty();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private Optional<LocalDateTime> to = Optional.empty();
    private Long offenderId;

    @Override
    public Predicate toPredicate(Root<OffenderExternalMovement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        if (from.isPresent()) {
            predicateBuilder.add(cb.greaterThanOrEqualTo(root.get("movementTime"), Timestamp.valueOf(from.get())));
        }

        if (to.isPresent()) {
            predicateBuilder.add(cb.lessThanOrEqualTo(root.get("movementTime"), Timestamp.valueOf(to.get())));
        }

        ImmutableList<Predicate> predicates = predicateBuilder.build();

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}