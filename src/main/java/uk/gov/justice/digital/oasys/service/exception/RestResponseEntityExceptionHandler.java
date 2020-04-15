package uk.gov.justice.digital.oasys.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.gov.justice.digital.oasys.api.ErrorResponse;

import static net.logstash.logback.argument.StructuredArguments.value;
import static org.springframework.http.HttpStatus.*;
import static uk.gov.justice.digital.oasys.utils.LogEvent.*;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationExceptions.EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ApplicationExceptions.EntityNotFoundException e) {
        log.error("EntityNotFoundException: {}", e.getMessage(), value(EVENT, e.getEvent()), value(EXCEPTION, e.getException()));
        return new ResponseEntity<>(ErrorResponse.builder().status(404)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), NOT_FOUND);
    }

    @ExceptionHandler(ApplicationExceptions.DuplicateOffenderRecordException.class)
    public ResponseEntity<ErrorResponse> handle(ApplicationExceptions.DuplicateOffenderRecordException e) {
        log.error("DuplicateOffenderRecordException: {}", e.getMessage(), value(EVENT, e.getEvent()), value(EXCEPTION, e.getException()));
        return new ResponseEntity<>(ErrorResponse.builder().status(409)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage(), value(EVENT, BAD_REQUEST));
        return new ResponseEntity<>(ErrorResponse.builder().status(400)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity handle(HttpMessageConversionException e) {
        log.error("HttpMessageConversionException: {}", e.getMessage(), value(EVENT, BAD_REQUEST));
        return new ResponseEntity<>(ErrorResponse.builder().status(400)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handle(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage(), value(EVENT, BAD_REQUEST));
        return new ResponseEntity<>(ErrorResponse.builder().status(400)
                .developerMessage(e.getMessage())
                .userMessage(e.getMessage()).build(), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        log.error("Exception: {}", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().status(500)
                .developerMessage("Internal Server Error. Check Logs")
                .userMessage("An unexpected error has occurred").build(), INTERNAL_SERVER_ERROR);

    }
}