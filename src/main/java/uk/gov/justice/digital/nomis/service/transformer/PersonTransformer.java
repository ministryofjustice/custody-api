package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.api.Person;
import uk.gov.justice.digital.nomis.jpa.entity.PersonAddress;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Component
public class PersonTransformer {

    private final AddressesTransformer addressesTransformer;
    private final TypesTransformer typesTransformer;

    @Autowired
    public PersonTransformer(AddressesTransformer addressesTransformer, TypesTransformer typesTransformer) {
        this.addressesTransformer = addressesTransformer;
        this.typesTransformer = typesTransformer;
    }

    public Person personOf(uk.gov.justice.digital.nomis.jpa.entity.Person person) {
        return Optional.ofNullable(person)
                .map(p -> Person.builder()
                        .aliasPersonId(p.getAliasPersonId())
                        .attention(p.getAttention())
                        .birthPlace(p.getBirthPlace())
                        .careOf(p.getCareOf())
                        .citizenship(p.getCitizenship())
                        .comprehendEnglish(typesTransformer.ynToBoolean(p.getComprehendEnglishFlag()))
                        .coronerNumber(p.getCoronerNumber())
                        .criminalHistoryText(p.getCriminalHistoryText())
                        .dateOfBirth(typesTransformer.localDateOf(p.getBirthdate()))
                        .dateOfBirth(typesTransformer.localDateOf(p.getDeceasedDate()))
                        .employer(p.getEmployer())
                        .firstName(p.getFirstName())
                        .interpreterRequired(typesTransformer.ynToBoolean(p.getInterpreterRequired()))
                        .keepBiometrics(typesTransformer.ynToBoolean(p.getKeepBiometrics()))
                        .languageCode(p.getLanguageCode())
                        .lastName(p.getLastName())
                        .maritalStatus(p.getMaritalStatus())
                        .memoText(p.getMemoText())
                        .middleName(p.getMiddleName())
                        .nameType(p.getNameType())
                        .nameSequence(p.getNameSequence())
                        .occupationCode(p.getOccupationCode())
                        .personId(p.getPersonId())
                        .primaryLanguageCode(p.getPrimaryLanguageCode())
                        .profileCode(p.getProfileCode())
                        .remitter(typesTransformer.ynToBoolean(p.getRemitterFlag()))
                        .rootPersonId(p.getRootPersonId())
                        .sex(p.getSex())
                        .staff(typesTransformer.ynToBoolean(p.getStaffFlag()))
                        .suspended(typesTransformer.ynToBoolean(p.getSuspendedFlag()))
                        .suspendedDate(typesTransformer.localDateOf(p.getSuspendedDate()))
                        .title(p.getTitle())
                        .build())
                .orElse(null);
    }

    public List<Address> addressesOf(uk.gov.justice.digital.nomis.jpa.entity.Person person) {
        return Optional.ofNullable(person)
                .map(p -> p.getAddresses()
                        .stream()
                        .sorted(byPersonAddressPriority())
                        .map(addressesTransformer::addressOf)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private Comparator<PersonAddress> byPersonAddressPriority() {
        return Comparator.comparing(PersonAddress::getPrimaryFlag)
                .thenComparing(a -> Optional.ofNullable(a.getEndDate()).orElse(new Timestamp(0))).reversed()
                .thenComparing(a -> Optional.ofNullable(a.getAddressUsage()).map(x -> x.getActiveFlag()).orElse("N"))
                .thenComparing(this::getLastModifiedDate);
    }

    private Timestamp getLastModifiedDate(PersonAddress address) {
        return Stream.of(
                Optional.ofNullable(address.getModifyDatetime()).orElse(new Timestamp(0)),
                Optional.ofNullable(address.getCreateDatetime()).orElse(new Timestamp(0)),
                Optional.ofNullable(address.getAddressUsage()).map(x -> x.getModifyDatetime()).orElse(new Timestamp(0)),
                Optional.ofNullable(address.getAddressUsage()).map(x -> x.getCreateDatetime()).orElse(new Timestamp(0))
        ).max(comparing(Timestamp::getTime)).get();
    }
}
