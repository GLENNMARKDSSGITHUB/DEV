package com.dss.security;

public class JwtProperties {

    public static final String JWT_SECRET = "JWTGeneration";
    public static final int EXPIRATION_TIME =  5 * 60 * 60;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
