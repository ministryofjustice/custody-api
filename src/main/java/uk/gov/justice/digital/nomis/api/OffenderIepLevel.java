package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OffenderIepLevel {
    private Long bookingId;
    private Long iepLevelSeq;
    private LocalDateTime iepDateTime;
    private IepLevel iepLevel;
    private String comments;
}
