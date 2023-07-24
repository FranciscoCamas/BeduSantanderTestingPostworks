package org.bedu.testing.models.errorsmsg;


import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Paulo Zamora                pauloizamora@outlook.com
 * @author Francisco Javier Camas Tec  francisco_Camas@hotmail.com
 */
public class ResponseError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;
    private Map<String, String> mistakes;
    private String route;

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

    public Map<String, String> getMistakes() {
        return mistakes;
    }

    public void setMistakes(Map<String, String> mistakes) {
        this.mistakes = mistakes;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
