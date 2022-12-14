package com.dss.security;

import com.auth0.jwt.JWT;
import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.dss.security.JwtProperties.*;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UsersRepository usersRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UsersRepository usersRepository) {
        super(authenticationManager);
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT token should be
        String header = request.getHeader(HEADER_STRING);

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING)
                .replace(TOKEN_PREFIX,"");

        log.debug("JwtAuthorizationFilter | getUsernamePasswordAuthentication | token : {}", token);

        // parse the token and validate it
        String userName = JWT.require(HMAC512(JWT_SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        log.debug("JwtAuthorizationFilter | getUsernamePasswordAuthentication | username : {}", userName);

        // Search in the DB if we find the user by token subject (username)
        // If so, then grab user details and create spring auth token using username, pass, authorities/roles
        if (userName != null) {
            Users user = usersRepository.findUserByEmailAddress(userName);
            DssUserDetailsImpl principal = new DssUserDetailsImpl(user);
            return new UsernamePasswordAuthenticationToken(userName, null, principal.getAuthorities());
        }
        return null;
    }
}
