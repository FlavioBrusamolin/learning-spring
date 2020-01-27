package com.leucotron.learningspring.exception.resolver;

import com.leucotron.learningspring.exception.JDuplicateEntityException;
import com.leucotron.learningspring.exception.JResourceNotFoundException;
import com.leucotron.learningspring.response.error.JError;
import com.leucotron.learningspring.response.error.JErrorResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author flavio
 */
@RestControllerAdvice
public class JGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<JError> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new JError("Invalid value in " + error.getField() + " field", error.getDefaultMessage()))
                .collect(Collectors.toList());

        JErrorResponse response = new JErrorResponse();
        response.setErrors(errors);

        return buildResponseEntity(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        JError error = new JError("Method Not Allowed", ex.getMessage());

        JErrorResponse response = new JErrorResponse();
        response.addError(error);

        return buildResponseEntity(response, status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        JError error = new JError("Database constraints violation", ex.getMostSpecificCause().getMessage());

        JErrorResponse response = new JErrorResponse();
        response.addError(error);

        return buildResponseEntity(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFound(JResourceNotFoundException ex) {
        JError error = new JError("Resource not found", ex.getMessage());

        JErrorResponse response = new JErrorResponse();
        response.addError(error);

        return buildResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JDuplicateEntityException.class)
    protected ResponseEntity<Object> handleDuplicateEntity(JDuplicateEntityException ex) {
        JError error = new JError("Duplicate entity", ex.getMessage());

        JErrorResponse response = new JErrorResponse();
        response.addError(error);

        return buildResponseEntity(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> buildResponseEntity(JErrorResponse response, HttpStatus status) {
        return ResponseEntity.status(status).body(response);
    }

}
