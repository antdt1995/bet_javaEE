package org.example.exception;

import lombok.Getter;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

@Getter
@ApplicationException
public class SecurityException extends Exception {

    private final transient ResponseBody responseBody;

    public SecurityException(Response.Status status, String keyMessage, String message) {
        this.responseBody = new ResponseBody(status, keyMessage, message);
    }
}
