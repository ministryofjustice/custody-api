package uk.gov.justice.digital.nomis.jpa.entity.visitor;

import uk.gov.justice.digital.nomis.jpa.entity.AgencyAddress;
import uk.gov.justice.digital.nomis.jpa.entity.CorporateAddress;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderAddress;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderEducationAddress;
import uk.gov.justice.digital.nomis.jpa.entity.OffenderEmploymentAddress;
import uk.gov.justice.digital.nomis.jpa.entity.PersonAddress;
import uk.gov.justice.digital.nomis.jpa.entity.PersonEmploymentAddress;
import uk.gov.justice.digital.nomis.jpa.entity.StaffAddress;

public class IdVisitor {

    public Object visit(OffenderAddress address) {
        return address.getOffenderId();
    }

    public Object visit(PersonAddress address) {
        return address.getAddressId();
    }

    public Object visit(CorporateAddress address) {
        return address.getCorporateId();
    }

    public Object visit(StaffAddress address) {
        return address.getStaffId();
    }

    public Object visit(OffenderEducationAddress address) {
        return address.getOffenderBookId();
    }

    public Object visit(OffenderEmploymentAddress address) {
        return address.getOffenderBookId();
    }

    public Object visit(AgencyAddress address) {
        return address.getAgencyLocationId();
    }

    public Object visit(PersonEmploymentAddress address) {
        return address.getPersonId();
    }








}
