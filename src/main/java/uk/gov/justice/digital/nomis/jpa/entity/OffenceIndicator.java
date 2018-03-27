package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
