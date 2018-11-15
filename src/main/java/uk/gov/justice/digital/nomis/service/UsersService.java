package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.UserDetail;
import uk.gov.justice.digital.nomis.jpa.repository.StaffUserAccountRepository;
import uk.gov.justice.digital.nomis.service.transformer.UserTransformer;

import java.util.Optional;

@Service
public class UsersService {

    private final UserTransformer userTransformer;
    private final StaffUserAccountRepository staffUserAccountRepository;

    @Autowired
    public UsersService(UserTransformer userTransformer, StaffUserAccountRepository staffUserAccountRepository) {
        this.userTransformer = userTransformer;
        this.staffUserAccountRepository = staffUserAccountRepository;
    }


    public Optional<UserDetail> userForUsername(String username) {

        return Optional.ofNullable(staffUserAccountRepository.findOne(username))
                .map(userTransformer::userOf);

    }
}
