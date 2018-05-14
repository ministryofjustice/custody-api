package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
public class ReferenceCodePK implements Serializable {
    @Column(name = "DOMAIN")
    @Id
    private String domain;
    @Column(name = "CODE")
    @Id
    private String code;

}
