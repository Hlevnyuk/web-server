package com.luxoft.exceptions;
import com.luxoft.domain.StatusCode;
public class ServerExceptions extends RuntimeException {
    private final StatusCode statusCode;
    public ServerExceptions(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
    public StatusCode getStatusCode() {
        return statusCode;
    }
}