package com.dss.util.exceptions;

public class JwtTokenMissingException extends RuntimeException{

    public JwtTokenMissingException(String exceptionStr) {
        super(exceptionStr);
    }
}
