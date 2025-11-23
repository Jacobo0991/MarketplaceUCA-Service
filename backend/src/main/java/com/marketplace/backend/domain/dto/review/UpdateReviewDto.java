package com.marketplace.backend.domain.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReviewDto {

    @JsonProperty("reviewId")
    @NotNull
    @NotBlank
    private String reviewId;

    @JsonProperty("rating")
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    @JsonProperty("comment")
    @NotNull
    @NotBlank
    @Size(min = 1, max = 250)
    private String comment;
}
