package uk.gov.justice.digital.oasys.api;

import uk.gov.justice.digital.oasys.jpa.entity.RefElement;

public final class DtoUtils {

    public static Boolean ynToBoolean(String ynValue) {
        if (ynValue == null) {
            return null;
        }
        return ynValue.equalsIgnoreCase("Y");
    }

    public static String refElementDesc(RefElement refElement) {
        if (refElement == null) {
            return null;
        }
        return refElement.getRefElementDesc();
    }

    public static String refElementCode(RefElement refElement) {
        if (refElement == null) {
            return null;
        }
        return refElement.getRefElementCode();
    }
}
