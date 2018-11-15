package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.UserDetail;
import uk.gov.justice.digital.nomis.jpa.entity.StaffUserAccount;

import java.util.stream.Collectors;

@Component
public class UserTransformer {

    private final CaseloadTransformer caseloadTransformer;

    @Autowired
    public UserTransformer(CaseloadTransformer caseloadTransformer) {
        this.caseloadTransformer = caseloadTransformer;
    }

    public UserDetail userOf(StaffUserAccount staffUserAccount) {
        return UserDetail.builder()
                .username(staffUserAccount.getUsername())
                .activeCaseload(staffUserAccount.getActiveCaseload() != null ? caseloadTransformer.caseloadsOf(staffUserAccount.getActiveCaseload()) : null)
                .caseloads(staffUserAccount.getCaseloads().stream()
                        .map(cl -> caseloadTransformer.caseloadsOf(cl.getCaseload())).collect(Collectors.toList()))
                .build();
    }


}
