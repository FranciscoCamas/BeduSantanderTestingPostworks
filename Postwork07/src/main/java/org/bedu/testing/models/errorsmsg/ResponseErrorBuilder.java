package org.bedu.testing.models.errorsmsg;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Paulo Zamora                pauloizamora@outlook.com
 * @author Francisco Javier Camas Tec  francisco_Camas@hotmail.com
 */
public class ResponseErrorBuilder {
    private int status;
    private String message;
    private Map<String, String> errors;
    private String route;

    public ResponseErrorBuilder status(int status) {
        this.status = status;
        return this;
    }

    public ResponseErrorBuilder status(HttpStatus status) {
        this.status = status.value();

        if (status.isError()) {
            if (this.errors != null )
                this.errors.put("error", status.getReasonPhrase());
        }

        return this;
    }

    public ResponseErrorBuilder errors(Map<String, String> errors) {
        this.errors = errors;
        return this;
    }

    public ResponseErrorBuilder message(String message) {
        this.message = message;
        return this;
    }
    public ResponseErrorBuilder exception(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        this.status = status.value();

        errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return this;
    }

    public ResponseErrorBuilder route(String route) {
        this.route = route;
        return this;
    }

    public ResponseError build() {
        ResponseError response = new ResponseError();
        response.setStatus(status);
        response.setMessage(message);
        response.setMistakes(errors);
        response.setRoute(route);
        return response;
    }

    public ResponseEntity<ResponseError> entity() {
        return ResponseEntity.status(status).headers(HttpHeaders.EMPTY).body(build());
    }
}