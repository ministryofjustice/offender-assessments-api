package uk.gov.justice.digital.oasys.service.exception;

import uk.gov.justice.digital.oasys.utils.LogEvent;

public interface ApplicationExceptions {

    class EntityCreationException extends RuntimeException {
        private final LogEvent event;
        private final LogEvent exception;

        public EntityCreationException(String msg, LogEvent event, Object... args) {
            super(String.format(msg, args));
            this.event = event;
            this.exception = null;
        }

        public EntityCreationException(String msg, LogEvent event, LogEvent exception, Object... args) {
            super(String.format(msg, args));
            this.event = event;
            this.exception = exception;
        }

        public LogEvent getEvent() {
            return event;
        }

        public LogEvent getException() { return  exception; }
    }

    class EntityNotFoundException extends RuntimeException {
        private final LogEvent event;
        private final LogEvent exception;

        public EntityNotFoundException(String msg, LogEvent event, Object... args) {
            super(String.format(msg, args));
            this.event = event;
            this.exception = null;
        }

        public EntityNotFoundException(String msg, LogEvent event, LogEvent exception, Object... args) {
            super(String.format(msg, args));
            this.event = event;
            this.exception = exception;
        }

        public LogEvent getEvent() {
            return event;
        }

        public LogEvent getException() { return  exception; }
    }
}