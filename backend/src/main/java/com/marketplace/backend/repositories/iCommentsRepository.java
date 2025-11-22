package com.marketplace.backend.repositories;

import com.marketplace.backend.domain.entities.Comments;
import com.marketplace.backend.domain.entities.Product;
import com.marketplace.backend.domain.entities.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface iCommentsRepository extends iGenericRepository<Comments, UUID> {

    Comments findCommentsById(UUID id);
    List<Comments> findCommentsByUser(User user);
    Comments findCommentsByUserAndId(User user, UUID id);
    List<Comments> findCommentsByParent(Comments parent);

    @Query("SELECT c FROM Comments c " +
            "WHERE c.product = :product " +
            "ORDER BY COALESCE(c.updatedAt, c.createdAt) DESC"
    )
    List<Comments> findCommentsByProductSortedByDate(@Param("product") Product product);
}
