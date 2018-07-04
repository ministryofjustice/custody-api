package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceCodePK implements Serializable {
    @Column(name = "DOMAIN")
    @Id
    private String domain;
    @Column(name = "CODE")
    @Id
    private String code;

}
