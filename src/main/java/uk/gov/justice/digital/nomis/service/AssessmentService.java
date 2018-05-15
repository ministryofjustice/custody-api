package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.ExternalMovement;
import uk.gov.justice.digital.nomis.api.OffenderAssessment;
import uk.gov.justice.digital.nomis.jpa.entity.Offender;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderAssessmentRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AssessmentsTransformer;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssessmentService {

    private final OffenderAssessmentRepository offenderAssessmentRepository;
    private final OffenderRepository offenderRepository;
    private final AssessmentsTransformer assessmentsTransformer;

    @Autowired
    public AssessmentService(OffenderAssessmentRepository offenderAssessmentRepository, OffenderRepository offenderRepository, AssessmentsTransformer assessmentsTransformer) {
        this.offenderAssessmentRepository = offenderAssessmentRepository;
        this.offenderRepository = offenderRepository;
        this.assessmentsTransformer = assessmentsTransformer;
    }

    @Transactional
    public Page<OffenderAssessment> getAssessments(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.OffenderAssessment> rawOffenderAssessmentsPage = offenderAssessmentRepository.findAll(pageable);

        List<OffenderAssessment> assessments = rawOffenderAssessmentsPage.getContent()
                .stream()
                .map(assessmentsTransformer::assessmentOf)
                .collect(Collectors.toList());

        return new PageImpl<>(assessments, pageable, rawOffenderAssessmentsPage.getTotalElements());
    }

    @Transactional
    public Optional<List<OffenderAssessment>> getOffenderAssessments(Long offenderId) {
        Optional<List<uk.gov.justice.digital.nomis.jpa.entity.OffenderAssessment>> maybeOffenderAssessments = Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender ->
                        offender.getOffenderBookings()
                                .stream()
                                .map(OffenderBooking::getOffenderAssessments)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList()));

        return maybeOffenderAssessments.map(offenderAssessments -> offenderAssessments
                .stream()
                .map(assessmentsTransformer::assessmentOf)
                .sorted(byAssessmentDate())
                .collect(Collectors.toList()));
    }

    public Optional<List<OffenderAssessment>> assessmentsForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        Optional<Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        if (!maybeOffender.isPresent()) {
            return Optional.empty();
        }

        Optional<OffenderBooking> maybeOffenderBooking = maybeOffender.get().getOffenderBookings()
                .stream()
                .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                .findFirst();

        return maybeOffenderBooking.map(ob -> ob.getOffenderAssessments()
                .stream()
                .map(assessmentsTransformer::assessmentOf)
                .sorted(byAssessmentDate())
                .collect(Collectors.toList()));
    }

    private Comparator<OffenderAssessment> byAssessmentDate() {
        return Comparator
                .comparing(OffenderAssessment::getAssessStatus)
                .thenComparing(OffenderAssessment::getAssessmentDate).reversed() // DESC
                .thenComparingInt(OffenderAssessment::getAssessmentSequence); // ASC
    }

}
