package com.dss;

import com.dss.dto.actors.ActorsDTO;
import com.dss.dto.movie.DssMovieDTO;
import com.dss.dto.reviews.ReviewsDTO;
import com.dss.dto.user.UsersDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.entity.roles.Roles;
import com.dss.entity.user.Users;
import com.dss.util.enums.UserRoles;
import com.dss.util.enums.UserStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
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

    public Actors actors(){
        return new Actors(
                "ACC9993BCB",
                "DSSC1BD5CB",
                "Robert",
                "Downey Jr.",
                "Male",
                45,
                "Tony Stark/Iron Man",
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                new Date(), UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }

    public ActorsDTO actorsDto(){
        return new ActorsDTO(
                "ACC9993BCB",
                "DSSC1BD5CB",
                "Robert",
                "Downey Jr.",
                "Male",
                45,
                "Tony Stark/Iron Man",
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                new Date(), UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }

    public ActorsDTO newActorsDto(){
        return new ActorsDTO(
                "ACC9993BCD",
                "DSSC1BD5CB",
                "Glenn Mark",
                "Anduiza",
                "Male",
                18,
                "Extra",
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                new Date(), UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }

    public ActorsDTO actorWithDtoNewMovieId(){
        return new ActorsDTO(
                "ACC9993BCD",
                "DSSC1BD5CD",
                "Glenn Mark",
                "Anduiza",
                "Male",
                18,
                "Extra",
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                new Date(), UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }

    public Reviews reviews(){
        return new Reviews(
                "RTCD5691EC",
                "DSSC1BD5CD",
                5,
                "Rating Headline 1",
                "Rating Content 1",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }
    public ReviewsDTO reviewsWithNewMovieId(){
        return new ReviewsDTO(
                "RTCD5691EC",
                "DSSC1BD5CB",
                5,
                "Rating Headline 1",
                "Rating Content 1",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }


    public ReviewsDTO reviewsDto(){
        return new ReviewsDTO(
                "RTCD5691EC",
                "DSSC1BD5CD",
                5,
                "Rating Headline 1",
                "Rating Content 1",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null);
    }

    public Users user(){
        return new Users(
                "US0001",
                "Glenn Mark",
                "Anduiza",
                "glenmark.ghl@gmail.com",
                encoder.encode("P@$$w0rd1234"),
                UserStatus.ACTIVE.toString(),
                "09106121529",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null
        );
    }

    public Users users(){
        return new Users(
                "US0001",
                "Glenn Mark",
                "Anduiza",
                "glenmark.ghl@gmail.com",
                encoder.encode("P@$$w0rd1234"),
                UserStatus.ACTIVE.toString(),
                "09106121529",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null,
                Collections.singletonList(new Roles(
                        "RS1",
                        UserRoles.ROLE_SUPER_ADMIN.getStrRole()
                ))
        );
    }

    public Users userForValidation(){
        return new Users(
                "US0001",
                "Glenn Mark@3",
                "Anduiza@3",
                "@gmail.com",
                encoder.encode("P@$$w0rd1234"),
                UserStatus.ACTIVE.toString(),
                "09106121529",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null,
                Collections.singletonList(new Roles(
                        "RS1",
                        UserRoles.ROLE_SUPER_ADMIN.getStrRole()
                ))
        );
    }

    @Valid
    public UsersDTO userDto(){
        return new UsersDTO(
                "US0001",
                "Glenn Mark",
                "Anduiza",
                "glenmark.ghl@gmail.com",
                encoder.encode("P@$$w0rd1234"),
                UserStatus.ACTIVE.toString(),
                "09106121529",
                new Date(),
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                null,
                null
        );
    }

    public Roles role(){
        return new Roles(
                "RS1",
                UserRoles.ROLE_SUPER_ADMIN.getStrRole(),
                user()
        );
    }
}
