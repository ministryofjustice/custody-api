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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Service
public class AddressService {

    public static final Timestamp EARLIEST = new Timestamp(0);

    private static final Comparator<uk.gov.justice.digital.nomis.jpa.entity.Address> BY_ADDRESS_PRIORITY =
            Comparator.comparing((uk.gov.justice.digital.nomis.jpa.entity.Address a) -> Optional.ofNullable(a.getPrimaryFlag()).orElse("N"))
                    .thenComparing(a -> Optional.ofNullable(a.getModifyDatetime()).orElse(EARLIEST))
                    .thenComparing(a -> Optional.ofNullable(a.getCreateDatetime()).orElse(EARLIEST))
                    .reversed();

    private static final Function<List<AddressUsage>, Timestamp> LATEST_CREATED_DATE_OF = (List<AddressUsage> usages) ->
            usages.stream().
                    map(x -> Optional.ofNullable(x.getCreateDatetime()).orElse(EARLIEST))
                    .max(comparing(Timestamp::getTime)).orElse(EARLIEST);

    private static final Function<List<AddressUsage>, Timestamp> LATEST_MODIFIED_DATE_OF = (List<AddressUsage> usages) ->
            usages.stream().
                    map(x -> Optional.ofNullable(x.getModifyDatetime()).orElse(EARLIEST))
                    .max(comparing(Timestamp::getTime)).orElse(EARLIEST);

    private static Function<OffenderAddress, Timestamp> LAST_MODIFIED_DATE_OF = (OffenderAddress address) ->
            Stream.of(
                    Optional.ofNullable(address.getModifyDatetime()).orElse(EARLIEST),
                    Optional.ofNullable(address.getCreateDatetime()).orElse(EARLIEST),
                    LATEST_MODIFIED_DATE_OF.apply(address.getAddressUsages()),
                    LATEST_CREATED_DATE_OF.apply(address.getAddressUsages()))
                    .max(comparing(Timestamp::getTime)).get();

    private static final Comparator<uk.gov.justice.digital.nomis.jpa.entity.OffenderAddress> BY_ADDRESS_MODIFIED = Comparator.comparing((OffenderAddress oa) -> Optional.ofNullable(oa.getPrimaryFlag()).orElse("N"))
            .thenComparing(OffenderAddress::getEndDate, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(LAST_MODIFIED_DATE_OF)
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
                        .sorted(BY_ADDRESS_MODIFIED)
                        .map(addressesTransformer::addressOf)
                        .collect(Collectors.toList()));
    }

}
