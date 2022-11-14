package com.dss.controller;

import com.dss.Resources;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
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
public class ReviewsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ReviewsRepository reviewsRepository;

    @MockBean
    private DssMovieRepository dssMovieRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private final Resources resources = new Resources();

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAddReview() throws Exception {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.reviews().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));

        String jsonRequest = objectMapper.writeValueAsString(resources.reviews());
        mockMvc.perform(MockMvcRequestBuilders.post("/API/reviews/add-review.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDisplayReviews() throws Exception {
        List<Reviews> reviewList = reviewsRepository.findAll();
        Mockito.when(reviewList).thenReturn(Collections.singletonList(resources.reviews()));

        String jsonRequest = objectMapper.writeValueAsString(resources.reviews());
        mockMvc.perform(MockMvcRequestBuilders.get("/API/reviews/display-reviews.do")
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
