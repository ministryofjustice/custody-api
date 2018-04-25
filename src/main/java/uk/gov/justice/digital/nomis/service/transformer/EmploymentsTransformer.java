package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Employment;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderEmployment;

@Component
public class EmploymentsTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public EmploymentsTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public Employment employmentOf(OffenderEmployment offenderEmployment) {
        return Employment.builder()
                .caseloadType(offenderEmployment.getCaseloadType())
                .certification(offenderEmployment.getCertification())
                .comments(offenderEmployment.getCommentText())
                .contactEmployer(typesTransformer.ynToBoolean(offenderEmployment.getContactEmployerFlag()))
                .contactNumber(offenderEmployment.getContactNumber())
                .contactType(offenderEmployment.getContactType())
                .employerAware(typesTransformer.ynToBoolean(offenderEmployment.getEmployerAwareFlag()))
                .employerName(offenderEmployment.getEmployerName())
                .employmentDate(typesTransformer.localDateOf(offenderEmployment.getEmploymentDate()))
                .employmentPostCode(offenderEmployment.getEmploymentPostCode())
                .employmentSchedule(offenderEmployment.getEmploymentSchedule())
                .employmentSequence(offenderEmployment.getEmploySeq())
                .employmentType(offenderEmployment.getEmploymentType())
                .hoursWeek(offenderEmployment.getHoursWeek())
                .occupationsCode(offenderEmployment.getOccupationsCode())
                .offenderEmploymentId(offenderEmployment.getOffenderEmploymentId())
                .partialEmploymentDate(typesTransformer.ynToBoolean(offenderEmployment.getPartialEmploymentDateFlag()))
                .partialTerminationDate(typesTransformer.ynToBoolean(offenderEmployment.getPartialTerminationDateFlag()))
                .position(offenderEmployment.getPosition())
                .scheduleHours(offenderEmployment.getScheduleHours())
                .scheduleType(offenderEmployment.getScheduleType())
                .supervisorName(offenderEmployment.getSupervisorName())
                .terminationDate(typesTransformer.localDateOf(offenderEmployment.getTerminationDate()))
                .terminationReason(offenderEmployment.getTerminationReasonText())
                .wage(offenderEmployment.getWage())
                .wagePeriodCode(offenderEmployment.getWagePeriodCode())
                .build();
    }
}
