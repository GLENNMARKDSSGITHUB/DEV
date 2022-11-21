/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.gateway.seeder;

import com.dss.gateway.entity.RequestPath;
import com.dss.gateway.repository.RequestPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.dss.gateway.util.enums.ActionCode.*;
import static com.dss.gateway.util.enums.RequestPath.*;
import static com.dss.gateway.util.enums.ResourceCode.*;

/**
 * This class is a Database Seeder for Incoming request paths
 */

@Component
public class DatabaseSeeder {

    @Autowired
    private RequestPathRepository requestPathRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
    }

    private void seedUser(){
        List<RequestPath> pathList = requestPathRepository.findAll();
        if(pathList.isEmpty()){
            List<RequestPath> paths = Arrays.asList(
                    new RequestPath(R_AUTH.getResourceCode(), A_AUTH.getActionCode(), REQUEST_PATH_AUTH_LOGIN.getEndpoint()),
                    new RequestPath(R_REGISTRATION.getResourceCode(), A_CREATE_ACCOUNT.getActionCode(), REQUEST_PATH_REG_ACCT.getEndpoint()),
                    new RequestPath(R_REGISTRATION.getResourceCode(), A_VIEW_ACCOUNTS.getActionCode(), REQUEST_PATH_REG_VIEW_ACCT.getEndpoint()),
                    new RequestPath(R_MOVIE.getResourceCode(), A_ADD_MOVIE.getActionCode(), REQUEST_PATH_MOVIES_ADD_MOVIE.getEndpoint()),
                    new RequestPath(R_MOVIE.getResourceCode(), A_VIEW_MOVIES.getActionCode(), REQUEST_PATH_MOVIES_VIEW_MOVIES.getEndpoint()),
                    new RequestPath(R_MOVIE.getResourceCode(), A_SEARCH_MOVIE.getActionCode(), REQUEST_PATH_MOVIES_SEARCH_MOVIE.getEndpoint()),
                    new RequestPath(R_MOVIE.getResourceCode(), A_UPDATE_MOVIE.getActionCode(), REQUEST_PATH_MOVIES_UPDATE_MOVIE.getEndpoint()),
                    new RequestPath(R_MOVIE.getResourceCode(), A_DELETE_MOVIE.getActionCode(), REQUEST_PATH_MOVIES_DELETE_MOVIE.getEndpoint()),
                    new RequestPath(R_ACTOR.getResourceCode(), A_ADD_ACTOR.getActionCode(), REQUEST_PATH_ACTORS_ADD_ACTOR.getEndpoint()),
                    new RequestPath(R_ACTOR.getResourceCode(), A_VIEW_ACTORS.getActionCode(), REQUEST_PATH_ACTORS_VIEW_ACTORS.getEndpoint()),
                    new RequestPath(R_ACTOR.getResourceCode(), A_SEARCH_ACTOR.getActionCode(), REQUEST_PATH_ACTORS_SEARCH_ACTOR.getEndpoint()),
                    new RequestPath(R_ACTOR.getResourceCode(), A_UPDATE_ACTOR.getActionCode(), REQUEST_PATH_ACTORS_UPDATE_ACTOR.getEndpoint()),
                    new RequestPath(R_ACTOR.getResourceCode(), A_DELETE_ACTOR.getActionCode(), REQUEST_PATH_ACTORS_DELETE_ACTOR.getEndpoint()),
                    new RequestPath(R_REVIEW.getResourceCode(), A_ADD_REVIEW.getActionCode(), REQUEST_PATH_REVIEWS_ADD_REVIEW.getEndpoint()),
                    new RequestPath(R_REVIEW.getResourceCode(), A_VIEW_REVIEWS.getActionCode(), REQUEST_PATH_REVIEWS_VIEW_REVIEWS.getEndpoint())
            );
            requestPathRepository.saveAll(paths);
        }
    }

}
