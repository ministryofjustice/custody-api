package uk.gov.justice.digital.nomis.service.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.gov.justice.digital.nomis.api.CaseNote;
import uk.gov.justice.digital.nomis.api.KeyValue;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderCaseNote;
import uk.gov.justice.digital.nomis.jpa.entity.ReferenceCodePK;
import uk.gov.justice.digital.nomis.jpa.repository.ReferenceCodesRepository;

import java.util.Optional;

@Component
public class OffenderCaseNotesTransformer {
    private static final String NOTE_SOURCE = "NOTE_SOURCE";
    private static final String TASK_TYPE = "TASK_TYPE";
    private static final String TASK_SUBTYPE = "TASK_SUBTYPE";

    private final TypesTransformer typesTransformer;
    private final ReferenceCodesRepository referenceCodesRepository;

    @Autowired
    public OffenderCaseNotesTransformer(
            TypesTransformer typesTransformer,
            ReferenceCodesRepository referenceCodesRepository) {
        this.typesTransformer = typesTransformer;
        this.referenceCodesRepository = referenceCodesRepository;
    }

    public CaseNote caseNoteOf(OffenderCaseNote ocn) {
        return CaseNote.builder()
                .caseNoteId(ocn.getCaseNoteId())
                .contactDateTime(typesTransformer.localDateTimeOf(ocn.getContactDate(), ocn.getContactTime()))
                .creationDateTime(typesTransformer.localDateTimeOf(ocn.getCreationDate(), ocn.getCreationTime()))
                .eventId(ocn.getEventId())
                .isAmendment(typesTransformer.ynToBoolean(ocn.getAmendmentFlag()))
                .isIwp(typesTransformer.ynToBoolean(ocn.getAmendmentFlag()))
                .offenderBookId(ocn.getOffenderBookId())
                .source(sourceOf(ocn.getNoteSourceCode()))
                .staffId(ocn.getStaffId())
                .subType(subTypeOf(ocn.getCaseNoteSubType()))
                .text(textOf(ocn.getCaseNoteText()))
                .type(typeOf(ocn.getCaseNoteType()))
                .build();
    }

    private String textOf(String caseNoteText) {
        //todo: need to understand how to parse the text content to create an amendments log
        return caseNoteText;
    }

    private KeyValue subTypeOf(String caseNoteSubType) {
        return Optional.ofNullable(caseNoteSubType != null ?
                referenceCodesRepository.findOne(ReferenceCodePK.builder()
                        .code(caseNoteSubType)
                        .domain(TASK_SUBTYPE)
                        .build()) : null)
                .map(rc -> KeyValue.builder().code(rc.getCode()).description(rc.getDescription()).build())
                .orElse(null);
    }

    private KeyValue typeOf(String caseNoteType) {
        return Optional.ofNullable(caseNoteType != null ?
                referenceCodesRepository.findOne(ReferenceCodePK.builder()
                        .code(caseNoteType)
                        .domain(TASK_TYPE)
                        .build()) : null)
                .map(rc -> KeyValue.builder().code(rc.getCode()).description(rc.getDescription()).build())
                .orElse(null);
    }

    private KeyValue sourceOf(String noteSourceCode) {
        return Optional.ofNullable(noteSourceCode != null ?
                referenceCodesRepository.findOne(ReferenceCodePK.builder()
                        .code(noteSourceCode)
                        .domain(NOTE_SOURCE)
                        .build()) : null)
                .map(rc -> KeyValue.builder().code(rc.getCode()).description(rc.getDescription()).build())
                .orElse(null);
    }
}
