package com.dss.client.service.reviews;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReviewsService {

    String addReview(@RequestBody Object obj);

    List<Object> displayReviews();
}
