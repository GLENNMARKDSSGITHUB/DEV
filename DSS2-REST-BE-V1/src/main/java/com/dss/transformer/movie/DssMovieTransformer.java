/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */


package com.dss.transformer.movie;

import com.dss.dto.movie.DssMovieDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.util.enums.UserRoles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is a DSS2 Movie Transformer
 */

public class DssMovieTransformer {

    public List<DssMovie> transformToDssMovie(List<DssMovie> movieList){
        List<DssMovie> dssMovie = new ArrayList<>();

        for(DssMovie dss : movieList){
            List<Actors> actorsList = new ArrayList<>();
            for(Actors ac : dss.getMovieActors()){
                actorsList.add(new Actors(
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

            List<Reviews> reviewsList = new ArrayList<>();
            for(Reviews rs : dss.getMovieReviews()){
                reviewsList.add(new Reviews(
                        rs.getReviewId(),
                        rs.getMovieId(),
                        rs.getRate(),
                        rs.getReviewHeadline(),
                        rs.getReviewContent(),
                        rs.getCreationDate(),
                        rs.getCreatedBy(),
                        rs.getLastModificationDate(),
                        rs.getLastModifiedBy()
                ));
            }

            dssMovie.add(new DssMovie(
                    dss.getMovieId(),
                    dss.getMovieTitle(),
                    dss.getYear(),
                    dss.getWriters(),
                    dss.getDirectedBy(),
                    dss.getProducedBy(),
                    dss.getMusicBy(),
                    dss.getDuration(),
                    dss.getMovieCost(),
                    dss.getCategory(),
                    dss.getCountry(),
                    dss.getLanguage(),
                    dss.getImage(),
                    dss.getCreationDate(),
                    dss.getCreatedBy(),
                    dss.getLastModificationDate(),
                    dss.getLastModifiedBy(),
                    actorsList,
                    reviewsList
            ));
        }
        return dssMovie;
    }

    public DssMovie transformToDssMovieAdd(DssMovieDTO dssDto, String movieId){
        return new DssMovie(
                movieId,
                dssDto.getMovieTitle(),
                dssDto.getYear(),
                dssDto.getWriters(),
                dssDto.getDirectedBy(),
                dssDto.getProducedBy(),
                dssDto.getMusicBy(),
                dssDto.getDuration(),
                dssDto.getMovieCost(),
                dssDto.getCategory(),
                dssDto.getCountry(),
                dssDto.getLanguage(),
                dssDto.getImage(),
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole(),
                dssDto.getLastModificationDate(),
                dssDto.getLastModifiedBy()
        );
    }

    public DssMovie transformToDssMovieUpdate(DssMovieDTO dssDto, DssMovie ddsMovie){
        return new DssMovie(
                ddsMovie.getMovieId(),
                ddsMovie.getMovieTitle(),
                ddsMovie.getYear(),
                ddsMovie.getWriters(),
                ddsMovie.getDirectedBy(),
                ddsMovie.getProducedBy(),
                ddsMovie.getMusicBy(),
                ddsMovie.getDuration(),
                dssDto.getMovieCost(),
                ddsMovie.getCategory(),
                ddsMovie.getCountry(),
                ddsMovie.getLanguage(),
                dssDto.getImage(),
                ddsMovie.getCreationDate(),
                ddsMovie.getCreatedBy(),
                new Date(),
                UserRoles.ROLE_ADMIN.getStrRole()
        );
    }
}
