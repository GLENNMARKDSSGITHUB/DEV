package com.dss.gateway.util.enums;

public enum RouteID {
    ROUTE_ID_AUTH("ms-auth"),
    ROUTE_ID_REG("ms-registration"),
    ROUTE_ID_MOVIES("ms-movies"),
    ROUTE_ID_ACTORS("ms-actors"),
    ROUTE_ID_REVIEWS("ms-reviews");

    private final String routeName;

    RouteID(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteName() {
        return routeName;
    }
}
