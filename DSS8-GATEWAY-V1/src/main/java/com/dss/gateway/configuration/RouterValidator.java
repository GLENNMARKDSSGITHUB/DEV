package com.dss.gateway.configuration;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

import static com.dss.gateway.util.enums.RequestPath.*;

@Component
public class RouterValidator {

    public static final List<String> openApiEndpoints= List.of(
            REQUEST_PATH_AUTH_LOGIN.getEndpoint(), REQUEST_PATH_REG_ACCT.getEndpoint()
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
