package com.dss.gateway.util.enums;

public enum DssURI {
    REQUEST_URI_AUTH_REG("lb://dss3-ms-login-v1"),
    REQUEST_URI_MOVIES("lb://dss4-ms-movie-v1"),
    REQUEST_URI_ACTORS("lb://dss5-ms-actor-v1"),
    REQUEST_URI_REVIEWS("lb://dss6-ms-review-v1");

    private final String requestUri;

    DssURI(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestUri() {
        return requestUri;
    }
}
