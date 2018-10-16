package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"username", "caseload"})
@Embeddable
@ToString(of = {"username", "caseload"})
public class UserCaseloadIdentity implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "caseload_id")
    private String caseload;

}
