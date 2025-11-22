package com.marketplace.backend.domain.dto.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommentsDto {

    private UUID id;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private Integer responseCount;
    private String username;
    private UUID parentId;
    private UUID productId;
}
