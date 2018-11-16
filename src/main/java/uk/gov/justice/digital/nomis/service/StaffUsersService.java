package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.StaffUser;
import uk.gov.justice.digital.nomis.jpa.entity.StaffUserAccount;
import uk.gov.justice.digital.nomis.jpa.repository.StaffUserAccountRepository;
import uk.gov.justice.digital.nomis.service.transformer.StaffUserTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffUsersService {

    private final StaffUserTransformer staffUserTransformer;
    private final StaffUserAccountRepository staffUserAccountRepository;

    @Autowired
    public StaffUsersService(StaffUserTransformer staffUserTransformer, StaffUserAccountRepository staffUserAccountRepository) {
        this.staffUserTransformer = staffUserTransformer;
        this.staffUserAccountRepository = staffUserAccountRepository;
    }


    public Optional<StaffUser> staffUserForUsername(String username) {

        return Optional.ofNullable(staffUserAccountRepository.findOne(username))
                .map(staffUserTransformer::userOf);
    }

    public Page<StaffUser> getStaffUsers(Pageable pageable) {
        Page<StaffUserAccount> staffUsers = staffUserAccountRepository.findAll(pageable);

        List<StaffUser> staffUserList = staffUsers.getContent()
                .stream()
                .map(staffUserTransformer::userOf)
                .collect(Collectors.toList());

        return new PageImpl<>(staffUserList, pageable, staffUsers.getTotalElements());
    }
}
