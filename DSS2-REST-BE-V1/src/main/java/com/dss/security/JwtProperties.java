package com.dss.security;

public class JwtProperties {

    public static final String JWT_SECRET = "JWTGeneration";
    public static final long EXPIRATION_TIME =  900_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
