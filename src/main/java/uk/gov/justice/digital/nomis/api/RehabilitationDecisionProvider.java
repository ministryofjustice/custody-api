package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RehabilitationDecisionProvider {
    private String code;
    private String type;
    private Long Sequence;
    private String description;
    private boolean active;
    private LocalDate employmentDate;

}
