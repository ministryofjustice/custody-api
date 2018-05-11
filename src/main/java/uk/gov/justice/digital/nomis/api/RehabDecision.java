package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class RehabDecision {
    private Long offenderRehabDecisionId;
    private Long offenderBookId;
    private String decisionCode;
    private LocalDate decisionDate;
    private String comments;
    private Long staffId;
    private LocalDateTime recordDateTime;
    private Boolean active;
    private List<RehabProvider> providers;
}
