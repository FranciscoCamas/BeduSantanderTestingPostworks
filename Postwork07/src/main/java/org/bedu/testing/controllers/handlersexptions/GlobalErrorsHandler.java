package org.bedu.testing.controllers.handlersexptions;

import org.bedu.testing.models.errorsmsg.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Paulo Zamora                pauloizamora@outlook.com
 * @author Francisco Javier Camas Tec  francisco_Camas@hotmail.com
 */

@RestControllerAdvice
public class GlobalErrorsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request) {

        return ResponseError.builder()
                .exception(ex)
                .message("An error occurred while validating the request information.")
                .route(request.getDescription(false).substring(4))
                .entity();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejaException(Exception ex, WebRequest request) {
        return ResponseError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An error occurred while processing the request Details:[" +ex.getMessage() +"]")
                .route(request.getDescription(false).substring(4))
                .entity();
    }


    @ExceptionHandler( ResponseStatusException.class )
    public ResponseEntity<Object> manejaExceptionStatusException(ResponseStatusException ex, WebRequest request) {

        Map<String, String> errors = new TreeMap<>();
        errors.put("Error", ex.getMessage() );

        ResponseError responseError = new ResponseError();
        responseError.setMessage("An error occurred while validating the request information.");
        responseError.setMistakes(errors);
        responseError.setRoute(request.getDescription(false).substring(4));

        return new ResponseEntity<Object>(responseError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
/*
    @ExceptionHandler(ClientNotFoundException.class )
    public ResponseEntity<Object> manejaExceptionClientNotFound(ClientNotFoundException ex, WebRequest request) {

        Map<String, String> errors = new TreeMap<>();

        StringBuilder builder = new StringBuilder();
        builder.append("This ClientNotFoundException ");
        builder.append(ex.getMessage() );
        builder.append(" is not supported for this request. Supported methods are ");

        //ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        errors.put("Error", builder.toString());
        ResponseError responseError = new ResponseError();
        responseError.setMistakes(errors);
        responseError.setMessage("An error occurred while validating the request information.");
        responseError.setRoute(request.getDescription(false).substring(4));

        return new ResponseEntity<Object>(responseError, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }*/

}