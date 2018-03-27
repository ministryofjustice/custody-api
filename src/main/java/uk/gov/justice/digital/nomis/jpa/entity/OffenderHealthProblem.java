package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OFFENDER_HEALTH_PROBLEMS")
@Entity
public class OffenderHealthProblem {
    @Id
    @Column(name = "OFFENDER_HEALTH_PROBLEM_ID")
    private Long offenderHealthProblemId;
    @ManyToOne
    @JoinColumn(name = "OFFENDER_BOOK_ID")
    private OffenderBooking offenderBooking;
    @Column(name = "PROBLEM_TYPE")
    private String problemType;
    @Column(name = "PROBLEM_CODE")
    private String problemCode;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "CASELOAD_TYPE")
    private String caseloadType;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PROBLEM_STATUS")
    private String problemStatus;
}
