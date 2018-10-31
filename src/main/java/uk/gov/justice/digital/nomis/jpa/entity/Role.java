package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "OMS_ROLES")
@Data()
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"code"})
@ToString(of = {"id", "code", "name"})
public class Role implements Serializable {

    @Id()
    @Column(name = "ROLE_ID", nullable = false)
    private Long id;

    @Column(name = "ROLE_CODE", nullable = false, unique = true)
    private String code;

    @Column(name = "ROLE_NAME")
    private String name;

    @Column(name = "ROLE_SEQ", nullable = false)
    private int sequence;

    @Column(name = "ROLE_TYPE")
    private String type;

    @Column(name = "ROLE_FUNCTION", nullable = false)
    private String function;

    @ManyToOne
    @JoinColumn(name = "PARENT_ROLE_CODE", referencedColumnName = "ROLE_CODE")
    private Role parent;

}
