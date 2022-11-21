package com.dss.gateway.util.exceptions;

/*
 * @throws NullObjectException - if the Object is null or empty
 *
 * */

public class JwtTokenMalformedException extends RuntimeException{

    public JwtTokenMalformedException(String exceptionStr) {
        super(exceptionStr);
    }
}
