package com.dss.gateway.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

public class ResponseStatusException extends RuntimeException{

    private final int status;

    private final String reason;

    public ResponseStatusException(HttpStatus status, String reason) {
        super("");
        Assert.notNull(status, "HttpStatus is required");
        this.status = status.value();
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}
