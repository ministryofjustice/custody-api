package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OFFENDER_IMPRISON_STATUSES")
@IdClass(OffenderImprisonStatusPK.class)
public class OffenderImprisonStatus {

    @Id
    @Column(name = "OFFENDER_BOOK_ID")
    private Long offenderBookId;

    @Id
    @Column(name = "IMPRISON_STATUS_SEQ")
    private Long imprisonStatusSeq;

    @Column(name = "IMPRISONMENT_STATUS")
    private String imprisonmentStatus;
    @Column(name = "EFFECTIVE_DATE")
    private Timestamp effectiveDate;
    @Column(name = "EFFECTIVE_TIME")
    private Time effectiveTime;
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;
    @Column(name = "AGY_LOC_ID")
    private String agyLocId;
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Column(name = "LATEST_STATUS")
    private String latestStatus;
}