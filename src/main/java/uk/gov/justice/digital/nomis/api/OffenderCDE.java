package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Value
@Builder
public class OffenderCDE {

    private Booking mainBooking;
    @Builder.Default
    private List<String> previousBookingNos = Collections.emptyList();
    private String custodyStatus;
    @Builder.Default
    private OffenderAlias mainAlias = OffenderAlias.builder().build();
    @Builder.Default
    private List<Identifier> offenderIdentifiers = Collections.emptyList();
    @Builder.Default
    private OffenderAssessment offenderSecurityCategory = OffenderAssessment.builder().build();
    @Builder.Default
    private Sentence offenderSentence = Sentence.builder().build();
    @Builder.Default
    private List<Sentence> offenderLicenses = Collections.emptyList();
    @Builder.Default
    private SentenceCalculation offenderSentenceCalculations = SentenceCalculation.builder().build();
    private SentenceCalculationDatesCDE offenderSentenceCalculationDates;
    private YMD effectiveSentenceLength;
    private Integer offenderSentenceLength;
    private LocalDate earliestReleaseDate;
    @Builder.Default
    private List<ExternalMovement> activeTransfers = Collections.emptyList();
    @Builder.Default
    private ExternalMovement firstSequentialMovement = ExternalMovement.builder().build();
    @Builder.Default
    private ExternalMovement lastSequentialMovement = ExternalMovement.builder().build();
    @Builder.Default
    private ExternalMovement lastSequentialMovementIfOut = ExternalMovement.builder().build();
    @Builder.Default
    private ExternalMovement lastSequentialTransfer = ExternalMovement.builder().build();
    private LocalDate earliestOutMovementDate;
    @Builder.Default
    private List<Employment> offenderEmployments = Collections.emptyList();
    @Builder.Default
    private Employment employment = Employment.builder().build();
    @Builder.Default
    private Employment dischargeEmployment = Employment.builder().build();
    @Builder.Default
    private Employment receptionEmployment = Employment.builder().build();
    @Builder.Default
    private List<Charge> offenderCharges = Collections.emptyList();
    private Boolean isSexOffender;
    private Charge highestRankedOffence;
    @Builder.Default
    private List<Charge> otherOffences = Collections.emptyList();
    @Builder.Default
    private List<OffenderContactPerson> offenderContactPersons = Collections.emptyList();
    private OffenderContactPerson nextOfKin;
    private OffenderContactPerson offenderManager;
    private Address offenderHomeAddress;
    private Address offenderReceptionAddress;
    private Address offenderDischargeAddress;
    @Builder.Default
    private List<Alert> activeAlerts = Collections.emptyList();
    private Alert notForRelease;
    private Alert MAPPA;
    @Builder.Default
    private CheckHoldAlertCDE checkHoldAlerts = CheckHoldAlertCDE.builder().build();
    private PhysicalsCDE physicals;
    private IepLevel IEPLevel;
    private OffenderImprisonmentStatus offenderImprisonmentStatus;
    private ReleaseDetails releaseDetails;
    private CourtEvent mostRecentConviction;
    private EarliestSentenceAndConvictionCDE earliestSentenceAndConviction;
    private CourtEvent courtOutcome;
    private HealthProblem maternityStatus;
    private Boolean offenderEmployed;
    private List<DiaryDetail> futureDiaryDetails;
    @Builder.Default
    private List<ActivityDetailCDE> activityDetails = Collections.emptyList();
}
