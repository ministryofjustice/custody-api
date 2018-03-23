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
    /*
    CREATE TABLE "OMS_OWNER"."OFFENDER_HEALTH_PROBLEMS"
   (    "OFFENDER_HEALTH_PROBLEM_ID" NUMBER(10,0) NOT NULL ENABLE,
        "OFFENDER_BOOK_ID" NUMBER(10,0) NOT NULL ENABLE,
        "PROBLEM_TYPE" VARCHAR2(12) NOT NULL ENABLE,
        "PROBLEM_CODE" VARCHAR2(12) NOT NULL ENABLE,
        "START_DATE" DATE,
        "END_DATE" DATE,
        "CASELOAD_TYPE" VARCHAR2(12) NOT NULL ENABLE,
        "DESCRIPTION" VARCHAR2(240),
        "PROBLEM_STATUS" VARCHAR2(12),
        "CREATE_DATETIME" TIMESTAMP (9) DEFAULT systimestamp NOT NULL ENABLE,
        "CREATE_USER_ID" VARCHAR2(32) DEFAULT USER NOT NULL ENABLE,
        "MODIFY_DATETIME" TIMESTAMP (9),
        "MODIFY_USER_ID" VARCHAR2(32),
        "AUDIT_TIMESTAMP" TIMESTAMP (9),
        "AUDIT_USER_ID" VARCHAR2(32),
        "AUDIT_MODULE_NAME" VARCHAR2(65),
        "AUDIT_CLIENT_USER_ID" VARCHAR2(64),
        "AUDIT_CLIENT_IP_ADDRESS" VARCHAR2(39),
        "AUDIT_CLIENT_WORKSTATION_NAME" VARCHAR2(64),
        "AUDIT_ADDITIONAL_INFO" VARCHAR2(256),
         CONSTRAINT "OFFENDER_HEALTH_PROBLEMS_PK" PRIMARY KEY ("OFFENDER_HEALTH_PROBLEM_ID")
     */

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
