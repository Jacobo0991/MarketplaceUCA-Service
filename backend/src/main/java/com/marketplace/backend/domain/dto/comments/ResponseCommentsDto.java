package com.marketplace.backend.domain.dto.comments;

import jakarta.annotation.Nullable;
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
    private String username;
    private UUID parentId;
    private UUID productId;
}
