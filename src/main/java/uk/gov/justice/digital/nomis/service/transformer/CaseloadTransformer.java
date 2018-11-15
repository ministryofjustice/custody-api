package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Caseload;

import java.util.stream.Collectors;

@Component
public class CaseloadTransformer {

    private final ReferenceDataTransformer referenceDataTransformer;

    @Autowired
    public CaseloadTransformer(ReferenceDataTransformer referenceDataTransformer) {
        this.referenceDataTransformer = referenceDataTransformer;
    }

    public Caseload caseloadsOf(uk.gov.justice.digital.nomis.jpa.entity.Caseload caseload) {
        return Caseload.builder()
                .id(caseload.getId())
                .name(caseload.getName())
                .type(caseload.getType())
                .agencyLocations(caseload.getAgencyLocations().stream().map(agency -> referenceDataTransformer.agencyLocationOf(agency.getAgencyLocation())).collect(Collectors.toList()))
                .build();
    }


}
