package org.example.exception;

import lombok.Getter;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

@Getter
@ApplicationException
public class ResourceNotFoundException extends Exception {

    private final transient ResponseBody responseBody;

    public ResourceNotFoundException(String keyMessage, String message) {
        this.responseBody = new ResponseBody(Response.Status.NOT_FOUND, keyMessage, message);
    }
}
