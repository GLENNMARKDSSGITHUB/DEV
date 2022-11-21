package com.dss.gateway.service;

import com.dss.gateway.filter.RegistrationAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import reactor.core.publisher.Flux;

import static com.dss.gateway.util.enums.DssURI.REQUEST_URI_AUTH_REG;

@SuppressWarnings("unchecked")
@AllArgsConstructor
public class ApiRouteServiceImpl implements RouteLocator {

    @Autowired
    private final RouteLocatorBuilder.Builder routeLocatorBuilder;

    @Autowired
    private final RegistrationAuthFilter filter;


    @Override
    public Flux<Route> getRoutes() {
        return (Flux<Route>) routeLocatorBuilder
                .route("ms-auth", r -> r.path("/API/login.do")
                        .filters(f -> f.filter(filter))
                        .uri(REQUEST_URI_AUTH_REG.getRequestUri()))
                .build();
    }
}
