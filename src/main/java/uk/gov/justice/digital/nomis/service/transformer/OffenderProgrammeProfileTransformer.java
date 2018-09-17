package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Activity;
import uk.gov.justice.digital.nomis.api.ProgrammeProfile;
import uk.gov.justice.digital.nomis.api.Schedule;
import uk.gov.justice.digital.nomis.jpa.entity.CourseActivity;
import uk.gov.justice.digital.nomis.jpa.entity.CourseSchedule;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderProgramProfile;

import java.time.format.TextStyle;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OffenderProgrammeProfileTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public OffenderProgrammeProfileTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public ProgrammeProfile programmeProfileOf(OffenderProgramProfile offenderProgramProfile) {
        return ProgrammeProfile.builder()
                .agencyLocationId(offenderProgramProfile.getAgyLocId())
                .bookingId(offenderProgramProfile.getOffenderBookId())
                .courseActivity(activityOf(offenderProgramProfile.getCourseActivity()))
                .offenderEndDate(typesTransformer.localDateOf(offenderProgramProfile.getOffenderEndDate()))
                .offenderProgramStatus(offenderProgramProfile.getOffenderProgramStatus())
                .offenderStartDate(typesTransformer.localDateOf(offenderProgramProfile.getOffenderStartDate()))
                .programProfileId(offenderProgramProfile.getProgramOffPrgrefId())
                .startSessionNo(offenderProgramProfile.getStartSessionNo())
                .suspended(typesTransformer.ynToBoolean(offenderProgramProfile.getSuspendedFlag()))
                .build();
    }

    public Activity activityAndSchedulesOf(CourseActivity courseActivity) {
        return Optional.ofNullable(courseActivity).map(
                ca -> activityOf(ca)
                        .toBuilder()
                        .schedules(schedulesOf(ca.getCourseSchedules()))
                        .build())
                .orElse(null);
    }

    public Activity activityOf(CourseActivity courseActivity) {
        return Optional.ofNullable(courseActivity).map(
                ca -> Activity.builder()
                        .courseActivityId(ca.getCrsActyId())
                        .description(ca.getDescription())
                        .active(typesTransformer.ynToBoolean(ca.getActiveFlag()))
                        .outsideWork(typesTransformer.ynToBoolean(ca.getOutsideWorkFlag()))
                        .scheduledStartDate(typesTransformer.localDateOf(ca.getScheduleStartDate()))
                        .scheduledEndDate(typesTransformer.localDateOf(ca.getScheduleEndDate())).build())
                .orElse(null);
    }

    public List<Schedule> schedulesOf(List<CourseSchedule> courseSchedules) {
        return Optional.ofNullable(courseSchedules).map(
                css -> css
                        .stream()
                        .map(this::courseScheduleOf)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public Schedule courseScheduleOf(CourseSchedule courseSchedule) {
        return Optional.ofNullable(courseSchedule).map(
                cs -> Schedule.builder()
                        .catchUpCourseScheduleId(cs.getCatchUpCrsSchId())
                        .courseScheduleId(cs.getCrsSchId())
                        .endTime(typesTransformer.localDateTimeOf(cs.getEndTime()).toLocalTime())
                        .scheduleDay(typesTransformer.localDateOf(cs.getScheduleDate()).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                        .scheduledDate(typesTransformer.localDateOf(cs.getScheduleDate()))
                        .sessionNo(cs.getSessionNo())
                        .startTime(typesTransformer.localDateTimeOf(cs.getStartTime()).toLocalTime())
                        .build()).orElse(null);
    }
}
