/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.transformer.reviews;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.entity.movie.DssMovie;
import com.dss.entity.reviews.Reviews;
import com.dss.util.enums.UserRoles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is a DSS2 Reviews Transformer
 */

public class ReviewsTransformer {

    public List<Reviews> transformToReviews(List<Reviews> reviewsList){
        List<Reviews> reviews = new ArrayList<>();
        for(Reviews rs : reviewsList){
            reviews.add(new Reviews(
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
        return reviews;
    }

    public Reviews transformToReviews(ReviewsDTO reviewDto, DssMovie movie){

        return new Reviews(
                reviewDto.getReviewId(),
                reviewDto.getMovieId(),
                reviewDto.getRate(),
                reviewDto.getReviewHeadline(),
                reviewDto.getReviewContent(),
                reviewDto.getCreationDate(),
                reviewDto.getCreatedBy(),
                reviewDto.getLastModificationDate(),
                reviewDto.getLastModifiedBy(),
                movie
        );
    }
}
