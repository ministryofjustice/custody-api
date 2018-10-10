package uk.gov.justice.digital.nomis.jpa.filters;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCaseNote;

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
@EqualsAndHashCode
public class OffenderCaseNotesFilter implements Specification<OffenderCaseNote> {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private LocalDateTime from = null;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Builder.Default
    private LocalDateTime to = null;

    @Builder.Default
    private Optional<Set<String>> types = Optional.empty();

    @Builder.Default
    private Optional<Set<String>> subTypes = Optional.empty();

    private Long bookingId;

    @Override
    public Predicate toPredicate(Root<OffenderCaseNote> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Timestamp tsFrom = Timestamp.valueOf(from);
        Timestamp tsTo = Timestamp.valueOf(to);

        if (tsFrom.after(tsTo)) {
            Timestamp tsTemp = tsFrom;
            tsFrom = tsTo;
            tsTo = tsTemp;
        }

        Root caseNotesTable = root;
        Path offenderBookId = caseNotesTable.get("offenderBookId");
        Path contactDate = caseNotesTable.get("contactDate");
        Path caseNoteType = caseNotesTable.get("caseNoteType");
        Path caseNoteSubType = caseNotesTable.get("caseNoteSubType");

        ImmutableList.Builder<Predicate> predicateBuilder = ImmutableList.builder();

        predicateBuilder.add(cb.equal(offenderBookId, bookingId))
                .add(cb.greaterThanOrEqualTo(contactDate, tsFrom))
                .add(cb.lessThanOrEqualTo(contactDate, tsTo))
        ;

        types.ifPresent(filter -> predicateBuilder.add(valueInList(cb, caseNoteType, filter)));
        subTypes.ifPresent(filter -> predicateBuilder.add(valueInList(cb, caseNoteSubType, filter)));

        ImmutableList<Predicate> predicates = predicateBuilder.build();

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Predicate valueInList(CriteriaBuilder cb, Path eventType, Set<String> list) {
        CriteriaBuilder.In inTypes = cb.in(eventType);
        list.stream().map(String::toUpperCase).forEach(inTypes::value);
        return inTypes;
    }

}
