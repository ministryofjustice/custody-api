package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RehabilitationDecisionProvider {
    private String code;
    private String type;
    private Long Sequence;
    private String description;
    private boolean active;
    private LocalDate employmentDate;

}
