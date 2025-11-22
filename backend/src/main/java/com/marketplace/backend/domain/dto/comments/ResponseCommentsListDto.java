package com.marketplace.backend.domain.dto.comments;

import com.marketplace.backend.domain.entities.Comments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommentsListDto {

    private UUID id;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private UUID parentId;
    private UUID productId;
}
