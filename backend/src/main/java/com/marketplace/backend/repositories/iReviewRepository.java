package com.marketplace.backend.repositories;

import com.marketplace.backend.domain.entities.Review;
import com.marketplace.backend.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface iReviewRepository extends iGenericRepository<Review, UUID> {

    Review findReviewById(UUID id);

    // Reseñas que recibioun usuar (vendedor)
    List<Review> findReviewsByReviewee(User reviewee);

    // Reseñas que escribioun usuario
    List<Review> findReviewsByReviewer(User reviewer);
}
