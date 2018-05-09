package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.api.Phone;
import uk.gov.justice.digital.nomis.jpa.entity.AddressPhone;
import uk.gov.justice.digital.nomis.jpa.entity.AddressUsage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AddressesTransformer {

    private final TypesTransformer typesTransformer;

    @Autowired
    public AddressesTransformer(TypesTransformer typesTransformer) {
        this.typesTransformer = typesTransformer;
    }

    public Address addressOf(uk.gov.justice.digital.nomis.jpa.entity.Address address) {
        return Address.builder()
                .addressId(address.getAddressId())
                .addressType(address.getAddressType())
                .addressUsage(Optional.ofNullable(address.getAddressUsage()).map(AddressUsage::getAddressUsage).orElse(null))
                .businessHour(address.getBusinessHour())
                .capacity(address.getCapacity())
                .cityCode(address.getCityCode())
                .cityName(address.getCityName())
                .comments(address.getCommentText())
                .contactPersonName(address.getContactPersonName())
                .countryCode(address.getCountryCode())
                .flat(address.getFlat())
                .relationship(address.getRelationship())
                .locality(address.getLocality())
                .mail(typesTransformer.ynToBoolean(address.getMailFlag()))
                .noFixedAddress(typesTransformer.ynToBoolean(address.getNoFixedAddressFlag()))
                .ownerClass(address.getOwnerClass())
                .ownerSeq(address.getOwnerSeq())
                .postalCode(address.getPostalCode())
                .premise(address.getPremise())
                .primary(typesTransformer.ynToBoolean(address.getPrimaryFlag()))
                .services(typesTransformer.ynToBoolean(address.getServicesFlag()))
                .specialNeedsCode(address.getSpecialNeedsCode())
                .street(address.getStreet())
                .type(address.getType())
                .validatedPaf(typesTransformer.ynToBoolean(address.getValidatedPafFlag()))
                .startDate(typesTransformer.localDateOf(address.getStartDate()))
                .endDate(typesTransformer.localDateOf(address.getEndDate()))
                .active(typesTransformer.ynToBoolean(Optional.ofNullable(address.getAddressUsage()).map(AddressUsage::getActiveFlag).orElse(null)))
                .phones(phonesOf(address.getPhones()))
                .build();
    }

    private List<Phone> phonesOf(List<AddressPhone> phones) {
        return phones
                .stream()
                .map(phone -> Phone.builder()
                        .extNo(phone.getExtNo())
                        .ownerClass(phone.getOwnerClass())
                        .ownerCode(phone.getOwnerCode())
                        .ownerSeq(phone.getOwnerSeq())
                        .phoneId(phone.getPhoneId())
                        .phoneNo(phone.getPhoneNo())
                        .phoneType(phone.getPhoneType())
                        .relationship(phone.getRelationship())
                        .build())
                //todo: sort phones
                .collect(Collectors.toList());
    }
}