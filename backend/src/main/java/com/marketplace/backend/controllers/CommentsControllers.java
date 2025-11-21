package com.marketplace.backend.controllers;

import com.marketplace.backend.domain.dto.GeneralResponse;
import com.marketplace.backend.domain.dto.comments.CreateCommentsDto;
import com.marketplace.backend.domain.dto.comments.ResponseCommentsDto;
import com.marketplace.backend.domain.dto.comments.UpdateCommentsDto;
import com.marketplace.backend.services.iCommentsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsControllers {

    private final iCommentsServices commentsServices;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createComment(@RequestBody CreateCommentsDto commentsDto) {
        ResponseCommentsDto response = commentsServices.addComment(commentsDto);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.CREATED)
                .build();
    }

    @PatchMapping("/update")
    public ResponseEntity<GeneralResponse> updateComment(@RequestBody UpdateCommentsDto commentsDto) {
        ResponseCommentsDto response = commentsServices.updateComment(commentsDto);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getCommentsById(@PathVariable String id) {
        ResponseCommentsDto response = commentsServices.getCommentsById(id);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<GeneralResponse> getCommentsByProductId(@PathVariable String id) {
        List<ResponseCommentsDto> response = commentsServices.getCommentsByProductId(id);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/user")
    public ResponseEntity<GeneralResponse> getCommentsByUserId() {
        List<ResponseCommentsDto> response = commentsServices.getCommentsByUser();

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteCommentsById(@PathVariable String id) {
        String response = commentsServices.deleteComment(id);

        return GeneralResponse.builder()
                .message(response)
                .status(HttpStatus.OK)
                .build();
    }
}
