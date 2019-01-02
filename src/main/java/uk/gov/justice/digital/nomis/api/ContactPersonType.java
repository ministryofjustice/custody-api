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
public class ContactPersonType {
    private String contactType;
    private String relationshipType;
    private Integer listSeq;
    private Boolean active;
    private Boolean updateAllowed;
    private LocalDate expiryDate;
    private String contactClass;

}
