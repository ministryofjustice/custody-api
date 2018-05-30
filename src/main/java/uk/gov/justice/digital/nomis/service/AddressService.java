package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.jpa.entity.AddressUsage;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAddress;
import uk.gov.justice.digital.nomis.jpa.repository.AddressRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AddressesTransformer;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Service
public class AddressService {

    private static final Comparator<uk.gov.justice.digital.nomis.jpa.entity.Address> BY_ADDRESS_PRIORITY = Comparator.comparing(uk.gov.justice.digital.nomis.jpa.entity.Address::getPrimaryFlag)
            .thenComparing(a -> Optional.ofNullable(a.getModifyDatetime()).orElse(new Timestamp(0)))
            .thenComparing(a -> Optional.ofNullable(a.getCreateDatetime()).orElse(new Timestamp(0)))
            .reversed();

    private final AddressRepository addressRepository;
    private final OffenderRepository offenderRepository;
    private final AddressesTransformer addressesTransformer;

    @Autowired
    public AddressService(AddressRepository addressRepository, OffenderRepository offenderRepository, AddressesTransformer addressesTransformer) {
        this.addressRepository = addressRepository;
        this.offenderRepository = offenderRepository;
        this.addressesTransformer = addressesTransformer;
    }

    @Transactional
    public Page<Address> getAddresses(Pageable pageable) {
        Page<uk.gov.justice.digital.nomis.jpa.entity.Address> rawAddressPage = addressRepository.findAll(pageable);

        List<Address> addresses = rawAddressPage.getContent().stream()
                .sorted(BY_ADDRESS_PRIORITY)
                .map(addressesTransformer::addressOf)
                .collect(Collectors.toList());

        return new PageImpl<>(addresses, pageable, rawAddressPage.getTotalElements());
    }

    @Transactional
    public Optional<List<Address>> getOffenderAddresses(Long offenderId) {
        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderAddresses()
                        .stream()
                        .sorted(Comparator.comparing(OffenderAddress::getPrimaryFlag)
                                .thenComparing(a -> Optional.ofNullable(a.getEndDate()).orElse(new Timestamp(0)))
                                .thenComparing(a -> Optional.ofNullable(a.getAddressUsage()).map(AddressUsage::getActiveFlag).orElse("N"))
                                .thenComparing(this::getLastModifiedDate)
                                .reversed())
                        .map(addressesTransformer::addressOf)
                        .collect(Collectors.toList()));
    }

    private Timestamp getLastModifiedDate(OffenderAddress address) {
        return Stream.of(
                Optional.ofNullable(address.getModifyDatetime()).orElse(new Timestamp(0)),
                Optional.ofNullable(address.getCreateDatetime()).orElse(new Timestamp(0)),
                Optional.ofNullable(address.getAddressUsage()).map(AddressUsage::getModifyDatetime).orElse(new Timestamp(0)),
                Optional.ofNullable(address.getAddressUsage()).map(AddressUsage::getCreateDatetime).orElse(new Timestamp(0))
        ).max(comparing(Timestamp::getTime)).get();
    }

}
