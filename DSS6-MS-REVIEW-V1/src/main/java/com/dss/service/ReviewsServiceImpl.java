/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.service;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.repository.movie.DssMovieRepository;
import com.dss.repository.reviews.ReviewsRepository;
import com.dss.transformer.ReviewsTransformer;
import com.dss.util.utils.CommonStringUtility;
import com.dss.util.utils.DssCommonMessageDetails;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * This class is a service implementation for DSS Account Registration
 * @see #addReview(ReviewsDTO)
 * @see #displayReviews()
 */

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private final ReviewsTransformer transformer = new ReviewsTransformer();
    private final DssCommonMessageDetails commonMsgDtl = new DssCommonMessageDetails();

    @Autowired
    private DssMovieRepository dssMovieRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public DssCommonMessageDetails addReview(ReviewsDTO reviewsDto) {
        List<DssMovie> movieList = dssMovieRepository.findDssMovieByMovieId(reviewsDto.getMovieId());
        if(!movieList.isEmpty()){
            reviewsDto.setReviewId(this.generateDssReviewId());
            reviewsRepository.save(transformer.transformToReviews(reviewsDto, movieList.get(0)));
            commonMsgDtl.setSuccess(true);
            commonMsgDtl.setContent(String.format(CommonStringUtility.SUCCESS_MSG_ADD_REVIEW));
        }else{
            commonMsgDtl.setSuccess(false);
            commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_MOVIE_NOT_EXIST);
        }
        return commonMsgDtl;
    }

    @Override
    public DssCommonMessageDetails displayReviews() {
        List<Reviews> reviewsList = reviewsRepository.findAll();
        if(!reviewsList.isEmpty()){
            commonMsgDtl.setObjList(transformer.transformToReviews((reviewsList)));
            commonMsgDtl.setSuccess(true);
        }else{
            commonMsgDtl.setContent(CommonStringUtility.ERR_MSG_NO_DISPLAY_RECORDS);
            commonMsgDtl.setSuccess(false);
        }
        return commonMsgDtl;
    }

    private String generateDssReviewId(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        return String.format(CommonStringUtility.DSS_REVIEW_ID, uuid.toString().substring(0, 8).toUpperCase());
    }
}
