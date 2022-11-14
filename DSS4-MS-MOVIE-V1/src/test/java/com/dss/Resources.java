package com.dss;

import com.dss.dto.DssMovieDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.util.enums.UserRoles;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Resources {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public DssMovie dssMovie(){
        List<Actors> actorsList = Collections.singletonList(new Actors(
                "ACC9993BCB",
                "DSSC1BD5CB",
                "Robert",
                "Downey Jr.",
                "Male", 45,
                "Tony Stark/Iron Man",
                UserRoles.ROLE_ADMIN.getStrRole(),
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                null,
                null)
        );

        List<Reviews> reviewsList =  Collections.singletonList(new Reviews(
                "RTCD5691EC",
                "DSSC1BD5CB",
                5,
                "Rating Headline 1",
                "Rating Content 1", new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                null,
                null));
        return new DssMovie(
                "DSSC1BD5CB",
                "The Avengers",
                "2021",
                "Stan Lee",
                "Joss Whedon",
                "Trinh Tran",
                "Alan Silvestri",
                "143 minutes",
                400000000,
                "PG-13 TV-14",
                "United States",
                "English",
                "dr-strange.jpg",
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                null,
                null,
                actorsList,
                reviewsList
        );
    }

    public DssMovieDTO dssMovieDto(){
        return new DssMovieDTO(
                "DSSC1BD5CB",
                "The Avengers",
                "2021",
                "Stan Lee",
                "Joss Whedon",
                "Trinh Tran",
                "Alan Silvestri",
                "143 minutes",
                400000000,
                "PG-13 TV-14",
                "United States",
                "English",
                "dr-strange.jpg",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null
        );
    }
}
