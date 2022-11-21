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
                    new RequestPath("R_AUTH", "A_AUTH", "/API/login.do"),
                    new RequestPath("R_REGISTRATION", "A_CREATE_ACCOUNT", "/API/registration/add-registration.do"),
                    new RequestPath("R_REGISTRATION", "A_VIEW_ACCOUNT", "/API/registration/display-registrations.do"),
                    new RequestPath("R_MOVIE", "A_ADD_MOVIE", "/API/movie-catalogue/add-digistreammovie.do"),
                    new RequestPath("R_MOVIE", "A_VIEW_MOVIES", "/API/movie-catalogue/display-digistreammovie.do"),
                    new RequestPath("R_MOVIE", "A_SEARCH_MOVIE", "/API/movie-catalogue/search-digistreammovie.do"),
                    new RequestPath("R_MOVIE", "A_UPDATE_MOVIE", "/API/movie-catalogue/update-digistreammovie.do"),
                    new RequestPath("R_MOVIE", "A_DELETE_MOVIE", "/API/movie-catalogue/delete-digistreammovie.do"),
                    new RequestPath("R_ACTOR", "A_ADD_ACTOR", "/API/actor/add-actor.do"),
                    new RequestPath("R_ACTOR", "A_VIEW_ACTOR", "/API/actor/display-actors.do"),
                    new RequestPath("R_ACTOR", "A_SEARCH_ACTOR", "/API/actor/search-actor.do"),
                    new RequestPath("R_ACTOR", "A_UPDATE_ACTOR", "/API/actor/update-actor.do"),
                    new RequestPath("R_ACTOR", "A_DELETE_ACTOR", "/API/actor/delete-actor.do"),
                    new RequestPath("R_REVIEW", "A_ADD_REVIEW", "/API/reviews/add-review.do"),
                    new RequestPath("R_REVIEW", "A_VIEW_REVIEWS", "/API/reviews/display-reviews.do")
            );
            requestPathRepository.saveAll(paths);
        }
    }

}
