package uk.gov.justice.digital.oasys.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DtoUtils {

    public static Boolean ynToBoolean(String ynValue) {
        if (ynValue == null) {
            return null;
        }
        if (!(ynValue.equalsIgnoreCase("Y") || ynValue.equalsIgnoreCase("N"))) {
            return null;
        }
        return ynValue.equalsIgnoreCase("Y");
    }

    static String refElementDesc(RefElement refElement) {
        if (refElement == null) {
            return null;
        }
        return refElement.getRefElementDesc();
    }

    static String refElementCode(RefElement refElement) {
        if (refElement == null) {
            return null;
        }
        return refElement.getRefElementCode();
    }
}
