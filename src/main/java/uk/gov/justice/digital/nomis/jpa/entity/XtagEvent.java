package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import oracle.sql.STRUCT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "XTAG_LISTENER_TAB", schema = "XTAG")
public class XtagEvent {
    @Id
    @Column(name = "MSGID")
    private String msgId;

    @Column(name = "Q_NAME")
    private String qName;
    @Column(name = "CORRID")
    private String corrID;
    @Column(name = "PRIORITY")
    private Long priority;
    @Column(name = "STATE")
    private Long state;
    @Column(name = "DELAY")
    private Timestamp delay;
    @Column(name = "EXPIRATION")
    private Long expiration;
    @Column(name = "TIME_MANAGER_INFO")
    private Timestamp timeManagerInfo;
    @Column(name = "LOCAL_ORDER_NO")
    private Long localOrderNo;
    @Column(name = "CHAIN_NO")
    private Long chainNo;
    @Column(name = "CSCN")
    private Long cscn;
    @Column(name = "DSCN")
    private Long dscn;
    @Column(name = "ENQ_TIME")
    private Timestamp enqTime;
    @Column(name = "ENQ_UID")
    private String enqUID;
    @Column(name = "ENQ_TID")
    private String enqTID;
    @Column(name = "DEQ_TIME")
    private Timestamp deqTime;
    @Column(name = "DEQ_UID")
    private String deqUID;
    @Column(name = "DEQ_TID")
    private String deqTID;
    @Column(name = "RETRY_COUNT")
    private Long retryCount;
    @Column(name = "EXCEPTION_QSCHEMA")
    private String exceptionQSchema;
    @Column(name = "EXCEPTION_QUEUE")
    private String exceptionQueue;
    @Column(name = "STEP_NO")
    private Long stepNo;
    @Column(name = "RECIPIENT_KEY")
    private Long recipientKey;

    //@Column(name = "DEQUEUE_MSGID")
    //private String DequeueMsgId;            // RAW(16)

    @Column(name = "SENDER_NAME")
    private String senderName;
    @Column(name = "SENDER_ADDRESS")
    private String senderAddress;
    @Column(name = "SENDER_PROTOCOL")
    private Long senderProtocol;

    //@Column(name = "USER_DATA")
    private STRUCT userData;                // SYS.AQ$_JMS_MESSAGE
    //@Column(name = "USER_PROP")
    //private STRUCT userProp;                // SYS.ANYDATA
}
