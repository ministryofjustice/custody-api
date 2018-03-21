package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.jpa.entity.AddressUsage;
import uk.gov.justice.digital.nomis.jpa.repository.AddressRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final OffenderRepository offenderRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, OffenderRepository offenderRepository) {
        this.addressRepository = addressRepository;
        this.offenderRepository = offenderRepository;
    }

    @Transactional
    public Page<Address> getAddresses(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.Address> rawAddressPage = addressRepository.findAll(pageable);

        List<Address> addresses = rawAddressPage.getContent().stream().map(
                this::addressOf
        ).collect(Collectors.toList());

        return new PageImpl<>(addresses, pageable, rawAddressPage.getTotalElements());
    }

    private Address addressOf(uk.gov.justice.digital.nomis.jpa.entity.Address address) {
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
                .mail(ynToBoolean(address.getMailFlag()))
                .noFixedAddress(ynToBoolean(address.getNoFixedAddressFlag()))
                .ownerClass(address.getOwnerClass())
                .ownerSeq(address.getOwnerSeq())
                .postalCode(address.getPostalCode())
                .premise(address.getPremise())
                .primary(ynToBoolean(address.getPrimaryFlag()))
                .services(ynToBoolean(address.getServicesFlag()))
                .specialNeedsCode(address.getSpecialNeedsCode())
                .street(address.getStreet())
                .type(address.getType())
                .validatedPaf(ynToBoolean(address.getValidatedPafFlag()))
                .build();
    }

    @Transactional
    public Optional<List<Address>> getOffenderAddresses(Long offenderId) {
        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderAddresses()
                        .stream()
                        .map(this::addressOf)
                        .collect(Collectors.toList()));
    }

    private Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn).map("Y"::equalsIgnoreCase).orElse(null);
    }


}
