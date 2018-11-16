package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.StaffUser;
import uk.gov.justice.digital.nomis.jpa.entity.StaffUserAccount;

import java.util.stream.Collectors;

@Component
public class StaffUserTransformer {

    private final CaseloadTransformer caseloadTransformer;

    @Autowired
    public StaffUserTransformer(CaseloadTransformer caseloadTransformer) {
        this.caseloadTransformer = caseloadTransformer;
    }

    public StaffUser userOf(StaffUserAccount staffUserAccount) {
        StaffUser staffUser = StaffUser.builder()
                .username(staffUserAccount.getUsername())
                .firstName(staffUserAccount.getStaff().getFirstName())
                .lastName(staffUserAccount.getStaff().getLastName())
                .staffId(staffUserAccount.getStaff().getStaffId())
                .status(staffUserAccount.getStaff().getStatus())
                .caseloads(staffUserAccount.getCaseloads().stream()
                        .collect(Collectors.toMap(uac -> uac.getCaseload().getId(), uac -> caseloadTransformer.caseloadsOf(uac.getCaseload()))))
                .build();

        // mark which caseload is current
        if (staffUserAccount.getActiveCaseload() != null) {
            staffUser.getCaseloads().values().stream()
                    .filter(c -> c.getId().equals(staffUserAccount.getActiveCaseload().getId()))
                    .findFirst()
                    .ifPresent(f -> f.setCurrentActive(true));

            staffUser.setActiveCaseload(staffUserAccount.getActiveCaseload().getId());
        }

        return staffUser;


    }


}
