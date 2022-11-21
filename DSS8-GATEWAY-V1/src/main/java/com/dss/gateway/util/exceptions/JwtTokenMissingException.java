package com.dss.gateway.util.exceptions;

public class JwtTokenMissingException extends RuntimeException{

    public JwtTokenMissingException(String exceptionStr) {
        super(exceptionStr);
    }
}
