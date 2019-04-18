package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class CheckHoldAlertCDE {
    private String T_TG;
    private String T_TAH;
    private String T_TSE;
    private String T_TM;
    private String T_TPR;
    private String H_HA;
    @Builder.Default
    private String VUL = "N";
    @Builder.Default
    private String V_45_46 = "N";
    @Builder.Default
    private String SH_STS = "N";
    private LocalDate SH_Date;
}
