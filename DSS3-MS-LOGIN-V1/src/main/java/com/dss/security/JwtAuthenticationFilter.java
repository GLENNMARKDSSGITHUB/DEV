package com.dss.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dss.entity.user.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/API/login.do");
    }

    /* Trigger when we issue POST request to /login.do
    * We also need to pass in {"username":"glenmark.ghl@gmail.com", "password":"P@$$w0rd1234"} in the request body
    */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // Grab credentials and map them to login viewmodel
        Users credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), Users.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create login token
        assert credentials != null;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getEmail(),
                credentials.getPassword(),
                new ArrayList<>());

        log.debug("JwtAuthenticationFilter | attemptAuthentication | username : {}", credentials.getEmail());
        log.debug("JwtAuthenticationFilter | attemptAuthentication | password : {}", credentials.getPassword());

        // Authenticate user
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        DssUserDetailsImpl principal = (DssUserDetailsImpl) authResult.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC512(JwtProperties.JWT_SECRET.getBytes());
        String access_token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).get(0))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        log.debug("JwtAuthenticationFilter | successfulAuthentication | access_token : {}", access_token);
        log.debug("JwtAuthenticationFilter | successfulAuthentication | refresh_token : {}", refresh_token);
        log.debug("JwtAuthenticationFilter | successfulAuthentication | expires at : {}", new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME));

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
