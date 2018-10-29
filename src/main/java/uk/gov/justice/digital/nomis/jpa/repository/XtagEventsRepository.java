package uk.gov.justice.digital.nomis.jpa.repository;

import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.XtagEventNonJpa;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;

import java.util.List;

@Repository
public interface XtagEventsRepository {
    List<XtagEventNonJpa> findAll(OffenderEventsFilter oeFilter);
}