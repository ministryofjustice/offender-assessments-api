package uk.gov.justice.digital.oasys.api;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Arrays;
@ApiModel(description = "Offender Type Models")
public enum OffenderIdentifierDto {
    OASYS("oasysOffenderId"),
    CRN("crn"),
    PNC("pnc"),
    NOMIS("nomisId"),
    BOOKING("bookingId"),
    UNKNOWN("none");

    private String value;

    OffenderIdentifierDto(String value){
        this.value = value;
    }

    public static OffenderIdentifierDto fromString(String enumValue) {
        return Arrays.stream(OffenderIdentifierDto.values()).filter(o -> o.value.equals(enumValue)).findFirst().orElse(OffenderIdentifierDto.UNKNOWN);
    }
}