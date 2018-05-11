package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "OFFENDER_REHAB_DECISIONS")
public class OffenderRehabDecision {
    @Id
    @Column(name = "OFFENDER_REHAB_DECISION_ID")
    private Long offenderRehabDecisionId;
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;
    @Column(name = "DECISION_CODE")
    private String decisionCode;
    @Column(name = "DECISION_DATE")
    private Timestamp decisionDate;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "STAFF_ID")
    private Long staffId;
    @Column(name = "RECORD_DATETIME")
    private Timestamp recordDatetime;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;

}
