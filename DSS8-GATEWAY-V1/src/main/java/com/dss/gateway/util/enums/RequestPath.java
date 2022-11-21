package com.dss.gateway.util.enums;

public enum RequestPath {
    REQUEST_PATH_AUTH("/API/login.do"),
    REQUEST_PATH_REG("/API/registration/**"),
    REQUEST_PATH_MOVIES("/API/movie-catalogue/**"),
    REQUEST_PATH_ACTORS("/API/actor/**"),
    REQUEST_PATH_REVIEWS("/API/reviews/**"),
    REQUEST_PATH_AUTH_LOGIN("/API/login.do"),
    REQUEST_PATH_REG_ACCT("/API/registration/add-registration.do"),
    REQUEST_PATH_REG_VIEW_ACCT("/API/registration/display-registrations.do"),
    REQUEST_PATH_MOVIES_ADD_MOVIE("/API/movie-catalogue/add-digistreammovie.do"),
    REQUEST_PATH_MOVIES_VIEW_MOVIES("/API/movie-catalogue/display-digistreammovie.do"),
    REQUEST_PATH_MOVIES_SEARCH_MOVIE("/API/movie-catalogue/search-digistreammovie.do"),
    REQUEST_PATH_MOVIES_UPDATE_MOVIE("/API/movie-catalogue/update-digistreammovie.do"),
    REQUEST_PATH_MOVIES_DELETE_MOVIE("/API/movie-catalogue/delete-digistreammovie.do"),
    REQUEST_PATH_ACTORS_ADD_ACTOR("/API/actor/add-actor.do"),
    REQUEST_PATH_ACTORS_VIEW_ACTORS("/API/actor/display-actors.do"),
    REQUEST_PATH_ACTORS_SEARCH_ACTOR("/API/actor/search-actor.do"),
    REQUEST_PATH_ACTORS_UPDATE_ACTOR("/API/actor/update-actor.do"),
    REQUEST_PATH_ACTORS_DELETE_ACTOR("/API/actor/delete-actor.do"),
    REQUEST_PATH_REVIEWS_ADD_REVIEW("/API/reviews/add-review.do"),
    REQUEST_PATH_REVIEWS_VIEW_REVIEWS("/API/reviews/display-reviews.do");
    ;

    private final String endpoint;

    RequestPath(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
