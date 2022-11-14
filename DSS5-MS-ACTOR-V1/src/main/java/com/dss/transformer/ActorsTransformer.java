/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.transformer;

import com.dss.dto.ActorsDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.util.enums.UserRoles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is a DSS2 Actors Transformer
 */

public class ActorsTransformer {

    public List<Actors> transformToActor(List<Actors> actorsList){
        List<Actors> actors = new ArrayList<>();
        for(Actors ac : actorsList){
            actors.add(new Actors(
                    ac.getActorId(),
                    ac.getMovieId(),
                    ac.getFirstName(),
                    ac.getLastName(),
                    ac.getGender(),
                    ac.getAge(),
                    ac.getMovieCast(),
                    ac.getRole(),
                    ac.getCreationDate(),
                    ac.getCreatedBy(),
                    ac.getLastModificationDate(),
                    ac.getLastModifiedBy()
            ));
        }
        return actors;
    }

    public Actors transformToActorAdd(ActorsDTO actorDto, DssMovie movie){
        return new Actors(
                actorDto.getActorId(),
                actorDto.getMovieId(),
                actorDto.getFirstName(),
                actorDto.getLastName(),
                actorDto.getGender(),
                actorDto.getAge(),
                actorDto.getMovieCast(),
                actorDto.getRole(),
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                actorDto.getLastModificationDate(),
                actorDto.getLastModifiedBy(),
                movie
        );
    }

    public Actors transformToActorUpdate(ActorsDTO actorDto, DssMovie movie){
        return new Actors(
                actorDto.getActorId(),
                actorDto.getMovieId(),
                actorDto.getFirstName(),
                actorDto.getLastName(),
                actorDto.getGender(),
                actorDto.getAge(),
                actorDto.getMovieCast(),
                actorDto.getRole(),
                actorDto.getCreationDate(),
                actorDto.getCreatedBy(),
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                movie
        );
    }
}
