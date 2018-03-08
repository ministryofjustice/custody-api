package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENCES")
public class Offence {

    @EmbeddedId
    private Offence.Pk id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SEVERITY_RANKING")
    private String severityRanking;
    @Column(name = "DEFAULT_OFFENCE_TYPE")
    private String defaultOffenceType;
    @Column(name = "MAX_SENTENCE_LENGTH")
    private Integer maxSentenceLength;
    @Column(name = "SENTENCE_UNIT_CODE")
    private String sentenceUnitCode;
    @Column(name = "OFFENCE_GROUP")
    private String offenceGroup;
    @Column(name = "UPDATE_ALLOWED_FLAG")
    private String updateAllowedFlag;
    @Column(name = "REPEALED_DATE")
    private Timestamp repealedDate;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    @Column(name = "LIST_SEQ")
    private Integer listSeq;
    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "CHECK_BOX1")
    private String checkBox1;
    @Column(name = "CHECK_BOX2")
    private String checkBox2;
    @Column(name = "CHECK_BOX3")
    private String checkBox3;
    @Column(name = "OFFENSE_DEGREE")
    private String offenseDegree;
    @Column(name = "MAX_GOOD_TIME_PERC")
    private Integer maxGoodTimePerc;
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "AUDIT_TIMESTAMP")
    private Timestamp auditTimestamp;
    @Column(name = "AUDIT_USER_ID")
    private String auditUserId;
    @Column(name = "AUDIT_MODULE_NAME")
    private String auditModuleName;
    @Column(name = "AUDIT_CLIENT_USER_ID")
    private String auditClientUserId;
    @Column(name = "AUDIT_CLIENT_IP_ADDRESS")
    private String auditClientIpAddress;
    @Column(name = "AUDIT_CLIENT_WORKSTATION_NAME")
    private String auditClientWorkstationName;
    @Column(name = "AUDIT_ADDITIONAL_INFO")
    private String auditAdditionalInfo;
    @JoinColumn(name = "HO_CODE")
    @OneToOne
    private HoCode hoCode;
    @JoinColumn(name = "OLD_STATUTE_CODE")
    @OneToOne
    private Statute oldStatute;

    @Embeddable
    @Data
    public static class Pk implements Serializable {
        @ManyToOne
        @JoinColumn(name = "STATUTE_CODE")
        private Statute statute;

        @Column(name = "OFFENCE_CODE")
        private String offenceCode;
    }


}
