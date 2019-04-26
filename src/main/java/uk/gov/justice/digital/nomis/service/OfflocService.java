package uk.gov.justice.digital.nomis.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Activity;
import uk.gov.justice.digital.nomis.api.ActivityDetailCDE;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.api.Alert;
import uk.gov.justice.digital.nomis.api.Charge;
import uk.gov.justice.digital.nomis.api.CheckHoldAlertCDE;
import uk.gov.justice.digital.nomis.api.CourtEvent;
import uk.gov.justice.digital.nomis.api.DiaryDetail;
import uk.gov.justice.digital.nomis.api.EarliestSentenceAndConvictionCDE;
import uk.gov.justice.digital.nomis.api.Employment;
import uk.gov.justice.digital.nomis.api.Exclusion;
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.api.HealthProblem;
import uk.gov.justice.digital.nomis.api.Identifier;
import uk.gov.justice.digital.nomis.api.IdentifyingMark;
import uk.gov.justice.digital.nomis.api.IdentifyingMarkCDE;
import uk.gov.justice.digital.nomis.api.IepLevel;
import uk.gov.justice.digital.nomis.api.KeyValue;
import uk.gov.justice.digital.nomis.api.OffenceResult;
import uk.gov.justice.digital.nomis.api.OffenderAlias;
import uk.gov.justice.digital.nomis.api.OffenderAssessment;
import uk.gov.justice.digital.nomis.api.OffenderCDE;
import uk.gov.justice.digital.nomis.api.OffenderContactPerson;
import uk.gov.justice.digital.nomis.api.OffenderIepLevel;
import uk.gov.justice.digital.nomis.api.OffenderImprisonmentStatus;
import uk.gov.justice.digital.nomis.api.PhysicalsCDE;
import uk.gov.justice.digital.nomis.api.ProfileDetails;
import uk.gov.justice.digital.nomis.api.ProgrammeProfile;
import uk.gov.justice.digital.nomis.api.ReleaseDetails;
import uk.gov.justice.digital.nomis.api.Schedule;
import uk.gov.justice.digital.nomis.api.Sentence;
import uk.gov.justice.digital.nomis.api.SentenceCalculation;
import uk.gov.justice.digital.nomis.api.SentenceCalculationDatesCDE;
import uk.gov.justice.digital.nomis.api.YMD;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAlert;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderSentCalculation;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AlertsTransformer;
import uk.gov.justice.digital.nomis.service.transformer.AttendanceAndExclusionTransformer;
import uk.gov.justice.digital.nomis.service.transformer.CourtEventsTransformer;
import uk.gov.justice.digital.nomis.service.transformer.EmploymentsTransformer;
import uk.gov.justice.digital.nomis.service.transformer.MovementsTransformer;
import uk.gov.justice.digital.nomis.service.transformer.OffenderActiveBookingTransformer;
import uk.gov.justice.digital.nomis.service.transformer.OffenderProgrammeProfileTransformer;
import uk.gov.justice.digital.nomis.service.transformer.OffenderTransformer;
import uk.gov.justice.digital.nomis.service.transformer.PhysicalsTransformer;
import uk.gov.justice.digital.nomis.service.transformer.ReleaseDetailsTransformer;
import uk.gov.justice.digital.nomis.service.transformer.SentenceCalculationsTransformer;
import uk.gov.justice.digital.nomis.service.transformer.SentenceTransformer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uk.gov.justice.digital.nomis.service.EmploymentsService.BY_EMPLOYMENT_SEQUENCE;

@Service
@Transactional(readOnly = true)
@Slf4j
public class OfflocService {
    private final OffenderRepository offenderRepository;
    private final AlertsTransformer alertsTransformer;
    private final MovementsTransformer movementsTransformer;
    private final OffenderProgrammeProfileTransformer offenderProgrammeProfileTransformer;
    private final AttendanceAndExclusionTransformer attendanceAndExclusionTransformer;
    private final CourtEventsTransformer courtEventsTransformer;
    private final EmploymentsTransformer employmentsTransformer;
    private final SentenceTransformer sentenceTransformer;
    private final SentenceCalculationsTransformer sentenceCalculationsTransformer;
    private final DiaryDetailService diaryDetailService;
    private final ChargesService chargesService;
    private final IEPService iepService;
    private final OffenderTransformer offenderTransformer;
    private final AlertsService alertsService;
    private final OffenderActiveBookingTransformer bookingTransformer;
    private final HealthProblemsService healthProblemsService;
    private final ContactPersonsService contactPersonsService;
    private final OffenderService offenderService;
    private final AddressService addressService;
    private final OffenderProgrammeProfilesService programmeProfilesService;
    private final IndividualSchedulesService individualSchedulesService;
    private final ImprisonStatusService imprisonStatusService;
    private final AssessmentService assessmentService;
    private final SentencesService sentencesService;
    private final PhysicalsTransformer physicalsTransformer;
    private final ReleaseDetailsTransformer releaseDetailsTransformer;

