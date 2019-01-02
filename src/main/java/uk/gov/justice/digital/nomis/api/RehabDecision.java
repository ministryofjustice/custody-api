package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RehabDecision {
    private Long offenderRehabDecisionId;
    private Long bookingId;
    private KeyValue decision;
    private LocalDate decisionDate;
    private String comments;
    private Long staffId;
    private LocalDateTime recordDateTime;
    private Boolean active;
    private List<RehabProvider> providers;
}
