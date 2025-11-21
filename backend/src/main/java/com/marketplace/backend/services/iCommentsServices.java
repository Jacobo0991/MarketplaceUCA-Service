package com.marketplace.backend.services;

import com.marketplace.backend.domain.dto.comments.CreateCommentsDto;
import com.marketplace.backend.domain.dto.comments.ResponseCommentsDto;
import com.marketplace.backend.domain.dto.comments.UpdateCommentsDto;
import com.marketplace.backend.domain.entities.User;

import java.util.List;

public interface iCommentsServices {

    User getUserSession();
    ResponseCommentsDto addComment(CreateCommentsDto comments);
    ResponseCommentsDto updateComment(UpdateCommentsDto comments);
    ResponseCommentsDto getCommentsById(String id);
    List<ResponseCommentsDto> getCommentsByProductId(String id);
    List<ResponseCommentsDto> getCommentsByUser();
    String deleteComment(String id);
}