    private final Set<String> headBodyParts = ImmutableSet.of("EAR", "FACE", "HEAD", "LIP", "NECK", "NOSE");


    @Autowired
    public OfflocService(OffenderRepository offenderRepository, AlertsTransformer alertsTransformer, MovementsTransformer movementsTransformer, OffenderProgrammeProfileTransformer offenderProgrammeProfileTransformer, AttendanceAndExclusionTransformer attendanceAndExclusionTransformer, CourtEventsTransformer courtEventsTransformer, EmploymentsTransformer employmentsTransformer, SentenceTransformer sentenceTransformer, SentenceCalculationsTransformer sentenceCalculationsTransformer, DiaryDetailService diaryDetailService, ChargesService chargesService, IEPService iepService, OffenderTransformer offenderTransformer, AlertsService alertsService, OffenderActiveBookingTransformer bookingTransformer, HealthProblemsService healthProblemsService, ContactPersonsService contactPersonsService, OffenderService offenderService, AddressService addressService, OffenderProgrammeProfilesService programmeProfilesService, IndividualSchedulesService individualSchedulesService, ImprisonStatusService imprisonStatusService, AssessmentService assessmentService, SentencesService sentencesService, PhysicalsTransformer physicalsTransformer, ReleaseDetailsTransformer releaseDetailsTransformer) {
        this.offenderRepository = offenderRepository;
        this.alertsTransformer = alertsTransformer;
        this.movementsTransformer = movementsTransformer;
        this.offenderProgrammeProfileTransformer = offenderProgrammeProfileTransformer;
        this.attendanceAndExclusionTransformer = attendanceAndExclusionTransformer;
        this.courtEventsTransformer = courtEventsTransformer;
        this.employmentsTransformer = employmentsTransformer;
        this.sentenceTransformer = sentenceTransformer;
        this.sentenceCalculationsTransformer = sentenceCalculationsTransformer;
        this.diaryDetailService = diaryDetailService;
        this.chargesService = chargesService;
        this.iepService = iepService;
        this.offenderTransformer = offenderTransformer;
        this.alertsService = alertsService;
        this.bookingTransformer = bookingTransformer;
        this.healthProblemsService = healthProblemsService;
        this.contactPersonsService = contactPersonsService;
        this.offenderService = offenderService;
        this.addressService = addressService;
        this.programmeProfilesService = programmeProfilesService;
        this.individualSchedulesService = individualSchedulesService;
        this.imprisonStatusService = imprisonStatusService;
        this.assessmentService = assessmentService;
        this.sentencesService = sentencesService;
        this.physicalsTransformer = physicalsTransformer;
        this.releaseDetailsTransformer = releaseDetailsTransformer;
    }

