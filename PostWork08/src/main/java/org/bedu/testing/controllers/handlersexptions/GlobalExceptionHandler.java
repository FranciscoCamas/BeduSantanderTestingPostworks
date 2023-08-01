package org.bedu.testing.controllers.handlersexptions;

import org.bedu.testing.models.errorsmsg.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.TreeMap;

//@ControllerAdvice
public class GlobalExceptionHandler  {

   //ConstraintViolationException:

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {


        ResponseError errorResponse = new ResponseError(
        );

        errorResponse.setMessage("An error occurred while validating the request information.");

        Map<String, String> errors = new TreeMap<>();
        errors.put("Error", ex.getMessage() );

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        errorResponse.setMistakes(errors);

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> catchAll(Exception exception, WebRequest request) {

        return ResponseError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An error occurred while processing the request Details:[" +exception.getMessage() +"]")
                .route(request.getDescription(false).substring(4))
                .entity();
    }
}
