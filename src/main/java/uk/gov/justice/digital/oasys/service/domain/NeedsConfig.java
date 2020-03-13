package uk.gov.justice.digital.oasys.service.domain;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

import static java.util.Map.entry;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NeedsConfig {

    private static final String REOFFENDING_QUESTION = "reoffendingQuestion";
    private static final String HARM_QUESTION = "harmQuestion";

    public static String[] getNeedsSectionHeadings() {
        return needsSections().keySet().toArray(new String[0]);
    }

    public static String getHarmQuestion(String section) {
        return needsSections().get(section).get(HARM_QUESTION);
    }

    public static String getReoffendingQuestion(String section) {
        return needsSections().get(section).get(REOFFENDING_QUESTION);
    }

    private static Map<String, Map<String, String>> needsSections() {

        return Map.ofEntries(
                entry("10"
                        , Map.of(
                                HARM_QUESTION, "10.98",
                                REOFFENDING_QUESTION, "10.99")),
                entry("11"
                        , Map.of(
                                HARM_QUESTION, "11.98",
                                REOFFENDING_QUESTION, "11.99")),
                entry("12"
                        , Map.of(
                                HARM_QUESTION, "12.98",
                                REOFFENDING_QUESTION, "12.99")),
                entry("3"
                        , Map.of(
                                HARM_QUESTION, "3.98",
                                REOFFENDING_QUESTION, "3.99")),
                entry("4"
                        , Map.of(
                                HARM_QUESTION, "4.96",
                                REOFFENDING_QUESTION, "4.98")),
                entry("5"
                        , Map.of(
                                HARM_QUESTION, "5.98",
                                REOFFENDING_QUESTION, "5.99")),
                entry("6"
                        , Map.of(
                                HARM_QUESTION, "6.98",
                                REOFFENDING_QUESTION, "6.99")),
                entry("7"
                        , Map.of(
                                HARM_QUESTION, "7.98",
                                REOFFENDING_QUESTION, "7.99")),
                entry("8"
                        , Map.of(
                                HARM_QUESTION, "8.97",
                                REOFFENDING_QUESTION, "8.98")),
                entry("9"
                        , Map.of(
                                HARM_QUESTION, "9.98",
                                REOFFENDING_QUESTION, "9.99"))
        );

    }
}
