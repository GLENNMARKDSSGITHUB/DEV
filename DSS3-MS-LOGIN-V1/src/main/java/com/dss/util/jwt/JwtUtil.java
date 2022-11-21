package com.dss.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.dss.util.jwt.JwtProperties.*;

@Component
@Slf4j
public class JwtUtil {

    @Autowired
    private UsersRepository usersRepository;

    public Map<String, String> generateToken(String id){
        Map<String, String> tokens = new HashMap<>();

        Users user = usersRepository.findUserByEmailAddress(id);

        Algorithm algorithm = Algorithm.HMAC512(JwtProperties.JWT_SECRET.getBytes());
        String access_token = JWT.create()
                .withSubject(id)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withIssuer(REQUEST_URL)
                .withClaim("roles", user.getUserRoles().get(0).getUserRole())
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(id)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withIssuer(REQUEST_URL)
                .sign(algorithm);

        log.debug("JwtUtil | generateToken | access_token : {}", access_token);
        log.debug("JwtUtil | generateToken | refresh_token : {}", refresh_token);
        log.debug("JwtUtil | generateToken | expires at : {}", new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME));
        log.debug("JwtUtil | generateToken | validate token : {}", validateToken(access_token));

        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        return tokens;
    }

    public boolean validateToken(String token){
        String userName = JWT.require(HMAC512(JWT_SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        if(userName != null) {
            return true;
        }
        return false;
    }
}
