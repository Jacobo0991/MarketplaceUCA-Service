package com.marketplace.backend.controllers;

import com.marketplace.backend.domain.dto.GeneralResponse;
import com.marketplace.backend.domain.dto.review.CreateReviewDto;
import com.marketplace.backend.domain.dto.review.ResponseReviewDto;
import com.marketplace.backend.domain.dto.review.UpdateReviewDto;
import com.marketplace.backend.services.iReviewServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final iReviewServices reviewServices;


    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createReview(@RequestBody CreateReviewDto reviewDto) {
        ResponseReviewDto response = reviewServices.addReview(reviewDto);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getReviewById(@PathVariable String id) {
        ResponseReviewDto response = reviewServices.getReviewById(id);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<GeneralResponse> getReviewsByProductId(@PathVariable String id) {
        List<ResponseReviewDto> response = reviewServices.getReviewsByProductId(id);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping("/seller/{username}")
    public ResponseEntity<GeneralResponse> getReviewsBySellerUsername(@PathVariable String username) {
        List<ResponseReviewDto> response = reviewServices.getReviewsBySellerUsername(username);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping("/user")
    public ResponseEntity<GeneralResponse> getReviewsByUser() {
        List<ResponseReviewDto> response = reviewServices.getReviewsByUser();

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/update")
    public ResponseEntity<GeneralResponse> updateReview(@RequestBody UpdateReviewDto reviewDto) {
        ResponseReviewDto response = reviewServices.updateReview(reviewDto);

        return GeneralResponse.builder()
                .data(response)
                .message("Ok")
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteReview(@PathVariable String id) {
        String message = reviewServices.deleteReview(id);

        return GeneralResponse.builder()
                .message(message)
                .status(HttpStatus.OK)
                .build();
    }
}
