package uk.gov.justice.digital.nomis.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.mongodb.entity.XtagEvent;

@Repository
public interface XtagEventRepository extends MongoRepository<XtagEvent, String> {
}
