package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDto {

    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private String addressLine2;
    @JsonProperty("addressLine3")
    private String addressLine3;
    @JsonProperty("addressLine4")
    private String addressLine4;
    @JsonProperty("addressLine5")
    private String addressLine5;
    @JsonProperty("addressLine6")
    private String addressLine6;
    @JsonProperty("addressPostcode")
    private String addressPostcode;
    @JsonProperty("telephoneNumber")
    private String telephoneNumber;

    public static AddressDto from (uk.gov.justice.digital.oasys.jpa.entity.Offender offender) {
        return new AddressDto(
                offender.getAddressLine1(),
                offender.getAddressLine2(),
                offender.getAddressLine3(),
                offender.getAddressLine4(),
                offender.getAddressLine5(),
                offender.getAddressLine6(),
                offender.getAddressPostcode(),
                offender.getTelephoneNumber());
    }
}