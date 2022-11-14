package com.dss.client.controller.reviews;

import com.dss.client.service.reviews.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @PostMapping("/add-review.do")
    String addReview(@RequestBody Object obj){
        return reviewsService.addReview(obj);
    }

    @GetMapping("/display-reviews.do")
    List<Object> displayReviews(){
        return reviewsService.displayReviews();
    }
}
