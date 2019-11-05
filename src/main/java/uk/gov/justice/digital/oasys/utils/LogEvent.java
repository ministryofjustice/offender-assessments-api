package uk.gov.justice.digital.oasys.utils;

public enum LogEvent {
    OFFENDER_NOT_FOUND,
    ASSESSMENT_NOT_FOUND,
    SECTION_NOT_FOUND,
    QUESTION_NOT_FOUND,
    UNCAUGHT_EXCEPTION,
    USER_AUTHENTICATION_ATTEMPT,
    USER_AUTHENTICATION_PARSE_ERROR,
    USER_AUTHENTICATION,
    USER_SERVICE_ERROR;
    public static final String EVENT = "event_id";
    public static final String EXCEPTION = "exception";

} 