package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DtoUtils {

    public static Boolean ynToBoolean(String ynValue) {
        if (Objects.isNull(ynValue)) {
            return null;
        }
        if (!(ynValue.equalsIgnoreCase("Y") || ynValue.equalsIgnoreCase("N"))) {
            return null;
        }
        return ynValue.equalsIgnoreCase("Y");
    }

    static String refElementDesc(RefElement refElement) {
        if (Objects.isNull(refElement)) {
            return null;
        }
        return refElement.getRefElementDesc();
    }

    static String refElementCode(RefElement refElement) {
        if (Objects.isNull(refElement)) {
            return null;
        }
        return refElement.getRefElementCode();
    }
}
