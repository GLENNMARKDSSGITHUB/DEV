package com.dss.controller.actors;

import com.dss.Resources;
import com.dss.entity.actors.Actors;
import com.dss.entity.movie.DssMovie;
import com.dss.repository.actors.ActorsRepository;
import com.dss.repository.movie.DssMovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActorsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ActorsRepository actorsRepository;

    @MockBean
    private DssMovieRepository dssMovieRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private final Resources resources = new Resources();

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAddActor() throws Exception {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.actors().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        String jsonRequest = objectMapper.writeValueAsString(resources.actors());
        mockMvc.perform(MockMvcRequestBuilders.post("/API/actor/add-actor.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDisplayActors() throws Exception {
        List<Actors> actorList = actorsRepository.findAll();
        Mockito.when(actorList).thenReturn(Collections.singletonList(resources.actors()));

        String jsonRequest = objectMapper.writeValueAsString(resources.actors());
        mockMvc.perform(MockMvcRequestBuilders.get("/API/actor/display-actors.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchActorByActorName() throws Exception {
        List<Actors> actorList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(actorList).thenReturn(Collections.singletonList(resources.actors()));

        String jsonRequest = objectMapper.writeValueAsString(resources.actors());
        mockMvc.perform(MockMvcRequestBuilders.get("/API/actor/search-actor.do/Robert/Downey Jr.")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateActor() throws Exception {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.actors().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        String jsonRequest = objectMapper.writeValueAsString(resources.actors());
        mockMvc.perform(MockMvcRequestBuilders.put("/API/actor/update-actor.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteActor() throws Exception {
        List<Actors> actorList = actorsRepository.findActorsByActorName("Robert", "Downey Jr.");
        Mockito.when(actorList).thenReturn(Collections.singletonList(resources.actors()));

        String jsonRequest = objectMapper.writeValueAsString(resources.actors());
        mockMvc.perform(MockMvcRequestBuilders.delete("/API/actor/delete-actor.do/Robert/Downey Jr.")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
