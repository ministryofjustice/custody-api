package uk.gov.justice.digital.nomis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.digital.nomis.api.Offender;
import uk.gov.justice.digital.nomis.api.OffenderImage;
import uk.gov.justice.digital.nomis.service.ImagesService;
import uk.gov.justice.digital.nomis.service.OffenderService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Api( description = "Offender images", tags = {"photographs", "images"})
public class ImagesController {

    private final OffenderService offenderService;
    private final ImagesService imagesService;

    @Autowired
    public ImagesController(OffenderService offenderService, ImagesService imagesService) {
        this.offenderService = offenderService;
        this.imagesService = imagesService;
    }

    @RequestMapping(path = "/offenders/offenderId/{offenderId}/images", method = RequestMethod.GET)
    @ApiResponses({
        @ApiResponse(code = 404, message = "Offender not found"),
        @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<OffenderImage>> getImages(@PathVariable("offenderId") final Long offenderId) {

        return offenderService.getOffenderByOffenderId(offenderId).
            map(offender -> new ResponseEntity<>(imagesForOffender(offender), HttpStatus.OK))
            .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/offenders/nomsId/{nomsId}/images", method = RequestMethod.GET)
    @ApiResponses({
        @ApiResponse(code = 404, message = "Offender not found"),
        @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<OffenderImage>> getImagesByNomsId(@PathVariable("nomsId") final String nomsId) {

        return offenderService.getOffenderByNomsId(nomsId).
            map(offender -> new ResponseEntity<>(imagesForOffender(offender), HttpStatus.OK))
            .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    @RequestMapping(path = "/images/{imageId}/thumbnail", method = RequestMethod.GET, produces = "image/jpeg")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Image not found"),
        @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<byte[]> getImageData(@PathVariable("imageId") final Long imageId) {

        return imagesService.getImageForImageId(imageId)
            .map(bytes -> new ResponseEntity<>(bytes, HttpStatus.OK))
            .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    private List<OffenderImage> imagesForOffender(Offender offender) {
        return offender.getBookings().stream()
            .map(booking -> imagesService.getImagesForBookingId(booking.getBookingId()))
            .flatMap(offenderImages -> offenderImages.stream())
            .sorted((image1, image2) -> image2.getCaptureTime().compareTo(image1.getCaptureTime()))
            .collect(Collectors.toList());
    }
}
