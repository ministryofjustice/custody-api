package uk.gov.justice.digital.nomis.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Optional;

@JsonPropertyOrder({"years","months","days"})
public class YMD {
    @JsonIgnore
    private final String yy_mm_dd;

    public YMD(String yy_mm_dd) {
        this.yy_mm_dd = yy_mm_dd;
    }

    public Integer getYears() {
        return Optional.ofNullable(yy_mm_dd).map(s -> Integer.valueOf(s.split("/")[0])).orElse(null);
    }

    public Integer getMonths() {
        return Optional.ofNullable(yy_mm_dd).map(s -> Integer.valueOf(s.split("/")[1])).orElse(null);
    }

    public Integer getDays() {
        return Optional.ofNullable(yy_mm_dd).map(s -> Integer.valueOf(s.split("/")[2])).orElse(null);
    }


}
