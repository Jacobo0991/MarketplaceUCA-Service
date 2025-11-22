package com.marketplace.backend.utils.mappers;

import com.marketplace.backend.domain.dto.comments.ResponseCommentsDto;
import com.marketplace.backend.domain.dto.comments.ResponseCommentsListDto;
import com.marketplace.backend.domain.entities.Comments;
import com.marketplace.backend.domain.entities.Images;
import com.marketplace.backend.exceptions.comment.CommentNotFound;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentsMappers {

    public ResponseCommentsDto castCommentsData(Comments comments) {
        ResponseCommentsDto response = new ResponseCommentsDto();
        response.setId(comments.getId());
        response.setComment(comments.getComment());
        response.setUsername(comments.getUser().getUsername());
        response.setProductId(comments.getProduct().getId());
        response.setParentId(
                comments.getParent() != null ? comments.getParent().getId() : null
        );

        if (!comments.getResponses().isEmpty()) {
            for (Comments _comments : comments.getResponses()) {
                response.getResponses().add(castResponseCommentsListDto(_comments));
            }
        }

        response.setCreatedAt(comments.getCreatedAt());
        response.setUpdatedAt(comments.getUpdatedAt());

        return response;
    }

    public List<ResponseCommentsDto> castResponseCommentsList(List<Comments> comments) {
        if (comments == null) {
            throw new CommentNotFound();
        }

        List<ResponseCommentsDto> response = new ArrayList<>();

        for (Comments comment : comments) {
            response.add(castCommentsData(comment));
        }

        return response;
    }

    public ResponseCommentsListDto castResponseCommentsListDto(Comments comments) {
        ResponseCommentsListDto response = new ResponseCommentsListDto();
        response.setId(comments.getId());
        response.setComment(comments.getComment());
        response.setUsername(comments.getUser().getUsername());
        response.setCreatedAt(comments.getCreatedAt());
        response.setUpdatedAt(comments.getUpdatedAt());

        return response;
    }
}
