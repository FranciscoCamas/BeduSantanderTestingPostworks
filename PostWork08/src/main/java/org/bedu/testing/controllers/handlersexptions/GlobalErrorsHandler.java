package org.bedu.testing.controllers.handlersexptions;

import org.bedu.testing.models.errorsmsg.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.TreeMap;
/**
 * @author Paulo Zamora                pauloizamora@outlook.com
 * @author Francisco Javier Camas Tec  francisco_Camas@hotmail.com
 */

//@RestControllerAdvice
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
    //@ExceptionHandler(MethodArgumentNotValid.class)
   // @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
   // @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException( ConstraintViolationException ex) {
/* , WebRequest request
HttpHeaders headers,
            HttpStatus status,
* */
        ResponseError errorResponse = new ResponseError(
        );

        errorResponse.setMessage("An error occurred while validating the request information.");

        Map<String, String> errors = new TreeMap<>();
        errors.put("Error", ex.getMessage() );

        for (ConstraintViolation fieldError : ex.getConstraintViolations() ) {
            errors.put(fieldError.getMessage() ,
                    fieldError.getMessage() );
        }
        errorResponse.setMistakes(errors);

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

   // @ExceptionHandler(ConstraintViolationException.class)
   // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView constraintViolation(HttpServletRequest request, final Throwable throwable, Model model){


        ModelAndView mav = new ModelAndView("index");

        String errMsg = throwable.getMessage().replaceAll(".*\\s?:\\s?(.*)$", "$1");

        if (!request.getHeader("Accept").startsWith("application/json")){
            model.addAttribute("error", errMsg);
           // return model;
        }

        //String error = new RestApiException(1007, errMsg).toString();
        model.addAttribute("error", errMsg);
        return mav;
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


    // add any exceptions/validations/binding problems
    /*
    @ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class } )
    public ResponseEntity<?> defaultErrorHandler(Exception e, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code", 400);
        request.setAttribute("javax.servlet.error.request_uri", request.getPathInfo());
        return errorController.error(request);
       //return request;
    }*/

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