    public Optional<OffenderCDE> getCDEByOffenderId(Long offenderId) {
        var now = LocalDateTime.now();
        var today = LocalDate.now();
        final Optional<Offender> maybeOffender = offenderRepository.findById(offenderId);
        final Optional<OffenderBooking> maybeMainBooking = maybeOffender
                .flatMap(o -> o.getOffenderBookings().stream().findFirst());

        var maybeSentenceCalc = mostRecentSentenceCalculationForBookingOf(maybeMainBooking);
        var employments = employmentsOf(maybeMainBooking);
        var movements = movementsOf(maybeMainBooking);
        var diaryDetails = maybeMainBooking.map(b -> diaryDetailService.diaryDetailsOfBooking(b).collect(Collectors.toList())).orElse(Collections.emptyList());
        var charges = maybeMainBooking.map(b -> chargesService.bookingChargesOf(b.getOffenderCharges())).orElse(Collections.emptyList());
        var iepLevels = maybeMainBooking.map(iepService::bookingIepsOf).orElse(Collections.emptyList());
        var alerts = maybeMainBooking.map(alertsService::offenderAlertsForBooking).orElse(Collections.emptyList());
        var healthProblems = maybeMainBooking.map(healthProblemsService::bookingHealthProblemsOf).orElse(Collections.emptyList());
        var contactPersons = maybeMainBooking.map(contactPersonsService::bookingContactPersonsOf).orElse(Collections.emptyList());
        var offenderContactPersons = offenderContactPersonsOf(contactPersons);
        var imprisonmentStatuses = maybeMainBooking.map(imprisonStatusService::bookingOffenderImprisonmentStatusesOf).orElse(Collections.emptyList());
        var offenderSentence = offenderSentenceOf(maybeMainBooking);
        var highestRankedOffence = highestRankedOffenceOf(charges);
        var lastSequentialMovement = lastSequentialMovementOf(movements);

        try {
        var x = maybeOffender.map(offender -> OffenderCDE.builder()
                .activeAlerts(activeAlertsOf(maybeMainBooking))
                .activeTransfers(activeTransfersOf(maybeMainBooking))
                .activityDetails(activityDetailsOf(maybeMainBooking, today))
                .checkHoldAlerts(checkHoldAlertsOf(maybeMainBooking))
                .courtOutcome(courtOutcomeOf(maybeMainBooking))
                .custodyStatus(custodyStatusOf(maybeMainBooking))
                .dischargeEmployment(dischargeEmploymentOf(employments))
                .earliestOutMovementDate(earliestMovementOutOf(movements))
                .earliestReleaseDate(earliestReleaseDateOf(maybeSentenceCalc))
                .earliestSentenceAndConviction(earliestSentenceAndConvictionOf(maybeMainBooking))
                .effectiveSentenceLength(ymdOf(maybeSentenceCalc))
                .employment(employmentOf(employments, maybeMainBooking))
                .firstSequentialMovement(firstSequentialMovementOf(movements))
                .futureDiaryDetails(futureDiaryDetailsOf(diaryDetails, now))
                .highestRankedOffence(highestRankedOffence.orElse(null))
                .IEPLevel(iepLevelOf(iepLevels))
                .isSexOffender(isSexOffenderOf(charges))
                    .lastSequentialMovement(lastSequentialMovement)
                .lastSequentialTransfer(lastSequentialTransferOf(movements))
                .lastSequentialMovementIfOut(lastSequentialMovementIfOutOf(Optional.ofNullable(lastSequentialMovement)))
                .mainAlias(mainAliasOf(maybeMainBooking))
                .mainBooking(maybeMainBooking.map(bookingTransformer::bookingOf).orElse(null))
                .MAPPA(mappaOf(alerts))
                .maternityStatus(maternityStatusOf(healthProblems, now))
                .mostRecentConviction(mostRecentConvictionOf(maybeMainBooking))
                .nextOfKin(nextOfKinOf(contactPersons))
                .notForRelease(notForReleaseOf(alerts))
                .offenderCharges(charges)
                .offenderContactPersons(offenderContactPersons)
                .offenderDischargeAddress(offenderDischargeAddressOf(maybeOffender))
                .offenderEmployed(isEmployedOf(maybeMainBooking, today))
                .offenderEmployments(offenderEmploymentsOf(employments))
                .offenderHomeAddress(offenderHomeAddressOf(maybeOffender))
                .offenderIdentifiers(offenderIdentifiersOf(maybeOffender))
                .offenderImprisonmentStatus(offenderImprisonmentStatusOf(imprisonmentStatuses))
                .offenderLicenses(offenderLicensesOf(maybeMainBooking))
                .offenderManager(offenderManagerOf(offenderContactPersons))
                .offenderReceptionAddress(offenderReceptionAddressOf(maybeOffender))
                .offenderSecurityCategory(offenderSecurityCategoryOf(maybeMainBooking))
                .offenderSentence(offenderSentence.orElse(null))
                .offenderSentenceCalculationDates(offenderSentenceCalculationDatesOf(maybeSentenceCalc))
                .offenderSentenceLength(offenderSentenceLengthOf(maybeSentenceCalc, offenderSentence))
                .otherOffences(otherOffencesOf(charges, highestRankedOffence))
                .offenderSentenceCalculations(maybeSentenceCalc.orElse(null))
                    .physicals(physicalsOf(maybeMainBooking))
                .previousBookingNos(previousBookingNumbersOf(maybeOffender, maybeMainBooking))
                .receptionEmployment(receptionEmploymentOf(employments))
                .releaseDetails(releaseDetailsOf(maybeMainBooking))
                .build());

        return x;} catch (Throwable t) {
            log.error(t.getMessage());
        }
        return null;
    }

