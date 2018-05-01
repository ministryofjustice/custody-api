package uk.gov.justice.digital.nomis.api;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Person {
    private Long personId;
    private String title;
    private String lastName;
    private String firstName;
    private String middleName;
    private String nameType;
    private String nameSequence;
    private LocalDate dateOfBirth;
    private String sex;
    private String birthPlace;
    private String attention;
    private String careOf;
    private String occupationCode;
    private String criminalHistoryText;
    private Long aliasPersonId;
    private Long rootPersonId;
    private String languageCode;
    private Boolean comprehendEnglish;
    private String employer;
    private String profileCode;
    private Boolean interpreterRequired;
    private String primaryLanguageCode;
    private String memoText;
    private Boolean suspended;
    private String maritalStatus;
    private String citizenship;
    private LocalDate deceasedDate;
    private String coronerNumber;
    private LocalDate suspendedDate;
    private Boolean staff;
    private Boolean remitter;
    private Boolean keepBiometrics;

}
