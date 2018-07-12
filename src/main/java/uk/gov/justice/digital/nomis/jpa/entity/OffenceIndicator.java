package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;
import oracle.sql.CHAR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "OFFENCE_INDICATORS")
public class OffenceIndicator {
    @Id
    @Column(name = "OFFENCE_INDICATOR_ID")
    private Long offenceIndicatorId;
    @Column(name = "OFFENCE_CODE")
    private String offenceCode;
    @Column(name = "STATUTE_CODE")
    private String statuteCode;
    @Column(name = "INDICATOR_CODE")
    private String indicatorCode;
}

