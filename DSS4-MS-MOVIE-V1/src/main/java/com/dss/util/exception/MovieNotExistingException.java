package com.dss.util.exception;

/*
 * @throws NullObjectException - if the Object is null or empty
 *
 * */

public class MovieNotExistingException extends Exception{

    public MovieNotExistingException(String exceptionStr) {
        super(exceptionStr);
    }
}
