package com.marketplace.backend.domain.dto.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDto {

    @JsonProperty("revieweeEmail")
    @NotNull
    @NotBlank
    private String revieweeId;   // email del vendedor (usuario que recibe la reseña)

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
