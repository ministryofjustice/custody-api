package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.api.KeyValue;
import uk.gov.justice.digital.nomis.api.Phone;
import uk.gov.justice.digital.nomis.jpa.entity.AddressPhone;
import uk.gov.justice.digital.nomis.jpa.entity.AddressUsage;
import uk.gov.justice.digital.nomis.jpa.entity.ReferenceCode;
import uk.gov.justice.digital.nomis.jpa.entity.ReferenceCodePK;
import uk.gov.justice.digital.nomis.jpa.repository.ReferenceCodesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AddressesTransformer {

    private static final String CITY = "CITY";
    private static final String COUNTY = "COUNTY";
    private static final String COUNTRY = "COUNTRY";

    private final TypesTransformer typesTransformer;
    private final ReferenceCodesRepository referenceCodesRepository;

    @Autowired
    public AddressesTransformer(TypesTransformer typesTransformer, ReferenceCodesRepository referenceCodesRepository) {
        this.typesTransformer = typesTransformer;
        this.referenceCodesRepository = referenceCodesRepository;
    }

    public Address addressOf(uk.gov.justice.digital.nomis.jpa.entity.Address address) {
        return Address.builder()
                .addressId(address.getAddressId())
                .addressType(address.getAddressType())
                .addressUsages(addressUsagesOf(address.getAddressUsages()))
                .businessHour(address.getBusinessHour())
                .capacity(address.getCapacity())
                .cityCode(address.getCityCode())
                .city(cityOf(address))
                .cityName(address.getCityName())
                .comments(address.getCommentText())
                .contactPersonName(address.getContactPersonName())
                .countyCode(address.getCountyCode())
                .county(countyOf(address))
                .countryCode(address.getCountryCode())
                .country(countryOf(address))
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
                .phones(phonesOf(address.getPhones()))
                .build();
    }

    private KeyValue cityOf(uk.gov.justice.digital.nomis.jpa.entity.Address address) {
        return referenceOf(address.getCityCode(), CITY);
    }

    private KeyValue countyOf(uk.gov.justice.digital.nomis.jpa.entity.Address address) {
        return referenceOf(address.getCountyCode(), COUNTY);
    }

    private KeyValue countryOf(uk.gov.justice.digital.nomis.jpa.entity.Address address) {
        return referenceOf(address.getCountryCode(), COUNTRY);
    }

    private KeyValue referenceOf(String code, String domain) {
        return Optional.ofNullable(referenceCodesRepository.findOne(ReferenceCodePK.builder().code(code).domain(domain).build()))
                .map(rc -> KeyValue.builder().code(rc.getCode()).description(rc.getDescription()).build())
                .orElse(null);
    }

    private List<uk.gov.justice.digital.nomis.api.AddressUsage> addressUsagesOf(List<AddressUsage> addressUsages) {
        return Optional.ofNullable(addressUsages).map(
                usages -> usages.stream().map(
                        usage -> uk.gov.justice.digital.nomis.api.AddressUsage.builder()
                                .active(typesTransformer.ynToBoolean(usage.getActiveFlag()))
                                .usage(usage.getAddressUsage())
                                .build()
                ).collect(Collectors.toList())).orElse(null);
    }

    private List<Phone> phonesOf(List<AddressPhone> phones) {
        return phones
                .stream()
                //todo: sort phones
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
                .collect(Collectors.toList());
    }
}