    private ReleaseDetails releaseDetailsOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.map(OffenderBooking::getOffenderReleaseDetails)
                .map(releaseDetailsTransformer::releaseDetailsOf)
                .orElse(null);
    }

    private Employment receptionEmploymentOf(List<Employment> employments) {
        return (employments.isEmpty()) ? null : Iterables.getLast(employments);
    }

    private List<String> previousBookingNumbersOf(Optional<Offender> maybeOffender, Optional<OffenderBooking> maybeMainBooking) {
        var mainBookinNo = maybeMainBooking.map(OffenderBooking::getBookingNo).orElse(null);

        return maybeOffender.map(o -> o.getOffenderBookings()
                .stream()
                .map(OffenderBooking::getBookingNo)
                .filter(bno -> !bno.equals(mainBookinNo))
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    private PhysicalsCDE physicalsOf(Optional<OffenderBooking> maybeMainBooking) {

        var physicals = maybeMainBooking.map(physicalsTransformer::physicalsOf);

        return physicals.map(p -> PhysicalsCDE.builder()
                .identifyingMarks(transformIdentifyingMarks(p.getIdentifyingMarks()))
                .profileDetails(transformProfileDetails(p.getProfileDetails()))
                .build()).orElse(null);
    }

    private Map<String, KeyValue> transformProfileDetails(List<ProfileDetails> profileDetails) {

        var builder = ImmutableMap.<String, KeyValue>builder();

        profileDetails.forEach(pd -> builder.put(pd.getProfileType(), KeyValue.builder()
                .code(pd.getProfileCode())
                .description("YOUTH".equals(pd.getProfileType()) ?
                        ("Y".equals(pd.getProfileCode()) ? "YP" : "A")
                        : pd.getProfileDescription())
                .build()));

        return builder.build();
    }

    private IdentifyingMarkCDE transformIdentifyingMarks(List<IdentifyingMark> identifyingMarks) {

        var headMarks = ImmutableList.<IdentifyingMark>builder();
        var bodyMarks = ImmutableList.<IdentifyingMark>builder();
        identifyingMarks.forEach(im -> {
            if (isHead(im.getBodyPartCode())) {
                headMarks.add(im);
            } else {
                bodyMarks.add(im);
            }
        });

        return IdentifyingMarkCDE.builder()
                .HEAD(headMarks.build())
                .BODY(bodyMarks.build())
                .build();
    }

    private boolean isHead(String bodyPartCode) {
        return headBodyParts.contains(bodyPartCode);
    }

    private List<Charge> otherOffencesOf(List<Charge> charges, Optional<Charge> maybeHighestRankedOffence) {
        if (maybeHighestRankedOffence.isEmpty()) {
            return Collections.emptyList();
        }

        return charges
                .stream()
                .filter(oc -> "A".equals(oc.getChargeStatus()) &&
                        !maybeHighestRankedOffence.get().getOffenceCode().equals(oc.getOffenceCode()))
                .collect(Collectors.toList());
    }

    private Integer offenderSentenceLengthOf(Optional<SentenceCalculation> maybeSentenceCalc, Optional<Sentence> maybeSentence) {

        if (maybeSentenceCalc.isEmpty() || maybeSentence.isEmpty()) {
            return null;
        }

        return Period.between(maybeSentence.get().getStartDate(), maybeSentenceCalc.get().getEffectiveSentenceEndDate().toLocalDate()).getDays() + 1;
    }

    private SentenceCalculationDatesCDE offenderSentenceCalculationDatesOf(Optional<SentenceCalculation> maybeSentenceCalc) {
        return maybeSentenceCalc.map(SentenceCalculationDatesCDE::new).orElse(null);
    }

    private Optional<Sentence> offenderSentenceOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.flatMap(b -> sentencesService.bookingSentencesOf(b)
                .stream()
                .filter(Sentence::getIsActive)
                .min(Comparator.comparing(Sentence::getStartDate)));
    }

    private OffenderAssessment offenderSecurityCategoryOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.flatMap(b -> assessmentService.bookingOffenderAssessmentsOf(b)
                .stream()
                .filter(oa -> "APP".equals(oa.getEvaluationResultCode()) &&
                        "A".equals(oa.getAssessStatus()) &&
                        oa.getAssessmentType() != null &&
                        "TYPE".equals(oa.getAssessmentType().getAssessmentClass()) &&
                        "CATEGORY".equals(oa.getAssessmentType().getAssessmentCode()) &&
                        oa.getAssessmentType().getDetermineSupLevelFlag())
                .findFirst())
                .orElse(null);
    }

    private OffenderContactPerson offenderManagerOf(List<OffenderContactPerson> offenderContactPersons) {
        return offenderContactPersons.stream()
                .filter(ocp -> (
                        ocp.getContactPersonType().getContactType().equals("O") &&
                                ocp.getContactPersonType().getRelationshipType().equals("PROB")))
                .findFirst()
                .map(ocp -> ocp.toBuilder().primaryAddressBias("BUS").build())
                .orElse(null);
    }

    private List<Sentence> offenderLicensesOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.map(b -> b.getOffenderSentences()
                .stream()
                .map(sentenceTransformer::sentenceOf)
                .filter(s -> "LICENCE".equals(s.getSentenceCategory()) &&
                        s.getIsActive())
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    private OffenderImprisonmentStatus offenderImprisonmentStatusOf(List<OffenderImprisonmentStatus> imprisonmentStatuses) {
        return imprisonmentStatuses.stream().findFirst().orElse(null);
    }

    private List<Identifier> offenderIdentifiersOf(Optional<Offender> maybeOffender) {
        return maybeOffender.map(offenderTransformer::offenderOf).map(offender -> {

                    final Stream<Identifier> identifierStream1 = Optional.ofNullable(offender.getIdentifiers())
                            .stream()
                            .flatMap(Collection::stream);
                    final Stream<Identifier> identifierStream2 = Optional.ofNullable(offender.getAliases())
                            .stream()
                            .flatMap(Collection::stream)
                            .flatMap(a -> Optional.ofNullable(a.getIdentifiers()).stream().flatMap(Collection::stream));

                    return List.copyOf(Stream.concat(identifierStream1, identifierStream2)
                            .collect(Collectors.toSet()));
                }
        ).orElse(Collections.emptyList());
    }

    private Address offenderAddressOf(Optional<Offender> maybeOffender, String type) {
        return maybeOffender.flatMap(o -> addressService.addressesOf(o)
                .stream()
                .filter(address -> address.getAddressUsages().stream()
                        .anyMatch(au -> au.getActive() &&
                                type.equals(au.getUsage().getCode())))
                .findFirst()).orElse(null);
    }

    private Address offenderHomeAddressOf(Optional<Offender> maybeOffender) {
        return offenderAddressOf(maybeOffender, "HOME");
    }

    private Address offenderReceptionAddressOf(Optional<Offender> maybeOffender) {
        return offenderAddressOf(maybeOffender, "RECEP");
    }

    private List<Employment> offenderEmploymentsOf(List<Employment> employments) {
        return employments.stream().filter(
                employment -> employment.getEmploymentDate() != null &&
                        employment.getTerminationDate() == null).collect(Collectors.toList());
    }

    private Boolean isEmployedOf(Optional<OffenderBooking> maybeMainBooking, LocalDate today) {
        LocalDateTime from = today.atStartOfDay();
        LocalDateTime to = from.toLocalDate().plusDays(1).atStartOfDay().minusNanos(1);
        var unwantedEventSubTypes = ImmutableSet.of("OPA", "R2", "F7");

        var ppEmployed = maybeMainBooking.map(b -> programmeProfilesService.bookingOffenderProgrammeProfilesOf(from, to, b)
                .stream()
                .anyMatch(pp -> {
                    final Activity courseActivity = pp.getCourseActivity();
                    return !pp.getSuspended() &&
                            pp.getOffenderStartDate() != null && pp.getOffenderStartDate().compareTo(today) <= 0 &&
                            pp.getOffenderEndDate() != null && pp.getOffenderEndDate().isAfter(today) &&
                            courseActivity != null &&
                            courseActivity.getOutsideWork() &&
                            courseActivity.getScheduledStartDate() != null && courseActivity.getScheduledStartDate().compareTo(today) <= 0 &&
                            courseActivity.getScheduledEndDate() != null && courseActivity.getScheduledEndDate().isAfter(today);
                })).orElse(false);

        return ppEmployed || maybeMainBooking.map(b -> individualSchedulesService.individualSchedulesOfBooking(b)
                .anyMatch(ois -> "TAP".equals(ois.getEventType()) &&
                        !unwantedEventSubTypes.contains(ois.getEventSubType()) &&
                        !"DEN".equals(ois.getEventStatus()) &&
                        ois.getReturnDateTime() != null && ois.getReturnDateTime().toLocalDate().isBefore(today))).orElse(false);
    }

    private Address offenderDischargeAddressOf(Optional<Offender> maybeOffender) {
        final Set<String> usageCodes = ImmutableSet.of("RELEASE", "DNF", "DUT", "DST", "DPH", "DSH", "DAP", "DBA", "DOH", "DBH");

        return maybeOffender.flatMap(o -> addressService.addressesOf(o)
                .stream()
                .filter(address -> address.getAddressUsages().stream().anyMatch(au -> au.getActive() && usageCodes.contains(au.getUsage().getCode())))
                .findFirst()).orElse(null);

    }

    private List<OffenderContactPerson> offenderContactPersonsOf(List<OffenderContactPerson> contactPersons) {
        return contactPersons.stream()
                .filter(ocp -> ocp.getActive() && ocp.getAddresses()
                        .stream()
                        .findFirst()
                        .filter(a -> a.getEndDate() == null)
                        .isEmpty())
                .map(ocp -> ocp.toBuilder().primaryAddressBias("HOME").build())
                .collect(Collectors.toList());
    }

    private Alert notForReleaseOf(List<Alert> alerts) {
        return alerts.stream()
                .filter(alert -> "ACTIVE".equals(alert.getAlertStatus()) && "X".equals(alert.getAlertType()))
                .findFirst()
                .orElse(null);
    }

    private OffenderContactPerson nextOfKinOf(List<OffenderContactPerson> contactPersons) {
        return contactPersons.stream()
                .filter(OffenderContactPerson::getNextOfKin)
                .findFirst().orElse(null);

    }

    private CourtEvent mostRecentConvictionOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.map(this::bookingConvictionsOf)
                .flatMap(s -> s.max(Comparator.comparing(CourtEvent::getStartDateTime)))
                .filter(ce -> ce.getOutcomeReasonCode() != null)
                .orElse(null);

    }

    private Stream<CourtEvent> bookingConvictionsOf(OffenderBooking offenderBooking) {
        return offenderBooking.getCourtEvents()
                .stream()
                .map(courtEventsTransformer::courtEventOf)
                .filter(courtEvent ->
                        courtEvent.getCourtEventCharges().stream()
                                .flatMap(cec -> cec.getResultCodes().stream())
                                .anyMatch(OffenceResult::getConviction));
    }

    private HealthProblem maternityStatusOf(List<HealthProblem> healthProblems, LocalDateTime now) {
        return healthProblems.stream().filter(
                hp -> "MATSTAT".equals(hp.getProblemType()) &&
                        "ON".equals(hp.getProblemStatus()) &&
                        (hp.getEndDate() == null || hp.getEndDate().isAfter(now.toLocalDate())))
                .findFirst()
                .orElse(null);
    }

    private Alert mappaOf(List<Alert> alerts) {
        return alerts.stream().filter(a -> "P".equals(a.getAlertType()) &&
                "ACTIVE".equals(a.getAlertStatus())).findFirst().orElse(null);
    }

    private OffenderAlias mainAliasOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.flatMap(b -> offenderRepository.findById(b.getOffenderId()))
                .map(offenderTransformer::aliasOf).orElse(null);
    }

    private ExternalMovement lastSequentialMovementIfOutOf(Optional<ExternalMovement> maybeLastMovement) {
        return maybeLastMovement.filter(m -> "OUT".equals(m.getMovementDirection())).orElse(null);
    }

    private ExternalMovement lastSequentialTransferOf(Optional<List<ExternalMovement>> maybeMovements) {
        return maybeMovements.flatMap(movements -> movements
                .stream()
                .filter(m -> "TRN".equals(m.getMovementTypeCode()))
                .max(Comparator.comparing(ExternalMovement::getSequenceNumber))).orElse(null);
    }

    private Boolean isSexOffenderOf(List<Charge> charges) {
        return charges.stream().anyMatch(c -> c.getOffenceIndicatorCodes().contains("S"));
    }

    private IepLevel iepLevelOf(List<OffenderIepLevel> ieps) {
        return ieps.stream().findFirst().map(OffenderIepLevel::getIepLevel).orElse(null);
    }

    private Optional<Charge> highestRankedOffenceOf(List<Charge> charges) {
        var mostSeriousCharges = charges.stream().filter(Charge::getMostSeriousCharge).collect(Collectors.toList());
        return mostSeriousCharges.size() == 1 ?
                Optional.ofNullable(mostSeriousCharges.get(0)) :
                charges.stream().filter(c -> "A".equals(c.getChargeStatus())).findFirst();
    }

    private List<DiaryDetail> futureDiaryDetailsOf(List<DiaryDetail> diaryDetails, LocalDateTime now) {
        return diaryDetails.stream().filter(dd -> dd.getMovementDateTime().compareTo(now) >= 0).collect(Collectors.toList());
    }

    private ExternalMovement firstSequentialMovementOf(Optional<List<ExternalMovement>> maybeMovements) {
        return maybeMovements.flatMap(m -> m.stream().findFirst()).orElse(null);
    }

    private ExternalMovement lastSequentialMovementOf(Optional<List<ExternalMovement>> maybeMovements) {
        return maybeMovements.map(Iterables::getLast).orElse(null);
    }

    private Optional<List<ExternalMovement>> movementsOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.map(offenderBooking -> offenderBooking.getOffenderExternalMovements()
                .stream()
                .map(movementsTransformer::movementOf)
                .sorted(Comparator.comparing(ExternalMovement::getSequenceNumber))
                .collect(Collectors.toList()));
    }

    private Employment employmentOf(List<Employment> employments, Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.flatMap(booking -> employments
                .stream()
                .filter(e -> e.getTerminationDate() == null || e.getTerminationDate().isAfter(booking.getBookingBeginDate().toLocalDateTime().toLocalDate()))
                .max(Comparator.comparing(Employment::getEmploymentSequence)))
                .orElse(null);
    }

    private YMD ymdOf(Optional<SentenceCalculation> maybeSentenceCalculation) {
        return maybeSentenceCalculation.map(SentenceCalculation::getEffectiveSentenceLength).map(YMD::new).orElse(null);
    }

    private EarliestSentenceAndConvictionCDE earliestSentenceAndConvictionOf(Optional<OffenderBooking> maybeMainBooking) {

        var earliestConvictionForBooking = maybeMainBooking.map(offenderBooking -> bookingConvictionsOf(offenderBooking))
                .flatMap(s -> s.min(Comparator.comparing(CourtEvent::getStartDateTime)));

        var earliestSentenceForBooking = maybeMainBooking.flatMap(offenderBooking -> offenderBooking.getCourtEvents()
                .stream()
                .map(courtEventsTransformer::courtEventOf)
                .flatMap(ce -> ce.getCourtEventCharges().stream())
                .flatMap(c -> c.getSentences().stream())
                .min(Comparator.comparing(Sentence::getStartDate)));

        return EarliestSentenceAndConvictionCDE.builder()
                .earliestConviction(earliestConvictionForBooking.orElse(null))
                .earliestSentence(earliestSentenceForBooking.orElse(null))
                .build();

    }

    private LocalDate earliestReleaseDateOf(Optional<SentenceCalculation> maybeSentenceCalc) {
        return asSentenceCalculationDates(maybeSentenceCalc)
                .flatMap(sc -> ImmutableSet.of(sc.getHdced(), sc.getHdcad(), sc.getEtd(), sc.getMtd(), sc.getLtd(), sc.getCrd(),
                        sc.getPed(), sc.getApd(), sc.getNpd(), sc.getArd())
                        .stream()
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .min(Comparator.naturalOrder())
                        .map(LocalDateTime::toLocalDate))
                .orElse(null);
    }

    private Optional<SentenceCalculationDatesCDE> asSentenceCalculationDates(Optional<SentenceCalculation> maybeSentenceCalc) {
        return maybeSentenceCalc.map(SentenceCalculationDatesCDE::new);
    }

    private Optional<SentenceCalculation> mostRecentSentenceCalculationForBookingOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.flatMap(offenderBooking -> offenderBooking.getOffenderSentCalculations()
                .stream()
                .max(Comparator.comparing(OffenderSentCalculation::getOffenderSentCalculationId))
                .map(sentenceCalculationsTransformer::sentenceCalculationOf));
    }

    private LocalDate earliestMovementOutOf(Optional<List<ExternalMovement>> maybeMovements) {
        return maybeMovements
                .flatMap(movements -> movements
                        .stream()
                        .filter(m -> "OUT".equals(m.getMovementDirection()))
                        .min(Comparator.comparing(ExternalMovement::getMovementDateTime)))
                .map(m -> m.getMovementDateTime().toLocalDate())
                .orElse(null);
    }

    private List<Employment> employmentsOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.map(offenderBooking -> offenderBooking.getOffenderEmployments()
                .stream()
                .filter(e -> e.getEmploymentDate() != null && e.getTerminationDate() == null)
                .map(employmentsTransformer::employmentOf)
                .sorted(BY_EMPLOYMENT_SEQUENCE)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    private Employment dischargeEmploymentOf(List<Employment> employments) {
        return employments.stream().findFirst().orElse(null);
    }

    private String custodyStatusOf(Optional<OffenderBooking> maybeMainBooking) {

        final Set<String> notActiveCodes = ImmutableSet.of("ESCP", "UAL", "UAL_ECL");

        if (maybeMainBooking.isEmpty()) {
            return null;
        }

        final OffenderBooking offenderBooking = maybeMainBooking.get();

        String statusReason = offenderBooking.getStatusReason().substring(0, Math.min(offenderBooking.getStatusReason().length(), 4));
        boolean inTransit = "TRN".equals(offenderBooking.getInOutStatus());
        boolean isActive = "Y".equals(offenderBooking.getActiveFlag());
        String inOutStatus = offenderBooking.getInOutStatus();
        Long bookingSequence = offenderBooking.getBookingSeq();

        String a = "";
        String b;

        if (isActive || (!notActiveCodes.contains(statusReason) || inTransit)) {
            a = "Active";
        } else if (bookingSequence == 1) {
            a = "INACTIVE";
        } else if (bookingSequence > 1) {
            a = "HISTORIC";
        }

        if (statusReason.equals("ESCP") || statusReason.equals("UAL")) {
            b = "UAL";
        } else if (statusReason.equals("UAL_ECL")) {
            b = "UAL_ECL";
        } else if (inTransit) {
            b = "In Transit";
        } else {
            b = StringUtils.capitalize(inOutStatus.toLowerCase());
        }

        return a.concat("-").concat(b);
    }

    private CourtEvent courtOutcomeOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.flatMap(ob -> ob.getCourtEvents()
                .stream()
                .filter(ce -> "OUT".equals(ce.getDirectionCode()) && ce.getCaseId() != null)
                .max(Comparator.comparing(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent::getEventDate)
                        .thenComparing(uk.gov.justice.digital.nomis.jpa.entity.CourtEvent::getEventId))
                .map(courtEventsTransformer::courtEventOf))
                .orElse(null);
    }

    private CheckHoldAlertCDE checkHoldAlertsOf(Optional<OffenderBooking> maybeMainBooking) {

        final CheckHoldAlertCDE.CheckHoldAlertCDEBuilder builder = CheckHoldAlertCDE.builder();

        final Set<String> tCodes = ImmutableSet.of("TG", "TAH", "TSE", "TM", "TPR");
        final Set<String> hCodes = ImmutableSet.of("HA");
        final Set<String> vCodes = ImmutableSet.of("V45", "VOP", "V46", "V49G", "V49P");

        maybeMainBooking.map(mb -> {
            final Stream<OffenderAlert> offenderAlertStream = getActiveOffenderAlertStream(mb)
                    .filter(alert -> alert.getAlertType().equals("V") || (alert.getAlertType().equals("T") && tCodes.contains(alert.getAlertCode()) ||
                            alert.getAlertType().equals("H") && hCodes.contains(alert.getAlertCode())));


            return offenderAlertStream
                    .peek(alert -> {
                        String compact = alert.getAlertType().concat("-").concat(alert.getAlertCode());
                        switch (compact) {
                            case "T-TG":
                                builder.T_TG(compact);
                                break;
                            case "T-TAH":
                                builder.T_TAH(compact);
                                break;
                            case "T-TSE":
                                builder.T_TSE(compact);
                                break;
                            case "T-TM":
                                builder.T_TM(compact);
                                break;
                            case "T-TPR":
                                builder.T_TPR(compact);
                                break;
                            case "H-HA":
                                builder.H_HA(compact);
                                builder.SH_STS("Y");
                                builder.SH_Date(alert.getAlertDate().toLocalDateTime().toLocalDate());
                                break;
                            default:
                                break;
                        }

                    })
                    .peek(alert -> {
                        if (alert.getAlertType().equals("V")) {
                            builder.VUL("Y");
                            if (vCodes.contains(alert.getAlertCode())) {
                                builder.V_45_46("Y");
                            }
                        }
                    });
        });

        return builder.build();
    }

    private List<ActivityDetailCDE> activityDetailsOf(Optional<OffenderBooking> maybeMainBooking, LocalDate today) {
        LocalDateTime from = today.atStartOfDay();
        LocalDateTime to = from.toLocalDate().plusDays(1).atStartOfDay().minusNanos(1);

        final List<Exclusion> exclusions = maybeMainBooking.map(
                b -> b.getOffenderExcludeActsSchds().stream().map(attendanceAndExclusionTransformer::exclusionOf).collect(Collectors.toList())).orElse(Collections.emptyList());

        return maybeMainBooking.map(ob -> ob.getOffenderProgramProfiles().stream()
                .filter(opp -> "ALLOC".equals(opp.getOffenderProgramStatus()))
                // Pointless filter:       (opp.suspended ? 'CANC' : 'SCH') !== 'DEL'
                .filter(opp -> !"Y".equals(opp.getSuspendedFlag()))
                .map(opp -> offenderProgrammeProfileTransformer.programmeProfileOf(opp, from, to))
                .filter(pp1 -> Optional.ofNullable(pp1.getCourseActivity()).map(Activity::getActive).orElse(false))
                .map(pp1 -> withoutExcludedSchedules(pp1, exclusions))
                .flatMap(pp -> pp.getCourseActivity().getSchedules()
                        .stream()
                        .map(sch -> ActivityDetailCDE.builder()
                                .description(pp.getCourseActivity().getDescription())
                                .livingUnit(pp.getCourseActivity().getLivingUnit())
                                .eventDate(sch.getScheduledDate())
                                .startTime(sch.getStartTime())
                                .endTime(sch.getEndTime())
                                .build())).collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    private ProgrammeProfile withoutExcludedSchedules(ProgrammeProfile pp, List<Exclusion> exclusions) {
        return pp.toBuilder()
                .courseActivity(
                        pp.getCourseActivity().toBuilder()
                                .schedules(notExcluded(pp, exclusions))
                                .build())
                .build();
    }

    private List<Schedule> notExcluded(ProgrammeProfile programmeProfile, List<Exclusion> exclusions) {
        final List<Schedule> schedules = programmeProfile.getCourseActivity().getSchedules();

        if (exclusions.isEmpty()) {
            return schedules;
        }

        return schedules.stream()
                .filter(
                        schedule -> exclusions.stream()
                                .anyMatch(
                                        exclusion -> schedule.getCatchUpCourseScheduleId() == null &&
                                                (!exclusion.getOffenderProgramProfileId().equals(programmeProfile.getProgramProfileId()) ||
                                                        !exclusion.getCourseActivity().getCourseActivityId().equals(programmeProfile.getCourseActivity().getCourseActivityId()) ||
                                                        !exclusion.getExcludeDay().equals(schedule.getScheduleDay()) &&
                                                                !exclusion.getSlotCategoryCode().equals(schedule.getSlotCategoryCode()))))
                .collect(Collectors.toList());
    }


    private List<ExternalMovement> activeTransfersOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.map(mb -> mb.getOffenderExternalMovements()
                .stream()
                .filter(m -> "Y".equals(m.getActiveFlag()))
                .map(movementsTransformer::movementOf)
                .filter(m -> "TRN".equals(m.getMovementReasonCode()))
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

    }

    private List<Alert> activeAlertsOf(Optional<OffenderBooking> maybeMainBooking) {
        return maybeMainBooking.map(mb -> getActiveOffenderAlertStream(mb)
                .map(alertsTransformer::alertOf)
                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private Stream<OffenderAlert> getActiveOffenderAlertStream(OffenderBooking mb) {
        return mb.getOffenderAlerts()
                .stream()
                .filter(a -> "ACTIVE".equals(a.getAlertStatus()));
    }
}
