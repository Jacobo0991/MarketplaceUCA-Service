package com.marketplace.backend.services.impl;

import com.marketplace.backend.domain.dto.review.CreateReviewDto;
import com.marketplace.backend.domain.dto.review.ResponseReviewDto;
import com.marketplace.backend.domain.dto.review.UpdateReviewDto;
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

        Product product = productRepository.findProductById(UUID.fromString(reviewDto.getProductId()));

        if (product == null) {
            throw new ProductNotFound();
        }

        User reviewer = getUserSession();  // El autor de la reseña
        User reviewee = product.getUser(); // El vendedor del producto

        Review review = new Review();
        review.setRating(reviewDto.getRating());
        review.setReviewText(reviewDto.getComment());
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setProduct(product);

        reviewer.addWrittenReview(review);
        reviewee.addReceivedReview(review);
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

        List<Review> reviews = reviewRepository.findReviewsByProduct(product);

        return reviewMappers.castReviewList(reviews);
    }


    @Override
    public List<ResponseReviewDto> getReviewsBySellerUsername(String username) {

        User seller = userRepository.findByUsername(username);

        if (seller == null) {
            throw new UserNotFound();
        }

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
    public ResponseReviewDto updateReview(UpdateReviewDto reviewDto) {

        Review review = reviewRepository.findReviewById(UUID.fromString(reviewDto.getReviewId()));

        if (review == null) {
            throw new ReviewNotFound();
        }

        // Solo puede editar quien la escribió
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

        // Solo el autor puede borrarla
        User sessionUser = getUserSession();
        if (!review.getReviewer().getId().equals(sessionUser.getId())) {
            throw new RuntimeException("You are not allowed to delete this review");
        }

        reviewRepository.delete(review);

        return "Review deleted successfully";
    }
}
