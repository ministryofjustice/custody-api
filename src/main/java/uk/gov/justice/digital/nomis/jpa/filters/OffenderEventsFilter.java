package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderEvent;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Builder
@Value
@EqualsAndHashCode
public class OffenderEventsFilter implements Specification<OffenderEvent> {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private LocalDateTime from = null;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private LocalDateTime to = null;

    @Builder.Default
    private Optional<Set<String>> types = Optional.empty();

    @Builder.Default
    private Optional<Long> offenderId = Optional.empty();

    @Override
    public Predicate toPredicate(Root<OffenderEvent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Timestamp tsFrom = Timestamp.valueOf(from);
        Timestamp tsTo = Timestamp.valueOf(to);

        if (tsFrom.after(tsTo)) {
            Timestamp tsTemp = tsFrom;
            tsFrom = tsTo;
            tsTo = tsTemp;
        }

        Root alertsTable = root;
        Path eventTimestamp = alertsTable.get("eventTimestamp");
        Path eventType = root.get("eventType");
        Path rootOffenderId = root.get("rootOffenderId");

        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        predicateBuilder
                .add(cb.greaterThanOrEqualTo(eventTimestamp, tsFrom))
                .add(cb.lessThanOrEqualTo(eventTimestamp, tsTo));

        types.ifPresent(filter -> {
            filter.add("CASE_NOTE");
            predicateBuilder.add(valueInList(cb, eventType, filter));
        });

        offenderId.ifPresent(id -> predicateBuilder.add(cb.equal(rootOffenderId, id)));

        ImmutableList<Predicate> predicates = predicateBuilder.build();

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Predicate valueInList(CriteriaBuilder cb, Path eventType, Set<String> list) {
        CriteriaBuilder.In inTypes = cb.in(eventType);
        list.stream().map(String::toUpperCase).forEach(inTypes::value);
        return inTypes;
    }

}
