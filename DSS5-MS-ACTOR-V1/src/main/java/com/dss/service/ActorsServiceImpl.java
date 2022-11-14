/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service;

import com.dss.dto.ActorsDTO;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.transformer.ActorsTransformer;
import com.dss.util.exception.MovieNotExistingException;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * This class is a service implementation for DSS Movie Actors
 * @see #addActor(ActorsDTO)
 * @see #displayActors()
 * @see #searchActorByActorName(String, String)
 * @see #updateActor(ActorsDTO)
 * @see #deleteActor(String, String)
 */

@Service
public class ActorsServiceImpl implements ActorsService{
    private final ActorsTransformer transformer = new ActorsTransformer();

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Override
    public DssCommonMessageDetails addActor(ActorsDTO actorDto) throws MovieNotExistingException {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(actorDto.getMovieId());
        if(!movieList.isEmpty()){
            List<Actors> actorsList = movieList.get(0).getMovieActors().stream()
                    .filter(actors -> actors.getFirstName().equalsIgnoreCase(actorDto.getFirstName()) || actors.getLastName().equalsIgnoreCase(actorDto.getLastName()))
                    .collect(Collectors.toList());
            if(actorsList.isEmpty()){
                actorDto.setActorId(this.generateDssActorId());
                actorsRepository.save(transformer.transformToActorAdd(actorDto,  movieList.get(0)));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_MSG_ADD_ACTOR, actorDto.getFirstName() + " " + actorDto.getLastName()));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setContent(String.format(CommonStringUtility.ERR_MSG_ACTOR_EXIST, actorDto.getFirstName() + " " + actorDto.getLastName()));
                commonMsgDtl.setSuccess(false);
            }
        }else{
            throw new MovieNotExistingException(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayActors() {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<Actors> actorsList = actorsRepository.findAll();
        if(!actorsList.isEmpty()){
            commonMsgDtl.setSuccess(true);
            commonMsgDtl.setObjList(transformer.transformToActor((actorsList)));
        }else{
            commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_NO_DISPLAY_RECORDS);
            commonMsgDtl.setSuccess(false);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails searchActorByActorName(String firstName, String lastName) {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<Actors> actorsList = actorsRepository.findActorsByActorName(firstName, lastName);
        if(!actorsList.isEmpty()){
            commonMsgDtl.setObjList(transformer.transformToActor((actorsList)));
            commonMsgDtl.setSuccess(true);
        }else{
            commonMsgDtl.setSuccess(false);
            commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_ACTOR_NOT_EXIST);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails updateActor(ActorsDTO actorDto) throws MovieNotExistingException {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(actorDto.getMovieId());
        if(!movieList.isEmpty()){
            List<Actors> actorsList = movieList.get(0).getMovieActors().stream()
                    .filter(actors -> actors.getActorId().equalsIgnoreCase(actorDto.getActorId()))
                    .collect(Collectors.toList());
            if(!actorsList.isEmpty()){
                actorsRepository.save(transformer.transformToActorUpdate(actorDto,  movieList.get(0)));
                commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_MSG_UPDATE_ACTOR, actorDto.getFirstName() + " " + actorDto.getLastName()));
                commonMsgDtl.setSuccess(true);
            }else{
                commonMsgDtl.setSuccess(false);
                commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_ACTOR_NOT_EXIST);
            }
        }else{
            throw new MovieNotExistingException(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails deleteActor(String firstName, String lastName) {
        DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();
        List<Actors> actorsList = actorsRepository.findActorsByActorName(firstName, lastName);
        if(!actorsList.isEmpty()){
            actorsRepository.delete(actorsList.get(0));
            commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_MSG_DELETE_ACTOR, firstName + " " + lastName));
            commonMsgDtl.setSuccess(true);
        }else{
            commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_ACTOR_NOT_EXIST);
            commonMsgDtl.setSuccess(false);
        }
        return commonMsgDtl;
    }

    private String generateDssActorId(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        return String.format(CommonStringUtility.DSS_ACTOR_ID, uuid.toString().substring(0, 8).toUpperCase());
    }
}
