package com.dss.gateway.configuration;

import com.dss.gateway.filter.AuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.dss.gateway.util.enums.DssURI.*;

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
                .route("ms-auth", r -> r.path("/API/login.do").filters(f -> f.filter(filter)).uri(REQUEST_URI_AUTH_REG.getRequestUri()))
                .route("ms-registration", r -> r.path("/API/registration/**").filters(f -> f.filter(filter)).uri(REQUEST_URI_AUTH_REG.getRequestUri()))
                .route("ms-movies", r -> r.path("/API/movie-catalogue/**").filters(f -> f.filter(filter)).uri(REQUEST_URI_MOVIES.getRequestUri()))
                .route("ms-actors", r -> r.path("/API/actor/**").filters(f -> f.filter(filter)).uri(REQUEST_URI_ACTORS.getRequestUri()))
                .route("ms-reviews", r -> r.path("/API/reviews/**").filters(f -> f.filter(filter)).uri(REQUEST_URI_REVIEWS.getRequestUri()))
                .build();
    }
}
