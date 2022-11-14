/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.controller.reviews;

import com.dss.dto.reviews.ReviewsDTO;
import com.dss.service.reviews.ReviewsService;
import com.dss.util.utils.DssCommonMessageDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * This class is a controller layer for DSS Reviews
 */

@RestController
@RequestMapping("/API/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    /** Returns a SDssCommonMessageDetails object if the admin user successfully adds the movie review or not.
     * @param reviewsDto reviewsDto
     * @return String
     * @see #addReview(ReviewsDTO)
     */
    @PostMapping("/add-review.do")
    public DssCommonMessageDetails addReview(@RequestBody ReviewsDTO reviewsDto){
        return reviewsService.addReview(reviewsDto);
    }

    /** Returns a DssCommonMessageDetails object
     * @return List<Reviews>
     * @see #displayReviews()
     */
    @GetMapping("/display-reviews.do")
    public DssCommonMessageDetails displayReviews(){
        return reviewsService.displayReviews();
    }
}
