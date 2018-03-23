package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.HealthProblem;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderHealthProblem;

import java.sql.Date;
import java.util.Optional;

@Component
public class HealthProblemsTransformer {
    public HealthProblem healthProblemOf(OffenderHealthProblem offenderHealthProblem) {
        return HealthProblem.builder()
                .bookingId(offenderHealthProblem.getOffenderBooking().getOffenderBookId())
                .caseloadType(offenderHealthProblem.getCaseloadType())
                .description(offenderHealthProblem.getDescription())
                .endDate(Optional.ofNullable(offenderHealthProblem.getEndDate()).map(Date::toLocalDate).orElse(null))
                .offenderHealthProblemId(offenderHealthProblem.getOffenderHealthProblemId())
                .offenderId(offenderHealthProblem.getOffenderBooking().getOffenderId())
                .problemCode(offenderHealthProblem.getProblemCode())
                .problemStatus(offenderHealthProblem.getProblemStatus())
                .problemType(offenderHealthProblem.getProblemType())
                .startDate(Optional.ofNullable(offenderHealthProblem.getStartDate()).map(Date::toLocalDate).orElse(null))
                .build();
    }
}
