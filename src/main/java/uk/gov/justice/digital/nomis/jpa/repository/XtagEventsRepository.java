package uk.gov.justice.digital.nomis.jpa.repository;

import oracle.sql.STRUCT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.XtagEvent;
import uk.gov.justice.digital.nomis.mongodb.entity.Xtag;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface XtagEventsRepository extends JpaRepository<XtagEvent, Long>, JpaSpecificationExecutor<XtagEvent> {
}

public class XtagUserDataRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public XtagUserDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Xtag> findByEnqDateBetween(LocalDateTime from, LocalDateTime to) {
        List<STRUCT> s = jdbcTemplate.queryForList("SELECT user_data FROM XTAG.XTAG_LISTENER_TAB", STRUCT.class);

        return null;
    }
}