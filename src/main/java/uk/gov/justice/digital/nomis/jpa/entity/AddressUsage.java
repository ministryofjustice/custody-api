package uk.gov.justice.digital.nomis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ADDRESS_USAGES")
@IdClass(AddressUsagePk.class)
public class AddressUsage {
    @Id
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    @Id
    @Column(name = "ADDRESS_USAGE")
    private String addressUsage;
    @Column(name = "ACTIVE_FLAG")
    private String activeFlag;
    
    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;
    @Column(name = "CREATE_USER_ID")
    private String createUserId;
    @Column(name = "MODIFY_DATETIME")
    private Timestamp modifyDatetime;
    @Column(name = "MODIFY_USER_ID")
    private String modifyUserId;
}
