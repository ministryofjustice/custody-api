package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import uk.gov.justice.digital.nomis.api.ReleaseDetails;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentCalculation;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Set;

@Builder
@EqualsAndHashCode
public class SentenceCalculationsFilter implements Specification<OffenderSentCalculation> {

    private Set<Long> bookingIds;

    @Override
    public Predicate toPredicate(Root<OffenderSentCalculation> table, CriteriaQuery<?> query, CriteriaBuilder cb) {
        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        if (!CollectionUtils.isEmpty(bookingIds)) {
            predicateBuilder.add(table.get("offenderBooking").get("offenderBookId").in(bookingIds));
        }
        ImmutableList<Predicate> predicates = predicateBuilder.build();
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}

