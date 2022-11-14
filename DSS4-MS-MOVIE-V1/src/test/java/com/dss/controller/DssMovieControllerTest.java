package com.dss.controller;

import com.dss.Resources;
import com.dss.entity.movie.DssMovie;
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
public class DssMovieControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private DssMovieRepository dssMovieRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private final Resources resources = new Resources();

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAddMovie() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(resources.dssMovie());
        mockMvc.perform(MockMvcRequestBuilders.post("/API/movie-catalogue/add-digistreammovie.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDisplayDigiStreamMovie() throws Exception {
        String jsonRequest = objectMapper.writeValueAsString(Collections.singletonList(resources.dssMovie()));
        mockMvc.perform(MockMvcRequestBuilders.get("/API/movie-catalogue/display-digistreammovie.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchDigiStreamMovie() throws Exception {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(resources.dssMovie().getMovieTitle());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        String jsonRequest = objectMapper.writeValueAsString(Collections.singletonList(resources.dssMovie()));
        mockMvc.perform(MockMvcRequestBuilders.get("/API/movie-catalogue/search-digistreammovie.do/The Avengers")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMovie() throws Exception {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.dssMovieDto().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        String jsonRequest = objectMapper.writeValueAsString(resources.dssMovieDto());
        mockMvc.perform(MockMvcRequestBuilders.put("/API/movie-catalogue/update-digistreammovie.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteDigiStreamMovie() throws Exception {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieTitle(resources.dssMovie().getMovieTitle());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        String jsonRequest = objectMapper.writeValueAsString(Collections.singletonList(resources.dssMovie()));
        mockMvc.perform(MockMvcRequestBuilders.delete("/API/movie-catalogue/delete-digistreammovie.do/The Avengers")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
