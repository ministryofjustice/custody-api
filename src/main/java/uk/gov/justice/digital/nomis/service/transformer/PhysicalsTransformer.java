package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.IdentifyingMark;
import uk.gov.justice.digital.nomis.api.PhysicalAttribute;
import uk.gov.justice.digital.nomis.api.Physicals;
import uk.gov.justice.digital.nomis.api.ProfileDetails;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderBooking;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderIdentifyingMarks;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderPhysicalAttributes;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderProfileDetails;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PhysicalsTransformer {


    public Physicals physicalsOf(OffenderBooking offenderBooking) {
        return Physicals.builder()
                .bookingId(offenderBooking.getOffenderBookId())
                .identifyingMarks(identifyingMarksOf(offenderBooking.getOffenderIdentifyingMarks()))
                .physicalAttributes(physicalAttributesOf(offenderBooking.getOffenderPhysicalAttributes()))
                .profileDetails(profileDetailsOf(offenderBooking.getOffenderProfileDetails()))
                .build();
    }

    private List<ProfileDetails> profileDetailsOf(List<OffenderProfileDetails> offenderProfileDetails) {
        return Optional.ofNullable(offenderProfileDetails)
                .map(dets -> dets
                        .stream()
                        .map(det -> ProfileDetails.builder()
                                .caseloadType(det.getCaseloadType())
                                .comments(det.getCommentText())
                                .listSeq(det.getListSeq())
                                .profileCode(det.getProfileCode())
                                .profileSeq(det.getProfileSeq())
                                .profileType(det.getProfileType())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private List<PhysicalAttribute> physicalAttributesOf(List<OffenderPhysicalAttributes> offenderPhysicalAttributes) {
        return Optional.ofNullable(offenderPhysicalAttributes)
                .map(attrs -> attrs
                        .stream()
                        .map(attr -> PhysicalAttribute.builder()
                                .attributeSeq(attr.getAttributeSeq())
                                .heightCm(attr.getHeightCm())
                                .heightFeet(attr.getHeightFt())
                                .heightInches(attr.getHeightIn())
                                .weightKg(attr.getWeightKg())
                                .weightLbs(attr.getWeightLbs())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    private List<IdentifyingMark> identifyingMarksOf(List<OffenderIdentifyingMarks> offenderIdentifyingMarks) {
        return Optional.ofNullable(offenderIdentifyingMarks)
                .map(marks -> marks
                        .stream()
                        .map(mark -> IdentifyingMark.builder()
                                .bodyPartCode(mark.getBodyPartCode())
                                .comments(mark.getCommentText())
                                .idMarkSeq(mark.getIdMarkSeq())
                                .markType(mark.getMarkType())
                                .partOrientationCode(mark.getPartOrientationCode())
                                .sideCode(mark.getSideCode())
                                .build())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

}
