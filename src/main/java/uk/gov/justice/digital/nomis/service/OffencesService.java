package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.Offence;
import uk.gov.justice.digital.nomis.jpa.repository.OffencesRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenceTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffencesService {

    private final OffencesRepository offencesRepository;
    private final OffenceTransformer offenceTransformer;

    @Autowired
    public OffencesService(OffencesRepository offencesRepository, OffenceTransformer offenceTransformer) {
        this.offencesRepository = offencesRepository;
        this.offenceTransformer = offenceTransformer;
    }

    public Page<Offence> getOffences(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.Offence> rawOffencesPage = offencesRepository.findAll(pageable);

        List<Offence> offences = rawOffencesPage.getContent().stream().map(offenceTransformer::offenceOf).collect(Collectors.toList());

        return new PageImpl<>(offences, pageable, rawOffencesPage.getTotalElements());
    }
}
