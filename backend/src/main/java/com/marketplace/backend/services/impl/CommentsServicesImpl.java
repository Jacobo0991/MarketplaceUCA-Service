package com.marketplace.backend.services.impl;

import com.marketplace.backend.domain.dto.comments.CreateCommentsDto;
import com.marketplace.backend.domain.dto.comments.ResponseCommentsDto;
import com.marketplace.backend.domain.dto.comments.UpdateCommentsDto;
import com.marketplace.backend.domain.entities.Comments;
import com.marketplace.backend.domain.entities.Product;
import com.marketplace.backend.domain.entities.User;
import com.marketplace.backend.exceptions.comment.CommentNotFound;
import com.marketplace.backend.exceptions.product.ProductNotFound;
import com.marketplace.backend.exceptions.user.UserNotFound;
import com.marketplace.backend.repositories.iCommentsRepository;
import com.marketplace.backend.repositories.iProductRepository;
import com.marketplace.backend.repositories.iUserRepository;
import com.marketplace.backend.services.iCommentsServices;
import com.marketplace.backend.utils.mappers.CommentsMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsServicesImpl implements iCommentsServices {

    private final iCommentsRepository iCommentsRepository;
    private final iProductRepository iProductRepository;
    private final iUserRepository userRepository;
    private final CommentsMappers commentsMappers;

    @Override
    public User getUserSession() {
        User userNoSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userNoSession.getUsername());

        if (user == null){
            throw new UserNotFound();
        }

        return user;
    }

    @Override
    public ResponseCommentsDto addComment(CreateCommentsDto commentsDto) {
        Product product = iProductRepository.findProductById(UUID.fromString(commentsDto.getProductId()));
        User user = getUserSession();

        if (product == null) {
            throw new ProductNotFound();
        }

        Comments comments = new Comments();
        comments.setComment(commentsDto.getComment());

        user.addComments(comments);
        product.addComments(comments);
        iCommentsRepository.save(comments);
        return commentsMappers.castCommentsData(comments);
    }

    @Override
    public ResponseCommentsDto updateComment(UpdateCommentsDto commentsDto) {
        User user = getUserSession();
        Comments comments = iCommentsRepository.findCommentsByUserAndId(user, UUID.fromString(commentsDto.getId()));
        if (comments == null) {
            throw new CommentNotFound();
        }
        comments.setComment(commentsDto.getComment());
        iCommentsRepository.save(comments);
        return commentsMappers.castCommentsData(comments);
    }

    @Override
    public ResponseCommentsDto getCommentsById(String id) {
        Comments comments = iCommentsRepository.findCommentsById(UUID.fromString(id));

        if (comments == null) {
            throw new CommentNotFound();
        }

        return commentsMappers.castCommentsData(comments);
    }

    @Override
    public List<ResponseCommentsDto> getCommentsByProductId(String id) {
        Product product = iProductRepository.findProductById(UUID.fromString(id));

        if (product == null) {
            throw new ProductNotFound();
        }

        List<Comments> comments = iCommentsRepository.findCommentsByProduct(product);

        return commentsMappers.castResponseCommentsList(comments);
    }

    @Override
    public List<ResponseCommentsDto> getCommentsByUser() {
        User user = getUserSession();
        List<Comments> comments = iCommentsRepository.findCommentsByUser(user);

        return commentsMappers.castResponseCommentsList(comments);
    }

    @Override
    public String deleteComment(String id) {
        User user = getUserSession();
        Comments commentsToDelete = iCommentsRepository.findCommentsByUserAndId(user, UUID.fromString(id));

        if (commentsToDelete == null) {
            throw new CommentNotFound();
        }

        iCommentsRepository.delete(commentsToDelete);
        return "Comment deleted successfully";
    }
}
