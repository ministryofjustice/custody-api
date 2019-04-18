package uk.gov.justice.digital.nomis.api;

import java.time.LocalDateTime;
import java.util.Optional;

public class SentenceCalculationDatesCDE {

    private final SentenceCalculation sentenceCalculation;

    public SentenceCalculationDatesCDE(SentenceCalculation sentenceCalculation) {
        this.sentenceCalculation = sentenceCalculation;
    }

    public static LocalDateTime optionalDateOf(LocalDateTime d1, LocalDateTime d2) {
        return Optional.ofNullable(d1).orElse(Optional.ofNullable(d2).orElse(null));
    }

    public YMD getEffectiveSentenceLength() {
        return new YMD(sentenceCalculation.getEffectiveSentenceLength());
    }

    public YMD getJudiciallyImposedSentenceLength() {
        return new YMD(sentenceCalculation.getJudiciallyImposedSentenceLength());
    }

    public LocalDateTime getEffectiveSentenceEndDate() {
        return sentenceCalculation.getEffectiveSentenceEndDate();
    }

    public LocalDateTime getSed() {
        return optionalDateOf(sentenceCalculation.getSedOverridedDate(), sentenceCalculation.getSedCalculatedDate());
    }

    public LocalDateTime getHdced() {
        return optionalDateOf(sentenceCalculation.getHdcedOverridedDate(), sentenceCalculation.getHdcedCalculatedDate());
    }

    public LocalDateTime getHdcad() {
        return optionalDateOf(sentenceCalculation.getHdcadOverridedDate(), sentenceCalculation.getHdcadCalculatedDate());
    }

    public LocalDateTime getEtd() {
        return optionalDateOf(sentenceCalculation.getEtdOverridedDate(), sentenceCalculation.getEtdCalculatedDate());
    }

    public LocalDateTime getMtd() {
        return optionalDateOf(sentenceCalculation.getMtdOverridedDate(), sentenceCalculation.getMtdCalculatedDate());
    }

    public LocalDateTime getLtd() {
        return optionalDateOf(sentenceCalculation.getLtdOverridedDate(), sentenceCalculation.getLtdCalculatedDate());
    }

    public LocalDateTime getCrd() {
        return optionalDateOf(sentenceCalculation.getCrdOverridedDate(), sentenceCalculation.getCrdCalculatedDate());
    }

    public LocalDateTime getPed() {
        return optionalDateOf(sentenceCalculation.getPedOverridedDate(), sentenceCalculation.getPedCalculatedDate());
    }

    public LocalDateTime getApd() {
        return optionalDateOf(sentenceCalculation.getApdOverridedDate(), sentenceCalculation.getApdCalculatedDate());
    }

    public LocalDateTime getNpd() {
        return optionalDateOf(sentenceCalculation.getNpdOverridedDate(), sentenceCalculation.getNpdCalculatedDate());
    }

    public LocalDateTime getArd() {
        return optionalDateOf(sentenceCalculation.getArdOverridedDate(), sentenceCalculation.getArdCalculatedDate());
    }

    public LocalDateTime getLed() {
        return optionalDateOf(sentenceCalculation.getLedOverridedDate(), sentenceCalculation.getLedCalculatedDate());
    }

    public LocalDateTime getTused() {
        return optionalDateOf(sentenceCalculation.getTusedOverridedDate(), sentenceCalculation.getTusedCalculatedDate());
    }

    public LocalDateTime getPrrd() {
        return optionalDateOf(sentenceCalculation.getPrrdOverridedDate(), sentenceCalculation.getPrrdCalculatedDate());
    }

    public LocalDateTime getErsed() {
        return optionalDateOf(sentenceCalculation.getErsedOverridedDate(), null);
    }

    public LocalDateTime getTersed() {
        return optionalDateOf(sentenceCalculation.getTersedOverridedDate(), null);
    }

    public LocalDateTime getRotl() {
        return optionalDateOf(sentenceCalculation.getRotlOverridedDate(), null);
    }

    public LocalDateTime getTariff() {
        return optionalDateOf(sentenceCalculation.getTariffOverridedDate(), sentenceCalculation.getTariffCalculatedDate());
    }
}
