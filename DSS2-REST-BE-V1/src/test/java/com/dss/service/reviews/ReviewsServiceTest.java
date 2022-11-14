package com.dss.service.reviews;

import com.dss.Resources;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
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
public class ReviewsServiceTest {

    @MockBean
    private DssMovieRepository dssMovieRepository;

    @MockBean
    private ReviewsRepository reviewsRepository;

    @Autowired
    private ReviewsService reviewsService;

    private final Resources resources = new Resources();

    @Test
    public void testAddReviewSuccess(){
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.reviewsDto().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));
        DssCommonMessageDetails commonMsgDtl = reviewsService.addReview(resources.reviewsDto());
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testMovieNotExist(){
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(resources.reviewsDto().getMovieId());
        Mockito.when(movieList).thenReturn(Collections.singletonList(resources.dssMovie()));
        DssCommonMessageDetails commonMsgDtl = reviewsService.addReview(resources.reviewsWithNewMovieId());
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testDisplayReviewsSuccess(){
        Mockito.when(reviewsRepository.findAll()).thenReturn(Collections.singletonList(resources.reviews()));
        DssCommonMessageDetails commonMsgDtl = reviewsService.displayReviews();
        Assert.assertEquals(Boolean.TRUE, commonMsgDtl.isSuccess());
    }

    @Test
    public void testDisplayReviewsFailed(){
        List<Reviews> reviewsList = new ArrayList<>();
        Mockito.when(reviewsRepository.findAll()).thenReturn(reviewsList);
        DssCommonMessageDetails commonMsgDtl = reviewsService.displayReviews();
        Assert.assertEquals(Boolean.FALSE, commonMsgDtl.isSuccess());
    }
}
