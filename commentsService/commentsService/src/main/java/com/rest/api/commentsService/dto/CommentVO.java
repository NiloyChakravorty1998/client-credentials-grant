package com.rest.api.commentsService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;
}
