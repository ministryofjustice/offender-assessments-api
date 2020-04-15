package uk.gov.justice.digital.oasys.service.exception;

import uk.gov.justice.digital.oasys.utils.LogEvent;

public interface ApplicationExceptions {

    class EntityNotFoundException extends RuntimeException {
        private final LogEvent event;
        private final LogEvent exception;

        public EntityNotFoundException(String msg, LogEvent event, Object... args) {
            super(String.format(msg, args));
            this.event = event;
            this.exception = null;
        }

        public LogEvent getEvent() {
            return event;
        }

        public LogEvent getException() {
            return exception;
        }
    }

    class DuplicateOffenderRecordException extends RuntimeException {
        private final LogEvent event;
        private final LogEvent exception;

        public DuplicateOffenderRecordException(String msg, LogEvent event, Object... args) {
            super(String.format(msg, args));
            this.event = event;
            this.exception = null;
        }

        public LogEvent getEvent() {
            return event;
        }

        public LogEvent getException() {
            return exception;
        }
    }

}