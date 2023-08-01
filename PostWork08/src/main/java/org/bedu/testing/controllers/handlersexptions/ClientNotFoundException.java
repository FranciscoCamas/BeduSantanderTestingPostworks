package org.bedu.testing.controllers.handlersexptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String msg) {
        super(msg);
    }
}
