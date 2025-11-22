package com.marketplace.backend.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReviewDto {

    private UUID id;
    private Integer rating;
    private String comment;
    private String reviewer;
    private UUID productId;
}
