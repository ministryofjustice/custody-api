package uk.gov.justice.digital.nomis.api;

import java.time.LocalDateTime;
import java.util.Optional;

public class SentenceCalculationDatesCDE {

    private final SentenceCalculation sentenceCalculation;

    public SentenceCalculationDatesCDE(SentenceCalculation sentenceCalculation) {
        this.sentenceCalculation = sentenceCalculation;
    }

    public static Optional<LocalDateTime> optionalDateOf(LocalDateTime d1, LocalDateTime d2) {
        return Optional.ofNullable((d1 != null) ? d1 : d2);
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

    public Optional<LocalDateTime> getSed() {
        return optionalDateOf(sentenceCalculation.getSedOverridedDate(), sentenceCalculation.getSedCalculatedDate());
    }

    public Optional<LocalDateTime> getHdced() {
        return optionalDateOf(sentenceCalculation.getHdcedOverridedDate(), sentenceCalculation.getHdcedCalculatedDate());
    }

    public Optional<LocalDateTime> getHdcad() {
        return optionalDateOf(sentenceCalculation.getHdcadOverridedDate(), sentenceCalculation.getHdcadCalculatedDate());
    }

    public Optional<LocalDateTime> getEtd() {
        return optionalDateOf(sentenceCalculation.getEtdOverridedDate(), sentenceCalculation.getEtdCalculatedDate());
    }

    public Optional<LocalDateTime> getMtd() {
        return optionalDateOf(sentenceCalculation.getMtdOverridedDate(), sentenceCalculation.getMtdCalculatedDate());
    }

    public Optional<LocalDateTime> getLtd() {
        return optionalDateOf(sentenceCalculation.getLtdOverridedDate(), sentenceCalculation.getLtdCalculatedDate());
    }

    public Optional<LocalDateTime> getCrd() {
        return optionalDateOf(sentenceCalculation.getCrdOverridedDate(), sentenceCalculation.getCrdCalculatedDate());
    }

    public Optional<LocalDateTime> getPed() {
        return optionalDateOf(sentenceCalculation.getPedOverridedDate(), sentenceCalculation.getPedCalculatedDate());
    }

    public Optional<LocalDateTime> getApd() {
        return optionalDateOf(sentenceCalculation.getApdOverridedDate(), sentenceCalculation.getApdCalculatedDate());
    }

    public Optional<LocalDateTime> getNpd() {
        return optionalDateOf(sentenceCalculation.getNpdOverridedDate(), sentenceCalculation.getNpdCalculatedDate());
    }

    public Optional<LocalDateTime> getArd() {
        return optionalDateOf(sentenceCalculation.getArdOverridedDate(), sentenceCalculation.getArdCalculatedDate());
    }

    public Optional<LocalDateTime> getLed() {
        return optionalDateOf(sentenceCalculation.getLedOverridedDate(), sentenceCalculation.getLedCalculatedDate());
    }

    public Optional<LocalDateTime> getTused() {
        return optionalDateOf(sentenceCalculation.getTusedOverridedDate(), sentenceCalculation.getTusedCalculatedDate());
    }

    public Optional<LocalDateTime> getPrrd() {
        return optionalDateOf(sentenceCalculation.getPrrdOverridedDate(), sentenceCalculation.getPrrdCalculatedDate());
    }

    public Optional<LocalDateTime> getErsed() {
        return optionalDateOf(sentenceCalculation.getErsedOverridedDate(), null);
    }

    public Optional<LocalDateTime> getTersed() {
        return optionalDateOf(sentenceCalculation.getTersedOverridedDate(), null);
    }

    public Optional<LocalDateTime> getRotl() {
        return optionalDateOf(sentenceCalculation.getRotlOverridedDate(), null);
    }

    public Optional<LocalDateTime> getTariff() {
        return optionalDateOf(sentenceCalculation.getTariffOverridedDate(), sentenceCalculation.getTariffCalculatedDate());
    }
}
