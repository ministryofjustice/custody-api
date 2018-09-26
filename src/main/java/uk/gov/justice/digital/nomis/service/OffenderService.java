package uk.gov.justice.digital.nomis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Offender;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OffenderService {

    private final OffenderRepository offenderRepository;
    private final OffenderTransformer offenderTransformer;

    @Autowired
    public OffenderService(OffenderRepository offenderRepository, OffenderTransformer offenderTransformer) {
        this.offenderRepository = offenderRepository;
        this.offenderTransformer = offenderTransformer;
    }

    @Transactional
    public Page<Offender> getOffenders(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.Offender> rootOffendersRawPage = offenderRepository.findAllRootOffenders(pageable);

        List<uk.gov.justice.digital.nomis.jpa.entity.Offender> rootOffenders = rootOffendersRawPage.getContent();

        List<Offender> offenderList = rootOffenders
                .stream()
                .map(offenderTransformer::offenderOf)
                .collect(Collectors.toList());

        return new PageImpl<>(offenderList, pageable, rootOffendersRawPage.getTotalElements());
    }

    @Transactional
    public Optional<Offender> getOffenderByOffenderId(Long offenderId) {
        Optional<uk.gov.justice.digital.nomis.jpa.entity.Offender> maybeOffender = Optional.ofNullable(offenderRepository.findOne(offenderId));

        return maybeOffender.map(offenderTransformer::offenderOf);
    }

    @Transactional
    public Optional<Offender> getOffenderByNomsId(String nomsId) {
        final Optional<uk.gov.justice.digital.nomis.jpa.entity.Offender> maybeOffender = offenderRepository.findByNomsId(nomsId);

        return maybeOffender.map(offenderTransformer::offenderOf);
    }

}
