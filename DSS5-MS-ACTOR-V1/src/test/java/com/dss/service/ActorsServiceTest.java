package com.dss.service;

import com.dss.Resources;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.util.exception.MovieNotExistingException;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActorsServiceTest {

    @MockBean
    private ActorsRepository actorsRepository;

    @MockBean
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ActorsService actorsService;

    private final Resources resources = new Resources();

    @Test
    public void testAddActorSuccess() throws MovieNotExistingException {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.newActorsDto().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));
        DssCommonMessageDetails commonMsgDtl = actorsService.addActor(resources.newActorsDto());
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testActorsExist() throws MovieNotExistingException {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.actorsDto().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));
        DssCommonMessageDetails commonMsgDtl = actorsService.addActor(resources.actorsDto());
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }

    @Test(expected = MovieNotExistingException.class)
    public void testMovieNotExist_Add() throws MovieNotExistingException {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.actorWithDtoNewMovieId().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        Mockito.when(actorsService.addActor(resources.actorsDto()))
                .thenThrow(new MovieNotExistingException(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST));
    }

    @Test
    public void testDisplayActorsSuccess(){
        List<Actors> actorsList = actorsRepository.findAll();
        Mockito.when(actorsList).thenReturn(Collections.singletonList(resources.actors()));
        DssCommonMessageDetails commonMsgDtl = actorsService.displayActors();
        Assert.assertEquals(1, commonMsgDtl.getObjList().size());
    }

    @Test
    public void testDisplayActorsFailed(){
        List<Actors> actorsList = new ArrayList<>();
        Mockito.when(actorsRepository.findAll()).thenReturn(actorsList);
        DssCommonMessageDetails commonMsgDtl = actorsService.displayActors();
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testSearchActorByActorNameSuccess(){
        List<Actors> actorsList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(actorsList).thenReturn(Collections.singletonList(resources.actors()));
        DssCommonMessageDetails commonMsgDtl = actorsService.searchActorByActorName("Robert", "Downey Jr.");
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testSearchActorFailed(){
        List<Actors> actorsList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(actorsList).thenReturn(Collections.singletonList(resources.actors()));
        DssCommonMessageDetails commonMsgDtl = actorsService.searchActorByActorName("Glenn Mark", "Anduiza");
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testUpdateActorSuccess() throws MovieNotExistingException {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.actorsDto().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));
        DssCommonMessageDetails commonMsgDtl =actorsService.updateActor(resources.actorsDto());
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test(expected = MovieNotExistingException.class)
    public void testMovieNotExist_Update() throws MovieNotExistingException {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.actorWithDtoNewMovieId().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        Mockito.when(actorsService.updateActor(resources.actorsDto()))
                .thenThrow(new MovieNotExistingException(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST));
    }

    @Test
    public void testActorsNotExist() throws MovieNotExistingException {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.actorsDto().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));
        DssCommonMessageDetails commonMsgDtl = actorsService.updateActor(resources.newActorsDto());
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testDeleteActorSuccess(){
        List<Actors> actorsList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(actorsList).thenReturn(Collections.singletonList(resources.actors()));
        DssCommonMessageDetails commonMsgDtl = actorsService.deleteActor("Robert", "Downey Jr.");
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testDeleteActorFailed(){
        List<Actors> actorsList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(actorsList).thenReturn(Collections.singletonList(resources.actors()));
        DssCommonMessageDetails commonMsgDtl = actorsService.deleteActor("Glenn Mark", "Anduiza");
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }
}
