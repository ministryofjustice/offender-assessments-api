package uk.gov.justice.digital.oasys.transformer;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class TypesTransformer {

    public static LocalDateTime localDateTimeOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(t -> t.toLocalDateTime())
                .orElse(null);
    }

    public static LocalDate localDateOf(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(t -> t.toLocalDateTime().toLocalDate())
                .orElse(null);
    }

    public static Boolean ynToBoolean(String yn) {
        return Optional.ofNullable(yn)
                .map("Y"::equalsIgnoreCase)
                .orElse(null);
    }
}