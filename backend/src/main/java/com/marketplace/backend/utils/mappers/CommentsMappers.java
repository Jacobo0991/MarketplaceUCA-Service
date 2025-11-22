package com.marketplace.backend.utils.mappers;

import com.marketplace.backend.domain.dto.comments.ResponseCommentsDto;
import com.marketplace.backend.domain.entities.Comments;
import com.marketplace.backend.exceptions.comment.CommentNotFound;
import com.marketplace.backend.utils.PrettyUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CommentsMappers {

    private PrettyUtils  prettyUtils;

    public ResponseCommentsDto castCommentsData(Comments comments) {
        ResponseCommentsDto response = new ResponseCommentsDto();
        response.setId(comments.getId());
        response.setComment(comments.getComment());
        response.setUsername(comments.getUser().getUsername());
        response.setProductId(comments.getProduct().getId());
        response.setParentId(
                comments.getParent() != null ? comments.getParent().getId() : null
        );
        response.setResponseCount(comments.getResponseCount());
        response.setCreatedAt(prettyUtils.generateTimeAgo(comments.getCreatedAt()));
        response.setUpdatedAt(prettyUtils.generateTimeAgo(comments.getUpdatedAt()));

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
}
