package com.dss.gateway.configuration;

import com.dss.gateway.filter.AuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.dss.gateway.util.enums.DssURI.*;
import static com.dss.gateway.util.enums.RequestPath.*;
import static com.dss.gateway.util.enums.RouteID.*;

@Configuration
@Slf4j
public class GatewayConfiguration {

    private final AuthenticationFilter filter;

    public GatewayConfiguration(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(ROUTE_ID_AUTH.getRouteName(), r -> r.path(REQUEST_PATH_AUTH.getEndpoint())
                        .filters(f -> f.filter(filter)).uri(REQUEST_URI_AUTH_REG.getRequestUri()))
                .route(ROUTE_ID_REG.getRouteName(), r -> r.path( REQUEST_PATH_REG.getEndpoint())
                        .filters(f -> f.filter(filter)).uri(REQUEST_URI_AUTH_REG.getRequestUri()))
                .route(ROUTE_ID_MOVIES.getRouteName(), r -> r.path(REQUEST_PATH_MOVIES.getEndpoint())
                        .filters(f -> f.filter(filter)).uri(REQUEST_URI_MOVIES.getRequestUri()))
                .route(ROUTE_ID_ACTORS.getRouteName(), r -> r.path(REQUEST_PATH_ACTORS.getEndpoint())
                        .filters(f -> f.filter(filter)).uri(REQUEST_URI_ACTORS.getRequestUri()))
                .route(ROUTE_ID_REVIEWS.getRouteName(), r -> r.path(REQUEST_PATH_REVIEWS.getEndpoint())
                        .filters(f -> f.filter(filter)).uri(REQUEST_URI_REVIEWS.getRequestUri()))
                .build();
    }
}
