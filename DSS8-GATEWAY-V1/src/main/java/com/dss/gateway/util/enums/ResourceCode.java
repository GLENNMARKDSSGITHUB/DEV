package com.dss.gateway.util.enums;

public enum ResourceCode {
    R_AUTH("R_AUTH"),
    R_REGISTRATION("R_REGISTRATION"),
    R_MOVIE("R_MOVIE"),
    R_ACTOR("R_ACTOR"),
    R_REVIEW("R_REVIEW");

    private final String resourceCode;

    ResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceCode() {
        return resourceCode;
    }
}
