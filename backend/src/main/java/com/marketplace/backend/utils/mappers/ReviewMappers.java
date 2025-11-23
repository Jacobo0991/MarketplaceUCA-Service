package com.marketplace.backend.utils.mappers;

import com.marketplace.backend.domain.dto.review.ResponseReviewDto;
import com.marketplace.backend.domain.entities.Review;
import com.marketplace.backend.exceptions.review.ReviewNotFound;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMappers {

    public ResponseReviewDto castReviewData(Review review) {
        if (review == null) {
            throw new ReviewNotFound();
        }

        ResponseReviewDto response = new ResponseReviewDto();
        response.setId(review.getId());
        response.setRating(review.getRating());
        response.setComment(review.getReviewText());
        response.setReviewer(review.getReviewer().getUsername());
        response.setProductId(review.getProduct().getId());

        return response;
    }

    public List<ResponseReviewDto> castReviewList(List<Review> reviews) {
        if (reviews == null) {
            throw new ReviewNotFound();
        }

        List<ResponseReviewDto> response = new ArrayList<>();

        for (Review review : reviews) {
            response.add(castReviewData(review));
        }

        return response;
    }
}
