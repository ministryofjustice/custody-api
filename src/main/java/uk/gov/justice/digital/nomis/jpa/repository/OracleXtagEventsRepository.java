package uk.gov.justice.digital.nomis.jpa.repository;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.STRUCT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.gov.justice.digital.nomis.jpa.entity.XtagEventNonJpa;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderEventsFilter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class OracleXtagEventsRepository implements XtagEventsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OracleXtagEventsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Optional<XtagEventNonJpa> xtagEventOf(ResultSet rs) {

        try {
            return Optional.of(XtagEventNonJpa.builder()
                    .chainNo(rs.getLong("CHAIN_NO"))
                    .corrID(rs.getString("CORRID"))
                    .cscn(rs.getLong("CSCN"))
                    .delay(rs.getTimestamp("DELAY"))
                    .deqTID(rs.getString("DEQ_TID"))
                    .deqTime(rs.getTimestamp("DEQ_TIME"))
                    .dequeueMsgId(rs.getString("DEQUEUE_MSGID"))
                    .deqUID(rs.getString("DEQ_UID"))
                    .dscn(rs.getLong("DSCN"))
                    .enqTID(rs.getString("ENQ_TID"))
                    .enqTime(rs.getTimestamp("ENQ_TIME"))
                    .enqUID(rs.getString("ENQ_UID"))
                    .exceptionQSchema(rs.getString("EXCEPTION_QSCHEMA"))
                    .exceptionQueue(rs.getString("EXCEPTION_QUEUE"))
                    .expiration(rs.getLong("EXPIRATION"))
                    .localOrderNo(rs.getLong("LOCAL_ORDER_NO"))
                    .msgId(rs.getString("MSGID"))
                    .priority(rs.getLong("PRIORITY"))
                    .qName(rs.getString("Q_NAME"))
                    .recipientKey(rs.getLong("RECIPIENT_KEY"))
                    .retryCount(rs.getLong("RETRY_COUNT"))
                    .senderAddress(rs.getString("SENDER_ADDRESS"))
                    .senderName(rs.getString("SENDER_NAME"))
                    .senderProtocol(rs.getLong("SENDER_PROTOCOL"))
                    .state(rs.getLong("STATE"))
                    .stepNo(rs.getLong("STEP_NO"))
                    .timeManagerInfo(rs.getTimestamp("TIME_MANAGER_INFO"))
                    .userData((STRUCT) rs.getObject("USER_DATA"))
                    .userProp((STRUCT) rs.getObject("USER_PROP"))
                    .build());
        } catch (SQLException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<XtagEventNonJpa> findAll(OffenderEventsFilter f) {

        final List<Optional<XtagEventNonJpa>> results = jdbcTemplate.query("select * from xtag.xtag_listener_tab where enq_time >= ? and enq_time <= ?", (rs, rowNum) -> xtagEventOf(rs), Timestamp.valueOf(f.getFrom()), Timestamp.valueOf(f.getTo()));

        return results.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }


}
