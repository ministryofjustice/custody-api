package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class ContactPersonTypePK implements Serializable {
    @Column(name = "CONTACT_TYPE")
    @Id
    private String contactType;
    @Column(name = "RELATIONSHIP_TYPE")
    @Id
    private String relationshipType;

}
