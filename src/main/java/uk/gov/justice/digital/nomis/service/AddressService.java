package uk.gov.justice.digital.nomis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.justice.digital.nomis.api.Address;
import uk.gov.justice.digital.nomis.jpa.repository.AddressRepository;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderRepository;
import uk.gov.justice.digital.nomis.service.transformer.AddressesTransformer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                        .map(addressesTransformer::addressOf)
                        .collect(Collectors.toList()));
    }

}
