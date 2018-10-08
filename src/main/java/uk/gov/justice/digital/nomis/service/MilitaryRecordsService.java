package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.MilitaryRecord;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderMilitaryRecord;
import uk.gov.justice.digital.nomis.jpa.filters.OffenderMilitaryRecordFilter;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderMilitaryRecordsRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.MilitaryRecordsTransformer;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MilitaryRecordsService {
    private static final Comparator<OffenderMilitaryRecord> BY_SEQUENCE = Comparator
            .comparingLong((OffenderMilitaryRecord oem) -> oem.getId().getMilitarySeq())
            .reversed();
    private final OffenderMilitaryRecordsRepository offenderMilitaryRecordsRepository;
    private final OffenderRepository offenderRepository;
    private final MilitaryRecordsTransformer militaryRecordsTransformer;

    @Autowired
    public MilitaryRecordsService(OffenderMilitaryRecordsRepository offenderMilitaryRecordsRepository,
                                  OffenderRepository offenderRepository,
                                  MilitaryRecordsTransformer militaryRecordsTransformer) {
        this.offenderMilitaryRecordsRepository = offenderMilitaryRecordsRepository;
        this.offenderRepository = offenderRepository;
        this.militaryRecordsTransformer = militaryRecordsTransformer;
    }

    @Transactional
    public Page<MilitaryRecord> getMilitaryRecords(Pageable pageable, Optional<LocalDateTime> maybeFrom, Optional<LocalDateTime> maybeTo) {
        OffenderMilitaryRecordFilter offenderMilitaryRecordFilter = OffenderMilitaryRecordFilter.builder()
                .from(maybeFrom)
                .to(maybeTo)
                .build();

        Page<OffenderMilitaryRecord> offenderMilitaryRecords = offenderMilitaryRecordsRepository.findAll(offenderMilitaryRecordFilter, pageable);

        List<MilitaryRecord> offenderMilitaryRecordList = offenderMilitaryRecords.getContent()
                .stream()
                .sorted(BY_SEQUENCE)
                .map(militaryRecordsTransformer::militaryRecordOf)
                .collect(Collectors.toList());

        return new PageImpl<>(offenderMilitaryRecordList, pageable, offenderMilitaryRecords.getTotalElements());
    }

    @Transactional
    public Optional<List<MilitaryRecord>> getMilitaryRecordsForOffenderId(Long offenderId) {
        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderBookings()
                        .stream()
                        .map(OffenderBooking::getOffenderMilitaryRecords)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList()))
                .map(offenderMilitaryRecords -> offenderMilitaryRecords
                .stream()
                .sorted(BY_SEQUENCE)
                .map(militaryRecordsTransformer::militaryRecordOf)
                .collect(Collectors.toList()));
    }

    public Optional<List<MilitaryRecord>> getMilitaryRecordsForOffenderIdAndBookingId(Long offenderId, Long bookingId) {
        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .flatMap(offender -> offender.getOffenderBookings()
                        .stream()
                        .filter(ob -> ob.getOffenderBookId().equals(bookingId))
                        .findFirst())
                .map(ob -> ob.getOffenderMilitaryRecords()
                        .stream()
                        .sorted(BY_SEQUENCE)
                        .map(militaryRecordsTransformer::militaryRecordOf)
                        .collect(Collectors.toList()));

    }
}
