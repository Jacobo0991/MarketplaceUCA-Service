package com.marketplace.backend.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReviewDto {

    private UUID id;
    private Integer rating;
    private String comment;

    private UUID reviewerId;
    private String reviewerUsername;

    private UUID revieweeId;
    private String revieweeUsername;

    private LocalDateTime createdAt;
}
