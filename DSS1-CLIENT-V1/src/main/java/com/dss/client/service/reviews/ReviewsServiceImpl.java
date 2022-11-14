package com.dss.client.service.reviews;

import com.dss.client.proxy.reviews.ReviewsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService{

    @Autowired
    ReviewsProxy reviewsProxy;

    @Override
    public String addReview(Object obj) {
        return reviewsProxy.addReview(obj);
    }

    @Override
    public List<Object> displayReviews() {
        return reviewsProxy.displayReviews();
    }
}
