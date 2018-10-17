package uk.gov.justice.digital.nomis.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.mongodb.entity.Xtag;

@Repository
public interface XtagRepository extends MongoRepository<Xtag, String> {
}
