package com.marketplace.backend.repositories;

import com.marketplace.backend.domain.entities.Comments;
import com.marketplace.backend.domain.entities.Product;
import com.marketplace.backend.domain.entities.User;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.UUID;

public interface iCommentsRepository extends iGenericRepository<Comments, UUID> {

    Comments findCommentsById(UUID id);
    List<Comments> findCommentsByProduct(Product product);
    List<Comments> findCommentsByUser(User user);
    Comments findCommentsByUserAndId(User user, UUID id);
    List<Comments> findCommentsByParent(Comments parent);
}
