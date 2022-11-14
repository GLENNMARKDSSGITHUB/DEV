/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service;

import com.dss.dto.DssMovieDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
import com.dss.transformer.DssMovieTransformer;
import com.dss.util.exception.MovieNotExistingException;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * This class is a service implementation for DSS Movie
 * @see #addDssMovie(DssMovieDTO)
 * @see #displayDssMovies()
 * @see #searchDssMovieByMovieTitle(String)
 * @see #updateDssMovie(DssMovieDTO)
 * @see #deleteDssMovie(String)
 */

@Service
public class DssMovieServiceImpl implements DssMovieService {

    private final DssMovieTransformer transformer = new DssMovieTransformer();

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Override
    public DssCommonMessageDetails addDssMovie(DssMovieDTO dssDto){
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(dssDto.getMovieTitle());
        if(!movieList.isEmpty()){
            commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_MSG_MOVIE_EXIST, dssDto.getMovieTitle()));
            commonMsgDtl.setSuccess(false);
        }else{
            dssMovieRepository.save(transformer.transformToDssMovieAdd(dssDto, this.generateDssMovieId()));
            commonMsgDtl.setContent(CommonStringUtility.SUCCESS_MSG_ADD_MOVIE);
            commonMsgDtl.setSuccess(true);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayDssMovies(){
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<DssMovie> movieList = dssMovieRepository.findAll();
        if(!movieList.isEmpty()){
            List<DssMovie> movies = transformer.transformToDssMovie((movieList));
            commonMsgDtl.setObjList(movies);
            commonMsgDtl.setSuccess(true);
        }else{
            commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_NO_DISPLAY_RECORDS);
            commonMsgDtl.setSuccess(false);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails searchDssMovieByMovieTitle(String movieTitle) throws MovieNotExistingException {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(movieTitle);
        if(!movieList.isEmpty()){
            List<DssMovie> movies = transformer.transformToDssMovie((movieList));
            commonMsgDtl.setSuccess(true);
            commonMsgDtl.setObjList(movies);

        }else{
            throw new MovieNotExistingException(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails updateDssMovie(DssMovieDTO dssDto) throws MovieNotExistingException {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(dssDto.getMovieId());
        if(!movieList.isEmpty()){
            dssMovieRepository.save(transformer.transformToDssMovieUpdate(dssDto, movieList.get(0)));
            commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_MSG_UPDATE_MOVIE, dssDto.getMovieTitle()));
            commonMsgDtl.setSuccess(true);
        }else{
            throw new MovieNotExistingException(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails deleteDssMovie(String movieTitle) throws MovieNotExistingException {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(movieTitle);
        if(!movieList.isEmpty()){
            List<Reviews> reviewsList = movieList.get(0).getMovieReviews();
            reviewsRepository.deleteAll(reviewsList);

            List<Actors> actorsList = movieList.get(0).getMovieActors();
            actorsRepository.deleteAll(actorsList);

            dssMovieRepository.delete(movieList.get(0));
            commonMsgDtl.setSuccess(true);
            commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_MSG_DELETE_MOVIE, movieTitle));
        }else{
            throw new MovieNotExistingException(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST);
        }
        return commonMsgDtl;
    }

    private String generateDssMovieId(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        return String.format(CommonStringUtility.DSS_MOVIE_ID, uuid.toString().substring(0, 7).toUpperCase());
    }
}
