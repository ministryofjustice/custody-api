package uk.gov.justice.digital.nomis.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OffenderIepLevel {
    private Long bookingId;
    private Long iepLevelSeq;
    private LocalDateTime iepDateTime;
    private IepLevel iepLevel;
    private String comments;
}
