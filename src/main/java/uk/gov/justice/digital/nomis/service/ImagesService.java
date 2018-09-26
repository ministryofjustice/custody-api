package uk.gov.justice.digital.nomis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.justice.digital.nomis.api.OffenderImage;
import uk.gov.justice.digital.nomis.jpa.repository.OffenderImagesRepository;
import uk.gov.justice.digital.nomis.service.transformer.OffenderImageTransformer;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ImagesService {

    private OffenderImagesRepository offenderImagesRepository;
    private OffenderImageTransformer offenderImageTransformer;

    @Autowired
    public ImagesService(OffenderImagesRepository offenderImagesRepository, OffenderImageTransformer offenderImageTransformer) {
        this.offenderImagesRepository = offenderImagesRepository;
        this.offenderImageTransformer = offenderImageTransformer;
    }

    public List<OffenderImage> getImagesForBookingId(Long bookingId) {
        return offenderImagesRepository.findByOffenderBookingId(bookingId).stream()
            .map(offenderImage -> offenderImageTransformer.offenderImageMetaDataOf(offenderImage))
            .collect(toList());
    }

    public Optional<byte[]> getImageForImageId(Long imageId) {
        Optional<uk.gov.justice.digital.nomis.jpa.entity.OffenderImage> maybeOffender =
            Optional.ofNullable(offenderImagesRepository.findOne(imageId));

        return maybeOffender.map(offenderImageTransformer::thumbnailOf);
    }
}
