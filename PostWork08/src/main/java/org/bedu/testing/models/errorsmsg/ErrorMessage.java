package org.bedu.testing.models.errorsmsg;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Paulo Zamora                pauloizamora@outlook.com
 * @author Francisco Javier Camas Tec  francisco_Camas@hotmail.com
 */
public class ErrorMessage {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;
    private Map<String, String> errors;
    private String path;

    public static ResponseErrorBuilder builder() {
        return new ResponseErrorBuilder();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}