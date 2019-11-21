package uk.gov.justice.digital.oasys.api;

import io.swagger.annotations.ApiModel;

import java.util.Arrays;
@ApiModel(description = "Offender Type Models")
public enum OffenderIdentifier {
    OASYS("oasysOffenderId"),
    CRN("crn"),
    PNC("pnc"),
    NOMIS("nomisId"),
    BOOKING("bookingId"),
    UNKNOWN("none");

    private String value;

    OffenderIdentifier(String value){
        this.value = value;
    }

    public static OffenderIdentifier fromString(String enumValue) {
        return Arrays.stream(OffenderIdentifier.values()).filter(o -> o.value.equals(enumValue)).findFirst().orElse(OffenderIdentifier.UNKNOWN);
    }
}