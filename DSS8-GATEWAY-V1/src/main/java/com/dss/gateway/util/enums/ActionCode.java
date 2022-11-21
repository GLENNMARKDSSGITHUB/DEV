package com.dss.gateway.util.enums;

public enum ActionCode {
    A_AUTH("A_AUTH"),
    A_CREATE_ACCOUNT("A_CREATE_ACCOUNT"),
    A_VIEW_ACCOUNTS("A_VIEW_ACCOUNTS"),
    A_ADD_MOVIE("A_ADD_MOVIE"),
    A_VIEW_MOVIES("A_VIEW_MOVIES"),
    A_SEARCH_MOVIE("A_SEARCH_MOVIE"),
    A_UPDATE_MOVIE("A_UPDATE_MOVIE"),
    A_DELETE_MOVIE("A_DELETE_MOVIE"),
    A_ADD_ACTOR("A_ADD_ACTOR"),
    A_VIEW_ACTORS("A_VIEW_ACTORS"),
    A_SEARCH_ACTOR("A_SEARCH_ACTOR"),
    A_UPDATE_ACTOR("A_UPDATE_ACTOR"),
    A_DELETE_ACTOR("A_DELETE_ACTOR"),
    A_ADD_REVIEW("A_ADD_REVIEW"),
    A_VIEW_REVIEWS("A_VIEW_REVIEWS");

    private final String actionCode;

    ActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionCode() {
        return actionCode;
    }
}
