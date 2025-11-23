package com.marketplace.backend.services.impl;

import com.marketplace.backend.domain.dto.review.CreateReviewDto;
import com.marketplace.backend.domain.dto.review.ResponseReviewDto;
import com.marketplace.backend.domain.entities.Product;
import com.marketplace.backend.domain.entities.Review;
import com.marketplace.backend.domain.entities.User;
import com.marketplace.backend.exceptions.product.ProductNotFound;
import com.marketplace.backend.exceptions.review.ReviewNotFound;
import com.marketplace.backend.exceptions.user.UserNotFound;
import com.marketplace.backend.repositories.iProductRepository;
import com.marketplace.backend.repositories.iReviewRepository;
import com.marketplace.backend.repositories.iUserRepository;
import com.marketplace.backend.services.iReviewServices;
import com.marketplace.backend.utils.mappers.ReviewMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServicesImpl implements iReviewServices {

    private final iReviewRepository reviewRepository;
    private final iProductRepository productRepository;
    private final iUserRepository userRepository;
    private final ReviewMappers reviewMappers;


    @Override
    public User getUserSession() {
        User userNoSession = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByUsername(userNoSession.getUsername());

        if (user == null) {
            throw new UserNotFound();
        }

        return user;
    }

    @Override
    public ResponseReviewDto addReview(CreateReviewDto reviewDto) {

        User reviewer = getUserSession();

        User reviewee = userRepository.findById(UUID.fromString(reviewDto.getRevieweeId()))
                .orElseThrow(UserNotFound::new);

        // Evitar que alguien se califique a sí mismo
        if (reviewer.getId().equals(reviewee.getId())) {
            throw new RuntimeException("You cannot review yourself");
        }

        Review review = new Review();
        review.setRating(reviewDto.getRating());
        review.setReviewText(reviewDto.getComment());
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);

        reviewRepository.save(review);

        return reviewMappers.castReviewData(review);
    }


    @Override
    public ResponseReviewDto getReviewById(String id) {
        Review review = reviewRepository.findReviewById(UUID.fromString(id));

        if (review == null) {
            throw new ReviewNotFound();
        }

        return reviewMappers.castReviewData(review);
    }


    @Override
    public List<ResponseReviewDto> getReviewsByProductId(String productId) {

        Product product = productRepository.findProductById(UUID.fromString(productId));

        if (product == null) {
            throw new ProductNotFound();
        }

        User seller = product.getUser(); // dueño del producto

        List<Review> reviews = reviewRepository.findReviewsByReviewee(seller);

        return reviewMappers.castReviewList(reviews);
    }


    @Override
    public List<ResponseReviewDto> getReviewsBySellerId(String sellerId) {

        User seller = userRepository.findById(UUID.fromString(sellerId))
                .orElseThrow(UserNotFound::new);

        List<Review> reviews = reviewRepository.findReviewsByReviewee(seller);

        return reviewMappers.castReviewList(reviews);
    }


    @Override
    public List<ResponseReviewDto> getReviewsByUser() {

        User user = getUserSession();
        List<Review> reviews = reviewRepository.findReviewsByReviewer(user);

        return reviewMappers.castReviewList(reviews);
    }


    @Override
    public ResponseReviewDto updateReview(String id, CreateReviewDto reviewDto) {

        Review review = reviewRepository.findReviewById(UUID.fromString(id));

        if (review == null) {
            throw new ReviewNotFound();
        }

        User sessionUser = getUserSession();
        if (!review.getReviewer().getId().equals(sessionUser.getId())) {
            throw new RuntimeException("You are not allowed to edit this review");
        }

        review.setRating(reviewDto.getRating());
        review.setReviewText(reviewDto.getComment());

        reviewRepository.save(review);

        return reviewMappers.castReviewData(review);
    }


    @Override
    public String deleteReview(String id) {

        Review review = reviewRepository.findReviewById(UUID.fromString(id));

        if (review == null) {
            throw new ReviewNotFound();
        }

        User sessionUser = getUserSession();
        if (!review.getReviewer().getId().equals(sessionUser.getId())) {
            throw new RuntimeException("You are not allowed to delete this review");
        }

        reviewRepository.delete(review);

        return "Review deleted successfully";
    }
}
