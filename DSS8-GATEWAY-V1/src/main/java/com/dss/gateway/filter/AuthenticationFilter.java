package com.dss.gateway.filter;

import com.dss.gateway.configuration.RouterValidator;
import com.dss.gateway.repository.RequestPathRepository;
import com.dss.gateway.util.exceptions.JwtTokenMalformedException;
import com.dss.gateway.util.exceptions.JwtTokenMissingException;
import com.dss.gateway.util.jwt.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.dss.gateway.util.jwt.JwtProperties.*;

@RefreshScope
@Component
@Slf4j
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private RouterValidator routerValidator;

    @Autowired
    private RequestPathRepository requestPathRepository;

    private static final String ERR_HTTP_401_UNAUTHORIZED = "Unauthorized. You cannot consume this service.";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String path = request.getPath().pathWithinApplication().value();
        log.debug("JwtAuthenticationFilter | filter | path : " + path);

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)) {
                log.error(ERR_HTTP_401_UNAUTHORIZED);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            final String token = this.getAuthHeader(request);

            try {
                //This method will validate the users' token.
                jwtUtility.validateToken(token);

                //check the resource String if user contains resourceCode String retrieved from the database
                String resourceCode = requestPathRepository.getPathCodeByRequestPath(path);
                String resource = jwtUtility.getResources(token);
                log.debug("JwtAuthenticationFilter | filter | resource : " + resource);
                log.debug("JwtAuthenticationFilter | filter | resourceCode : " + resourceCode);
                if(!resource.contains(resourceCode)){
                    log.error(ERR_HTTP_401_UNAUTHORIZED);
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }

                //check the action String if user contains actionCode retrieved from the database
                String action = jwtUtility.getAction(token);
                String actionCode = requestPathRepository.getActionCodeByRequestPath(path);
                log.debug("JwtAuthenticationFilter | filter | action : " + action);
                log.debug("JwtAuthenticationFilter | filter | actionCode : " + actionCode);
                if(!action.contains(actionCode)){
                    log.error(ERR_HTTP_401_UNAUTHORIZED);
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }

            } catch (JwtTokenMalformedException | JwtTokenMissingException | JSONException ex) {
                log.error("Bad Request. Invalid token.");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        return chain.filter(exchange);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HEADER_STRING);
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HEADER_STRING).get(0);
    }
}
