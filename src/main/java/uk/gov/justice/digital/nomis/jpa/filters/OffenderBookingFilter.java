package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Builder
@EqualsAndHashCode
public class OffenderBookingFilter implements Specification<OffenderBooking> {

    private String agencyLocationId;
    private String activeFlag;


    @Override
    public Predicate toPredicate(Root<OffenderBooking> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        predicateBuilder.add(cb.equal(root.get("activeFlag"), activeFlag));

        predicateBuilder.add(cb.equal(root.get("agyLocId"),agencyLocationId));

        ImmutableList<Predicate> predicates = predicateBuilder.build();

        if (Long.class != query.getResultType()) {
            root.fetch("offender", JoinType.INNER);
        }

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}

