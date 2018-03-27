package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAddress;
import uk.gov.justice.digital.nomis.jpa.repository.AddressRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AddressesTransformer;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class AddressService {

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

        List<Address> addresses = rawAddressPage.getContent().stream().map(
                addressesTransformer::addressOf
        ).collect(Collectors.toList());

        return new PageImpl<>(addresses, pageable, rawAddressPage.getTotalElements());
    }

    @Transactional
    public Optional<List<Address>> getOffenderAddresses(Long offenderId) {
        return Optional.ofNullable(offenderRepository.findOne(offenderId))
                .map(offender -> offender.getOffenderAddresses()
                        .stream()
                        .sorted(byOffenderAddressPriority().reversed())
                        .map(addressesTransformer::addressOf)
                        .collect(Collectors.toList()));
    }

    private Comparator<OffenderAddress> byOffenderAddressPriority() {
        return (o1, o2) -> {
            int compare = o1.getPrimaryFlag().compareTo(o2.getPrimaryFlag());
            if (compare != 0) {
                return compare;
            }
            compare = o1.getEndDate().compareTo(o2.getEndDate());
            if (compare != 0) {
                return compare;
            }
            compare = o1.getAddressUsage().getActiveFlag().compareTo(o2.getAddressUsage().getActiveFlag());
            if (compare != 0) {
                return compare;
            }
            return getLastModifiedDate(o1).compareTo(getLastModifiedDate(o2));
        };
    }

    private Timestamp getLastModifiedDate(OffenderAddress address) {
        return Arrays.asList(
                address.getModifyDatetime(),
                address.getCreateDatetime(),
                address.getAddressUsage().getModifyDatetime(),
                address.getAddressUsage().getCreateDatetime()
        ).stream().max(comparing(Timestamp::getTime)).get();
    }

}
