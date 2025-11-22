package com.marketplace.backend.repositories;

import com.marketplace.backend.domain.entities.Review;
import com.marketplace.backend.domain.entities.Product;
import com.marketplace.backend.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface iReviewRepository extends iGenericRepository<Review, UUID> {

    Review findReviewById(UUID id);
    List<Review> findReviewsByReviewee(User reviewee);   // antes Seller
    List<Review> findReviewsByReviewer(User reviewer);   // antes Author
    List<Review> findReviewsByProduct(Product product);
}
