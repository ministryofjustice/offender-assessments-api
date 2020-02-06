package uk.gov.justice.digital.oasys.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.OffenderEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

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

    public static Address from (OffenderEntity offenderEntity) {
        return new Address(
                offenderEntity.getAddressLine1(),
                offenderEntity.getAddressLine2(),
                offenderEntity.getAddressLine3(),
                offenderEntity.getAddressLine4(),
                offenderEntity.getAddressLine5(),
                offenderEntity.getAddressLine6(),
                offenderEntity.getAddressPostcode(),
                offenderEntity.getTelephoneNumber());
    }
}