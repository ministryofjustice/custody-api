package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import uk.gov.justice.digital.nomis.jpa.entity.XtagEvent;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@EqualsAndHashCode
public class XtagEventsFilter implements Specification<XtagEvent> {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private LocalDateTime from = null;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private LocalDateTime to = null;

    private Long offenderId;

    @Override
    public Predicate toPredicate(Root<XtagEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Timestamp tsFrom = Timestamp.valueOf(from);
        Timestamp tsTo = Timestamp.valueOf(to);

        if (tsFrom.after(tsTo)) {
            Timestamp tsTemp = tsFrom;
            tsFrom = tsTo;
            tsTo = tsTemp;
        }

        Root xtagEventTable = root;
        Path eventTimestamp = xtagEventTable.get("enqTime");

        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        predicateBuilder
                .add(cb.greaterThanOrEqualTo(eventTimestamp, tsFrom))
                .add(cb.lessThanOrEqualTo(eventTimestamp, tsTo));

        ImmutableList<Predicate> predicates = predicateBuilder.build();

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Predicate valueInList(CriteriaBuilder cb, Path eventType, Set<String> list) {
        CriteriaBuilder.In inTypes = cb.in(eventType);
        list.stream().map(String::toUpperCase).forEach(inTypes::value);
        return inTypes;
    }

}
