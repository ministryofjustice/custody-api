package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class IepLevelPK implements Serializable {
    @Column(name = "IEP_LEVEL")
    @Id
    private String iepLevel;
    @Column(name = "AGY_LOC_ID")
    @Id
    private String agyLocId;
}
